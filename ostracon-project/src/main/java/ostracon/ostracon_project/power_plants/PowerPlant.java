package ostracon.ostracon_project.power_plants;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ostracon.ostracon_project.account.Account;

@Entity
@Table(name = "power_plants")
public class PowerPlant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="account_id", foreignKey=@ForeignKey(name="power_plant_account_id_fk"))
	private Account account;
	
	@Column
	private String name;
	
	@Column
	private String city;
	
	@Column
	private String country;
	
	@Column
	private String year;
	
	@Column
	private String coordinates;
	
	@Column(name = "fuel_type")
	private String fuelType;
	
	@Column
	private int capacity;
	
	@Column(name = "capacity_factor")
	private int capacityFactor;

	@Column(name = "anual_electricity_generated")
	private int anualElectricityGenerated;
	
	@Column(name = "direct_emissions")
	private int directEmissions;
	
	@Column(name = "global_warming_potential")
	private int globalWarmingPotential;
	
	@Column(name = "total_lcoe")
	private BigInteger totalLcoe;

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
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
	
	public String getYear() {
		return year;
	}

	public String getCoordinates() {
		return coordinates;
	}
	
	public String getFuelType() {
		return fuelType;
	}
	
	public void setAccount(Account account) {
		this.account = account;
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
	
	public void setYear(String year) {
		this.year = year;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
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
