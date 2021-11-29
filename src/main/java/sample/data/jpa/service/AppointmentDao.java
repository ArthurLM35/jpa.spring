package sample.data.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import sample.data.jpa.domain.*;

@Transactional
public interface AppointmentDao  extends JpaRepository<Appointment, Long> {
	
	//public List<Appointment> findUserRDV(User id);
	//public List<Appointment> findWorkerRDV(Worker id);
	public List<Appointment> findByDate(String date);
	

}
