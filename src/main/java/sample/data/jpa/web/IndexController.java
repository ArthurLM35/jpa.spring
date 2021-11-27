package sample.data.jpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		return "page1.html";
	}
	
	@GetMapping("/inscription")
	public String insc() {
		return "inscription.html";
	}
	
	@GetMapping("/connection")
	public String con() {
		return "connection.html";
	}
}
