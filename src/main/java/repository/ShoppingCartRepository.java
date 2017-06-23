package repository;

import org.springframework.data.repository.CrudRepository;

import vo.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
