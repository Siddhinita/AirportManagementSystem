package src;
import java.util.ArrayList;
/**
 * It represents the flight and the PNR of the passengers that have been cleared by the security personnel for the flight.
 * This record is used by the flight staff to generate list of passengers according to the flight.
 */
public class FlightRecord  {
    private Flight flight;
    /**
     * A list of PNR of passengers who have successfully passed through security checkin.
     */
    private ArrayList<String> checkinPassengers;

    /**
     *
     * @param flight the flight to which the record belongs. The flight contains all details of the flight.
     */
    public FlightRecord(Flight flight)
    {
        checkinPassengers = new ArrayList<>();
        this.flight = flight;
    }

    /**
     *
     * @param PNR When a passenger is cleared by security, the PNR on his ticket(booking) is added to the list.
     */
    public void addPassenger(String PNR)
    {
        checkinPassengers.add(PNR);
    }

    /**
     *
     * @return number of passengers who have done security checkin.
     */
    public int noOfCheckinPassengers()
    {
        return checkinPassengers.size();
    }

    /**
     *
     * @return the flight object representing the details of the flight associated with this record.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     *
     * @return the list of PNR of passengers who have done security checkin.
     */
    public ArrayList<String> getCheckinPassengers() {
        return checkinPassengers;
    }



}
