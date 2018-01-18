package ostracon.ostracon_project.power_plants;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class CalculationsServiceImpl implements CalculationsService {

	@Override
	public double electricityGeneratedAnnually(PowerPlant powerPlant) {
		return powerPlant.getCapacity() * powerPlant.getCapacityFactor() * 8760;
	}

	@Override
	public double directEmissionsOfCarbonDioxide(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		double annualElectricity = powerPlant.getAnualElectricityGenerated();
		double directEmissions = 0;
		
		if (fuelType == "Coal") {
			directEmissions = annualElectricity * 1.047;
		}
		if (fuelType == "Natural Gas") {
			directEmissions = annualElectricity * 0.9;
		}
		return directEmissions;
	}

	@Override
	public double globalWarmingPotential(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		double annualElectricity = powerPlant.getAnualElectricityGenerated();
		double globalWarmingPotential = 0;
		
		if (fuelType == "Coal") {
			globalWarmingPotential = annualElectricity * 0.65;
		}
		if (fuelType == "Natural Gas") {
			globalWarmingPotential = annualElectricity * 0.5;
		}
		if (fuelType == "Wind") {
			globalWarmingPotential = annualElectricity * 0.038;
		}
		if (fuelType == "Solar") {
			globalWarmingPotential = annualElectricity * 0.116;
		}
		if (fuelType == "Hydro") {
			globalWarmingPotential = annualElectricity * 0.03;
		}
		if (fuelType == "Biomass") {
			globalWarmingPotential = annualElectricity * 0.258;
		}
		
		return globalWarmingPotential;
	}

	@Override
	public double totalLcoe(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		double annualElectricity = powerPlant.getAnualElectricityGenerated();
		double totalLcoe = 0;
		
		if (fuelType == "Coal") {
			totalLcoe = annualElectricity * 140;
		}
		if (fuelType == "Natural Gas") {
			totalLcoe = annualElectricity * 140;
		}
		if (fuelType == "Wind") {
			totalLcoe = annualElectricity * 90;
		}
		if (fuelType == "Solar") {
			totalLcoe = annualElectricity * 170;
		}
		if (fuelType == "Hydro") {
			totalLcoe = annualElectricity * 100;
		}
		if (fuelType == "Biomass") {
			totalLcoe = annualElectricity * 80;
		}
		
		return totalLcoe;
	}

}
