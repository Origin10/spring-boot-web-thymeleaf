package service;

import vo.BillingAddress;
import vo.MemBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(MemBilling memBilling, BillingAddress billingAddress);
}
