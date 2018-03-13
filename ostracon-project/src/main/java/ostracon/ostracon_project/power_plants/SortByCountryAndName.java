package ostracon.ostracon_project.power_plants;

import java.util.Comparator;

public class SortByCountryAndName implements Comparator<PowerPlant> {

	@Override
	public int compare(PowerPlant plant1, PowerPlant plant2) {
		int i = plant1.getCountry().compareTo(plant2.getCountry());
		if (i != 0) return i;
		
		i = plant1.getName().compareTo(plant2.getName());
		if (i != 0) return i;
		
		return i;
	}
	
}
