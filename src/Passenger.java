package src;
import java.util.Objects;
/**
 * represents a passenger with his/her personal information which is later verified by the security personel.
 */
public class Passenger {

    private String passengerFirstName;
    private String passengerLastName;
    private int passengerAge;
    private String passengerGender;

    /**
     *
     * @param passengerFirstName first name of passenger
     * @param passengerLastName last name of passenger
     * @param passengerAge age of passenger
     * @param passengerGender gender of passenger ("m","f")
     */
    public Passenger(String passengerFirstName, String passengerLastName, int passengerAge, String passengerGender) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.passengerAge = passengerAge;
        this.passengerGender = passengerGender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return passengerAge == passenger.passengerAge &&
                Objects.equals(passengerFirstName, passenger.passengerFirstName) &&
                Objects.equals(passengerLastName, passenger.passengerLastName) &&
                Objects.equals(passengerGender, passenger.passengerGender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerFirstName, passengerLastName, passengerAge, passengerGender);
    }

    /**
     *
     * @return age of passenger
     */
    public int getPassengerAge() {
        return passengerAge;
    }

    /**
     *
     * @return gender of passenger
     */
    public String getPassengerGender() {
        return passengerGender;
    }

    /**
     *
     * @return first name of passenger
     */
    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    /**
     *
     * @return last name of passenger
     */
    public String getPassengerLastName() {
        return passengerLastName;
    }
}
