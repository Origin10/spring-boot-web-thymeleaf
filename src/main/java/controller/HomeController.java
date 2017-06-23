package controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.StuffService;
import service.CartItemService;
import service.OrderService;
import service.MemPaymentService;
import service.MemService;
import service.MemShippingService;


import utility.MailConstructor;
import utility.SecurityUtility;
import utility.Constants;

@Controller
public class HomeController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private MemService memService;
	
	@Autowired
	private StuffService stuffService;
	
	@Autowired
	private MemPaymentService memPaymentService;
	
	@Autowired
	private MemShippingService memShippingService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("classActiveLogin", true);
		return "myAccount";
	}
	
	@RequestMapping("/hours")
	public String hours() {
		return "hours";
	}
	
	@RequestMapping("/faq")
	public String faq() {
		return "faq";
	}
	
	@RequestMapping("/bookshelf")
	public String bookshelf(Model model, Principal principal) {
		if(principal != null) {
			String username = principal.getName();
			Mem_VO mem = memService.findByUsername(username);
			model.addAttribute("user", mem);
		}
		
		List<Book> bookList = stuffService.findAll();
		model.addAttribute("bookList", bookList);
		model.addAttribute("activeAll",true);
		
		return "bookshelf";
	}
	
	@RequestMapping("/bookDetail")
	public String bookDetail(
			@PathParam("id") Long id, Model model, Principal principal
			) {
		if(principal != null) {
			String username = principal.getName();
			Mem_VO mem = memService.findByUsername(username);
			model.addAttribute("user", mem);
		}
		
		Book book = stuffService.findOne(id);
		
		model.addAttribute("book", book);
		
		List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		
		return "bookDetail";
	}

	
	@RequestMapping("/myProfile")
	public String myProfile(Model model, Principal principal) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		model.addAttribute("user", mem);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("orderList", mem.getOrderList());
		
		MemShipping memShipping = new MemShipping();
		model.addAttribute("memShipping", memShipping);
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		List<String> stateList = Constants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("classActiveEdit", true);
		
		return "myProfile";
	}
	
	@RequestMapping("/listOfCreditCards")
	public String listOfCreditCards(
			Model model, Principal principal, HttpServletRequest request
			) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		model.addAttribute("user", mem);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("orderList", mem.getOrderList());
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		return "myProfile";
	}
	
	@RequestMapping("/listOfShippingAddresses")
	public String listOfShippingAddresses(
			Model model, Principal principal, HttpServletRequest request
			) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		model.addAttribute("user", mem);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("orderList", mem.getOrderList());
		
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		return "myProfile";
	}
	
	@RequestMapping("/addNewCreditCard")
	public String addNewCreditCard(
			Model model, Principal principal
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		model.addAttribute("user", mem);
		
		model.addAttribute("addNewCreditCard", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		MemBilling memBilling = new MemBilling();
		UserPayment userPayment = new UserPayment();
		
		
		model.addAttribute("memBilling", memBilling);
		model.addAttribute("userPayment", userPayment);
		
		List<String> stateList = Constants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("orderList", mem.getOrderList());
		
		return "myProfile";
	}
	
	@RequestMapping("/addNewShippingAddress")
	public String addNewShippingAddress(
			Model model, Principal principal
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		model.addAttribute("user", mem);
		
		model.addAttribute("addNewShippingAddress", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfCreditCards", true);
		
		MemShipping memShipping = new MemShipping();
		
		model.addAttribute("memShipping", memShipping);
		
		List<String> stateList = Constants.listOfUSStatesCode;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("orderList", mem.getOrderList());
		
		return "myProfile";
	}
	
	@RequestMapping(value="/addNewCreditCard", method=RequestMethod.POST)
	public String addNewCreditCard(
			@ModelAttribute("userPayment") UserPayment userPayment,
			@ModelAttribute("userBilling") MemBilling memBilling,
			Principal principal, Model model
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		memService.updateUserBilling(memBilling, userPayment, mem);
		
		model.addAttribute("user", mem);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("orderList", mem.getOrderList());
		
		return "myProfile";
	}
	
	@RequestMapping(value="/addNewShippingAddress", method=RequestMethod.POST)
	public String addNewShippingAddressPost(
			@ModelAttribute("userShipping") MemShipping memShipping,
			Principal principal, Model model
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		memService.updateUserShipping(memShipping, mem);
		
		model.addAttribute("user", mem);
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("orderList", mem.getOrderList());
		
		return "myProfile";
	}
	
	
	@RequestMapping("/updateCreditCard")
	public String updateCreditCard(
			@ModelAttribute("id") Long creditCardId, Principal principal, Model model
			) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		UserPayment userPayment = memPaymentService.findById(creditCardId);
		
		if(mem.getmem_id() != userPayment.getMem().getmem_id()) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", mem);
			MemBilling memBilling = userPayment.getMemBilling();
			model.addAttribute("userPayment", userPayment);
			model.addAttribute("memBilling", memBilling);
			
			List<String> stateList = Constants.listOfUSStatesCode;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			model.addAttribute("addNewCreditCard", true);
			model.addAttribute("classActiveBilling", true);
			model.addAttribute("listOfShippingAddresses", true);
			
			model.addAttribute("userPaymentList", mem.getUserPaymentList());
			model.addAttribute("userShippingList", mem.getMemShippingList());
			model.addAttribute("orderList", mem.getOrderList());
			
			return "myProfile";
		}
	}
	
	@RequestMapping("/updateUserShipping")
	public String updateUserShipping(
			@ModelAttribute("id") Long shippingAddressId, Principal principal, Model model
			) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		MemShipping memShipping = memShippingService.findById(shippingAddressId);
		
		if(mem.getmem_id()!= memShipping.getMem().getmem_id()) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", mem);
			
			model.addAttribute("memShipping", memShipping);
			
			List<String> stateList = Constants.listOfUSStatesCode;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			model.addAttribute("addNewShippingAddress", true);
			model.addAttribute("classActiveShipping", true);
			model.addAttribute("listOfCreditCards", true);
			
			model.addAttribute("userPaymentList", mem.getUserPaymentList());
			model.addAttribute("userShippingList", mem.getMemShippingList());
			model.addAttribute("orderList", mem.getOrderList());
			
			return "myProfile";
		}
	}
	
	@RequestMapping(value="/setDefaultPayment", method=RequestMethod.POST)
	public String setDefaultPayment(
			@ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal, Model model
			) {
		Mem_VO mem = memService.findByUsername(principal.getName());
		memService.setMemDefaultPayment(defaultPaymentId, mem);
		
		model.addAttribute("user", mem);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		model.addAttribute("userPaymentList", mem.getUserPaymentList());
		model.addAttribute("userShippingList", mem.getMemShippingList());
		model.addAttribute("orderList", mem.getOrderList());
		
		return "myProfile";
	}
	
	@RequestMapping(value="/setDefaultShippingAddress", method=RequestMethod.POST)
	public String setDefaultShippingAddress(
			@ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal, Model model
			) {
		Mem_VO user = memService.findByUsername(principal.getName());
		memService.setUserDefaultShipping(defaultShippingId, user);
		
		model.addAttribute("user", user);
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getMemShippingList());
		model.addAttribute("orderList", user.getOrderList());
		
		return "myProfile";
	}
	
	@RequestMapping("/removeCreditCard")
	public String removeCreditCard(
			@ModelAttribute("id") Long creditCardId, Principal principal, Model model
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		UserPayment userPayment = memPaymentService.findById(creditCardId);
		
		if(mem.getmem_id() != userPayment.getMem().getmem_id()) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", mem);
			memPaymentService.removeById(creditCardId);
			
			model.addAttribute("listOfCreditCards", true);
			model.addAttribute("classActiveBilling", true);
			model.addAttribute("listOfShippingAddresses", true);
			
			model.addAttribute("userPaymentList", mem.getUserPaymentList());
			model.addAttribute("userShippingList", mem.getMemShippingList());
			model.addAttribute("orderList", mem.getOrderList());
			
			return "myProfile";
		}
	}
	
	@RequestMapping("/removeUserShipping")
	public String removeUserShipping(
			@ModelAttribute("id") Long userShippingId, Principal principal, Model model
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		MemShipping memShipping = memShippingService.findById(userShippingId);
		
		if(mem.getmem_id() != memShipping.getMem().getmem_id()) {
			return "badRequestPage";
		} else {
			model.addAttribute("user", mem);
			
			memShippingService.removeById(userShippingId);
			
			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveShipping", true);
			
			model.addAttribute("userPaymentList", mem.getUserPaymentList());
			model.addAttribute("userShippingList", mem.getMemShippingList());
			model.addAttribute("orderList", mem.getOrderList());
			
			return "myProfile";
		}
	}
	


	@RequestMapping("/orderDetail")
	public String orderDetail(
			@RequestParam("id") Long orderId,
			Principal principal, Model model
			){
		Mem_VO mem = memService.findByUsername(principal.getName());
		Order order = orderService.findOne(orderId);
		
		if(order.getMem().getmem_id()!=mem.getmem_id()) {
			return "badRequestPage";
		} else {
			List<CartItem> cartItemList = cartItemService.findByOrder(order);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("user", mem);
			model.addAttribute("order", order);
			
			model.addAttribute("userPaymentList", mem.getUserPaymentList());
			model.addAttribute("userShippingList", mem.getMemShippingList());
			model.addAttribute("orderList", mem.getOrderList());
			
			MemShipping memShipping = new MemShipping();
			model.addAttribute("memShipping", memShipping);
			
			List<String> stateList = Constants.listOfUSStatesCode;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveOrders", true);
			model.addAttribute("listOfCreditCards", true);
			model.addAttribute("displayOrderDetail", true);
			
			return "myProfile";
		}
	}
	
	
	
	
}
