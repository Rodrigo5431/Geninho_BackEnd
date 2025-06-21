package br.com.geninho.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.geninho.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

	Optional<Coupon> findByCode(String code);
}
