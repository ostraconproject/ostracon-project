package ostracon.ostracon_project.power_plants;

/**
 * Fuel types the user will be able to select from.
 * 
 * @author NMarlor
 */
public enum FuelType {
	
	BIOMASS("Biomass"),
	COAL("Coal"),
	HYDRO("Hydro"),
	NATURAL_GAS("Natural Gas"),
	SOLAR("Solar"),
	WIND("Wind");
	
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
