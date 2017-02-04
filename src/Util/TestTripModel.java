package Util;

import Model.TripModel;
import POJO.Trip;
import fit.ColumnFixture;

public class TestTripModel extends ColumnFixture{
    int numOfElements;

    TripModel tripModel = new TripModel();

    public int verifyTestTripModel(){
        for (int i = 0; i < numOfElements; i++) {
            tripModel.addTrip(new Trip());
        }

        return tripModel.getTrips().size();
    }
}
