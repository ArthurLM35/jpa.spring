package sample.data.jpa.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Worker;
import sample.data.jpa.service.WorkerDao;

@Controller
@RequestMapping("/worker")
public class WorkerController {

  /**
   * GET /create  --> Create a new worker and save it in the database.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name,String password, String job) {
    String workerId = "";
    try {
      Worker worker = new Worker(email, name, password, job);
      workerDao.save(worker);
      workerId = String.valueOf(worker.getId());
    }
    catch (Exception ex) {
      return "Error creating the worker: " + ex.toString();
    }
    return "Worker succesfully created with id = " + workerId;
  }
  
  /**
   * GET /delete  --> Delete the worker having the passed id.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      Worker worker = workerDao.findById(id).get();
      workerDao.delete(worker);
    }
    catch (Exception ex) {
      return "Error deleting the worker:" + ex.toString();
    }
    return "Worker succesfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the worker having the passed
   * email.
   */
  @RequestMapping("/get-by-email/{email}")
  @ResponseBody
  public String getByEmail(@PathVariable("email") String email) {
    String workerId = "";
    try {
      Worker worker = workerDao.findByEmail(email);
      workerId = String.valueOf(worker.getId());
    }
    catch (Exception ex) {
      return "Worker not found";
    }
    return "The worker id is: " + workerId;
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-by-id/{id}")
  @ResponseBody
  public String getByID(@PathVariable("id") Long id) {
    String workerToString = "";
    try {
      Optional<Worker> worker = workerDao.findById(id);
      workerToString = worker.get().toString();
    }
    catch (Exception ex) {
      return "Worker not found";
    }
    return "The worker id is: " + workerToString;
  }
  
  @RequestMapping("/get-all/")
  @ResponseBody
  public String getAll() {
    String workerId = "";
    try {
      List<Worker> workers = workerDao.findAll();
      for(Worker wrk : workers)
      workerId += String.valueOf(wrk.getId())+ " | " + wrk.getName() +"<br>";
      //userId = "curtis.klomegan@gmail.fr"
    }
    catch (Exception ex) {
      return "Worker not found";
    }
    return "The worker id is: " + workerId;
  }
  
  /**
   * GET /update  --> Update the email and the name for the worker in the 
   * database having the passed id.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateWorker(long id, String email, String name) {
    try {
      Worker worker = workerDao.findById(id).get();
      worker.setEmail(email);
      worker.setName(name);
      workerDao.save(worker);
    }
    catch (Exception ex) {
      return "Error updating the worker: " + ex.toString();
    }
    return "Worker succesfully updated!";
  }

  // Private fields

  @Autowired
  private WorkerDao workerDao;
  
}