package ostracon.ostracon_project.power_plants;

import java.util.List;

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
	
}
