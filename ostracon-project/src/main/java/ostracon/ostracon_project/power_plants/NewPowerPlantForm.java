package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;
import java.util.ArrayList;

public class NewPowerPlantForm {

	private Long plantId;
	private String name;
	private String country;
	private String city;
	private String year;
	private String coordinates;
	private String fuelType;
	private int capacity;
	private int capacityFactor;
	private int anualElectricityGenerated;
	private int directEmissions;
	private int globalWarmingPotential;
	private BigInteger totalLcoe;
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
	public ArrayList<String> getFuleTypes() {
		return fuleTypes;
	}
	public void setFuleTypes(ArrayList<String> fuleTypes) {
		this.fuleTypes = fuleTypes;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getCapacityFactor() {
		return capacityFactor;
	}
	public int getAnualElectricityGenerated() {
		return anualElectricityGenerated;
	}
	public int getDirectEmissions() {
		return directEmissions;
	}
	public int getGlobalWarmingPotential() {
		return globalWarmingPotential;
	}
	public BigInteger getTotalLcoe() {
		return totalLcoe;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setCapacityFactor(int capacityFactor) {
		this.capacityFactor = capacityFactor;
	}
	public void setAnualElectricityGenerated(int anualElectricityGenerated) {
		this.anualElectricityGenerated = anualElectricityGenerated;
	}
	public void setDirectEmissions(int directEmissions) {
		this.directEmissions = directEmissions;
	}
	public void setGlobalWarmingPotential(int globalWarmingPotential) {
		this.globalWarmingPotential = globalWarmingPotential;
	}
	public void setTotalLcoe(BigInteger totalLcoe) {
		this.totalLcoe = totalLcoe;
	}
	
	

}
