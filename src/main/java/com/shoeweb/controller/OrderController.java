package com.shoeweb.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shoeweb.config.PaymentMethod;
import com.shoeweb.dao.OrderDAO;
import com.shoeweb.dao.OrderDetailDAO;
import com.shoeweb.dao.ProductDAO;
import com.shoeweb.entity.Order;
import com.shoeweb.entity.OrderDetail;
import com.shoeweb.entity.Product;
import com.shoeweb.entity.User;
import com.shoeweb.service.CartService;



@Controller
public class OrderController {
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	CartService cart;
	
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;

	@GetMapping("/order/checkout")
	public String showForm(@ModelAttribute("order") Order order, Model model) {
		User user = (User) session.getAttribute("user");

		order.setOrderDate(new Date());
		order.setUser(user);
		order.setAmount(cart.getAmount());
		order.setStatus(1);
		List<String> payments=PaymentMethod.LIST_PAYMENT();
		model.addAttribute("payments",payments);
		return "order/checkout";

	}

	
	
//	@PostMapping("/order/checkout")
//	public String purchase(Model model, 
//			@ModelAttribute("order") Order order){
//		Collection<Product> list = cart.getItems();
//		List<OrderDetail> details = new  ArrayList<>();
//		for(Product product:list) {
//			OrderDetail detail =new OrderDetail();
//			detail.setOrder(order);
//			detail.setProduct(product);
//			detail.setUnitPrice(product.getUnitPrice());
//			detail.setQuantity(product.getQuantity());
//			detail.setDiscount(product.getDiscount());
//			details.add(detail);
//
//		}
//		dao.create(order, details);
//		cart.clear();
//		
//		 
//		model.addAttribute("message", "Đặt hàng thành công!");
//
//		
//		return "redirect:/order/list";
//
//	}
	
	
	@GetMapping("/order/list")
	public String list(Model model) {
		User user = (User) session.getAttribute("user");
		List<Order> orders = dao.findByUser(user);
		model.addAttribute("orders", orders);
		return "order/list";
	}
	
	@GetMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Order order = dao.findById(id);
		List<OrderDetail> details = ddao.findByOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("details", details);
		return "order/detail";
	}
	
	@GetMapping("/order/items")
	public String items(Model model) {
		User user = (User) session.getAttribute("user");
		List<Product> list = dao.findItemsByUser(user);
		model.addAttribute("list", list);
		return "product/list_order_item";
	}
}




