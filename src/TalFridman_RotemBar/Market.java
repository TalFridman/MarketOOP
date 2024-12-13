package TalFridman_RotemBar;


import java.util.Date;

public class Market extends Exception implements IMarket{
    private Buyer[] buyers;
    private Seller[] sellers;
    private int numOfSellers;
    private int numOfBuyers;
    private int serialNumberCounter = 0;
    private int cartIdCounter = 0;
    private ExceptionsUtil exceptionsUtil;


    public Market() {
        this.buyers = new Buyer[0];
        this.sellers = new Seller[0];
        this.exceptionsUtil = new ExceptionsUtil();

    }


    public Buyer[] getBuyers() {
        return buyers;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public Buyer getBuyerByName(String name) {
        for (int i = 0; i < numOfBuyers; i++) {
            if (buyers[i].getName().equalsIgnoreCase(name)) {
                return buyers[i];
            }
        }
        return null;
    }

    public int getNumOfBuyers() {
        return numOfBuyers;
    }

    public Seller getSellerByName(String name) {
        for (int i = 0; i < numOfSellers; i++) {
            if (sellers[i].getName().equalsIgnoreCase(name)) {
                return sellers[i];
            }
        }
        return null;
    }

    public void addBuyer(String buyerName, Address address, String password) {
            buyers = addItemToBuyersArray(buyerName, buyers, address, password);
            numOfBuyers++;
    }

    public int getNumOfSellers() {
        return numOfSellers;
    }

    public void addSeller(String sellerName, String password) {
            sellers = addItemToSellersArray(sellerName.trim(), sellers, password);
            numOfSellers++;
    }

    public Buyer[] addItemToBuyersArray(String currString, Buyer[] currArray, Address address, String password) {
        if (currArray.length == 0) {
            currArray = expandBuyersArray(currArray);
        }
        if (currArray[currArray.length - 1] != null) {
            currArray = expandBuyersArray(currArray);


        }
        for (int i = 0; i <= currArray.length; i++) {
            if (currArray[i] == null) {
                currArray[i] = new Buyer(currString, address, password);
                break;
            }
        }
        return currArray;

    }

    public Seller[] addItemToSellersArray(String currString, Seller[] currArray, String currPassword) {
        if (currArray.length == 0) {
            currArray = expandSellersArray(currArray);
        }
        if (currArray[currArray.length - 1] != null) {
            currArray = expandSellersArray(currArray);


        }
        for (int i = 0; i <= currArray.length; i++) {
            if (currArray[i] == null) {
                currArray[i] = new Seller(currString, currPassword);
                break;
            }
        }
        return currArray;

    }

    public Buyer[] expandBuyersArray(Buyer[] currArrayE) {
        int newSize = currArrayE.length * 2;
        if (newSize == 0) {
            newSize += 1;
        }
        Buyer[] expandedArray = new Buyer[newSize];
        System.arraycopy(currArrayE, 0, expandedArray, 0, currArrayE.length);

        return expandedArray;
    }

    public Seller[] expandSellersArray(Seller[] currArrayE) {
        int newSize = currArrayE.length * 2;
        if (newSize == 0) {
            newSize += 1;
        }
        Seller[] expandedArray = new Seller[newSize];
        System.arraycopy(currArrayE, 0, expandedArray, 0, currArrayE.length);

        return expandedArray;
    }

    public Cart[] expandCartsArray(Cart[] currCartArray) {
        int newSize = currCartArray.length * 2;
        if (newSize == 0) {
            newSize += 1;
        }
        Cart[] expandedArray = new Cart[newSize];
        System.arraycopy(currCartArray, 0, expandedArray, 0, currCartArray.length);

        return expandedArray;
    }

    public Product[] expandProductsArray(Product[] currArrayE) {
        int newSize = currArrayE.length * 2;
        if (newSize == 0) {
            newSize += 1;
        }
        Product[] expandedArray = new Product[newSize];
        System.arraycopy(currArrayE, 0, expandedArray, 0, currArrayE.length);

        return expandedArray;
    }

    public boolean checkBuyersDouble(Buyer[] currArrayD, String currStringD) {

        for (int i = 0; i < currArrayD.length; i++) {
            if (currArrayD[i] == null) {
                return false;
            }
            if (currArrayD[i].getName().equalsIgnoreCase(currStringD)) {
                return true;
            }
        }
        return false;

    }

    public boolean checkSellersDouble(Seller[] currArrayD, String currStringD) {

        for (int i = 0; i < currArrayD.length; i++) {
            if (currArrayD[i] == null) {
                return false;
            }
            if (currArrayD[i].getName().equalsIgnoreCase(currStringD)) {
                return true;
            }
        }
        return false;
    }

    public Product getProductByName(String selectedProduct, Seller seller) {
        Product[] currProducts = seller.getProducts();
        for (int i = 0; i < currProducts.length; i++) {
            if (currProducts[i] == null) {
                return null;
            }
            if (currProducts[i].getProductName().equals(selectedProduct)) {
                return currProducts[i];
            }
        }
        return null;
    }

    public boolean addProductToSeller(double price, String productName, String seller, Category category, boolean isSpecialPacking, double specialPackingFee) {
        this.serialNumberCounter++;
        String serialNumber = this.serialNumberCounter + "";
        Product currProduct = new Product(price, productName, serialNumber, category, isSpecialPacking, specialPackingFee);
        if(isSpecialPacking){
            currProduct.setPrice(price + specialPackingFee);
        }
        Seller currSeller = getSellerByName(seller);
        if (currSeller == null) {
            return false;
        } else {
            Product[] currProducts = currSeller.getProducts();
            currSeller.setProducts(addProductToProducts(currProduct, currProducts));
            currSeller.setNumOfProducts(currSeller.getNumOfProducts() + 1);
            return true;
        }
    }

    public Product[] addProductToProducts(Product currProduct, Product[] currProducts) {
        if (currProducts.length == 0) {
            currProducts = expandProductsArray(currProducts);
        }
        if (currProducts[currProducts.length - 1] != null) {
            currProducts = expandProductsArray(currProducts);
        }
        for (int i = 0; i < currProducts.length; i++) {
            if (currProducts[i] == null) {
                currProducts[i] = currProduct;
                break;
            }
        }

        return currProducts;
    }

    public boolean addProductToCart(Buyer currBuyer, Product currProduct) {
        Cart currCart = currBuyer.getBuyerCurrentCart();
        if (currCart == null) {
            currCart = new Cart();
        }
        currBuyer.getBuyerCurrentCart().setProducts(addProductToProducts(currProduct, currCart.getProducts()));
        double totalPrice = 0;
        for (int i = 0; i < currCart.getNumOfProducts()+1; i++) {
            if (currCart.getProducts()[i] == null) {
                break;
            }
            totalPrice += currCart.getProducts()[i].getPrice();
        }
        currBuyer.getBuyerCurrentCart().setTotalPrice(totalPrice);
        currCart.setNumOfProducts(currCart.getNumOfProducts() + 1);
        return true;
    }

    public Cart[] addCartToCartsHistory(Cart currCart, Buyer currBuyer) {
        Cart[] cartsHistory = currBuyer.getBuyerCartHistory();
        if (cartsHistory.length == 0) {
            cartsHistory = expandCartsArray(cartsHistory);
        }

        if(currBuyer.getBuyerHistorySize() ==  cartsHistory.length){
            cartsHistory = expandCartsArray(cartsHistory);
        }


        for (int i = 0; i < cartsHistory.length; i++) {
            if (cartsHistory[i] == null) {
                cartsHistory[i] = currCart;
                break;
            }
        }
        currBuyer.setBuyerHistorySize(currBuyer.getBuyerHistorySize()+1);
        return cartsHistory;
    }

    public boolean payForCart(Buyer currBuyer) {
        Cart currCart = currBuyer.getBuyerCurrentCart();
        if (currCart.getNumOfProducts() == 0) {
            return false;
        }
        cartIdCounter++;
        currCart.setCartId(cartIdCounter + "");
        currCart.setDateOfPurchase(new Date());
        currBuyer.setBuyerCartHistory(addCartToCartsHistory(currCart, currBuyer));
        currBuyer.setBuyerCurrentCart(new Cart());
        return true;
    }

    public boolean replaceCurrCart(String id, Buyer buyer) {
        Cart[] currCartHistory = buyer.getBuyerCartHistory();
        for (int i = 0; i < currCartHistory.length; i++) {
            if (currCartHistory[i].getCartId().equalsIgnoreCase(id)) {
                Cart currCart = currCartHistory[i];
                Product[] currentProducts = currCart.getProducts();
                Product[] newProducts = new Product[currentProducts.length];
                for (int j = 0; j < currCart.getNumOfProducts(); j++) {
                    newProducts[j] = new Product(currentProducts[j]);

                }

                double totalPrice = currCart.getTotalPrice();

                Cart newCart = new Cart(newProducts,totalPrice,currCart.getNumOfProducts());

                buyer.setBuyerCurrentCart(newCart);

                return true;
            }
        }
        return false;
    }

    public Buyer[] cleanBuyersNull(Buyer[] buyers) {
        Buyer[] newBuyers = new Buyer[numOfBuyers];
        System.arraycopy(buyers, 0, newBuyers, 0, numOfBuyers);
        return newBuyers;
    }

    public Seller[] cleanSellersNull(Seller[] sellers) {
        Seller[] newSellers = new Seller[numOfSellers];
        System.arraycopy(sellers, 0, newSellers, 0, numOfSellers);
        return newSellers;
    }

    public String checkStringInput(String str){
        try {
            exceptionsUtil.checkStringInput(str);
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }

    public String checkNoNumInput(String str){
        try {
            exceptionsUtil.checkNoNumInput(str);
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }

    public String checkOnlyNumInput(String str){
        try {
            exceptionsUtil.checkOnlyNumInput(str);
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }

    public String checkNotOnlyNumInput(String str){
        try {
            exceptionsUtil.checkNotOnlyNumInput(str);
        }catch(IllegalArgumentException e){
            return e.getMessage();
        }
        return null;
    }

    public String checkCategoryInput(String str){
        try {
            exceptionsUtil.checkCategoryExists(str);
        } catch (IllegalArgumentException e) {
            return "invalid input";
        }
        return null;
    }

    public String checkBooleanInput(String str) {
        try {
            exceptionsUtil.checkBooleanInput(str);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return null;
    }

    public String checkRangeInput(String currCartId, int length) {
        try {
            exceptionsUtil.checkRangeInput(currCartId,length);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return null;
    }

    public String checkMenuInput(String stringChoice) {
        try {
            exceptionsUtil.checkMenuInput(stringChoice);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return null;
    }

}
