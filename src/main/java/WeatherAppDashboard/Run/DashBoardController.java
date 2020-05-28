package WeatherAppDashboard.Run;

import WeatherAppDashboard.Actualizations.ActualizationGios;
import WeatherAppDashboard.AnotherClasses.DatePickerHelper;
import WeatherAppDashboard.AnotherClasses.TextFieldRestrict;
import WeatherAppDashboard.DbComunication.Jdbc;
import WeatherAppDashboard.DbComunication.JdbcQuery;
import WeatherAppDashboard.DbFinalObjects.*;
import WeatherAppDashboard.Actualizations.ActualizationOwmStation;

import WeatherAppDashboard.Repositories.FinalFromDB.*;
import WeatherAppDashboard.ViewControll.Finders.GIOSSensorsFinder;
import WeatherAppDashboard.ViewControll.Finders.MeasuresFromUsersFinder;
import WeatherAppDashboard.ViewControll.Finders.StationsFinder;
import WeatherAppDashboard.ViewControll.Finders.UsersFinder;
import WeatherAppDashboard.ViewControll.TableViews.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class DashBoardController {
    //Repozytoria
    AppSettingsRepository appSettingsRepository;
    UsersRepository usersRepository;
    MeasuresFromUserRepository measuresFromUsersRepository;
    OwmStationsRepository owmStationsRepository;
    UsersStationsRepository userStationsRepository;
    GiosStationsRepository giosStationsRepository;
    GiosSensorsRepository giosSensorsRepository;
    DustyPlantsRepository dustyPlantsRepository;
    OwmTranslatorRepository owmTranslatorRepository;
    //Wyszukiwarki
    StationsFinder owmstationsFinder;
    StationsFinder userstationsFinder;
    StationsFinder giosstationsFinder;
    UsersFinder usersFinder;
    GIOSSensorsFinder giosSensorsFinder;
    MeasuresFromUsersFinder measuresFromUsersFinder;

    JdbcQuery jdbcQuery;//Praca z baza danych


    //Dolna część dashboardu
    @FXML
    TextArea consoleTextArea; //Konsola
    @FXML
    Button refreshDataButton; //Przycisk odświeżający dane
    //owmstations
    @FXML
    TableView<Station>owmstationsTableView; //Tabela stacji OWM
    @FXML
    TableColumn<Station,Integer> owmIdStationColumn;
    @FXML
    TableColumn<Station,String> owmNameColumn;
    @FXML
    TableColumn<Station,Double> owmLonColumn;
    @FXML
    TableColumn<Station,Double> owmLatColumn;
    @FXML
    TextField finderOwmStationByStationNameTextField; //Pole do wyszukiwania stacji OWM po jej nazwie
    @FXML
    TextField finderOwmStationByIdTextField; //Pole do wyszukiwania stacji OWM po jej ID
    @FXML
    Label howManyOwmStationsLabel; //Ilość stacji OWM
    @FXML
    Button startActualizationOwmButton; //Przycisk rozpoczynający aktualizacje OWM
    @FXML
    Button backToFullOwmStationsListButton; //Przycisk do wyświetlenia wszystkich stacji OWM
    @FXML
    Button owmRemoveSelectedStationButton; //Przycisk do usuniecia wybranej stacji OWM
    //owmtranslator
    @FXML
    TableView<OwmTranslate> owmTranslatorTableView;  //Tabela tłumaczeń zachmurzeń OWM
    @FXML
    TableColumn<OwmTranslate,Integer>OWMIDTranslatorColumn;
    @FXML
    TableColumn<OwmTranslate,String>OWMPLTranslatorColumn;
    @FXML
    TableColumn<OwmTranslate,String>OWMENGTranslatorColumn;
    //Pola do dania nowego tłumaczenia
    @FXML
    TextField addPlTranslatorValueTextField;
    @FXML
    TextField addEngTranslatorValueTextField;
    //Pola do zmodyfikowania istniejącego tłumaczenia
    @FXML
    TextField updatePlTranslatorValueTextField;
    @FXML
    TextField updateEngTranslatorValueTextField;
    //users
    @FXML
    TableView<User>usersTableView; //Tabela zawierająca użytkowników
    @FXML
    TableColumn<User,String>usersPasswordColumn;
    @FXML
    TableColumn<User,String>usersEmailColumn;
    @FXML
    TableColumn<User,Integer>usersIDColumn;
    @FXML
    TextField finderUsersByEmailTextField; //Pole umożlwia wyszukanie użytkownika po e-mail
    @FXML
    TextField finderUsersByIdTextField; //Pole umożlwia wyszukanie użytkownika po ID
    @FXML
    Label howManyUsersLabel; //Ilość zarejestrowanych użytkowników
    //appsettings
    //Pola do modyfikowania ustawień aplikacji
    @FXML
    TextField minTemp;
    @FXML
    TextField maxTemp;
    @FXML
    TextField minWindSpeed;
    @FXML
    TextField maxWindSpeed;
    @FXML
    TextField minPressure;
    @FXML
    TextField maxPressure;
    //giosstations
    @FXML
    Button backToFullGiosStationsListButton; //Przycisk do wyświetlenia wszystkich stacji GIOS
    @FXML
    Button startActualizationGiosButton; //Przycisk rozpoczynający aktualizacje GIOS
    @FXML
    Label howManyGiosStationsLabel; //Ilość stacji GIOS
    @FXML
    TableView<Station> giosStationsTableView; //Tabela ze stacjami GIOS
    @FXML
    TableColumn<Station,Integer>GIOSIDStationColumn;
    @FXML
    TableColumn<Station,String>GIOSNameColumn;
    @FXML
    TableColumn<Station,Double>GIOSLonColumn;
    @FXML
    TableColumn<Station,Double>GIOSLatColumn;
    @FXML
    Button removeSelectedGiosStationButton; //Przycisk do usuniecia wybranej stacji GIOS i jej sensorów
    @FXML
    TextField finderGiosStationByIdStationTextField; //Pole do wyszukiwania stacji GIOS po jej ID
    @FXML
    TextField finderGiosStationByStationNameTextField; //Pole do wyszukiwania stacji GIOS po jej nazwie
    //giossensors
    @FXML
    TableView<GiosSensor> giosSensorsTableView; //Tabela sensorów GIOS
    @FXML
    TableColumn<GiosSensor,Integer>GIOSIDSensorColumn;
    @FXML
    TableColumn<GiosSensor,Integer>GIOSIDSensorStationColumn;
    @FXML
    TableColumn<GiosSensor,String>GIOSSensorNameColumn;
    @FXML
    TableColumn<GiosSensor,String>GIOSSensorShortNameColumn;
    @FXML
    TableColumn<GiosSensor,String>GIOSSensorsStationNameColumn;

    @FXML
    Button backToFullGiosSensorsListButton; //Przycisk do wyświetlenia wszystkich sensorów GIOS
    @FXML
    TextField finderGiosSensorByIdSensorTextField; //Pole do wyszukiwania sensora GIOS po jego ID
    @FXML
    TextField finderGiosSensorByIdStationTextField; //Pole do wyszukiwania sensora GIOS po ID jego stacji
    @FXML
    Label howManyGiosSensorsLabel; //Ilość sensorów GIOS
    @FXML
    Button removeSelectedGiosSensorButton; //Przycisk do usunięcia wybranego sensora GIOS
    //userstations
    @FXML
    TableView<Station> usersStationsTableView;
    @FXML
    TableColumn<Station,Integer>userIDStationColumn;
    @FXML
    TableColumn<Station,String>userNameColumn;
    @FXML
    TableColumn<Station,Double>userLonColumn;
    @FXML
    TableColumn<Station,Double>userLatColumn;
    //Pola do dodania stacji użytkownika
    @FXML
    TextField latAddUserStationTextField;
    @FXML
    TextField lonAddUserStationTextField;
    @FXML
    TextField nameAddUserStationTextField;
    @FXML
    TextField finderUsersStationsByStationNameTextField; //Pole do wyszukiwania stacji użytkownika po jej nazwie
    @FXML
    TextField finderUserStationsByIdFindTextField;  //Pole do wyszukiwania stacji użytkownika po jej ID
    @FXML
    Label howManyUsersStationsLabel; //Ilość stacji użytkowników
    //dustyplants
    @FXML
    TableView<DustyPlant> dustyPlantsTableView; //Tabela zawierająca roślny pylące
    @FXML
    TableColumn<DustyPlant,Integer>dustyPlantsIDColumn;
    @FXML
    TableColumn<DustyPlant,String>dustyPlantsNameColumn;
    @FXML
    TableColumn<DustyPlant,Integer>dustyPlantsSDMColumn;
    @FXML
    TableColumn<DustyPlant,Integer>dustyPlantsEDMColumn;
    @FXML
    TableColumn<DustyPlant,Integer>dustyPlantsSDDColumn;
    @FXML
    TableColumn<DustyPlant,Integer>dustyPlantsEDDColumn;
    //Pola do dodania rośliny pylącej
    @FXML
    TextField addNameDustyPlantTextField;
    @FXML
    DatePicker addStartDustDatePicker;
    @FXML
    DatePicker addEndDustDatePicker;
    //Pola do zmodyfikowania istniejącej rośliny pylącej
    @FXML
    DatePicker updateStartDustDatePicker;
    @FXML
    DatePicker updateEndDustDatePicker;
    @FXML
    TextField updateNameDustyPlantTextField;
    //measuresfromuser
    @FXML
    TableView<MeasureFromUser>measuresFromUsersTableView; //Tabela z pomiarami od użytkowników
    @FXML
    TableColumn<MeasureFromUser,Integer>mfuIDColumn;
    @FXML
    TableColumn<MeasureFromUser,Integer>mfuIDUSERColumn;
    @FXML
    TableColumn<MeasureFromUser,Integer>mfuIDUSERSTATIONColumn;
    @FXML
    TableColumn<MeasureFromUser,String>mfuDateColumn;
    @FXML
    TableColumn<MeasureFromUser,Object>mfuTempColumn;
    @FXML
    TableColumn<MeasureFromUser,Object>mfuWindSpeedColumn;
    @FXML
    TableColumn<MeasureFromUser,Object>mfuHumidityColumn;
    @FXML
    TableColumn<MeasureFromUser,String>mfuCloudinessColumn;
    @FXML
    TableColumn<MeasureFromUser,Object>mfuPressureColumn;
    @FXML
    TableColumn<MeasureFromUser,String>mfuStationNameColumn;
    @FXML
    TableColumn<MeasureFromUser,String>mfuEmailColumn;
    @FXML
    TextField finderMfuByIdUserTextField; //Pole do wyszukiwania pomariu po ID użytkownika
    @FXML
    TextField finderMfuByIdUserStationTextField; //Pole do wyszukiwania pomariu po ID stacji
    @FXML
    Label howManyMfuLabel; //Ilość stacji użytkowników

    @FXML
    void initialize() throws SQLException, IOException {
        connectToDataBase();
        disablePlaceholdersInAllTableViews();
        addDataFromRepositoriesToView();
        activateRestrictionsOnTextFields();
        startFidersOnTextFields();
    }

    private void connectToDataBase() throws IOException { //Funkcja ustanawia połączenie z bazą
        Jdbc jdbc=new Jdbc();
        jdbc.getDbConnection();
        jdbcQuery=new JdbcQuery(jdbc);
    }
    private void disablePlaceholdersInAllTableViews(){ //Funkcja usuwa placeholder z TableView
        owmstationsTableView.setPlaceholder(new Label(""));
        owmTranslatorTableView.setPlaceholder(new Label(""));
        giosSensorsTableView.setPlaceholder(new Label(""));
        giosStationsTableView.setPlaceholder(new Label(""));
        usersTableView.setPlaceholder(new Label(""));
        usersStationsTableView.setPlaceholder(new Label(""));
        measuresFromUsersTableView.setPlaceholder(new Label(""));
        dustyPlantsTableView.setPlaceholder(new Label(""));
    }
    private void addDataFromRepositoriesToView() throws SQLException {
        showOWMStations();
        showOWMTranslates();
        showUsersTable();
        showUserStationsTable();
        showAppSettings();
        showGIOSStations();
        showGIOSSensors();
        showDustyPlantsTable();
        showMeasuresFromUsersTable();
    } //Funkcja odswieża dane

    //Funkcje uruchamiające widoki (Dodają dane do tabel z repozytoriów, uruchamiają wyszukiwarki)
    private void showOWMStations() throws SQLException {
        owmStationsRepository=new OwmStationsRepository(jdbcQuery.getowmtationsTable());
        new OWMStationsTableView(owmstationsTableView, owmIdStationColumn, owmNameColumn, owmLonColumn, owmLatColumn,owmStationsRepository.getStationsList());
        howManyOwmStationsLabel.setText("Ilość stacji: "+owmStationsRepository.getStationsList().size());
        owmstationsFinder =new StationsFinder(owmStationsRepository.getStationsList(), finderOwmStationByIdTextField, finderOwmStationByStationNameTextField,owmstationsTableView, howManyOwmStationsLabel, consoleTextArea);
    }
    private void showGIOSStations() throws SQLException {
        giosStationsRepository=new GiosStationsRepository(jdbcQuery.getgiosstationsTable());
        new GIOSStationsTableView(this.giosStationsTableView,GIOSIDStationColumn,GIOSNameColumn,GIOSLonColumn,GIOSLatColumn,giosStationsRepository.getGiosStationsList());
        howManyGiosStationsLabel.setText("Ilość stacji: "+giosStationsRepository.getGiosStationsList().size());
        giosstationsFinder =new StationsFinder(giosStationsRepository.getGiosStationsList(), finderGiosStationByIdStationTextField, finderGiosStationByStationNameTextField, this.giosStationsTableView, howManyGiosStationsLabel, consoleTextArea);

    }
    private void showGIOSSensors() throws SQLException {
        giosSensorsRepository=new GiosSensorsRepository(jdbcQuery.getgiossensorsTable());
        new GIOSSensorsTableView(this.giosSensorsTableView,GIOSIDSensorColumn,GIOSIDSensorStationColumn,GIOSSensorNameColumn,GIOSSensorShortNameColumn,GIOSSensorsStationNameColumn,giosSensorsRepository.getSensorsList());
        giosSensorsFinder=new GIOSSensorsFinder(giosSensorsRepository.getSensorsList(), finderGiosSensorByIdSensorTextField, finderGiosSensorByIdStationTextField, this.giosSensorsTableView, howManyGiosSensorsLabel,consoleTextArea);
        howManyGiosSensorsLabel.setText("Ilość sensorów: "+giosSensorsRepository.getSensorsList().size());
    }
    private void showOWMTranslates() throws SQLException {
        owmTranslatorRepository=new OwmTranslatorRepository(jdbcQuery.getcloudinesstranslatorTable());
        new OWMTranslatorTableView(this.owmTranslatorTableView,OWMIDTranslatorColumn,OWMPLTranslatorColumn,OWMENGTranslatorColumn,owmTranslatorRepository.getOwmtranslatesList());
    }
    private void showUsersTable() throws SQLException {
        usersRepository=new UsersRepository(jdbcQuery.getusersTable());
        new UsersTableView(usersTableView,usersIDColumn,usersEmailColumn,usersPasswordColumn,usersRepository.getUsersList());
        usersFinder=new UsersFinder(usersRepository.getUsersList(), finderUsersByIdTextField, finderUsersByEmailTextField,usersTableView,howManyUsersLabel);
        howManyUsersLabel.setText("Ilość użytkowników: "+usersRepository.getUsersList().size());
    }
    private void showUserStationsTable() throws SQLException {
        userStationsRepository=new UsersStationsRepository(jdbcQuery.getusersstationsTable());
        new UsersStationsTableView(this.usersStationsTableView,userIDStationColumn,userNameColumn,userLonColumn,userLatColumn,userStationsRepository.getUsersStationsList());
        howManyUsersStationsLabel.setText("Ilość stacji: "+userStationsRepository.getUsersStationsList().size());
        userstationsFinder =new StationsFinder(userStationsRepository.getUsersStationsList(), finderUserStationsByIdFindTextField, finderUsersStationsByStationNameTextField, this.usersStationsTableView, howManyUsersStationsLabel, consoleTextArea);
    }
    private void showDustyPlantsTable() throws SQLException {
        dustyPlantsRepository=new DustyPlantsRepository(jdbcQuery.getdustyplantsTable());
        new DustyPlantsTableView(dustyPlantsTableView,dustyPlantsIDColumn,dustyPlantsNameColumn,dustyPlantsSDMColumn,dustyPlantsEDMColumn,dustyPlantsSDDColumn,dustyPlantsEDDColumn,dustyPlantsRepository.getDustyPlantsList());

    }
    private void showMeasuresFromUsersTable() throws SQLException {
        measuresFromUsersRepository =new MeasuresFromUserRepository(jdbcQuery.getmeasuresfromusersTable());
        new MeasuresFromUserTableView(measuresFromUsersTableView,mfuIDColumn,mfuIDUSERColumn,mfuIDUSERSTATIONColumn,mfuDateColumn,mfuTempColumn,mfuWindSpeedColumn,mfuHumidityColumn,mfuCloudinessColumn,mfuPressureColumn,mfuStationNameColumn,mfuEmailColumn, measuresFromUsersRepository.getMesuresFromUserList());
        measuresFromUsersFinder=new MeasuresFromUsersFinder(measuresFromUsersRepository.getMesuresFromUserList(), finderMfuByIdUserTextField, finderMfuByIdUserStationTextField,measuresFromUsersTableView, howManyMfuLabel);
        howManyMfuLabel.setText("Ilość pomiarów: "+measuresFromUsersRepository.getMesuresFromUserList().size());
    }
    private void showAppSettings() throws SQLException {
        appSettingsRepository=new AppSettingsRepository(jdbcQuery.getappsettingsTable());
        minTemp.setText(String.valueOf(appSettingsRepository.getAppSettings().getMinTemp()));
        maxTemp.setText(String.valueOf(appSettingsRepository.getAppSettings().getMaxTemp()));
        minWindSpeed.setText(String.valueOf(appSettingsRepository.getAppSettings().getMinWind()));
        maxWindSpeed.setText(String.valueOf(appSettingsRepository.getAppSettings().getMaxWind()));
        minPressure.setText(String.valueOf(appSettingsRepository.getAppSettings().getMinPressure()));
        maxPressure.setText(String.valueOf(appSettingsRepository.getAppSettings().getMaxPressure()));
    }
    private void activateRestrictionsOnTextFields(){ //Funkcja ustawia restrykcje na pola do wprowadzania danych
        TextFieldRestrict textFieldRestrict=new TextFieldRestrict();
        textFieldRestrict.onlyIntegerInTextField(finderGiosSensorByIdSensorTextField);
        textFieldRestrict.onlyIntegerInTextField(finderGiosSensorByIdStationTextField);
        textFieldRestrict.limitCharsForTextField(finderOwmStationByIdTextField,10);
        textFieldRestrict.limitCharsForTextField(finderOwmStationByStationNameTextField,40);
        textFieldRestrict.onlyIntegerInTextField(finderMfuByIdUserTextField);
        textFieldRestrict.limitCharsForTextField(finderMfuByIdUserTextField,10);
        textFieldRestrict.onlyIntegerInTextField(finderMfuByIdUserStationTextField);
        textFieldRestrict.limitCharsForTextField(finderMfuByIdUserStationTextField,10);
        textFieldRestrict.onlyTextInTextField(finderUsersStationsByStationNameTextField);
        textFieldRestrict.onlyIntegerInTextField(finderUserStationsByIdFindTextField);
        textFieldRestrict.limitCharsForTextField(finderUsersStationsByStationNameTextField,40);
        textFieldRestrict.limitCharsForTextField(finderUserStationsByIdFindTextField,10);
        textFieldRestrict.onlyTextInTextField(nameAddUserStationTextField);
        textFieldRestrict.onlyPlusDigitsInTextField(lonAddUserStationTextField);
        textFieldRestrict.onlyPlusDigitsInTextField(latAddUserStationTextField);
        textFieldRestrict.limitCharsForTextField(nameAddUserStationTextField,40);
        textFieldRestrict.limitCharsForTextField(lonAddUserStationTextField,10);
        textFieldRestrict.limitCharsForTextField(latAddUserStationTextField,10);
        textFieldRestrict.onlyTextInTextField(finderOwmStationByStationNameTextField);
        textFieldRestrict.onlyIntegerInTextField(finderOwmStationByIdTextField);
        textFieldRestrict.onlyTextInTextField(addEngTranslatorValueTextField);
        textFieldRestrict.onlyTextInTextField(addPlTranslatorValueTextField);
        textFieldRestrict.onlyTextInTextField(updatePlTranslatorValueTextField);
        textFieldRestrict.onlyTextInTextField(updateEngTranslatorValueTextField);
        textFieldRestrict.limitCharsForTextField(addEngTranslatorValueTextField,40);
        textFieldRestrict.limitCharsForTextField(addPlTranslatorValueTextField,40);
        textFieldRestrict.limitCharsForTextField(updatePlTranslatorValueTextField,40);
        textFieldRestrict.limitCharsForTextField(updateEngTranslatorValueTextField,40);
        textFieldRestrict.onlyTextInTextField(addNameDustyPlantTextField);
        textFieldRestrict.limitCharsForTextField(addNameDustyPlantTextField,40);
        textFieldRestrict.onlyDigitsInTextField(minTemp);
        textFieldRestrict.limitCharsForTextField(minTemp,10);
        textFieldRestrict.onlyDigitsInTextField(maxTemp);
        textFieldRestrict.limitCharsForTextField(maxTemp,10);
        textFieldRestrict.onlyPlusDigitsInTextField(minWindSpeed);
        textFieldRestrict.limitCharsForTextField(minWindSpeed,10);
        textFieldRestrict.onlyPlusDigitsInTextField(maxWindSpeed);
        textFieldRestrict.limitCharsForTextField(maxWindSpeed,10);
        textFieldRestrict.onlyPlusDigitsInTextField(minPressure);
        textFieldRestrict.limitCharsForTextField(minPressure,10);
        textFieldRestrict.onlyPlusDigitsInTextField(maxPressure);
        textFieldRestrict.limitCharsForTextField(maxPressure,10);
    }
    private void startFidersOnTextFields(){ //Funkcja umożliwia włączanie lub wyłaczanie wyszukiwarek
        owmstationsFinder.searchByNameOnWriteInTextField(finderOwmStationByStationNameTextField);
        owmstationsFinder.searchByIDOnWriteInTextField(finderOwmStationByIdTextField);
        userstationsFinder.searchByNameOnWriteInTextField(finderUsersStationsByStationNameTextField);
        userstationsFinder.searchByIDOnWriteInTextField(finderUserStationsByIdFindTextField);
        giosstationsFinder.searchByNameOnWriteInTextField(finderGiosStationByStationNameTextField);
        giosstationsFinder.searchByIDOnWriteInTextField(finderGiosStationByIdStationTextField);
        giosSensorsFinder.searchByIDGIOSSensor(finderGiosSensorByIdSensorTextField);
        giosSensorsFinder.searchByIDGIOSStation(finderGiosSensorByIdStationTextField);
        usersFinder.searchByEmail(finderUsersByEmailTextField);
        usersFinder.searchByID(finderUsersByIdTextField);
        measuresFromUsersFinder.searchByIDUser(finderMfuByIdUserTextField);
        measuresFromUsersFinder.searchByIDUserStation(finderMfuByIdUserStationTextField);
    }

    //Obsługa buttonów
    @FXML
    void onClickRefreshDataButton() throws SQLException { //Przycisk do odswieżania danych
        showOWMTranslates();
        showUsersTable();
        showUserStationsTable();
        showAppSettings();
        showDustyPlantsTable();
        showMeasuresFromUsersTable();
        consoleTextArea.appendText("Dane zostały odświeżone \n");
    }
    @FXML
    void onClickSaveSettingsButton() throws SQLException {
        jdbcQuery.updateAppSetings(minTemp,maxTemp, minWindSpeed, maxWindSpeed,minPressure,maxPressure,consoleTextArea);
        appSettingsRepository=new AppSettingsRepository(jdbcQuery.getappsettingsTable());
        showAppSettings();
    }

    //Funkcje umożliwiające aktualizacje
    @FXML
    void onClickActualizionOwmStationsButton(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aktualizacja stacji OpenWeatherMap");
        alert.setHeaderText("Aktualizacja potrwa około 5-10 minut. Stracisz poprzednie dane dotyczące stacji.");
        alert.setContentText("Czy chcesz wykonać aktualizacje?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            howManyOwmStationsLabel.setText("Aby zrestować licznik kliknij odśwież");
            ActualizationOwmStation actualizationOWMStation =new ActualizationOwmStation(jdbcQuery,consoleTextArea, backToFullOwmStationsListButton, startActualizationOwmButton,owmRemoveSelectedStationButton,refreshDataButton, finderOwmStationByIdTextField, finderOwmStationByStationNameTextField,owmstationsTableView,owmStationsRepository,startActualizationOwmButton);
            Thread OWMActualizationThread=new Thread(actualizationOWMStation);
            OWMActualizationThread.start();
        }
    }
    @FXML
    void onClickActualizionGIOSButton(){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aktualizacja GIOS");
        alert.setHeaderText("Aktualizacja potrwa około 20 minut. Stracisz poprzednie dane dotyczące stacji.");
        alert.setContentText("Czy chcesz wykonać aktualizacje?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            howManyGiosStationsLabel.setText("Aby zrestować licznik kliknij odśwież");
            howManyGiosSensorsLabel.setText("Aby zrestować licznik kliknij odśwież");
            ActualizationGios actualizationgios =new ActualizationGios(jdbcQuery,consoleTextArea, backToFullGiosStationsListButton, backToFullGiosSensorsListButton, startActualizationGiosButton,removeSelectedGiosStationButton,removeSelectedGiosSensorButton,refreshDataButton, finderGiosStationByIdStationTextField, finderGiosStationByStationNameTextField, finderGiosSensorByIdSensorTextField, finderGiosSensorByIdStationTextField, giosStationsTableView, giosSensorsTableView,giosStationsRepository,giosSensorsRepository,startActualizationGiosButton);
            Thread OWMActualizationThread=new Thread(actualizationgios);
            OWMActualizationThread.start();
        }

    }

    //Funkcje umożliwiające powrót do pełnej listy obiektów w tabeli
    @FXML
    void onClickShowAllOwmStationsButton(){
        owmstationsTableView.getItems().clear();
        owmstationsTableView.getItems().addAll(owmStationsRepository.getStationsList());
        howManyOwmStationsLabel.setText("Ilość stacji: "+owmStationsRepository.getStationsList().size());
    }
    @FXML
    void onClickShowAllGiosStationsButton(){
        giosStationsTableView.getItems().clear();
        giosStationsTableView.getItems().addAll(giosStationsRepository.getGiosStationsList());
        howManyGiosStationsLabel.setText("Ilość stacji: "+giosStationsRepository.getGiosStationsList().size());
    }
    @FXML
    void onClickShowAllUsersStationsButton(){
        usersStationsTableView.getItems().clear();
        usersStationsTableView.getItems().addAll(userStationsRepository.getUsersStationsList());
        howManyUsersStationsLabel.setText("Ilość stacji: "+userStationsRepository.getUsersStationsList().size());
    }
    @FXML
    void onClickShowAllUsersButton(){
        usersTableView.getItems().clear();
        usersTableView.getItems().addAll(usersRepository.getUsersList());
        howManyUsersLabel.setText("Ilość stacji: "+usersRepository.getUsersList().size());
    }

    //Funkcje umożliwiające usunięcie obiektu z tabeli
    @FXML
    void onClickRemoveOWMStationButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie stacji OpenWeatherMap z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybraną stacje z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedOwmStation(owmstationsTableView, consoleTextArea);
            showOWMStations();
        }
    }
    @FXML
    void onClickRemoveGIOSStationButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie stacji GIOS z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybraną stacje GIOS i jej sensory z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedGIOSStationFromDataBase(giosStationsTableView, consoleTextArea);
            showGIOSStations();
            showGIOSSensors();
        }
    }
    @FXML
    void onClickRemoveGIOSSensorButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie sensora GIOS z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybrany sensor GIOS z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedGIOSSensor(giosSensorsTableView, consoleTextArea);
            showGIOSStations();
            showGIOSSensors();
        }
    }
    @FXML
    void onClickRemoveSelectedTranslateButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie tłumaczenia z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybrane tłumaczenie z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedOwmTranslate(owmTranslatorTableView,consoleTextArea);
            showOWMTranslates();
        }
    }
    @FXML
    void onClickRemoveUserButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie pomiaru z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybranego użytkownika z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedUser(usersTableView,consoleTextArea);
            showUsersTable();
        }
    }
    @FXML
    void onClickRemoveUserStationButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie stacji z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybraną stacje z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedUserStationFromDataBase(usersStationsTableView, consoleTextArea);
            showUserStationsTable();
        }
    }
    @FXML
    void onClickRemoveMeasureFromUserButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie pomiaru z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybrany pomiar z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedMeasureFromUser(measuresFromUsersTableView,consoleTextArea);
            showMeasuresFromUsersTable();
        }
    }
    @FXML
    void onClickRemoveSelectedDustyPlant() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Usunięcie pylącej rośliny z bazy danych");
        alert.setHeaderText("");
        alert.setContentText("Czy napewno chcesz usunąć wybraną roślinę z bazy danych?");
        ButtonType buttonTypeOne = new ButtonType("Tak");
        ButtonType buttonTypeTwo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            jdbcQuery.removeSelectedDustyPlant(dustyPlantsTableView, consoleTextArea);
            showDustyPlantsTable();
        }
    }

    //Funkcje umożliwiające dodanie nowego obiektu do tabeli
    @FXML
    void onClickAddNewTranslateButton() throws IOException, SQLException {
        jdbcQuery.addOwmTranslate(owmTranslatorRepository,addPlTranslatorValueTextField, addEngTranslatorValueTextField,consoleTextArea);
        showOWMTranslates();
    }
    @FXML
    void onClickAddUsersStationButton() throws SQLException {
        jdbcQuery.addNewUserStation(userStationsRepository,nameAddUserStationTextField,lonAddUserStationTextField,latAddUserStationTextField,consoleTextArea);
        showUserStationsTable();
    }
    @FXML
    void onClickAddNewDustyPlant() throws SQLException {
        jdbcQuery.addNewDustyPlant(dustyPlantsRepository,addNameDustyPlantTextField,addStartDustDatePicker,addEndDustDatePicker,consoleTextArea);
        showDustyPlantsTable();
    }

    //Funkcje umożliwiające zmodyfikowanie biektu w tabeli
    @FXML
    void onClickUpdateTranslateButton() throws SQLException {
        jdbcQuery.updateSelectedOwmTranslate(owmTranslatorRepository,owmTranslatorTableView, updatePlTranslatorValueTextField, updateEngTranslatorValueTextField,consoleTextArea);
        showOWMTranslates();
    }
    @FXML
    void onClickUpdateSelectedDustyPlant() throws SQLException {
        jdbcQuery.updateSelectedDustyPlant(dustyPlantsRepository,updateNameDustyPlantTextField, updateStartDustDatePicker, updateEndDustDatePicker, consoleTextArea, dustyPlantsTableView);
        showDustyPlantsTable();
    }

    //Funkcje przepisujące dane z zaznaczonych obiektów tabeli
    @FXML
    void onClickOwmTranslatorTableView(){
        updateEngTranslatorValueTextField.setText(owmTranslatorTableView.getSelectionModel().getSelectedItem().getENG());
        updatePlTranslatorValueTextField.setText(owmTranslatorTableView.getSelectionModel().getSelectedItem().getPL());
    }
    @FXML
    void onClickDustyPlantsTableView(){

        DatePickerHelper.setDatePickerDate(updateStartDustDatePicker, dustyPlantsTableView.getSelectionModel().getSelectedItem().getStartDustMonth(), dustyPlantsTableView.getSelectionModel().getSelectedItem().getStartDustDay());
        DatePickerHelper.setDatePickerDate(updateEndDustDatePicker, dustyPlantsTableView.getSelectionModel().getSelectedItem().getEndDustMonth(), dustyPlantsTableView.getSelectionModel().getSelectedItem().getEndDustDay());
        updateNameDustyPlantTextField.setText(dustyPlantsTableView.getSelectionModel().getSelectedItem().getName());

    }
}
