package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;
import java.util.ArrayList;

public class YearSearchForm {

	private ArrayList<String> years;
	private String year;
	private ArrayList<String> countries;
	private String country;
	private int electricityGenerated;
	private int carbonDioxide;
	private int globalWarming;
	private BigInteger totalLcoe;
	private ArrayList<PowerPlant> powerPlants;
	
	private String formattedElectricity;
	private String formattedCarbon;
	private String formattedGlobal;
	private String formattedLcoe;
	
	public ArrayList<String> getYears() {
		return years;
	}
	public String getYear() {
		return year;
	}
	public void setYears(ArrayList<String> years) {
		this.years = years;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public ArrayList<String> getCountries() {
		return countries;
	}
	public String getCountry() {
		return country;
	}
	public void setCountries(ArrayList<String> countries) {
		this.countries = countries;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getElectricityGenerated() {
		return electricityGenerated;
	}
	public int getCarbonDioxide() {
		return carbonDioxide;
	}
	public int getGlobalWarming() {
		return globalWarming;
	}
	public BigInteger getTotalLcoe() {
		return totalLcoe;
	}
	public void setElectricityGenerated(int electricityGenerated) {
		this.electricityGenerated = electricityGenerated;
	}
	public void setCarbonDioxide(int carbonDioxide) {
		this.carbonDioxide = carbonDioxide;
	}
	public void setGlobalWarming(int globalWarming) {
		this.globalWarming = globalWarming;
	}
	public void setTotalLcoe(BigInteger totalLcoe) {
		this.totalLcoe = totalLcoe;
	}
	public ArrayList<PowerPlant> getPowerPlants() {
		return powerPlants;
	}
	public void setPowerPlants(ArrayList<PowerPlant> powerPlants) {
		this.powerPlants = powerPlants;
	}
	public String getFormattedElectricity() {
		return formattedElectricity;
	}
	public void setFormattedElectricity(String formattedElectricity) {
		this.formattedElectricity = formattedElectricity;
	}
	public String getFormattedCarbon() {
		return formattedCarbon;
	}
	public String getFormattedGlobal() {
		return formattedGlobal;
	}
	public String getFormattedLcoe() {
		return formattedLcoe;
	}
	public void setFormattedCarbon(String formattedCarbon) {
		this.formattedCarbon = formattedCarbon;
	}
	public void setFormattedGlobal(String formattedGlobal) {
		this.formattedGlobal = formattedGlobal;
	}
	public void setFormattedLcoe(String formattedLcoe) {
		this.formattedLcoe = formattedLcoe;
	}
	
}
