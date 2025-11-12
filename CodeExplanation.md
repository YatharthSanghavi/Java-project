# Bus Booking System - Code Explanation

This document provides a detailed line-by-line explanation of the Java code for the Bus Booking System.

---

## 1. `User.java`

This file defines a base class `User` which acts as a blueprint for different types of users in the system.

```java
// File: User.java

class User {
    // Declares a class named 'User'. This class serves as a basic template for users.
    // It contains common properties that any user (like a Passenger) would have.

    int id;
    // Declares an integer instance variable 'id'.
    // It is used to store a unique identifier for each user.

    String name;
    // Declares a String instance variable 'name'.
    // It is used to store the user's full name.

    String email;
    // Declares a String instance variable 'email'.
    // It is used to store the user's email address.

    User() {}
    // This is a default (no-argument) constructor.
    // It allows creating a 'User' object without providing any initial details.
    // For example: User newUser = new User();

    User(int id, String name, String email) {
        // This is a parameterized constructor. It's used to create a 'User' object with initial values.
        // 'id', 'name', and 'email' are parameters that will be provided when creating the object.

        this.id = id;
        // 'this.id' refers to the instance variable 'id' of the current object.
        // It assigns the value of the 'id' parameter (from the constructor) to the object's 'id' variable.
        // The 'this' keyword is used to distinguish between the instance variable and the parameter, as they have the same name.

        this.name = name;
        // Assigns the value of the 'name' parameter to the object's 'name' instance variable.

        this.email = email;
        // Assigns the value of the 'email' parameter to the object's 'email' instance variable.
    }

    void showProfile() {
        // Declares a method named 'showProfile'. A method is a block of code that performs a specific task.
        // This method's purpose is to display the user's details. 'void' means it does not return any value.

        System.out.println("User ID: " + id + " Name: " + name + " Email: " + email);
        // 'System.out.println' is a standard Java statement to print a line of text to the console.
        // It prints the user's ID, name, and email, concatenated (+) into a single string.
    }
}
```

---

## 2. `AdminLogin.java`

This file handles the authentication logic for the administrator.

```java
// File: AdminLogin.java

class AdminLogin {
    // Declares a class named 'AdminLogin'.
    // This class is responsible for verifying the administrator's credentials.

    String username = "admin";
    // Declares and initializes a String instance variable 'username'.
    // It's hardcoded to "admin", meaning this is the required username for a successful login.

    String password = "admin123";
    // Declares and initializes a String instance variable 'password'.
    // It's hardcoded to "admin123", serving as the required password.

    void login(String user, String pass) {
        // Declares a method 'login' that takes two String parameters: 'user' (for the entered username) and 'pass' (for the entered password).
        // This method contains the logic to check if the credentials are correct.

        boolean res = user.equals(username) && pass.equals(password);
        // This line performs the core login check.
        // 'user.equals(username)' compares the provided username with the stored admin username. It's the correct way to compare strings for equality in Java (not `==`).
        // 'pass.equals(password)' does the same for the password.
        // '&&' is the logical AND operator. It means both comparisons must be true for the entire expression to be true.
        // The result (either 'true' or 'false') is stored in the boolean variable 'res'.

        if(res) {
            // An 'if' statement that checks if the value of 'res' is true (i.e., if the login was successful).

            System.out.println("Login successful.");
            // Prints a success message to the console.

            AdminDashboard dashboard = new AdminDashboard();
            // Creates a new object (instance) of the 'AdminDashboard' class.
            // This is done so that the admin can access the dashboard functionalities after a successful login.

            dashboard.display();
            // Calls the 'display' method on the newly created 'dashboard' object.
            // This action shows the admin menu and allows the admin to start managing the system.

        } else {
            // The 'else' block is executed if the 'if' condition was false (i.e., 'res' is false).

            System.out.println("Invalid credentials.");
            // Prints an error message to the console, informing the user that the login failed.
        }
    }
}
```

---

## 3. `Bus.java`

This file is the most complex. It defines custom exceptions, an abstract class for payments, an interface for cancellations, and the core `Bus` and `Booking` classes.

```java
// File: Bus.java

// ============= Custom Exceptions =============
// Custom exceptions are created to handle specific errors in a more readable and organized way.

class InvalidBusException extends Exception {
    // Declares a new exception class 'InvalidBusException'.
    // By 'extending Exception', it becomes a checked exception, meaning the compiler will force you to handle it (with try-catch or throws).
    public InvalidBusException(String message) {
        // The constructor for this exception. It takes a 'message' string.
        super(message);
        // 'super(message)' calls the constructor of the parent class ('Exception') and passes the message to it.
        // This sets the error message that can be retrieved later with 'e.getMessage()'.
    }
}

class InvalidSeatException extends Exception {
    // Similar to above, this defines a custom exception for errors related to seat selection (e.g., booking an already booked seat).
    public InvalidSeatException(String message) {
        super(message);
    }
}

class PaymentFailedException extends Exception {
    // A custom exception for when a payment process fails.
    public PaymentFailedException(String message) {
        super(message);
    }
}

// ============= Abstract Class =============
// An abstract class cannot be instantiated on its own. It serves as a base for other classes.
// It can have both abstract methods (without a body) and concrete methods (with a body).

abstract class Payment {
    // Declares an abstract class 'Payment'. It defines a general structure for any payment process.
    protected double amount;
    // 'protected' means this variable can be accessed by this class, its subclasses, and other classes in the same package.
    // 'amount' will store the payment value.
    protected String paymentId;
    // 'paymentId' will store a unique ID for the transaction.

    abstract boolean processPayment();
    // An abstract method. It has no implementation (no `{}`).
    // Any class that extends 'Payment' MUST provide an implementation for this method.
    // It's expected to return 'true' for a successful payment and 'false' otherwise.

    abstract void generateReceipt();
    // Another abstract method. Subclasses must implement this to define how a receipt is generated and displayed.

    void displayPaymentInfo() {
        // This is a concrete (non-abstract) method within the abstract class.
        // Subclasses will inherit this method as-is. It provides a default way to display payment info.
        System.out.println("Payment ID: " + paymentId + ", Amount: Rs." + amount);
    }
}

// ============= Interface =============
// An interface is a completely abstract type that defines a contract.
// Any class that 'implements' an interface must provide an implementation for all its methods.

interface Cancellable {
    // Declares an interface named 'Cancellable'.
    // It defines the set of actions related to cancellation that a class must be able to perform.
    void cancelBooking(int bookingId);
    // Defines a method for cancelling a booking.
    double calculateRefund(int daysBeforeJourney);
    // Defines a method to calculate the refund amount.
    void displayCancellationPolicy();
    // Defines a method to show the cancellation rules.
}

// ============= Enhanced Bus Class =============
class Bus {
    // The main class for representing a bus.
    private int id; // 'private' means this variable can only be accessed within this 'Bus' class.
    private String busNo;
    String operator; // 'default' access: accessible within the same package.
    String type;
    String from;
    String to;
    String time;
    private int totalSeats;
    private boolean[] seatStatus; // An array of booleans. 'true' means booked, 'false' means available.
    private double baseFare;
    static int busCount = 0; // 'static' means this variable belongs to the class itself, not to any single object. There's only one 'busCount' shared by all 'Bus' objects.

    static {
        // This is a static initialization block. It runs only once, when the 'Bus' class is first loaded into memory.
        System.out.println("Static Block: Bus Booking System Initialized");
    }

    public Bus(int id, String busNo, String operator, String type, String from,
               String to, String time, int totalSeats, double baseFare) {
        // The constructor for the 'Bus' class. It initializes a new bus object with all its details.
        this.id = id;
        this.busNo = busNo;
        this.operator = operator;
        this.type = type;
        this.from = from;
        this.to = to;
        this.time = time;
        this.totalSeats = totalSeats;
        this.baseFare = baseFare;
        this.seatStatus = new boolean[totalSeats + 1]; // Creates the boolean array. The size is 'totalSeats + 1' so that seat numbers (like 1 to 50) can be used directly as array indices. Index 0 is left unused.
        busCount++; // Increments the static 'busCount' every time a new Bus object is created.
    }

    // Getters
    // Getters are public methods used to access private variables from outside the class. This is a principle of encapsulation.
    public int getId() { return id; }
    public String getBusNo() { return busNo; }
    public int getTotalSeats() { return totalSeats; }
    public double getBaseFare() { return baseFare; }

    // Setters
    // Setters are public methods used to modify private variables from outside the class.
    public void setOperator(String operator) { this.operator = operator; }
    public void setType(String type) { this.type = type; }
    public void setTime(String time) { this.time = time; }

    public boolean isSeatAvailable(int seatNo) {
        // A method to check if a specific seat is available.
        if (seatNo < 1 || seatNo > totalSeats) return false; // Basic validation: if the seat number is invalid, return false.
        return !seatStatus[seatNo]; // Returns the opposite of the seat's status. If 'seatStatus[seatNo]' is 'false' (available), it returns 'true'. If it's 'true' (booked), it returns 'false'.
    }

    public void bookSeat(int seatNo) throws InvalidSeatException {
        // A method to book a seat. It 'throws InvalidSeatException', indicating that this method can cause this error, and the caller must handle it.
        if (seatNo < 1 || seatNo > totalSeats) {
            throw new InvalidSeatException("Seat number " + seatNo + " is invalid."); // Creates and 'throws' a new exception if the seat number is out of range.
        }
        if (seatStatus[seatNo]) {
            throw new InvalidSeatException("Seat " + seatNo + " is already booked."); // Throws an exception if the seat is already booked.
        }
        seatStatus[seatNo] = true; // If no exceptions were thrown, it marks the seat as booked by setting its status to 'true'.
    }

    public void cancelSeat(int seatNo) {
        // A method to cancel a seat booking.
        if (seatNo >= 1 && seatNo <= totalSeats) {
            seatStatus[seatNo] = false; // Marks the seat as available again.
        }
    }

    public int getAvailableSeats() {
        // A method to count the total number of available seats on the bus.
        int count = 0; // Initializes a counter.
        for (int i = 1; i <= totalSeats; i++) { // A 'for' loop that iterates from seat 1 to the last seat.
            if (!seatStatus[i]) { // Checks if the seat is not booked.
                count++; // Increments the counter if the seat is available.
            }
        }
        return count; // Returns the final count.
    }

    void show() {
        // A method to display all the details of the bus in a formatted way.
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
    // Declares the 'Booking' class.
    // 'extends Payment' means 'Booking' is a subclass of 'Payment' and inherits its properties/methods.
    // 'implements Cancellable' means 'Booking' must provide implementations for all methods in the 'Cancellable' interface.
    private static int bookingCounter = 1000; // A static counter to generate unique booking IDs.
    private int bookingId;
    private String passengerName;
    private String gender;
    private int age;
    private String contact;
    private Bus bus; // A reference to the 'Bus' object for which this booking is made.
    private int[] seatNumbers; // An array to hold all seat numbers for this booking (e.g., if a user books multiple seats).
    private String bookingDate;
    private String journeyDate;
    private String paymentMethod;
    private double discount;
    private double finalAmount;
    private boolean isCancelled;

    public Booking(String passengerName, String gender, int age, String contact,
                   Bus bus, int[] seatNumbers, String journeyDate) {
        // The constructor for the 'Booking' class.
        this.bookingId = ++bookingCounter; // Pre-increments the counter and assigns it as the new booking ID.
        this.passengerName = passengerName;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.bus = bus;
        this.seatNumbers = seatNumbers;
        this.journeyDate = journeyDate;
        this.bookingDate = java.time.LocalDateTime.now().toString(); // Gets the current date and time and converts it to a string.
        this.isCancelled = false; // A new booking is not cancelled by default.
    }

    public double calculateFare() {
        // Method to calculate the total fare for the booking.
        double totalFare = bus.getBaseFare() * seatNumbers.length; // Calculates the initial fare based on the number of seats.

        // Apply discounts based on age.
        if (age < 12) {
            discount = totalFare * 0.25; // 25% discount for children under 12.
        } else if (age > 60) {
            discount = totalFare * 0.15; // 15% discount for seniors over 60.
        }

        finalAmount = totalFare - discount; // Calculates the final amount after discount.
        this.amount = finalAmount; // Sets the 'amount' variable inherited from the 'Payment' class.
        return finalAmount; // Returns the calculated final amount.
    }

    @Override
    // The '@Override' annotation indicates that this method is overriding a method from its superclass ('Payment').
    public boolean processPayment() {
        // Provides the implementation for the abstract 'processPayment' method.
        System.out.println("\n=== Processing Payment ===");
        System.out.println("Total Amount: Rs." + finalAmount);
        // In a real application, this would involve a payment gateway. Here, it just simulates a successful payment.
        return true;
    }

    @Override
    public void generateReceipt() {
        // Provides the implementation for the abstract 'generateReceipt' method.
        // This method prints a detailed ticket/receipt to the console.
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
        for (int seat : seatNumbers) { // A 'for-each' loop to iterate through the 'seatNumbers' array.
            System.out.print(seat + " "); // Prints each seat number.
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

        displayCancellationPolicy(); // Calls the method to show the cancellation policy on the ticket.

        System.out.println("\n--- TERMS & CONDITIONS ---");
        System.out.println("1. Please arrive 15 minutes before departure");
        System.out.println("2. Carry a valid ID proof during journey");
        System.out.println("3. Have a nice trip!");
        System.out.println("=============================================");
    }

    @Override
    public void displayCancellationPolicy() {
        // Implementation of the method from the 'Cancellable' interface.
        System.out.println("\n--- CANCELLATION POLICY ---");
        System.out.println("- 75% refund if cancelled > 24 hours before journey");
        System.out.println("- 50% refund if cancelled < 24 hours before journey");
        System.out.println("- No refund if cancelled < 6 hours before journey");
    }

    @Override
    public double calculateRefund(int hoursBeforeJourney) {
        // Implementation of the method from the 'Cancellable' interface.
        if (hoursBeforeJourney > 24) return finalAmount * 0.75;
        if (hoursBeforeJourney > 6) return finalAmount * 0.50;
        return 0; // No refund if less than 6 hours.
    }

    @Override
    public void cancelBooking(int bookingId) {
        // Implementation of the method from the 'Cancellable' interface.
        // This is a placeholder. The actual cancellation logic is in the 'Passenger' class.
        // This demonstrates that while the interface requires the method, its implementation can be simple if the logic resides elsewhere.
        System.out.println("Cancellation request for booking " + bookingId + " processed.");
        this.isCancelled = true;
        for (int seat : seatNumbers) {
            bus.cancelSeat(seat); // Calls the 'cancelSeat' method on the 'Bus' object to make the seats available again.
        }
    }

    public void setPaymentMethod(String method) {
        // A setter method for the payment method.
        this.paymentMethod = method;
    }

    public int getBookingId() {
        // A getter for the booking ID.
        return bookingId;
    }

    public boolean isCancelled() {
        // A getter to check if the booking has been cancelled.
        return isCancelled;
    }
}
```

---

## 4. `AdminDashboard.java`

This file provides the user interface and functionality for the administrator to manage buses.

```java
// File: AdminDashboard.java

import java.util.Scanner;
// Imports the Scanner class to read input from the admin.

class AdminDashboard {
    private Scanner sc = new Scanner(System.in);
    // Creates a Scanner object to read console input. It's 'private' so it's only used within this class.

    static Bus[] buses = new Bus[100];
    // A static array to store all 'Bus' objects. 'static' means this array is shared across the entire application.
    // It can hold up to 100 buses.

    static int busCount = 0;
    // A static counter to keep track of how many buses are currently in the 'buses' array.

    void addBus() {
        // Method to add a new bus to the system.
        try {
            // A 'try-catch' block is used for error handling. Code that might throw an exception is placed in the 'try' block.
            System.out.println("\n=== ADD NEW BUS ===");

            System.out.print("Enter Bus ID: ");
            int id = sc.nextInt(); // Reads the integer bus ID.

            // Check if the bus ID already exists to ensure uniqueness.
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    throw new InvalidBusException("Bus with ID " + id + " already exists!"); // Throws a custom exception if the ID is a duplicate.
                }
            }

            System.out.print("Enter Bus Number: ");
            String busNo = sc.next(); // Reads the bus number string.

            System.out.print("Enter Bus Operator: ");
            sc.nextLine(); // This is a common trick. 'sc.nextInt()' or 'sc.next()' doesn't consume the newline character. This line consumes it so the next 'sc.nextLine()' works correctly.
            String operator = sc.nextLine(); // Reads the full line for the operator's name.

            System.out.print("Enter Bus Type (AC/NonAC/Sleeper/Express): ");
            String type = sc.next();

            System.out.print("Enter From Location: ");
            String from = sc.next();

            System.out.print("Enter To Location: ");
            String to = sc.next();

            System.out.print("Enter Departure Time (HH:MM): ");
            String time = sc.next();

            System.out.print("Enter Number of Seats: ");
            int seats = sc.nextInt();

            if (seats <= 0) {
                throw new InvalidBusException("Number of seats must be positive!"); // Validates that the number of seats is a positive number.
            }

            System.out.print("Enter Base Fare (Rs.): ");
            double fare = sc.nextDouble(); // Reads the double value for the fare.

            if (fare <= 0) {
                throw new InvalidBusException("Fare must be positive!"); // Validates the fare.
            }

            // After collecting all data, create a new Bus object.
            Bus newBus = new Bus(id, busNo, operator, type, from, to, time, seats, fare);

            if (busCount < buses.length) {
                // Checks if there is space in the array.
                buses[busCount++] = newBus; // Adds the new bus to the array and then increments the counter.
                System.out.println("\n✓ Bus added successfully!");
                newBus.show(); // Displays the details of the newly added bus.
            } else {
                throw new InvalidBusException("Bus limit reached. Cannot add more buses."); // Throws an error if the array is full.
            }

        } catch (InvalidBusException e) {
            // This 'catch' block executes if an 'InvalidBusException' was thrown in the 'try' block.
            System.out.println("\n✗ ERROR: " + e.getMessage()); // Prints the specific error message from the exception.
        } catch (Exception e) {
            // This is a general 'catch' block for any other type of exception (e.g., if the user enters text instead of a number).
            System.out.println("\n✗ Invalid input. Please try again.");
            sc.nextLine(); // Consumes the rest of the invalid line to prevent an infinite loop.
        }
    }

    void viewAllBuses() {
        // Method to display all buses currently in the system.
        if (busCount == 0) {
            System.out.println("\nNo buses available.");
            return; // Exits the method early if there are no buses.
        }

        System.out.println("\n=== ALL BUSES ===\n");
        for (int i = 0; i < busCount; i++) {
            // Loops through the 'buses' array up to the current 'busCount'.
            buses[i].show(); // Calls the 'show' method for each bus to display its details.
        }
    }

    void updateBus() {
        // Method to modify the details of an existing bus.
        if (busCount == 0) {
            System.out.println("No buses available to update.");
            return;
        }

        viewAllBuses(); // First, show all buses so the admin can choose which one to update.

        try {
            System.out.print("\nEnter Bus ID to update: ");
            int id = sc.nextInt();

            Bus targetBus = null; // A variable to hold the bus object that needs to be updated.
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    targetBus = buses[i]; // If the bus is found, assign it to 'targetBus'.
                    break; // Exit the loop since the bus has been found.
                }
            }

            if (targetBus == null) {
                throw new InvalidBusException("Bus with ID " + id + " not found!"); // If the loop finishes and 'targetBus' is still null, the bus doesn't exist.
            }

            // Display a menu of update options.
            System.out.println("\n=== UPDATE OPTIONS ===");
            System.out.println("1. Operator");
            System.out.println("2. Type");
            System.out.println("3. From Location");
            System.out.println("4. To Location");
            System.out.println("5. Departure Time");
            System.out.println("6. All Details");
            System.out.print("Choose what to update: ");

            int choice = sc.nextInt();

            if (choice < 1 || choice > 6) {
                throw new InvalidBusException("Invalid choice!");
            }

            // A 'switch' statement to handle the admin's choice.
            switch(choice) {
                case 1:
                    System.out.print("Enter new operator: ");
                    sc.nextLine(); // Consume newline.
                    String operator = sc.nextLine();
                    targetBus.setOperator(operator); // Use the setter method to update the operator.
                    break;

                case 2:
                    System.out.print("Enter new bus type: ");
                    String type = sc.next();
                    targetBus.setType(type); // Use the setter to update the type.
                    break;

                case 3:
                    System.out.print("Enter new from location: ");
                    String from = sc.next();
                    targetBus.from = from; // Directly access and update the 'from' field.
                    break;

                case 4:
                    System.out.print("Enter new to location: ");
                    String to = sc.next();
                    targetBus.to = to; // Directly update the 'to' field.
                    break;

                case 5:
                    System.out.print("Enter new departure time: ");
                    String time = sc.next();
                    targetBus.setTime(time); // Use the setter to update the time.
                    break;

                case 6:
                    // This case updates all details at once.
                    System.out.print("Enter new operator: ");
                    sc.nextLine();
                    operator = sc.nextLine();
                    targetBus.setOperator(operator);

                    System.out.print("Enter new bus type: ");
                    type = sc.next();
                    targetBus.setType(type);

                    System.out.print("Enter new from location: ");
                    from = sc.next();
                    targetBus.from = from;

                    System.out.print("Enter new to location: ");
                    to = sc.next();
                    targetBus.to = to;

                    System.out.print("Enter new departure time: ");
                    time = sc.next();
                    targetBus.setTime(time);
                    break;
            }

            System.out.println("\n✓ Bus updated successfully!");
            targetBus.show(); // Show the updated bus details.

        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Invalid input. Please try again.");
            sc.nextLine();
        }
    }

    void deleteBus() {
        // Method to remove a bus from the system.
        if (busCount == 0) {
            System.out.println("No buses to delete.");
            return;
        }

        viewAllBuses(); // Show all buses.

        try {
            System.out.print("\nEnter Bus ID to delete: ");
            int id = sc.nextInt();

            int index = -1; // A variable to store the array index of the bus to be deleted.
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    index = i; // Store the index.
                    break;
                }
            }

            if (index == -1) {
                throw new InvalidBusException("Bus with ID " + id + " not found!");
            }

            System.out.print("Are you sure you want to delete this bus? (yes/no): ");
            String confirm = sc.next();

            if (confirm.equalsIgnoreCase("yes")) {
                // This is the logic to remove an element from the array.
                // It shifts all elements after the deleted one to the left.
                for (int i = index; i < busCount - 1; i++) {
                    buses[i] = buses[i + 1];
                }
                busCount--; // Decrement the total count of buses.
                System.out.println("\n✓ Bus deleted successfully!");
            } else {
                System.out.println("\n✗ Deletion cancelled.");
            }

        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void display() {
        // This method displays the main menu for the admin dashboard.
        System.out.println("\n======================");
        System.out.println("   ADMIN DASHBOARD");
        System.out.println("======================");

        int choice;
        boolean exit = false; // A flag to control the menu loop.

        while (!exit) {
            // A 'while' loop that continues as long as 'exit' is false.
            System.out.println("\n1. Add Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Update Bus Details");
            System.out.println("4. Delete Bus");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        addBus(); // Calls the addBus method.
                        break;
                    case 2:
                        viewAllBuses(); // Calls the viewAllBuses method.
                        break;
                    case 3:
                        updateBus(); // Calls the updateBus method.
                        break;
                    case 4:
                        deleteBus(); // Calls the deleteBus method.
                        break;
                    case 5:
                        exit = true; // Sets 'exit' to true, which will terminate the while loop.
                        System.out.println("\nLogging out...");
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\nInvalid input. Please enter a number.");
                sc.nextLine(); // Clear the invalid input.
            }
        }
    }
}
```

---

## 5. `Passenger.java`

This file defines the `Passenger` class, which inherits from `User` and provides all the functionality for a regular user, like searching for buses, booking tickets, and viewing their bookings.

```java
// File: Passenger.java

import java.util.Scanner;

class Passenger extends User {
    // 'Passenger' is a subclass of 'User'. It inherits 'id', 'name', and 'email' from 'User'.
    private String phone;
    private Booking[] myBookings; // An array to store all bookings made by this passenger.
    private int myBookingsCount;
    Scanner sc = new Scanner(System.in);

    public Passenger() {
        // A default constructor.
        myBookings = new Booking[0]; // Initializes the bookings array with a size of 0. It will be resized later.
        myBookingsCount = 0;
    }

    public Passenger(int id, String name, String email, String phone) {
        // A parameterized constructor.
        super(id, name, email); // 'super()' calls the constructor of the parent class ('User') to initialize the inherited fields.
        this.phone = phone;
        myBookings = new Booking[0];
        myBookingsCount = 0;
    }

    void searchBus() {
        // Method for a passenger to search for a bus based on route.
        System.out.print("Enter From Location: ");
        String from = sc.next();
        System.out.print("Enter To Location: ");
        String to = sc.next();
        System.out.println("\nSearching buses from " + from + " to " + to + "...\n");

        boolean found = false; // A flag to check if any buses were found.
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            // Accesses the static 'buses' array from the 'AdminDashboard' class to search through all available buses.
            if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) &&
                AdminDashboard.buses[i].to.equalsIgnoreCase(to)) {
                // 'equalsIgnoreCase' is used for case-insensitive comparison.
                AdminDashboard.buses[i].show(); // If a bus matches the route, display it.
                found = true; // Set the flag to true.
            }
        }

        if (!found) {
            // If the loop finishes and 'found' is still false.
            System.out.println("================================");
            System.out.println("No buses found for the given route.");
            System.out.println("================================");
        }
    }

    void showBus() {
        // Method to show all available buses, regardless of route.
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
        // Method to filter buses by their type (e.g., AC, Sleeper).
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
        // The main method for the ticket booking process.
        try {
            System.out.println("\n=== BOOK TICKET ===");

            showBus(); // Show all available buses first.

            if (AdminDashboard.busCount == 0) return; // If no buses, exit.

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
                throw new InvalidBusException("Bus with ID " + busId + " not found.");
            }

            // Collect passenger details.
            System.out.println("\n--- PASSENGER DETAILS ---");
            System.out.print("Enter Name: ");
            sc.nextLine(); // Consume newline.
            String name = sc.nextLine();

            System.out.print("Enter Gender (M/F/Other): ");
            String gender = sc.next();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            System.out.print("Enter Contact Number (10 digits): ");
            String contact = sc.next();
            if (!contact.matches("\\d{10}")) {
                // Uses a regular expression ('\\d{10}') to validate that the contact number is exactly 10 digits.
                throw new Exception("Invalid contact number format.");
            }

            // Journey details.
            System.out.print("\nEnter Journey Date (DD/MM/YYYY): ");
            String journeyDateStr = sc.next();
            // The following lines parse the date string into a 'LocalDate' object to perform date-based validation.
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate journeyDate = java.time.LocalDate.parse(journeyDateStr, formatter);
            if (journeyDate.isBefore(java.time.LocalDate.now())) {
                // Checks if the entered journey date is in the past.
                throw new Exception("Journey date cannot be in the past.");
            }

            // Number of tickets.
            System.out.println("\nAvailable Seats: " + selectedBus.getAvailableSeats());
            System.out.print("Enter Number of Tickets: ");
            int numTickets = sc.nextInt();

            if (numTickets > selectedBus.getAvailableSeats()) {
                throw new InvalidSeatException("Not enough seats available.");
            }

            // Seat selection.
            int[] seats = new int[numTickets];
            System.out.println("\n--- SEAT SELECTION ---");
            for (int i = 0; i < numTickets; i++) {
                System.out.print("Enter seat number for ticket " + (i + 1) + ": ");
                int seatNo = sc.nextInt();
                if (!selectedBus.isSeatAvailable(seatNo)) {
                    throw new InvalidSeatException("Seat " + seatNo + " is not available.");
                }
                selectedBus.bookSeat(seatNo); // Tentatively book the seat.
                seats[i] = seatNo;
            }

            // Create a new Booking object with all the collected details.
            Booking booking = new Booking(name, gender, age, contact, selectedBus, seats, journeyDate.format(formatter));
            double fare = booking.calculateFare(); // Calculate the fare.

            // Confirm booking with the user.
            System.out.println("\n--- FARE CALCULATION ---");
            System.out.println("Total Amount: Rs." + fare);
            System.out.print("\nConfirm Booking? (yes/no): ");
            String confirm = sc.next();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Booking cancelled.");
                // If booking is not confirmed, release the tentatively booked seats.
                for (int seat : seats) {
                    selectedBus.cancelSeat(seat);
                }
                return;
            }

            // Payment process simulation.
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
                default: throw new PaymentFailedException("Invalid payment method.");
            }

            booking.setPaymentMethod(payMethod);

            if (booking.processPayment()) {
                // If payment is successful.
                System.out.println("\n✓ Payment successful! Booking confirmed.");
                booking.generateReceipt(); // Generate and display the ticket.

                // Add the booking to the passenger's personal booking list.
                // This part resizes the 'myBookings' array to add the new booking.
                Booking[] newBookings = new Booking[myBookingsCount + 1];
                for (int i = 0; i < myBookingsCount; i++) {
                    newBookings[i] = myBookings[i];
                }
                newBookings[myBookingsCount] = booking;
                myBookings = newBookings;
                myBookingsCount++;

            } else {
                throw new PaymentFailedException("Payment failed. Please try again.");
            }

        } catch (InvalidBusException | InvalidSeatException | PaymentFailedException e) {
            // A multi-catch block to handle specific booking-related exceptions.
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            // A general catch block for other errors like invalid date format or number format.
            System.out.println("\n✗ An error occurred: " + e.getMessage());
            sc.nextLine(); // Consume the invalid input.
        }
    }

    void cancelTicket() {
        // Method to cancel a previously made booking.
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
                // Found the booking to cancel.
                System.out.print("Are you sure? (yes/no): ");
                String confirm = sc.next();
                if (confirm.equalsIgnoreCase("yes")) {
                    // In a real app, you'd calculate hours before journey. Here we simulate it.
                    double refund = b.calculateRefund(25); // Assume cancellation is > 24 hours before.
                    b.cancelBooking(bookingId); // This will mark the booking as cancelled and release the seats.
                    System.out.println("Booking cancelled successfully.");
                    System.out.println("Refund amount of Rs." + refund + " will be processed.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                return; // Exit the method after handling the cancellation.
            }
        }

        System.out.println("Booking ID not found or already cancelled.");
    }

    void viewMyBookings() {
        // Method to view all bookings made by the passenger.
        if (myBookingsCount == 0) {
            System.out.println("You have no bookings.");
            return;
        }

        System.out.println("\n=== MY BOOKINGS ===");
        for (int i = 0; i < myBookingsCount; i++) {
            System.out.println("--- Booking " + (i + 1) + " ---");
            myBookings[i].generateReceipt(); // Re-generates the receipt for each booking.
        }
    }

    @Override
    void showProfile() {
        // Overrides the 'showProfile' method from the 'User' class to add passenger-specific info.
        System.out.println("\n=== PROFILE ===");
        System.out.println("Passenger ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }

    void display() {
        // The main menu for the passenger.
        int choice;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- PASSENGER MENU ---");
            System.out.println("1. Search Bus by Route");
            System.out.println("2. Search Bus by Type");
            System.out.println("3. View All Buses");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View My Bookings");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1: searchBus(); break;
                    case 2: busType(); break;
                    case 3: showBus(); break;
                    case 4: bookTicket(); break;
                    case 5: cancelTicket(); break;
                    case 6: viewMyBookings(); break;
                    case 7: exit = true; break;
                    default: System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}
```

---

## 6. `BusBookingSystem.java`

This is the main entry point of the application. It contains the `main` method that starts the program.

```java
// File: BusBookingSystem.java

import java.util.Scanner;

public class BusBookingSystem {
    // This is the main class that contains the entry point of the program.

    public static void main(String[] args) {
        // The 'main' method is where the Java Virtual Machine (JVM) begins execution.
        // 'public' means it can be called from anywhere.
        // 'static' means it belongs to the class, not an object, so it can be run without creating an instance of 'BusBookingSystem'.
        // 'void' means it doesn't return any value.
        // 'String[] args' is an array that can hold command-line arguments passed to the program.

        Scanner sc = new Scanner(System.in);
        // Creates a Scanner object for user input.
        int choice;
        while(true){
            // 'while(true)' creates an infinite loop, which keeps the main menu running until the user explicitly chooses to exit.
            System.out.println("======================");
            System.out.println("Bus Booking System");
            System.out.println("======================");
            System.out.print("1. Admin Login\n2. User Menu\n3. Exit\nChoose an option: ");
            choice = sc.nextInt(); // Reads the user's menu choice.

            switch(choice) {
                // A 'switch' statement to handle the main menu options.
                case 1:
                    // Handles Admin Login.
                    System.out.print("Enter username: ");
                    String user = sc.next();
                    System.out.print("Enter password: ");
                    String pass = sc.next();
                    AdminLogin admin = new AdminLogin(); // Creates an AdminLogin object.
                    admin.login(user, pass); // Calls the login method to verify credentials.
                    break;
                case 2:
                    // Handles User (Passenger) Menu.
                    Passenger p  = new Passenger(); // Creates a Passenger object.
                    p.display(); // Calls the display method, which shows the passenger menu.
                    break;
                case 3:
                    // Handles Exit.
                    System.out.println("Exiting...");
                    System.exit(0); // 'System.exit(0)' terminates the entire application. A status of 0 indicates normal termination.
                    break;
                default:
                    // Handles any invalid choice.
                    System.out.println("Invalid choice.");
            }
        }
    }
}
```
