package repository;

import org.springframework.data.repository.CrudRepository;

import vo.UserPayment;

public interface MemPaymentRepository extends CrudRepository<UserPayment, Long>{

}
