import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.TreeMap;

//kelas anak dari program 
public class datestring implements Program {
    //method yang diwariskan kelas induk

    @Override
    //date (String & Date)
    public void date(){
        Date date = new Date ();
        //simpleDateFormat d = new SimpleDateFormat ("dd-MM-yyyy")
        String dateToStr = DateFormat.getInstance(). format(date);
        System.out.print("Tanggal:" + dateToStr);

    }
    //Collection Framework (Set)
    public void set(){
        //LinkedHashSet
        LinkedHashSet<String> x = new LinkedHashSet<String>();
        x.add("Lapangan 1");
        x.add("Lapangan 2");
        x.add("Lapangan 3");
        x.add("Lapangan 4");
        x.add("Lapangan 5");

        System.out.println("\n\nLapangan Badminton : " + x);

        x.remove("Lapangan 2");
        x.remove("Lapangan 5");

        System.out.println("Lapangan Yang Tersedia :" + x);
        System.out.println();

    }
    
}