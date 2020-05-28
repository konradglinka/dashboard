package WeatherAppDashboard.Repositories.FinalForActualization;

import WeatherAppDashboard.AnotherClasses.JsonReader;
import WeatherAppDashboard.ForMapper.GIOS.Sensors.GiosSensorForMapper;
import WeatherAppDashboard.ForMapper.GIOS.Stations.GiosStationForMapper;
import WeatherAppDashboard.DbFinalObjects.GiosSensor;
import WeatherAppDashboard.DbFinalObjects.Station;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class GiosRepositoryToActualization { //Repozytorium zawierające niedyfikowalne dane stacji i sensorów GIOS pobrane w aktualizacji
    private final ArrayList<Station> giosActualizationStationsList =new ArrayList<>();
    private final ArrayList<GiosSensor> giosActualizationSensorsList =new ArrayList<>();
    public GiosRepositoryToActualization() throws IOException{
        JsonReader jsonReader=new JsonReader();
        ObjectMapper mapper=new ObjectMapper();
        List<GiosStationForMapper> list=mapper.readValue(jsonReader.readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/station/findAll"),new TypeReference<List<GiosStationForMapper>>(){
        }); //Pobieramy stacje
        for(int i=0;i<list.size();i++){
                this.giosActualizationStationsList.add(new Station(Integer.parseInt(list.get(i).getId()),list.get(i).getCity().getName(),Double.parseDouble(list.get(i).getGegrLon()),Double.parseDouble(list.get(i).getGegrLat())));
        }
        ObjectMapper mapper2=new ObjectMapper();
        for(int i=0;i<list.size();i++){
        List<GiosSensorForMapper> list2=mapper2.readValue(jsonReader.readJsonFromUrl("http://api.gios.gov.pl/pjp-api/rest/station/sensors/"+list.get(i).getId()),new TypeReference<List<GiosSensorForMapper>>(){
        }); //Pobieramy sensory
        for(int j=0;j<list2.size();j++){
            this.giosActualizationSensorsList.add(new GiosSensor(Integer.parseInt(list2.get(j).getId()),Integer.parseInt(list2.get(j).getStationId()),list2.get(j).getParam().getParamName(),list2.get(j).getParam().getParamCode(),null));
        }
        }
    }
    public ArrayList<Station> getGiosActualizationStationsList() {
        return giosActualizationStationsList;
    }
    public ArrayList<GiosSensor> getGiosActualizationSensorsList() {
        return giosActualizationSensorsList;
    }
}
