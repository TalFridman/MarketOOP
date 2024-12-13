package TalFridman_RotemBar;

import java.util.Comparator;

public class CompareUserByNumOfProducts implements Comparator<Seller> {

    @Override
    public int compare(Seller o1, Seller o2) {
        return  o2.getProducts().length - o1.getProducts().length;
    }

}
