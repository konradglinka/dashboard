package WeatherAppDashboard.Actualizations;

import WeatherAppDashboard.DbComunication.JdbcQuery;
import WeatherAppDashboard.DbFinalObjects.GiosSensor;
import WeatherAppDashboard.DbFinalObjects.Station;
import WeatherAppDashboard.Repositories.FinalFromDB.GiosSensorsRepository;
import WeatherAppDashboard.Repositories.FinalFromDB.GiosStationsRepository;
import javafx.scene.control.*;
import org.json.JSONException;
import java.io.IOException;
import java.sql.SQLException;

public class ActualizationGios implements Runnable { //Klasa odpowiada za aktualizacje GIOS

    //Dostarczamy komponenty niezbędne do wykonania aktualizacji
    private JdbcQuery jdbcQuery; //Dostęp do bazy danych
    private TextArea consoleTextArea; //Konsola do wyświetlenia komunikatów
    private Button backToFullGiosStationsListButton; //Przycisk do wyświetlenia wszystkich stacji GIOS
    private Button backToFullGiosSensorsListButton; //Przycisk do wyświetlenia wszystkich sensorów GIOS
    private Button startActualizationGiosButton; //Przycisk rozpoczynający aktualizacje GIOS
    private Button removeSelectedGiosStationButton; //Przycisk do usuniecia wybranej stacji GIOS i jej sensorów
    private Button removeSelectedGiosSensorButton; //Przycisk do usunięcia wybranego sensora GIOS
    private Button refreshDataButton; //Przycisk odswieżajacy dane w aplikacji
    private TextField finderGiosStationByIdStationTextField; //Pole do wyszukiwania stacji GIOS po jej ID
    private TextField finderGiosStationByStationNameTextField; //Pole do wyszukiwania stacji GIOS po jej nazwie
    private TextField finderGiosSensorByIdStationTextField; //Pole do wyszukiwania sensora GIOS po ID jego stacji
    private TextField finderGiosSensorByIdSensorTextField; //Pole do wyszukiwania sensora GIOS po jego ID
    private TableView<Station> giosStationsTableView; //Tabela zawierajace dane o stacjach GIOS
    private TableView<GiosSensor> giosSensorsTableView; //Tabela zawierajace dane o sensorach GIOS
    private GiosStationsRepository giosStationsRepository; //Repozytorium przechowujące stacje GIOS pobrane z bazy danych
    private GiosSensorsRepository giosSensorsRepository;  //Repozytorium przechowujące sensory GIOS pobrane z bazy danych
    private Button actualizationOwmButton;

    public ActualizationGios(JdbcQuery jdbcQuery, TextArea consoleTextArea, Button backToFullGiosStationsListButton, Button backToFullGiosSensorsListButton, Button startActualizationGiosButton, Button removeSelectedGiosStationButton, Button removeSelectedGiosSensorButton, Button refreshDataButton, TextField finderGiosStationByIdStationTextField, TextField finderGiosStationByStationNameTextField, TextField finderGiosSensorByIdStationTextField, TextField finderGiosSensorByIdSensorTextField, TableView<Station> giosStationsTableView, TableView<GiosSensor> giosSensorsTableView, GiosStationsRepository giosStationsRepository, GiosSensorsRepository giosSensorsRepository, Button actualizationOwmButton) {
        this.jdbcQuery = jdbcQuery;
        this.consoleTextArea = consoleTextArea;
        this.backToFullGiosStationsListButton = backToFullGiosStationsListButton;
        this.backToFullGiosSensorsListButton = backToFullGiosSensorsListButton;
        this.startActualizationGiosButton = startActualizationGiosButton;
        this.removeSelectedGiosStationButton = removeSelectedGiosStationButton;
        this.removeSelectedGiosSensorButton = removeSelectedGiosSensorButton;
        this.refreshDataButton = refreshDataButton;
        this.finderGiosStationByIdStationTextField = finderGiosStationByIdStationTextField;
        this.finderGiosStationByStationNameTextField = finderGiosStationByStationNameTextField;
        this.finderGiosSensorByIdStationTextField = finderGiosSensorByIdStationTextField;
        this.finderGiosSensorByIdSensorTextField = finderGiosSensorByIdSensorTextField;
        this.giosStationsTableView = giosStationsTableView;
        this.giosSensorsTableView = giosSensorsTableView;
        this.giosStationsRepository = giosStationsRepository;
        this.giosSensorsRepository = giosSensorsRepository;
        this.actualizationOwmButton = actualizationOwmButton;
    }

    @Override
    public void run() { //Funkcja wykonuje aktualizacje w tle
        try {
            //Wyłączamy wszystkie komponenty które mogą kolidować z aktualizacją

            actualizationOwmButton.setDisable(true);
            removeSelectedGiosSensorButton.setDisable(true);
            removeSelectedGiosStationButton.setDisable(true);
            backToFullGiosStationsListButton.setDisable(true);
            backToFullGiosSensorsListButton.setDisable(true);
            startActualizationGiosButton.setDisable(true);
            finderGiosStationByIdStationTextField.setDisable(true);
            finderGiosStationByStationNameTextField.setDisable(true);
            finderGiosSensorByIdStationTextField.setDisable(true);
            finderGiosSensorByIdSensorTextField.setDisable(true);
            //Czyścimy tabele z danych
            giosStationsTableView.getItems().clear();
            giosSensorsTableView.getItems().clear();
            consoleTextArea.appendText("Rozpoczęto aktualizacje GIOS \n");
            jdbcQuery.GIOSActualization();
            consoleTextArea.appendText("Aktualizacja GIOS zakończona pomyślnie \n");
            //Repozytoria są tworzone na nowo bo w bazie są nowe dane po aktualziacji.
            giosSensorsRepository=new GiosSensorsRepository(jdbcQuery.getgiossensorsTable());
            giosStationsRepository=new GiosStationsRepository(jdbcQuery.getgiosstationsTable());
            giosStationsTableView.getItems().addAll(giosStationsRepository.getGiosStationsList());
            giosSensorsTableView.getItems().addAll(giosSensorsRepository.getSensorsList());
            //UWAGA LICZNIK STACJI I SENSORÓW NIE DZIAŁA PRZY AKTUALIZACJI BO NIE MOŻNA UŻYĆ SET TEXT W TEJ FUNKCJI (BŁĄD JEST POWODOWANY DZIAŁANIEM NA INNYM WĄTKU NIE ZALEZNIE CZY BEDZIE TO LABEL CZY JAKIEKOLWIEK INNE POLE Z TEKSTEM)
            //Włączamy na nowo komonenty po ukończeniu aktualizacji
            backToFullGiosStationsListButton.setDisable(false);
            backToFullGiosSensorsListButton.setDisable(false);
            startActualizationGiosButton.setDisable(false);
            finderGiosStationByIdStationTextField.setDisable(false);
            finderGiosStationByStationNameTextField.setDisable(false);
            finderGiosSensorByIdStationTextField.setDisable(false);
            finderGiosSensorByIdSensorTextField.setDisable(false);

            removeSelectedGiosSensorButton.setDisable(false);
            removeSelectedGiosStationButton.setDisable(false);
            actualizationOwmButton.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
