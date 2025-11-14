# BusBookingSystem.java - Complete Code Explanation

## File Overview
**Purpose**: Main entry point for the Bus Booking System application.

**Why**: Provides the primary menu interface and coordinates the entire application flow.

**How**: Contains the `main` method that displays options for admin login, user menu, and exit.

---

## Line-by-Line Explanation

### Line 1: Import Statement
```java
import java.util.Scanner;
```
**What**: Imports the Scanner class from java.util package  
**Why**: Needed to read user input from the console/keyboard  
**How**: `import` keyword brings external class into current file  
**Package**: `java.util` - Java utility package containing helper classes  
**Class**: `Scanner` - reads primitive types and strings from various input sources  
**Without This**: Would need to write `java.util.Scanner` every time  
**Alternative**: Could import entire package with `import java.util.*;`

---

### Line 3: Class Declaration
```java
public class BusBookingSystem {
```
**What**: Declares the main class with public access  
**Why**: Entry point class must be public and match filename  
**How**: `public class` keyword followed by class name  

**Access Modifier**: `public` - accessible from anywhere  
**Class Name**: `BusBookingSystem` - must match filename (BusBookingSystem.java)  
**Java Rule**: Public class name must match the file name  
**Purpose**: Main application controller class

---

### Line 4: Main Method Declaration
```java
    public static void main(String[] args) {
```
**What**: The main method - entry point of Java application  
**Why**: JVM (Java Virtual Machine) calls this method to start the program  
**How**: Special method signature that Java recognizes as the starting point  

**Breakdown**:
- `public` - must be accessible to JVM
- `static` - can be called without creating an object
- `void` - doesn't return any value
- `main` - specific name required by JVM
- `String[] args` - command-line arguments (array of strings)

**Execution**: JVM searches for this exact signature when program runs  
**Why static**: JVM needs to call this before any objects exist  
**args Parameter**: 
- Receives command-line arguments
- Example: `java BusBookingSystem arg1 arg2`
- `args[0]` would be "arg1"
- Not used in this application

---

### Line 5: Scanner Initialization
```java
        Scanner sc = new Scanner(System.in);
```
**What**: Creates a Scanner object to read keyboard input  
**Why**: Application needs to get user choices from the console  
**How**: Instantiates Scanner with System.in as the input source  

**Breakdown**:
- `Scanner` - the class type
- `sc` - variable name (short for scanner)
- `new Scanner()` - creates new Scanner object
- `System.in` - standard input stream (keyboard)

**Memory**: Object created on heap, `sc` holds reference  
**Purpose**: Read integers and strings from user throughout the program  
**Scope**: Local to main method, available to entire method  
**Resource**: Scanner should ideally be closed, but program exits so it's acceptable here

---

### Line 6: Variable Declaration - choice
```java
        int choice;
```
**What**: Declares an integer variable to store user menu selection  
**Why**: Needs to store which option (1, 2, or 3) the user selects  
**How**: Variable declaration without initialization  
**Type**: `int` - primitive type for whole numbers  
**Initial Value**: Not initialized yet (will be assigned in the loop)  
**Scope**: Local to main method  
**Usage**: Used in switch statement to determine which action to take

---

### Line 7: Infinite Loop
```java
        while(true){
```
**What**: Creates an infinite loop that runs continuously  
**Why**: Application should keep showing menu until user explicitly chooses to exit  
**How**: `while` loop with condition that's always true  

**Condition**: `true` - literal boolean value, never changes  
**Behavior**: Loop runs forever unless explicitly broken  
**Exit Mechanism**: `System.exit(0)` in case 3 terminates the program  
**Alternative**: Could use `boolean running = true` with `while(running)` and set to false  
**Purpose**: Main application loop - keeps program alive and responsive

---

### Line 8: Menu Header Line 1
```java
            System.out.println("======================");
```
**What**: Prints a decorative border line  
**Why**: Visual formatting to make menu more readable and professional  
**How**: `println()` outputs text and moves to next line  
**Output**: `======================`  
**Purpose**: Top border of menu box  
**User Experience**: Creates clear visual separation and structure

---

### Line 9: Menu Title
```java
            System.out.println("Bus Booking System");
```
**What**: Prints the application title  
**Why**: Identifies the application to the user  
**How**: Standard println() call with string literal  
**Output**: `Bus Booking System`  
**Positioning**: Centered between border lines  
**Purpose**: Branding and clarity about what application does

---

### Line 10: Menu Header Line 2
```java
            System.out.println("======================");
```
**What**: Prints bottom border line of the header  
**Why**: Completes the box around the title  
**How**: Same as line 8  
**Output**: `======================`  
**Visual Effect**: Creates enclosed header section

---

### Line 11: Menu Options and Prompt
```java
            System.out.print("1. Admin Login\n2. User Menu\n3. Exit\nChoose an option: ");
```
**What**: Prints all menu options and prompt in one statement  
**Why**: Efficient way to display multiple lines of text  
**How**: Uses `print()` (not `println()`) with embedded newline characters  

**Breakdown**:
- `System.out.print()` - prints without adding newline at end
- `"1. Admin Login"` - first option
- `\n` - escape sequence for newline (line break)
- `"2. User Menu"` - second option
- `\n` - another newline
- `"3. Exit"` - third option
- `\n` - newline before prompt
- `"Choose an option: "` - prompt stays on same line as user input

**Escape Sequence**: `\n` represents a line break  
**Why print() not println()**: Keeps cursor on same line after prompt for user input  
**Output**:
```
1. Admin Login
2. User Menu
3. Exit
Choose an option: _
```

**User Experience**: Clear options with cursor ready for input

---

### Line 12: Read User Choice
```java
            choice = sc.nextInt();
```
**What**: Reads an integer from user input and stores in choice variable  
**Why**: Need to know which menu option user selected  
**How**: Scanner's `nextInt()` method reads next integer token  

**Process**:
1. Program waits for user to type a number
2. User presses Enter
3. Scanner reads the integer
4. Value stored in `choice` variable

**Input Buffer**: Leaves the newline character in buffer (can cause issues)  
**Potential Issues**:
- If user enters non-integer, throws `InputMismatchException`
- No validation (user could enter 999)

**Example**: User types `1` → choice = 1

---

### Line 13: Switch Statement
```java
            switch(choice) {
```
**What**: Begins a switch statement to handle different menu choices  
**Why**: Execute different code based on user's selection  
**How**: Evaluates `choice` variable and jumps to matching case  

**Switch Expression**: `choice` - the value being evaluated  
**How It Works**: Compares choice against each case value  
**Advantage**: Cleaner than multiple if-else statements for discrete values  
**Execution**: Jumps directly to matching case label

---

### Line 14: Case 1 - Admin Login
```java
                case 1:
```
**What**: Label for when choice equals 1  
**Why**: User selected "Admin Login" option  
**How**: `case` keyword followed by value and colon  
**Execution**: If choice == 1, execution starts here  
**Purpose**: Handle administrator authentication flow

---

### Line 15: Prompt for Username
```java
                    System.out.print("Enter username: ");
```
**What**: Prompts admin to enter username  
**Why**: Need username for authentication  
**How**: `print()` keeps cursor on same line for input  
**Output**: `Enter username: _`  
**User Action**: Types username and presses Enter

---

### Line 16: Read Username
```java
                    String user = sc.next();
```
**What**: Reads username from input and stores in `user` variable  
**Why**: Capture username for credential validation  
**How**: `next()` method reads next token (word) until whitespace  

**Method**: `next()` - reads next complete token (no spaces)  
**Type**: `String` - variable declared and initialized in one line  
**Scope**: Local to case 1 block  
**Limitation**: Username cannot contain spaces (use `nextLine()` for that)  
**Example**: User types `admin` → user = "admin"

---

### Line 17: Prompt for Password
```java
                    System.out.print("Enter password: ");
```
**What**: Prompts admin to enter password  
**Why**: Need password for authentication  
**How**: Same as username prompt  
**Output**: `Enter password: _`  
**Security Issue**: Password visible as user types (console doesn't hide it)  
**Production**: Should use `Console.readPassword()` to hide password

---

### Line 18: Read Password
```java
                    String pass = sc.next();
```
**What**: Reads password from input and stores in `pass` variable  
**Why**: Capture password for credential validation  
**How**: Same as username reading  
**Type**: `String`  
**Scope**: Local to case 1 block  
**Example**: User types `admin123` → pass = "admin123"

---

### Line 19: Create AdminLogin Object
```java
                    AdminLogin admin = new AdminLogin();
```
**What**: Creates a new AdminLogin object  
**Why**: Need an instance to call the login method  
**How**: Uses `new` keyword with default constructor  

**Object Creation**: 
- Allocates memory on heap
- Initializes username="admin" and password="admin123"
- Returns reference stored in `admin` variable

**Purpose**: Prepare to authenticate the admin  
**Scope**: Local to case 1 block

---

### Line 20: Call Login Method
```java
                    admin.login(user, pass);
```
**What**: Calls the login method with provided credentials  
**Why**: Authenticate admin and grant access if valid  
**How**: Method invocation with two String arguments  

**Parameters**:
- `user` - username entered by user
- `pass` - password entered by user

**Process**:
- Validates credentials
- If valid: prints success message, shows AdminDashboard
- If invalid: prints error message

**Control Flow**: 
- Execution enters AdminLogin.login()
- If successful, enters AdminDashboard.display()
- Returns here when admin logs out

**Effect**: Admin can manage buses if credentials are correct

---

### Line 21: Break Statement (Case 1)
```java
                    break;
```
**What**: Exits the switch statement  
**Why**: Prevent fall-through to next case  
**How**: `break` keyword transfers control to after switch  

**Without break**: Code would continue executing into case 2  
**Effect**: Returns to while loop, menu displays again  
**Purpose**: Complete case 1 execution and show menu again

---

### Line 22: Case 2 - User Menu
```java
                case 2:
```
**What**: Label for when choice equals 2  
**Why**: User selected "User Menu" (passenger functions)  
**How**: Case label for value 2  
**Execution**: If choice == 2, execution starts here  
**Purpose**: Handle passenger operations (search, book, cancel tickets)

---

### Line 23: Create Passenger Object
```java
                    Passenger p  = new Passenger();
```
**What**: Creates a new Passenger object using default constructor  
**Why**: Need a Passenger instance to access user functionalities  
**How**: Instantiation with `new` keyword  

**Object Creation**:
- Calls `Passenger()` default constructor
- Initializes myBookings array and count
- Inherits from User class (id=0, name=null, email=null)

**Variable**: `p` - reference to Passenger object  
**Scope**: Local to case 2 block  
**Note**: Extra space after `p` (stylistic, no functional difference)

---

### Line 24: Display User Menu
```java
                    p.display();
```
**What**: Calls display method to show passenger menu  
**Why**: Allow user to perform passenger operations  
**How**: Method invocation on Passenger object  

**Effect**: 
- Shows passenger menu (view buses, search, book, cancel, etc.)
- Enters a loop for passenger operations
- Returns when user selects "Back to Main Menu"

**User Experience**: Enters passenger interface with multiple options  
**Control Flow**: Stays in Passenger.display() until user exits back to main menu

---

### Line 25: Break Statement (Case 2)
```java
                    break;
```
**What**: Exits the switch statement  
**Why**: Complete case 2 execution  
**How**: Transfers control to end of switch  
**Effect**: Returns to while loop, main menu displays again

---

### Line 26: Case 3 - Exit
```java
                case 3:
```
**What**: Label for when choice equals 3  
**Why**: User wants to exit the application  
**How**: Case label for value 3  
**Execution**: If choice == 3, execution starts here  
**Purpose**: Graceful application termination

---

### Line 27: Exit Message
```java
                    System.out.println("Exiting...");
```
**What**: Prints farewell message  
**Why**: Inform user that application is closing  
**How**: Standard println() call  
**Output**: `Exiting...`  
**User Experience**: Confirmation that exit request was received

---

### Line 28: Terminate Program
```java
                    System.exit(0);
```
**What**: Terminates the JVM and ends the application  
**Why**: Only way to break out of infinite while(true) loop  
**How**: Calls System.exit() with status code  

**Method**: `System.exit(int status)`  
**Parameter**: `0` - exit status code  
**Status Codes**:
- `0` - normal termination (success)
- Non-zero - abnormal termination (error)

**Effect**: 
- Immediately stops all threads
- Closes all resources
- JVM shuts down
- Control returns to operating system

**Alternative**: Could use `boolean running` flag and break loop  
**Note**: Scanner `sc` not explicitly closed, but program termination closes it

---

### Line 29: Break Statement (Case 3)
```java
                    break;
```
**What**: Break statement (technically unreachable)  
**Why**: Good practice to include, though `System.exit(0)` already terminates  
**How**: Standard break keyword  
**Execution**: Never reached because System.exit() stops program  
**Purpose**: Code completeness and consistency  
**Compiler**: Recognizes this is unreachable but doesn't error

---

### Line 30: Default Case
```java
                default:
```
**What**: Default case label for unhandled values  
**Why**: Handle any invalid choice (not 1, 2, or 3)  
**How**: `default` keyword - catches all other cases  
**When Executed**: If choice is 4, 5, -1, 999, etc.  
**Purpose**: Input validation and error handling

---

### Line 31: Invalid Choice Message
```java
                    System.out.println("Invalid choice.");
```
**What**: Prints error message for invalid input  
**Why**: Inform user their choice was not recognized  
**How**: Standard println() call  
**Output**: `Invalid choice.`  
**User Experience**: Clear feedback that input was wrong  
**Next Action**: Loop repeats, menu displays again

---

### Line 32: Closing Brace (Switch)
```java
            }
```
**What**: Closes the switch statement  
**Why**: Marks end of all case blocks  
**After This**: Execution continues to end of while loop

---

### Line 33: Closing Brace (While Loop)
```java
        }
```
**What**: Closes the while loop  
**Why**: Marks end of loop body  
**Effect**: Control returns to while condition (line 7)  
**Loop Behavior**: Checks condition `true`, which is always true, so loops again  
**Result**: Menu displays again unless program exited via System.exit()

---

### Line 34: Closing Brace (Main Method)
```java
    }
```
**What**: Closes the main method  
**Why**: Marks end of main method body  
**Note**: Typically never reached due to infinite loop  
**If Reached**: Program would terminate normally

---

### Line 35: Closing Brace (Class)
```java
}
```
**What**: Closes the BusBookingSystem class  
**Why**: Marks end of class definition  
**Effect**: Class is complete and ready to compile/run

---

## Summary

### Class Structure
- **1 Method**: main() - entry point
- **Dependencies**: Scanner, AdminLogin, Passenger classes
- **Purpose**: Main menu controller

### Application Flow
```
Start Program (main method)
        ↓
Create Scanner
        ↓
Enter infinite loop
        ↓
Display Menu
        ↓
Read user choice
        ↓
    Switch (choice)
    /     |     \
   1      2      3
   ↓      ↓      ↓
Admin  User   Exit
Login  Menu  Program
   ↓      ↓
Admin  Passenger
Dashboard Operations
   ↓      ↓
Loop   Loop
Back   Back
   \     |     /
    Menu Again
        ↓
    Infinite Loop
```

### Menu Options Explained
1. **Admin Login** (Case 1)
   - Input: username and password
   - Authentication via AdminLogin
   - Access to bus management (CRUD operations)
   - Returns to main menu after logout

2. **User Menu** (Case 2)
   - Creates Passenger object
   - Access to passenger functions
   - Search, view, book, cancel tickets
   - Returns to main menu when done

3. **Exit** (Case 3)
   - Displays exit message
   - Terminates entire application
   - Clean program closure

### Key Java Concepts Demonstrated
1. **Main Method**: Entry point with correct signature
2. **Scanner**: Reading console input
3. **Infinite Loop**: while(true) for continuous operation
4. **Switch Statement**: Multi-way branching
5. **Object Creation**: Instantiating AdminLogin and Passenger
6. **Method Invocation**: Calling methods on objects
7. **System.exit()**: Programmatic termination
8. **String Concatenation**: Using \n in strings
9. **Local Variables**: Scope within methods and blocks

### Input/Output Flow
**Input Methods**:
- `sc.nextInt()` - reads integer (choice)
- `sc.next()` - reads string tokens (username, password)

**Output Methods**:
- `System.out.println()` - print with newline
- `System.out.print()` - print without newline

### Potential Issues & Improvements

**Current Issues**:
1. **No Input Validation**: Doesn't handle non-integer input
2. **Resource Management**: Scanner not closed
3. **Error Handling**: No try-catch blocks
4. **Infinite Loop**: Only exits via System.exit()
5. **Hard-Coded Menu**: Not easily extensible

**Suggested Improvements**:
```java
// Add input validation
try {
    choice = sc.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Please enter a valid number");
    sc.nextLine(); // Clear buffer
    continue;
}

// Use boolean flag instead of infinite loop
boolean running = true;
while(running) {
    // ... menu code ...
    if(choice == 3) {
        running = false; // Instead of System.exit(0)
    }
}
sc.close(); // Properly close Scanner

// Add enum for menu options
enum MenuOption {
    ADMIN_LOGIN(1),
    USER_MENU(2),
    EXIT(3);
    
    private int value;
    MenuOption(int value) { this.value = value; }
}
```

### Complete Execution Example
```
[Program Starts]
======================
Bus Booking System
======================
1. Admin Login
2. User Menu
3. Exit
Choose an option: 1
Enter username: admin
Enter password: admin123
Login successful.
[AdminDashboard displays]
...
[Admin logs out]
======================
Bus Booking System
======================
1. Admin Login
2. User Menu
3. Exit
Choose an option: 2
[Passenger menu displays]
...
[User exits to main menu]
======================
Bus Booking System
======================
1. Admin Login
2. User Menu
3. Exit
Choose an option: 3
Exiting...
[Program terminates]
```

### Class Responsibilities
- **Entry Point**: Starts application via main()
- **Main Menu**: Displays and handles top-level navigation
- **Router**: Directs to appropriate functionality based on user choice
- **Coordinator**: Creates and manages AdminLogin and Passenger objects

### Design Pattern
**Front Controller Pattern**: Single entry point that routes to appropriate handlers
- Main menu acts as front controller
- Routes to admin or passenger controllers
- Centralized navigation logic
