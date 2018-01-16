package ostracon.ostracon_project.power_plants;

/**
 * Fuel types the user will be able to select from.
 * 
 * @author NMarlor
 */
public enum FuelType {
	
	COAL("Coal"),
	NATURAL_GAS("Natural Gas"),
	WIND("Wind"),
	SOLAR("Solar"),
	HYDRO("Hydro"),
	BIOMASS("Biomass");
	
	private String prettyName;
	
	private FuelType(String prettyName)
	{
		this.prettyName = prettyName;
	}
	
	public String getPrettyName()
	{
		return prettyName;
	}

}
