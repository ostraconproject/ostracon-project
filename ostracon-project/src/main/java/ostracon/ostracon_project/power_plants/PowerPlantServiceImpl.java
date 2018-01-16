package ostracon.ostracon_project.power_plants;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
