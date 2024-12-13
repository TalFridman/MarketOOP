package TalFridman_RotemBar;

import java.util.Arrays;
import java.util.Scanner;

/* Presenters name : Tal Fridman (ID-207859323), Rotem Bar (ID-208416529) */
/*Lecturer : Eyal Aizenshtein*/

public class Main {


    public static void main(String[] args) {
        Market m = new Market();
        try (Scanner s = new Scanner(System.in)) {
            menu(s, m);
        }


    }

    public static void menu(Scanner s, Market m) {
        String choice = null;
        do {
            do {

                System.out.println("""
                    Please select your action:\

                    0 - Exit\

                    1 - Add seller\

                    2 - Add buyer\

                    3 - Add product to seller\

                    4 - Add product to buyer\

                    5 - Who you would like to pay for\

                    6 - Buyers info\

                    7 - Sellers info\
                    
                    8 - Show all the products from specific category\
                    
                    9 - Choose cart from history carts""");

                choice = s.nextLine();
                if (m.checkMenuInput(choice) != null){
                    System.out.println(m.checkMenuInput(choice));
                }
            }while (m.checkMenuInput(choice) != null);
            int intChoice = Integer.valueOf(choice);
            if (intChoice == 1) {
                choice1(s, m);
            } else if (intChoice == 2) {
                choice2(s, m);

            } else if (intChoice == 3) {
                choice3(s, m);

            } else if (intChoice == 4) {
                choice4(s, m);

            } else if (intChoice == 5) {
                choice5(s, m);

            } else if (intChoice == 6) {
                choice6(m);

            } else if (intChoice == 7) {
                choice7(m);
            }
            else if (intChoice == 8) {
                choice8(s, m);
            } else if (intChoice == 9) {
                choice9(s, m);
            }

        } while (Integer.valueOf(choice) > 0 && Integer.valueOf(choice) < 10);
        System.out.println("Have a nice day!");
    }

    private static void choice9(Scanner s, Market m) {
        String currBuyerName = null;
        String choice = null;
        boolean isChoice = true;
        String currCartId = null;
        do {
            System.out.println("Which buyer you want to change current cart: ");
            showAllBuyers(m.getBuyers());
             currBuyerName = s.nextLine();
             if (!m.checkBuyersDouble(m.getBuyers(),currBuyerName)){
                 System.out.println("Buyer name does not exists");
             }
        }while(!m.checkBuyersDouble(m.getBuyers(),currBuyerName));
        Buyer currBuyer = m.getBuyerByName(currBuyerName);
        if (currBuyer.getBuyerCurrentCart().getNumOfProducts() != 0) {
            do {
                System.out.println("would you like to replace current cart with one from history? (Y/N)");
                choice = s.nextLine();
                if(m.checkBooleanInput(choice) != null){
                    System.out.println(m.checkBooleanInput(choice));
                }
            } while (m.checkBooleanInput(choice) != null);
            if (choice.equalsIgnoreCase("n")) {
                isChoice = false;
            }
        }
        if (isChoice) {
            do {
                    if (currBuyer.getBuyerHistorySize() == 0){
                        System.out.println("there are no carts in "+currBuyerName+ "'s history\n");
                        break;
                    }
                    do {
                        System.out.println("Which cart would you like to choose as current cart: (by ID) ");
                        System.out.print(currBuyer.toHistoryCartsString());
                        currCartId = s.nextLine();
                        if(m.checkRangeInput(currCartId,currBuyer.getBuyerHistorySize()) != null){
                            System.out.println(m.checkRangeInput(currCartId,currBuyer.getBuyerHistorySize()));
                        }
                    }while(m.checkRangeInput(currCartId,currBuyer.getBuyerHistorySize()) != null);
                    if (m.replaceCurrCart(currCartId, currBuyer)) {
                        System.out.println("Your current cart has been replaced!\n");
                    }
                } while (!m.replaceCurrCart(currCartId,currBuyer));

            }

    }

    private static void choice8(Scanner s, Market m) {
        String productCategory =null;
        Category category = null;
        do {
            System.out.println("Which category you would like to see its products? (Kids/Electricity/Office/Clothing)");
             productCategory = s.nextLine().toUpperCase();
            if(m.checkCategoryInput(productCategory) != null){
                System.out.println(m.checkCategoryInput(productCategory));
            }
        }while(m.checkCategoryInput(productCategory) != null);
        category = Category.valueOf(productCategory);
        showAllProductsFromCategory(m, category);
    }

    private static void choice7(Market m) {
        Seller[] sellers = m.getSellers();
        Seller[] newSellers;
        newSellers = m.cleanSellersNull(sellers);
        Arrays.sort(newSellers, new CompareUserByNumOfProducts());
        if (newSellers.length == 0) {
            System.out.println("There is no info to display yet.");
        }
        for (int i = 0; i < newSellers.length; i++) {
            if (newSellers[i] == null) {
                break;
            }
            Seller currSeller = newSellers[i];
            System.out.println((i + 1) + " - " + currSeller.toString());
       }
    }

    private static void choice6(Market m) {
        Buyer[] buyers = m.getBuyers();
        Buyer[] newBuyers;
        newBuyers = m.cleanBuyersNull(buyers);
        Arrays.sort(newBuyers, new CompareUserByName());
        if (newBuyers.length == 0) {
            System.out.println("There is no info to display yet.");
        }
        for (int i = 0; i < newBuyers.length; i++) {
            if (newBuyers[i] == null) {
                break;
            }
            Buyer currBuyer = newBuyers[i];
            System.out.println((i+1) + " - " + currBuyer.toString());

        }
    }

    private static void choice5(Scanner s, Market m) {
        String selectedBuyer = null;
        if (m.getNumOfBuyers() == 0) {
            System.out.println("There are no Buyers yet.");
            return;
        }
        do {
            System.out.println("Please type the buyer's name you would like to pay for his cart: ");
            showAllBuyers(m.getBuyers());
            selectedBuyer = s.nextLine();
            if (!m.checkBuyersDouble(m.getBuyers(), selectedBuyer)) {
                System.out.println("Buyer name does not exists");
            }
        }while (!m.checkBuyersDouble(m.getBuyers(),selectedBuyer));
        Buyer currBuyer = m.getBuyerByName(selectedBuyer);
        if (currBuyer.getBuyerCurrentCart().getNumOfProducts() == 0){
            System.out.println("There is no cart to pay for yet try adding products first.\n");
            return;
        }
        System.out.println("Order summary: ");
        System.out.println(currBuyer.getBuyerCurrentCart().toString());
        m.payForCart(currBuyer) ;
        System.out.println("Your cart has been paid successfully\n");

    }

    private static void choice4(Scanner s, Market m) {
        String selectedBuyer = null;
        String selectedSeller = null;
        String selectedProduct = null;
        if (m.getBuyers().length == 0) {
            System.out.println("There are no Buyers yet.");
            return;
        }
        do {
            System.out.println("Please type the buyer's name you would like to add the product to his cart: ");
            showAllBuyers(m.getBuyers());
             selectedBuyer = s.nextLine();
            if (!m.checkBuyersDouble(m.getBuyers(), selectedBuyer)) {
                System.out.println("Buyer name does not exists");
            }
        }while (!m.checkBuyersDouble(m.getBuyers(),selectedBuyer));
        Buyer currBuyer = m.getBuyerByName(selectedBuyer);
        do {
            System.out.println("Please type the seller's name you would like to buy from: ");
            showAllSellers(m.getSellers());
             selectedSeller = s.nextLine();
            if (!m.checkSellersDouble(m.getSellers(), selectedSeller)) {
                System.out.println("Seller name does not exists");
            }
        }while (!m.checkSellersDouble(m.getSellers(),selectedSeller));
        Seller currSeller = m.getSellerByName(selectedSeller);

        if(currSeller.getNumOfProducts() == 0){
            System.out.println("This seller dont have any Products yet.");
        }else {
            do {
                System.out.println("Please type the product's name you would like to buy: ");
                showSellerProducts(currSeller);
                selectedProduct = s.nextLine();
                if (m.getProductByName(selectedProduct, currSeller)==null){
                    System.out.println("This product is not on the list please provide a different one: ");
                }
            }while(m.getProductByName(selectedProduct, currSeller)==null);
            Product currProduct = m.getProductByName(selectedProduct+"", currSeller);
            m.addProductToCart(currBuyer, currProduct);
                System.out.println("You have successfully added the product to your cart.\n");
        }
    }

    private static void choice3(Scanner s, Market m) {
        String selectedSeller = null;
        String productName = null;
        String productPrice = null;
        Category category = null;
        String productCategory = null;
        String specialPacking = null;
        boolean isSpecialPacking = false;
        String specialPackingFee = null;
        double doubleFee = 0.0;

        if (m.getNumOfSellers() == 0) {
            System.out.println("There are no Sellers yet.");
            return;
        }
        do{
            System.out.println("Please type the seller you would like to add the product: ");
            showAllSellers(m.getSellers());
            selectedSeller  = s.nextLine();
            if (!m.checkSellersDouble(m.getSellers(), selectedSeller)) {
                System.out.println("seller name does not exists");
            }
        }while (!m.checkSellersDouble(m.getSellers(),selectedSeller));
        do{
            System.out.println("Please type the name of the product: ");
            productName = s.nextLine();
            if (m.checkNotOnlyNumInput(productName) != null){
                System.out.println(m.checkNotOnlyNumInput(productName));
            }
        }
        while(m.checkNotOnlyNumInput(productName) != null);
        do {
            System.out.println("Please type the price of the product: ");
             productPrice = s.nextLine();
            if(m.checkOnlyNumInput(productPrice) != null){
                System.out.println(m.checkOnlyNumInput(productPrice));
            }
        }while(m.checkOnlyNumInput(productPrice) != null);
        double doublePrice = Double.parseDouble(productPrice);
        do {
            System.out.println("Please type the category of the product: ");
            System.out.println("KIDS / OFFICE / ELECTRICITY / CLOTHING");
             productCategory = s.nextLine().toUpperCase();
            if(m.checkCategoryInput(productCategory) != null){
                System.out.println(m.checkCategoryInput(productCategory));
            }
        }while(m.checkCategoryInput(productCategory) != null);
        category = Category.valueOf(productCategory);
        do {
            System.out.println("Would you like to have a special packing? (Y/N)");
             specialPacking = s.nextLine();
            if(m.checkBooleanInput(specialPacking) != null){
                System.out.println(m.checkBooleanInput(specialPacking));
            }
        }while(m.checkBooleanInput(specialPacking) != null);
        if (specialPacking.equalsIgnoreCase("y")){
             isSpecialPacking = true;
        }
        if (isSpecialPacking) {
            do {
                System.out.println("Please type the special packing fee: ");
                specialPackingFee = s.nextLine();
                if(m.checkOnlyNumInput(specialPackingFee) != null){
                    System.out.println(m.checkOnlyNumInput(specialPackingFee));
                }
            }while(m.checkOnlyNumInput(specialPackingFee) != null);
             doubleFee = Double.parseDouble(specialPackingFee);
        }
        m.addProductToSeller(doublePrice, productName, selectedSeller, category, isSpecialPacking, doubleFee);
        System.out.println("The product has been added successfully.");
    }

    private static void choice2(Scanner s, Market m) {
        String buyerName = null;
        String buyerPassword = null;
        String buyerCountry = null;
        String buyerCity = null;
        String buyerStreet = null;
        String buyerHouseNum = null;
        do {
            if (m.checkBuyersDouble(m.getBuyers(), buyerName)) {
                System.out.println("Buyer exists");
            }
            System.out.println("Please enter the buyer's name");
            buyerName = s.nextLine();
            if(m.checkNotOnlyNumInput(buyerName) != null){
                System.out.println(m.checkNotOnlyNumInput(buyerName));
            }
        }while(m.checkNotOnlyNumInput(buyerName) != null || m.checkBuyersDouble(m.getBuyers(),buyerName));
        do {
            System.out.println("Please type your password: ");
             buyerPassword = s.nextLine();
             if(m.checkStringInput(buyerPassword) != null){
                 System.out.println(m.checkStringInput(buyerPassword));
             }
        }while (m.checkStringInput(buyerPassword) != null);
        do {
            System.out.println("Please type your country: ");
             buyerCountry = s.nextLine();
            if(m.checkNoNumInput(buyerCountry) != null){
                System.out.println(m.checkNoNumInput(buyerCountry));
            }
        }while(m.checkNoNumInput(buyerCountry) != null);
        do {
            System.out.println("Please type your city: ");
            buyerCity = s.nextLine();
            if(m.checkNoNumInput(buyerCity) != null){
                System.out.println(m.checkNoNumInput(buyerCity));
            }
        }while(m.checkNoNumInput(buyerCity) != null);
        do {
            System.out.println("Please type your street name: ");
             buyerStreet = s.nextLine();
            if(m.checkNoNumInput(buyerStreet) != null){
                System.out.println(m.checkNoNumInput(buyerStreet));
            }
        }while(m.checkNoNumInput(buyerStreet) != null);
        do {
            System.out.println("Please type your house number: ");
             buyerHouseNum = s.nextLine();
             if(m.checkOnlyNumInput(buyerHouseNum) != null){
                 System.out.println(m.checkOnlyNumInput(buyerHouseNum));
             }
        }while (m.checkOnlyNumInput(buyerHouseNum) != null);
        Address buyerAddress = new Address(buyerCountry, buyerCity, buyerStreet, buyerHouseNum);
        m.addBuyer(buyerName, buyerAddress, buyerPassword);
        System.out.println("The name has been added successfully.");
    }

    private static void choice1(Scanner s, Market m) {
        String sellerName = null;
        String sellerPassword;
        do {
            if(m.checkSellersDouble(m.getSellers(),sellerName)) {
                System.out.println("Seller exists");
            }
            System.out.println("Please enter the seller's name: ");
            sellerName = s.nextLine();
            if (m.checkNotOnlyNumInput(sellerName) != null){
                System.out.println(m.checkNotOnlyNumInput(sellerName));
            }
        }
        while(m.checkNotOnlyNumInput(sellerName) != null || m.checkSellersDouble(m.getSellers(),sellerName));
        do {
            System.out.println("Please enter your password: ");
            sellerPassword = s.nextLine();
            if(m.checkStringInput(sellerPassword) != null){
                System.out.println(m.checkStringInput(sellerPassword));
            }
        }while(m.checkStringInput(sellerPassword) != null);
        m.addSeller(sellerName, sellerPassword);
        System.out.println("The name has been added successfully.");
    }

    public static void showAllSellers(Seller[] currArrayS) {
        if (currArrayS.length == 0) {
            System.out.println("There is no info to display yet.");
        }
        for (int i = 0; i < currArrayS.length; i++) {
            if (currArrayS[i] == null) {
                break;
            }
            System.out.println((i + 1) + " - " + currArrayS[i].getName());
        }

    }

    public static void showAllBuyers(Buyer[] currArrayS) {
        if (currArrayS.length == 0) {
            System.out.println("There is no info to display yet.");
        }
        for (int i = 0; i < currArrayS.length; i++) {
            if (currArrayS[i] == null) {
                break;
            }
            Buyer currBuyer = currArrayS[i];
            System.out.println((i + 1) + " - " + currBuyer.getName());
        }

    }

    public static void showSellerProducts(Seller currSeller) {
        Product[] currProductArray = currSeller.getProducts();
        if (currProductArray.length == 0) {
            System.out.println("There is no info to display yet.");
        }
        for (int i = 0; i < currProductArray.length; i++) {
            if (currProductArray[i] == null) {
                break;
            }
            System.out.println((i + 1) + " - " + currProductArray[i].getProductName() + " - " + currProductArray[i].getPrice() + " $");
        }

    }

    public static void showAllProductsFromCategory(Market m ,Category category) {
        int productIndex = 1;
        for (int i = 0; i < m.getNumOfSellers(); i++) {
            Product[] currSellerProducts = m.getSellers()[i].getProducts();
            for(int j = 0; j < currSellerProducts.length; j++) {
                if(currSellerProducts[j] == null){
                    break;
                }
                Product currProduct = currSellerProducts[j];
                if (currProduct.getCategory() == category){
                    System.out.println((productIndex) + " - " + currProduct.getProductName() + " - " + currProduct.getPrice() + "$");
                    productIndex++;
                }

            }

        }
        if (productIndex == 1){
            System.out.println("There are no products in this category to display yet.\n");
        }else {
            System.out.println("");
        }

    }

}