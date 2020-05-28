package WeatherAppDashboard.Actualizations;

import WeatherAppDashboard.DbComunication.JdbcQuery;
import WeatherAppDashboard.DbFinalObjects.Station;
import WeatherAppDashboard.Repositories.FinalFromDB.OwmStationsRepository;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.SQLException;

public class ActualizationOwmStation implements Runnable { //Klasa odpowiada za aktualziacje OWM

    private JdbcQuery jdbcQuery; //Dostęp do bazy danych
    private TextArea consoleTextArea; //Konsola do wyświetlenia komunikatów
    private Button backToFullOwmStationsListButton; //Przycisk do wyświetlenia wszystkich stacji OWM
    private Button startActualizationOwmButton; //Przycisk rozpoczynający aktualizacje OWM
    private Button removeSelectedOwmStationButton; //Przycisk do usuniecia wybranej stacji OWM
    private Button refreshDataButton; //Przycisk odswieżajacy dane w aplikacji
    private TextField finderOwmStationByIdTextField; //Pole do wyszukiwania stacji OWM po jej ID
    private TextField finderOwmStationByStationNameTextField;  //Pole do wyszukiwania stacji OWM po jej nazwie
    private TableView<Station> owmStationsTableView; //Tabela zawierajace dane o stacjach OWM
    private OwmStationsRepository owmStationsRepository; //Repozytorium przechowujące stacje GIOS pobrane z bazy danych
    private Button actualizationOwmButton;

    public ActualizationOwmStation(JdbcQuery jdbcQuery, TextArea consoleTextArea, Button backToFullOwmStationsListButton, Button startActualizationOwmButton, Button removeSelectedOwmStationButton, Button refreshDataButton, TextField finderOwmStationByIdTextField, TextField finderOwmStationByStationNameTextField, TableView<Station> owmStationsTableView, OwmStationsRepository owmStationsRepository, Button actualizationOwmButton) {
        this.jdbcQuery = jdbcQuery;
        this.consoleTextArea = consoleTextArea;
        this.backToFullOwmStationsListButton = backToFullOwmStationsListButton;
        this.startActualizationOwmButton = startActualizationOwmButton;
        this.removeSelectedOwmStationButton = removeSelectedOwmStationButton;
        this.refreshDataButton = refreshDataButton;
        this.finderOwmStationByIdTextField = finderOwmStationByIdTextField;
        this.finderOwmStationByStationNameTextField = finderOwmStationByStationNameTextField;
        this.owmStationsTableView = owmStationsTableView;
        this.owmStationsRepository = owmStationsRepository;
        this.actualizationOwmButton = actualizationOwmButton;
    }

    @Override
    public void run() {
        try {
            //Wyłączamy wszystkie komponenty które mogą kolidować z aktualizacją

            actualizationOwmButton.setDisable(true);
            backToFullOwmStationsListButton.setDisable(true);
            removeSelectedOwmStationButton.setDisable(true);
            startActualizationOwmButton.setDisable(true);
            finderOwmStationByIdTextField.setDisable(true);
            finderOwmStationByStationNameTextField.setDisable(true);
            //Czyścimy tabele z danych
            owmStationsTableView.getItems().clear();
            consoleTextArea.appendText("Rozpoczęto aktualizacje stacji OWM \n");
            jdbcQuery.owmStationsActualization();
            consoleTextArea.appendText("Aktualizacja stacji OWM zakończona pomyślnie \n");
            //Repozytorium jest tworzone na nowo bo w bazie są nowe dane po aktualziacji.
            owmStationsRepository=new OwmStationsRepository(jdbcQuery.getowmtationsTable());
            owmStationsTableView.getItems().addAll(owmStationsRepository.getStationsList());
            //UWAGA LICZNIK STACJI NIE DZIAŁA PRZY AKTUALIZACJI BO NIE MOŻNA UŻYĆ SET TEXT W TEJ FUNKCJI (BŁĄD JEST POWODOWANY DZIAŁANIEM NA INNYM WĄTKU NIE ZALEZNIE CZY BEDZIE TO LABEL CZY JAKIEKOLWIEK INNE POLE Z TEKSTEM)
            //Włączamy na nowo komonenty po ukończeniu aktualizacji
            backToFullOwmStationsListButton.setDisable(false);
            startActualizationOwmButton.setDisable(false);
            removeSelectedOwmStationButton.setDisable(false);

            finderOwmStationByIdTextField.setDisable(false);
            finderOwmStationByStationNameTextField.setDisable(false);
            actualizationOwmButton.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
