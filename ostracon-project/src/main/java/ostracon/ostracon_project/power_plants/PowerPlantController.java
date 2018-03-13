package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ostracon.ostracon_project.account.Account;
import ostracon.ostracon_project.account.AccountRepository;

@Controller
public class PowerPlantController {
	
	@Autowired
	private PowerPlantService powerPlantService;
	
	@Autowired
	private PowerPlantDAO powerPlantDAO;
	
	@Autowired
	private CalculationsService calculationsService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PowerPlantValidator powerPlantValidator;

	@RequestMapping(value = "addPowerPlant", method = RequestMethod.GET)
	public ModelAndView addPowerPlant(){
		ModelAndView mv = new ModelAndView("power_plants/newPowerPlant");
		
		NewPowerPlantForm powerPlantForm = new NewPowerPlantForm();
		
		FuelType[] fuelTypeValues = FuelType.values();
		ArrayList<String> fuelTypes = new ArrayList<>();
		for (FuelType fuelType : fuelTypeValues) {
			fuelTypes.add(fuelType.getPrettyName());
		}
		
		powerPlantForm.setFuleTypes(fuelTypes);
		
		mv.addObject("powerPlantForm", powerPlantForm);
		mv.addObject("fuelTypes", fuelTypes);
		return mv;
	}
	
	@RequestMapping(value = "addPowerPlant", method = RequestMethod.POST)
	public ModelAndView submitPowerPlant(@ModelAttribute("powerPlantForm") NewPowerPlantForm powerPlantForm, Principal principal, BindingResult bindingResult){
		String accountName = principal.getName();
		Account account = accountRepository.findByEmail(accountName);
		
		ModelAndView thisMv = new ModelAndView("power_plants/newPowerPlant");
		thisMv.addObject("powerPlantForm", powerPlantForm);
		
		powerPlantValidator.validate(powerPlantForm, bindingResult);
		if (bindingResult.hasErrors()) {
			thisMv.addObject("errors", bindingResult);
			return thisMv;
		}
		
		PowerPlant powerPlant = new PowerPlant();
		powerPlant.setAccount(account);
		powerPlant.setName(powerPlantForm.getName());
		powerPlant.setYear(powerPlantForm.getYear());
		powerPlant.setCountry(powerPlantForm.getCountry());
		powerPlant.setCity(powerPlantForm.getCity());
		powerPlant.setLatitude(powerPlantForm.getLatitude());
		powerPlant.setLongitude(powerPlantForm.getLongitude());
		powerPlant.setFuelType(powerPlantForm.getFuelType());
		powerPlant.setCapacity(powerPlantForm.getCapacity());
		powerPlant.setCapacityFactor(powerPlantForm.getCapacityFactor());
		
		int electricityGenerated = calculationsService.electricityGeneratedAnnually(powerPlant);
		powerPlant.setAnualElectricityGenerated(electricityGenerated);
		powerPlantForm.setAnualElectricityGenerated(electricityGenerated);
		
		int directEmissions = calculationsService.directEmissionsOfCarbonDioxide(powerPlant);
		powerPlant.setDirectEmissions(directEmissions);
		powerPlantForm.setDirectEmissions(directEmissions);
		
		int globalWarming = calculationsService.globalWarmingPotential(powerPlant);
		powerPlant.setGlobalWarmingPotential(globalWarming);
		powerPlantForm.setGlobalWarmingPotential(globalWarming);
		
		BigInteger totalLcoe = calculationsService.totalLcoe(powerPlant);
		powerPlant.setTotalLcoe(totalLcoe);
		powerPlantForm.setTotalLcoe(totalLcoe);
		
		powerPlantService.createPowerPlant(powerPlant);
		
		String year = powerPlantForm.getYear();
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlantsForAccountAndYear(account, year);
		ArrayList<String> years = powerPlantService.getYearsForPowerPlants(account);
		
		PowerPlantsArrayForm arrayForm = new PowerPlantsArrayForm();
		
		JsonArray jsonArray = new JsonArray();
		for (PowerPlant retrievedPowerPlant : powerPlants) {
			JsonObject jsonObject = new JsonObject();
			try {
				jsonObject.addProperty("name", retrievedPowerPlant.getName());
				jsonObject.addProperty("lat", retrievedPowerPlant.getLatitude());
				jsonObject.addProperty("lng", retrievedPowerPlant.getLongitude());
				jsonObject.addProperty("type", retrievedPowerPlant.getFuelType());
				jsonObject.addProperty("annual", retrievedPowerPlant.getAnualElectricityGenerated());
				jsonObject.addProperty("emissions", retrievedPowerPlant.getDirectEmissions());
				jsonObject.addProperty("warming", retrievedPowerPlant.getGlobalWarmingPotential());
				jsonObject.addProperty("lcoe", retrievedPowerPlant.getTotalLcoe());
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonArray.add(jsonObject);
		}
		
		Gson gson = new Gson();
		String jsonStringArray = gson.toJson(jsonArray);
		arrayForm.setJsonStringArray(jsonStringArray);
		arrayForm.setYear(year);
		arrayForm.setYears(years);
		
		ModelAndView successMv = new ModelAndView("home/homepage");
		successMv.addObject("arrayForm", arrayForm);
		successMv.addObject("jsonStringArray", jsonStringArray);
		successMv.addObject("year", year);
		successMv.addObject("years", years);
		
		return successMv;
	}
	
	@RequestMapping(value = "myPowerPlants", method = RequestMethod.GET)
	public ModelAndView myPowerPlants(Principal principal){
		ModelAndView mv = new ModelAndView("power_plants/allPlants");
		
		YearSearchForm yearSearch = new YearSearchForm();
		
		String accountName = principal.getName();
		Account account = accountRepository.findByEmail(accountName);
		String year = powerPlantService.getCurrentYear();
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlantsForAccountAndYear(account, year);
		ArrayList<String> years = powerPlantService.getYearsForPowerPlants(account);
		
		ArrayList<String> countries = powerPlantService.getCountriesForYear(account, year);
		
		yearSearch.setYear(year);
		yearSearch.setYears(years);
		yearSearch.setCountries(countries);
		
		mv.addObject("powerPlants", powerPlants);
		mv.addObject("year", year);
		mv.addObject("years", years);
		mv.addObject("yearSearch", yearSearch);
		mv.addObject("countries", countries);
		return mv;
	}
	
	@RequestMapping(value = "powerPlantsForYear", method = RequestMethod.POST)
	public ModelAndView powerPlantsForYear(@ModelAttribute("yearSearch") YearSearchForm yearSearch, Principal principal, BindingResult bindingResult){
		ModelAndView mv = new ModelAndView("power_plants/allPlants");
		
		String accountName = principal.getName();
		Account account = accountRepository.findByEmail(accountName);
		String year = yearSearch.getYear();
		ArrayList<String> years = powerPlantService.getYearsForPowerPlants(account);
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlantsForAccountAndYear(account, year);
		ArrayList<String> countries = powerPlantService.getCountriesForYear(account, year);
		
		yearSearch.setYear(year);
		yearSearch.setYears(years);
		yearSearch.setCountries(countries);
		
		mv.addObject("powerPlants", powerPlants);
		mv.addObject("yearSearch", yearSearch);
		mv.addObject("year", year);
		mv.addObject("years", years);
		mv.addObject("countries", countries);
		return mv;
	}
	
	@RequestMapping(value = "resultsForCountryAndYear", method = RequestMethod.POST)
	public ModelAndView resultsForCountryAndYear(@ModelAttribute("yearSearch") YearSearchForm yearSearch, Principal principal, BindingResult bindingResult){
		ModelAndView mv = new ModelAndView("power_plants/resultsForCountryAndYear");
		
		String accountName = principal.getName();
		Account account = accountRepository.findByEmail(accountName);
		String year = yearSearch.getYear();
		String country = yearSearch.getCountry();
		ArrayList<String> countries = powerPlantService.getCountriesForYear(account, year);
		ArrayList<PowerPlant> powerPlants = (ArrayList<PowerPlant>) powerPlantDAO.findPowerPlantsByCountryAndYear(country, year, account);
		
		int electricityGenerated = calculationsService.totalElectricityGeneratedAnnuallyByCountryandYear(country, year, account);
		int carbonDioxide = calculationsService.totalDirectEmissionsOfCarbonDioxideByCountryAndYear(country, year, account);
		int globalWarming = calculationsService.totalGlobalWarmingPotentialByCountryAndYear(country, year, account);
		BigInteger totalLcoe = calculationsService.totalOfAllLcoeByCountryAndYear(country, year, account);
		
		yearSearch.setYear(year);
		yearSearch.setElectricityGenerated(electricityGenerated);
		yearSearch.setCarbonDioxide(carbonDioxide);
		yearSearch.setGlobalWarming(globalWarming);
		yearSearch.setTotalLcoe(totalLcoe);
		yearSearch.setCountry(country);
		yearSearch.setCountries(countries);
		yearSearch.setPowerPlants(powerPlants);
		
		mv.addObject("powerPlants", powerPlants);
		mv.addObject("yearSearch", yearSearch);
		mv.addObject("year", year);
		mv.addObject("country", country);
		mv.addObject("countries", countries);
		return mv;
	}
	
	@RequestMapping(value = "editPowerPlant", method = RequestMethod.GET)
	public ModelAndView editPowerPlantRequest(Long id){
		ModelAndView mv = new ModelAndView("power_plants/editPowerPlant");
		
		PowerPlant powerPlant = powerPlantService.retrievePowerPlant(id);
		
		FuelType[] fuelTypeValues = FuelType.values();
		ArrayList<String> fuelTypes = new ArrayList<>();
		for (FuelType fuelType : fuelTypeValues) {
			fuelTypes.add(fuelType.getPrettyName());
		}
		
		NewPowerPlantForm powerPlantForm = new NewPowerPlantForm();
		powerPlantForm.setPlantId(id);
		powerPlantForm.setName(powerPlant.getName());
		powerPlantForm.setFuelType(powerPlant.getFuelType());
		powerPlantForm.setYear(powerPlant.getYear());
		powerPlantForm.setCountry(powerPlant.getCountry());
		powerPlantForm.setCity(powerPlant.getCity());
		powerPlantForm.setLatitude(powerPlant.getLatitude());
		powerPlantForm.setLongitude(powerPlant.getLongitude());
		powerPlantForm.setCapacity(powerPlant.getCapacity());
		powerPlantForm.setCapacityFactor(powerPlant.getCapacityFactor());
		powerPlantForm.setFuleTypes(fuelTypes);
		
		mv.addObject("powerPlantForm", powerPlantForm);
		mv.addObject("powerPlant", powerPlant);
		mv.addObject("fuelTypes", fuelTypes);
		return mv;
	}
	
	@RequestMapping(value = "editPowerPlant", method = RequestMethod.POST)
	public ModelAndView editPowerPlant(@ModelAttribute("powerPlantForm") NewPowerPlantForm powerPlantForm, BindingResult result, HttpServletRequest request){
		
		PowerPlant powerPlant = powerPlantService.retrievePowerPlant(powerPlantForm.getPlantId());
		powerPlant.setName(powerPlantForm.getName());
		powerPlant.setFuelType(powerPlantForm.getFuelType());
		powerPlant.setYear(powerPlantForm.getYear());
		powerPlant.setCountry(powerPlantForm.getCountry());
		powerPlant.setCity(powerPlantForm.getCity());
		powerPlant.setLatitude(powerPlantForm.getLatitude());
		powerPlant.setLongitude(powerPlantForm.getLongitude());
		powerPlant.setCapacity(powerPlantForm.getCapacity());
		powerPlant.setCapacityFactor(powerPlantForm.getCapacityFactor());
		
		int electricityGenerated = calculationsService.electricityGeneratedAnnually(powerPlant);
		powerPlant.setAnualElectricityGenerated(electricityGenerated);
		
		int directEmissions = calculationsService.directEmissionsOfCarbonDioxide(powerPlant);
		powerPlant.setDirectEmissions(directEmissions);
		
		int globalWarming = calculationsService.globalWarmingPotential(powerPlant);
		powerPlant.setGlobalWarmingPotential(globalWarming);
		
		BigInteger totalLcoe = calculationsService.totalLcoe(powerPlant);
		powerPlant.setTotalLcoe(totalLcoe);
		
		powerPlantService.updatePowerPlant(powerPlant);
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setModelKey("redirect");
		return new ModelAndView (jsonView, "redirect", request.getContextPath() + "power_plants/allPlants");
	}

	@RequestMapping(value = "viewPowerPlant", method = RequestMethod.GET)
	public ModelAndView viewPowerPlantRequest(Long id){
		ModelAndView mv = new ModelAndView("power_plants/viewPowerPlant");
		
		PowerPlant powerPlant = powerPlantService.retrievePowerPlant(id);
		
		NewPowerPlantForm powerPlantForm = new NewPowerPlantForm();
		powerPlantForm.setPlantId(id);
		powerPlantForm.setName(powerPlant.getName());
		powerPlantForm.setAnualElectricityGenerated(powerPlant.getAnualElectricityGenerated());
		powerPlantForm.setDirectEmissions(powerPlant.getDirectEmissions());
		powerPlantForm.setGlobalWarmingPotential(powerPlant.getGlobalWarmingPotential());
		powerPlantForm.setTotalLcoe(powerPlant.getTotalLcoe());
		
		mv.addObject("powerPlantForm", powerPlantForm);
		mv.addObject("powerPlant", powerPlant);
		return mv;
	}
	
	@RequestMapping(value = "deletePowerPlant", method = RequestMethod.GET)
	public ModelAndView deletePowerPlantRequest(Long id){
		ModelAndView mv = new ModelAndView("power_plants/deletePowerPlant");
		
		PowerPlant powerPlant = powerPlantService.retrievePowerPlant(id);
		
		NewPowerPlantForm powerPlantForm = new NewPowerPlantForm();
		powerPlantForm.setPlantId(id);
		powerPlantForm.setName(powerPlant.getName());
		
		mv.addObject("powerPlantForm", powerPlantForm);
		mv.addObject("powerPlant", powerPlant);
		return mv;
	}
	
	@RequestMapping(value = "deletePowerPlant", method = RequestMethod.POST)
	public ModelAndView deletePowerPlant(@ModelAttribute("powerPlantForm") NewPowerPlantForm powerPlantForm, BindingResult result, HttpServletRequest request){
		PowerPlant powerPlant = powerPlantService.retrievePowerPlant(powerPlantForm.getPlantId());
		
		powerPlantService.deletePowerPlant(powerPlant);
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setModelKey("redirect");
		return new ModelAndView (jsonView, "redirect", request.getContextPath() + "power_plants/allPlants");
	}

}
