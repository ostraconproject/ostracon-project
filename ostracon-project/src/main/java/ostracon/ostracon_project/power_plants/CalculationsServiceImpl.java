package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;
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
	public int electricityGeneratedAnnually(PowerPlant powerPlant) {
		double convertedCapacityFactor = powerPlant.getCapacityFactor();
		convertedCapacityFactor = convertedCapacityFactor / 100;
		
		return (int) (powerPlant.getCapacity() * convertedCapacityFactor * 8760);
	}

	@Override
	public int directEmissionsOfCarbonDioxide(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		int annualElectricity = powerPlant.getAnualElectricityGenerated();
		int directEmissions = 0;
		
		if (fuelType.equals("Coal")) {
			directEmissions = (int) (annualElectricity * 1.047);
		}
		if (fuelType.equals("Natural Gas")) {
			directEmissions = (int) (annualElectricity * 0.9);
		}
		return directEmissions;
	}

	@Override
	public int globalWarmingPotential(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		int annualElectricity = powerPlant.getAnualElectricityGenerated();
		int globalWarmingPotential = 0;
		
		if (fuelType.equals("Coal")) {
			globalWarmingPotential = (int) (annualElectricity * 0.65);
		}
		if (fuelType.equals("Natural Gas")) {
			globalWarmingPotential = (int) (annualElectricity * 0.5);
		}
		if (fuelType.equals("Wind")) {
			globalWarmingPotential = (int) (annualElectricity * 0.038);
		}
		if (fuelType.equals("Solar")) {
			globalWarmingPotential = (int) (annualElectricity * 0.116);
		}
		if (fuelType.equals("Hydro")) {
			globalWarmingPotential = (int) (annualElectricity * 0.03);
		}
		if (fuelType.equals("Biomass")) {
			globalWarmingPotential = (int) (annualElectricity * 0.258);
		}
		
		return globalWarmingPotential;
	}

	@Override
	public BigInteger totalLcoe(PowerPlant powerPlant) {
		String fuelType = powerPlant.getFuelType();
		int annualElectricity = powerPlant.getAnualElectricityGenerated();
		int sum = 0;
		BigInteger totalLcoe = BigInteger.valueOf(0);
		
		if (fuelType.equals("Coal")) {
			sum = annualElectricity * 140;
			totalLcoe = totalLcoe.add(BigInteger.valueOf(sum));
		}
		if (fuelType.equals("Natural Gas")) {
			sum = annualElectricity * 140;
			totalLcoe = totalLcoe.add(BigInteger.valueOf(sum));
		}
		if (fuelType.equals("Wind")) {
			sum = annualElectricity * 90;
			totalLcoe = totalLcoe.add(BigInteger.valueOf(sum));
		}
		if (fuelType.equals("Solar")) {
			sum = annualElectricity * 170;
			totalLcoe = totalLcoe.add(BigInteger.valueOf(sum));
		}
		if (fuelType.equals("Hydro")) {
			sum = annualElectricity * 100;
			totalLcoe = totalLcoe.add(BigInteger.valueOf(sum));
		}
		if (fuelType.equals("Biomass")) {
			sum = annualElectricity * 80;
			totalLcoe = totalLcoe.add(BigInteger.valueOf(sum));
		}
		
		return totalLcoe;
	}

	@Override
	public int totalElectricityGeneratedAnnuallyByCountryandYear(String country, String year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		int annualElectricityGenerated = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			int aeg = powerPlant.getAnualElectricityGenerated();
			annualElectricityGenerated = annualElectricityGenerated + aeg;
		}
		return annualElectricityGenerated;
	}

	@Override
	public int totalDirectEmissionsOfCarbonDioxideByCountryAndYear(String country, String year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		int directEmissionsOfCarbonDioxide = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			int deocd = powerPlant.getDirectEmissions();
			directEmissionsOfCarbonDioxide = directEmissionsOfCarbonDioxide + deocd;
		}
		return directEmissionsOfCarbonDioxide;
	}

	@Override
	public int totalGlobalWarmingPotentialByCountryAndYear(String country, String year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		int globalWarmingPotential = 0;
		
		for (PowerPlant powerPlant : powerPlants) {
			int gwp = powerPlant.getGlobalWarmingPotential();
			globalWarmingPotential = globalWarmingPotential + gwp;
		}
		return globalWarmingPotential;
	}

	@Override
	public BigInteger totalOfAllLcoeByCountryAndYear(String country, String year) {
		List<PowerPlant> powerPlants = powerPlantDAO.findPowerPlantsByCountryAndYear(country, year);
		BigInteger totalLcoe = BigInteger.valueOf(0);
		
		for (PowerPlant powerPlant : powerPlants) {
			BigInteger tlcoe = powerPlant.getTotalLcoe();
			totalLcoe = totalLcoe.add(BigInteger.valueOf(tlcoe.intValue()));
		}
		return totalLcoe;
	}

}
