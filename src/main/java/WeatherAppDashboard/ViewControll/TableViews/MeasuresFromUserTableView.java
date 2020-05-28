package WeatherAppDashboard.ViewControll.TableViews;

import WeatherAppDashboard.DbFinalObjects.MeasureFromUser;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MeasuresFromUserTableView {
    public MeasuresFromUserTableView(TableView<MeasureFromUser>measuresFromUserTableView , TableColumn<MeasureFromUser,Integer> ID,  TableColumn<MeasureFromUser,Integer> IDUSER, TableColumn<MeasureFromUser,Integer> IDUSERSTATION, TableColumn<MeasureFromUser,String> date, TableColumn<MeasureFromUser,Object> temp, TableColumn<MeasureFromUser,Object> windspeed,TableColumn<MeasureFromUser,Object> humidity,TableColumn<MeasureFromUser,String> cloudiness,TableColumn<MeasureFromUser,Object> pressure,TableColumn<MeasureFromUser,String>mfuStationNameColumn,TableColumn<MeasureFromUser,String>mfuEmailColumn,ArrayList<MeasureFromUser>measuresFromUsersFromRepositoryArrayList) {
       measuresFromUserTableView.getItems().clear();
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        IDUSER.setCellValueFactory(new PropertyValueFactory<>("IDUSER"));
        IDUSERSTATION.setCellValueFactory(new PropertyValueFactory<>("IDUSERSTATION"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        temp.setCellValueFactory(new PropertyValueFactory<>("temp"));
        windspeed.setCellValueFactory(new PropertyValueFactory<>("windspeed"));
        humidity.setCellValueFactory(new PropertyValueFactory<>("humidity"));
        cloudiness.setCellValueFactory(new PropertyValueFactory<>("cloudiness"));
        pressure.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        mfuEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        mfuStationNameColumn.setCellValueFactory(new PropertyValueFactory<>("stationName"));
        measuresFromUserTableView.getItems().addAll(measuresFromUsersFromRepositoryArrayList);

}
}
