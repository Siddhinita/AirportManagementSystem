
import java.util.*;
import src.*;
import static org.junit.Assert.*;

public class AirportTest {
    String IN = "./test_resources/";
    String OUT = "./test_output/";

    @org.junit.Test
    public void loadFlightInfo() {
        String filename = IN + "flight_info.csv";
        Airport.loadFlightInfo(filename);
        assert Airport.flights.size() == 8;
    }

    @org.junit.Test
    public void bookTickets() {
        String filename = IN + "booking_info.csv";
        loadFlightInfo();
        Airport.bookTickets(filename);
        Set<String> PNRset = Airport.bookings.keySet();
        if(PNRset.size()>0)
        {
            Iterator<String> itr = PNRset.iterator();
            Airport.bookings.get((String)itr.next()).printTicket(OUT);
        }
        assert Airport.bookings.size() == 10;
    }

    @org.junit.Test
    public void checkPassenger() {
        String filename = "/home/sid/check_in.csv";
        loadFlightInfo();
        bookTickets();
        Iterator itr = Airport.bookings.entrySet().iterator();
        ArrayList<String[]> writeCheckinInfo = new ArrayList<>();
        int i=0;
        String[] header={"PNR","firstName","lastName","age","gender"};
        writeCheckinInfo.add(header);
        while(itr.hasNext())
        {
            i++;
            String[] checkinInfo = new String[5];
            Map.Entry pair = (Map.Entry)itr.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            String PNR = (String) pair.getKey();
            Booking booking  = (Booking) pair.getValue();
            checkinInfo[0] = PNR;
            checkinInfo[1] = booking.getPassenger().getPassengerFirstName();
            checkinInfo[2] = booking.getPassenger().getPassengerLastName();
            checkinInfo[3] = Integer.toString(booking.getPassenger().getPassengerAge());
            checkinInfo[4] = booking.getPassenger().getPassengerGender();
            writeCheckinInfo.add(checkinInfo);
        }
        Util.writeRecords(writeCheckinInfo,filename);
        Airport.checkPassenger(filename);


    }

    @org.junit.Test
    public void generateFlightReport() {
        loadFlightInfo();
        bookTickets();
        Airport.generateFlightReport(6,OUT);
    }

    @org.junit.Test
    public void generateAllReports() {
        loadFlightInfo();
        bookTickets();
        checkPassenger();
        Airport.generateAllReports(OUT);

    }
}
