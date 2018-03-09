package ostracon.ostracon_project.power_plants;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;

import ostracon.ostracon_project.account.Account;
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
	public List<PowerPlant> findPowerPlantsByCountryAndYear(String country, String year) {
		Search search = new Search(PowerPlant.class);
		search.addFilterEqual("country", country);
		search.addFilterEqual("year", year);
		return super.search(search);
	}

	@Override
	public List<PowerPlant> findPowerPlantsByYear(String year) {
		Search search = new Search(PowerPlant.class);
		search.addFilterEqual("year", year);
		return super.search(search);
	}

	@Override
	public List<PowerPlant> findPowerPlantsByAccount(Account account) {
		Search search = new Search(PowerPlant.class);
		search.addFilterEqual("account", account);
		return super.search(search);
	}

	@Override
	public List<PowerPlant> findPowerPlantsByAccountAndYear(Account account, String year) {
		Search search = new Search(PowerPlant.class);
		search.addFilterEqual("account", account);
		search.addFilterEqual("year", year);
		return super.search(search);
	}

}
