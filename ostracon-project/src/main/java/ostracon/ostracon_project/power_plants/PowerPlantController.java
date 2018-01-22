package ostracon.ostracon_project.power_plants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PowerPlantController {
	
	@Autowired
	private PowerPlantService powerPlantService;
	
	@Autowired
	private CalculationsService calculationsService;

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
	public ModelAndView submitPowerPlant(@ModelAttribute("powerPlantForm") NewPowerPlantForm powerPlantForm, BindingResult bindingResult){
		PowerPlant powerPlant = new PowerPlant();
		powerPlant.setName(powerPlantForm.getName());
		powerPlant.setCity(powerPlantForm.getCity());
		powerPlant.setCoordinates(powerPlantForm.getCoordinates());
		powerPlant.setFuelType(powerPlantForm.getFuelType());
		powerPlant.setCapacity(powerPlantForm.getCapacity());
		powerPlant.setCapacityFactor(powerPlantForm.getCapacityFactor());
		
		double electricityGenerated = calculationsService.electricityGeneratedAnnually(powerPlant);
		powerPlant.setAnualElectricityGenerated(electricityGenerated);
		powerPlantForm.setAnualElectricityGenerated(electricityGenerated);
		
		double directEmissions = calculationsService.directEmissionsOfCarbonDioxide(powerPlant);
		powerPlant.setDirectEmissions(directEmissions);
		powerPlantForm.setDirectEmissions(directEmissions);
		
		double globalWarming = calculationsService.globalWarmingPotential(powerPlant);
		powerPlant.setGlobalWarmingPotential(globalWarming);
		powerPlantForm.setGlobalWarmingPotential(globalWarming);
		
		double totalLcoe = calculationsService.totalLcoe(powerPlant);
		powerPlant.setTotalLcoe(totalLcoe);
		powerPlantForm.setTotalLcoe(totalLcoe);
		
		powerPlantService.createPowerPlant(powerPlant);
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlants();
		
		ModelAndView successMv = new ModelAndView("power_plants/successfulPlantCreation");
		successMv.addObject("powerPlant", powerPlant);
		successMv.addObject("powerPlantForm", powerPlantForm);
		successMv.addObject("powerPlants", powerPlants);
		
		return successMv;
	}

}
