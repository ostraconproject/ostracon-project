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

import ostracon.ostracon_project.account.Account;
import ostracon.ostracon_project.account.AccountRepository;

@Controller
public class PowerPlantController {
	
	@Autowired
	private PowerPlantService powerPlantService;
	
	@Autowired
	private CalculationsService calculationsService;
	
	@Autowired
	private AccountRepository accountRepository;

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
		
		PowerPlant powerPlant = new PowerPlant();
		powerPlant.setAccount(account);
		powerPlant.setName(powerPlantForm.getName());
		powerPlant.setYear(powerPlantForm.getYear());
		powerPlant.setCountry(powerPlantForm.getCountry());
		powerPlant.setCity(powerPlantForm.getCity());
		powerPlant.setCoordinates(powerPlantForm.getCoordinates());
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
		
		YearSearchForm yearSearch = new YearSearchForm();
		yearSearch.setYears(years);
		yearSearch.setYear(year);
		
		ModelAndView successMv = new ModelAndView("power_plants/allPlants");
		successMv.addObject("powerPlant", powerPlant);
		successMv.addObject("powerPlantForm", powerPlantForm);
		successMv.addObject("powerPlants", powerPlants);
		successMv.addObject("yearSearch", yearSearch);
		successMv.addObject("years", years);
		successMv.addObject("year", year);
		
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
		
		yearSearch.setYear(year);
		yearSearch.setYears(years);
		
		mv.addObject("powerPlants", powerPlants);
		mv.addObject("year", year);
		mv.addObject("years", years);
		mv.addObject("yearSearch", yearSearch);
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
		
		yearSearch.setYear(year);
		yearSearch.setYears(years);
		
		mv.addObject("powerPlants", powerPlants);
		mv.addObject("yearSearch", yearSearch);
		mv.addObject("year", year);
		mv.addObject("years", years);
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
		powerPlantForm.setCoordinates(powerPlant.getCoordinates());
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
		powerPlant.setCoordinates(powerPlantForm.getCoordinates());
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
