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
	
	@GetMapping("/")
	public String index() {
		return "page1.html";
	}
	
	@GetMapping("/rdv")
	public String rdv() {
		return "rdv.html";
	}

	@GetMapping("/inscription2")
	public String insc(Model model) {
		User user = new User();
		model.addAttribute("user", user); 
		return "inscription2.html";
	}
	
	@PostMapping("/inscription2")
	public String savePerson(Model model, @ModelAttribute("user") User user) {	
			userDao.save(user);
			return "redirect:mypage";
	}
	
	@GetMapping("/prendreRdv")
	public String prendreRDV(Model model) {
		Appointment ap = new Appointment();
		model.addAttribute("ap", ap); 
		return "rdv.html";
	}
	
	@PostMapping("/prendreRdv")
	public String saveRDV(Model model, @ModelAttribute("appoint") Appointment appoint) {
			AppaintmentDao.save(appoint);
			return "redirect:mypage";
	}

	@GetMapping("/connection")
	public String connect() { 
		return "connection"; //peut-être connection tout court
	}

	@GetMapping("/mypage")
	public String acc1(Model model) {
		model.addAttribute("reus", AppaintmentDao.findAll());
		//model.addAttribute("users", users);
		return "mypage"; //peut-être connection tout court
	}
	
	@Autowired
	private  UserDao userDao;
	
	@Autowired
	private  AppointmentDao AppaintmentDao;
}
