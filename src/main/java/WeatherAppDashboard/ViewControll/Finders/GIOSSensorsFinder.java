package WeatherAppDashboard.ViewControll.Finders;

import WeatherAppDashboard.DbFinalObjects.GiosSensor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class GIOSSensorsFinder {
   private ArrayList<GiosSensor> findedSensorsList =new ArrayList<>();
   private ArrayList<GiosSensor> giosSensorsFromRepository;
   private TextField IDGIOSSensorTextField;
   private TextField IDGIOSStationTextField;
   private TableView giosSensorsTableView;
   private Label howManySensors;


    public GIOSSensorsFinder(ArrayList<GiosSensor> GiosSensorsFromRepository, TextField IDGIOSSensorTextField, TextField IDGIOSStationTextField, TableView<GiosSensor> giosSensorsTableView, Label howManySensors, TextArea consoleTextArea) {
        this.giosSensorsFromRepository = GiosSensorsFromRepository;
        this.IDGIOSSensorTextField = IDGIOSSensorTextField;
        this.IDGIOSStationTextField = IDGIOSStationTextField;
        this.giosSensorsTableView = giosSensorsTableView;
        this.howManySensors = howManySensors;
    }
    private void findSensorByGIOSSensorID(){
        giosSensorsTableView.getItems().clear();
        findedSensorsList.clear();
        if(!IDGIOSSensorTextField.getText().equals("")) {
            for (int i = 0; i < giosSensorsFromRepository.size(); i++) {
                if (String.valueOf(giosSensorsFromRepository.get(i).getIDSensor()).startsWith(IDGIOSSensorTextField.getText())) {
                    findedSensorsList.add(giosSensorsFromRepository.get(i));
                }
            }
            if (findedSensorsList.size() < 1) {
                giosSensorsTableView.getItems().addAll(giosSensorsFromRepository);
                howManySensors.setText("Ilość znlezionych stacji: " + findedSensorsList.size());
                IDGIOSSensorTextField.setStyle("-fx-text-fill:red;");
            }
            else {
                giosSensorsTableView.getItems().addAll(findedSensorsList);
                howManySensors.setText("Ilość znlezionych stacji: " + findedSensorsList.size());
                IDGIOSSensorTextField.setStyle("-fx-text-fill:green;");
            }
        }
        else{
            giosSensorsTableView.getItems().addAll(giosSensorsFromRepository);
            howManySensors.setText("Ilość znlezionych stacji: " + giosSensorsFromRepository.size());
            IDGIOSSensorTextField.setStyle("-fx-text-fill:red; ");
        }

    }
    private void findSensorByGIOSStationID(){
        giosSensorsTableView.getItems().clear();
        findedSensorsList.clear();
        if(!IDGIOSStationTextField.getText().equals("")) {
            for (int i = 0; i < giosSensorsFromRepository.size(); i++) {
                if (String.valueOf(giosSensorsFromRepository.get(i).getIDStation()).startsWith(IDGIOSStationTextField.getText())) {
                    findedSensorsList.add(giosSensorsFromRepository.get(i));
                }
            }
            if (findedSensorsList.size() < 1) {
                giosSensorsTableView.getItems().addAll(giosSensorsFromRepository);
                howManySensors.setText("Ilość znlezionych stacji: " + findedSensorsList.size());
                IDGIOSStationTextField.setStyle("-fx-text-fill:red;");
            }
            else{
                giosSensorsTableView.getItems().addAll(findedSensorsList);
                howManySensors.setText("Ilość znlezionych stacji: " + findedSensorsList.size());
                IDGIOSStationTextField.setStyle("-fx-text-fill:green;");
            }
        }
        else{
            giosSensorsTableView.getItems().addAll(giosSensorsFromRepository);
            howManySensors.setText("Ilość znlezionych stacji: " + giosSensorsFromRepository.size());
            IDGIOSStationTextField.setStyle("-fx-text-fill:red; ");
        }

    }
    public void searchByIDGIOSSensor(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                    findSensorByGIOSSensorID();
            }
        });
    }
    public void searchByIDGIOSStation(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                findSensorByGIOSStationID();
            }
        });
    }
}
