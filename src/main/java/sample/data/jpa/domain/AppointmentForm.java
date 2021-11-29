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



// PAS BESOIN DU FORM SI LISTE DéROULANTE
public class AppointmentForm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String date;
	
	//Faudra peut faire un form pour pouvoir faire un new Appointement avec une durée prédéfini
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int lenght;
	
	@NotNull
	@ManyToOne
	private String us;
	
	@NotNull
	@ManyToOne
	private String work;
	
	private String description;
	//ajouter un créneau plutot qu'une durée ?
	

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
	public String getUs() {
		return us;
	}
	
	public void setUs(String us) {
		this.us = us;
	}
	
	//@ManyToOne
	public String getWork() {
		return work;
	}
	
	public void setWork(String work) {
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
		return String.valueOf(getId())+ " | RDV Le " + getDate() + " de " + getUs() +
			      " avec " + getWork() + "<br>" + "Motif :" + getDescription() + "<br>";
	}

}
