package WeatherAppDashboard.AnotherClasses;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader { //Klasa umożliwia przeczytanie JSONA z dowolnego adresu URL
    public String readJsonFromUrl(String url) throws IOException{
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
