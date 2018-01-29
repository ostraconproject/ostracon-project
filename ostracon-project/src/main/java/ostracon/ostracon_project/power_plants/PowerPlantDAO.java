package ostracon.ostracon_project.power_plants;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

public interface PowerPlantDAO extends GenericDAO<PowerPlant, Long> 
{
	/**
	 * For finding all of the power plants within a country.
	 * @param country
	 * @return power plants
	 */
	List<PowerPlant> findPowerPlantsByCountry(String country);
	
	/**
	 * For finding all the power plants within a country for a particular year.
	 * @param country
	 * @param year
	 * @return power plants
	 */
	List<PowerPlant> findPowerPlantsByCountryAndYear(String country, Integer year);
}
