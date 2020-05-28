package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.Station;

import java.util.ArrayList;

public final class UsersStationsRepository { //W repozytorium przechowywane są dane z tabeli usersstations dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<Station> usersStationsList;

    public UsersStationsRepository(ArrayList<Station> usersStationsList) {
        this.usersStationsList = usersStationsList;
    }

    public ArrayList<Station> getUsersStationsList() {
        return usersStationsList;
    }
}
