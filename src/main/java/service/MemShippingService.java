package service;

import vo.MemShipping;

public interface MemShippingService {
	MemShipping findById(Long id);
	
	void removeById(Long id);
}
