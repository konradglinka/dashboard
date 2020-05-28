package WeatherAppDashboard.Run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader myLoader= new FXMLLoader();
        myLoader.setLocation(this.getClass().getResource("/DashboardView.fxml"));
        Parent root = myLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.setTitle("Weather APP Dashboard");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
