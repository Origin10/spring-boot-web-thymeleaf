package service.impl;

import org.springframework.stereotype.Service;

import vo.ShippingAddress;
import vo.MemShipping;
import service.ShippingAddressService;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	public ShippingAddress setByUserShipping(MemShipping memShipping, ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressName(memShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet1(memShipping.getUserShippingStreet1());
		shippingAddress.setShippingAddressStreet2(memShipping.getUserShippingStreet2());
		shippingAddress.setShippingAddressCity(memShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(memShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(memShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(memShipping.getUserShippingZipcode());
		
		return shippingAddress;
	}
}
