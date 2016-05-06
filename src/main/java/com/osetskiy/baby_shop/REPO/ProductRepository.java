package com.osetskiy.baby_shop.REPO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.osetskiy.baby_shop.model.Category;
import com.osetskiy.baby_shop.model.Product;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, String>  {	
	List<Product> findByFeatured(Boolean featured);
	List<Product> findByCategory(Category category);
	Page<Product> findByCategory(Category category,Pageable pageable);

}
