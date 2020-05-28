package WeatherAppDashboard.ViewControll.Finders;

import WeatherAppDashboard.DbFinalObjects.Station;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import net.aksingh.owmjapis.api.APIException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class StationsFinder{
   private ArrayList<Station>findedStationsList=new ArrayList<>();
   private ArrayList<Station>stationsFromRepository;
   private TextField IDTextField;
   private TextField StationNameTextField;
   private TableView owmStationsTableView;
   private Label howManyStations;


    public StationsFinder(ArrayList<Station> stationsFromRepository, TextField IDTextField, TextField stationNameTextField, TableView owmStationsTableView, Label howManyStations, TextArea consoleTextArea) {
        this.stationsFromRepository = stationsFromRepository;
        this.IDTextField = IDTextField;
        this.StationNameTextField = stationNameTextField;
        this.owmStationsTableView = owmStationsTableView;
        this.howManyStations = howManyStations;
    }

    public void findStationByID(){
        owmStationsTableView.getItems().clear();
        findedStationsList.clear();
        if(!IDTextField.getText().equals("")) {
            for (int i = 0; i < stationsFromRepository.size(); i++) {
                if (String.valueOf(stationsFromRepository.get(i).getId()).startsWith(IDTextField.getText())) {
                    findedStationsList.add(stationsFromRepository.get(i));
                }
            }
            if (findedStationsList.size() < 1) {
                owmStationsTableView.getItems().addAll(stationsFromRepository);
                howManyStations.setText("Ilość znlezionych stacji: " + findedStationsList.size());
                IDTextField.setStyle("-fx-text-fill:red;");
            }
            else{
                owmStationsTableView.getItems().addAll(findedStationsList);
                howManyStations.setText("Ilość znlezionych stacji: " + findedStationsList.size());
                IDTextField.setStyle("-fx-text-fill:green;");
            }
        }
        else{
            owmStationsTableView.getItems().addAll(stationsFromRepository);
            howManyStations.setText("Ilość znlezionych stacji: " + stationsFromRepository.size());
            IDTextField.setStyle("-fx-text-fill:red; ");
        }

    }

    public void findStationByName() throws APIException, ParseException, IOException {
        owmStationsTableView.getItems().clear();
        findedStationsList.clear();
        boolean finded=false;
        String input = StationNameTextField.getText().toLowerCase();
        String finalInput = "";
        for (int i = 0; i < input.length(); i++) {
            if (i == 0) {
                finalInput += input.charAt(i);
                finalInput = finalInput.toUpperCase();
            }
            if (i > 0) {
                try {
                    if (input.charAt(i) == 32 ) { //SPACJA
                        String bigletter = " " + input.substring(i + 1, i + 2).toUpperCase();
                        finalInput += bigletter;
                        i++;
                    } else {
                        finalInput += input.charAt(i);
                    }
                    if (input.charAt(i) == '-' ) {
                        String bigletter = input.substring(i + 1, i + 2).toUpperCase();
                        finalInput += bigletter;
                        i++;
                    }
                }
                catch (StringIndexOutOfBoundsException e){
                }
            }
        }
        if(finalInput.length()>0) {
            for (int i = 0; i < stationsFromRepository.size(); i++) {
                if (stationsFromRepository.get(i).getName().startsWith(finalInput)) {
                    finded = true;
                    findedStationsList.add(stationsFromRepository.get(i));
                }
            }
            if (finded == true) {
                StationNameTextField.setStyle("-fx-text-fill:green;");
                howManyStations.setText("Ilość znlezionych stacji: " + findedStationsList.size());
                owmStationsTableView.getItems().clear();
                owmStationsTableView.getItems().addAll(findedStationsList);
                owmStationsTableView.getSelectionModel().selectFirst();
            } else if (finded == false) {
                StationNameTextField.setStyle("-fx-text-fill:red; ");
                howManyStations.setText("Ilość znlezionych stacji: " + findedStationsList.size());
                owmStationsTableView.getItems().removeAll();
                owmStationsTableView.getItems().clear();
                owmStationsTableView.getItems().addAll(stationsFromRepository);
            }
        }
        else
        {
            StationNameTextField.setStyle("-fx-text-fill:red; ");
            howManyStations.setText("Ilość znlezionych stacji: " + stationsFromRepository.size());
           owmStationsTableView.getItems().removeAll();
            owmStationsTableView.getItems().clear();
            owmStationsTableView.getItems().addAll(stationsFromRepository);

        }
    }
    public void searchByNameOnWriteInTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                try {
                    findStationByName();
                } catch (APIException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void searchByIDOnWriteInTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                    findStationByID();
            }
        });
    }
}
