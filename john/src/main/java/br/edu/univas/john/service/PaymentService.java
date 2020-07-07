package br.edu.univas.john.service;
import java.util.Optional;
import java.sql.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.DeliveryMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import br.edu.univas.john.model.Auditing;
import br.edu.univas.john.model.Payment;
import br.edu.univas.john.repository.AuditingRepository;
import br.edu.univas.john.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;

	AuditingRepository auditingRepository;
	
	Random random = new Random();

	public ResponseEntity<Object> getPaymentById(long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()) {
			return ResponseEntity.ok(payment);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<Object> createPayment(Payment newPayment) {
		try {
			Payment paymentCreated = paymentRepository.save(newPayment);
			Auditing log = new Auditing(random.nextInt(1), paymentCreated.getOrderCode(), "CREATE", newDate());
			auditingRepository.save(log);
			return ResponseEntity.ok(paymentCreated);
		}catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
		
	}

	private Date newDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Object> updatePayment(Payment payment, long id) {
		Optional<Payment> oldPayment = paymentRepository.findById(id);
		if (oldPayment.isPresent()) {
			Payment PaymentUpdated = paymentRepository.save(buildPaymentToUpdate(oldPayment.get(), payment));
			Auditing log = new Auditing(random.nextInt(1), PaymentUpdated.getOrderCode(), "UPDATE", newDate());
			auditingRepository.save(log);
			
			return ResponseEntity.ok(PaymentUpdated);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> deletePayment(long id) {
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()){
			paymentRepository.delete(payment.get());
			Auditing log = new Auditing(random.nextInt(1), id, "DELETE", newDate());
			auditingRepository.save(log);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	private Payment buildPaymentToUpdate (Payment newPayment, Payment oldPayment) {
		
		
		if(newPayment.getDue_date() != null) {
			oldPayment.setValue(newPayment.getValue());
		}
		
		if(newPayment.getPayment_date() != null) {
			oldPayment.setPayment_date(newPayment.getPayment_date());
		}
		
		if(newPayment.getPayment_type() != null) {
			oldPayment.setPayment_type(newPayment.getPayment_type());
		}
		
		if(newPayment.getValue()() != null) {
			oldPayment.setValue(newPayment.getValue());
		}
		
		
		
		return oldPayment;
	}

}
