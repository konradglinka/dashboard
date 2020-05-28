package WeatherAppDashboard.ViewControll.TableViews;

import WeatherAppDashboard.DbFinalObjects.OwmTranslate;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class OWMTranslatorTableView {
    public OWMTranslatorTableView(TableView<OwmTranslate>owmTranslatorTableView , TableColumn<OwmTranslate,Integer> ID, TableColumn<OwmTranslate,String> PL, TableColumn<OwmTranslate,String> ENG, ArrayList<OwmTranslate>owmTranslatesFromRepositoryArrayList) {
        owmTranslatorTableView.getItems().clear();
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PL.setCellValueFactory(new PropertyValueFactory<>("PL"));
        ENG.setCellValueFactory(new PropertyValueFactory<>("ENG"));
        owmTranslatorTableView.getItems().addAll(owmTranslatesFromRepositoryArrayList);
    }
}
