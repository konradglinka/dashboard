package WeatherAppDashboard.ViewControll.TableViews;

import WeatherAppDashboard.DbFinalObjects.User;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class UsersTableView {
    public UsersTableView(TableView<User>usersTableView , TableColumn<User,Integer> ID, TableColumn<User,String> email, TableColumn<User,String> password, ArrayList<User>usersFromRepositoryArrayList) {
        usersTableView.getItems().clear();
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        usersTableView.getItems().addAll(usersFromRepositoryArrayList);
    }
}
