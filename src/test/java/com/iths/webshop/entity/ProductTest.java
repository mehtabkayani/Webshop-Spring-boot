package com.iths.webshop.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void setPriceToNegative() {
        //Arrange
        Product product = new Product();
        double expectedPrice = 0;

        //Action
        product.setPrice(3);

        Executable setPriceToNegative = () -> product.setPrice(-1);
        //Assert
        assertThrows(IllegalArgumentException.class, setPriceToNegative);

    }
}