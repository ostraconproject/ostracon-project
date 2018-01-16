package ostracon.ostracon_project.power_plants;

import org.springframework.stereotype.Repository;

import ostracon.ostracon_project.lib.HibernateJPABase;

@Repository
public class PowerPlantDAOImpl extends HibernateJPABase<PowerPlant, Long> implements PowerPlantDAO {

}
