package ostracon.ostracon_project.power_plants;

import java.util.ArrayList;

public class NewPowerPlantForm {

	private Long plantId;
	private String name;
	private String country;
	private String city;
	private String year;
	private String coordinates;
	private String fuelType;
	private double capacity;
	private double capacityFactor;
	private double anualElectricityGenerated;
	private double directEmissions;
	private double globalWarmingPotential;
	private double totalLcoe;
	private ArrayList<String> fuleTypes;
	
	public Long getPlantId() {
		return plantId;
	}
	public void setPlantId(Long plantId) {
		this.plantId = plantId;
	}
	public String getName() {
		return name;
	}
	public String getYear() {
		return year;
	}
	public String getCountry() {
		return country;
	}
	public String getCity() {
		return city;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public String getFuelType() {
		return fuelType;
	}
	public double getCapacity() {
		return capacity;
	}
	public double getCapacityFactor() {
		return capacityFactor;
	}
	public double getAnualElectricityGenerated() {
		return anualElectricityGenerated;
	}
	public double getDirectEmissions() {
		return directEmissions;
	}
	public double getGlobalWarmingPotential() {
		return globalWarmingPotential;
	}
	public double getTotalLcoe() {
		return totalLcoe;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public void setCapacityFactor(double capacityFactor) {
		this.capacityFactor = capacityFactor;
	}
	public void setAnualElectricityGenerated(double anualElectricityGenerated) {
		this.anualElectricityGenerated = anualElectricityGenerated;
	}
	public void setDirectEmissions(double directEmissions) {
		this.directEmissions = directEmissions;
	}
	public void setGlobalWarmingPotential(double globalWarmingPotential) {
		this.globalWarmingPotential = globalWarmingPotential;
	}
	public void setTotalLcoe(double totalLcoe) {
		this.totalLcoe = totalLcoe;
	}
	public ArrayList<String> getFuleTypes() {
		return fuleTypes;
	}
	public void setFuleTypes(ArrayList<String> fuleTypes) {
		this.fuleTypes = fuleTypes;
	}

}
