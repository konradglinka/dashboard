package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.OwmTranslate;

import java.util.ArrayList;

public final class OwmTranslatorRepository { //W repozytorium przechowywane są dane z tabeli owmtranslator dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<OwmTranslate> owmtranslatesList;

    public OwmTranslatorRepository(ArrayList<OwmTranslate> owmtranslatesList) {
        this.owmtranslatesList = owmtranslatesList;
    }

    public ArrayList<OwmTranslate> getOwmtranslatesList() {
        return owmtranslatesList;
    }


}
