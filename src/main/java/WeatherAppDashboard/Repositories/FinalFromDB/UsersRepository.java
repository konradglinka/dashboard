package WeatherAppDashboard.Repositories.FinalFromDB;

import WeatherAppDashboard.DbFinalObjects.User;

import java.util.ArrayList;

public final class UsersRepository { //W repozytorium przechowywane są dane z tabeli users dlatego nie można ich modyfikkować
    //Nowe dane w tabeli oznaczają konieczność utworzenia repozytorium na nowo
    private final ArrayList<User> usersList;

    public UsersRepository(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }


}
