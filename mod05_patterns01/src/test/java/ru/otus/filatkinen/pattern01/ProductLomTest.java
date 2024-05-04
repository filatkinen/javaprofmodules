package ru.otus.filatkinen.pattern01;

import org.junit.jupiter.api.Test;
import ru.otus.filatkinen.pattern01.ProductLom.ProductLomBok;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductLomTest {

    @Test
    void runProductLomTest() {

        ProductLomBok product1 = ProductLomBok.builder()
                .id(1)
                .title("Product1")
                .cost(10)
                .width(20)
                .build();

        ProductLomBok product2 = ProductLomBok.builder()
                .id(2)
                .title("Product2")
                .description("Description product2")
                .length(100)
                .build();


        assertEquals(product1.getCost(), 10);
        assertEquals(product1.getWidth(), 20);
        assertEquals(product1.getHeight(), 0);

        assertEquals(product2.getDescription(), "Description product2");
        assertEquals(product2.getLength(), 100);
        assertEquals(product2.getCost(), 0);

    }
}
