import java.util.HashMap;
import java.util.Map;

// 1. Encapsulation: Dedicated class for Inventory Logic
class RoomInventory {
    // HashMap provides O(1) average lookup and update performance
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    // 2. Controlled Updates: Method to register/add rooms to the system
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // 3. Retrieval: Check current availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // 4. State Management: Update count after a booking or cancellation
    public void updateAvailability(String roomType, int delta) {
        int currentCount = getAvailability(roomType);
        inventory.put(roomType, currentCount + delta);
    }

    public void displayInventory() {
        System.out.println("Current Hotel Inventory Status:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " available");
        }
    }
}
public class BookMyStay
{
    public static void main(String[] args) {
        System.out.println("Book My Stay App - Version 3.0");
        System.out.println("------------------------------------------");

        // Initialize the centralized inventory
        RoomInventory hotelInventory = new RoomInventory();

        // Registering Room Types (Setup phase)
        hotelInventory.addRoomType("Single Room", 10);
        hotelInventory.addRoomType("Double Room", 5);
        hotelInventory.addRoomType("Suite Room", 2);

        // Display Initial State
        hotelInventory.displayInventory();
        System.out.println("------------------------------------------");

        // Simulate a booking (Update state)
        System.out.println("Processing Booking for 1 Double Room...");
        hotelInventory.updateAvailability("Double Room", -1);

        // Verify state consistency
        hotelInventory.displayInventory();
        System.out.println("------------------------------------------");

        System.out.println("Final Check: Suite availability is " +
                hotelInventory.getAvailability("Suite Room"));
    }
    }
