package service;

import vo.Payment;
import vo.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
