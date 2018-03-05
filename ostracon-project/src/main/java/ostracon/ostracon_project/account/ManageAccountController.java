package ostracon.ostracon_project.account;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManageAccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public ModelAndView manageAccount(Principal principal)
	{	
		ModelAndView mv = new ModelAndView("account/myAccount");

		String name = principal.getName();
		Account account = accountRepository.findByEmail(name);
		Long accountId = account.getId();
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		String email = account.getEmail();
		
		UpdateAccountInfoForm accountForm = new UpdateAccountInfoForm();
		accountForm.setAccountId(accountId);
		accountForm.setFirstName(firstName);
		accountForm.setLastName(lastName);
		accountForm.setEmail(email);
		
		mv.addObject("account", account);
		mv.addObject("accountId", accountId);
		mv.addObject("accountForm", accountForm);
		return mv;
	}
	
	@RequestMapping(value = "/myAccount", method = RequestMethod.POST)
	public ModelAndView updateClientAccount(@ModelAttribute("accountForm") UpdateAccountInfoForm accountForm, BindingResult bindingResult)
	{
		Long accountId = accountForm.getAccountId();
		Account account = accountService.retrieveAccount(accountId);
		
		account.setEmail(accountForm.getEmail());
		account.setFirstName(accountForm.getFirstName());
		account.setLastName(accountForm.getLastName());
		accountService.updateAccount(account);
		
		ModelAndView successMv = new ModelAndView("account/successfulAccountUpdate");
		successMv.addObject("accountForm", accountForm);
		successMv.addObject("accountId", accountId);
		successMv.addObject("account", account);
		
		return successMv;
	}
}
