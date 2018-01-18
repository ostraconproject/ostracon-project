package ostracon.ostracon_project.power_plants;

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
	public double electricityGeneratedAnnually(PowerPlant powerPlant);
	
	/**
	 * To calculate the direct emissions of CO2, based on a power plants 
	 * electricity generated annually * a specified amount for each fuel type. 
	 * @param powerPlant
	 * @return direct emissions of CO2
	 */
	public double directEmissionsOfCarbonDioxide(PowerPlant powerPlant);
	
	/**
	 * To calculate the global warming potential of a power plant, based on its 
	 * electricity generated annually * a specified amount for each fuel type.
	 * @param powerPlant
	 * @return global warming potential
	 */
	public double globalWarmingPotential(PowerPlant powerPlant);
	
	/**
	 * To calculate the total LCoE of a power plant, based on its
	 * electricity generated annually * a specified amount for each fuel type.
	 * @param powerPlant
	 * @return total LCoE
	 */
	public double totalLcoe(PowerPlant powerPlant);
}
