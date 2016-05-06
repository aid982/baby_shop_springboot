package com.osetskiy.baby_shop.REPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.osetskiy.baby_shop.model.Product;
import com.osetskiy.baby_shop.model.ProductItem;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItem,String> {
	List<ProductItem> findByProduct(Product product);
	@Query(value = "SELECT u.size,COUNT(u.id) FROM ProductItem u GROUP BY u.size")
	List<ProductItem> findAllGroupBySizes();
	
	

}
