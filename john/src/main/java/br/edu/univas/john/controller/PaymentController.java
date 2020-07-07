package br.edu.univas.john.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.john.model.Payment;
import br.edu.univas.john.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	PaymentService PaymentService;

	@GetMapping("/(id)")
	public ResponseEntity<Object> getPaymentById(@PathVariable(value="id") long id){
		return PaymentService.getPaymentById(id);
	}

	@PostMapping()
	public ResponseEntity<Object> createPayment(@RequestBody Payment newPayment) {
		return PaymentService.createPayment(newPayment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePayment(@PathVariable(value = "id") long id, @RequestBody @Validated Payment payment) {
		return PaymentService.updatePayment(payment, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePaymentById(@PathVariable(value = "id") long id) {
		return PaymentService.deletePayment(id);
	}
}
