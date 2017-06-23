package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import vo.CartItem;
import vo.Order;
import vo.ShoppingCart;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	List<CartItem> findByOrder(Order order);
}
