package ostracon.ostracon_project.account;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDAO accountDAO;
	
	@Override
	public Account retrieveAccount(Long accountId) {
		return accountDAO.find(accountId);
	}

	@Override
	public Account updateAccount(Account account) {
		accountDAO.save(account);
		return account;
	}

	@Override
	public void deleteAccount(Account account) {
		accountDAO.remove(account);
	}

	@Override
	public List<Account> findAllAccounts() {
		return accountDAO.findAll();
	}

}
