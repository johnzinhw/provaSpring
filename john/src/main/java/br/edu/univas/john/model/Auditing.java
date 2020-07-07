package br.edu.univas.john.model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @OneToOne
    @JoinColumn (name ="fk_payment")
    private long product_code;
    
    private Date dateTime;
    
    private String operation;
    
    public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Auditing (long id, long product_code, String operation, Date dateTime) {
    	this.id=id;
    	this.product_code = product_code;
    	this.dateTime = dateTime;
    	this.operation=operation;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProduct_code() {
		return product_code;
	}

	public void setProduct_code(long product_code) {
		this.product_code = product_code;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
    
    
    
    
    
    
    

    
}