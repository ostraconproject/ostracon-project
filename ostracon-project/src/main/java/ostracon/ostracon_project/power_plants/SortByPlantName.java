package ostracon.ostracon_project.power_plants;

import java.util.Comparator;

public class SortByPlantName implements Comparator<PowerPlant> {

	@Override
	public int compare(PowerPlant plant1, PowerPlant plant2) {
		return plant1.getName().compareTo(plant2.getName());
	}

}
