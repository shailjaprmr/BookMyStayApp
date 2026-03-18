import java.util.*;

/**
 * Use Case 9: Error Handling & Validation
 * demonstrates custom exceptions and fail-fast validation logic.
 * @author Devanshu Lingwal
 * @version 9.0
 */

// 1. Custom Exceptions for Domain-Specific Errors
class InvalidRoomTypeException extends Exception {
    public InvalidRoomTypeException(String message) { super(message); }
}

class InsufficientInventoryException extends Exception {
    public InsufficientInventoryException(String message) { super(message); }
}

// 2. Validator Class to separate validation logic from business logic
class BookingValidator {
    public static void validate(String roomType, Map<String, Integer> inventory)
            throws InvalidRoomTypeException, InsufficientInventoryException {

        // Check 1: Is the room type valid (Case Sensitive)?
        if (!inventory.containsKey(roomType)) {
            throw new InvalidRoomTypeException("Error: Room type '" + roomType + "' does not exist in our system.");
        }

        // Check 2: Is there stock available?
        if (inventory.get(roomType) <= 0) {
            throw new InsufficientInventoryException("Error: No inventory left for '" + roomType + "'.");
        }
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Book My Stay App - Version 9.0");
        System.out.println("------------------------------------------");

        // Setup Initial Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 1);
        inventory.put("Suite", 0);

        // Test Scenarios with Try-Catch Blocks
        processSafeBooking("Devanshu", "Single", inventory); // Should succeed
        processSafeBooking("Aarav", "Deluxe", inventory);    // Should fail (Invalid Type)
        processSafeBooking("Isha", "Suite", inventory);      // Should fail (No Stock)
        processSafeBooking("Rohan", "single", inventory);    // Should fail (Case Sensitive check)
    }

    public static void processSafeBooking(String guest, String type, Map<String, Integer> inv) {
        try {
            System.out.println("Attempting to book '" + type + "' for " + guest + "...");

            // Fail-Fast: Validate before any state change
            BookingValidator.validate(type, inv);

            // If we reach here, validation passed
            inv.put(type, inv.get(type) - 1);
            System.out.println("SUCCESS: Booking confirmed for " + guest);

        } catch (InvalidRoomTypeException | InsufficientInventoryException e) {
            // Graceful Failure Handling
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            System.out.println("------------------------------------------");
        }
    }
}