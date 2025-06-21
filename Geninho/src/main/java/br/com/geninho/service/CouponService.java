package br.com.geninho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.geninho.Security.Jwt;
import br.com.geninho.dto.CouponDTO;
import br.com.geninho.dto.CouponInsertDTO;
import br.com.geninho.entities.Coupon;
import br.com.geninho.exception.CouponException;
import br.com.geninho.exception.NotFoundException;
import br.com.geninho.repository.CouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Autowired
	private Jwt jwt;

	public List<CouponDTO> listAll() {
		return couponRepository.findAll().stream().map(CouponDTO::new).toList();
	}

	public CouponDTO search(Long id) {
		Optional<Coupon> couponOpt = couponRepository.findById(id);

		if (couponOpt.isPresent()) {
			CouponDTO couponDTO = new CouponDTO(couponOpt.get());
			return couponDTO;
		} else {
			throw new NotFoundException("Cupom não encontrado");
		}
	}

	public CouponDTO insert(CouponInsertDTO couponInsertDTO) {

		Coupon coupon = new Coupon();

		if (couponRepository.findByCode(couponInsertDTO.getCode().toUpperCase()) != null) {
			throw new CouponException("Codigo de coupon já existente");
		}

		coupon.setCode(couponInsertDTO.getCode().toUpperCase());
		coupon.setDiscount(couponInsertDTO.getDiscount());
		coupon.setValid(couponInsertDTO.getValid());
		coupon.setStatus(true);

		couponRepository.save(coupon);

		CouponDTO couponDTO = new CouponDTO(coupon);
		return couponDTO;
	}

	public CouponDTO update(Long id, CouponInsertDTO couponInsertDTO) {

		Optional<Coupon> couponOpt = couponRepository.findById(id);
		if (couponOpt.isEmpty()) {
			throw new NotFoundException("Cupom não encontrado");
		}
		Coupon coupon = couponOpt.get();

		coupon.setId(id);
		coupon.setCode(coupon.getCode());
		coupon.setDiscount(coupon.getDiscount());
		coupon.setValid(coupon.getValid());
		coupon.setStatus(couponUpdateDTO.getStatus() == null ? coupon.getStatus() : couponUpdateDTO.getStatus());

		couponRepository.save(coupon);

		CouponDTO couponDTO = new CouponDTO(coupon);
		return couponDTO;

	}

	public void delete(Long id) {
		Optional<Coupon> couponOpt = couponRepository.findById(id);
		if (couponOpt.isEmpty()) {
			throw new NotFoundException("Cupom não encontrado");
		}

		couponRepository.deleteById(id);

	}

}