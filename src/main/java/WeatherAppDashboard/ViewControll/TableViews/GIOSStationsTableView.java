package WeatherAppDashboard.ViewControll.TableViews;

import WeatherAppDashboard.DbFinalObjects.Station;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class GIOSStationsTableView {
    public GIOSStationsTableView(TableView<Station>giosStationsTableView , TableColumn<Station,Integer> IDSTATION, TableColumn<Station,String> NAME, TableColumn <Station,Double>LON, TableColumn<Station,Double> LAT, ArrayList<Station> giosStationsFromRepositoryArrayList) {
        giosStationsTableView.getItems().clear();
        IDSTATION.setCellValueFactory(new PropertyValueFactory<>("id"));
        NAME.setCellValueFactory(new PropertyValueFactory<>("name"));
        LON.setCellValueFactory(new PropertyValueFactory<>("lon"));
        LAT.setCellValueFactory(new PropertyValueFactory<>("lat"));
        giosStationsTableView.getItems().addAll(giosStationsFromRepositoryArrayList);
    }
}
