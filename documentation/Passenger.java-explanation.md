# Passenger.java - Complete Code Explanation

## File Overview
**Purpose**: Implements passenger/user functionality for searching buses, booking tickets, cancelling bookings, and managing their travel reservations.

**Why**: Users need an interface to interact with the bus booking system without administrative privileges.

**How**: Extends User class and provides methods for bus search, ticket booking, cancellation, and viewing personal bookings.

---

## Line-by-Line Explanation

### Line 1: Import Statement
```java
import java.util.Scanner;
```
**What**: Imports Scanner class for user input  
**Why**: Passenger needs to input search criteria, booking details, etc.  
**Package**: `java.util` - Java utility library  
**Usage**: Create Scanner object for reading keyboard input

---

### Line 3: Class Declaration
```java
class Passenger extends User {
```
**What**: Declares Passenger class that inherits from User  
**Why**: Passenger IS-A type of User with additional functionality  
**Inheritance**: `extends User` - inherits id, name, email fields  
**Access**: Package-private (no modifier)  
**Relationship**: Child class of User  
**Purpose**: Represents a customer using the bus booking system

---

### Line 4: Private Variable - phone
```java
    private String phone;
```
**What**: Stores passenger's phone number  
**Why**: Contact information specific to passengers  
**Access**: `private` - encapsulated, accessed via getter/setter  
**Type**: `String` - can store formatted numbers with dashes or spaces  
**Additional**: Beyond User's fields (id, name, email)

---

### Line 5: Private Variable - myBookings
```java
    private Booking[] myBookings;
```
**What**: Array to store passenger's bookings  
**Why**: Each passenger needs to track their own bookings  
**Type**: `Booking[]` - array of Booking objects  
**Dynamic**: Resized when new bookings added  
**Access**: `private` - internal management only  
**Purpose**: Personal booking history

---

### Line 6: Private Variable - myBookingsCount
```java
    private int myBookingsCount;
```
**What**: Counter for number of bookings  
**Why**: Track how many bookings in array  
**Type**: `int` - integer counter  
**Usage**: Loop boundary and array sizing  
**Initial Value**: 0 - no bookings initially

---

### Line 7: Scanner Instance Variable
```java
    Scanner sc = new Scanner(System.in);
```
**What**: Scanner object for reading input  
**Why**: Multiple methods need input capability  
**Access**: Package-private (no modifier) - accessible within package  
**Scope**: Instance variable - available to all methods  
**System.in**: Standard input (keyboard)  
**Note**: Could be private for better encapsulation

---

## CONSTRUCTOR 1: Default Constructor - Lines 9-12

### Lines 9-12: Default Constructor
```java
    public Passenger() {
        myBookings = new Booking[0]; // No initial capacity
        myBookingsCount = 0;
    }
```
**What**: No-argument constructor  
**Why**: Create Passenger without initial data  
**Access**: `public` - can be called from anywhere  

**Line 10**: Initialize empty bookings array  
- `new Booking[0]` - zero-length array
- Will grow dynamically as bookings added
- Comment explains design choice

**Line 11**: Set count to zero  
**Note**: Doesn't initialize inherited fields (id, name, email remain default/null)  
**Usage**: `Passenger p = new Passenger();`

---

## CONSTRUCTOR 2: Parameterized Constructor - Lines 14-19

### Lines 14-15: Constructor Declaration
```java
    public Passenger(int id, String name, String email, String phone) {
        super(id, name, email);
```
**What**: Constructor with parameters  
**Why**: Create Passenger with initial data  
**Parameters**:
1. `id` - passenger identifier
2. `name` - passenger name
3. `email` - email address
4. `phone` - phone number

**Line 15**: Call parent constructor  
- `super()` - calls User(int, String, String)
- Initializes inherited fields (id, name, email)
- **MUST** be first line in constructor

---

### Lines 16-18: Initialize Passenger-Specific Fields
```java
        this.phone = phone;
        myBookings = new Booking[0]; // No initial capacity
        myBookingsCount = 0;
    }
```
**Line 16**: Set phone number  
**Lines 17-18**: Initialize bookings (same as default constructor)  
**Result**: Fully initialized Passenger with user data and empty booking list

---

## METHOD 1: searchBus() - Lines 21-41

### Line 21: Method Declaration
```java
    void searchBus() {
```
**What**: Method to search buses by route  
**Why**: Users need to find buses between specific locations  
**Return**: `void` - displays results, doesn't return data  
**Called By**: display() menu option 2

---

### Lines 22-25: Input Route
```java
        System.out.print("Enter From Location: ");
        String from = sc.next();
        System.out.print("Enter To Location: ");
        String to = sc.next();
```
**What**: Gets origin and destination from user  
**Why**: Define search criteria  
**Line 22-23**: Read starting location  
**Line 24-25**: Read destination  
**Method**: `next()` - reads single word (no spaces in city names)

---

### Line 26: Search Message
```java
        System.out.println("\nSearching buses from " + from + " to " + to + "...\n");
```
**What**: Displays search operation message  
**Why**: User feedback during search  
**Format**: Shows what route is being searched  
**Spacing**: `\n` before and after for visual separation

---

### Line 28: Found Flag
```java
        boolean found = false;
```
**What**: Flag to track if any buses found  
**Why**: Display "no results" message if needed  
**Initial**: `false` - assume not found  
**Updated**: Set to `true` when match found

---

### Lines 29-35: Search Loop
```java
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) && 
                AdminDashboard.buses[i].to.equalsIgnoreCase(to)) {
                AdminDashboard.buses[i].show();
                found = true;
            }
        }
```
**What**: Loops through all buses checking for route match  
**Why**: Find buses matching search criteria  

**Line 29**: Iterate through buses  
- Accesses static array in AdminDashboard
- Only checks filled positions (`i < busCount`)

**Lines 30-31**: Match condition  
- `from.equalsIgnoreCase(from)` - origin matches (case-insensitive)
- `&&` - AND operator (both must match)
- `to.equalsIgnoreCase(to)` - destination matches
- **equalsIgnoreCase**: "mumbai" matches "Mumbai"

**Line 32**: Display matching bus  
**Line 33**: Mark as found

---

### Lines 37-40: No Results Message
```java
        if (!found) {
            System.out.println("================================");
            System.out.println("No buses found for the given route.");
            System.out.println("================================");
        }
    }
```
**What**: Displays message if no buses found  
**Condition**: `!found` - if flag still false  
**Format**: Boxed message for emphasis  
**User Experience**: Clear feedback when search yields no results

---

## METHOD 2: showBus() - Lines 43-54

### Line 43: Method Declaration
```java
    void showBus() {
```
**What**: Displays all available buses  
**Why**: Users need to see all options  
**Called By**: display() menu option 1

---

### Lines 44-49: Empty Check
```java
        if (AdminDashboard.busCount == 0) {
            System.out.println("================================");
            System.out.println("No buses available.");
            System.out.println("================================");
            return;
        }
```
**What**: Handles case when no buses exist  
**Why**: Can't display empty list  
**Line 44**: Check if bus count is zero  
**Lines 45-47**: Boxed message  
**Line 48**: Early return - exit method

---

### Lines 50-53: Display All Buses
```java
        System.out.println("\n=== AVAILABLE BUSES ===\n");
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            AdminDashboard.buses[i].show();
        }
    }
```
**What**: Loops through and displays each bus  
**Line 50**: Section header  
**Lines 51-53**: Loop calling show() on each bus  
**Result**: Complete listing of all buses

---

## METHOD 3: busType() - Lines 56-74

### Line 56: Method Declaration
```java
    void busType() {
```
**What**: Searches buses by type (AC/NonAC/Sleeper/Express)  
**Why**: Users may prefer specific bus types  
**Called By**: display() menu option 3

---

### Lines 57-59: Input Bus Type
```java
        System.out.print("Search by bus type (AC/Non-AC/Sleeper/Express): ");
        String type = sc.next();
        System.out.println();
```
**What**: Gets bus type from user  
**Line 57**: Prompt with valid options  
**Line 58**: Read type  
**Line 59**: Blank line for spacing

---

### Lines 61-73: Search and Display
```java
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
```
**What**: Similar pattern to searchBus()  
**Line 61**: Found flag  
**Lines 62-67**: Search loop  
**Line 63**: Match by type (case-insensitive)  
**Lines 69-73**: No results message with searched type

---

## METHOD 4: bookTicket() - Lines 76-232 (MOST COMPLEX)

### Line 76: Method Declaration
```java
    void bookTicket() {
```
**What**: Complete ticket booking process  
**Why**: Core functionality - allows users to book seats  
**Complexity**: Most complex method with multiple steps  
**Called By**: display() menu option 4

---

### Line 77: Try Block Start
```java
        try {
```
**What**: Exception handling for entire booking process  
**Why**: Many validation points that can fail  
**Catches**: InvalidBusException, InvalidSeatException, PaymentFailedException, general Exception

---

### Line 78: Booking Header
```java
            System.out.println("\n=== BOOK TICKET ===");
```
**What**: Section header for booking  
**User Experience**: Clear indication of operation

---

### Lines 80-88: Route Search (Step 1)
```java
            // Show available buses
            System.out.print("Enter From Location: ");
            String from = sc.next();
            System.out.print("Enter To Location: ");
            String to = sc.next();
            System.out.println("\nSearching buses from " + from + " to " + to + "...\n");
            
            boolean found = false;
            for (int i = 0; i < AdminDashboard.busCount; i++) {
```
**What**: Gets route and starts search  
**Why**: Show available buses for chosen route  
**Comment**: Line 80 explains section  
**Lines 81-84**: Input origin and destination  
**Line 85**: Search message  
**Line 87**: Initialize found flag  
**Line 88**: Begin search loop

---

### Lines 89-94: Display Matching Buses
```java
                if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) && 
                    AdminDashboard.buses[i].to.equalsIgnoreCase(to)) {
                    AdminDashboard.buses[i].show();
                    found = true;
                }
            }
```
**What**: Shows buses matching route  
**Same Logic**: As searchBus() method  
**Line 91**: Display matching bus  
**Line 92**: Mark as found

---

### Lines 96-101: Handle No Buses Found
```java
            if (!found) {
                System.out.println("================================");
                System.out.println("No buses found for the given route.");
                System.out.println("================================");

                throw new InvalidBusException("NO BUSES FOUND");
            }
```
**What**: Stops booking if no buses available  
**Why**: Can't book if no buses on route  
**Lines 97-99**: Display message  
**Line 101**: **Throw exception** - aborts booking process  
**Effect**: Jumps to catch block on line 227

---

### Lines 103-105: Input Bus ID (Step 2)
```java
            // Get bus ID
            System.out.print("\nEnter Bus ID: ");
            int busId = sc.nextInt();
```
**What**: User selects specific bus  
**Why**: May be multiple buses on same route  
**Comment**: Step explanation  
**Line 105**: Read bus ID number

---

### Lines 107-114: Find Selected Bus
```java
            Bus selectedBus = null;
            for (int i = 0; i < AdminDashboard.busCount; i++) {
                if (AdminDashboard.buses[i].getId() == busId) {
                    selectedBus = AdminDashboard.buses[i];
                    break;
                }
            }
```
**What**: Finds bus object by ID  
**Why**: Need reference to book seats  
**Line 107**: Initialize as null  
**Lines 108-113**: Search loop  
**Line 109**: Compare IDs  
**Line 110**: Store reference  
**Line 111**: Break early when found

---

### Lines 116-118: Validate Bus Exists
```java
            if (selectedBus == null) {
                throw new InvalidBusException("Bus with ID " + busId + " not found!");
            }
```
**What**: Ensures valid bus was selected  
**Why**: User might enter wrong ID  
**Action**: Throw exception if not found

---

### Lines 120-125: Passenger Name (Step 3)
```java
            // Passenger details
            System.out.println("\n--- PASSENGER DETAILS ---");
            System.out.print("Enter Name: ");
            sc.nextLine(); // consume newline
            String name = sc.nextLine();
```
**What**: Collects passenger information  
**Comment**: Section marker  
**Line 122**: Section header  
**Line 124**: **CRITICAL** - clear buffer after nextInt()  
**Line 125**: Read full name (can have spaces)  
**Why nextLine()**: Names can be multiple words ("John Smith")

---

### Lines 127-128: Input Gender
```java
            System.out.print("Enter Gender (M/F/Other): ");
            String gender = sc.next();
```
**What**: Collects gender information  
**Options**: M, F, Other  
**Validation**: None - accepts any string

---

### Lines 130-131: Input Age
```java
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
```
**What**: Gets passenger age  
**Why**: Used for discount calculation  
**Discounts**: Child (<12) 25%, Senior (≥60) 30%

---

### Lines 133-137: Input and Validate Contact
```java
            System.out.print("Enter Contact Number (10 digits): ");
            String contact = sc.next();
            if (!contact.matches("\\d{10}")) {
                throw new Exception("Invalid contact number. Must be 10 digits.");
            }
```
**What**: Gets and validates phone number  
**Why**: Contact information required  
**Line 134**: Read contact as string  
**Line 135**: **Regex Validation**  
- `matches("\\d{10}")` - checks pattern
- `\\d` - digit character
- `{10}` - exactly 10 times
- Pattern: Must be exactly 10 digits

**Line 136**: Throw exception if invalid  
**Example Valid**: "9876543210"  
**Example Invalid**: "123", "98765abc12", "12345678901"

---

### Lines 139-145: Input and Validate Journey Date (Step 4)
```java
            // Journey details
            System.out.print("\nEnter Journey Date (DD/MM/YYYY): ");
            String journeyDateStr = sc.next();
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate journeyDate = java.time.LocalDate.parse(journeyDateStr, formatter);
            if (journeyDate.isBefore(java.time.LocalDate.now())) {
                throw new Exception("Journey date cannot be in the past.");
            }
```
**What**: Gets and validates journey date  
**Why**: Need to know travel date, prevent past dates  

**Line 140**: Comment marker  
**Line 141**: Prompt with format  
**Line 142**: Read date string  

**Line 143**: Create date formatter  
- Full path: `java.time.format.DateTimeFormatter`
- Pattern: "dd/MM/yyyy" (day/month/year)
- Example: "25/12/2025"

**Line 144**: Parse string to LocalDate  
- Converts string to date object
- Throws exception if format wrong

**Lines 145-147**: Validate not in past  
- `journeyDate.isBefore(LocalDate.now())` - compare with today
- Prevents booking for yesterday

---

### Lines 149-156: Input and Validate Ticket Count (Step 5)
```java
            // Number of tickets
            System.out.println("\nAvailable Seats: " + selectedBus.getAvailableSeats());
            System.out.print("Enter Number of Tickets: ");
            int numTickets = sc.nextInt();
            
            if (numTickets > selectedBus.getAvailableSeats()) {
                throw new InvalidSeatException("Only " + selectedBus.getAvailableSeats() + " seats available!");
            }
```
**What**: Gets number of seats to book with validation  
**Why**: Ensure enough seats available  

**Line 150**: Shows available seat count  
**Line 151**: Prompt  
**Line 152**: Read number  

**Lines 154-156**: Availability check  
- Compare requested with available
- Throw exception if not enough seats
- Message includes how many are available

---

### Lines 158-171: Seat Selection (Step 6)
```java
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
```
**What**: User selects specific seat numbers  
**Why**: Let passenger choose preferred seats  
**Complex**: Nested loops with validation  

**Line 159**: Create array to store seat numbers  
**Line 160**: Section header  

**Line 161**: Outer loop - for each ticket  
- `i = 0` to `numTickets - 1`

**Line 162**: **Inner while(true) loop**  
- Infinite loop until valid seat selected
- Keeps asking until user picks available seat

**Line 163**: Prompt with seat number and range  
- `(i + 1)` - show ticket number (1-based)
- Shows valid range

**Line 164**: Read seat number  

**Line 165**: Check if seat available  
- Calls `isSeatAvailable()` method
- Returns false if booked or invalid

**Line 166**: If NOT available, show error  
**Lines 167-169**: If available  
- Store in array
- Break inner loop (move to next ticket)

**Example Flow**:
```
Select Seat 1 (1-40): 5
✓ Seat 5 available, stored
Select Seat 2 (1-40): 5
✗ Seat 5 not available! Choose another.
Select Seat 2 (1-40): 6
✓ Seat 6 available, stored
```

---

### Lines 173-174: Create Booking Object (Step 7)
```java
            // Create booking
            Booking booking = new Booking(name, gender, age, contact, selectedBus, seats, journeyDate.format(formatter));
```
**What**: Creates Booking object with collected data  
**Why**: Instantiate booking for payment processing  
**Comment**: Step marker  

**Parameters Passed**:
1. `name` - passenger name
2. `gender` - M/F/Other
3. `age` - for discount
4. `contact` - phone number
5. `selectedBus` - Bus object reference
6. `seats` - array of seat numbers
7. `journeyDate.format(formatter)` - date as string

**Line 174**: Calculate fare  
- Calls `calculateFare()` on booking
- Returns final amount after discounts

---

### Lines 176-183: Fare Display and Confirmation (Step 8)
```java
            // Show fare and confirm
            System.out.println("\n--- FARE CALCULATION ---");
            System.out.println("Total Amount: Rs." + fare);
            System.out.print("\nConfirm Booking? (yes/no): ");
            String confirm = sc.next();
            
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("\nBooking cancelled by user.");
                return;
            }
```
**What**: Shows fare and gets confirmation  
**Why**: User must approve before payment  

**Line 177**: Section header  
**Line 178**: Display calculated fare  
**Line 179**: Confirmation prompt  
**Line 180**: Read response  

**Lines 182-185**: Handle rejection  
- If NOT "yes", cancel booking
- Early return - exit method
- No payment, no seat booking

**User Choice**: Opt-out point before payment

---

### Lines 186-198: Payment Method Selection (Step 9)
```java
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
```
**What**: User selects payment method  
**Why**: Record how payment was made  

**Lines 187-192**: Display payment options  
**Line 193**: Read choice  

**Line 195**: Initialize payment method string  

**Lines 196-203**: Switch statement  
- Maps number to payment method name
- Each case sets `payMethod` string
- Break after each case
- **Default**: Throws exception for invalid choice (5, 6, etc.)

---

### Line 205: Set Payment Method
```java
            booking.setPaymentMethod(payMethod);
```
**What**: Stores payment method in booking  
**Why**: Record for receipt and tracking

---

### Lines 207-223: Process Payment and Complete Booking (Step 10)
```java
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
```
**What**: Processes payment and finalizes booking  
**Why**: Complete transaction if payment succeeds  

**Line 207**: Call payment processing  
- Returns `true` for success, `false` for failure
- Currently always returns true (simplified)

**Lines 208-211**: Book the seats  
**Comment**: Line 208 explains timing (after payment)  
**Lines 209-211**: Enhanced for loop  
- Iterate through selected seats
- Call `bookSeat()` to mark as booked
- **Critical**: Only book AFTER payment succeeds

**Lines 213-217**: Add booking to passenger's list  
**Comment**: Line 213 explains operation  

**Line 214**: Create new larger array  
- Size = current count + 1
- **Dynamic Array Growth**

**Line 215**: Copy existing bookings  
- `System.arraycopy(source, srcPos, dest, destPos, length)`
- Copies all existing bookings to new array

**Line 216**: Add new booking at end  
**Line 217**: Replace old array with new one  
**Line 218**: Increment counter  

**Why This Way**: Original array has size 0, needs to grow dynamically

**Lines 220-221**: Success message and receipt  
- Checkmark symbol
- Generate full receipt with all details

---

### Lines 222-224: Handle Payment Failure
```java
            } else {
                throw new PaymentFailedException("Payment failed. Please try again.");
            }
```
**What**: Handles payment failure  
**When**: If `processPayment()` returns false  
**Action**: Throw PaymentFailedException  
**Effect**: Seats not booked, no receipt generated

---

### Lines 226-232: Exception Handling
```java
        } catch (InvalidBusException | InvalidSeatException | PaymentFailedException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ An error occurred: " + e.getMessage());
            sc.nextLine(); // Consume the invalid input
        }
    }
```
**What**: Catches and handles various exceptions  
**Why**: Provide error messages, prevent crashes  

**Lines 226-227**: Multi-catch block  
- `|` operator - catches any of three exception types
- InvalidBusException - bus not found
- InvalidSeatException - seat issues
- PaymentFailedException - payment problems
- Shows specific error message

**Lines 228-231**: General exception catch  
- Catches everything else (date parsing, contact validation, etc.)
- Line 230: Clear input buffer
- Prevents infinite loop on invalid input

---

## METHOD 5: cancelTicket() - Lines 234-268

### Line 234: Method Declaration
```java
    void cancelTicket() {
```
**What**: Cancels an existing booking  
**Why**: Users may need to cancel travel plans  
**Called By**: display() menu option 5

---

### Lines 235-238: Empty Check
```java
        if (myBookingsCount == 0) {
            System.out.println("No bookings found to cancel.");
            return;
        }
```
**What**: Validates bookings exist  
**Why**: Can't cancel if no bookings  
**Early Return**: Exit if empty

---

### Lines 240-246: Display Active Bookings
```java
        System.out.println("\n=== YOUR BOOKINGS ===");
        for (int i = 0; i < myBookingsCount; i++) {
            Booking b = myBookings[i];
            if (!b.isCancelled()) {
                System.out.println("Booking ID: " + b.getBookingId());
            }
        }
```
**What**: Shows bookings that can be cancelled  
**Why**: User needs to see booking IDs  
**Line 241**: Loop through bookings  
**Line 242**: Get booking reference  
**Line 243**: Check if NOT already cancelled  
**Line 244**: Display booking ID  
**Filter**: Only shows active bookings

---

### Lines 248-249: Input Booking ID
```java
        System.out.print("\nEnter Booking ID to cancel: ");
        int bookingId = sc.nextInt();
```
**What**: Gets which booking to cancel  
**Why**: Identify target booking

---

### Lines 251-267: Find and Cancel Booking
```java
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
```
**What**: Searches, calculates refund, and cancels booking  
**Complex**: Multi-step cancellation process  

**Line 251**: Loop through bookings  
**Line 252**: Get booking reference  

**Line 253**: Match conditions  
- Booking ID matches
- Not already cancelled
- Both must be true

**Lines 254-255**: Get cancellation timing  
- Asks hours before journey
- Used for refund calculation

**Lines 257-258**: Calculate and show refund  
- Calls `calculateRefund()` with hours
- Displays refund amount
- **Tiered**: 24h+ = 90%, 12-24h = 50%, 6-12h = 25%, <6h = 0%

**Lines 260-261**: Confirmation prompt  
**Lines 263-267**: If confirmed  
- Line 264: Cancel booking (frees seats)
- Line 265: Success message
- Line 266: Refund processing information

**Line 267**: **Return** - exit after cancelling  
- Only cancels one booking
- Doesn't continue searching

---

### Lines 269-271: Not Found Message
```java
        System.out.println("Booking ID not found or already cancelled.");
    }
```
**What**: Message if booking not found  
**When**: Loop completes without finding match  
**Covers**: Invalid ID or already cancelled booking

---

## METHOD 6: viewMyBookings() - Lines 273-285

### Line 273: Method Declaration
```java
    void viewMyBookings() {
```
**What**: Displays all passenger's bookings  
**Why**: User wants to see booking history  
**Called By**: display() menu option 6

---

### Lines 274-277: Empty Check
```java
        if (myBookingsCount == 0) {
            System.out.println("\nNo bookings found.");
            return;
        }
```
**What**: Handles no bookings case  
**Early Return**: Exit if empty

---

### Lines 279-284: Display All Bookings
```java
        System.out.println("\n=== MY BOOKINGS ===");
        for (int i = 0; i < myBookingsCount; i++) {
            Booking b = myBookings[i];
            System.out.println("Booking ID: " + b.getBookingId() + 
                             " | Status: " + (b.isCancelled() ? "CANCELLED" : "CONFIRMED"));
        }
    }
```
**What**: Lists all bookings with status  
**Line 280**: Loop through all bookings  
**Line 281**: Get booking reference  

**Lines 282-283**: Display booking info  
- Shows booking ID
- **Ternary Operator**: `condition ? valueIfTrue : valueIfFalse`
- `b.isCancelled()` - if true, show "CANCELLED", else "CONFIRMED"

**Output Example**:
```
=== MY BOOKINGS ===
Booking ID: 1001 | Status: CONFIRMED
Booking ID: 1002 | Status: CANCELLED
Booking ID: 1003 | Status: CONFIRMED
```

---

## METHOD 7: showProfile() - Lines 287-293

### Line 287: Override Annotation
```java
    @Override
    void showProfile() {
```
**What**: Overrides User's showProfile() method  
**Why**: Add phone number to profile display  
**@Override**: Indicates method overrides parent  
**Polymorphism**: Passenger version called, not User version

---

### Lines 289-293: Display Profile
```java
        System.out.println("\n=== PROFILE ===");
        System.out.println("Passenger ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }
```
**What**: Displays passenger information  
**Line 290**: ID (inherited from User)  
**Line 291**: Name (inherited)  
**Line 292**: Email (inherited)  
**Line 293**: Phone (Passenger-specific)  
**Enhanced**: Shows more than User's version

---

## METHOD 8: display() - Lines 295-345

### Line 295: Method Declaration
```java
    void display() {
```
**What**: Main passenger menu interface  
**Why**: User's control panel for all operations  
**Called By**: BusBookingSystem.main() when user selects option 2

---

### Lines 296-297: Initialize Variables
```java
        int choice;
        boolean exit = false;
```
**What**: Menu control variables  
**Line 296**: Stores menu selection  
**Line 297**: Loop exit flag

---

### Line 299: While Loop
```java
        while (!exit) {
```
**What**: Main menu loop  
**Condition**: While exit is false  
**Controlled**: Exits when user chooses option 7

---

### Lines 300-309: Display Menu
```java
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
```
**What**: Displays user menu options  
**Lines 300-302**: Header with borders  
**Lines 303-309**: Seven menu options  

**Menu Options**:
1. **View All** - see all buses
2. **Search Route** - find by origin/destination
3. **Search Type** - filter by AC/NonAC/etc.
4. **Book** - create new booking
5. **Cancel** - cancel existing booking
6. **View Bookings** - see personal bookings
7. **Exit** - return to main menu

---

### Line 311: Read Choice
```java
            choice = sc.nextInt();
```
**What**: Gets user's menu selection  
**No Try-Catch**: Unlike AdminDashboard, no exception handling here  
**Risk**: Could crash on non-integer input

---

### Lines 313-343: Switch Statement
```java
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
```
**What**: Routes to appropriate method based on choice  

**Case 1 (Lines 314-316)**: View buses  
- Calls `showBus()`

**Case 2 (Lines 317-319)**: Search by route  
- Calls `searchBus()`

**Case 3 (Lines 320-322)**: Search by type  
- Calls `busType()`

**Case 4 (Lines 323-325)**: Book ticket  
- Calls `bookTicket()` - most complex operation

**Case 5 (Lines 326-328)**: Cancel ticket  
- Calls `cancelTicket()`

**Case 6 (Lines 329-331)**: View bookings  
- Calls `viewMyBookings()`

**Case 7 (Lines 332-335)**: Exit  
- Line 333: Exit message
- Line 334: Set `exit = true`
- Loop ends on next iteration

**Default (Lines 336-338)**: Invalid choice  
- Error message
- Loop continues

**Line 340**: Closes while loop  
**Line 341**: Closes display() method  
**Line 342-343**: Closes Passenger class

---

## COMPLETE SUMMARY

### Class Hierarchy
```
User (parent)
  ↓
Passenger (child)
  - Inherits: id, name, email, showProfile()
  - Adds: phone, myBookings, booking methods
```

### Instance Variables
1. **phone** (String) - contact number
2. **myBookings** (Booking[]) - dynamic array of bookings
3. **myBookingsCount** (int) - number of bookings
4. **sc** (Scanner) - input reader

### Methods Summary
1. **Passenger()** - Default constructor
2. **Passenger(...)** - Parameterized constructor
3. **searchBus()** - Find buses by route
4. **showBus()** - Display all buses
5. **busType()** - Search by bus type
6. **bookTicket()** - Complete booking process (10 steps)
7. **cancelTicket()** - Cancel and refund booking
8. **viewMyBookings()** - Show booking history
9. **showProfile()** - Display user information (override)
10. **display()** - Main menu interface

### Dynamic Array Growth Pattern
**Problem**: Bookings array starts with size 0  
**Solution**: Create new larger array when adding booking

**Algorithm**:
```java
1. Create new array (size = current + 1)
2. Copy all existing elements
3. Add new element at end
4. Replace old array with new array
5. Increment counter
```

**Code**:
```java
Booking[] newBookings = new Booking[myBookingsCount + 1];
System.arraycopy(myBookings, 0, newBookings, 0, myBookingsCount);
newBookings[myBookingsCount] = booking;
myBookings = newBookings;
myBookingsCount++;
```

**Better Alternative**: Use `ArrayList<Booking>` for automatic growth

### Booking Process Flow (10 Steps)
```
1. Input Route → Show matching buses
2. Select Bus ID → Validate exists
3. Enter Passenger Details → Name, gender, age, contact
4. Enter Journey Date → Validate not in past
5. Enter Ticket Count → Check availability
6. Select Seat Numbers → Validate each seat
7. Create Booking → Calculate fare
8. Confirm Booking → User approval
9. Select Payment Method → 4 options
10. Process Payment → Book seats, add to list, generate receipt
```

### Search Capabilities
1. **By Route**: Origin + Destination (case-insensitive)
2. **By Type**: AC/NonAC/Sleeper/Express (case-insensitive)
3. **View All**: No filters

### Validation Points
1. **Contact**: Must be exactly 10 digits (regex)
2. **Journey Date**: Cannot be in past
3. **Ticket Count**: Cannot exceed available seats
4. **Seat Selection**: Must be available and valid range
5. **Booking ID**: Must exist and not be cancelled
6. **Payment Method**: Must be 1-4

### Refund Policy Implementation
**Tiered Refunds** based on hours before journey:
- **≥24 hours**: 90% refund
- **12-23 hours**: 50% refund
- **6-11 hours**: 25% refund
- **<6 hours**: 0% refund

**Calculated in**: Booking.calculateRefund()  
**Applied in**: cancelTicket() method

### User Experience Features
1. **Search Before Book**: Shows available buses first
2. **Seat Availability Check**: Real-time validation
3. **Fare Preview**: Shows cost before confirmation
4. **Confirmation Points**: Booking and cancellation both require "yes"
5. **Receipt Generation**: Complete booking details
6. **Status Tracking**: CONFIRMED vs CANCELLED
7. **Error Messages**: Specific feedback for issues

### Data Relationships
```
Passenger
    ↓ has many
  Booking[]
    ↓ has reference to
   Bus
```

**Access Pattern**:
- Passenger stores own bookings
- Each booking references a Bus
- Bus accessed via AdminDashboard.buses array

### Exception Handling Strategy
**Try-Catch Blocks**:
- **bookTicket()**: Comprehensive exception handling
  - Multi-catch for specific exceptions
  - General catch for others
- **Other methods**: No try-catch (simpler operations)

### Key Java Concepts
1. **Inheritance**: Extends User class
2. **Method Overriding**: showProfile() customized
3. **Dynamic Arrays**: Manual array growth
4. **Enhanced For Loop**: Iterating seat arrays
5. **Regex Validation**: Contact number pattern
6. **Date/Time API**: Java 8+ LocalDate
7. **Ternary Operator**: Status display
8. **Multi-Catch**: Multiple exception types
9. **Static Access**: AdminDashboard.buses
10. **Object References**: Bus and Booking objects

### Integration Points
**Accesses**:
- AdminDashboard.buses (static array)
- AdminDashboard.busCount (static counter)
- Bus methods (show, getId, bookSeat, etc.)
- Booking class (create and manage bookings)

**Called By**:
- BusBookingSystem.main() creates Passenger
- Calls display() to show menu

### Potential Improvements
1. **Input Validation**: Add try-catch in display() menu
2. **ArrayList Usage**: Replace manual array growth
3. **Profile Feature**: Actually use showProfile() somewhere
4. **Persistence**: Save bookings to file/database
5. **Search Enhancement**: Combine filters (route + type)
6. **Booking History**: More detailed view with dates
7. **Edit Booking**: Modify before journey date
8. **Multi-Passenger**: Book for multiple passengers at once
9. **Payment Gateway**: Integrate real payment processing
10. **Email Confirmation**: Send receipt via email

### Complete User Journey Example
```
1. Main menu → Select "2. User Menu"
2. Passenger menu displays
3. Select "2. Search Bus by Route"
4. Enter: From=Mumbai, To=Pune
5. System shows matching buses
6. Select "4. Book Ticket"
7. Enter route again (Mumbai → Pune)
8. Enter Bus ID: 1
9. Enter passenger details (name, gender, age, contact)
10. Enter journey date: 20/12/2025
11. Enter number of tickets: 2
12. Select seats: 5, 6
13. See fare: Rs.400
14. Confirm: yes
15. Choose payment: 2 (UPI)
16. Payment processes
17. Seats booked
18. Receipt generated
19. Back to menu
20. Select "6. View My Bookings"
21. See: Booking ID: 1001 | Status: CONFIRMED
22. Select "7. Back to Main Menu"
23. Return to BusBookingSystem main menu
```

This class provides the complete passenger-facing functionality of the bus booking system!

---

## PROJECT COMPLETE! 🎉

All 6 files have been documented with detailed line-by-line explanations:
1. ✅ User.java
2. ✅ AdminLogin.java
3. ✅ BusBookingSystem.java
4. ✅ Bus.java
5. ✅ AdminDashboard.java
6. ✅ Passenger.java

Each file explanation includes:
- What each line does
- Why it's implemented that way
- How it works technically
- Usage examples
- Design patterns
- Relationships with other classes
- Complete summaries

All documentation is stored in the `documentation` folder!
