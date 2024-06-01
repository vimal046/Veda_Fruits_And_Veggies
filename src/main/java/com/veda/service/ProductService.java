package com.veda.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.veda.entity.Product;
import com.veda.repo.ProductRepo;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	String uploaDir = System.getProperty("user.dir") + "/src/main/resources/static/img/u_img/";

	public void inserToProduct(String name,int price,String description, MultipartFile imageFile) {

		
		String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
		String imgLoc="img/u_img/"+imageName;

		File directory = new File(uploaDir);

		if (!directory.exists()) {
			directory.mkdirs();
		}
		try {
			File file = new File(uploaDir + imageName);
			imageFile.transferTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repo.insertToProduct(name, price,imgLoc,description);
	}
	
	
	
	@Transactional
	public List<Product> getProductData(){	
		return repo.getProductData();
	}
	
	
	
	public void deleteProduct(int id,String imgName) {
		
		System.out.println(id);
		System.out.println(imgName);
		
		String imagePath=uploaDir+imgName;
		File imageFile=new File(imagePath);
		
		if(imageFile.exists()) {
			if(imageFile.delete()) {
				System.out.println("Image deleted successfully");
				repo.deleteProduct(id);
			}else {
				System.out.println("Failed to delete Image");
			}
		}else {
			System.out.println("Image not found");
		}
	}
	
	public void updateProduct(Product obj) {
		
		int id=obj.getId();
		String name=obj.getName();
		int price=obj.getPrice();
		String desc=obj.getDescription();
		String imgLoc=obj.getImgLoc();
		
		
		repo.updateProduct(id, name, price, imgLoc,desc);
	}
	
}
