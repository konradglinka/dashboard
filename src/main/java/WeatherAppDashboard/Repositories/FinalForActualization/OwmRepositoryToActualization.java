package WeatherAppDashboard.Repositories.FinalForActualization;

import WeatherAppDashboard.DbFinalObjects.Station;
import WeatherAppDashboard.ForMapper.OWM.OwmStationForMapper;
import WeatherAppDashboard.AnotherClasses.OwmJsonDownloader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class OwmRepositoryToActualization {  //Repozytorium zawierające niedyfikowalne dane stacji OWM pobrane w aktualizacji
    private final ArrayList<Station> owmActualizationStationsList =new ArrayList<>();
    public OwmRepositoryToActualization() throws IOException {
        OwmJsonDownloader owmJsonDownloader=new OwmJsonDownloader();
        owmJsonDownloader.downloadOWMStationsFile(); //Pobieramy plik z danymi
        owmJsonDownloader.decompresOwmStationsFile();
        ObjectMapper mapper=new ObjectMapper();
        List<OwmStationForMapper>list=mapper.readValue(new File("city.list.json"),new TypeReference<List<OwmStationForMapper>>(){
        }); //Pobieramy wszystkie stacje OWM
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCountry().equals("PL")){ //Dodajemy tylko te stacje OWM które dotyczą Polski
                this.owmActualizationStationsList.add(new Station(Integer.parseInt(list.get(i).getId()),list.get(i).getName(),Double.parseDouble(list.get(i).getCoord().getLon()),Double.parseDouble(list.get(i).getCoord().getLat())));
            }
        }
        owmJsonDownloader.removeFilesAfterActualization(); //Usuwamy plik z danymi
    }
    public ArrayList<Station> getOwmActualizationStationsList() {
        return owmActualizationStationsList;
    }


}

