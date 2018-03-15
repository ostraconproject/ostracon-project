package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class NewPowerPlantForm {

	private static final String NOT_BLANK_MESSAGE = "{blankField.message}";
	
	private Long plantId;
	
	@NotBlank(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private String name;
	
	@NotBlank(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private String country;
	
	@NotBlank(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private String city;
	
	@NotBlank(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private String year;
	
	@NotBlank(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private String fuelType;
	
	@NotNull(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private Integer capacity;
	
	@NotNull(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private Integer capacityFactor;
	
	@NotNull(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private float latitude;
	
	@NotNull(message = NewPowerPlantForm.NOT_BLANK_MESSAGE)
	private float longitude;
	
	private int anualElectricityGenerated;
	private int directEmissions;
	private int globalWarmingPotential;
	private BigInteger totalLcoe;
	private ArrayList<String> fuleTypes;
	
	private String formattedElectricity;
	private String formattedEmissions;
	private String formattedGlobal;
	private String formattedLcoe;
	
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
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public ArrayList<String> getFuleTypes() {
		return fuleTypes;
	}
	public void setFuleTypes(ArrayList<String> fuleTypes) {
		this.fuleTypes = fuleTypes;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public Integer getCapacityFactor() {
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
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public void setCapacityFactor(Integer capacityFactor) {
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
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getFormattedElectricity() {
		return formattedElectricity;
	}
	public String getFormattedEmissions() {
		return formattedEmissions;
	}
	public String getFormattedGlobal() {
		return formattedGlobal;
	}
	public String getFormattedLcoe() {
		return formattedLcoe;
	}
	public void setFormattedElectricity(String formattedElectricity) {
		this.formattedElectricity = formattedElectricity;
	}
	public void setFormattedEmissions(String formattedEmissions) {
		this.formattedEmissions = formattedEmissions;
	}
	public void setFormattedGlobal(String formattedGlobal) {
		this.formattedGlobal = formattedGlobal;
	}
	public void setFormattedLcoe(String formattedLcoe) {
		this.formattedLcoe = formattedLcoe;
	}
	
}
