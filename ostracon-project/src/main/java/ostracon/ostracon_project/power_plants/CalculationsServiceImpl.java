package ostracon.ostracon_project.power_plants;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CalculationsServiceImpl implements CalculationsService {
	
	@Autowired
	private PowerPlantDAO powerPlantDAO;

	@Override
	public double electricityGeneratedAnnually(PowerPlant powerPlant) {
		return powerPlant.getCapacity() * powerPlant.getCapacityFactor() * 8760;
	}

	@Override
	public double directEmissionsOfCarbonDioxide(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		double annualElectricity = powerPlant.getAnualElectricityGenerated();
		double directEmissions = 0;
		
		if (fuelType.equals("Coal")) {
			directEmissions = annualElectricity * 1.047;
		}
		if (fuelType.equals("Natural Gas")) {
			directEmissions = annualElectricity * 0.9;
		}
		return directEmissions;
	}

	@Override
	public double globalWarmingPotential(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		double annualElectricity = powerPlant.getAnualElectricityGenerated();
		double globalWarmingPotential = 0;
		
		if (fuelType.equals("Coal")) {
			globalWarmingPotential = annualElectricity * 0.65;
		}
		if (fuelType.equals("Natural Gas")) {
			globalWarmingPotential = annualElectricity * 0.5;
		}
		if (fuelType.equals("Wind")) {
			globalWarmingPotential = annualElectricity * 0.038;
		}
		if (fuelType.equals("Solar")) {
			globalWarmingPotential = annualElectricity * 0.116;
		}
		if (fuelType.equals("Hydro")) {
			globalWarmingPotential = annualElectricity * 0.03;
		}
		if (fuelType.equals("Biomass")) {
			globalWarmingPotential = annualElectricity * 0.258;
		}
		
		return globalWarmingPotential;
	}

	@Override
	public double totalLcoe(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		double annualElectricity = powerPlant.getAnualElectricityGenerated();
		double totalLcoe = 0;
		
		if (fuelType.equals("Coal")) {
			totalLcoe = annualElectricity * 140;
		}
		if (fuelType.equals("Natural Gas")) {
			totalLcoe = annualElectricity * 140;
		}
		if (fuelType.equals("Wind")) {
			totalLcoe = annualElectricity * 90;
		}
		if (fuelType.equals("Solar")) {
			totalLcoe = annualElectricity * 170;
		}
		if (fuelType.equals("Hydro")) {
			totalLcoe = annualElectricity * 100;
		}
		if (fuelType.equals("Biomass")) {
			totalLcoe = annualElectricity * 80;
		}
		
		return totalLcoe;
	}

	@Override
	public double totalElectricityGeneratedAnnuallyByCountryandYear(String country, Integer year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		double annualElectricityGenerated = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			double aeg = powerPlant.getAnualElectricityGenerated();
			annualElectricityGenerated = annualElectricityGenerated + aeg;
		}
		return annualElectricityGenerated;
	}

	@Override
	public double totalDirectEmissionsOfCarbonDioxideByCountryAndYear(String country, Integer year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		double directEmissionsOfCarbonDioxide = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			double deocd = powerPlant.getDirectEmissions();
			directEmissionsOfCarbonDioxide = directEmissionsOfCarbonDioxide + deocd;
		}
		return directEmissionsOfCarbonDioxide;
	}

	@Override
	public double totalGlobalWarmingPotentialByCountryAndYear(String country, Integer year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		double globalWarmingPotential = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			double gwp = powerPlant.getGlobalWarmingPotential();
			globalWarmingPotential = globalWarmingPotential + gwp;
		}
		return globalWarmingPotential;
	}

	@Override
	public double totalOfAllLcoeByCountryAndYear(String country, Integer year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		double totalLcoe = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			double tlcoe = powerPlant.getTotalLcoe();
			totalLcoe = totalLcoe + tlcoe;
		}
		return totalLcoe;
	}

}
