package com.veda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veda.entity.FormObject;
import com.veda.entity.OrderForm;
import com.veda.entity.OrderItem;
import com.veda.repo.OrderRepo;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepo repo;

	public void insertForm(OrderForm obj, List<OrderItem> itemObjList) {

		String fName = obj.getfName();
		String lName = obj.getlName();
		String cName = obj.getcName();
		String street = obj.getStreet();
		String city = obj.getCity();
		String country = obj.getCountry();
		String postcode = obj.getPostcode();
		String mobile = obj.getMobile();
		String email = obj.getEmail();
		String notes = obj.getNotes();
		int grandTotal = obj.getGrandTotal();

		int orderId = repo.InsertToOrderForm(fName, lName, cName, street, city, country, postcode, mobile, email, notes,
				grandTotal);

		for (OrderItem itemObj : itemObjList) {
			String productName = itemObj.getProductName();
			int price = itemObj.getPrice();
			int quantity = itemObj.getQuantity();

			repo.insetToOrderItem(productName, price, quantity, orderId);
		}

	}

	@Transactional
	public List<FormObject> getFormObject() {

		List<OrderForm> formList = repo.getOrderForm();
		List<Object[]> objectArrays = repo.getOrderItem();

		List<OrderItem> itemList = new ArrayList<>();
		for (Object[] objArray : objectArrays) {

			OrderItem orderItem = new OrderItem();
			orderItem.setItemId((int) objArray[0]);
			orderItem.setProductName((String) objArray[1]);
			orderItem.setPrice((int) objArray[2]);
			orderItem.setQuantity((int) objArray[3]);
			orderItem.setOrderId((int) objArray[4]);

			itemList.add(orderItem);
		}

		List<FormObject> formObjects = new ArrayList<>();

		for (OrderForm form : formList) {
			FormObject formObject = new FormObject();
			formObject.setFormId(form.getOrderId());

			List<OrderItem> matchingItems = itemList.stream().filter(item -> item.getOrderId() == form.getOrderId())
					.collect(Collectors.toList());

			formObject.setFormObj(form);
			formObject.setItemObj(matchingItems);
			formObjects.add(formObject);
		}

		return formObjects;
	}
}
