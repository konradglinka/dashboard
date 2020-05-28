package WeatherAppDashboard.Repositories.FinalFromDB;
import WeatherAppDashboard.DbFinalObjects.MeasureFromUser;

import java.util.ArrayList;

public final class MeasuresFromUserRepository { //W repozytorium przechowywane są dane z tabeli measuresfromusers dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<MeasureFromUser> mesuresFromUserList;

    public MeasuresFromUserRepository(ArrayList<MeasureFromUser> mesuresFromUserList) {
        this.mesuresFromUserList = mesuresFromUserList;
    }

    public ArrayList<MeasureFromUser> getMesuresFromUserList() {
        return mesuresFromUserList;
    }
}
