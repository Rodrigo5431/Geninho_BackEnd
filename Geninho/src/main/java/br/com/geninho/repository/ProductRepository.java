package br.com.geninho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.geninho.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
	
}
