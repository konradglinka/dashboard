package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.AppSettings;

public final class AppSettingsRepository { //W repozytorium przechowywane są dane z tabeli appsettings dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final AppSettings appSettings;
    public AppSettingsRepository(AppSettings appSettings) {
        this.appSettings = appSettings;
    }
    public AppSettings getAppSettings() {
        return appSettings;
    }
}
