package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;

/**
 * Services for calculating a power plants electricity generated annually,
 * direct emissions of carbon dioxide, global warming potential and total LCoE.
 * 
 * @author NMarlor
 */
public interface CalculationsService
{
	/**
	 * To calculate the electricity generated annually, based on a 
	 * power plants capacity & capacity factor.
	 * @param powerPlant
	 * @return electricity generated annually
	 */
	public int electricityGeneratedAnnually(PowerPlant powerPlant);
	
	/**
	 * To calculate the direct emissions of CO2, based on a power plants 
	 * electricity generated annually * a specified amount for each fuel type. 
	 * @param powerPlant
	 * @return direct emissions of CO2
	 */
	public int directEmissionsOfCarbonDioxide(PowerPlant powerPlant);
	
	/**
	 * To calculate the global warming potential of a power plant, based on its 
	 * electricity generated annually * a specified amount for each fuel type.
	 * @param powerPlant
	 * @return global warming potential
	 */
	public int globalWarmingPotential(PowerPlant powerPlant);
	
	/**
	 * To calculate the total LCoE of a power plant, based on its
	 * electricity generated annually * a specified amount for each fuel type.
	 * @param powerPlant
	 * @return total LCoE
	 */
	public BigInteger totalLcoe(PowerPlant powerPlant);
	
	/**
	 * For returning the total sum of the electricity generated annually
	 * of all power plants in a country for a particular year.
	 * @param country
	 * @param year
	 * @return electricity generated annually
	 */
	public int totalElectricityGeneratedAnnuallyByCountryandYear(String country, String year);
	
	/**
	 * For returning the total sum of direct emissions of CO2
	 * for all power plants in a country for a particular year.
	 * @param country
	 * @param year
	 * @return direct emissions of CO2
	 */
	public int totalDirectEmissionsOfCarbonDioxideByCountryAndYear(String country, String year);
	
	/**
	 * For returning the total sum of global warming potential
	 * for all power plants in a country for a particular year.
	 * @param country
	 * @param year
	 * @return global warming potential
	 */
	public int totalGlobalWarmingPotentialByCountryAndYear(String country, String year);
	
	/**
	 * For returning the total sum of all total LCoE's
	 * for all power plants in a country for a particular year.
	 * @param country
	 * @param year
	 * @return total LCoE
	 */
	public BigInteger totalOfAllLcoeByCountryAndYear(String country, String year);
	
}
