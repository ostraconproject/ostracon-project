package ostracon.ostracon_project.power_plants;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

import ostracon.ostracon_project.lib.HibernateJPABase;

@Repository
public class PowerPlantDAOImpl extends HibernateJPABase<PowerPlant, Long> implements PowerPlantDAO {

	@Override
	public List<PowerPlant> findPowerPlantsByCountry(String country) {
		Search search = new Search(PowerPlant.class);
		search.addFilterEqual("country", country);
		return super.search(search);
	}

	@Override
	public List<PowerPlant> findPowerPlantsByCountryAndYear(String country, Integer year) {
		Search search = new Search(PowerPlant.class);
		search.addFilterEqual("country", country);
		search.addFilterEqual("year", year);
		return super.search(search);
	}

}
