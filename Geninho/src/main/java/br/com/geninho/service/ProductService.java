package br.com.geninho.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.geninho.dto.ProductDTO;
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
	
}
