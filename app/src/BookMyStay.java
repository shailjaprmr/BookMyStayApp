import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request (First-Come-First-Served)
 * demonstrates the use of a Queue to manage booking intent fairly.
 * @author Devanshu Lingwal
 * @version 5.0
 */

// 1. Domain Model: Represents a Guest's intent to book
class ReservationRequest {
    private String guestName;
    private String roomType;

    public ReservationRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Request [Guest: " + guestName + ", Room: " + roomType + "]";
    }
}

// 2. Booking Request Queue: Manages the arrival order
class BookingQueue {
    // Using LinkedList as the underlying implementation for the Queue interface
    private Queue<ReservationRequest> queue;

    public BookingQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds a request to the end of the line (Enqueue)
     */
    public void submitRequest(ReservationRequest request) {
        queue.add(request);
        System.out.println("Submitted: " + request);
    }

    /**
     * Displays all pending requests in order
     */
    public void showPendingRequests() {
        System.out.println("\n--- Current Booking Queue (Waiting List) ---");
        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            for (ReservationRequest req : queue) {
                System.out.println(">> " + req);
            }
        }
    }

    // This method prepares the system for the next use case (Processing)
    public ReservationRequest getNextRequest() {
        return queue.poll();
    }
}
public class BookMyStay
{
    public static void main(String[] args)
    {
        System.out.println("Book My Stay App - Version 5.0");
        System.out.println("------------------------------------------");

        BookingQueue hotelQueue = new BookingQueue();

        // 3. Simulating multiple guests submitting requests at peak time
        hotelQueue.submitRequest(new ReservationRequest("Devanshu", "Suite Room"));
        hotelQueue.submitRequest(new ReservationRequest("Aarav", "Single Room"));
        hotelQueue.submitRequest(new ReservationRequest("Isha", "Double Room"));

        // 4. Verifying that the order is preserved (FIFO)
        hotelQueue.showPendingRequests();

        System.out.println("\nNote: Requests are queued but inventory remains untouched.");
        System.out.println("Ready for Allocation Logic in the next stage.");
    }
}
