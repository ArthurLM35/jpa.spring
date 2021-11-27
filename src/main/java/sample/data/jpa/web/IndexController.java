package sample.data.jpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sample.data.jpa.domain.User;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		return "page1.html";
	}
	
	@GetMapping("/inscription2")
	public String insc() {
		return "inscription2.html";
	}
	
	@PostMapping("/inscription2")
	public String getInsc(@ModelAttribute User newUser) {
		User user = new User(newUser.getEmail(),newUser.getName(),newUser.getPassword());
		
		return "redirect:inscription2.html";
	}
	
	@GetMapping("/connection")
	public String connect() { 
		return "connection.html"; //peut-être connection tout court
	}
	
	@GetMapping("/mypage")
	public String acc() { 
		return "mypage.html"; //peut-être connection tout court
	}
}
