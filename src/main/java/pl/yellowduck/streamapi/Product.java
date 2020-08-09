package pl.yellowduck.streamapi;

public class Product {

    private String name;
    private int size;

    private Product() {
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static final class ProductBuilder {
        private String name;
        private int size;

        private ProductBuilder() {
        }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withSize(int size) {
            this.size = size;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.name = this.name;
            product.size = this.size;
            return product;
        }
    }
}
