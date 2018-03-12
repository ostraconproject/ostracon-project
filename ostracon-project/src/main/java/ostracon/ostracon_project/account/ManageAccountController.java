package ostracon.ostracon_project.account;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import ostracon.ostracon_project.power_plants.PowerPlant;
import ostracon.ostracon_project.power_plants.PowerPlantService;

@Controller
public class ManageAccountController {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PowerPlantService powerPlantService;
	
	@Autowired
	private AccountService accountService;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/manageAccount", method = RequestMethod.GET)
	public ModelAndView manageAccount(Principal principal)
	{	
		String name = principal.getName();
		Account account = accountRepository.findByEmail(name);
		String role = account.getRole();
		Long accountId = account.getId();
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		String email = account.getEmail();
		
		if (role.equals("ROLE_USER")) {
			ModelAndView mv = new ModelAndView("account/myAccount");
			
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
		if (role.equals("ROLE_ADMIN")) {
			ModelAndView mv = new ModelAndView("account/adminAccount");
			
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
		if (role.equals("ROLE_SUPER_ADMIN")) {
			ModelAndView mv = new ModelAndView("account/superAdminAccount");
			
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

		return new ModelAndView("home/homepage");
	}
	
	@RequestMapping(value = "/manageAccount", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "deleteAccount", method = RequestMethod.GET)
	public ModelAndView deleteAccountRequest(Long id){
		ModelAndView mv = new ModelAndView("account/deleteAccount");
		
		Account account = accountService.retrieveAccount(id);
		
		UpdateAccountInfoForm accountForm = new UpdateAccountInfoForm();
		accountForm.setAccountId(account.getId());
		
		mv.addObject("accountForm", accountForm);
		mv.addObject("account", account);
		
		return mv;
	}
	
	@RequestMapping(value = "deleteAccount", method = RequestMethod.POST)
	public ModelAndView deleteAccount(@ModelAttribute("accountForm") UpdateAccountInfoForm accountForm, BindingResult result, HttpServletRequest request){
		Account account = accountService.retrieveAccount(accountForm.getAccountId());
	
		List<PowerPlant> powerPlants = powerPlantService.retrieveAllPowerPlantsForAccount(account);
		
		if (!powerPlants.isEmpty()) {
			for (PowerPlant powerPlant : powerPlants) {
				powerPlantService.deletePowerPlant(powerPlant);
			}
		}
		
		accountService.deleteAccount(account);
		
		//TODO redirect needs to be fixed, throws null pointer!
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setModelKey("redirect");
		return new ModelAndView (jsonView, "redirect", request.getContextPath()+ "/");
	}
	
	@RequestMapping(value = "addNewAccount", method = RequestMethod.GET)
	public ModelAndView addNewAccount(){
		ModelAndView mv = new ModelAndView("account/addNewAccount");
		
		AdminAccountForm accountForm = new AdminAccountForm();
		
		mv.addObject("accountForm", accountForm);
		return mv;
	}
	
	@RequestMapping(value = "addNewAccount", method = RequestMethod.POST)
	public ModelAndView submitNewAccount(@ModelAttribute("accountForm") AdminAccountForm accountForm, Principal principal, BindingResult bindingResult)
	{	
		ModelAndView mv = new ModelAndView("account/addNewAccount");
		
		String email = accountForm.getEmail();
		String firstName = accountForm.getFirstName();
		String LastName = accountForm.getLastName();
		String password = accountForm.getPassword();
		String role = accountForm.getRole();
		
		accountRepository.save(new Account(email, password, role, firstName, LastName));
		
		return mv;
	}
	
	@RequestMapping(value = "manageAccounts", method = RequestMethod.GET)
	public ModelAndView manageAccounts(Principal principal){
		
		String name = principal.getName();
		Account account = accountRepository.findByEmail(name);
		String role = account.getRole();
		
		if (role.equals("ROLE_SUPER_ADMIN") || role.equals("ROLE_ADMIN")) {
			ModelAndView mv = new ModelAndView("account/manageAccounts");
			
			List <Account> accounts = new ArrayList<Account>();
			List<Account> retrievedAccounts = accountService.findAllAccounts();
			for (Account retrievedAccount : retrievedAccounts) {
				if (retrievedAccount.getRole().equals("ROLE_USER") || retrievedAccount.getRole().equals("ROLE_ADMIN")) {
					accounts.add(retrievedAccount);
				}
			}
			ManageAccountForm accountForm = new ManageAccountForm();
			accountForm.setAccounts(accounts);
			
			mv.addObject("accountForm", accountForm);
			mv.addObject("accounts", accounts);
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/");
		return mv;
	}
	
	@RequestMapping(value = "editAccount", method = RequestMethod.GET)
	public ModelAndView editAccountRequest(Long id){
		ModelAndView mv = new ModelAndView("account/editAccount");
		
		Account account = accountService.retrieveAccount(id);
		
		UpdateAccountInfoForm accountForm = new UpdateAccountInfoForm();
		accountForm.setAccountId(account.getId());
		accountForm.setEmail(account.getEmail());
		accountForm.setFirstName(account.getFirstName());
		accountForm.setLastName(account.getLastName());
		accountForm.setRole(account.getRole());
		
		mv.addObject("accountForm", accountForm);
		mv.addObject("account", account);
		
		return mv;
	}
	
	@RequestMapping(value = "editAccount", method = RequestMethod.POST)
	public ModelAndView editAccount(@ModelAttribute("accountForm") UpdateAccountInfoForm accountForm, BindingResult result, HttpServletRequest request){
		Long accountId = accountForm.getAccountId();
		Account account = accountService.retrieveAccount(accountId);
		
		String email = accountForm.getEmail();
		String firstName = accountForm.getFirstName();
		String lastName = accountForm.getLastName();
		String role = accountForm.getRole();
		
		account.setEmail(email);
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setRole(role);
		
		accountService.updateAccount(account);
		
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setModelKey("redirect");
		return new ModelAndView (jsonView, "redirect", request.getContextPath() + "account/manageAccounts");
	}
	
	@RequestMapping(value = "changeAccountsPassword", method = RequestMethod.GET)
	public ModelAndView changeAccountsPasswordRequest(Long id){
		ModelAndView mv = new ModelAndView("account/changeAccountsPassword");
		
		Account account = accountService.retrieveAccount(id);
		
		UpdateAccountInfoForm accountForm = new UpdateAccountInfoForm();
		accountForm.setAccountId(account.getId());
		accountForm.setEmail(account.getEmail());
		accountForm.setFirstName(account.getFirstName());
		accountForm.setLastName(account.getLastName());
		accountForm.setRole(account.getRole());
		
		mv.addObject("accountForm", accountForm);
		mv.addObject("account", account);
		
		return mv;
	}
	
	@RequestMapping(value = "changeAccountsPassword", method = RequestMethod.POST)
	public ModelAndView changeAccountsPassword(@ModelAttribute("accountForm") UpdateAccountInfoForm accountForm, BindingResult result, HttpServletRequest request){
		Long accountId = accountForm.getAccountId();
		Account account = accountService.retrieveAccount(accountId);
		
		String password = accountForm.getPassword();
		String newEncryptedPassword = passwordEncoder.encode(password);
		
		account.setPassword(newEncryptedPassword);
		accountService.updateAccount(account);
		
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setModelKey("redirect");
		return new ModelAndView (jsonView, "redirect", request.getContextPath() + "account/manageAccounts");
	}
}
