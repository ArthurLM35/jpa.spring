package sample.data.jpa.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import sample.data.jpa.domain.User;

@Transactional
public interface UserDao extends JpaRepository<User, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
  public User findByEmail(String email); // A revoir
  
  public Optional<User> findById(Long id);
  
 public User findByName(String name);
  
  //public List<Appointment> findRDV(Long id);

}