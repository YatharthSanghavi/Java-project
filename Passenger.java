import java.util.Scanner;

class Passenger extends User {
    private String phone;
    private Booking[] myBookings;
    private int myBookingsCount;
    Scanner sc = new Scanner(System.in);
    
    public Passenger() {
        myBookings = new Booking[0]; // No initial capacity
        myBookingsCount = 0;
    }

    public Passenger(int id, String name, String email, String phone) {
        super(id, name, email);
        this.phone = phone;
        myBookings = new Booking[0]; // No initial capacity
        myBookingsCount = 0;
    }
    
    void searchBus() {
        System.out.print("Enter From Location: ");
        String from = sc.next();
        System.out.print("Enter To Location: ");
        String to = sc.next();
        System.out.println("\nSearching buses from " + from + " to " + to + "...\n");
        
        boolean found = false;
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) && 
                AdminDashboard.buses[i].to.equalsIgnoreCase(to)) {
                AdminDashboard.buses[i].show();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("================================");
            System.out.println("No buses found for the given route.");
            System.out.println("================================");
        }
    }

    void showBus() {
        if (AdminDashboard.busCount == 0) {
            System.out.println("================================");
            System.out.println("No buses available.");
            System.out.println("================================");
            return;
        }
        System.out.println("\n=== AVAILABLE BUSES ===\n");
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            AdminDashboard.buses[i].show();
        }
    }

    void busType() {
        System.out.print("Search by bus type (AC/Non-AC/Sleeper/Express): ");
        String type = sc.next();
        System.out.println();
        
        boolean found = false;
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            if (AdminDashboard.buses[i].type.equalsIgnoreCase(type)) {
                AdminDashboard.buses[i].show();
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("================================");
            System.out.println("No buses found for type: " + type);
            System.out.println("================================");
        }
    }

    void bookTicket() {
        try {
            System.out.println("\n=== BOOK TICKET ===");
            
            // Show available buses
            System.out.print("Enter From Location: ");
            String from = sc.next();
            System.out.print("Enter To Location: ");
            String to = sc.next();
            System.out.println("\nSearching buses from " + from + " to " + to + "...\n");
            
            boolean found = false;
            for (int i = 0; i < AdminDashboard.busCount; i++) {
                if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) && 
                    AdminDashboard.buses[i].to.equalsIgnoreCase(to)) {
                    AdminDashboard.buses[i].show();
                    found = true;
                }
            }
            
            if (!found) {
                System.out.println("================================");
                System.out.println("No buses found for the given route.");
                System.out.println("================================");

                throw new InvalidBusException("NO BUSES FOUND");
            }
            
            // Get bus ID
            System.out.print("\nEnter Bus ID: ");
            int busId = sc.nextInt();
            
            Bus selectedBus = null;
            for (int i = 0; i < AdminDashboard.busCount; i++) {
                if (AdminDashboard.buses[i].getId() == busId) {
                    selectedBus = AdminDashboard.buses[i];
                    break;
                }
            }
            
            if (selectedBus == null) {
                throw new InvalidBusException("Bus with ID " + busId + " not found!");
            }
            
            // Passenger details
            System.out.println("\n--- PASSENGER DETAILS ---");
            System.out.print("Enter Name: ");
            sc.nextLine(); // consume newline
            String name = sc.nextLine();
            
            System.out.print("Enter Gender (M/F/Other): ");
            String gender = sc.next();
            
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            
            System.out.print("Enter Contact Number (10 digits): ");
            String contact = sc.next();
            if (!contact.matches("\\d{10}")) {
                throw new Exception("Invalid contact number. Must be 10 digits.");
            }
            
            // Journey details
            System.out.print("\nEnter Journey Date (DD/MM/YYYY): ");
            String journeyDateStr = sc.next();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate journeyDate = java.time.LocalDate.parse(journeyDateStr, formatter);
            if (journeyDate.isBefore(java.time.LocalDate.now())) {
                throw new Exception("Journey date cannot be in the past.");
            }
            
            // Number of tickets
            System.out.println("\nAvailable Seats: " + selectedBus.getAvailableSeats());
            System.out.print("Enter Number of Tickets: ");
            int numTickets = sc.nextInt();
            
            if (numTickets > selectedBus.getAvailableSeats()) {
                throw new InvalidSeatException("Only " + selectedBus.getAvailableSeats() + " seats available!");
            }
            
            // Seat selection
            int[] seats = new int[numTickets];
            System.out.println("\n--- SEAT SELECTION ---");
            for (int i = 0; i < numTickets; i++) {
                while (true) {
                    System.out.print("Select Seat " + (i + 1) + " (1-" + selectedBus.getTotalSeats() + "): ");
                    int seat = sc.nextInt();
                    if (!selectedBus.isSeatAvailable(seat)) {
                        System.out.println("Seat " + seat + " is not available! Please choose another.");
                    } else {
                        seats[i] = seat;
                        break;
                    }
                }
            }
            
            // Create booking
            Booking booking = new Booking(name, gender, age, contact, selectedBus, seats, journeyDate.format(formatter));
            double fare = booking.calculateFare();
            
            // Show fare and confirm
            System.out.println("\n--- FARE CALCULATION ---");
            System.out.println("Total Amount: Rs." + fare);
            System.out.print("\nConfirm Booking? (yes/no): ");
            String confirm = sc.next();
            
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("\nBooking cancelled by user.");
                return;
            }
            
            // Payment
            System.out.println("\n--- PAYMENT OPTIONS ---");
            System.out.println("1. Credit/Debit Card");
            System.out.println("2. UPI");
            System.out.println("3. Net Banking");
            System.out.println("4. Cash");
            System.out.print("Choose Payment Method: ");
            int payChoice = sc.nextInt();
            
            String payMethod = "";
            switch (payChoice) {
                case 1: payMethod = "Credit/Debit Card"; break;
                case 2: payMethod = "UPI"; break;
                case 3: payMethod = "Net Banking"; break;
                case 4: payMethod = "Cash"; break;
                default: throw new Exception("Invalid payment method.");
            }
            
            booking.setPaymentMethod(payMethod);
            
            if (booking.processPayment()) {
                // Book seats only after successful payment
                for (int seat : seats) {
                    selectedBus.bookSeat(seat);
                }
                
                // Add booking to myBookings
                Booking[] newBookings = new Booking[myBookingsCount + 1];
                System.arraycopy(myBookings, 0, newBookings, 0, myBookingsCount);
                newBookings[myBookingsCount] = booking;
                myBookings = newBookings;
                myBookingsCount++;
                
                System.out.println("\n✓ Booking successful!");
                booking.generateReceipt();
            } else {
                throw new PaymentFailedException("Payment failed. Please try again.");
            }
            
        } catch (InvalidBusException | InvalidSeatException | PaymentFailedException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ An error occurred: " + e.getMessage());
            sc.nextLine(); // Consume the invalid input
        }
    }
    
    void cancelTicket() {
        if (myBookingsCount == 0) {
            System.out.println("No bookings found to cancel.");
            return;
        }
        
        System.out.println("\n=== YOUR BOOKINGS ===");
        for (int i = 0; i < myBookingsCount; i++) {
            Booking b = myBookings[i];
            if (!b.isCancelled()) {
                System.out.println("Booking ID: " + b.getBookingId());
            }
        }
        
        System.out.print("\nEnter Booking ID to cancel: ");
        int bookingId = sc.nextInt();
        
        for (int i = 0; i < myBookingsCount; i++) {
            Booking b = myBookings[i];
            if (b.getBookingId() == bookingId && !b.isCancelled()) {
                System.out.print("Hours before journey: ");
                int hours = sc.nextInt();
                
                double refund = b.calculateRefund(hours);
                System.out.println("Refund Amount: Rs." + refund);
                
                System.out.print("Confirm Cancellation? (yes/no): ");
                String confirm = sc.next();
                
                if (confirm.equalsIgnoreCase("yes")) {
                    b.cancelBooking(bookingId);
                    System.out.println("Ticket cancelled successfully!");
                    System.out.println("Refund of Rs." + refund + " will be processed in 5-7 business days.");
                }
                return;
            }
        }
        
        System.out.println("Booking ID not found or already cancelled.");
    }
    
    void viewMyBookings() {
        if (myBookingsCount == 0) {
            System.out.println("\nNo bookings found.");
            return;
        }
        
        System.out.println("\n=== MY BOOKINGS ===");
        for (int i = 0; i < myBookingsCount; i++) {
            Booking b = myBookings[i];
            System.out.println("Booking ID: " + b.getBookingId() + 
                             " | Status: " + (b.isCancelled() ? "CANCELLED" : "CONFIRMED"));
        }
    }
    
    @Override
    void showProfile() {
        System.out.println("\n=== PROFILE ===");
        System.out.println("Passenger ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }

    void display() {
        int choice;
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n======================");
            System.out.println("    USER MENU");
            System.out.println("======================");
            System.out.println("1. View Available Buses");
            System.out.println("2. Search Bus by Route");
            System.out.println("3. Search by Bus Type");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View My Bookings");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            choice = sc.nextInt();
        
            switch(choice) {
                case 1:
                    showBus();
                    break;
                case 2:
                    searchBus();
                    break;
                case 3:
                    busType();
                    break;
                case 4:
                    bookTicket();
                    break;
                case 5:
                    cancelTicket();
                    break;
                case 6:
                    viewMyBookings();
                    break;
                case 7:
                    System.out.println("Returning to main menu...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break; 
            }
        }
    }
}