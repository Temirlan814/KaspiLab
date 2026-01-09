package project.labmodule1project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.labmodule1project2.models.Category;
import project.labmodule1project2.models.Item;
import project.labmodule1project2.services.CartService;

import java.util.*;

@SpringBootApplication
public class LabModule1Project2Application {

	public static void main(String[] args) {
		SpringApplication.run(LabModule1Project2Application.class, args);
        List<Item> items = new ArrayList<>();
        CartService cartService = new CartService();


        cartService.add(new Item("Milk", Category.FOOD, 500));
        cartService.add(new Item("Bread", Category.FOOD, 250));
        cartService.add(new Item("Cheese", Category.FOOD, 1800));

        cartService.add(new Item("TV", Category.ELECTRONICS, 200000));
        cartService.add(new Item("Phone", Category.ELECTRONICS, 120000));
        cartService.add(new Item("Headphones", Category.ELECTRONICS, 15000));


        List<Item> items2 = cartService.findAllAboveItems(6000);
        System.out.println(items2.toString());


        Map<Category, Integer> map = cartService.countAllItemsByCategory();
        System.out.println(map.toString());
	}

}
