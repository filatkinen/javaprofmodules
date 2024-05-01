package ru.otus.filatkinen.pattern01.Product;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString()
public class Product {
    private int id;
    private String title;

    private String description;
    private int cost;
    private int weight;
    private int width;
    private int length;
    private int height;

    private Product(){
    }


    private Product(ProductBuilder productBuilder){
        this.id = productBuilder.id;
        this.title = productBuilder.title;
        this.description = productBuilder.description;
        this.cost = productBuilder.cost;
        this.weight = productBuilder.weight;
        this.width = productBuilder.width;
        this.length = productBuilder.length;
        this.height = productBuilder.height;
    }

    public static class ProductBuilder{
        private int id;
        private String title;

        private String description;
        private int cost;
        private int weight;
        private int width;
        private int length;
        private int height;


        public static ProductBuilder builder(int id, String title) {
            ProductBuilder builder = new ProductBuilder();
            builder.id = id;
            builder.title = title;
            return builder;
        }

        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setCost(int cost) {
            this.cost = cost;
            return this;
        }
        public ProductBuilder setWeight(int weight) {
            this.weight = weight;
            return this;
        }
        public ProductBuilder setWidth(int width) {
            this.width = width;
            return this;
        }
        public ProductBuilder setLength(int length) {
            this.length = length;
            return this;
        }
        public ProductBuilder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }

}
