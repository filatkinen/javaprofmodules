package ru.otus.filatkinen.pattern01;

import org.junit.jupiter.api.Test;
import ru.otus.filatkinen.pattern01.Box.Box;
import ru.otus.filatkinen.pattern01.Box.Iterator;
import ru.otus.filatkinen.pattern01.Product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void runProductTest() {

        Product product1 = new Product.ProductBuilder(1,"Product1")
                .setCost(10)
                .setWidth(20)
                .build();

        Product product2 = new Product.ProductBuilder(2,"Product2")
                .setDescription("Description product2")
                .setLength(100)
                .build();


        assertEquals(product1.getCost(), 10);
        assertEquals(product1.getWidth(),20);
        assertEquals(product1.getHeight(),0);

        assertEquals(product2.getDescription(),"Description product2");
        assertEquals(product2.getLength(),100);
        assertEquals(product2.getCost(),0);

    }

}
