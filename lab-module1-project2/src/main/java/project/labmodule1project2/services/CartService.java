package project.labmodule1project2.services;

import project.labmodule1project2.models.Category;
import project.labmodule1project2.models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CartService {
    List<Item> items;
    public CartService() {
        items = new ArrayList<>();
    }
    public void add(Item item){
        items.add(item);
    }
    public List<Item> findAllAboveItems(Integer amount){

//        for(Item item:items){
//            if(amount < item.getPrice()){
//                itemsAbovePrice.add(item);
//            }
//        }
        return items.
                stream().
                filter(item -> item.getPrice() > amount).
                toList();
    }
    public Map<Category, Integer> countAllItemsByCategory(){
        Map<Category, Integer> itemsByCategory = new HashMap<>();
        items.forEach((item)->{
            if(itemsByCategory.containsKey(item.getCategory())){
                itemsByCategory.put(item.getCategory(), itemsByCategory.get(item.getCategory())+1);
            } else {
                itemsByCategory.put(item.getCategory(), 1);
            }
        });
        return itemsByCategory;
//        for(Item item:items){
//            if(itemsByCategory.containsKey(item.getCategory())){
//                itemsByCategory.put(item.getCategory(),itemsByCategory.get(item.getCategory())+1);
//            }else {
//                itemsByCategory.put(item.getCategory(),1);
//            }
//        }
//        return itemsByCategory;
    }
}
