package ostracon.ostracon_project.account;

import java.util.Comparator;

public class SortByEmail implements Comparator<Account> {

	@Override
	public int compare(Account account1, Account account2) {
		return account1.getEmail().compareTo(account2.getEmail());
	}

}
