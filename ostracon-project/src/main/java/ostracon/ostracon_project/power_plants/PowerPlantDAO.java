package ostracon.ostracon_project.power_plants;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

import ostracon.ostracon_project.account.Account;

/**
 * DAO functions to interact with the power plants table
 * within the database.
 * 
 * @author NMarlor
 */
public interface PowerPlantDAO extends GenericDAO<PowerPlant, Long> 
{
	/**
	 * For finding all of the power plants within a country.
	 * @param country
	 * @return power plants
	 */
	public List<PowerPlant> findPowerPlantsByCountry(String country);
	
	/**
	 * For finding all the power plants within a country for a particular year.
	 * @param country
	 * @param year
	 * @param account
	 * @return power plants
	 */
	public List<PowerPlant> findPowerPlantsByCountryAndYear(String country, String year, Account account);
	
	/**
	 * For finding all the power plants for a particular year.
	 * @param year
	 * @return power plants
	 */
	public List<PowerPlant> findPowerPlantsByYear(String year);
	
	/**
	 * For finding all power plants of an account.
	 * @param account
	 * @return power plants
	 */
	public List<PowerPlant> findPowerPlantsByAccount(Account account);
	
	/**
	 * For finding all power plants of an account and a particular year.
	 * @param account
	 * @param year
	 * @return power plants
	 */
	public List<PowerPlant> findPowerPlantsByAccountAndYear(Account account, String year);
}
