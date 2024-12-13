package TalFridman_RotemBar;


public class Buyer extends User {

    private Cart buyerCurrentCart;
    private final Address buyerAddress;
    private Cart[] buyerHistoryCarts;
    private int buyerHistorySize;


    public Buyer(String name, Address buyerAddress, String password) {
        super(name, password);
        this.buyerAddress = buyerAddress;
        this.buyerCurrentCart = new Cart();
        this.buyerHistoryCarts = new Cart[0];
        this.buyerHistorySize = 0;
    }

    public int getBuyerHistorySize() {
        return buyerHistorySize;
    }

    public void setBuyerHistorySize(int buyerHistorySize) {
        this.buyerHistorySize = buyerHistorySize;
    }

    public Cart[] getBuyerCartHistory() {
        return buyerHistoryCarts;
    }

    public void setBuyerCartHistory(Cart[] buyerHistoryCarts) {
        this.buyerHistoryCarts = buyerHistoryCarts;
    }

    public Cart getBuyerCurrentCart() {
        return buyerCurrentCart;
    }

    public void setBuyerCurrentCart(Cart buyerCurrentCart) {
        this.buyerCurrentCart = buyerCurrentCart;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Buyer name: ").append(name).append("\n")
                .append("    Buyer address: ").append("\n").append(buyerAddress)
                .append("\nBuyer current cart: ").append("\n").append(buyerCurrentCart).append("\n");
        if (buyerHistoryCarts.length > 0) {
            builder.append("Buyer carts history:\n");
//            builder.append("----------------------\n");
            for (int i = 0; i < buyerHistoryCarts.length; i++) {
                if(buyerHistoryCarts[i] != null) {
                    builder.append("    ").append(i + 1).append(". ").append(buyerHistoryCarts[i]);
                }else{
                    break;
                }
            }
        }else{
            builder.append(name).append(" doesn't have history cart yet\n");
            builder.append("----------------------------------------------------");

        }
        return builder.toString();
    }


    public String toHistoryCartsString() {
        StringBuilder builder = new StringBuilder();
        if (buyerHistoryCarts.length > 0) {
            builder.append("Buyer carts history:\n");
            for (int i = 0; i < buyerHistoryCarts.length; i++) {
                if (buyerHistoryCarts[i] != null) {
                    builder.append(i + 1).append(". ").append(buyerHistoryCarts[i]).append("\n");
                } else {
                    break;
                }
            }
        }else{
            builder.append(name).append(" doesn't have history cart yet\n");
            builder.append("----------------------------------------------------");

        }
        return builder.toString();
    }

}

