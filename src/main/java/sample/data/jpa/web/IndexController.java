package sample.data.jpa.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.AppointmentDao;
import sample.data.jpa.service.UserDao;
import sample.data.jpa.service.WorkerDao;

@Controller
public class IndexController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private WorkerDao workerDao;

	@Autowired
	private AppointmentDao appointmentDao;

	private String welcomeMessage;
	private String errorMessage;

	private long connectedUser;

	@GetMapping("/")
	public String index() {
		return "page1.html";
	}

	@GetMapping("/rdv")
	public String rdv(Model model) {
		Appointment appoint = new Appointment();
		model.addAttribute("appoint", appoint); 
		return "rdv";
	}

	// VOIR LE SOUCIS RECUPéRATION D'UN STRING AU LIEU DU USER/WORKER
	@PostMapping("/prendreRdv")
	public String saveRDV(Model model, @ModelAttribute("appoint") Appointment appoint) {
		String date = appoint.getDate();
		int length = appoint.getLenght();
		User us = appoint.getUs();
		Worker wrk = appoint.getWork();
		String desc = appoint.getDescription();
		
		//if (userDao.findById(us.getId()) == null || workerDao.findById(wrk.getId())==null) {
			appointmentDao.save(appoint);
			return "redirect:mypage";
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

	@GetMapping("/inscription")
	public String inscP(Model model) {
		Worker worker = new Worker();
		model.addAttribute("worker", worker);
		return "inscription.html";
	}

	@PostMapping("/inscription")
	public String saveWorker(Model model, @ModelAttribute("worker") Worker worker) {
		String mail = worker.getEmail();
		if (workerDao.findByEmail(mail) == null) {
			workerDao.save(worker);
			return "redirect:connection";
		}
		model.addAttribute("errorMessage", "Wesh pelo, le mail est déjà utilisé");
		return "inscription";
	}

	@GetMapping("/prendreRdv")
	public String prendreRDV(Model model) {
		Appointment ap = new Appointment();
		model.addAttribute("ap", ap);
		return "rdv.html";
	}

//	@PostMapping("/prendreRdv")
//	public String saveRDV(Model model, @ModelAttribute("appoint") Appointment appoint) {
//		appointmentDao.save(appoint);
//		return "redirect:mypage";
//	}

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

//	@RequestMapping("/get-by-user")
//	@ResponseBody
//	public String aptByUser(long id) {
//		try {
//			// User us = userDao
//			Appointment appointment = appointmentDao.findById(id).get();
//			appointmentDao.delete(appointment);
//		} catch (Exception ex) {
//			return "Error deleting the appointment:" + ex.toString();
//		}
//		return "Appointment " + id + " succesfully deleted!";
//	}

	@GetMapping("/mypage")
	public String acc(Model model) {
		// model.addAttribute("reus", aptByUser(connectedUser));
		List<Appointment> appaintmentDao = new ArrayList<>();
		for (Appointment d : appointmentDao.findAll()) {

			if (d.getUs().getId() == connectedUser || d.getWork().getId() == connectedUser) {
				appaintmentDao.add(d);
			}
		}
		model.addAttribute("reus", appaintmentDao);
		return "mypage";
	}

//	 @PostMapping("/delete/{id}", method = RequestMethod.POST))
//	  public String delete(@PathVariable("id") long id) {
//		 System.out.println("azergthyujk,tygr");
//	    try {
//	      Appointment appointment = appointmentDao.findById(id).get();
//	      appointmentDao.delete(appointment);
//	      return "redirect:mypage";
//	    }
//	    catch (Exception ex) {
//	      return "Error deleting the appointment:" + ex.toString();
//	    }
//	  }
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		try {
			Appointment appointment = appointmentDao.findById(id).get();
			appointmentDao.delete(appointment);		
			
		} catch (Exception ex) {
			return "Error deleting the appointment:" + ex.toString();
		}
		return "redirect:/mypage";
	}

}
