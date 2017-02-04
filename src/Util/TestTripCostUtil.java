package Util;

import fit.ColumnFixture;

public class TestTripCostUtil extends ColumnFixture{
    float price;
    int quantity;

    public float getFinalCost(){
        return SetUp.tripCostUtil.getFinalCost(price, quantity);
    }
}
