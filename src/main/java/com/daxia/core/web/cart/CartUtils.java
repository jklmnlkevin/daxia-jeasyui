package com.daxia.core.web.cart;

import javax.servlet.http.HttpServletRequest;

public class CartUtils {
	public static Cart getCart(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute("cart");
		Cart cart;
		if (obj == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		} else {
			cart = (Cart) obj;
		}
		return cart;
	}
}
