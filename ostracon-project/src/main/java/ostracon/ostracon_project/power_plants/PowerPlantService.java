package ostracon.ostracon_project.power_plants;

import java.util.ArrayList;
import java.util.List;

import ostracon.ostracon_project.account.Account;

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
	 * To retrieve all power plants in the system.
	 * @return powerPlants
	 */
	public List<PowerPlant> retrieveAllPowerPlants();
	
	/**
	 * To retrieve all power plants in the system for an account.
	 * @return powerPlants
	 */
	public List<PowerPlant> retrieveAllPowerPlantsForAccount(Account account);
	
	/**
	 * To retrieve all power plants in the system for an account
	 * and a given year.
	 * @param account
	 * @param year
	 * @return powerPlants
	 */
	public List<PowerPlant> retrieveAllPowerPlantsForAccountAndYear(Account account, String year);
	
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
	
	/**
	 * To get the current year, which will be useful for when 
	 * entering the site and viewing powerPlants.
	 * @return year
	 */
	public String getCurrentYear();

	/**
	 * To get all of the years from an accounts created powerPlants.
	 * @param account
	 * @return years
	 */
	public ArrayList<String> getYearsForPowerPlants(Account account);
	
	public ArrayList<String> getCountriesForYear(Account account, String year);
}
