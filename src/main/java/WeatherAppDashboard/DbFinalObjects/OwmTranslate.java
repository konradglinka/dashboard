package WeatherAppDashboard.DbFinalObjects;

public final class OwmTranslate { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabeli owmtranslator dlatego jej
    // pola mogą być jedynie ustawiane przy tworzeniu obiektu
    private final int id;
    private final String PL;
    private final String ENG;

    public OwmTranslate(int id, String PL, String ENG) {
        this.id = id;
        this.PL = PL;
        this.ENG = ENG;
    }

    public int getId() {
        return id;
    }
    public String getPL() {
        return PL;
    }
    public String getENG() {
        return ENG;
    }

}
