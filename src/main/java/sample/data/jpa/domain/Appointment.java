package sample.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "Appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String date;
	
	private int lenght;
	
	@NotNull
	@ManyToOne
	private User us;
	
	@NotNull
	@ManyToOne
	private Worker work;
	
	private String description;
	//ajouter un créneau plutot qu'une durée ?
	
	public Appointment() {
    }
	
	public Appointment(String date,int lenght, User us, Worker work, String desc) {
		this.date = date;
		this.lenght = lenght;
		this.us = us;
		this.work = work;
		this.description = desc;		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getLenght() {
		return lenght;
	}
	
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	

	//@ManyToOne
	public User getUs() {
		return us;
	}
	
	public void setUs(User us) {
		this.us = us;
	}
	
	//@ManyToOne
	public Worker getWork() {
		return work;
	}
	
	public void setWork(Worker work) {
		this.work = work;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "";
	}

}
