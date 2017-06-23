package service;

import vo.BillingAddress;
import vo.Mem_VO;
import vo.Order;
import vo.Payment;
import vo.ShippingAddress;
import vo.ShoppingCart;


public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			Mem_VO mem);
	
	Order findOne(Long id);
}
