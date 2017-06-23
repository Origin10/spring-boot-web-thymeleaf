package service;

import vo.UserPayment;

public interface MemPaymentService {
	UserPayment findById(Long id);
	
	void removeById(Long id);
}
