package com.example.SpringSecurity;

import com.example.SpringSecurity.dto.ItemDTO;
import com.example.SpringSecurity.mapper.ItemMapper;
import com.example.SpringSecurity.model.Item;
import com.example.SpringSecurity.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityApplicationTests {
	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemService itemService;

	@Test
 void compareDtoAndModel(){
		Item item = new Item();
		item.setPrice(10000);
		item.setName("Ipad");
		item.setDescription("Laptop");

		ItemDTO itemDTO = itemMapper.toItemDTO(item);

		Assertions.assertEquals(item.getDescription(),itemDTO.getDescription());
		Assertions.assertEquals(item.getPrice(),itemDTO.getPrice());
		Assertions.assertEquals(item.getName(),itemDTO.getItemName());

	}
	@Test
	void examinationDB(){
		Item item = new Item();
		item.setName("Samsung S21");
		item.setPrice(200000);
		ItemDTO itemDTO = itemMapper.toItemDTO(item);
		itemService.addItem(itemDTO);
		itemService.deleteItem(itemDTO.getId());
	}

}
