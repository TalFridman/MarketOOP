package TalFridman_RotemBar;


public class Seller extends User implements Comparable<Seller> {
    private Product[] products;
    private int numOfProducts;

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    public Seller(String name, String password) {
        super(name, password);
        this.products = new Product[0];
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Seller name: ").append(name).append("\n");
        if (products.length > 0) {
            builder.append("    ").append(name).append("'s ").append("Products: ").append("\n");
        for (Product product : products) {
            if (product != null) {
                builder.append(product);
            } else {
                break;
            }
        }
        }else{
            builder.append(name).append(" dont have any products yet\n");
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Seller o) {
        return this.products.length - o.products.length;
    }
}
