package src;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The main driving class of the Airport.
 * Performs the following functions-
 * 1) Loads the flight information from a CSV file
 * 2) Enables the passengers to book tickets from a desired source to destination.
 * 3) Enables the security personnel to verify the passengers who have come to the airport and update the records
 *    of the flights departing from this airport.
 * 4) Enables the flight staff to generate the record for a flight by providing the flight number.
 * 5) Generates the records of all flights departing from the airport.
 */
public class Airport {
    /**
     * the name of the airport for which the flight management system is built.
     */
    public static final String AIRPORT_NAME = "A";
    /**
     * flightByNumber maps the flight to the flight number which is a unique identifier for a flight.
     * This maintains information of all the flights and search and insert complexity is average case O(1).
     */
    public static HashMap<Integer, Flight> flightByNumber;
    /**
     * flights maps the Journey(source-destination pair) to flights.
     * This is used to enable booking for passengers and will make available all flights
     * that travel by the passenger's desired route in O(1) time.
     */
    public static HashMap<Journey, HashSet<Flight>> flights;
    /**
     * bookings maps the PNR to a booking.
     * Security personnel can access the booking made by a passenger from the PNR number on his ticket in O(1) time
     * and then verify all the details of the passenger with the details recorded while booking the ticket.
     */
    public static HashMap<String, Booking> bookings;
    /**
     * flightRecords maps the airline Number to its respective record.
     * This map only contains records for those airlines that depart from this particular airport.
     */
    public static HashMap<Integer, FlightRecord> flightRecords;

    /**
     *
     * @param filename the path to the CSV file that contains information about a flight in the following format:
     *                 (flightNumber,airlineName,maximumCapacity,source,destination)
     * We assume that the input provided is in the correct format.
     *
     */
    public static void loadFlightInfo(String filename) {
        flights = new HashMap<Journey, HashSet<Flight>>();
        flightByNumber = new HashMap<Integer, Flight>();
        flightRecords = new HashMap<Integer, FlightRecord>();
        int noOfFields = 5;
        int flightNumber;
        String airlineName;
        int maximumCapacity;
        String source;
        String destination;
        Journey journey;
        Flight flight;
        ArrayList<String[]> records = Util.readRecords(filename, noOfFields);
        for (String[] nextRecord : records) {
            flightNumber = Integer.parseInt(nextRecord[0]);
            airlineName = nextRecord[1];
            maximumCapacity = Integer.parseInt(nextRecord[2]);
            source = nextRecord[3];
            destination = nextRecord[4];
            journey = new Journey(source, destination);
            flight = new Flight(flightNumber, airlineName, maximumCapacity, source, destination);
            /**
             * A flight record is added only if the source is this airport.
             */
            if (source.equals(AIRPORT_NAME) && !(flightRecords.containsKey(flightNumber))) {
                flightRecords.put(flightNumber, new FlightRecord(flight));
            }
            if (flights.containsKey(journey)) {
                flights.get(journey).add(flight);
                flightByNumber.put(flightNumber, flight);
            } else {
                HashSet<Flight> flightSet = new HashSet<>();
                flightSet.add(flight);
                flights.put(journey, flightSet);
            }
        }
    }

    /**
     *
     * @param filename the path to the CSV file that contains information about a passenger and his desired journey in the following format:
     *                       (firstName,lastName,age,gender,source,destination)
     * We assume that the input provided is in the correct format.
     * The method finds all the flights which have the passenger's desired source and destination and the
     * passenger is assigned the first flight with vacant seats while iterating through the list of flights.
     * The passenger can book any flight within the list irrespective of whether the source airport is this particular airport or not.
     * The successful bookings are logged to the standard output.
     *
     */
    public static void bookTickets(String filename) {

        bookings = new HashMap<String, Booking>();
        int noOfFields = 6;
        String passengerFirstName;
        String passengerLastName;
        int passengerAge;
        String passengerGender;
        String source;
        String destination;
        String PNR;
        Passenger passenger;
        Journey journey;
        ArrayList<String[]> records = Util.readRecords(filename, noOfFields);
        for (String[] nextRecord : records) {
            passengerFirstName = nextRecord[0];
            passengerLastName = nextRecord[1];
            passengerAge = Integer.parseInt(nextRecord[2]);
            passengerGender = nextRecord[3];
            source = nextRecord[4];
            destination = nextRecord[5];
            journey = new Journey(source, destination);
            if (flights.containsKey(journey)) {
                Iterator<Flight> itr = flights.get(journey).iterator();
                while (itr.hasNext()) {
                    Flight flightToBook = (Flight) itr.next();
                    if (flightToBook.getSeatsLeft() < flightToBook.getMaximumCapacity()) {
                        flightToBook.setSeatsLeft(flightToBook.getSeatsLeft() + 1);
                        passenger = new Passenger(passengerFirstName, passengerLastName, passengerAge, passengerGender);
                        PNR = Util.generatePNR(Integer.toString(flightToBook.getFlightNumber())
                                ,Integer.toString(flightToBook.getSeatsLeft()-1),source,destination);

                        bookings.put(PNR, new Booking(PNR, passenger, journey, flightToBook.getFlightNumber(),
                                flightToBook.getAirlineName(), flightToBook.getSeatsLeft()-1));
                        System.out.println("Booking for "+ passengerFirstName + " " + passengerLastName +" successful with PNR "+ PNR );
                        break;
                    }

                }
            }
        }
    }

    /**
     *
     * @param filename the path to the CSV file that contains information about passengers who have come to the airport along with their ticket:
     *                 (PNR,firstName,lastName,age,gender)
     * We assume that the input provided is in the correct format.
     * The method enables security checkin. It verifies if the current airport is this particular airport and then it
     * verifies the passenger information with the booking details.
     * The passengers that pass the checkin are added to their respective flight's record.
     */
    public static void checkPassenger(String filename) {

        Passenger passenger;
        String PNR;
        String passengerFirstName;
        String passengerLastName;
        int passengerAge;
        String passengerGender;
        int noOfFields = 5;
        ArrayList<String[]> records = Util.readRecords(filename, noOfFields);
        for (String[] nextRecord : records) {
            PNR = nextRecord[0];
            passengerFirstName = nextRecord[1];
            passengerLastName = nextRecord[2];
            passengerAge = Integer.parseInt(nextRecord[3]);
            passengerGender = nextRecord[4];
            passenger = new Passenger(passengerFirstName, passengerLastName, passengerAge, passengerGender);
            if (bookings.containsKey(PNR) && bookings.get(PNR).getPassenger().equals(passenger) && bookings.get(PNR).getJourney().getSource().equals(AIRPORT_NAME)) {
                int flightNumber = bookings.get(PNR).getFlighNumber();
                if (flightRecords.containsKey(flightNumber)) {
                    flightRecords.get(flightNumber).addPassenger(PNR);
                }
            }
        }
    }

    /**
     *
     * @param flightNumber the flight number for which the flight staff wishes to generate the record.
     * @param dirpath This method enables to generate the record for a particular flight.
     *               The output is recorded in the output directory specified by the dirpath.
     */
    public static void generateFlightReport(int flightNumber,String dirpath)
    {
        if(!flightRecords.containsKey(flightNumber)) {
            System.out.println("The record for flight number "+ flightNumber+ " doesnot exist.");
        }
        else {
            FlightRecord flightRecord = flightRecords.get(flightNumber);
            String filename = dirpath + "flight_report_" + flightNumber + flightRecord.getFlight().getSource()+"-->"+flightRecord.getFlight().getDestination() +".csv";
            String[] header = {"FirstName","LastName","PNR"};
            ArrayList<String[]> writeRecords = new ArrayList<>();
            writeRecords.add(header);

            for(String PNR: flightRecord.getCheckinPassengers()) {
                String[] nextRecord = new String[3];
                nextRecord[0] = bookings.get(PNR).getPassenger().getPassengerFirstName();
                nextRecord[1] = bookings.get(PNR).getPassenger().getPassengerLastName();
                nextRecord[2] = PNR;
                writeRecords.add(nextRecord);
            }
            Util.writeRecords(writeRecords,filename);
        }
    }

    /**
     *
     * @param dirpath generates records of all flights departing from this airport and saves them in the directory
     *                specified by dirpath.
     */
    public static void generateAllReports(String dirpath) {
        for(Integer flightNumber: flightRecords.keySet()) {
            generateFlightReport(flightNumber,dirpath);
        }
    }

}
