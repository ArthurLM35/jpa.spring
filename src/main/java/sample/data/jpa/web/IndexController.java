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

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.service.AppointmentDao;
import sample.data.jpa.service.UserDao;

@Controller
public class IndexController {	
	
	@Autowired
	private  UserDao userDao;
	
	@Autowired
	private  AppointmentDao appointmentDao;
	
	private String welcomeMessage;
	private String errorMessage;

	private long connectedUser;
	
	@GetMapping("/")
	public String index() {
		return "page1.html";
	}
	
	@GetMapping("/rdv")
	public String rdv() {
		return "rdv.html";
	}
	
	@GetMapping("/deco")
	public String deco() {
		return "page1.html";
	}

	@GetMapping("/inscription2")
	public String insc(Model model) {
		User user = new User();
		model.addAttribute("user", user); 
		return "inscription2.html";
	}
	
	@PostMapping("/inscription2")
	public String saveUser(Model model, @ModelAttribute("user") User user) {
		String mail = user.getEmail();

		if (userDao.findByEmail(mail) == null) {
			userDao.save(user);
			return "redirect:connection";
		}
		model.addAttribute("errorMessage", "Wesh pelo, le mail est déjà utilisé");
		return "inscription2";
	}
	
	@GetMapping("/prendreRdv")
	public String prendreRDV(Model model) {
		Appointment ap = new Appointment();
		model.addAttribute("ap", ap); 
		return "rdv.html";
	}
	
	@PostMapping("/prendreRdv")
	public String saveRDV(Model model, @ModelAttribute("appoint") Appointment appoint) {
			appointmentDao.save(appoint);
			return "redirect:mypage";
	}

	@GetMapping("/connection")
	public String connect(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "connection";
	}

	@PostMapping("/connection")
    public String connectUser(Model model, @ModelAttribute("user") User user) {
        String mail = user.getEmail();
        String mdp = user.getPassword();
        try {
            User userTemp = userDao.findByEmail(mail);
            if (!userTemp.getPassword().equals(mdp)) {
                model.addAttribute("errorMessage", "Wesh pelo, Mauvais mdp fdp !");
                return "connection";
            }
            connectedUser = userTemp.getId();
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Wesh pelo, le mail est pas dans la base, tu veux t'inscrire ?");
            return "connection";
        }
        return "redirect:mypage";
    } 
	
//	@GetMapping("/mypage")
//	public String acc(Model model) {
//		model.addAttribute("users", userDao.findAll());
//		return "mypage";
//	}
	
	  @RequestMapping("/get-by-user")
	  @ResponseBody
	  public String aptByUser(long id) {
	    try {
	    	//User us = userDao
	      Appointment appointment = appointmentDao.findById(id).get();
	      appointmentDao.delete(appointment);
	    }
	    catch (Exception ex) {
	      return "Error deleting the appointment:" + ex.toString();
	    }
	    return "Appointment "+ id +" succesfully deleted!";
	  }
	  
	@GetMapping("/mypage")
    public String acc(Model model) {
       // model.addAttribute("reus", aptByUser(connectedUser));		
		List<Appointment> appaintmentDao = new ArrayList<>();
		for(Appointment d : appointmentDao.findAll()) {
			System.out.println(connectedUser);
			if(d.getUs().getId() == connectedUser) {
				System.out.println("zertyukhgfdesfg");
				appaintmentDao.add(d);
			}
		}
		System.out.println(appaintmentDao.size());
        model.addAttribute("reus", appaintmentDao);
        return "mypage"; 
    }
	
	

}
