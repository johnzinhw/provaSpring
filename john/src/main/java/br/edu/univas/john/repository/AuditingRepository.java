package br.edu.univas.john.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.univas.john.model.Auditing;
import br.edu.univas.john.model.Payment;

@Repository
public interface AuditingRepository extends JpaRepository<Auditing, Long>{

}
