package Controller;

import Model.TripModel;
import POJO.Trip;
import Util.TreeTableFormatFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class TripsController implements Initializable {

    @FXML
    private TreeTableColumn<TreeTableFormatFactory,String> tripLabel;
    @FXML
    private TreeTableColumn<TreeTableFormatFactory,String> tripContent;
    @FXML
    private TreeTableView<TreeTableFormatFactory> tripsTree;

    private TripModel tripModel;

    public TripsController() {
        tripModel = new TripModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Trip> trips = tripModel.getTrips();
        TreeItem<TreeTableFormatFactory> root = new TreeItem<>(new TreeTableFormatFactory("","", null));

        for (Trip trip: trips) {
            root.getChildren().add(generateTreeItem(trip));
        }

        tripLabel.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableFormatFactory, String> parameter)
                                                -> parameter.getValue().getValue().getLabelProperty());
        tripContent.setCellValueFactory((TreeTableColumn.CellDataFeatures<TreeTableFormatFactory, String> parameter)
                                                -> parameter.getValue().getValue().getDescriptionProperty());

        tripsTree.setShowRoot(false);
        tripsTree.setRoot(root);
    }

    private TreeItem<TreeTableFormatFactory> generateTreeItem(Trip trip) {
        TreeItem<TreeTableFormatFactory> root = new TreeItem<>(new TreeTableFormatFactory(trip.getTitle(), "", trip));
        root.getChildren().add(new TreeItem<>(new TreeTableFormatFactory("Opis", trip.getDescription(), trip)));
        root.getChildren().add(new TreeItem<>(new TreeTableFormatFactory("Ilosc dni", Integer.toString(trip.getDays()), trip)));
        root.getChildren().add(new TreeItem<>(new TreeTableFormatFactory("Cena", Float.toString(trip.getPrice()), trip)));
        root.getChildren().add(new TreeItem<>(new TreeTableFormatFactory("Data", trip.getDate().toString(), trip)));
        return root;
    }

    @FXML
    private void handleButtonAddTrip() {
        showTripView(new Trip());
    }

    @FXML
    private void handleButtonEditTrip() {
        TreeItem<TreeTableFormatFactory> selectedItem = tripsTree.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            showTripView((Trip)selectedItem.getValue().getConnectedObject());
        }
    }

    private void showTripView(Trip trip) {
        Stage dataTripStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/TripView.fxml"));

            BorderPane layout = loader.load();

            Scene scene = new Scene(layout);
            TripController tripController = loader.getController();

            dataTripStage.setScene(scene);
            dataTripStage.initModality(Modality.APPLICATION_MODAL);
            dataTripStage.initOwner(tripsTree.getScene().getWindow());
            dataTripStage.setTitle("Wycieczka");

            tripController.setTrip(trip);

            dataTripStage.showAndWait();

            if ( tripController.isDataToSave() ) {
                saveTrip( tripController.getTrip() );
            } else {
                System.out.println( "Nie zapisano!" );
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveTrip( Trip trip ) {
        tripModel.saveTrip(trip);
        updateTripInTable(trip);
    }

    private void updateTripInTable(Trip trip) {
        TreeItem<TreeTableFormatFactory> newTreeItem = generateTreeItem(trip);
        ObservableList<TreeItem<TreeTableFormatFactory>> rootItemChildren = tripsTree.getRoot().getChildren();

        for (TreeItem<TreeTableFormatFactory> item: rootItemChildren) {
            if ( item.getValue().getConnectedObject() == trip ) {
                rootItemChildren.remove(item);
                break;
            }
        }

        rootItemChildren.add(newTreeItem);
        rootItemChildren.sort( Comparator.comparing(item -> item.getValue().getLabelProperty().getValue()) );
        tripsTree.refresh();
    }

}
