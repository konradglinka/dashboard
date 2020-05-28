package WeatherAppDashboard.AnotherClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class OwmJsonDownloader { //Klasa umożliwia pobranie pliku JSON zawierającego liste stacji OWM
    public void downloadOWMStationsFile() throws IOException { //Funkcja pobiera archiwum zawierające plik
        URL url = new URL("http://bulk.openweathermap.org/sample/city.list.json.gz");
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("city.list.json.gz");
        fileOutputStream.getChannel().transferFrom(readableByteChannel,0, Long.MAX_VALUE);
        fileOutputStream.close();
        readableByteChannel.close();
    }
    public void decompresOwmStationsFile() { //Funkcja dekompresuje pobrane archiwum
        byte[] buffer = new byte[1024];
        try {
            FileInputStream fileIn = new FileInputStream("city.list.json.gz");
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
            FileOutputStream fileOutputStream = new FileOutputStream("city.list.json");
            int bytes_read;
            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, bytes_read);
            }
            gZIPInputStream.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void removeFilesAfterActualization() throws IOException { //Funkcja usuwa pliki po aktualizacji
        Files.deleteIfExists(Paths.get("city.list.json.gz"));
        Files.deleteIfExists(Paths.get("city.list.json"));
    }
}
