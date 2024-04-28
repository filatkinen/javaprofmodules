package ru.otus.filatkinen.pattern01.Product;

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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
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


        public ProductBuilder(int id, String title) {
            this.id = id;
            this.title = title;
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


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                ", width=" + width +
                ", length=" + length +
                ", height=" + height +
                '}';
    }
}
