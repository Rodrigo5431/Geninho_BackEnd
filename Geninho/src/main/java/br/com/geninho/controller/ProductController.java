package br.com.geninho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geninho.dto.ProductDTO;
import br.com.geninho.dto.ProductInsertDTO;
import br.com.geninho.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));

	}

	@PreAuthorize("hasRole('ADM')")
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductInsertDTO productInsert) {
		return ResponseEntity.ok(productService.createProduct(productInsert));
	}

	@PreAuthorize("hasRole('ADM')")
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
			@Valid @RequestBody ProductInsertDTO productInsert) {
		return ResponseEntity.ok(productService.updateProduct(id, productInsert));

	}

	@PreAuthorize("hasRole('ADM')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.deleteProduct(id));
	}

	@PreAuthorize("hasRole('ADM')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDTO> disableProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.disableProduct(id));
	}
}
