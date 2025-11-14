# AdminDashboard.java - Complete Code Explanation

## File Overview
**Purpose**: Provides administrative interface for managing buses in the Bus Booking System.

**Why**: Admins need CRUD (Create, Read, Update, Delete) operations on bus records.

**How**: Implements methods for adding, viewing, updating, and deleting buses with a menu-driven interface.

---

## Line-by-Line Explanation

### Line 1: Import Statement
```java
import java.util.Scanner;
```
**What**: Imports Scanner class for reading user input  
**Why**: Admin needs to input bus details and menu choices  
**How**: Makes Scanner available without fully qualified name  
**Package**: `java.util` - Java's utility library  
**Alternative**: Could use `java.util.*` to import all utilities

---

### Line 3: Class Declaration
```java
class AdminDashboard {
```
**What**: Declares the AdminDashboard class  
**Why**: Encapsulates all admin functionality  
**Access**: Package-private (no modifier)  
**Purpose**: Admin control panel for bus management  
**Called By**: AdminLogin after successful authentication

---

### Line 4: Scanner Instance Variable
```java
    private Scanner sc = new Scanner(System.in);
```
**What**: Creates Scanner instance for this dashboard  
**Why**: Read input throughout admin operations  
**How**: Instance variable initialized at declaration  
**Access**: `private` - only this class uses it  
**System.in**: Standard input stream (keyboard)  
**Scope**: Available to all methods in this class  
**Resource**: Should be closed, but program termination handles it

---

### Line 5: Static Bus Array
```java
    static Bus[] buses = new Bus[100];
```
**What**: Static array to store all buses in the system  
**Why**: Central storage accessible by all parts of application  
**How**: Static array with fixed size of 100  
**Type**: `Bus[]` - array of Bus objects  
**Access**: `static` - shared across all instances  
**Capacity**: Maximum 100 buses  
**Initial State**: All elements are `null`  
**Shared**: Passenger class also accesses this array  
**Design**: Global data store (not ideal, but simple for learning)

---

### Line 6: Static Bus Counter
```java
    static int busCount = 0;
```
**What**: Tracks how many buses are currently stored  
**Why**: Know how many valid entries exist in buses array  
**How**: Static variable incremented on add, decremented on delete  
**Initial Value**: 0 - no buses initially  
**Usage**: Loop boundary (`i < busCount`)  
**Static**: Shared across all dashboard instances  
**Also Used By**: Passenger class for searching/viewing buses

---

## METHOD 1: addBus() - Lines 8-78

### Line 8: Method Declaration
```java
    void addBus() {
```
**What**: Method to add a new bus to the system  
**Why**: Admin needs to create bus records  
**How**: Collects bus details and validates before adding  
**Return**: `void` - performs action, no return value  
**Access**: Package-private  
**Called By**: display() method when admin selects option 1

---

### Line 9: Try Block Start
```java
        try {
```
**What**: Begins try-catch block for exception handling  
**Why**: Input validation and error handling  
**How**: Catches InvalidBusException and general Exception  
**Protects**: Against invalid input, duplicate IDs, capacity limits

---

### Line 10: Header Message
```java
            System.out.println("\n=== ADD NEW BUS ===");
```
**What**: Prints section header  
**Why**: Visual organization and user clarity  
**\n**: Newline for spacing from previous content

---

### Lines 12-13: Input Bus ID
```java
            System.out.print("Enter Bus ID: ");
            int id = sc.nextInt();
```
**What**: Prompts and reads bus ID  
**Why**: Each bus needs unique identifier  
**Line 12**: Prompt without newline (cursor on same line)  
**Line 13**: Reads integer from input  
**Validation**: Checked for uniqueness in lines 16-20

---

### Lines 15-20: Duplicate ID Check
```java
            // Check if ID already exists
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    throw new InvalidBusException("Bus ID " + id + " already exists!");
                }
            }
```
**What**: Validates that bus ID is unique  
**Why**: Prevent duplicate IDs in the system  
**How**: Loops through existing buses checking ID  

**Line 15**: Comment explaining validation  
**Line 16**: For loop iterating existing buses  
- `i = 0` - start at first bus
- `i < busCount` - check only filled positions
- `i++` - increment counter

**Line 17**: Check if current bus has same ID  
**Line 18**: Throw custom exception if duplicate found  
**Exception Message**: Includes the duplicate ID for clarity  
**Effect**: Jumps to catch block on line 73

---

### Lines 22-23: Input Bus Number
```java
            System.out.print("Enter Bus Number: ");
            String busNo = sc.next();
```
**What**: Reads bus registration number  
**Why**: Official bus identification (like license plate)  
**Method**: `next()` - reads one token (no spaces)  
**Example**: "MH-12-AB-1234"

---

### Lines 25-27: Input Bus Operator
```java
            System.out.print("Enter Bus Operator: ");
            sc.nextLine(); // consume newline
            String operator = sc.nextLine();
```
**What**: Reads bus company/operator name  
**Why**: Identify which company operates the bus  

**Line 26**: `sc.nextLine()` - CRITICAL LINE  
**Purpose**: Consume leftover newline from previous `nextInt()`  
**Problem**: `nextInt()` leaves `\n` in buffer  
**Solution**: Call `nextLine()` to clear it  
**Without This**: `nextLine()` on line 27 would read empty string  

**Line 27**: Reads full line (can include spaces)  
**Example**: "Express Travels Limited"  
**Method**: `nextLine()` - reads until newline

---

### Lines 29-30: Input Bus Type
```java
            System.out.print("Enter Bus Type (AC/NonAC/Sleeper/Express): ");
            String type = sc.next();
```
**What**: Reads bus category  
**Why**: Classify bus for searching and filtering  
**Options**: Prompt suggests valid types  
**Validation**: None - accepts any string  
**Improvement**: Could use enum for type safety

---

### Lines 32-33: Input From Location
```java
            System.out.print("Enter From Location: ");
            String from = sc.next();
```
**What**: Reads departure city/location  
**Why**: Define route starting point  
**Method**: `next()` - single word (no spaces)  
**Example**: "Mumbai", "Delhi"

---

### Lines 35-36: Input To Location
```java
            System.out.print("Enter To Location: ");
            String to = sc.next();
```
**What**: Reads destination city/location  
**Why**: Define route ending point  
**Example**: "Pune", "Bangalore"

---

### Lines 38-39: Input Departure Time
```java
            System.out.print("Enter Departure Time (HH:MM): ");
            String time = sc.next();
```
**What**: Reads departure time  
**Why**: Passengers need to know when bus leaves  
**Format**: Suggested as HH:MM (24-hour format)  
**Validation**: None - accepts any string  
**Example**: "10:30", "23:45"  
**Improvement**: Could validate time format

---

### Lines 41-42: Input Number of Seats
```java
            System.out.print("Enter Number of Seats: ");
            int seats = sc.nextInt();
```
**What**: Reads total seat capacity  
**Why**: Define how many passengers bus can hold  
**Type**: `int` - whole number  
**Validation**: Checked in lines 44-46

---

### Lines 44-46: Validate Seats
```java
            if (seats <= 0) {
                throw new InvalidBusException("Number of seats must be positive!");
            }
```
**What**: Validates seat count is positive  
**Why**: Bus must have at least 1 seat  
**Condition**: `seats <= 0` - zero or negative  
**Action**: Throws exception with error message  
**Effect**: Jumps to catch block, add operation fails

---

### Lines 48-52: Input and Validate Distance
```java
            System.out.print("Enter Distance (km): ");
            double distanceKm = sc.nextDouble();
            if (distanceKm <= 0) {
                throw new InvalidBusException("Distance must be positive!");
            }
```
**What**: Reads and validates route distance  
**Why**: Used for fare calculation  
**Type**: `double` - allows decimals (150.5 km)  
**Line 49**: Reads decimal number  
**Lines 50-52**: Validation - must be positive  
**Business Rule**: Route must have positive distance

---

### Lines 54-58: Input and Validate Price per km
```java
            System.out.print("Enter Price per km (Rs.): ");
            double pricePerKm = sc.nextDouble();
            if (pricePerKm <= 0) {
                throw new InvalidBusException("Price per km must be positive!");
            }
```
**What**: Reads and validates pricing rate  
**Why**: Determine fare calculation basis  
**Type**: `double` - currency values (Rs. 2.50)  
**Validation**: Must be positive  
**Usage**: `fare = distance × pricePerKm × seats`

---

### Line 60: Create Bus Object
```java
            Bus newBus = new Bus(id, busNo, operator, type, from, to, time, seats, distanceKm, pricePerKm);
```
**What**: Creates new Bus object with collected data  
**Why**: Instantiate bus with all properties  
**How**: Calls Bus constructor with 10 parameters  
**Variable**: `newBus` - reference to created object  
**Constructor**: Initializes all bus fields and seat array  
**Effect**: Bus object ready to be added to system

---

### Lines 62-68: Add to Array or Reject
```java
            if (busCount < buses.length) {
                buses[busCount++] = newBus;
                System.out.println("\n✓ Bus added successfully!");
                newBus.show();
            } else {
                throw new InvalidBusException("Bus limit reached. Cannot add more buses.");
            }
```
**What**: Adds bus to array if space available  
**Why**: System has limited capacity (100 buses)  

**Line 62**: Check if array has space  
- `busCount < buses.length` (busCount < 100)

**Line 63**: Add and increment counter  
- `buses[busCount++]` - post-increment
- Assigns to current index, then increments
- Example: If busCount=5, assigns to buses[5], then busCount becomes 6

**Line 64**: Success message with checkmark symbol  
**Line 65**: Display newly added bus details  
**Lines 66-67**: Else - array is full  
**Line 67**: Throw exception - system at capacity

---

### Lines 70-76: Exception Handling
```java
        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Invalid input. Please try again.");
            sc.nextLine();
        }
    }
```
**What**: Catches and handles exceptions  
**Why**: Provide user-friendly error messages  

**Lines 70-71**: Catch custom InvalidBusException  
- Displays specific error message (duplicate ID, invalid values)
- `e.getMessage()` retrieves exception's message

**Lines 72-76**: Catch all other exceptions  
- Generic catch for InputMismatchException, etc.
- Line 74: Generic error message
- Line 75: **CRITICAL** - clears input buffer with `nextLine()`
- Without line 75: Invalid input remains, causes infinite loop

**X Symbol**: ✗ indicates error visually

---

## METHOD 2: viewAllBuses() - Lines 78-90

### Line 78: Method Declaration
```java
    void viewAllBuses() {
```
**What**: Method to display all buses  
**Why**: Admin needs to see all available buses  
**Access**: Package-private  
**Called By**: display() method (option 2), also called before update/delete

---

### Lines 79-82: Empty Check
```java
        if (busCount == 0) {
            System.out.println("\nNo buses available.");
            return;
        }
```
**What**: Handles empty bus list  
**Why**: Avoid showing empty list  
**Line 79**: Check if no buses exist  
**Line 80**: Inform user  
**Line 81**: Exit method early with `return`  
**Effect**: Rest of method skipped if no buses

---

### Lines 84-88: Display All Buses
```java
        System.out.println("\n=== ALL BUSES ===\n");
        for (int i = 0; i < busCount; i++) {
            buses[i].show();
        }
    }
```
**What**: Loops through and displays each bus  
**Line 84**: Section header with spacing  
**Line 85**: For loop through valid buses  
- Only iterates filled positions (`i < busCount`)
**Line 86**: Calls each bus's `show()` method  
- Displays formatted bus information
**Result**: Complete list of all buses with details

---

## METHOD 3: updateBus() - Lines 90-204

### Line 90: Method Declaration
```java
    void updateBus() {
```
**What**: Method to modify existing bus details  
**Why**: Admin needs to correct or update bus information  
**How**: Finds bus by ID, offers update options, applies changes  
**Complex**: Most complex method with multiple update options

---

### Lines 91-94: Empty Check
```java
        if (busCount == 0) {
            System.out.println("No buses available to update.");
            return;
        }
```
**What**: Validates buses exist before updating  
**Why**: Can't update if no buses in system  
**Early Return**: Exits method if empty

---

### Line 96: Display Buses First
```java
        viewAllBuses();
```
**What**: Shows all buses before asking for ID  
**Why**: Admin can see available buses and their IDs  
**User Experience**: Informed choice of which bus to update

---

### Line 98: Try Block Start
```java
        try {
```
**What**: Exception handling for update process  
**Catches**: InvalidBusException, general Exception

---

### Lines 99-100: Input Bus ID
```java
            System.out.print("\nEnter Bus ID to update: ");
            int id = sc.nextInt();
```
**What**: Get ID of bus to update  
**Why**: Identify which bus to modify

---

### Lines 102-108: Find Target Bus
```java
            Bus targetBus = null;
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    targetBus = buses[i];
                    break;
                }
            }
```
**What**: Searches for bus with matching ID  
**Why**: Need reference to the bus object  

**Line 102**: Initialize as null (not found)  
**Lines 103-107**: Loop through buses  
**Line 104**: Check if ID matches  
**Line 105**: Store reference to matching bus  
**Line 106**: `break` - exit loop early (found it)  
**Result**: `targetBus` either points to bus or remains null

---

### Lines 110-112: Validate Bus Found
```java
            if (targetBus == null) {
                throw new InvalidBusException("Bus with ID " + id + " not found!");
            }
```
**What**: Checks if bus was found  
**Why**: Can't update non-existent bus  
**Condition**: If still null, bus doesn't exist  
**Action**: Throw exception with ID in message

---

### Lines 114-121: Update Options Menu
```java
            System.out.println("\n=== UPDATE OPTIONS ===");
            System.out.println("1. Operator");
            System.out.println("2. Type");
            System.out.println("3. From Location");
            System.out.println("4. To Location");
            System.out.println("5. Departure Time");
            System.out.println("6. All Details");
            System.out.println("7. Fare Settings (Distance & Price per km)");
            System.out.print("Choose what to update: ");
```
**What**: Displays menu of what can be updated  
**Why**: Admin chooses specific field(s) to update  
**Options**:
1. Just operator name
2. Just bus type
3. Just origin location
4. Just destination
5. Just departure time
6. Update all fields at once
7. Update fare calculation parameters

**Design**: Granular control - update only what's needed

---

### Line 123: Read Update Choice
```java
            int choice = sc.nextInt();
```
**What**: Reads which update option user selects  
**Type**: `int` for switch statement

---

### Lines 125-127: Validate Choice
```java
            if (choice < 1 || choice > 7) {
                throw new InvalidBusException("Invalid choice!");
            }
```
**What**: Validates choice is in valid range  
**Why**: Only 7 options available  
**Condition**: Less than 1 OR greater than 7  
**Action**: Throw exception if invalid

---

### Lines 129-193: Switch Statement for Updates
```java
            switch(choice) {
```
**What**: Multi-way branch based on user choice  
**Why**: Different code for each update option  
**Cases**: 1-7 corresponding to menu options

---

### Lines 130-135: Case 1 - Update Operator
```java
                case 1:
                    System.out.print("Enter new operator: ");
                    sc.nextLine();
                    String operator = sc.nextLine();
                    targetBus.setOperator(operator);
                    break;
```
**What**: Updates only the operator name  
**Line 131**: Prompt for new operator  
**Line 132**: Clear input buffer (after nextInt)  
**Line 133**: Read full line (can have spaces)  
**Line 134**: Call setter method to update  
**Line 135**: Exit switch

---

### Lines 137-141: Case 2 - Update Type
```java
                case 2:
                    System.out.print("Enter new bus type: ");
                    String type = sc.next();
                    targetBus.setType(type);
                    break;
```
**What**: Updates only bus type  
**Line 139**: Read new type (single word)  
**Line 140**: Update via setter method  
**No buffer clear**: `next()` doesn't require it here

---

### Lines 143-147: Case 3 - Update From Location
```java
                case 3:
                    System.out.print("Enter new from location: ");
                    String from = sc.next();
                    targetBus.from = from;
                    break;
```
**What**: Updates origin location  
**Line 145**: Direct field access (package-private)  
**Note**: Could use setter if it existed

---

### Lines 149-153: Case 4 - Update To Location
```java
                case 4:
                    System.out.print("Enter new to location: ");
                    String to = sc.next();
                    targetBus.to = to;
                    break;
```
**What**: Updates destination location  
**Similar**: Direct field assignment

---

### Lines 155-159: Case 5 - Update Departure Time
```java
                case 5:
                    System.out.print("Enter new departure time: ");
                    String time = sc.next();
                    targetBus.setTime(time);
                    break;
```
**What**: Updates departure time  
**Uses Setter**: `setTime()` method

---

### Lines 161-179: Case 6 - Update All Details
```java
                case 6:
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
```
**What**: Updates all basic details at once  
**Why**: Convenient for major changes  
**How**: Combines logic from cases 1-5  
**Note**: Reuses variable names (operator, type, from, to, time)  
**Scope**: Variables declared in case 6 block

---

### Lines 180-193: Case 7 - Update Fare Settings
```java
                case 7:
                    System.out.print("Enter new distance (km): ");
                    double newDistance = sc.nextDouble();
                    if (newDistance <= 0) throw new InvalidBusException("Distance must be positive!");
                    System.out.print("Enter new price per km (Rs.): ");
                    double newPrice = sc.nextDouble();
                    if (newPrice <= 0) throw new InvalidBusException("Price per km must be positive!");
                    targetBus.setDistanceKm(newDistance);
                    targetBus.setPricePerKm(newPrice);
                    break;
```
**What**: Updates fare calculation parameters  
**Why**: Admin can adjust pricing  

**Lines 181-183**: Get and validate distance  
- Line 183: Inline validation with throw

**Lines 184-186**: Get and validate price per km  
- Line 186: Inline validation

**Lines 187-188**: Update both values using setters  
**Validation**: Ensures positive values before setting

---

### Lines 195-196: Success Message
```java
            System.out.println("\n✓ Bus updated successfully!");
            targetBus.show();
```
**What**: Confirms update and shows new details  
**Why**: User feedback and verification  
**Line 196**: Displays updated bus information

---

### Lines 198-204: Exception Handling
```java
        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Invalid input. Please try again.");
            sc.nextLine();
        }
    }
```
**What**: Handles errors during update  
**Same Pattern**: As addBus() method  
**Lines 198-199**: Custom exception handling  
**Lines 200-204**: General exception handling with buffer clear

---

## METHOD 4: deleteBus() - Lines 206-247

### Line 206: Method Declaration
```java
    void deleteBus() {
```
**What**: Method to remove a bus from the system  
**Why**: Admin needs to delete obsolete or incorrect buses  
**How**: Finds bus, confirms deletion, removes from array

---

### Lines 207-210: Empty Check
```java
        if (busCount == 0) {
            System.out.println("No buses to delete.");
            return;
        }
```
**What**: Validates buses exist  
**Why**: Can't delete from empty list  
**Early Exit**: Returns if no buses

---

### Line 212: Display Buses
```java
        viewAllBuses();
```
**What**: Shows buses before deletion  
**Why**: Admin sees what's available to delete

---

### Line 214: Try Block Start
```java
        try {
```
**What**: Exception handling for delete operation

---

### Lines 215-216: Input Bus ID
```java
            System.out.print("\nEnter Bus ID to delete: ");
            int id = sc.nextInt();
```
**What**: Get ID of bus to delete  
**Why**: Identify target bus

---

### Lines 218-224: Find Bus Index
```java
            int index = -1;
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    index = i;
                    break;
                }
            }
```
**What**: Finds array index of bus to delete  
**Why**: Need position to remove from array  
**Difference**: Stores index, not bus reference  
**Line 218**: Initialize as -1 (not found marker)  
**Line 222**: Store matching index  
**Line 223**: Break early when found

---

### Lines 226-228: Validate Bus Found
```java
            if (index == -1) {
                throw new InvalidBusException("Bus with ID " + id + " not found!");
            }
```
**What**: Checks if bus exists  
**Condition**: If still -1, not found  
**Action**: Throw exception

---

### Lines 230-231: Confirmation Prompt
```java
            System.out.print("Are you sure you want to delete this bus? (yes/no): ");
            String confirm = sc.next();
```
**What**: Asks for deletion confirmation  
**Why**: Prevent accidental deletion (safety measure)  
**Security**: Double-check before destructive action  
**Input**: Expects "yes" or "no"

---

### Lines 233-242: Conditional Deletion
```java
            if (confirm.equalsIgnoreCase("yes")) {
                for (int j = index; j < busCount - 1; j++) {
                    buses[j] = buses[j + 1];
                }
                buses[--busCount] = null;
                System.out.println("\n✓ Bus with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
```
**What**: Deletes bus if confirmed, otherwise cancels  

**Line 233**: Check confirmation  
- `equalsIgnoreCase()` - accepts "yes", "Yes", "YES"

**Lines 234-236**: Shift array elements  
- **Purpose**: Remove gap by moving elements left
- **Line 234**: Loop from deletion point to second-last element
- **Line 235**: Copy next element to current position
- **Example**: Delete index 2
  - buses[2] = buses[3]
  - buses[3] = buses[4]
  - buses[4] = buses[5]
  - etc.

**Line 237**: Clean up and decrement count  
- `--busCount` - pre-decrement (decrement first)
- Sets last position to null
- Example: If busCount was 10, becomes 9, sets buses[9] = null

**Line 238**: Success message  

**Lines 239-241**: Else block - cancellation  
- User said anything other than "yes"
- No deletion occurs

---

### Lines 244-247: Exception Handling
```java
        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
```
**What**: Error handling for delete operation  
**Different**: Line 246 doesn't clear buffer (not critical here)  
**Line 246**: Shows exception message for debugging

---

## METHOD 5: display() - Lines 249-296

### Line 249: Method Declaration
```java
    void display() {
```
**What**: Main dashboard menu and control loop  
**Why**: Admin interface for accessing all functions  
**How**: Menu-driven loop until logout  
**Called By**: AdminLogin.login() after successful authentication

---

### Lines 250-252: Dashboard Header
```java
        System.out.println("\n======================");
        System.out.println("   ADMIN DASHBOARD");
        System.out.println("======================");
```
**What**: Prints dashboard title with borders  
**Why**: Clear identification of admin area  
**Formatting**: Centered title with border lines

---

### Lines 254-255: Initialize Loop Variables
```java
        int choice;
        boolean exit = false;
```
**What**: Variables for menu control  
**Line 254**: `choice` - stores menu selection  
**Line 255**: `exit` - loop control flag  
**Initial Value**: `false` - don't exit yet  
**Purpose**: Controlled loop termination

---

### Line 257: While Loop
```java
        while (!exit) {
```
**What**: Main dashboard loop  
**Why**: Keep showing menu until admin logs out  
**Condition**: `!exit` - while exit is false  
**Behavior**: Continues until exit becomes true  
**Controlled**: Not infinite - has exit condition

---

### Lines 258-263: Menu Options
```java
            System.out.println("\n1. Add Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Update Bus Details");
            System.out.println("4. Delete Bus");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
```
**What**: Displays admin menu options  
**Why**: Show available operations  
**Options**:
1. Create new bus
2. Read all buses
3. Update existing bus
4. Delete bus
5. Exit dashboard

**CRUD Operations**: Options 1-4 are Create, Read, Update, Delete

---

### Line 265: Try Block Start
```java
            try {
```
**What**: Exception handling for menu selection  
**Why**: Handle invalid input gracefully

---

### Line 266: Read Menu Choice
```java
                choice = sc.nextInt();
```
**What**: Gets admin's menu selection  
**Type**: Integer for switch statement  
**Can Throw**: InputMismatchException if non-integer entered

---

### Lines 268-287: Switch Statement
```java
                switch(choice) {
                    case 1:
                        addBus();
                        break;
                    case 2:
                        viewAllBuses();
                        break;
                    case 3:
                        updateBus();
                        break;
                    case 4:
                        deleteBus();
                        break;
                    case 5:
                        System.out.println("\n✓ Logging out...");
                        exit = true;
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice. Please try again.");
                }
```
**What**: Routes to appropriate method based on choice  
**How**: Switch statement with 5 cases plus default  

**Case 1 (Lines 269-271)**: Add Bus  
- Calls addBus() method
- Break returns to menu

**Case 2 (Lines 272-274)**: View Buses  
- Calls viewAllBuses() method

**Case 3 (Lines 275-277)**: Update Bus  
- Calls updateBus() method

**Case 4 (Lines 278-280)**: Delete Bus  
- Calls deleteBus() method

**Case 5 (Lines 281-284)**: Logout  
- Line 282: Logout message
- Line 283: Set `exit = true` - ends loop
- Next iteration: condition `!exit` is false, loop exits

**Default (Lines 285-286)**: Invalid Choice  
- Handles any number not 1-5
- Displays error message
- Loop continues

---

### Lines 288-292: Exception Catch Block
```java
            } catch (Exception e) {
                System.out.println("\n✗ Invalid input. Please enter a number.");
                sc.nextLine(); // clear buffer
            }
```
**What**: Handles input errors in menu selection  
**Why**: Prevent crash on non-numeric input  
**Line 289**: User-friendly error message  
**Line 290**: **CRITICAL** - clears input buffer  
**Without Line 290**: Would cause infinite loop with bad input  
**Example**: User types "abc" → caught here, buffer cleared, menu shows again

---

### Lines 293-294: Closing Braces
```java
        }
    }
```
**Line 293**: Closes while loop  
- Reached when exit becomes true
- Admin has logged out

**Line 294**: Closes display() method  
- Returns to AdminLogin
- Which returns to main menu

---

### Line 295-296: Final Closing Braces
```java
}
```
**What**: Closes AdminDashboard class  
**Complete**: All admin functionality implemented

---

## COMPLETE SUMMARY

### Class Structure
- **1 Instance Variable**: Scanner sc
- **2 Static Variables**: buses array, busCount
- **5 Methods**: addBus, viewAllBuses, updateBus, deleteBus, display

### CRUD Operations
1. **Create**: addBus() - Add new bus records
2. **Read**: viewAllBuses() - Display all buses
3. **Update**: updateBus() - Modify existing buses
4. **Delete**: deleteBus() - Remove buses

### Data Storage Design
**Static Array Approach**:
```
buses[0] → Bus object 1
buses[1] → Bus object 2
buses[2] → Bus object 3
...
buses[busCount-1] → Last bus
buses[busCount] → null (empty)
...
buses[99] → null (empty)
```

**Advantages**:
- Simple implementation
- Fast access by index
- Shared across all classes

**Disadvantages**:
- Fixed capacity (100 buses)
- Inefficient deletion (shifting elements)
- Wasted space if underutilized

### Validation Strategy
1. **Input Validation**: Check data types and ranges
2. **Business Rules**: Positive values for seats, distance, price
3. **Uniqueness**: No duplicate bus IDs
4. **Existence**: Bus must exist for update/delete
5. **Capacity**: Check array limit before adding

### Exception Handling Pattern
```java
try {
    // Operation code
    // Input collection
    // Validation
    // Data manipulation
} catch (InvalidBusException e) {
    // Handle custom exceptions
    // Display specific error
} catch (Exception e) {
    // Handle general exceptions
    // Display generic error
    // Clear input buffer
}
```

### User Experience Features
1. **Clear Headers**: Section titles for each operation
2. **Confirmation**: "Are you sure?" for deletion
3. **Feedback**: Success/error messages with symbols (✓, ✗)
4. **Information**: Display bus details after operations
5. **Guidance**: Menu options and prompts

### Input Buffer Management
**Critical Points**:
- After `nextInt()`, call `nextLine()` to consume newline
- In catch blocks, call `nextLine()` to clear bad input
- Without this: Infinite loops or unexpected behavior

**Pattern**:
```java
int x = sc.nextInt();  // Leaves \n in buffer
sc.nextLine();         // Consume the \n
String s = sc.nextLine();  // Now reads properly
```

### Menu Flow Diagram
```
AdminLogin
    ↓
display() - Dashboard Menu
    ↓
While Loop
    ↓
Show Menu (1-5)
    ↓
Read Choice
    ↓
Switch (choice)
    ↓
1 → addBus() → Add new bus
2 → viewAllBuses() → Show all
3 → updateBus() → Modify bus
4 → deleteBus() → Remove bus
5 → Set exit=true → Break loop
    ↓
    ↓ (if exit=false, loop back)
    ↓
    ↓ (if exit=true, exit loop)
    ↓
Return to AdminLogin
    ↓
Return to Main Menu
```

### Array Deletion Algorithm
**Problem**: Remove element from middle of array  
**Solution**: Shift elements left

**Example**: Delete index 2 from array of 5 elements
```
Before:  [A][B][C][D][E]
                ↑ delete this
Step 1:  [A][B][D][D][E]  (copy buses[3] to buses[2])
Step 2:  [A][B][D][E][E]  (copy buses[4] to buses[3])
Step 3:  [A][B][D][E][X]  (set buses[4] to null)
busCount: 5 → 4
```

**Code**:
```java
for (int j = index; j < busCount - 1; j++) {
    buses[j] = buses[j + 1];  // Shift left
}
buses[--busCount] = null;  // Clean up and decrement
```

### Design Patterns Used
1. **Singleton-like**: Static array shared globally
2. **CRUD Pattern**: Standard database operations
3. **Menu-Driven**: User interface pattern
4. **Error Handling**: Try-catch for robustness

### Potential Improvements
1. **Dynamic Array**: Use ArrayList instead of fixed array
2. **Database**: Persist data (currently in-memory only)
3. **Search**: Add search functionality by various criteria
4. **Sorting**: Sort buses by different fields
5. **Validation**: More thorough input validation
6. **Edit History**: Track who modified what and when
7. **Bulk Operations**: Import/export buses from file

### Integration Points
**Used By**:
- AdminLogin.login() - creates and displays dashboard
- Passenger class - accesses buses array for booking

**Uses**:
- Bus class - creates and manipulates Bus objects
- Scanner - for user input
- InvalidBusException - for validation errors

### Complete Admin Workflow Example
```
1. Admin logs in (AdminLogin)
2. Dashboard displays with menu
3. Admin selects "1. Add Bus"
4. System prompts for all bus details
5. Admin enters: ID=1, Number=MH-123, etc.
6. Validation checks (unique ID, positive values)
7. Bus object created and added to array
8. Success message and bus details displayed
9. Menu shows again
10. Admin can perform more operations
11. Admin selects "5. Logout"
12. Returns to main menu
```

### Key Java Concepts Demonstrated
1. **Static Members**: Shared data across instances
2. **Array Manipulation**: Add, remove, shift elements
3. **Exception Handling**: Try-catch with custom exceptions
4. **Method Organization**: Each operation in separate method
5. **Loop Control**: Boolean flag for controlled iteration
6. **Input Handling**: Scanner with buffer management
7. **Switch Statement**: Multi-way branching
8. **Object References**: Working with object arrays
9. **Validation**: Business rule enforcement
10. **User Interaction**: Menu-driven interface

This class is the administrative heart of the bus booking system, providing complete bus management capabilities!
