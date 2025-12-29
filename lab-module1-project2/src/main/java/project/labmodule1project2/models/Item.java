package project.labmodule1project2.models;

public class Item {
    String name;
    Category category;
    Integer price;

    public Item(String name,  Category category, Integer price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", category=" + category + ", price=" + price + '}';
    }
}
