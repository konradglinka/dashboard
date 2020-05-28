package WeatherAppDashboard.ViewControll.Finders;

import WeatherAppDashboard.DbFinalObjects.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.aksingh.owmjapis.api.APIException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class UsersFinder {
    private ArrayList<User>findedUsersList=new ArrayList<>();
    private ArrayList<User> usersFromRepository;
   private TextField IDTextField;
   private TextField emailTextField;
   private TableView tableView;
   private Label howManyUsers;


    public UsersFinder(ArrayList<User> usersFromRepository, TextField IDTextField, TextField emailTextField, TableView tableView, Label howManyUsers) {
        this.usersFromRepository = usersFromRepository;
        this.IDTextField = IDTextField;
        this.emailTextField = emailTextField;
        this.tableView = tableView;
        this.howManyUsers = howManyUsers;
    }

    public void findUserByID(){
        tableView.getItems().clear();
        findedUsersList.clear();
        if(!IDTextField.getText().equals("")) {
            for (int i = 0; i < usersFromRepository.size(); i++) {
                if (String.valueOf(usersFromRepository.get(i).getId()).startsWith(IDTextField.getText())) {
                    findedUsersList.add(usersFromRepository.get(i));
                }
            }
            tableView.getItems().addAll(findedUsersList);
            howManyUsers.setText("Ilość znlezionych użytkowników: " + findedUsersList.size());
            IDTextField.setStyle("-fx-text-fill:green;");
            if (findedUsersList.size() < 1) {
                tableView.getItems().addAll(usersFromRepository);
                howManyUsers.setText("Ilość znlezionych użytkowników: " + findedUsersList.size());
                IDTextField.setStyle("-fx-text-fill:red;");
            }
        }
        else{
            tableView.getItems().addAll(usersFromRepository);
            howManyUsers.setText("Ilość użytkowników: " + usersFromRepository.size());
            IDTextField.setStyle("-fx-text-fill:red; ");
        }

    }

    public void findUserByEmail() throws APIException, ParseException, IOException {
        tableView.getItems().clear();
        findedUsersList.clear();
        boolean finded=false;
        String input = emailTextField.getText();
        if(input.length()>0) {
            for (int i = 0; i < usersFromRepository.size(); i++) {
                if (usersFromRepository.get(i).getEmail().startsWith(input)) {
                    finded = true;
                    findedUsersList.add(usersFromRepository.get(i));
                }
            }
            if (finded == true) {
                emailTextField.setStyle("-fx-text-fill:green;");
                howManyUsers.setText("Ilość znalezionych użytkowników:"+findedUsersList.size());
                tableView.getItems().clear();
                tableView.getItems().addAll(findedUsersList);

            } else if (finded == false) {
                emailTextField.setStyle("-fx-text-fill:red; ");
                howManyUsers.setText("Ilość znalezionych użytkowników: "+findedUsersList.size());
                tableView.getItems().removeAll();
                tableView.getItems().clear();
                tableView.getItems().addAll(usersFromRepository);

            }
        }
        else
        {
            emailTextField.setStyle("-fx-text-fill:red; ");
            howManyUsers.setText("Ilość użytkowników: "+usersFromRepository.size());
            tableView.getItems().removeAll();
            tableView.getItems().clear();
            tableView.getItems().addAll(usersFromRepository);

        }
    }
    public void searchByEmail(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                try {
                   findUserByEmail();
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
    public void searchByID(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                    findUserByID();
            }
        });
    }
}
