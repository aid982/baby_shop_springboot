package com.osetskiy.baby_shop.rest.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.osetskiy.baby_shop.REPO.AccountRepository;
import com.osetskiy.baby_shop.REPO.OrderRepository;
import com.osetskiy.baby_shop.model.Order;
import com.osetskiy.baby_shop.model.User;
import com.osetskiy.baby_shop.rest.resources.OrderResource;

import com.osetskiy.baby_shop.rest.resources.asm.OrderResourceAsm;

import com.osetskiy.baby_shop.rest.utils.exceptions.ForbiddenException;
import com.osetskiy.baby_shop.security.CurrentUser;

@Controller
@RequestMapping("rest/orders")
public class OrderControllerRest {
	@Autowired
	private JavaMailSender javaMailSender;

	private OrderRepository orderRepository;
	private AccountRepository accountRepository;

	@Autowired
	public OrderControllerRest(OrderRepository orderRepository, AccountRepository accountRepository) {
		this.orderRepository = orderRepository;
		this.accountRepository = accountRepository;
	}

	@RequestMapping(value = "")
	@ResponseBody
	public ResponseEntity<List<OrderResource>> getOrdersByUser(@CurrentUser User currentUser) {
		List<Order> listOrders;		
		listOrders = orderRepository.findAll();				
		List<OrderResource> listOrderResorce = new ArrayList<OrderResource>();
		Iterator<Order> iterator = listOrders.iterator();
		while (iterator.hasNext()) {
			listOrderResorce.add(
					new OrderResourceAsm(OrderControllerRest.class, OrderResource.class).toResource(iterator.next()));
		}

		return new ResponseEntity<List<OrderResource>>(listOrderResorce, HttpStatus.OK);

	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderResource> getOrderById(@PathVariable("id") Integer orderId) {
		Order order = orderRepository.findOne(orderId);
		if (order != null) {
			OrderResource resource = new OrderResourceAsm(OrderControllerRest.class, OrderResource.class)
					.toResource(order);
			return new ResponseEntity<OrderResource>(resource, HttpStatus.OK);
		} else {
			return new ResponseEntity<OrderResource>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.POST)
	public void setProcessedOrders(@PathVariable("id") String orderId) {
		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo("osetskiy@dragon-capital.com");
			helper.setReplyTo("osetskiy@dragon-capital.com");
			helper.setFrom("osetskiy@dragon-capital.com");
			helper.setSubject("Lorem ipsum");
			helper.setText("Lorem ipsum dolor sit amet [...]");
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
		}
		javaMailSender.send(mail);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OrderResource> createOrder(@RequestBody OrderResource sentOrder) {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			User user = accountRepository.findByName(username);
			if (user != null) {
				Order order = sentOrder.toOrder();
				order.setCustomer(user);
				order.setProcessed(false);
				orderRepository.save(order);

			} else {
				throw new ForbiddenException();

			}
			return new ResponseEntity<OrderResource>(HttpStatus.OK);
		} else {
			return new ResponseEntity<OrderResource>(HttpStatus.FORBIDDEN);
		}

	}

}
