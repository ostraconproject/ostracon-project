package ostracon.ostracon_project.account;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
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
	
	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public ModelAndView changePasswordRequest(Long id){
		ModelAndView mv = new ModelAndView("account/changePassword");
		
		Account account = accountService.retrieveAccount(id);
		ChangePasswordForm passwordForm = new ChangePasswordForm();
		passwordForm.setAccountId(account.getId());
		
		mv.addObject("passwordForm", passwordForm);
		mv.addObject("account", account);
		return mv;
	}
	
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute("passwordForm") ChangePasswordForm passwordForm, BindingResult result, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("account/changePassword");
		Long accountId = passwordForm.getAccountId();
		Account account = accountService.retrieveAccount(accountId);
		String oldPassword = passwordForm.getOldPassword();
		String newPassword = passwordForm.getNewPassword();
		String actualPassword = account.getPassword();
		String newEncryptedPassword = passwordEncoder.encode(newPassword);
		
		if (passwordEncoder.matches(oldPassword, actualPassword) && !passwordEncoder.matches(newPassword, actualPassword)) {
			account.setPassword(newEncryptedPassword);
			accountService.updateAccount(account);
		}
		return mv;
	}
}
