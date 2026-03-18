import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Use Case 4: Room Search & Availability Check
 * Demonstrates read-only access and filtering logic.
 */

// --- Reusing Domain Logic from UC2 ---
abstract class Room {
    private String type;
    private double price;
    public Room(String type, double price) { this.type = type; this.price = price; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public abstract String getFeatures();
}

class SingleRoom extends Room {
    public SingleRoom() { super("Single", 1500); }
    public String getFeatures() { return "Single Bed, WiFi"; }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double", 2500); }
    public String getFeatures() { return "King Bed, Mini Bar"; }
}

// --- Search Service: The Read-Only Layer ---
class RoomSearchService {
    private Map<String, Integer> inventory;
    private List<Room> roomCatalog;

    public RoomSearchService(Map<String, Integer> inventory, List<Room> catalog) {
        this.inventory = inventory;
        this.roomCatalog = catalog;
    }

    /**
     * Filters and displays only available rooms without modifying state.
     */
    public void searchAvailableRooms() {
        System.out.println("\n--- Search Results: Available Rooms ---");
        boolean found = false;

        for (Room room : roomCatalog) {
            int count = inventory.getOrDefault(room.getType(), 0);

            // Validation Logic: Only show rooms with count > 0
            if (count > 0) {
                System.out.println("Type: " + room.getType() +
                        " | Price: " + room.getPrice() + " INR" +
                        " | Left: " + count);
                System.out.println("Details: " + room.getFeatures());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no rooms are currently available.");
        }
    }
}
public class BookMyStay
{
    public static void main(String[] args) {
        public static void main(String[] args) {
            System.out.println("Book My Stay App - Version 4.0");

            // 1. Setup Inventory (State)
            Map<String, Integer> hotelInventory = new HashMap<>();
            hotelInventory.put("Single", 5);
            hotelInventory.put("Double", 0); // Sold out

            // 2. Setup Catalog (Domain)
            List<Room> catalog = new ArrayList<>();
            catalog.add(new SingleRoom());
            catalog.add(new DoubleRoom());

            // 3. Initialize Search Service
            RoomSearchService searchService = new RoomSearchService(hotelInventory, catalog);

            // 4. Perform Search
            searchService.searchAvailableRooms();

            System.out.println("\nNote: Inventory remains unchanged after search.");
        }
    }
