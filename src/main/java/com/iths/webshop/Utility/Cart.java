package com.iths.webshop.Utility;

import com.iths.webshop.entity.Product;
import lombok.Data;
import java.util.HashMap;
import java.util.Optional;


@Data
public class Cart {

    HashMap<Product,Integer> products = new HashMap<>();

    public Cart() {

    }

    public int size(){

       int cartSize = products.values().stream().reduce(0, Integer::sum);
        return cartSize;
    }

    public void add(Product product) {
        add(product,1);
    }
    public void add(int id) {
        Optional<Product> product = products.keySet().stream().filter(p -> p.getId() == id).findAny();
        if(product.isPresent()){
            add(product.get());
        }
    }

    public void add(Product product, int quantity) {
        if(product == null || quantity <1){
            return;
        }

            Product productToBeAdded = product;
        Optional<Product> oproduct = products.keySet().stream().filter(p -> p.getId() == product.getId()).findAny();
        if(oproduct.isPresent()){
            productToBeAdded = oproduct.get();
        }


        if(products.containsKey(productToBeAdded)){

          int qty =  products.get(productToBeAdded);
            products.put(productToBeAdded,qty+quantity);
        }else{

        products.put(productToBeAdded,quantity);
        }
    }

    public void removeOne(Product product) {
    Product productToRemove = product;
    Optional<Product> oproduct = products.keySet().stream().filter(p -> p.getId() == product.getId()).findAny();
    if(oproduct.isPresent()){
        productToRemove = oproduct.get();
    }

      if(!products.containsKey(productToRemove)){return;}

      int qty = products.get(productToRemove);
        System.out.println(qty);
      if(qty <=1){
          removeAll(productToRemove);
          return;
      }

      products.put(productToRemove,qty-1);
    }

    public void removeAll(Product product) {
        Optional<Product> oproduct = products.keySet().stream().filter(p -> p.getId() == product.getId()).findAny();
        if(oproduct.isPresent()){
        products.remove(oproduct.get());

        }
    }

    public void clear() {
        products.clear();
    }

    public double getTotalPrice(Product product) {
        int quantity = products.get(product);
        double sum = quantity * product.getPrice();
        return sum;
    }

    public double getTotalPrice() {
        double totalPrice = products.entrySet().stream().map(t -> t.getKey().getPrice() * t.getValue()).reduce(0.0, Double::sum);
        return totalPrice;
    }
}
