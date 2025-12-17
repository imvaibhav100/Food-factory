package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.CartItem;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderServices;
import com.example.demo.services.ProductServices;
import com.example.demo.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserDashboardController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private ProductServices productServices;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderServices orderServices;

	private User getCurrentUser(HttpSession session) {
		User user = (User) session.getAttribute("loggedInUser");
		if (user != null) {
			// Refresh user data from database
			return userServices.getUser(user.getU_id());
		}
		return null;
	}

	@GetMapping("/home")
	public String userHome(Model model, HttpSession session) {
		User currentUser = getCurrentUser(session);
		if (currentUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("name", currentUser.getUname());
		model.addAttribute("isLoggedIn", true);
		return "Home";
	}

	@GetMapping("/products")
	public String userProducts(Model model, HttpSession session) {
		User currentUser = getCurrentUser(session);
		if (currentUser == null) {
			return "redirect:/login";
		}
		List<Product> allProducts = productServices.getAllProducts();
		model.addAttribute("products", allProducts);
		model.addAttribute("name", currentUser.getUname());
		model.addAttribute("isLoggedIn", true);
		return "Products";
	}

	@GetMapping("/location")
	public String userLocation(Model model, HttpSession session) {
		User currentUser = getCurrentUser(session);
		if (currentUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("name", currentUser.getUname());
		model.addAttribute("isLoggedIn", true);
		return "Locate_us";
	}

	@GetMapping("/about")
	public String userAbout(Model model, HttpSession session) {
		User currentUser = getCurrentUser(session);
		if (currentUser == null) {
			return "redirect:/login";
		}
		model.addAttribute("name", currentUser.getUname());
		model.addAttribute("isLoggedIn", true);
		return "About";
	}

	@GetMapping("/dashboard")
	public String userDashboard(Model model, HttpSession session) {
		User currentUser = getCurrentUser(session);
		if (currentUser == null) {
			return "redirect:/login";
		}
		
		List<Product> allProducts = productServices.getAllProducts();
		List<CartItem> cartItems = cartService.getCartItems(currentUser);
		List<Orders> orderHistory = orderServices.getOrdersForUser(currentUser);
		double cartTotal = cartService.getCartTotal(currentUser);

		model.addAttribute("user", currentUser);
		model.addAttribute("products", allProducts);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("cartTotal", cartTotal);
		model.addAttribute("orderHistory", orderHistory);
		model.addAttribute("isLoggedIn", true);

		return "UserDashboard";
	}

	@PostMapping("/cart/add")
	@ResponseBody
	public String addToCart(@RequestParam("productId") int productId, 
	                        @RequestParam(value = "quantity", defaultValue = "1") int quantity,
	                        HttpSession session) {
		try {
			User currentUser = getCurrentUser(session);
			if (currentUser == null) {
				return "error";
			}
			Product product = productServices.getProduct(productId);
			cartService.addToCart(currentUser, product, quantity);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/cart/update")
	@ResponseBody
	public String updateCartQuantity(@RequestParam("cartItemId") int cartItemId,
	                                 @RequestParam("quantity") int quantity,
	                                 HttpSession session) {
		try {
			if (quantity > 0) {
				cartService.updateCartItemQuantity(cartItemId, quantity);
				return "success";
			} else {
				cartService.removeFromCart(cartItemId);
				return "removed";
			}
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/cart/remove")
	@ResponseBody
	public String removeFromCart(@RequestParam("cartItemId") int cartItemId, HttpSession session) {
		try {
			cartService.removeFromCart(cartItemId);
			return "success";
		} catch (Exception e) {
			return "error";
		}
	}

	@PostMapping("/checkout")
	public String checkout(Model model, HttpSession session) {
		try {
			User currentUser = getCurrentUser(session);
			if (currentUser == null) {
				return "redirect:/login";
			}
			
			List<CartItem> cartItems = cartService.getCartItems(currentUser);

			if (cartItems.isEmpty()) {
				return "redirect:/user/dashboard?error=emptyCart";
			}

			// Create orders from cart items
			for (CartItem item : cartItems) {
				Orders order = new Orders();
				order.setoName(item.getProduct().getPname());
				order.setoPrice(item.getProduct().getPprice());
				order.setoQuantity(item.getQuantity());
				order.setTotalAmmout(item.getTotalPrice());
				order.setOrderDate(new Date());
				order.setUser(currentUser);
				orderServices.saveOrder(order);
			}

			double orderTotal = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();

			// Clear cart after successful checkout
			cartService.clearCart(currentUser);

			model.addAttribute("orderTotal", orderTotal);
			return "Order_success";
		} catch (Exception e) {
			return "redirect:/user/dashboard?error=checkoutFailed";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
}
