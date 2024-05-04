package ru.otus.filatkinen.pattern01.ProductLom;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductLomBok {
    private int id;
    private String title;

    private String description;
    private int cost;
    private int weight;
    private int width;
    private int length;
    private int height;
}
