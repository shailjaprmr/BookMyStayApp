// 1. Abstract Class - Defines the blueprint for all rooms
abstract class Room {
    private String roomType;
    private double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    // Encapsulation: Accessors for room details
    public String getRoomType() { return roomType; }
    public double getPrice() { return price; }

    // Abstract method to be implemented by concrete classes
    public abstract void displayFeatures();
}

// 2. Inheritance - Concrete Room Implementations
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1500.0); }
    @Override
    public void displayFeatures() {
        System.out.println("Features: Single Bed, Free WiFi, Standard Bathroom.");
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2500.0); }
    @Override
    public void displayFeatures() {
        System.out.println("Features: King Size Bed, Mini Bar, City View.");
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 5000.0); }
    @Override
    public void displayFeatures() {
        System.out.println("Features: Master Bedroom, Living Area, Private Jacuzzi.");
    }
}
public class BookMyStay
{
    public static void main(String[] args) {
        System.out.println("Book My Stay App - Version 2.0");
        System.out.println("------------------------------------------");

        // 4. Static Availability Representation
        // (Note: This is intentionally inefficient to show why we need data structures later)
        int singleRoomAvailability = 5;
        int doubleRoomAvailability = 3;
        int suiteRoomAvailability = 2;

        // 5. Polymorphism - Using the Room reference for different objects
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        displayRoomInfo(r1, singleRoomAvailability);
        displayRoomInfo(r2, doubleRoomAvailability);
        displayRoomInfo(r3, suiteRoomAvailability);
    }

    private static void displayRoomInfo(Room room, int availability) {
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Price: " + room.getPrice() + " INR");
        room.displayFeatures();
        System.out.println("Available Units: " + availability);
        System.out.println("------------------------------------------");
    }
}
