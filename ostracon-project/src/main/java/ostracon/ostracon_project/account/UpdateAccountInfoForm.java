package ostracon.ostracon_project.account;

public class UpdateAccountInfoForm {

	private Long accountId;
	private String firstName;
	private String lastName;
	private String email;
	
	public Long getAccountId() {
		return accountId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
