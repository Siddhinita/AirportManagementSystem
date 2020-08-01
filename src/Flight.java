package src;
import java.util.Objects;
/**
 * represents all the details pertaining to a flight.
 */
public class Flight {

    private String airlineName;
    private int maximumCapacity;
    private int flightNumber;
    private String source;
    private String destination;
    /**
     * represents the number of vacant seats in the flight. It ranges from 0 to maximumCapacity - 1.
     */
    private int seatsLeft;

    /**
     *
     * @param flightNumber a unique number to represent a flight which is uniquely identified by the source
     *                     and destination and the airline. It ranges from 0 to 99.
     * @param airlineName the name of the airline to whoch the flight belongs. Multiple flights can belong to the same
     *                    airline
     * @param maximumCapacity maximum seating capacity of the flight. It ranges from 1 to 100.
     * @param source source airport of the flight. It can take any value from A to Z.
     * @param destination destination airport of the flight It can take any value from A to Z.
     * The assumption is that source and destination airport are never the same.
     */
    public Flight(int flightNumber, String airlineName, int maximumCapacity, String source, String destination) {
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.maximumCapacity = maximumCapacity;
        this.source = source;
        this.destination = destination;
        this.seatsLeft = 0;
    }

    /**
     * Any flight with a specific source, destination, airline name and capacity is uniquely identified by the  flightNumber.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(airlineName, flight.airlineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlineName);
    }

    /**
     *
     * @param seatsLeft sets the number of vacant seats as and when a seat in this particular flight is booked.
     */
    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    /**
     *
     * @return name of airline to which flight belongs.
     */
    public String getAirlineName() {
        return airlineName;
    }

    /**
     *
     * @return maximum seating capacity of flight.
     */
    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    /**
     *
     * @return number of vacant seats in flight.
     */
    public int getSeatsLeft() {
        return seatsLeft;
    }

    /**
     *
     * @return the flight number.
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     *
     * @return name of source airport.
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @return name of destintion airport.
     */
    public String getDestination() {
        return destination;
    }

}
