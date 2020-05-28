package WeatherAppDashboard.ViewControll.Finders;

import WeatherAppDashboard.DbFinalObjects.MeasureFromUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MeasuresFromUsersFinder {
    private ArrayList<MeasureFromUser>findedMeasuresFromUsersList=new ArrayList<>();
    private ArrayList<MeasureFromUser> measureFromUsersFromRepository;
   private TextField IDUserTextField;
   private TextField IDUserStationTextField;
   private TableView tableView;
   private Label howManyMeasuresFromUsers;


    public MeasuresFromUsersFinder(ArrayList<MeasureFromUser> measureFromUsersFromRepository, TextField IDUserTextField, TextField IDUserStationTextField, TableView tableView, Label howManyMeasuresFromUsers) {
        this.measureFromUsersFromRepository = measureFromUsersFromRepository;
        this.IDUserTextField = IDUserTextField;
        this.IDUserStationTextField = IDUserStationTextField;
        this.tableView = tableView;
        this.howManyMeasuresFromUsers = howManyMeasuresFromUsers;
    }

    private void findMeasureFromUserByIDUser(){
        tableView.getItems().clear();
        findedMeasuresFromUsersList.clear();
        if(!IDUserTextField.getText().equals("")) {
            for (int i = 0; i < measureFromUsersFromRepository.size(); i++) {
                if (String.valueOf(measureFromUsersFromRepository.get(i).getIDUSER()).startsWith(IDUserTextField.getText())) {
                    findedMeasuresFromUsersList.add(measureFromUsersFromRepository.get(i));
                }
            }
            if (findedMeasuresFromUsersList.size() < 1) {
                tableView.getItems().addAll(measureFromUsersFromRepository);
                howManyMeasuresFromUsers.setText("Ilość znlezionych użytkowników: " + findedMeasuresFromUsersList.size());
                IDUserTextField.setStyle("-fx-text-fill:red;");
            }
            else{
                tableView.getItems().addAll(findedMeasuresFromUsersList);
                howManyMeasuresFromUsers.setText("Ilość znlezionych użytkowników: " + findedMeasuresFromUsersList.size());
                IDUserTextField.setStyle("-fx-text-fill:green;");
            }
        }
        else{
            tableView.getItems().addAll(measureFromUsersFromRepository);
            howManyMeasuresFromUsers.setText("Ilość użytkowników: " + measureFromUsersFromRepository.size());
            IDUserTextField.setStyle("-fx-text-fill:red; ");
        }

    }
    private void findMeasureFromUserByIDStation(){
        tableView.getItems().clear();
        findedMeasuresFromUsersList.clear();
        if(!IDUserStationTextField.getText().equals("")) {
            for (int i = 0; i < measureFromUsersFromRepository.size(); i++) {
                if (String.valueOf(measureFromUsersFromRepository.get(i).getIDUSERSTATION()).startsWith(IDUserStationTextField.getText())) {
                    findedMeasuresFromUsersList.add(measureFromUsersFromRepository.get(i));
                }
            }
            if (findedMeasuresFromUsersList.size() < 1) {
                tableView.getItems().addAll(measureFromUsersFromRepository);
                howManyMeasuresFromUsers.setText("Ilość znlezionych użytkowników: " + findedMeasuresFromUsersList.size());
                IDUserStationTextField.setStyle("-fx-text-fill:red;");
            }
            else{
                tableView.getItems().addAll(findedMeasuresFromUsersList);
                howManyMeasuresFromUsers.setText("Ilość znlezionych użytkowników: " + findedMeasuresFromUsersList.size());
                IDUserStationTextField.setStyle("-fx-text-fill:green;");
            }
        }
        else{
            tableView.getItems().addAll(measureFromUsersFromRepository);
            howManyMeasuresFromUsers.setText("Ilość użytkowników: " + measureFromUsersFromRepository.size());
            IDUserStationTextField.setStyle("-fx-text-fill:red; ");
        }

    }

    public void searchByIDUser(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                findMeasureFromUserByIDUser();
            }
        });
    }
    public void searchByIDUserStation(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                    findMeasureFromUserByIDStation();
            }
        });
    }
}
