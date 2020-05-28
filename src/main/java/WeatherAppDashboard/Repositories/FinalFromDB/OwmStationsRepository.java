package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.Station;

import java.util.ArrayList;

public final class OwmStationsRepository { //W repozytorium przechowywane są dane z tabeli owmstations dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<Station> StationsList;

    public OwmStationsRepository(ArrayList<Station> StationsList) {
        this.StationsList = StationsList;
    }

    public ArrayList<Station> getStationsList() {
        return StationsList;
    }

}
