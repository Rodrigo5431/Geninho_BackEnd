package br.com.geninho.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CouponInsertDTO {

	private String code;

	@Positive(message = "O preço deve ser um valor positivo.")
	private Double discount;

	@NotNull(message = "A data de validade é obrigatória")
	private Date valid;

	private Boolean status;

	public CouponInsertDTO(String code, Double discount, Date valid, Boolean status) {
		super();
		this.code = code;
		this.discount = discount;
		this.valid = valid;
		this.status = status;
	}

	public CouponInsertDTO() {
		super();
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