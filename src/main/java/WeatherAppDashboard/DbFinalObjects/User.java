package WeatherAppDashboard.DbFinalObjects;

public final class User { //Obiekty tej klasy odwzorowują rekord z bazy danych z tabeli users dlatego jej
    // pola mogą być jedynie ustawiane przy tworzeniu obiektu
    private final int id;
    private final String email;
    private final String password;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
