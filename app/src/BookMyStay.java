import java.util.*;

/**
 * Use Case 7: Add-On Service Selection
 * Demonstrates a One-to-Many mapping between Reservations and Services.
 */

// 1. Service Domain Model
class AddOnService {
    private String name;
    private double cost;

    public AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }
    public double getCost() { return cost; }

    @Override
    public String toString() {
        return name + " (" + cost + " INR)";
    }
}

// 2. Service Manager: Handles the association logic
class AddOnServiceManager {
    // Map: Reservation ID -> List of selected services
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        this.reservationServices = new HashMap<>();
    }

    /**
     * Attaches a service to a specific reservation ID.
     */
    public void addServiceToReservation(String reservationId, AddOnService service) {
        // computeIfAbsent ensures a list exists for the ID before adding
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Added " + service.getName() + " to Reservation: " + reservationId);
    }

    /**
     * Calculates total additional cost for a reservation.
     */
    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        if (services == null) return 0.0;

        return services.stream().mapToDouble(AddOnService::getCost).sum();
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.get(reservationId);
        System.out.println("Services for " + reservationId + ": " +
                (services != null ? services : "None"));
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Book My Stay App - Version 7.0");
        System.out.println("------------------------------------------");

        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Define available services
        AddOnService breakfast = new AddOnService("Buffet Breakfast", 500.0);
        AddOnService spa = new AddOnService("Spa Treatment", 2000.0);
        AddOnService wifi = new AddOnService("Premium WiFi", 300.0);

        // Simulation: Assigning services to unique Reservation IDs from UC6
        String resId1 = "S-101";
        String resId2 = "D-102";

        serviceManager.addServiceToReservation(resId1, breakfast);
        serviceManager.addServiceToReservation(resId1, wifi);
        serviceManager.addServiceToReservation(resId2, spa);

        System.out.println("------------------------------------------");

        // Output results
        serviceManager.displayServices(resId1);
        System.out.println("Total Add-on Cost for " + resId1 + ": " +
                serviceManager.calculateTotalServiceCost(resId1) + " INR");

        System.out.println("\n");

        serviceManager.displayServices(resId2);
        System.out.println("Total Add-on Cost for " + resId2 + ": " +
                serviceManager.calculateTotalServiceCost(resId2) + " INR");
    }
}