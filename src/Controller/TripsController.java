package Controller;

import Model.TripModel;
import POJO.Trip;
import Util.TreeTableFormatFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class TripsController implements Initializable {

    @FXML
    private TreeTableColumn<TreeTableFormatFactory,String> tripLabel;
    @FXML
    private TreeTableColumn<TreeTableFormatFactory,String> tripContent;
    @FXML
    private TreeTableView<TreeTableFormatFactory> tripsTree;

    private TripController tripController;
    private TripModel tripModel;
    private ObservableList<Trip> trips;

    public TripsController() {
        tripModel = new TripModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trips = tripModel.getTrips();
        TreeItem<TreeTableFormatFactory> root = new TreeItem<>(new TreeTableFormatFactory("","", null));

        for (Trip trip: trips) {
            TreeItem<TreeTableFormatFactory> description = new TreeItem<>(new TreeTableFormatFactory("Opis", trip.getDescription(), trip)),
                                            days = new TreeItem<>(new TreeTableFormatFactory("Ilosc dni", Integer.toString(trip.getDays()), trip)),
                                            price = new TreeItem<>(new TreeTableFormatFactory("Cena", Float.toString(trip.getPrice()), trip)),
                                            date = new TreeItem<>(new TreeTableFormatFactory("Data", trip.getDate().toString(), trip)),
                                            title = new TreeItem<>(new TreeTableFormatFactory(trip.getTitle(), "", trip));

            title.getChildren().setAll(description, days, price, date);

            root.getChildren().add(title);
        }

        tripLabel.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableFormatFactory, String> parameter) -> parameter.getValue().getValue().getLabelProperty());
        tripContent.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableFormatFactory, String> parameter) -> parameter.getValue().getValue().getDescriptionProperty());

        tripsTree.setShowRoot(false);
        tripsTree.setRoot(root);
    }

    @FXML
    private void handleButtonAddTrip() {
        System.out.println("Add!");
    }

    @FXML
    private void handleButtonEditTrip() {
        System.out.println("Edit!");
    }

}
