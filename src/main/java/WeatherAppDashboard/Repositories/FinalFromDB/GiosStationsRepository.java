package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.Station;

import java.util.ArrayList;

public final class GiosStationsRepository { //W repozytorium przechowywane są dane z tabeli giosstations dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private ArrayList<Station> giosStationsList;

    public GiosStationsRepository(ArrayList<Station> giosStationsList) {
        this.giosStationsList = giosStationsList;
    }

    public ArrayList<Station> getGiosStationsList() {
        return giosStationsList;
    }

}
