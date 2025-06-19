package br.com.geninho.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.geninho.dto.ProductDTO;
import br.com.geninho.dto.ProductInsertDTO;
import br.com.geninho.entities.Product;
import br.com.geninho.exception.NotFoundException;
import br.com.geninho.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
	}
	
	public ProductDTO getProductById(Long id) {
		Optional<Product>productOpt = productRepository.findById(id);
		if (productOpt.isEmpty()) {
			throw new NotFoundException("Produto não encontrado");
			
		}
		return new ProductDTO(productOpt.get());
	}
	
	public ProductDTO createProduct(ProductInsertDTO productInsert) {
		
		Product product = new Product();
		product.setName(productInsert.getName());
		product.setPrice(productInsert.getPrice());
		product.setDescription(productInsert.getDescription());
		product.setStock(productInsert.getStock());
		product.setStatus(true); 
		productRepository.save(product);
		
		return new ProductDTO(product);
	}
	
	public ProductDTO updateProduct (Long id, ProductInsertDTO productInsert) {
		
		Optional<Product>productOpt = productRepository.findById(id);
		if (productOpt.isEmpty()) {
			throw new NotFoundException("Produto não encontrado");
		}
		
		Product product = new Product();
		product.setId(id);
		product.setName(productInsert.getName() != null? productInsert.getName() : productOpt.get().getName());
		product.setPrice(productInsert.getPrice() != null? productInsert.getPrice() : productOpt.get().getPrice());
		product.setDescription(productInsert.getDescription() != null? productInsert.getDescription() : productOpt.get().getDescription());
		product.setStock(productInsert.getStock() != null? productInsert.getStock() : productOpt.get().getStock());
		product.setStatus(productOpt.get().getStatus());
		productRepository.save(product);
		
		return new ProductDTO(product);
	}
	
	public String deleteProduct(Long id) {
		
		Optional<Product>productOpt = productRepository.findById(id);
		if (productOpt.isEmpty()) {
			throw new NotFoundException("Produto não encontrado");
		}
		productRepository.deleteById(id);
		return "Produto deletado com sucesso";
	}
	public ProductDTO disableProduct(Long id) {
		
		Optional<Product>productOpt = productRepository.findById(id);
		if (productOpt.isEmpty()) {
			throw new NotFoundException("Produto não encontrado");
		}
		
		Product product = productOpt.get();
		product.setStatus(false);
		productRepository.save(product);
		
		return new ProductDTO(product);
	}
}
