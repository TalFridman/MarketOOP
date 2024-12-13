package TalFridman_RotemBar;

public interface IMarket {

    public Buyer[] getBuyers();

    public Seller[] getSellers();

    public Buyer getBuyerByName(String name);

    public Seller getSellerByName(String name);

    public void addBuyer(String buyerName, Address address, String password);

    public int getNumOfSellers();

    public int getNumOfBuyers();

    public void addSeller(String sellerName, String password);

    public Buyer[] addItemToBuyersArray(String currString, Buyer[] currArray, Address address, String password);

    public Seller[] addItemToSellersArray(String currString, Seller[] currArray, String currPassword);

    public Buyer[] expandBuyersArray(Buyer[] currArrayE);

    public Seller[] expandSellersArray(Seller[] currArrayE);

    public Cart[] expandCartsArray(Cart[] currCartArray);

    public Product[] expandProductsArray(Product[] currArrayE);

    public boolean checkBuyersDouble(Buyer[] currArrayD, String currStringD);

    public boolean checkSellersDouble(Seller[] currArrayD, String currStringD);

    public Product getProductByName(String selectedProduct, Seller seller);

    public boolean addProductToSeller(double price, String productName, String seller, Category category, boolean isSpecialPacking, double specialPackingFee);

    public Product[] addProductToProducts(Product currProduct, Product[] currProducts);

    public boolean addProductToCart(Buyer currBuyer, Product currProduct);

    public Cart[] addCartToCartsHistory(Cart currCart, Buyer currBuyer);

    public boolean payForCart(Buyer currBuyer);

    public boolean replaceCurrCart(String id, Buyer buyer);

    public Buyer[] cleanBuyersNull(Buyer[] buyers);

    public Seller[] cleanSellersNull(Seller[] sellers);

    public String checkStringInput(String str);

    public String checkNoNumInput(String str);

    public String checkOnlyNumInput(String str);

    public String checkNotOnlyNumInput(String str);

    public String checkCategoryInput(String str);

    public String checkBooleanInput(String str);

    public String checkRangeInput(String currCartId, int length);

    public String checkMenuInput(String stringChoice);

}
