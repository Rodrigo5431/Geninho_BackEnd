package br.com.geninho.dto;

import java.util.List;

import br.com.geninho.entities.Product;
import br.com.geninho.entities.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

public class ProductDTO {

	private String name;

	private Double price;

	private String description;

	private Integer stock;

	private Boolean status;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;

	public ProductDTO(String name, Double price, String description, Integer stock, Boolean status,
			List<Review> reviews) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.status = status;
		this.reviews = reviews;
	}
	public ProductDTO(Product product) {
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
		this.stock = product.getStock();
		this.status = product.getStatus();
		this.reviews = product.getReviews();
	}

	public ProductDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
