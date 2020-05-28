package WeatherAppDashboard.ViewControll.TableViews;

import WeatherAppDashboard.DbFinalObjects.DustyPlant;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class DustyPlantsTableView {
    public DustyPlantsTableView(TableView<DustyPlant>dustyPlantsTableView , TableColumn<DustyPlant,Integer> IDDUSTYPLANT, TableColumn<DustyPlant,String> NAME, TableColumn <DustyPlant,Integer>STARTDUSTMONTH, TableColumn<DustyPlant,Integer> ENDDUSTMONTH, TableColumn<DustyPlant,Integer> STARTDUSTDAY, TableColumn<DustyPlant,Integer> ENDDUSTDAY, ArrayList<DustyPlant>dustyPlantsFromRepositoryArrayList) {
        dustyPlantsTableView.getItems().clear();
        IDDUSTYPLANT.setCellValueFactory(new PropertyValueFactory<>("id"));
        NAME.setCellValueFactory(new PropertyValueFactory<>("name"));
        STARTDUSTMONTH.setCellValueFactory(new PropertyValueFactory<>("startDustMonth"));
        ENDDUSTMONTH.setCellValueFactory(new PropertyValueFactory<>("endDustMonth"));
        STARTDUSTDAY.setCellValueFactory(new PropertyValueFactory<>("startDustDay"));
        ENDDUSTDAY.setCellValueFactory(new PropertyValueFactory<>("endDustDay"));
        dustyPlantsTableView.getItems().addAll(dustyPlantsFromRepositoryArrayList);
    }





}
