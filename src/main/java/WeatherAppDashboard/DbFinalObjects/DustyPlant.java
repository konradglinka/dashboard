package WeatherAppDashboard.DbFinalObjects;

public final class DustyPlant { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabeli dustyplants dlatego jej
    // pola mogą być jedynie ustawiane przy tworzeniu obiektu
    private final int id;
    private final String name;
    private final int startDustMonth;
    private final int endDustMonth;
    private final int startDustDay;
    private final int endDustDay;

    public DustyPlant(int id,String name, int startDustMonth, int endDustMonth, int startDustDay, int endDustDay) {
        this.id=id;
        this.name = name;
        this.startDustMonth = startDustMonth;
        this.endDustMonth = endDustMonth;
        this.startDustDay = startDustDay;
        this.endDustDay = endDustDay;
    }

    public String getName() {
        return name;
    }

    public int getStartDustMonth() {
        return startDustMonth;
    }

    public int getStartDustDay(){return startDustDay;}

    public int getEndDustMonth() {
        return endDustMonth;
    }

    public int getEndDustDay() {return endDustDay;}
    public int getId() {
        return id;
    }


}
