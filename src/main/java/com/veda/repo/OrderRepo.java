package com.veda.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.veda.entity.OrderForm;


@Repository
public interface OrderRepo extends JpaRepository<OrderForm, Integer> {

	@Procedure(name = "InsertToOrderForm")
	int InsertToOrderForm(@Param("f_name") String fName, @Param("l_name") String lName, @Param("c_name") String cName,
			@Param("Street") String street, @Param("City") String city, @Param("Country") String country,
			@Param("Postcode") String postcode, @Param("Mobile") String mob, @Param("Email") String email,
			@Param("Notes") String notes, @Param("grand_total") int gTotal);

	@Procedure(name = "insetToOrderItem")
	void insetToOrderItem(@Param("product_name") String productName, @Param("Price") int price,
			@Param("Quantity") int quantity, @Param("order_id") int orderId);
	
	@Procedure(name = "getOrderForm")
	List<OrderForm> getOrderForm();
	
	@Procedure(name = "getOrderItem")
	List<Object[]> getOrderItem();
}
