package TalFridman_RotemBar;

public class Product {
    private String productName;
    private double price;
    private String serialNumber;
    private Category category;
    private boolean isSpecialPacking;
    private double specialPackingFee;

    public Product(double price, String productName,String serialNumber, Category category, boolean isSpecialPacking, double specialPackingFee) {
        this.price = price;
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.category = category;
        this.isSpecialPacking = isSpecialPacking;
        this.specialPackingFee = specialPackingFee;
    }
    public Product(Product product) {
        this.price = product.getPrice();
        this.productName = product.getProductName();
        this.serialNumber = product.getSerialNumber();
        this.category = product.getCategory();
        this.isSpecialPacking = product.getIsSpecialPacking();
        this.specialPackingFee = product.getSpecialPackingFee();
    }

    public double getSpecialPackingFee() {
        return specialPackingFee;
    }

    public boolean getIsSpecialPacking() {
        return isSpecialPacking;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setPrice(double v) {
        this.price = v;
    }

    public Category getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("        Product name: ").append(productName);
        if (isSpecialPacking) {
            builder.append("\n        Price: ").append(price - specialPackingFee).append("$ + ").append(specialPackingFee).append("$ for special packing\n");
            builder.append("        Total price: ").append(price).append("$\n");

        }else {
            builder.append("\n        Price: ").append(price).append("$\n");
        }
        builder.append("        Serial number: ").append("#").append(serialNumber).append("\n");
        builder.append("        Category: ").append(category).append("\n");
        builder.append("        ---------------\n");
        return builder.toString();
    }

}
