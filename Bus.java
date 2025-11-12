// ============= Custom Exceptions =============
class InvalidBusException extends Exception {
    public InvalidBusException(String message) {
        super(message);
    }
}

class InvalidSeatException extends Exception {
    public InvalidSeatException(String message) {
        super(message);
    }
}

class PaymentFailedException extends Exception {
    public PaymentFailedException(String message) {
        super(message);
    }
}

// ============= Abstract Class =============
abstract class Payment {
    protected double amount;
    protected String paymentId;
    
    abstract boolean processPayment();
    abstract void generateReceipt();
    
    void displayPaymentInfo() {
        System.out.println("Payment ID: " + paymentId + ", Amount: Rs." + amount);
    }
}

// ============= Interface =============
interface Cancellable {
    void cancelBooking(int bookingId);
    double calculateRefund(int daysBeforeJourney);
    void displayCancellationPolicy();
}

// ============= Enhanced Bus Class =============
class Bus {
    private int id;
    private String busNo;
    String operator;
    String type;
    String from;
    String to;
    String time;
    private int totalSeats;
    private boolean[] seatStatus; // true = booked, false = available
    private double baseFare;
    static int busCount = 0;
    
    static {
        System.out.println("Static Block: Bus Booking System Initialized");
    }

    public Bus(int id, String busNo, String operator, String type, String from, 
               String to, String time, int totalSeats, double baseFare) {
        this.id = id;
        this.busNo = busNo;
        this.operator = operator;
        this.type = type;
        this.from = from;
        this.to = to;
        this.time = time;
        this.totalSeats = totalSeats;
        this.baseFare = baseFare;
        this.seatStatus = new boolean[totalSeats + 1]; // index 0 unused
        busCount++;
    }

    // Getters
    public int getId() { return id; }
    public String getBusNo() { return busNo; }
    public int getTotalSeats() { return totalSeats; }
    public double getBaseFare() { return baseFare; }
    
    // Setters
    public void setOperator(String operator) { this.operator = operator; }
    public void setType(String type) { this.type = type; }
    public void setTime(String time) { this.time = time; }
    
    public boolean isSeatAvailable(int seatNo) {
        if (seatNo < 1 || seatNo > totalSeats) return false;
        return !seatStatus[seatNo];
    }
    
    public void bookSeat(int seatNo) throws InvalidSeatException {
        if (seatNo < 1 || seatNo > totalSeats) {
            throw new InvalidSeatException("Invalid seat number: " + seatNo);
        }
        if (seatStatus[seatNo]) {
            throw new InvalidSeatException("Seat " + seatNo + " is already booked");
        }
        seatStatus[seatNo] = true;
    }
    
    public void cancelSeat(int seatNo) {
        if (seatNo >= 1 && seatNo <= totalSeats) {
            seatStatus[seatNo] = false;
        }
    }
    
    public int getAvailableSeats() {
        int count = 0;
        for (int i = 1; i <= totalSeats; i++) {
            if (!seatStatus[i]) count++;
        }
        return count;
    }
    
    void show() {
        System.out.println("==================================");
        System.out.println("Bus ID: " + id + ", Bus No: " + busNo);
        System.out.println("Operator: " + operator + ", Type: " + type);
        System.out.println("Route: " + from + " → " + to);
        System.out.println("Departure: " + time);
        System.out.println("Available Seats: " + getAvailableSeats() + "/" + totalSeats);
        System.out.println("Base Fare: Rs." + baseFare);
        System.out.println("==================================");
    }
}

// ============= Enhanced Booking Class =============
class Booking extends Payment implements Cancellable {
    private static int bookingCounter = 1000;
    private int bookingId;
    private String passengerName;
    private String gender;
    private int age;
    private String contact;
    private Bus bus;
    private int[] seatNumbers;
    private String bookingDate;
    private String journeyDate;
    private String paymentMethod;
    private double discount;
    private double finalAmount;
    private boolean isCancelled;
    
    public Booking(String passengerName, String gender, int age, String contact,
                   Bus bus, int[] seatNumbers, String journeyDate) {
        this.bookingId = ++bookingCounter;
        this.passengerName = passengerName;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.bus = bus;
        this.seatNumbers = seatNumbers;
        this.journeyDate = journeyDate;
        this.bookingDate = java.time.LocalDateTime.now().toString();
        this.isCancelled = false;
    }
    
    public double calculateFare() {
        double totalFare = bus.getBaseFare() * seatNumbers.length;
        
        // Apply discounts
        if (age < 12) {
            discount = totalFare * 0.25; // 25% child discount
        } else if (age >= 60) {
            discount = totalFare * 0.30; // 30% senior citizen discount
        } else {
            discount = 0;
        }
        
        finalAmount = totalFare - discount;
        this.amount = finalAmount;
        return finalAmount;
    }
    
    @Override
    public boolean processPayment() {
        System.out.println("\n=== Processing Payment ===");
        System.out.println("Total Amount: Rs." + finalAmount);
        return true;
    }
    
    @Override
    public void generateReceipt() {
        System.out.println("=============================================");
        System.out.println("         BUS TICKET - BOOKING CONFIRMED      ");
        System.out.println("=============================================");
        System.out.println("\n--- PASSENGER DETAILS ---");
        System.out.println("Name: " + passengerName);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Contact: " + contact);
        
        System.out.println("\n--- BOOKING DETAILS ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Journey Date: " + journeyDate);
        
        System.out.println("\n--- BUS DETAILS ---");
        System.out.println("Bus No: " + bus.getBusNo());
        System.out.println("Bus Type: " + bus.type);
        System.out.println("Operator: " + bus.operator);
        System.out.println("From: " + bus.from + " To: " + bus.to);
        System.out.println("Departure Time: " + bus.time);
        
        System.out.println("\n--- SEAT DETAILS ---");
        System.out.print("Seat Numbers: ");
        for (int seat : seatNumbers) {
            System.out.print(seat + " ");
        }
        System.out.println("\nTotal Tickets: " + seatNumbers.length);
        
        System.out.println("\n--- PAYMENT DETAILS ---");
        System.out.println("Base Fare per Seat: Rs." + bus.getBaseFare());
        System.out.println("Subtotal: Rs." + (bus.getBaseFare() * seatNumbers.length));
        if (discount > 0) {
            System.out.println("Discount Applied: Rs." + discount);
        }
        System.out.println("Total Payable: Rs." + finalAmount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Status: PAID");
        
        displayCancellationPolicy();
        
        System.out.println("\n--- TERMS & CONDITIONS ---");
        System.out.println("1. Please arrive 15 minutes before departure");
        System.out.println("2. Carry a valid ID proof during journey");
        System.out.println("3. No refund for no-show passengers");
        System.out.println("4. Management reserves the right to change timings");
        
        System.out.println("=============================================");
        System.out.println("     Thank you for choosing our service!     ");
        System.out.println("=============================================");
    }
    
    @Override
    public void displayCancellationPolicy() {
        System.out.println("\n--- CANCELLATION POLICY ---");
        System.out.println("• 24+ hours before departure: 90% refund");
        System.out.println("• 12-24 hours before: 50% refund");
        System.out.println("• 6-12 hours before: 25% refund");
        System.out.println("• Less than 6 hours: No refund");
    }
    
    @Override
    public double calculateRefund(int hoursBeforeJourney) {
        if (hoursBeforeJourney >= 24) return finalAmount * 0.90;
        else if (hoursBeforeJourney >= 12) return finalAmount * 0.50;
        else if (hoursBeforeJourney >= 6) return finalAmount * 0.25;
        else return 0;
    }
    
    @Override
    public void cancelBooking(int bookingId) {
        if (this.bookingId == bookingId && !isCancelled) {
            for (int seat : seatNumbers) {
                bus.cancelSeat(seat);
            }
            isCancelled = true;
            System.out.println("Booking " + bookingId + " cancelled successfully.");
        }
    }
    
    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }
    
    public int getBookingId() {
        return bookingId;
    }
    
    public boolean isCancelled() {
        return isCancelled;
    }
}