package ostracon.ostracon_project.home;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ostracon.ostracon_project.account.Account;
import ostracon.ostracon_project.account.AccountRepository;
import ostracon.ostracon_project.power_plants.PowerPlant;
import ostracon.ostracon_project.power_plants.PowerPlantService;
import ostracon.ostracon_project.power_plants.PowerPlantsArrayForm;

@Controller
public class HomeController {
	
	@Autowired
	private PowerPlantService powerPlantService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		//if the user isn't signed in, redirect to splash screen
		if (principal == null) {
			ModelAndView mv = new ModelAndView("home/homeNotSignedIn");
			return mv;
		}
		ModelAndView mv = new ModelAndView("home/homepage");
		
		String accountName = principal.getName();
		Account account = accountRepository.findByEmail(accountName);
		String year = powerPlantService.getCurrentYear();
		PowerPlantsArrayForm arrayForm = new PowerPlantsArrayForm();
		
		JsonArray jsonArray = new JsonArray();
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlantsForAccountAndYear(account, year);
		for (PowerPlant powerPlant : powerPlants) {
			JsonObject jsonObject = new JsonObject();
			try {
				jsonObject.addProperty("name", powerPlant.getName());
				jsonObject.addProperty("lat", powerPlant.getLatitude());
				jsonObject.addProperty("lng", powerPlant.getLongitude());
				jsonObject.addProperty("type", powerPlant.getFuelType());
				jsonObject.addProperty("annual", powerPlant.getAnualElectricityGenerated());
				jsonObject.addProperty("emissions", powerPlant.getDirectEmissions());
				jsonObject.addProperty("warming", powerPlant.getGlobalWarmingPotential());
				jsonObject.addProperty("lcoe", powerPlant.getTotalLcoe());
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonArray.add(jsonObject);
		}
		
		Gson gson = new Gson();
		String jsonStringArray = gson.toJson(jsonArray);
		arrayForm.setJsonStringArray(jsonStringArray);
		
		ArrayList<String> years = powerPlantService.getYearsForPowerPlants(account);
		Collections.sort(years);
		
		arrayForm.setYear(year);
		arrayForm.setYears(years);
		
		mv.addObject("arrayForm", arrayForm);
		mv.addObject("jsonStringArray", jsonStringArray);
		mv.addObject("year", year);
		mv.addObject("years", years);
		return mv;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView index(@Valid @ModelAttribute("arrayForm") PowerPlantsArrayForm arrayForm, Principal principal, BindingResult bindingResult){
		ModelAndView mv = new ModelAndView("home/homepage");
		
		String accountName = principal.getName();
		Account account = accountRepository.findByEmail(accountName);
		String year = arrayForm.getYear();
		ArrayList<String> years = powerPlantService.getYearsForPowerPlants(account);
		Collections.sort(years);
		
		JsonArray jsonArray = new JsonArray();
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlantsForAccountAndYear(account, year);
		for (PowerPlant powerPlant : powerPlants) {
			JsonObject jsonObject = new JsonObject();
			try {
				jsonObject.addProperty("name", powerPlant.getName());
				jsonObject.addProperty("lat", powerPlant.getLatitude());
				jsonObject.addProperty("lng", powerPlant.getLongitude());
				jsonObject.addProperty("type", powerPlant.getFuelType());
				jsonObject.addProperty("annual", powerPlant.getAnualElectricityGenerated());
				jsonObject.addProperty("emissions", powerPlant.getDirectEmissions());
				jsonObject.addProperty("warming", powerPlant.getGlobalWarmingPotential());
				jsonObject.addProperty("lcoe", powerPlant.getTotalLcoe());
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
		
		mv.addObject("arrayForm", arrayForm);
		mv.addObject("jsonStringArray", jsonStringArray);
		mv.addObject("year", year);
		mv.addObject("years", years);
		return mv;
	}
}
