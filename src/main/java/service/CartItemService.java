package service;

import java.util.List;

import vo.Book;
import vo.CartItem;
import vo.Order;
import vo.ShoppingCart;
import vo.Mem_VO;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addBookToCartItem(Book book, Mem_VO mem, int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
