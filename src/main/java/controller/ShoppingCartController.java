package controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.CartItemService;
import service.MemService;
import service.ShoppingCartService;
import service.StuffService;
import vo.Book;
import vo.CartItem;
import vo.Mem_VO;
import vo.ShoppingCart;


@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private MemService memService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private StuffService stuffService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = mem.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		return "shoppingCart";
	}

	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("book") Book book,
			@ModelAttribute("qty") String qty,
			Model model, Principal principal
			) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		book = stuffService.findOne(book.getId());
		
		if (Integer.parseInt(qty) > book.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/bookDetail?id="+book.getId();
		}
		
		CartItem cartItem = cartItemService.addBookToCartItem(book, mem, Integer.parseInt(qty));
		model.addAttribute("addBookSuccess", true);
		
		return "forward:/bookDetail?id="+book.getId();
	}
	
	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(
			@ModelAttribute("id") Long cartItemId,
			@ModelAttribute("qty") int qty
			) {
		CartItem cartItem = cartItemService.findById(cartItemId);
		cartItem.setQty(qty);
		cartItemService.updateCartItem(cartItem);
		
		return "forward:/shoppingCart/cart";
	}
	
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id) {
		cartItemService.removeCartItem(cartItemService.findById(id));
		
		return "forward:/shoppingCart/cart";
	}
}
