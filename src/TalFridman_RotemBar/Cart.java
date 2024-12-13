package TalFridman_RotemBar;


import java.util.Date;

public class Cart {
    private double totalPrice;
    private Product[] products;
    private Date dateOfPurchase;
    private String cartId;
    private int numOfProducts;

    public Cart() {
        this.products = new Product[0];
        this.totalPrice = 0.0;
        this.cartId = null;
        this.numOfProducts = 0;
    }

    public Cart(Product[] products,double price,int numOfProducts) {
        this.products = products;
        this.totalPrice = price;
        this.cartId = null;
        this.numOfProducts = numOfProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setCartId(String s) {
        this.cartId = s;
    }

    public String getCartId() {
        return cartId;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(products.length == 0){
            builder.append("Current cart is empty\n");
        }else if (products != null && products.length > 0) {
            if (cartId != null) {
                builder.append("cartId: #" + cartId + "\n");
            }
            builder.append("    Cart total price: ").append(totalPrice + "$").append("\n");
            builder.append("    The products are: \n");
            for (Product product : products) {
                if (product != null) {
                    builder.append(product);
                }
            }
        }
        if (dateOfPurchase != null) {
            builder.append("Date of purchase: ").append(dateOfPurchase).append("\n");
            builder.append("----------------------------------------------------\n");
        }
        return builder.toString();
    }


}

