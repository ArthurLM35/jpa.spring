package sample.data.jpa.service;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import sample.data.jpa.domain.Worker;

@Transactional
public interface WorkerDao  extends JpaRepository<Worker, Long> {
	

	  public Worker findByEmail(String email); // A revoir
}
