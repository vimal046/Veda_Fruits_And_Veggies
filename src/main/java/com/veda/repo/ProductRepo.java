package com.veda.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veda.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Procedure(name = "insertToProduct")
	void insertToProduct(@Param("Name") String name, @Param("Price") int price, @Param("ImageLocation") String imgLoc,
			@Param("Description") String desc);

	@Procedure(name = "getProductData")
	List<Product> getProductData();

	@Procedure(name = "updateProduct")
	void updateProduct(@Param("Id") int id, @Param("Name") String name, @Param("Price") int price,
			@Param("ImageLocation") String imgLoc, @Param("Description") String desc);
	
	@Procedure(name = "deleteProduct")
	void deleteProduct(@Param("Id") int id);
}
