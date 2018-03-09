package ostracon.ostracon_project.account;

import java.util.List;

public interface AccountService 
{
	public Account retrieveAccount(Long accountId);
	
	public Account updateAccount(Account account);
	
	public void deleteAccount(Account account);
	
	public List<Account> findAllAccounts();
}
