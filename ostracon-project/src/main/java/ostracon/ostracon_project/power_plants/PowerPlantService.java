package ostracon.ostracon_project.power_plants;

/**
 * CRUD services for interacting with the database, in relation
 * to a power plant.
 * 
 * @author NMarlor
 */
public interface PowerPlantService 
{
	/**
	 * To create a power plant and save into the database.
	 * @param powerPlant
	 */
	public void createPowerPlant(PowerPlant powerPlant);
	
	/**
	 * To retrieve a power plant from the database.
	 * @param powerPlantId
	 * @return powerPlant
	 */
	public PowerPlant retrievePowerPlant(Long powerPlantId);
	
	/**
	 * To update a power plants values and save within the database.
	 * @param powerPlant
	 * @return powerPlant
	 */
	public PowerPlant updatePowerPlant(PowerPlant powerPlant);
	
	/**
	 * To delete a power plant from the database.
	 * @param powerPlant
	 */
	public void deletePowerPlant(PowerPlant powerPlant);
}
