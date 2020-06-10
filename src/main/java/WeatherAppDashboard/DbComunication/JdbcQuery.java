package WeatherAppDashboard.DbComunication;

import WeatherAppDashboard.DbFinalObjects.*;
import WeatherAppDashboard.Repositories.FinalForActualization.GiosRepositoryToActualization;
import WeatherAppDashboard.Repositories.FinalForActualization.OwmRepositoryToActualization;
import WeatherAppDashboard.Repositories.FinalFromDB.DustyPlantsRepository;
import WeatherAppDashboard.Repositories.FinalFromDB.OwmTranslatorRepository;
import WeatherAppDashboard.Repositories.FinalFromDB.UsersStationsRepository;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class JdbcQuery { //Klasa zawiera metody współpracujące z bazą danych

    private static Connection connection; //Polaczenie z baza danych
    public JdbcQuery(Jdbc jdbc) throws IOException { //Laczymy sie z baza
        connection = jdbc.getConnection();
    }
    //Metody do pobierania danych z tabeli
    public ArrayList<Station> getowmtationsTable() throws SQLException {
        ArrayList<Station> stations =new ArrayList<>();
        String selectAllFromowmstationsTableQuerySQL="SELECT * FROM owmstations";
        Statement stmt= connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(selectAllFromowmstationsTableQuerySQL);
        while (resultSet.next()) {
            stations.add(new Station(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4)));
        }
        return stations;
    }
    public ArrayList<OwmTranslate> getcloudinesstranslatorTable() throws SQLException {
        ArrayList<OwmTranslate> owmTranslates = new ArrayList<>();
        String selectAllFromowmstationsTableQuerySQL = "SELECT * FROM cloudinesstranslator";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllFromowmstationsTableQuerySQL);
        while (resultSet.next()) {
            owmTranslates.add(new OwmTranslate(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }
        return owmTranslates;
    }
    public ArrayList<User> getusersTable() throws SQLException {
        ArrayList<User> usersTable = new ArrayList<>();
        String selectAllFromusersTablequerySQL = "SELECT * FROM users";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllFromusersTablequerySQL);
        while (resultSet.next()) {
            usersTable.add(new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
        }
        return usersTable;
    }
    public ArrayList<Station> getgiosstationsTable() throws SQLException {
        ArrayList<Station> stations =new ArrayList<>();
        String getgiosstationsTableQuerySQL="SELECT * FROM giosstations";
        Statement stmt= connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(getgiosstationsTableQuerySQL);
        while (resultSet.next()) {
            stations.add(new Station(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4)));
        }
        return stations;
    }
    public ArrayList<GiosSensor> getgiossensorsTable() throws SQLException {
        ArrayList<GiosSensor> sensors =new ArrayList<>();
        String getgiossensorsTableQuerySQL="SELECT * FROM giossensors";
        Statement stmt= connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(getgiossensorsTableQuerySQL);
        while (resultSet.next()) {
            sensors.add(new GiosSensor(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),getGiosStationNameById(resultSet.getInt(2))));
        }
        return sensors;
    }
    private String getGiosStationNameById(int id) throws SQLException {
        String selectAllFromusersTablequerySQL = "SELECT NAME FROM giosstations WHERE IDGIOSSTATION="+id;
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllFromusersTablequerySQL);
        resultSet.next();
        return resultSet.getString(1);
    }
    public ArrayList<Station> getusersstationsTable() throws SQLException {
        ArrayList<Station> stations =new ArrayList<>();
        String selectAllFromowmstationsTableQuerySQL="SELECT * FROM usersstations";
        Statement stmt= connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(selectAllFromowmstationsTableQuerySQL);
        while (resultSet.next()) {
            stations.add(new Station(resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4)));
        }
        return stations;
    }
    public ArrayList<DustyPlant> getdustyplantsTable() throws SQLException {
        ArrayList<DustyPlant> dustyPlants =new ArrayList<>();
        String getdustyplantsTableQuerySQL="SELECT * FROM dustyplants";
        Statement stmt= connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(getdustyplantsTableQuerySQL);
        while (resultSet.next()) {

            dustyPlants.add(new DustyPlant(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6)));
        }
        return dustyPlants;
    }
    public ArrayList<MeasureFromUser> getmeasuresfromusersTable() throws SQLException {
        ArrayList<MeasureFromUser> measuresFromUsers =new ArrayList<>();
        String getdustyplantsTableQuerySQL="SELECT * FROM measuresfromusers";
        Statement stmt= connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(getdustyplantsTableQuerySQL);
        while (resultSet.next()) {
            measuresFromUsers.add(new MeasureFromUser(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getObject(5),resultSet.getObject(6),resultSet.getObject(7),resultSet.getString(8),resultSet.getObject(9), getEmailById(resultSet.getInt(2)), getStationNameById(resultSet.getInt(3))));
        }
        return measuresFromUsers;
    }
    private String getEmailById(int iDUser) throws SQLException {
        String selectAllFromusersTablequerySQL = "SELECT EMAIL FROM users WHERE IDUSER="+iDUser;
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllFromusersTablequerySQL);
        resultSet.next();
        return resultSet.getString(1);
    }
    private String getStationNameById(int iDStation) throws SQLException {
        String selectAllFromusersTablequerySQL = "SELECT NAME FROM usersstations WHERE IDUSERSTATION="+iDStation;
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllFromusersTablequerySQL);
        resultSet.next();
        return resultSet.getString(1);
    }
    public AppSettings getappsettingsTable() throws SQLException {
        String selectAllFromusersTablequerySQL = "SELECT * FROM appsettings";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllFromusersTablequerySQL);
        resultSet.next();
        return new AppSettings(resultSet.getDouble(2),resultSet.getDouble(3),resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getDouble(6),resultSet.getDouble(7));
    }

    //Metody do usuwania zaznaczonych danych z tabeli
    public void removeSelectedOwmStation(TableView<Station>owmstationsTableView, TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String addGiosSensorsQuerySQL="DELETE FROM owmstations WHERE IDSTATION="+owmstationsTableView.getSelectionModel().getSelectedItem().getId();
            consoleArea.appendText("Wybrana stacja została usunięta \n");
            stmt.executeUpdate(addGiosSensorsQuerySQL);
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie usunięto stacji ponieważ żadna nie została wybrana \n");
        }
    }
    public void removeSelectedUser(TableView<User>usersTableView, TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String removeUselessMeasures = "DELETE FROM measuresfromusers WHERE IDUSER=" + usersTableView.getSelectionModel().getSelectedItem().getId();
            stmt.executeUpdate(removeUselessMeasures);
            String addGiosSensorsQuerySQL = "DELETE FROM users WHERE IDUSER=" + usersTableView.getSelectionModel().getSelectedItem().getId();
            stmt.executeUpdate(addGiosSensorsQuerySQL);
            consoleArea.appendText("Usunieto wybranego użytkownika z bazy danych \n");
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie usunieto użytkownika z bazy danych ponieważ żaden nie został wybrany \n");
        }

    }
    public void removeSelectedGIOSSensor(TableView<GiosSensor>GIOSSensorsTableView, TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String removeSelectedSesnorQuerySQL = "DELETE FROM giossensors WHERE IDGIOSSENSOR=" + GIOSSensorsTableView.getSelectionModel().getSelectedItem().getIDSensor();
            stmt.executeUpdate(removeSelectedSesnorQuerySQL);
            consoleArea.appendText("Usunieto wybrany sensor \n");
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie usunieto sensora ponieważ żaden nie został wybrany \n");
        }
    }
    public void removeSelectedUserStationFromDataBase(TableView<Station>usersstationsTableView, TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String deleteUselessMeasures = "DELETE FROM measuresfromusers WHERE IDUSERSTATION=" + usersstationsTableView.getSelectionModel().getSelectedItem().getId();
            stmt.executeUpdate(deleteUselessMeasures);
            String addGiosSensorsQuerySQL = "DELETE FROM usersstations WHERE IDUSERSTATION=" + usersstationsTableView.getSelectionModel().getSelectedItem().getId();
            stmt.executeUpdate(addGiosSensorsQuerySQL);

            consoleArea.appendText("Wybrana stacja została usunięta \n");
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie usunięto stacji ponieważ żadna nie została wybrana \n");
        }
    }
    public void removeSelectedGIOSStationFromDataBase(TableView<Station>GIOSStationsTableView, TextArea consoleArea) throws SQLException {
       try {
           Statement stmt = connection.createStatement();
           String removeUselessSesnorsQuerySQL = "DELETE FROM giossensors WHERE IDGIOSSTATION=" + GIOSStationsTableView.getSelectionModel().getSelectedItem().getId();
           stmt.executeUpdate(removeUselessSesnorsQuerySQL);
           String removeSelectedGIOSSttionQuerySQL = "DELETE FROM giosstations WHERE IDGIOSSTATION=" + GIOSStationsTableView.getSelectionModel().getSelectedItem().getId();
           stmt.executeUpdate(removeSelectedGIOSSttionQuerySQL);
           consoleArea.appendText("Wybrana stacja została usunięta \n");
       }
       catch (NullPointerException e){
           consoleArea.appendText("Nie usunięto stacji ponieważ żadna nie została wybrana \n");
       }
    }
    public void removeSelectedDustyPlant(TableView<DustyPlant>dustyPlantTableView,TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String addGiosSensorsQuerySQL = "DELETE FROM dustyplants WHERE IDDUSTYPLANT=" + dustyPlantTableView.getSelectionModel().getSelectedItem().getId();
            stmt.executeUpdate(addGiosSensorsQuerySQL);
            consoleArea.appendText("Usunięto zaznaczoną roślinę pylącą o ID:" + dustyPlantTableView.getSelectionModel().getSelectedItem().getId() + "\n");
        } catch (NullPointerException e) {
            consoleArea.appendText("Nie usunięto rośliny pylącej ponieważ żadna nie została zaznaczona \n");
        }
    }
    public void removeSelectedMeasureFromUser(TableView<MeasureFromUser>measureFromUserTableView,TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String addGiosSensorsQuerySQL = "DELETE FROM measuresfromusers WHERE ID=" + measureFromUserTableView.getSelectionModel().getSelectedItem().getID();
            stmt.executeUpdate(addGiosSensorsQuerySQL);
            consoleArea.appendText("Usunięto zaznaczony pomiar o ID:" + measureFromUserTableView.getSelectionModel().getSelectedItem().getID() + "\n");
        } catch (NullPointerException e) {
            consoleArea.appendText("Nie pomiaru ponieważ żaden nie został zaznaczony \n");
        }
    }
    public void removeSelectedOwmTranslate(TableView<OwmTranslate>owmstranslatorTableView, TextArea consoleArea) throws SQLException {
        try {
            Statement stmt = connection.createStatement();
            String addGiosSensorsQuerySQL = "DELETE FROM cloudinesstranslator WHERE IDTRANSLATOR=" + owmstranslatorTableView.getSelectionModel().getSelectedItem().getId();
            stmt.executeUpdate(addGiosSensorsQuerySQL);
            consoleArea.appendText("Usunięto zaznaczone tłumaczenie \n");
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie usunięto tłumaczenia ponieważ żadnego nie zaznaczono \n");
        }

    }

    //Metody do dodawania danych do tabeli
    public void addOwmTranslate(OwmTranslatorRepository owmTranslatorRepository,TextField PL, TextField ENG, TextArea consoleArea) throws IOException, SQLException {
        if(isAlreadyOwmTranslateInDataBase(owmTranslatorRepository,PL,ENG,consoleArea)==false) {
            if (!(PL.getText().equals("") || ENG.getText().equals(""))) {
                Statement stmt = connection.createStatement();
                String insertIntoTranslatorNewRecordQuerySQL = "INSERT INTO cloudinesstranslator (PL,ENG) VALUES ('" + PL.getText() + "','" + ENG.getText() + "')";
                stmt.executeUpdate(insertIntoTranslatorNewRecordQuerySQL);
                consoleArea.appendText("Dodano nowe tłumaczenie \n");
            } else {
                consoleArea.appendText("Nie dodano tłumaczenia ponieważ nie wprowadzono wszystkich danych \n");
            }
        }
    }
    public void addNewUserStation(UsersStationsRepository usersStationsRepository,TextField NAME,TextField LON, TextField LAT,TextArea consoleArea) throws SQLException {
        if(!(NAME.getText().equals("")||LON.getText().equals("")||LAT.getText().equals(""))) {
            if(isUsersStationAlreadyInDataBase(usersStationsRepository,NAME,LON,LAT,consoleArea)==false) {
                String addNewUserStationQuerySQL = "INSERT INTO usersstations (NAME,LON,LAT) VALUES ('" + NAME.getText() + "'," + LON.getText() + "," + LAT.getText() + ")";
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(addNewUserStationQuerySQL);
                consoleArea.appendText("Dodano nową stacje \n");
            }
        }
        else{
            consoleArea.appendText("Nie dodano nowej stacji ponieważ nie wprowadzono kompletnych danych \n");
        }
    }
    public void addNewDustyPlant(DustyPlantsRepository dustyPlantsRepository,TextField dustyPlantName,DatePicker startDustDatePicker,DatePicker endDustDatePicker,TextArea consoleArea) throws SQLException {
        try{
            if(isAlreadyDustyPlantInDataBase(dustyPlantsRepository,dustyPlantName,consoleArea)==false) {
                if (startDustDatePicker.getValue().isBefore(endDustDatePicker.getValue())) {
                    String startDustMonth = String.valueOf(startDustDatePicker.getValue().getMonthValue());
                    String endDustMonth = String.valueOf(endDustDatePicker.getValue().getMonthValue());
                    String startDustDay = String.valueOf(startDustDatePicker.getValue().getDayOfMonth());
                    String endDustDay = String.valueOf(endDustDatePicker.getValue().getDayOfMonth());
                    String addNewUserStationQuerySQL = "INSERT INTO dustyplants (NAME,STARTDUSTMONTH,ENDDUSTMONTH,STARTDUSTDAY,ENDDUSTDAY) VALUES ('" + dustyPlantName.getText() + "'," + startDustMonth + "," + endDustMonth + "," + startDustDay + "," + endDustDay + ")";
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(addNewUserStationQuerySQL);
                    consoleArea.appendText("Dodano nową rośline pylącą \n");
                } else {
                    consoleArea.appendText("Nie dodano nowej rośliny pylącej, data rozpoczęcia pylenia nie może być późniejsza niż data zakończenia pylenia \n");
                }
            }
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie dodano nowej rośliny pylącej, nieprawidłowe dane wejściowe \n");
        }
    }
    //Metody do modyfikowania danych w tabeli
    public void updateSelectedOwmTranslate(OwmTranslatorRepository owmTranslatorRepository,TableView<OwmTranslate> owmtranslatorTableView, TextField PL, TextField ENG, TextArea consoleArea) throws SQLException {
        try {
            if (isAlreadyOwmTranslateInDataBase(owmTranslatorRepository, PL, ENG, consoleArea)) {
                Statement stmt = connection.createStatement();
                String addGiosSensorsQuerySQL = "UPDATE cloudinesstranslator SET PL='" + PL.getText() + "',ENG='" + ENG.getText() + "' WHERE IDTRANSLATOR=" + owmtranslatorTableView.getSelectionModel().getSelectedItem().getId();
                stmt.executeUpdate(addGiosSensorsQuerySQL);
                consoleArea.appendText("Zmodyfikowano tłumaczenie o ID: " + owmtranslatorTableView.getSelectionModel().getSelectedItem().getId() + "\n");
            }
        }
        catch(NullPointerException e){
                consoleArea.appendText("Nie zmodyfikowano tłumaczenia ponieważ żadnego nie zaznaczono \n");
            }
    }
    public void updateSelectedDustyPlant(DustyPlantsRepository dustyPlantsRepository,TextField dustyPlantName,DatePicker startDustDatePicker,DatePicker endDustDatePicker,TextArea consoleArea,TableView<DustyPlant>dustyPlantTableView) throws SQLException {
        try {
            if(isAlreadyDustyPlantInDataBase(dustyPlantsRepository,dustyPlantName,consoleArea)==false) {
                if (startDustDatePicker.getValue().isBefore(endDustDatePicker.getValue())) {
                    String startDustMonth = String.valueOf(startDustDatePicker.getValue().getMonthValue());
                    String endDustMonth = String.valueOf(endDustDatePicker.getValue().getMonthValue());
                    String startDustDay = String.valueOf(startDustDatePicker.getValue().getDayOfMonth());
                    String endDustDay = String.valueOf(endDustDatePicker.getValue().getDayOfMonth());
                    Statement stmt = connection.createStatement();
                    String updateAppSettingsQuerySQL = "UPDATE dustyplants SET NAME='" + dustyPlantName.getText() + "',STARTDUSTMONTH=" + startDustMonth + ",ENDDUSTMONTH=" + endDustMonth + ",STARTDUSTDAY=" + startDustDay + ",ENDDUSTDAY=" + endDustDay + " WHERE IDDUSTYPLANT=" + dustyPlantTableView.getSelectionModel().getSelectedItem().getId();
                    stmt.executeUpdate(updateAppSettingsQuerySQL);
                    consoleArea.appendText("Zmodyfikowano rośline pylącą o ID:" + dustyPlantTableView.getSelectionModel().getSelectedItem().getId() + "\n");
                }
            }
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie zmodyfiowano rośliny pylącej ponieważ nie zaznaczono rośliny pylącej \n");
        }
    }
    public void updateAppSetings(TextField minTemp,TextField maxTemp,TextField minWind, TextField maxWind,TextField minPressure,TextField maxPressure,TextArea consoleArea) throws SQLException {
        try {
            if(isSettigsValuesCorect(minTemp,maxTemp,minWind,maxWind,minPressure,maxPressure)) {
                Statement stmt = connection.createStatement();
                String updateAppSettingsQuerySQL = "UPDATE appsettings SET MINTEMP=" + minTemp.getText() + ",MAXTEMP=" + maxTemp.getText() + ",MINWIND=" + minWind.getText() + ",MAXWIND=" + maxWind.getText() + ",MINPRESSURE=" + minPressure.getText() + ",MAXPRESSURE=" + maxPressure.getText() + " WHERE IDSETTINGS=1";
                stmt.executeUpdate(updateAppSettingsQuerySQL);
                consoleArea.appendText("Zapisano ustawienia \n");
            }
            else{
                consoleArea.appendText("Nie zapisano ustawień ponieważ podane ustawienia są nieprawidłowe \n");
            }
        }
        catch (NullPointerException e){
            consoleArea.appendText("Nie zapisano ustawień ponieważ podane ustawienia są nieprawidłowe \n");
        }
    }

    //Metody do aktualziacji
    public void owmStationsActualization() throws IOException, SQLException {
        OwmRepositoryToActualization owmRepositoryToActualization =new OwmRepositoryToActualization(); //Pobieramy plik i stacje z niego
        ArrayList<Station>list= owmRepositoryToActualization.getOwmActualizationStationsList();
        eraseOldOwmDataBeforeActualization(); //Usuwamy stare stacje
            Statement stmt = connection.createStatement();
            for(int i=0;i<list.size();i++) {
                    String addGiosSensorsQuerySQL="INSERT INTO owmstations (IDSTATION,NAME,LON,LAT) VALUES ("+list.get(i).getId()+",'"+list.get(i).getName()+"',"+list.get(i).getLon()+","+list.get(i).getLat()+")";
                    stmt.executeUpdate(addGiosSensorsQuerySQL); //Wstawiamy nowe stacje
            }
        }
    public void GIOSActualization() throws IOException, SQLException {
        GiosRepositoryToActualization giosRepositoryToActualization=new GiosRepositoryToActualization();
        ArrayList<Station>list= giosRepositoryToActualization.getGiosActualizationStationsList();
        ArrayList<GiosSensor>list2=giosRepositoryToActualization.getGiosActualizationSensorsList();
        eraseOldGIOSDataBeforeActualization();
        Statement stmt = connection.createStatement();
        for(int i=0;i<list.size();i++) {
            String addGiosSensorsQuerySQL="INSERT INTO giosstations (IDGIOSSTATION,NAME,LON,LAT) VALUES ("+list.get(i).getId()+",'"+list.get(i).getName()+"',"+list.get(i).getLon()+","+list.get(i).getLat()+")";
            stmt.executeUpdate(addGiosSensorsQuerySQL); //Wstawiamy nowe stacje
        }
        for(int i=0;i<list2.size();i++) {
            String addGiosSensorsQuerySQL = "INSERT INTO giossensors(IDGIOSSENSOR,IDGIOSSTATION,NAME,SHORTNAME) VALUES (" + list2.get(i).getIDSensor() + "," + list2.get(i).getIDStation() + ",'" + list2.get(i).getNameOfSensor() + "','" + list2.get(i).getShortNameOfSensor() + "')";
            stmt.executeUpdate(addGiosSensorsQuerySQL);
        }
    }

    //Metody pomocnicze usuwające dane sprzed aktualizacji
    private void eraseOldOwmDataBeforeActualization() throws SQLException {
        Statement stmt = connection.createStatement();
        String addGiosSensorsQuerySQL="DELETE FROM owmstations";
        stmt.executeUpdate(addGiosSensorsQuerySQL);
    }
    private void eraseOldGIOSDataBeforeActualization() throws SQLException {
        Statement stmt = connection.createStatement();
        String deletegiossensorsQuerySQL="DELETE FROM giossensors";
        stmt.executeUpdate(deletegiossensorsQuerySQL);
        String deletegiosstationsQuerySQL="DELETE FROM giosstations";
        stmt.executeUpdate(deletegiosstationsQuerySQL);
    }

    //Inne metody pomocnicze
    private boolean isSettigsValuesCorect(TextField minTemp,TextField maxTemp,TextField minWind, TextField maxWind,TextField minPressure,TextField maxPressure) {
        double minTempValue=Double.parseDouble(minTemp.getText());
        double maxTempValue=Double.parseDouble(maxTemp.getText());
        double minWindValue=Double.parseDouble(minWind.getText());
        double maxWindValue=Double.parseDouble(maxWind.getText());
        double minPressureValue=Double.parseDouble(minPressure.getText());
        double maxPressureValue=Double.parseDouble(maxPressure.getText());
        if(minTempValue>maxTempValue||minWindValue>maxWindValue||minPressureValue>maxPressureValue){
            return false;
        }
        else {
            return true;
        }
    }
    //Metody sprawdzające czy obiekt nie dubluje się w bazie
    private boolean isUsersStationAlreadyInDataBase(UsersStationsRepository usr,TextField NAME,TextField LON, TextField LAT,TextArea consoleArea){
        for(int i=0; i<usr.getUsersStationsList().size();i++){
            if(usr.getUsersStationsList().get(i).getName().equals(NAME.getText())&&usr.getUsersStationsList().get(i).getLon()==Double.parseDouble(LON.getText())&&usr.getUsersStationsList().get(i).getLat()==Double.parseDouble(LAT.getText())){
                consoleArea.appendText("Nie dodano nowej stacji ponieważ stacja o takich parametrach juz istnieje \n");
                return true;
            }
        }
        return false;
    }
    private boolean isAlreadyDustyPlantInDataBase(DustyPlantsRepository dpr,TextField dustyPlantName, TextArea consoleArea){
        for(int i=0; i<dpr.getDustyPlantsList().size();i++){
            if(dustyPlantName.getText().equals(dpr.getDustyPlantsList().get(i).getName())){
                consoleArea.appendText("Nie dodano nowej rośliny pylącej, ponieważ taka roślina jest już w bazie danych \n");
                return true;
            }
        }
        return false;
    }
    private boolean isAlreadyOwmTranslateInDataBase(OwmTranslatorRepository otr,TextField PL, TextField ENG, TextArea consoleArea){
        for(int i=0;i<otr.getOwmtranslatesList().size();i++){
            if(PL.getText().equals(otr.getOwmtranslatesList().get(i).getPL())||ENG.getText().equals(otr.getOwmtranslatesList().get(i).getENG())){
                return true;
            }
        }
        return false;
    }
}



