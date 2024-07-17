
package CafeMSApp;

import java.text.NumberFormat;

public class Item {
    
    private String name;
    private int quantity;
    private double price;
    private double total;
    
    public Item(){
        name ="";
        quantity = 0;
        price = 0;
        total = 0;
    }
    
    public  Item(String item, int quantity, double price){
        this.name = item;
        this.quantity = quantity;
        this.price = price;
        this.total = price * quantity;
    }
    
    
    public  Item(String item, double price){
        this.name = item;
        this.price = price;
    }
    
    public void setName(String item){
        this.name = item;
    }
    
    public String getName(){
        return name;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getPriceFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    
    public String getTotalFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(total);
    }
     
    
    public double getTotal(){
        return total;
    }
    
}
