package sample.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Worker")
public class Worker extends User{


	@NotNull
	private String job;
  
	public Worker() { 
		super();
	}
  
	public Worker(String name, String password, String email, String job) {		
		super(name,email,password);
		this.job = job;  
	}

	public String getJob() {
		return job;
	}

	public void setName(String job) {
		this.job = job;
	}
}