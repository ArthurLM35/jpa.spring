package sample.data.jpa.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.User;
import sample.data.jpa.service.UserDao;

@Controller
@RequestMapping("/user")
public class UserController {

  /**
   * GET /create  --> Create a new user and save it in the database.
   */
  @RequestMapping("/create")
  @ResponseBody
  public String create(String email, String name,String password) {
    String userId = "";
    try {
      User user = new User(email, name, password);
      userDao.save(user);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created with id = " + userId;
  }
  

  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = userDao.findById(id).get();
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-by-email/{email}")
  @ResponseBody
  public String getByEmail(@PathVariable("email") String email) {
    String userId = "";
    try {
      User user = userDao.findByEmail(email);
      userId = String.valueOf(user.getId())+ " | " + user.getName();
      //userId = "curtis.klomegan@gmail.fr"
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
 
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-all/")
  @ResponseBody
  public String getAll() {
    String userId = "";
    try {
      List<User> users = userDao.findAll();
      for(User us : users)
      userId += String.valueOf(us.getId())+ " | " + us.getName() +"<br>";
      //userId = "curtis.klomegan@gmail.fr"
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
  
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/get-by-id/{id}")
  @ResponseBody
  public String getByID(@PathVariable("id") Long id) {
    String userToString = "";
    try {
      Optional<User> user = userDao.findById(id);
      userToString = user.get().toString();
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userToString;
  }
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userDao.findById(id).get();
      user.setEmail(email);
      user.setName(name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }

  // Private fields

  @Autowired
  private UserDao userDao;
  
}