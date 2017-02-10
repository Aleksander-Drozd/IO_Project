package Util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TreeTableFormatFactory {

    private StringProperty labelProperty;
    private StringProperty descriptionProperty;
    private Object connectedObject;

    public TreeTableFormatFactory(String label, String description, Object connectedObject) {
        labelProperty = new SimpleStringProperty(label);
        descriptionProperty = new SimpleStringProperty(description);

        this.connectedObject = connectedObject;
    }

    public StringProperty getLabelProperty() {
        return labelProperty;
    }

    public StringProperty getDescriptionProperty() {
        return descriptionProperty;
    }

    public Object getConnectedObject() {
        return connectedObject;
    }

}
