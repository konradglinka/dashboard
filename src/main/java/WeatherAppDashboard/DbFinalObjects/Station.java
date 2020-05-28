package WeatherAppDashboard.DbFinalObjects;

public final class Station { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabel giosstations/userstations/owmstations
    // dlatego jej pola mogą być jedynie ustawiane przy tworzeniu obiektu
    private final int id;
    private final String name;
    private final double lon;
    private final double lat;

    public Station(int id, String name, double lon, double lat) {
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getLon() {
        return lon;
    }
    public double getLat() {
        return lat;
    }


}
