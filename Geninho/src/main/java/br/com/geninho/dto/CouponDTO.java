package br.com.geninho.dto;

import java.util.Date;

import br.com.geninho.entities.Coupon;

public class CouponDTO {
	private Long id;
	private String code;
	private Double discount;
	private Date valid;
	private Boolean status;

	public CouponDTO(Long id, String code, Double discount, Date valid, Boolean status) {
		super();
		this.id = id;
		this.code = code;
		this.discount = discount;
		this.valid = valid;
		this.status = status;
	}

	public CouponDTO() {
		super();
	}

	public CouponDTO(Coupon coupon) {
		this.id = coupon.getId();
		this.code = coupon.getCode();
		this.discount = coupon.getDiscount();
		this.valid = coupon.getValid();
		this.status = coupon.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Date getValid() {
		return valid;
	}

	public void setValid(Date valid) {
		this.valid = valid;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}