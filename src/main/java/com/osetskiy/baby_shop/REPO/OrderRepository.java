package com.osetskiy.baby_shop.REPO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.osetskiy.baby_shop.model.Order;
import com.osetskiy.baby_shop.model.User;
public interface OrderRepository extends CrudRepository<Order, Integer>{
	List<Order> findByCustomer(User user);
	//@Query("select o from Order o where o.customer.id = ?#{principal.id}")
	//@Query("select o from Order o where o.customer.id like "+"?#{hasRole('ROLE_ADMIN') ? '' : principal.id}")
	@Query("select o from Order o where o.customer.id = ?#{principal.id} or 1=?#{hasRole('ROLE_ADMIN') ? 1: 0}")
	List<Order> findAll();

}
