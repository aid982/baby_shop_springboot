package com.osetskiy.baby_shop.REPO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.osetskiy.baby_shop.model.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,String> {
	@Query(value = "SELECT u.category.name,COUNT(u.id) FROM Product u  GROUP BY u.category.name")
	List<Category> findAllCategoriesWithCountingProducts();

}
