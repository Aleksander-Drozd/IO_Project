package Util;

import POJO.Sale;

public class TripCostUtil {

    public static float getFinalCost(float price, int quantity) {
        return price * quantity;
    }

    public static float getFinalCost(Sale sale) {
        Float cost = 0f;

        try {
             cost = sale.getTrip().getPrice() * sale.getQuantity();
        } catch ( Exception e ) {

        }

        return Math.round(cost * 100.0) / 100.0f;
    }

}
