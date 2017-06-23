package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vo.UserPayment;
import service.MemPaymentService;

import repository.MemPaymentRepository;

@Service
public class MemPaymentServiceImpl implements MemPaymentService {

	@Autowired
	private MemPaymentRepository memPaymentRepository;
		
	public UserPayment findById(Long id) {
		return memPaymentRepository.findOne(id);
	}
	
	public void removeById(Long id) {
		memPaymentRepository.delete(id);
	}
} 
