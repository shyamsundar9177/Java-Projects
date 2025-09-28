import java.util.*;
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    private double distance;
    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    public abstract double calculateFare();
}

class TempRide extends Ride {
    public TempRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * 10;
    }
}

class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return getDistance() * 20;
    }
}

public class Bikeride {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = Double.parseDouble(sc.nextLine().trim());

            if (distance <= 0) {
                System.out.println("Distance must be greater than zero.");
                return;
            }

            Ride ride;

            switch (rideType) {
                case "bike":
                    ride = new TempRide("Shyam", "BIKE1234", distance);
                    break;
                case "car":
                    ride = new CarRide("Radha", "CAR5678", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type entered.");
            }

            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.printf("Distance: %.2f km\n", ride.getDistance());
            System.out.printf("Fare: ₹%.2f\n", ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for distance.");
        } finally {
            sc.close();
        }
    }
}
