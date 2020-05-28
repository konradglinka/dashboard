package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.DustyPlant;

import java.util.ArrayList;

public final class DustyPlantsRepository { //W repozytorium przechowywane są dane z tabeli dustyplants dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<DustyPlant> dustyPlantsList;

    public DustyPlantsRepository(ArrayList<DustyPlant> dustyPlantsList) {
        this.dustyPlantsList = dustyPlantsList;
    }

    public ArrayList<DustyPlant> getDustyPlantsList() {
        return dustyPlantsList;
    }

}
