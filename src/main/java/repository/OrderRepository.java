package repository;

import org.springframework.data.repository.CrudRepository;

import vo.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
