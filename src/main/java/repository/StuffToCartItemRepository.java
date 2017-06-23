package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vo.StuffToCartItem;
import vo.CartItem;

@Transactional
public interface StuffToCartItemRepository extends CrudRepository<StuffToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
