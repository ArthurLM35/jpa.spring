package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Appointment;
import sample.data.jpa.domain.User;
import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.AppointmentDao;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

  /**
   * GET /create  --> Create a new appointment and save it in the database.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String date, int length,User us, Worker work, String des) {
    String appointmentId = "";
    try {
      Appointment appointment = new Appointment(date, length, us,work,des);
      appointmentDao.save(appointment);
      appointmentId = String.valueOf(appointment.getId());
    }
    catch (Exception ex) {
      return "Error creating the appointment: " + ex.toString();
    }
    return "appointment succesfully created with id = " + appointmentId;
  }
  
  /**
   * GET /delete  --> Delete the appointment having the passed id.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      Appointment appointment = appointmentDao.findById(id).get();
      appointmentDao.delete(appointment);
    }
    catch (Exception ex) {
      return "Error deleting the appointment:" + ex.toString();
    }
    return "Appointment "+ id +" succesfully deleted!";
  }
  
  @RequestMapping("/get-by-user")
  @ResponseBody
  public String byUser(long id) {
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

@RequestMapping("/get-by-user/{username}")
@ResponseBody
public String byUser(@PathVariable("username") String username) {
	  //Faire un toString de appointment
	String aptId = "";
  try {

  	List<Appointment> appoints = appointmentDao.findAll();
  	for(Appointment apt : appoints) {
  		if(apt.getUs().getName().equals(username))
  			aptId += apt.toString();
  	}
  }
  catch (Exception ex) {
    return "Error deleting the appointment:" + ex.toString();
  }
  if(!aptId.isBlank())
	  return username + " appointment(s) succesfully found!" + "</br>" + aptId;
  return "No appointment found for " + username;
}

@RequestMapping("/get-by-worker/{workername}")
@ResponseBody
public String byWorker(@PathVariable("workername") String workername) {
	  //Faire un toString de appointment
	String aptId = "";
  try {

  	List<Appointment> appoints = appointmentDao.findAll();
  	for(Appointment apt : appoints) {
  		if(apt.getWork().getName().equals(workername))
  			aptId += apt.toString();
  	}
  	
  }
  catch (Exception ex) {
    return "Error deleting the appointment:" + ex.toString();
  }
  if(!aptId.isBlank())
	  return workername + " appointment(s) succesfully found!" + "</br>" + aptId;
  return "No appointment found for " + workername;
}


  
  @RequestMapping("/get-all/")
  @ResponseBody
  public String getAll() {
    String aptId = "";
    try {
      List<Appointment> appoints = appointmentDao.findAll();
      for(Appointment apt : appoints)
      aptId += apt.toString();
      //userId = "curtis.klomegan@gmail.fr"
    }
    catch (Exception ex) {
      return "No appointment found";
    }
    return "The appointments : "+ "</br>" + aptId;
  }
  
//  @RequestMapping("/get-user/")
//  @ResponseBody
//  public String getUser() {
//    String aptId = "";
//    try {
//      List<Appointment> appoints = appointmentDao.findAll();
//      for(Appointment apt : appoints)
//      aptId += String.valueOf(apt.getId())+ " | RDV Le " + apt.getDate() + " de " + apt.getUs().getName() +
//      " avec " + apt.getWork().getName() + "<br>" + "Motif :" + apt.getDescription() + "<br>";
//      //userId = "curtis.klomegan@gmail.fr"
//    }
//    catch (Exception ex) {
//      return "No appointment found";
//    }
//    return "The appointments : "+ "</br>" + aptId;
//  }
  /**
   * GET /get-by-email  --> Return the name of User having a rdv at the specifiate date
   * 
   */
//  @RequestMapping("/get-user-by-date/{date}")
//  @ResponseBody
//  public String getByDate(@PathVariable("date") String date) {
//    List<String> appointmentUser = null ;
//    try {
//      List<Appointment> appointments = appointmentDao.findByDate(date);
//      for (Appointment app : appointments ) {
//      appointmentUser.add(String.valueOf(app.getUs()));
//      }
//    }
//    catch (Exception ex) {
//      return "appointment not found for this date";
//    }
//    return "The appointment id is: " + appointmentUser;
//  }
  
//  @RequestMapping("/get-by-date/{date}")
//  @ResponseBody
//  public String getByDate(@PathVariable("date") String date) {
//    List<String> appointmentUser = null ;
//    try {
//      List<Appointment> appointments = appointmentDao.findByDate(date);
//      appointmentUser.add(appointments.toString());
//    }
//    catch (Exception ex) {
//      return "appointment not found for this date";
//    }
//    return "The appointment id is: " + appointmentUser;
//  }  
  
  /**
   * GET /update  --> Update the email and the name for the appointment in the 
   * database having the passed id.
   */
  //peut etre faire un update pour chaque argument
  @RequestMapping("/update")
  @ResponseBody
  public String updateappointment(long id,String date, int length,User us, Worker work, String des) {
    try {
      Appointment appointment = appointmentDao.findById(id).get();
      appointment.setDate(date);
      appointment.setLenght(length);
      appointment.setUs(us);
      appointment.setWork(work);
      appointment.setDescription(des);
      appointmentDao.save(appointment);
    }
    catch (Exception ex) {
      return "Error updating the appointment: " + ex.toString();
    }
    return "appointment succesfully updated!";
  }

  // Private fields

  @Autowired
  private AppointmentDao appointmentDao;
  
}