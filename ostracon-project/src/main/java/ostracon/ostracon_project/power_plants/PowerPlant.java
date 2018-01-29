package ostracon.ostracon_project.power_plants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "power_plants")
public class PowerPlant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String city;
	
	@Column
	private String country;
	
	@Column
	private Integer year;
	
	@Column
	private String coordinates;
	
	@Column(name = "fuel_type")
	private String fuelType;
	
	@Column
	private double capacity;
	
	@Column(name = "capacity_factor")
	private double capacityFactor;

	@Column(name = "anual_electricity_generated")
	private double anualElectricityGenerated;
	
	@Column(name = "direct_emissions")
	private double directEmissions;
	
	@Column(name = "global_warming_potential")
	private double globalWarmingPotential;
	
	@Column(name = "total_lcoe")
	private double totalLcoe;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public Integer getYear() {
		return year;
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

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setYear(Integer year) {
		this.year = year;
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
	
}
