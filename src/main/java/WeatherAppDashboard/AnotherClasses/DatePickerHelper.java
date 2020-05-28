package WeatherAppDashboard.AnotherClasses;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

public class DatePickerHelper { //Klasa pomaga w ustawieniu daty pylenia zaznaczonej rośliny pylącej w DatePickerze
    public static void setDatePickerDate(DatePicker datePicker, int MONTH, int DAY){ //Jeżeli numer miesiąca lub dnia jest mniejszy
        //od 10 to funkcja dopisuje 0 i umożliwia to wpisanie daty do DatePickera w formacie yyyy-MM-dd
        String month;
        String day;
        String year=String.valueOf(LocalDate.now().getYear());
        if(MONTH>9){
            month=String.valueOf(MONTH);
        }
        else
        {
            month="0"+String.valueOf(MONTH);
        }
        if(DAY>9){
            day=String.valueOf(DAY);
        }
        else
        {
            day="0"+String.valueOf(DAY);
        }
        datePicker.setValue(LocalDate.parse(year+"-"+month+"-"+day)); //Wpisujemy date do DatePickera
    }


}
