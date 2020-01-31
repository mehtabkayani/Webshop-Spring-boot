package com.iths.webshop.entity;

import com.iths.webshop.Utility.Cart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    @Test
    public void createNewCart(){
        //Setup

        Cart cart = new Cart();
        int expectedSize = 0;
        //Action

        //Assert
        assertEquals(expectedSize,cart.size());

    }

    @Test
    public void addToCart(){
        //Setup
        Product product = new Product();
        Cart cart = new Cart();
        int expectedSize = 1;

        //Action
        cart.add(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void addToCartTwice(){
        //Setup
        Product product = new Product();

        Cart cart = new Cart();
        int expectedSize = 2;

        //Action
        cart.add(product);
        cart.add(product);

        //Assert
        assertEquals(expectedSize,cart.size());
        assertEquals(1,cart.getProducts().size());
    }

    @Test
    public void addTwoOfOneProductToCart(){
        //Setup
        Product product = new Product();

        Cart cart = new Cart();
        int expectedSize = 2;

        //Action
        cart.add(product, 2);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void removeOneProductFromCart(){
        //Setup
        Product product = new Product();

        Cart cart = new Cart();
        int expectedSize = 1;

        //Action
        cart.add(product, 2);
        cart.removeOne(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void removeOneProductFromEmptyCart(){
        //Setup
        Product product = new Product();
        Cart cart = new Cart();
        int expectedSize = 0;

        //Action

        cart.removeOne(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void removeOneProductFromNonEmptyCart(){
        //Setup
        Product product = new Product();
        Product product2 = new Product();
        product2.setId(2);
        product.setId(1);
        product2.setName("Apple");

        Cart cart = new Cart();
        int expectedSize = 1;

        //Action
        cart.add(product2);

        cart.removeOne(product);

        //Assert
        assertEquals(expectedSize,cart.size());

    }

    @Test
    public void addTwoOfOneProductWhichExistsToCart(){
        //Setup
        Product product = new Product();

        Cart cart = new Cart();
        int expectedSize = 8;

        //Action
        cart.add(product, 2);
        cart.add(product, 5);
        cart.add(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void addNull(){
        //Setup
        Product product = null;

        Cart cart = new Cart();
        int expectedSize = 0;

        //Action

        cart.add(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void addProductWithNegativeQty(){
        //Setup
        Product product = new Product();

        Cart cart = new Cart();
        int expectedSize = 0;

        //Action

        cart.add(product,-1);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void removeAllProducts(){
        //Setup
        Product product = new Product();

        Cart cart = new Cart();
        int expectedSize = 0;

        //Action

        cart.add(product,4);
        cart.removeAll(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void clearCart(){
        //Setup
        Product product = new Product();
        Product product2 = new Product();

        Cart cart = new Cart();
        int expectedSize = 0;

        //Action

        cart.add(product,4);
        cart.add(product2,5);
        cart.clear();

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void clearCart2(){
        //Setup
        Product product = new Product();
        Product product2 = new Product();

        product.setId(1);
        product2.setId(2);

        Cart cart = new Cart();
        int expectedSize = 5;

        //Action

        cart.add(product,1);
        cart.add(product2,5);
        cart.removeOne(product);
        cart.removeOne(product);

        //Assert
        assertEquals(expectedSize,cart.size());
    }

    @Test
    public void totalPriceForProduct(){
        //Setup
        Product apple = new Product();
        Product banana = new Product();

        apple.setPrice(1);
        apple.setId(1);
        banana.setPrice(3);
        banana.setId(2);
        int appleQuantity = 1;
        int bananaQuantity = 5;
        Cart cart = new Cart();

        double expectedApplePrice = apple.getPrice() * appleQuantity;
        double expectedBananaPrice = banana.getPrice() * bananaQuantity;


        //Action

        cart.add(apple,appleQuantity);
        cart.add(banana,bananaQuantity);
        double applesPrice = cart.getTotalPrice(apple);
        double bananasPrice = cart.getTotalPrice(banana);

        //Assert
        assertEquals(expectedApplePrice,applesPrice);
        assertEquals(expectedBananaPrice,bananasPrice);
    }

    @Test
    public void totalPriceForCart(){
        //Setup
        Product apple = new Product();
        Product banana = new Product();

        apple.setPrice(1);
        apple.setId(1);
        banana.setPrice(3);
        banana.setId(2);
        int appleQuantity = 1;
        int bananaQuantity = 5;
        Cart cart = new Cart();

        double expectedApplePrice = apple.getPrice() * appleQuantity;
        double expectedBananaPrice = banana.getPrice() * bananaQuantity;


        //Action

        cart.add(apple,appleQuantity);
        cart.add(banana,bananaQuantity);
        double applesPrice = cart.getTotalPrice(apple);
        double bananasPrice = cart.getTotalPrice(banana);
        double expectedTotalPrice = applesPrice + bananasPrice;
        double totalPrice = cart.getTotalPrice();

        //Assert
        assertEquals(expectedTotalPrice,totalPrice);

    }




}