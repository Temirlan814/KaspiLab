package project.labmodule1project2.services;

import project.labmodule1project2.models.Category;
import project.labmodule1project2.models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    List<Item> items;
    public CartService() {
        items = new ArrayList<>();
    }
    public void add(Item item){
        items.add(item);
    }
    public List<Item> findAllAboveItems(Integer amount){
        List<Item> itemsAbovePrice = new ArrayList<>();
        for(Item item:items){
            if(amount < item.getPrice()){
                itemsAbovePrice.add(item);
            }
        }
        return itemsAbovePrice;
    }
    public Map<Category, Integer> countAllItemsByCategory(){
        Map<Category, Integer> itemsByCategory = new HashMap<>();
        for(Item item:items){
            if(itemsByCategory.containsKey(item.getCategory())){
                itemsByCategory.put(item.getCategory(),itemsByCategory.get(item.getCategory())+1);
            }else {
                itemsByCategory.put(item.getCategory(),1);
            }
        }
        return itemsByCategory;
    }
}
