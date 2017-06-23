package service;

import vo.MemShipping;
import vo.ShippingAddress;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(MemShipping memShipping, ShippingAddress shippingAddress);
}
