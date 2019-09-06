package fr.wcs.wcstravel;


public class AirportModel {

    String label;
    String value;

    public AirportModel(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public AirportModel() {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
