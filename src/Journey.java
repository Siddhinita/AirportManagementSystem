package src;
import java.util.Objects;
/**
 * a pair of source airport and destination airport.
 */
public class Journey {

    private String source;
    private String destination;

    /**
     *
     * @param source the name of source airport which can take value from A to Z.
     * @param destination the name of destination airport which can take value from A to Z.
     * The assumption is that source and destination airport are never the same.
     */
    public Journey(String source, String destination)
    {
        this.source = source;
        this.destination = destination;
    }
    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(source, journey.source) &&
                Objects.equals(destination, journey.destination);
    }

    /**
     *
     * @return source airport
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @return destination airport
     */
    public String getDestination() {
        return destination;
    }


}
