package br.com.geninho.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Order {

	
	@ManyToOne
	@JoinColumn(name = "id_coupon")
	private Coupon coupon;

}
