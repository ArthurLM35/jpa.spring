package sample.data.jpa.domain;
// Imports ...

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "Worker")
public class Worker extends  User{


  @NonNull
  private String job;
  
  public Worker() { }

  
  public Worker(String name, String password, String email, String job) {
	  super(email,name,password);
    this.job = job;  
  }


public Worker(long id) {
	// TODO Auto-generated constructor stub
}


public String getJob() {
	return job;
}

public void setName(String job) {
	this.job = job;
}

  // Getter and setter methods
  // ...

}