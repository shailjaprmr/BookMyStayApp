import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 * Maintains a chronological audit trail of confirmed reservations.
 */

// 1. Data Model: Represents a completed, immutable transaction record
class ConfirmedBooking {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double totalCost;
    private long timestamp;

    public ConfirmedBooking(String resId, String name, String type, double cost) {
        this.reservationId = resId;
        this.guestName = name;
        this.roomType = type;
        this.totalCost = cost;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return String.format("[%tT] ID: %s | Guest: %-10s | Room: %-10s | Total: %.2f INR",
                timestamp, reservationId, guestName, roomType, totalCost);
    }
}

// 2. Booking History Service: Manages the audit trail
class BookingHistoryService {
    // List preserves the chronological order of confirmation
    private List<ConfirmedBooking> history;

    public BookingHistoryService() {
        this.history = new ArrayList<>();
    }

    public void recordBooking(ConfirmedBooking booking) {
        history.add(booking);
    }

    // 3. Reporting Service: Generates summaries without modifying the data
    public void generateSummaryReport() {
        System.out.println("\n--- OFFICIAL BOOKING HISTORY REPORT ---");
        if (history.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        double totalRevenue = 0;
        for (ConfirmedBooking record : history) {
            System.out.println(record);
            // In a real app, cost might be fetched from a field, here we sum for the report
            // totalRevenue += record.getTotalCost(); // Simplified for this UC
        }
        System.out.println("---------------------------------------");
        System.out.println("Total Bookings Processed: " + history.size());
    }
}

public class BookMyStay {
    public static void main(String[] args) {
        System.out.println("Book My Stay App - Version 8.0");
        System.out.println("------------------------------------------");

        BookingHistoryService historyService = new BookingHistoryService();

        // Simulating the arrival of confirmed bookings from UC6 and UC7
        System.out.println("Recording confirmed transactions...");

        historyService.recordBooking(new ConfirmedBooking("S-101", "Devanshu", "Suite", 5800.0));
        historyService.recordBooking(new ConfirmedBooking("D-102", "Aarav", "Double", 2500.0));
        historyService.recordBooking(new ConfirmedBooking("S-103", "Isha", "Single", 1800.0));

        // Admin requests the report
        historyService.generateSummaryReport();

        System.out.println("\nNote: Data is stored in-memory, preserving insertion order.");
    }
}