package WeatherAppDashboard.ViewControll.TableViews;

import WeatherAppDashboard.DbFinalObjects.GiosSensor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class GIOSSensorsTableView {
    public GIOSSensorsTableView(TableView<GiosSensor>giosStationsTableView , TableColumn<GiosSensor,Integer> IDGIOSSENSOR, TableColumn<GiosSensor,Integer> IDGIOSSTATION, TableColumn <GiosSensor,String>NAME, TableColumn<GiosSensor,String> SHORTNAME,    TableColumn<GiosSensor,String>GIOSSensorsStationNameColumn, ArrayList<GiosSensor> giosStationsFromRepositoryArrayList) {
        giosStationsTableView.getItems().clear();
        IDGIOSSTATION.setCellValueFactory(new PropertyValueFactory<>("IDStation"));
        IDGIOSSENSOR.setCellValueFactory(new PropertyValueFactory<>("IDSensor"));
        NAME.setCellValueFactory(new PropertyValueFactory<>("nameOfSensor"));
        SHORTNAME.setCellValueFactory(new PropertyValueFactory<>("shortNameOfSensor"));
        GIOSSensorsStationNameColumn.setCellValueFactory(new PropertyValueFactory<>("stationName"));
        giosStationsTableView.getItems().addAll(giosStationsFromRepositoryArrayList);
    }





}
