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
	private String informationMessage;

	private long connectedUser;
	private long appointmentWorker;

	@GetMapping("/")
	public String index() {
		return "page1.html";
	}

	@GetMapping("/rdv")
	public String rdv(Model model) {
		if(connectedUser != 0) {
			System.out.println("efezfzfzf");
		    List<Worker> workers = new ArrayList<>();
			for(Worker work : workerDao.findAll()) {
				if(work.getId() != connectedUser) {
					workers.add(work);
				}
			}
		    model.addAttribute("workers", workers);
			Appointment appoint = new Appointment();
			model.addAttribute("appoint", appoint);
			Worker idWorker = new Worker();
			model.addAttribute("idWorker", idWorker);		
			return "rdv";
		}
		else {
			System.out.println("151156165561561");
			return "redirect:/";
		}
		
	}

	@PostMapping("/chooseWorker")
	public String  saveWork(Model model, @ModelAttribute Worker idWorker) {
		Long a  = idWorker.getId();
		System.out.println("J'ai le " + a);
		appointmentWorker = a;
		model.addAttribute("informationMessage", " M??decin " + workerDao.getById(a).getName() + " s??lectionn?? !");
		return "redirect:rdv";
	}

	 //VOIR LE SOUCIS RECUP??RATION D'UN STRING AU LIEU DU USER/WORKER
	@PostMapping("/prendreRdv")
	public String saveRDV(Model model, @ModelAttribute("appoint") Appointment appoint) {
		String date = appoint.getDate();
		String creneau= creneauHeure(appoint.getCreneau());
		boolean present = false;
		for(Appointment app : appointmentDao.findAll()) {
			if(app.getDate().equals(date) && app.getCreneau().equals(creneau)) {
				present = true;
			}
		}	
		if(present) {
			return "redirect:rdv";
		}
		else {		
			appoint.setUs(userDao.getById(connectedUser));
			appoint.setWork(workerDao.getById(appointmentWorker));		
			//for(Appointment apt : appointmentDao.findAll()) { //VERIFIER ET HORAIRE
			//if(apt.getDate()!= date && apt.getCreneau) // A am??liorer avec +30 / genre 16/16H30 et 16H30/17
			appoint.setCreneau(creneauHeure(appoint.getCreneau()));
			appointmentDao.save(appoint);
			//appointmentWorker =0;
			return "redirect:mypage";
			//}
		}			
	}
	
	public int heureCreneau(String h) {
		String heures = h.substring(0, 2);
		return Integer.parseInt(heures);
	}
	
	public int minuteCreneau(String h) {
		String minutes = h.substring(3, 5);
		return Integer.parseInt(minutes);
	}
	public String creneauHeure(String h) {
		int heur = heureCreneau(h);
		int minut = minuteCreneau(h);
		if(0 <= minut && minut < 15) {
			minut=0;
		}
		else if(15 <= minut && minut < 45) {
			minut=30;
		}
		else {
			minut=0;
			heur++;
		}
		if(minut == 0) {
			return heur +":"+ minut+"0";
		}
		else {
			return heur +":"+ minut;
		}
		
	}
	
	@GetMapping("/deco")
	public String deco() {
		connectedUser = 0;
		return "page1.html";
	}

	@GetMapping("/inscription2")
	public String inscU(Model model) {
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
		model.addAttribute("errorMessage", "Wesh pelo, le mail est d??j?? utilis??");
		return "inscription2";
	}

	@GetMapping("/inscription")
	public String inscW (Model model) {
		Worker worker = new Worker();
		model.addAttribute("worker", worker);
		return "inscription.html";
	}

	@PostMapping("/inscription")
	public String saveWorker(Model model, @ModelAttribute("worker") Worker worker) {
		String mail = worker.getEmail();
		System.out.println("MAIL :" + mail);
		System.out.println("NAME :" +worker.getName());
		System.out.println("PSW :" +worker.getPassword());
		System.out.println("JOB :" + worker.getJob());
		if (workerDao.findByEmail(mail) == null) {
			workerDao.save(worker);
			return "redirect:connection";
		}
		model.addAttribute("errorMessage", "Wesh pelo, le mail est d??j?? utilis??");
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
//		if(){
//			appointmentDao.save(appoint);
//			return "redirect:mypage";
//		}
//		else{
//			return "Vous ??tes connect?? avec votre compte professionnel, vous ne pouvez pas prendre de rdv!";
//		}
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



	@GetMapping("/mypage")
	public String acc(Model model) {
		// model.addAttribute("reus", aptByUser(connectedUser));
		model.addAttribute("welcomeMessage", "Bienvenue " + userDao.getById(connectedUser).getName());
		List<Appointment> appaintmentDao = new ArrayList<>();
		for (Appointment d : appointmentDao.findAll()) {
			System.out.println(connectedUser);
			if (d.getUs().getId() == connectedUser || d.getWork().getId() == connectedUser) {
				appaintmentDao.add(d);
			}
		}
		model.addAttribute("reus", appaintmentDao);
		return "mypage";
	}

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
