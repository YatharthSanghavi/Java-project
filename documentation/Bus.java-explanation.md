# Bus.java - Complete Code Explanation

## File Overview
**Purpose**: Contains core business logic classes for the Bus Booking System including custom exceptions, payment handling, bus management, and booking operations.

**Why**: Centralizes the main domain models and business rules for the application.

**How**: Defines exception classes, abstract classes, interfaces, and concrete implementations for buses and bookings.

---

## PART 1: CUSTOM EXCEPTIONS (Lines 1-17)

### Lines 1-2: Comment Header
```java
// ============= Custom Exceptions =============
class InvalidBusException extends Exception {
```
**What**: Comment and class declaration for custom bus exception  
**Why**: Custom exceptions provide specific error types for bus-related errors  
**How**: Extends Java's built-in `Exception` class  
**Purpose**: Handle bus-specific errors (invalid ID, invalid seats count, etc.)

---

### Line 3: Constructor
```java
    public InvalidBusException(String message) {
```
**What**: Constructor that accepts an error message  
**Why**: Allows creating exception with descriptive error message  
**How**: Public constructor with String parameter  
**Parameter**: `message` - describes what went wrong  
**Access**: `public` - can be thrown from anywhere

---

### Line 4: Super Call
```java
        super(message);
```
**What**: Calls parent Exception class constructor  
**Why**: Exception class needs the message for error reporting  
**How**: `super()` passes message up to Exception constructor  
**Effect**: Message is stored and can be retrieved with `getMessage()`  
**Required**: Must call super() when extending Exception

---

### Line 5-6: Closing Braces
```java
    }
}
```
**What**: Closes constructor and class  
**Usage Example**: 
```java
throw new InvalidBusException("Bus ID already exists!");
```

---

### Lines 8-11: InvalidSeatException Class
```java
class InvalidSeatException extends Exception {
    public InvalidSeatException(String message) {
        super(message);
    }
}
```
**What**: Custom exception for seat-related errors  
**Why**: Distinguish seat errors from other errors  
**How**: Same pattern as InvalidBusException  
**When Thrown**: 
- Invalid seat number (< 1 or > totalSeats)
- Seat already booked
- Seat doesn't exist

**Usage Example**:
```java
throw new InvalidSeatException("Seat 25 is already booked");
```

---

### Lines 13-17: PaymentFailedException Class
```java
class PaymentFailedException extends Exception {
    public InvalidPaymentException(String message) {
        super(message);
    }
}
```
**What**: Custom exception for payment errors  
**Why**: Handle payment processing failures specifically  
**How**: Extends Exception with message parameter  
**When Thrown**: Payment processing issues  
**Usage**: Currently defined but not actively used in the code

---

## PART 2: ABSTRACT PAYMENT CLASS (Lines 19-31)

### Lines 19-20: Abstract Class Declaration
```java
// ============= Abstract Class =============
abstract class Payment {
```
**What**: Abstract class defining payment contract  
**Why**: Provides common structure for all payment types  
**How**: `abstract` keyword - cannot be instantiated directly  
**Purpose**: Blueprint for payment implementations  
**Must Be**: Extended by concrete classes like Booking

---

### Line 21: Protected Variable - amount
```java
    protected double amount;
```
**What**: Variable to store payment amount  
**Why**: All payments need an amount  
**How**: Instance variable of type double  
**Access**: `protected` - accessible to subclasses  
**Type**: `double` - allows decimal values (Rs. 150.50)  
**Inheritance**: Subclasses can directly access this

---

### Line 22: Protected Variable - paymentId
```java
    protected String paymentId;
```
**What**: Variable to store unique payment identifier  
**Why**: Track and reference specific payments  
**How**: String type for flexibility (can include letters)  
**Access**: `protected` - subclasses can access  
**Purpose**: Payment tracking and receipts

---

### Line 24: Abstract Method - processPayment
```java
    abstract boolean processPayment();
```
**What**: Abstract method declaration (no implementation)  
**Why**: Every payment type must process, but logic varies  
**How**: `abstract` keyword - no body, ends with semicolon  
**Return**: `boolean` - true if payment succeeds, false otherwise  
**Contract**: All subclasses MUST implement this method  
**Purpose**: Handle actual payment processing logic

---

### Line 25: Abstract Method - generateReceipt
```java
    abstract void generateReceipt();
```
**What**: Abstract method for receipt generation  
**Why**: Every payment needs a receipt, but format varies  
**How**: Abstract method with no implementation  
**Return**: `void` - performs action, doesn't return value  
**Contract**: Subclasses must provide implementation  
**Purpose**: Create payment confirmation/receipt

---

### Lines 27-29: Concrete Method - displayPaymentInfo
```java
    void displayPaymentInfo() {
        System.out.println("Payment ID: " + paymentId + ", Amount: Rs." + amount);
    }
```
**What**: Concrete method with implementation  
**Why**: Common functionality all payments can use  
**How**: Regular method (not abstract) with body  
**Inheritance**: Subclasses inherit this method as-is  
**Can Be**: Overridden by subclasses if needed  
**Output Example**: `Payment ID: PAY123, Amount: Rs.500.0`

---

### Line 30-31: Closing Braces
```java
}
```
**What**: Closes the Payment abstract class  
**Summary**: Defines contract for payment operations

---

## PART 3: CANCELLABLE INTERFACE (Lines 33-38)

### Lines 33-34: Interface Declaration
```java
// ============= Interface =============
interface Cancellable {
```
**What**: Interface defining cancellation contract  
**Why**: Any class that supports cancellation must implement these methods  
**How**: `interface` keyword - pure contract, no implementation  
**Purpose**: Enforce cancellation capability  
**Implementation**: Classes use `implements Cancellable`

---

### Line 35: Method Signature - cancelBooking
```java
    void cancelBooking(int bookingId);
```
**What**: Method signature for cancelling a booking  
**Why**: Core cancellation functionality  
**How**: Method declaration (no body in interface)  
**Parameter**: `bookingId` - which booking to cancel  
**Return**: `void` - performs action  
**Implicit**: Methods in interfaces are public and abstract by default

---

### Line 36: Method Signature - calculateRefund
```java
    double calculateRefund(int daysBeforeJourney);
```
**What**: Method to calculate refund amount  
**Why**: Refund varies based on cancellation timing  
**How**: Takes days/hours before journey, returns refund amount  
**Parameter**: `daysBeforeJourney` - timing of cancellation  
**Return**: `double` - refund amount in currency  
**Purpose**: Implement refund policy logic

---

### Line 37: Method Signature - displayCancellationPolicy
```java
    void displayCancellationPolicy();
```
**What**: Method to show cancellation rules  
**Why**: Users need to know refund terms  
**How**: Display-only method  
**Return**: `void` - displays information  
**Purpose**: Transparency about cancellation terms

---

### Line 38: Closing Brace
```java
}
```
**What**: Closes Cancellable interface  
**Contract**: Any implementing class must provide all three methods

---

## PART 4: BUS CLASS (Lines 40-130)

### Lines 40-41: Class Declaration
```java
// ============= Enhanced Bus Class =============
class Bus {
```
**What**: Main Bus class declaration  
**Why**: Represents a bus in the system  
**How**: Regular class (not abstract)  
**Purpose**: Store bus details and manage seat bookings

---

### Line 42: Private Variable - id
```java
    private int id;
```
**What**: Unique bus identifier  
**Why**: Distinguish between different buses  
**How**: Private instance variable  
**Access**: `private` - only accessible within Bus class  
**Type**: `int` - numeric ID  
**Encapsulation**: Must use getter/setter to access

---

### Line 43: Private Variable - busNo
```java
    private String busNo;
```
**What**: Bus registration/license number  
**Why**: Official bus identification (like license plate)  
**How**: Private String variable  
**Example**: "MH-12-AB-1234"  
**Access**: Via `getBusNo()` getter method

---

### Lines 44-48: Package-Private Variables
```java
    String operator;
    String type;
    String from;
    String to;
    String time;
```
**What**: Bus details with package-private access  
**Why**: Frequently accessed by other classes in same package  
**Access**: No modifier = package-private  
**Details**:
- `operator` - bus company name (e.g., "Express Travels")
- `type` - bus category (AC/NonAC/Sleeper/Express)
- `from` - starting location
- `to` - destination location
- `time` - departure time (e.g., "10:30")

**Design Note**: Could be private with getters for better encapsulation

---

### Line 49: Private Variable - totalSeats
```java
    private int totalSeats;
```
**What**: Total number of seats in the bus  
**Why**: Define bus capacity  
**How**: Private int variable  
**Usage**: For seat validation and availability calculation  
**Access**: Via `getTotalSeats()` getter

---

### Line 50: Private Variable - seatStatus
```java
    private boolean[] seatStatus; // true = booked, false = available
```
**What**: Array tracking each seat's booking status  
**Why**: Know which seats are available or booked  
**How**: Boolean array where index = seat number  
**Type**: `boolean[]` - array of true/false values  
**Convention**: 
- `true` = seat is booked
- `false` = seat is available
**Comment**: Inline comment explains the convention  
**Size**: Created as `totalSeats + 1` (index 0 unused)

---

### Line 51: Private Variable - distanceKm
```java
    private double distanceKm;
```
**What**: Distance of the bus route in kilometers  
**Why**: Calculate fare based on distance  
**How**: Double for decimal precision  
**Example**: 250.5 km  
**Usage**: `baseFare = distanceKm * pricePerKm`

---

### Line 52: Private Variable - pricePerKm
```java
    private double pricePerKm;
```
**What**: Price charged per kilometer  
**Why**: Dynamic fare calculation  
**How**: Double for currency precision  
**Example**: Rs. 2.50 per km  
**Formula**: Total fare = distance × pricePerKm × seats

---

### Line 53: Static Variable - busCount
```java
    static int busCount = 0;
```
**What**: Static counter for total buses created  
**Why**: Track how many Bus objects exist  
**How**: `static` - shared across all instances  
**Access**: Class-level, not instance-level  
**Initialization**: Starts at 0  
**Incremented**: In constructor (line 73)  
**Usage**: `Bus.busCount` or `busCount` from within class

---

### Lines 55-57: Static Block
```java
    static {
        System.out.println("Static Block: Bus Booking System Initialized");
    }
```
**What**: Static initialization block  
**Why**: Execute code when class is first loaded  
**How**: `static { }` block runs once when class loads  
**When**: Before any object is created, before main() if this class is referenced  
**Purpose**: One-time initialization, logging, setup  
**Output**: Prints message when Bus class first used  
**Execution**: Only once per program run

---

### Lines 59-60: Constructor Declaration
```java
    public Bus(int id, String busNo, String operator, String type, String from, 
               String to, String time, int totalSeats, double distanceKm, double pricePerKm) {
```
**What**: Parameterized constructor for creating Bus objects  
**Why**: Initialize all bus properties at creation  
**How**: 10 parameters for complete bus setup  
**Access**: `public` - can create buses from anywhere  
**Parameters**:
1. `int id` - unique identifier
2. `String busNo` - bus number/registration
3. `String operator` - company name
4. `String type` - AC/NonAC/Sleeper/Express
5. `String from` - origin
6. `String to` - destination
7. `String time` - departure time
8. `int totalSeats` - capacity
9. `double distanceKm` - route distance
10. `double pricePerKm` - pricing rate

---

### Lines 61-70: Constructor Assignments
```java
        this.id = id;
        this.busNo = busNo;
        this.operator = operator;
        this.type = type;
        this.from = from;
        this.to = to;
        this.time = time;
        this.totalSeats = totalSeats;
        this.distanceKm = distanceKm;
        this.pricePerKm = pricePerKm;
```
**What**: Assigns parameters to instance variables  
**Why**: Initialize object state  
**How**: `this.variable = parameter` pattern  
**this Keyword**: Distinguishes instance variables from parameters  
**Effect**: Object now has all its properties set

---

### Line 71: Initialize seatStatus Array
```java
        this.seatStatus = new boolean[totalSeats + 1]; // index 0 unused
```
**What**: Creates boolean array for seat tracking  
**Why**: Need to track booking status of each seat  
**How**: `new boolean[size]` creates array  
**Size**: `totalSeats + 1` - makes 1-indexed (seat numbers start at 1)  
**Index 0**: Unused, allows seat numbers to match array indices  
**Default**: All elements initialize to `false` (available)  
**Example**: 40 seats → array size 41, indices 1-40 used  
**Comment**: Explains design decision

---

### Line 72: Increment busCount
```java
        busCount++;
```
**What**: Increments static bus counter  
**Why**: Track total buses created  
**How**: Post-increment operator  
**Effect**: `busCount` increases by 1  
**Static**: Updates class-level counter  
**Timing**: Every time a Bus object is created

---

### Line 73: Closing Brace
```java
    }
```
**What**: Closes constructor  
**Result**: Bus object fully initialized and ready to use

---

### Lines 75-77: Getter Methods (Simple)
```java
    // Getters
    public int getId() { return id; }
    public String getBusNo() { return busNo; }
```
**What**: Getter methods for private variables  
**Why**: Provide read access to private data  
**How**: Public methods that return private values  
**Pattern**: `public Type getVariable() { return variable; }`  
**Encapsulation**: Controlled access to private fields  
**Read-Only**: No setters for id and busNo (immutable after creation)

---

### Line 78: Getter - getTotalSeats
```java
    public int getTotalSeats() { return totalSeats; }
```
**What**: Returns total seat capacity  
**Usage**: Check bus capacity, display info

---

### Lines 79-80: Calculated Getter - getBaseFare
```java
    // Returns fare per seat = distance * pricePerKm
    public double getBaseFare() { return distanceKm * pricePerKm; }
```
**What**: Calculates and returns base fare per seat  
**Why**: Centralize fare calculation logic  
**How**: Multiplies distance by price rate  
**Formula**: `baseFare = distanceKm × pricePerKm`  
**Example**: 100 km × Rs.2 = Rs.200 per seat  
**Comment**: Explains the calculation  
**Computed**: Not stored, calculated on demand

---

### Lines 82-83: Getter Methods - Pricing
```java
    public double getDistanceKm() { return distanceKm; }
    public double getPricePerKm() { return pricePerKm; }
```
**What**: Getters for distance and price rate  
**Why**: Access pricing components  
**Usage**: Display route details, manual calculations

---

### Lines 85-86: Setter Methods - Pricing
```java
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
    public void setPricePerKm(double pricePerKm) { this.pricePerKm = pricePerKm; }
```
**What**: Setters to update pricing  
**Why**: Allow fare adjustments after creation  
**How**: Assign new value to instance variable  
**Validation**: None (should validate positive values)  
**Used By**: AdminDashboard when updating bus fare settings

---

### Lines 88-91: Setter Methods - Bus Details
```java
    // Setters
    public void setOperator(String operator) { this.operator = operator; }
    public void setType(String type) { this.type = type; }
    public void setTime(String time) { this.time = time; }
```
**What**: Setters for mutable bus properties  
**Why**: Allow updates to operator, type, and departure time  
**Used By**: AdminDashboard.updateBus() method  
**One-Line**: Compact setter format

---

### Lines 93-96: Method - isSeatAvailable
```java
    public boolean isSeatAvailable(int seatNo) {
        if (seatNo < 1 || seatNo > totalSeats) return false;
        return !seatStatus[seatNo];
    }
```
**What**: Checks if a specific seat is available for booking  
**Why**: Validate before booking  
**How**: Checks array value at seat index  

**Logic**:
1. Line 94: Validates seat number range
   - If `seatNo < 1` → invalid (too low)
   - If `seatNo > totalSeats` → invalid (too high)
   - Returns `false` for invalid seats
2. Line 95: Returns opposite of seatStatus
   - `seatStatus[seatNo]` is `true` (booked) → return `false` (not available)
   - `seatStatus[seatNo]` is `false` (free) → return `true` (available)

**! Operator**: Negation/NOT operator  
**Example**: 
- Seat 5 booked: `seatStatus[5] = true`, returns `!true = false`
- Seat 6 free: `seatStatus[6] = false`, returns `!false = true`

---

### Lines 98-106: Method - bookSeat
```java
    public void bookSeat(int seatNo) throws InvalidSeatException {
        if (seatNo < 1 || seatNo > totalSeats) {
            throw new InvalidSeatException("Invalid seat number: " + seatNo);
        }
        if (seatStatus[seatNo]) {
            throw new InvalidSeatException("Seat " + seatNo + " is already booked");
        }
        seatStatus[seatNo] = true;
    }
```
**What**: Books a seat (marks as occupied)  
**Why**: Reserve seat for a passenger  
**How**: Validates, then sets seatStatus to true  
**Throws**: `InvalidSeatException` for errors  

**Line 98**: Method signature with exception declaration  
- `throws InvalidSeatException` - caller must handle this

**Lines 99-101**: Validate seat number range  
- Throws exception with descriptive message if invalid

**Lines 102-104**: Check if already booked  
- `if (seatStatus[seatNo])` - if true, already booked
- Throws exception to prevent double-booking

**Line 105**: Mark seat as booked  
- `seatStatus[seatNo] = true` - seat now occupied
- Only reaches here if validations pass

**Error Handling**: Throws exceptions rather than returning boolean

---

### Lines 108-112: Method - cancelSeat
```java
    public void cancelSeat(int seatNo) {
        if (seatNo >= 1 && seatNo <= totalSeats) {
            seatStatus[seatNo] = false;
        }
    }
```
**What**: Cancels a seat booking (marks as available)  
**Why**: Free up seat when booking cancelled  
**How**: Sets seatStatus to false  

**Line 109**: Validation  
- Checks if seat number is valid range
- Uses `&&` (AND) - both conditions must be true

**Line 110**: Mark seat as available  
- `seatStatus[seatNo] = false` - seat now free

**Silent Failure**: Invalid seat numbers silently ignored (no exception)  
**Safe**: Won't crash if called with invalid seat number

---

### Lines 114-120: Method - getAvailableSeats
```java
    public int getAvailableSeats() {
        int count = 0;
        for (int i = 1; i <= totalSeats; i++) {
            if (!seatStatus[i]) count++;
        }
        return count;
    }
```
**What**: Counts how many seats are still available  
**Why**: Display availability, validate booking requests  
**How**: Loops through array counting false values  

**Line 115**: Initialize counter at 0

**Lines 116-118**: For loop  
- `i = 1` - start at seat 1 (skip index 0)
- `i <= totalSeats` - check all seats
- `i++` - increment by 1
- Line 117: If seat NOT booked (`!seatStatus[i]`), increment count

**Line 119**: Return total available seats

**Example**: 40 total seats, 10 booked → returns 30

---

### Lines 122-130: Method - show
```java
    void show() {
        System.out.println("==================================");
        System.out.println("Bus ID: " + id + ", Bus No: " + busNo);
        System.out.println("Operator: " + operator + ", Type: " + type);
        System.out.println("Route: " + from + " → " + to);
        System.out.println("Departure: " + time);
        System.out.println("Available Seats: " + getAvailableSeats() + "/" + totalSeats);
        System.out.println("Distance: " + distanceKm + " km | Price per km: Rs." + pricePerKm);
        System.out.println("Base Fare (per seat): Rs." + getBaseFare());
        System.out.println("==================================");
    }
```
**What**: Displays all bus information  
**Why**: Show bus details to users/admins  
**How**: Multiple println statements with formatted output  

**Line 123**: Top border

**Line 124**: ID and bus number

**Line 125**: Operator and type

**Line 126**: Route with arrow symbol (→)

**Line 127**: Departure time

**Line 128**: Availability (available/total)  
- Calls `getAvailableSeats()` method
- Shows fraction format: "30/40"

**Line 129**: Distance and price rate

**Line 130**: Calculated base fare  
- Calls `getBaseFare()` method

**Line 131**: Bottom border

**Output Example**:
```
==================================
Bus ID: 1, Bus No: MH-12-AB-1234
Operator: Express Travels, Type: AC
Route: Mumbai → Pune
Departure: 10:30
Available Seats: 35/40
Distance: 150.0 km | Price per km: Rs.2.5
Base Fare (per seat): Rs.375.0
==================================
```

---

### Line 132: Closing Brace
```java
}
```
**What**: Closes Bus class  
**Summary**: Complete bus entity with booking management

---

## PART 5: BOOKING CLASS (Lines 134-282)

### Lines 134-135: Class Declaration
```java
// ============= Enhanced Booking Class =============
class Booking extends Payment implements Cancellable {
```
**What**: Booking class declaration with inheritance and interface  
**Why**: Represents a ticket booking transaction  
**How**: 
- `extends Payment` - inherits from abstract Payment class
- `implements Cancellable` - must implement cancellation methods

**Multiple Inheritance**: Extends one class, implements one interface  
**Responsibilities**: 
- Payment processing (from Payment)
- Cancellation logic (from Cancellable)
- Booking management

---

### Line 136: Static Variable - bookingCounter
```java
    private static int bookingCounter = 1000;
```
**What**: Static counter for generating booking IDs  
**Why**: Ensure unique booking IDs across all bookings  
**How**: Static variable shared by all Booking objects  
**Initial Value**: 1000 (booking IDs start from 1001)  
**Incremented**: Each time a new booking is created  
**Access**: `private static` - class-level, internal only

---

### Lines 137-148: Instance Variables
```java
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
```
**What**: Private instance variables for booking data  
**Why**: Store all booking-related information  
**Access**: All `private` - strong encapsulation  

**Details**:
- `bookingId` - unique identifier
- `passengerName` - who is traveling
- `gender` - passenger gender
- `age` - used for discount calculation
- `contact` - phone number
- `bus` - reference to Bus object
- `seatNumbers` - array of booked seats
- `bookingDate` - when booking was made
- `journeyDate` - when travel happens
- `paymentMethod` - how payment was made
- `discount` - discount amount applied
- `finalAmount` - total after discount
- `isCancelled` - booking status flag

---

### Lines 150-151: Constructor Declaration
```java
    public Booking(String passengerName, String gender, int age, String contact,
                   Bus bus, int[] seatNumbers, String journeyDate) {
```
**What**: Constructor to create new booking  
**Why**: Initialize booking with required details  
**How**: 7 parameters for essential booking info  
**Parameters**:
1. `passengerName` - traveler's name
2. `gender` - M/F/Other
3. `age` - for discount eligibility
4. `contact` - phone number
5. `bus` - which bus is booked
6. `seatNumbers` - which seats
7. `journeyDate` - travel date

**Note**: bookingId generated automatically, not passed

---

### Line 152: Generate Booking ID
```java
        this.bookingId = ++bookingCounter;
```
**What**: Generates unique booking ID  
**Why**: Each booking needs unique identifier  
**How**: Pre-increment static counter  
**Operator**: `++bookingCounter` - increments THEN assigns  
**Example**: 
- First booking: bookingCounter becomes 1001, bookingId = 1001
- Second booking: bookingCounter becomes 1002, bookingId = 1002

**Alternative**: `bookingCounter++` would assign then increment (use old value)

---

### Lines 153-158: Simple Assignments
```java
        this.passengerName = passengerName;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.bus = bus;
        this.seatNumbers = seatNumbers;
```
**What**: Assigns parameters to instance variables  
**Pattern**: Standard constructor initialization  
**Line 157**: Stores reference to Bus object  
**Line 158**: Stores reference to seat array (not a copy)

---

### Line 159: Store Journey Date
```java
        this.journeyDate = journeyDate;
```
**What**: Assigns journey date  
**Format**: Expected as DD/MM/YYYY string

---

### Line 160: Generate Booking Date
```java
        this.bookingDate = java.time.LocalDateTime.now().toString();
```
**What**: Records current date/time as booking date  
**Why**: Track when booking was made  
**How**: Uses Java 8+ time API  
**Breakdown**:
- `java.time.LocalDateTime` - date-time class
- `.now()` - gets current date and time
- `.toString()` - converts to string format

**Output Example**: `"2025-11-14T10:30:45.123"`  
**Format**: ISO-8601 (YYYY-MM-DDTHH:MM:SS)

---

### Line 161: Initialize Cancelled Flag
```java
        this.isCancelled = false;
```
**What**: Sets booking as not cancelled initially  
**Why**: New bookings start as active  
**Default**: `false` - booking is confirmed

---

### Line 162: Closing Brace
```java
    }
```
**What**: Closes constructor  
**Result**: Booking object created and initialized

---

### Lines 164-177: Method - calculateFare
```java
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
```
**What**: Calculates total booking fare with discounts  
**Why**: Determine payment amount  
**How**: Base fare × seats, apply age-based discounts  

**Line 165**: Calculate base total  
- `bus.getBaseFare()` - fare per seat
- `seatNumbers.length` - number of seats booked
- Multiplication gives total before discount

**Lines 168-172**: Age-based discount logic  
- **Child** (< 12 years): 25% discount
- **Senior** (≥ 60 years): 30% discount  
- **Adult** (12-59): No discount

**Line 174**: Calculate final amount  
- Subtracts discount from total fare

**Line 175**: Set inherited amount variable  
- `this.amount` from Payment class
- Stores in parent class variable

**Line 176**: Return final amount

**Example**:
- Base fare: Rs.200, 2 seats, child (age 8)
- Total: 200 × 2 = Rs.400
- Discount: 400 × 0.25 = Rs.100
- Final: 400 - 100 = Rs.300

---

### Lines 179-183: Override - processPayment
```java
    @Override
    public boolean processPayment() {
        System.out.println("\n=== Processing Payment ===");
        System.out.println("Total Amount: Rs." + finalAmount);
        return true;
    }
```
**What**: Implements abstract method from Payment class  
**Why**: Required by Payment contract  
**How**: Displays processing message, returns success  

**@Override**: Annotation indicating method overrides parent  
**Line 180**: Method signature matches Payment.processPayment()  
**Lines 181-182**: Display payment processing message  
**Line 183**: Returns `true` - payment always succeeds (simplified)  
**Real-World**: Would integrate with payment gateway

---

### Lines 185-239: Override - generateReceipt
```java
    @Override
    public void generateReceipt() {
```
**What**: Generates detailed booking receipt  
**Why**: Provide confirmation and details to passenger  
**How**: Multiple println statements with all booking info  

**Lines 187-189**: Receipt header
```java
        System.out.println("=============================================");
        System.out.println("         BUS TICKET - BOOKING CONFIRMED      ");
        System.out.println("=============================================");
```
**Formatting**: Box with title centered

---

**Lines 190-194**: Passenger Details Section
```java
        System.out.println("\n--- PASSENGER DETAILS ---");
        System.out.println("Name: " + passengerName);
        System.out.println("Gender: " + gender);
        System.out.println("Age: " + age);
        System.out.println("Contact: " + contact);
```
**What**: Displays who is traveling  
**\n**: Newline for spacing

---

**Lines 196-199**: Booking Details Section
```java
        System.out.println("\n--- BOOKING DETAILS ---");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Journey Date: " + journeyDate);
```
**What**: When booking was made and travel date

---

**Lines 201-206**: Bus Details Section
```java
        System.out.println("\n--- BUS DETAILS ---");
        System.out.println("Bus No: " + bus.getBusNo());
        System.out.println("Bus Type: " + bus.type);
        System.out.println("Operator: " + bus.operator);
        System.out.println("From: " + bus.from + " To: " + bus.to);
        System.out.println("Departure Time: " + bus.time);
```
**What**: Which bus and route details  
**Access**: Uses both getter method and direct field access

---

**Lines 208-214**: Seat Details Section
```java
        System.out.println("\n--- SEAT DETAILS ---");
        System.out.print("Seat Numbers: ");
        for (int seat : seatNumbers) {
            System.out.print(seat + " ");
        }
        System.out.println("\nTotal Tickets: " + seatNumbers.length);
```
**What**: Which seats were booked  
**Lines 210-212**: Enhanced for loop  
- `for (int seat : seatNumbers)` - iterates through array
- Prints each seat number with space
- Example output: `Seat Numbers: 5 6 7 `

**Line 213**: Total count of tickets

---

**Lines 215-224**: Payment Details Section
```java
        System.out.println("\n--- PAYMENT DETAILS ---");
        System.out.println("Base Fare per Seat: Rs." + bus.getBaseFare());
        System.out.println("Subtotal: Rs." + (bus.getBaseFare() * seatNumbers.length));
        if (discount > 0) {
            System.out.println("Discount Applied: Rs." + discount);
        }
        System.out.println("Total Payable: Rs." + finalAmount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Status: PAID");
```
**What**: Fare breakdown and payment info  
**Line 217**: Fare per single seat  
**Line 218**: Subtotal before discount  
**Lines 219-221**: Only shows discount if applicable  
**Line 222**: Final amount paid  
**Line 223**: How payment was made  
**Line 224**: Status confirmation

---

**Line 226**: Call to displayCancellationPolicy()
```java
        displayCancellationPolicy();
```
**What**: Shows refund policy on receipt  
**Why**: Inform passenger of cancellation terms

---

**Lines 228-232**: Terms & Conditions
```java
        System.out.println("\n--- TERMS & CONDITIONS ---");
        System.out.println("1. Please arrive 15 minutes before departure");
        System.out.println("2. Carry a valid ID proof during journey");
        System.out.println("3. No refund for no-show passengers");
        System.out.println("4. Management reserves the right to change timings");
```
**What**: Important travel guidelines and disclaimers

---

**Lines 234-237**: Receipt Footer
```java
        System.out.println("=============================================");
        System.out.println("     Thank you for choosing our service!     ");
        System.out.println("=============================================");
    }
```
**What**: Closing message and border  
**Purpose**: Professional, complete receipt

---

### Lines 239-245: Override - displayCancellationPolicy
```java
    @Override
    public void displayCancellationPolicy() {
        System.out.println("\n--- CANCELLATION POLICY ---");
        System.out.println("• 24+ hours before departure: 90% refund");
        System.out.println("• 12-24 hours before: 50% refund");
        System.out.println("• 6-12 hours before: 25% refund");
        System.out.println("• Less than 6 hours: No refund");
    }
```
**What**: Displays refund policy based on timing  
**Why**: Required by Cancellable interface  
**How**: Shows tiered refund percentages  

**Refund Tiers**:
1. **24+ hours**: 90% refund (10% cancellation fee)
2. **12-24 hours**: 50% refund
3. **6-12 hours**: 25% refund
4. **< 6 hours**: 0% refund (no refund)

**Bullet Points**: • (bullet character for formatting)

---

### Lines 247-252: Override - calculateRefund
```java
    @Override
    public double calculateRefund(int hoursBeforeJourney) {
        if (hoursBeforeJourney >= 24) return finalAmount * 0.90; // provide 90% refund
        else if (hoursBeforeJourney >= 12) return finalAmount * 0.50; // provide 50% refund
        else if (hoursBeforeJourney >= 6) return finalAmount * 0.25; // provide 25% refund
        else return 0;
    }
```
**What**: Calculates refund amount based on cancellation timing  
**Why**: Required by Cancellable interface  
**How**: Implements the policy shown above  

**Parameter**: `hoursBeforeJourney` - how many hours until departure

**Logic**:
- **Line 249**: ≥24 hours → 90% of finalAmount
- **Line 250**: ≥12 hours → 50% of finalAmount  
- **Line 251**: ≥6 hours → 25% of finalAmount
- **Line 252**: <6 hours → 0 (no refund)

**Example**: 
- Booking: Rs.1000
- Cancel 20 hours before: 1000 × 0.50 = Rs.500 refund

**Inline Comments**: Explain refund percentage

---

### Lines 254-262: Override - cancelBooking
```java
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
```
**What**: Cancels a booking and frees seats  
**Why**: Required by Cancellable interface  
**How**: Validates, frees seats, updates status  

**Line 256**: Validation check  
- `this.bookingId == bookingId` - correct booking
- `!isCancelled` - not already cancelled
- Both must be true (&&)

**Lines 257-259**: Free all booked seats  
- Enhanced for loop through seatNumbers array
- Calls `bus.cancelSeat()` for each seat
- Makes seats available again

**Line 260**: Mark as cancelled  
- Sets `isCancelled = true`
- Prevents re-cancellation

**Line 261**: Confirmation message

**Silent**: If ID doesn't match or already cancelled, does nothing

---

### Lines 264-266: Setter - setPaymentMethod
```java
    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }
```
**What**: Sets how payment was made  
**Why**: Store payment method after user selection  
**How**: Simple setter  
**Called**: In Passenger.bookTicket() after user chooses method

---

### Lines 268-270: Getter - getBookingId
```java
    public int getBookingId() {
        return bookingId;
    }
```
**What**: Returns booking ID  
**Why**: Access booking identifier  
**Usage**: For cancellation, viewing bookings

---

### Lines 272-274: Getter - isCancelled
```java
    public boolean isCancelled() {
        return isCancelled;
    }
```
**What**: Checks if booking is cancelled  
**Why**: Verify booking status  
**Returns**: `true` if cancelled, `false` if active  
**Usage**: Filter cancelled bookings in lists

---

### Lines 275-276: Closing Braces
```java
    }
}
```
**What**: Closes Booking class  
**Summary**: Complete booking management with payment and cancellation

---

## COMPLETE SUMMARY

### File Structure
1. **Custom Exceptions** (3 classes)
   - InvalidBusException
   - InvalidSeatException  
   - PaymentFailedException

2. **Abstract Class** (1)
   - Payment

3. **Interface** (1)
   - Cancellable

4. **Concrete Classes** (2)
   - Bus
   - Booking

### Object-Oriented Concepts

**Inheritance**:
- Booking `extends` Payment
- All exception classes `extend` Exception

**Interface Implementation**:
- Booking `implements` Cancellable

**Abstract Methods**:
- Payment defines abstract methods
- Booking provides concrete implementations

**Encapsulation**:
- Private fields with public getters/setters
- Controlled access to data

**Polymorphism**:
- Method overriding (@Override)
- Interface method implementation

**Static Members**:
- Bus.busCount
- Booking.bookingCounter
- Static initialization block

### Design Patterns

**Template Method**: Payment class defines template
**Strategy Pattern**: Different payment/cancellation strategies
**Factory Pattern**: Constructors create objects with validation

### Key Features

**Bus Management**:
- Track seat availability
- Book/cancel individual seats
- Calculate fares dynamically
- Display bus information

**Booking System**:
- Unique booking IDs
- Age-based discounts
- Payment processing
- Receipt generation
- Tiered cancellation policy

**Error Handling**:
- Custom exceptions for specific errors
- Validation at multiple levels
- Descriptive error messages

### Relationships

```
Exception
    ↑
    ├── InvalidBusException
    ├── InvalidSeatException
    └── PaymentFailedException

Payment (abstract)
    ↑
    └── Booking

Cancellable (interface)
    ↑
    └── Booking

Bus ←── Booking (has-a relationship)
```

### Usage Flow

1. **Create Bus**: Admin adds bus to system
2. **Create Booking**: Passenger books seats
3. **Calculate Fare**: Apply discounts
4. **Process Payment**: Handle payment
5. **Generate Receipt**: Provide confirmation
6. **Cancel (optional)**: Refund based on timing

This file forms the core business logic of the entire bus booking system!
