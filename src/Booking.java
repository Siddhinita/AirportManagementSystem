package src;
import java.io.FileWriter;
import java.io.IOException;

/**
 * represents the passenger and flight details of a ticket booking.
 */
public class Booking {

    private String PNR;
    private Passenger passenger;
    private Journey journey;
    private int flighNumber;
    private String airlineName;
    private int seatNumber;

    /**
     *
     * @param PNR unique identifier of 6 characters for a booking. Every successful booking made by a passenger has a unique PNR.
     * @param passenger contains the information of the passenger for whom the booking is done.
     * @param journey contains the source airport and destination airport.
     * @param flighNumber unique number given to every flight. It represents the flight which is booked. It ranges from 0 to 99.
     * @param airlineName name of the airline to which the flight booked belongs.
     * @param seatNumber the seat number in the particular flight. It ranges from 0 to (maximum capacity-1) of flight
     */
    public Booking(String PNR, Passenger passenger, Journey journey, int flighNumber, String airlineName, int seatNumber) {
        this.PNR = PNR;
        this.passenger = passenger;
        this.journey = journey;
        this.flighNumber = flighNumber;
        this.airlineName = airlineName;
        this.seatNumber = seatNumber;
    }

    /**
     * prints the ticket
     * @param dirpath the path to the directory where you wish to save the ticket.
     */
    public void printTicket(String dirpath) {
        try{
            FileWriter fileWriter = new FileWriter(dirpath + "/flightTicket" + PNR + ".txt");
            fileWriter.write("----------------------------------\n");
            fileWriter.write("             TICKET               \n");
            fileWriter.write("PNR: " + PNR + "\n");
            fileWriter.write("AIRLINE_NAME: " + airlineName + " FLIGHT_NUMBER: "+ flighNumber + "\n");
            fileWriter.write("FROM " + journey.getSource() + " TO " + journey.getDestination() + "\n");
            fileWriter.write("NAME: " + passenger.getPassengerFirstName() + " " + passenger.getPassengerLastName() + "\n");
            fileWriter.write("AGE: " + passenger.getPassengerAge() + " GENDER: " + (passenger.getPassengerGender().equals("m")?"male":"female") + "\n");
            fileWriter.write("SEAT_NUMBER: " + seatNumber + "\n");
            fileWriter.write("-----------------------------------");
            fileWriter.close();
        }
        catch(IOException e) {
            System.out.println(dirpath);
            e.printStackTrace();
        }
    }

    /**
     *
     * @return PNR
     */
    public String getPNR() {
        return PNR;
    }

    /**
     *
     * @return object representing passenger
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     *
     * @return object representing journey
     */
    public Journey getJourney() {
        return journey;
    }

    /**
     *
     * @return flight number
     */
    public int getFlighNumber() {
        return flighNumber;
    }

    /**
     *
     * @return name of the airline
     */
    public String getAirlineName() {
        return airlineName;
    }
}
