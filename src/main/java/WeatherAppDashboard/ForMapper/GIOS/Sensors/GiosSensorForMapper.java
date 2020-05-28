package WeatherAppDashboard.ForMapper.GIOS.Sensors;

public class GiosSensorForMapper {
        private Param param;

        private String id;

        private String stationId;

        public Param getParam ()
        {
            return param;
        }

        public void setParam (Param param)
        {
            this.param = param;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getStationId ()
        {
            return stationId;
        }

        public void setStationId (String stationId)
        {
            this.stationId = stationId;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [param = "+param+", id = "+id+", stationId = "+stationId+"]";
        }
}
