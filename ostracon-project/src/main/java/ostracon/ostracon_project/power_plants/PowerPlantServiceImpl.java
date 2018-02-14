package ostracon.ostracon_project.power_plants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ostracon.ostracon_project.account.Account;

@Service
@Transactional
public class PowerPlantServiceImpl implements PowerPlantService {

	@Autowired
	private PowerPlantDAO powerPlantDAO;

	@Override
	public void createPowerPlant(PowerPlant powerPlant) {
		powerPlantDAO.persist(powerPlant);
	}

	@Override
	public PowerPlant retrievePowerPlant(Long powerPlantId) {
		return powerPlantDAO.find(powerPlantId);
	}

	@Override
	public PowerPlant updatePowerPlant(PowerPlant powerPlant) {
		return powerPlantDAO.save(powerPlant);
	}

	@Override
	public void deletePowerPlant(PowerPlant powerPlant) {
		powerPlantDAO.remove(powerPlant);
	}

	@Override
	public List<PowerPlant> retrieveAllPowerPlants() {
		return powerPlantDAO.findAll();
	}

	@Override
	public List<PowerPlant> retrieveAllPowerPlantsForAccount(Account account) {
		List<PowerPlant> powerPlantsForAccount = powerPlantDAO.findPowerPlantsByAccount(account);
		return powerPlantsForAccount;
	}

	@Override
	public List<PowerPlant> retrieveAllPowerPlantsForAccountAndYear(Account account, String year) {
		List<PowerPlant> powerPlantsForAccountAndYear = powerPlantDAO.findPowerPlantsByAccountAndYear(account, year);
		return powerPlantsForAccountAndYear;
	}

	@Override
	public String getCurrentYear() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		return yearInString;
	}

	@Override
	public ArrayList<String> getYearsForPowerPlants(Account account) {
		List<PowerPlant> powerPlants = retrieveAllPowerPlantsForAccount(account);
		ArrayList<String> years = new ArrayList<>();
		for (PowerPlant powerPlant : powerPlants) {
			years.add(powerPlant.getYear());
		}
		
		//Add to HashSet and then back to ArrayList to delete duplicate years
		Set<String> hs = new HashSet<>();
		hs.addAll(years);
		years.clear();
		years.addAll(hs);
		
		return years;
	}
	
}
