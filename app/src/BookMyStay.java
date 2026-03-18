import java.util.*;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 * Implements unique room ID generation and inventory synchronization.
 * @author Devanshu Lingwal
 * @version 6.0
 */

class BookingService {
    // Maps Room Type -> Set of Unique Occupied Room IDs
    private Map<String, Set<String>> allocatedRooms;
    private Map<String, Integer> inventory;
    private int idCounter = 101; // Simple counter for unique ID generation

    public BookingService(Map<String, Integer> inventory) {
        this.inventory = inventory;
        this.allocatedRooms = new HashMap<>();
        // Initialize sets for each room type
        for (String type : inventory.keySet()) {
            allocatedRooms.put(type, new HashSet<>());
        }
    }

    /**
     * Processes a single request: Dequeue -> Check -> Allocate -> Decrement
     */
    public void processBooking(String guestName, String roomType) {
        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            // 1. Generate Unique Room ID
            String roomId = roomType.substring(0, 1).toUpperCase() + "-" + idCounter++;

            // 2. Uniqueness Enforcement using Set
            Set<String> assignedSet = allocatedRooms.get(roomType);
            if (!assignedSet.contains(roomId)) {
                assignedSet.add(roomId);

                // 3. Atomic Logical Operation: Decrement Inventory
                inventory.put(roomType, available - 1);

                System.out.println("CONFIRMED: Guest " + guestName +
                        " allocated Room [" + roomId + "] (" + roomType + ")");
            }
        } else {
            System.out.println("REJECTED: No " + roomType + " available for " + guestName);
        }
    }

    public void displayFinalState() {
        System.out.println("\n--- Final Allocation Report ---");
        allocatedRooms.forEach((type, rooms) -> {
            System.out.println(type + " Occupied IDs: " + rooms);
            System.out.println(type + " Remaining Inventory: " + inventory.get(type));
        });
    }
}

public class BookMyStay
{
    public static void main(String[] args) {
        System.out.println("Book My Stay App - Version 6.0");
        System.out.println("------------------------------------------");

        // Setup Initial Inventory
        Map<String, Integer> hotelInventory = new HashMap<>();
        hotelInventory.put("Single", 2);
        hotelInventory.put("Suite", 1);

        BookingService service = new BookingService(hotelInventory);

        // Simulate processing requests from the UC5 Queue
        service.processBooking("Devanshu", "Suite");
        service.processBooking("Aarav", "Single");
        service.processBooking("Isha", "Suite"); // Should be rejected (Sold out)
        service.processBooking("Rohan", "Single");

        service.displayFinalState();
    }
}