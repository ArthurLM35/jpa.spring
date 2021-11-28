package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

@Controller
public class IndexController {
	
	
	//private static List<User> users = new ArrayList<User>();
	
	@GetMapping("/")
	public String index() {
		return "page1.html";
	}
	

	
//	@GetMapping("/inscription2")
//	public String insc(Model model) {
//		System.out.println("SALUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUT");
//		UserForm userForm = new UserForm();
//		model.addAttribute("userForm", userForm); 
//		return "inscription2.html";
//	}

	@GetMapping("/inscription2")
	public String insc(Model model) {
		System.out.println("SALUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUT");
		User user = new User();
		model.addAttribute("user", user); 
		return "inscription2.html";
	}
//	@PostMapping("/inscription2")
//	public String getInsc(@ModelAttribute User newUser) {
//		User user = new User(newUser.getEmail(),newUser.getName(),newUser.getPassword());
//		
//		return "redirect:inscription2.html";
//	}
	
//	@PostMapping("/inscription2")
//	public String savePerson(Model model, @ModelAttribute User userF) {
//
//		String mail = userF.getEmail();
//		String name = userF.getName();
//		String mdp = userF.getPassword();
//
////		if (firstName != null && firstName.length() > 0 //
////				&& lastName != null && lastName.length() > 0) {
//			User newUser = new User(mail, name,mdp);
//			userDao.save(newUser);
//
//			return "redirect:mypage.html";
//		}
	
//	@PostMapping("/inscription2")
//	public String savePerson(Model model, @ModelAttribute("userForm") UserForm userForm) {
//		System.out.println("JE SUIS LA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		String mail = userForm.getEmail();
//		System.out.println(mail);
//		String name = userForm.getName();
//		System.out.println(name);
//		String mdp = userForm.getPassword();
//		System.out.println(mdp);
//		
//		System.out.println(userForm.toString());
//			User newUser = new User(mail,name,mdp);
//			//newUser.setId(29);
//			System.out.println(newUser.toString());
//			//users.add(newUser);
//			userDao.save(newUser);
//			System.out.println(newUser.toString());
//			//System.out.println(users.toString());
//			System.out.println("FINITO PIPO");
//			return "redirect:mypage";
//	}
	
	@PostMapping("/inscription2")
	public String savePerson(Model model, @ModelAttribute("user") User user) {
		System.out.println("JE SUIS LA !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String mail = user.getEmail();
		System.out.println(mail);
		String name = user.getName();
		System.out.println(name);
		String mdp = user.getPassword();
		System.out.println(mdp);
		
		System.out.println(user.toString());
			//User newUser = new User(mail,name,mdp);
			//newUser.setId(29);
			//System.out.println(newUser.toString());
			//users.add(newUser);
			userDao.save(user);
			//System.out.println(newUser.toString());
			//System.out.println(users.toString());
			System.out.println("FINITO PIPO");
			return "redirect:mypage";
	}
//	@ExceptionHandler({})
//    public void handleException() {
//		
//	}
	
//	  @RequestMapping("/create/{email},{name},{mdp}")
//	  @ResponseBody
//	  public String createParams(@PathVariable String email, @PathVariable String name, @PathVariable String mdp)  {
//	    String userId = "";
//	   // User us;
//	    try {
//	     if(userDao.findByEmail(email) == null) {
//	      User user = new User(email, name, mdp);
//	      System.out.println(user.getId());
//	      userDao.save(user);
//	      userId = String.valueOf(user.getId());
//	     // us = user;
//	     }
//	    }
//	    catch (Exception ex) {
//	      return "Error creating the user: " + ex.toString();
//	    }
//	    return "User succesfully created with id = " + userId + "</br>"; //+ us.toString();
//	  }
	
	
//	@RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
//	public String savePerson(Model model, //
//			@ModelAttribute("personForm") PersonForm personForm) {
//
//		String firstName = personForm.getFirstName();
//		String lastName = personForm.getLastName();
//
//		if (firstName != null && firstName.length() > 0 //  // --------------------------------- A INTEGRER
//				&& lastName != null && lastName.length() > 0) {
//			Person newPerson = new Person(firstName, lastName);
//			persons.add(newPerson);
//
//			return "redirect:/personList";
//		}
	
	@GetMapping("/connection")
	public String connect() { 
		return "connection"; //peut-être connection tout court
	}
	
	//A revoir
	@GetMapping("/mypage")
	//public String acc() {
		public String acc(Model model) {
		model.addAttribute("users", userDao.findAll());
		//model.addAttribute("users", users);
		return "mypage"; //peut-être connection tout court
	}
	
	@Autowired
	private  UserDao userDao;
}
