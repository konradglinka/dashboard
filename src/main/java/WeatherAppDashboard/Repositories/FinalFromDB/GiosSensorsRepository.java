package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.GiosSensor;

import java.util.ArrayList;

public final class GiosSensorsRepository { //W repozytorium przechowywane są dane z tabeli giossensors dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<GiosSensor> giosSensorsList;

    public GiosSensorsRepository(ArrayList<GiosSensor> giosSensorsList) {
        this.giosSensorsList = giosSensorsList;
    }

    public ArrayList<GiosSensor> getSensorsList() {
        return giosSensorsList;
    }
}
