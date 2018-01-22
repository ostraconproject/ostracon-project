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

}
