# Bus Booking System - Code Explanation

This document provides a detailed line-by-line explanation of the Java code for the Bus Booking System project.

## `User.java`

This file defines a base class `User` which serves as a template for different types of users in the system.

- **Line 1: `class User {`**
  - **What:** Declares a new class named `User`.
  - **Why:** To create a blueprint for user objects, containing common properties that all users (like a Passenger) will have.
  - **How:** Using the `class` keyword.
  - **Where:** This is the base class, intended to be extended by more specific user classes.

- **Line 2: `int id;`**
  - **What:** Declares an instance variable `id` of type `int`.
  - **Why:** To store a unique identifier for each user.
  - **How:** By specifying the data type `int` and the variable name `id`.

- **Line 3: `String name;`**
  - **What:** Declares an instance variable `name` of type `String`.
  - **Why:** To store the user's name.
  - **How:** By specifying the data type `String` and the variable name `name`.

- **Line 4: `String email;`**
  - **What:** Declares an instance variable `email` of type `String`.
  - **Why:** To store the user's email address, which can be used for communication or as a unique identifier.
  - **How:** By specifying the data type `String` and the variable name `email`.

- **Line 6: `User() {}`**
  - **What:** Declares a no-argument (default) constructor for the `User` class.
  - **Why:** To allow the creation of a `User` object without providing initial values for its properties. This is useful for flexibility, especially when values will be set later.
  - **How:** By defining a method with the same name as the class and no parameters.

- **Line 8: `User(int id, String name, String email) {`**
  - **What:** Declares a parameterized constructor for the `User` class.
  - **Why:** To provide a convenient way to create a `User` object and initialize all its fields (`id`, `name`, `email`) at the time of creation.
  - **How:** By defining a constructor that accepts `int`, `String`, and `String` arguments.

- **Line 9: `this.id = id;`**
  - **What:** Assigns the value of the `id` parameter to the `id` instance variable of the object.
  - **Why:** The `this` keyword is used to distinguish between the instance variable and the parameter, as they have the same name. This initializes the object's state.
  - **How:** Using the assignment operator `=` and the `this` keyword.

- **Line 10: `this.name = name;`**
  - **What:** Assigns the value of the `name` parameter to the `name` instance variable.
  - **Why:** To initialize the `name` field of the `User` object being created.
  - **How:** Using the `this` keyword to refer to the current object's field.

- **Line 11: `this.email = email;`**
  - **What:** Assigns the value of the `email` parameter to the `email` instance variable.
  - **Why:** To initialize the `email` field of the `User` object.
  - **How:** Using the `this` keyword.

- **Line 14: `void showProfile() {`**
  - **What:** Declares a method named `showProfile` that returns no value (`void`).
  - **Why:** To provide a standard way to display the details of a user object to the console.
  - **How:** By defining a method with the `void` return type.

- **Line 15: `System.out.println("User ID: " + id + " Name: " + name + " Email: " + email);`**
  - **What:** Prints the user's `id`, `name`, and `email` to the console in a formatted string.
  - **Why:** To show the user's profile information.
  - **How:** Using `System.out.println()` and string concatenation (`+`) to combine text and variable values.

## `AdminLogin.java`

This file contains the logic for authenticating an administrator.

- **Line 1: `class AdminLogin {`**
  - **What:** Declares a class named `AdminLogin`.
  - **Why:** To encapsulate the functionality related to the admin login process, separating it from other parts of the application.
  - **How:** Using the `class` keyword.

- **Line 2: `String username = "admin";`**
  - **What:** Declares and initializes an instance variable `username` with the value `"admin"`.
  - **Why:** To store the correct username for the administrator. In this simple application, it's hardcoded.
  - **How:** By defining a `String` variable and assigning it a literal value.

- **Line 3: `String password = "admin123";`**
  - **What:** Declares and initializes an instance variable `password` with the value `"admin123"`.
  - **Why:** To store the correct password for the administrator. This is also hardcoded for simplicity.
  - **How:** By defining a `String` variable and assigning it a literal value.

- **Line 5: `void login(String user, String pass) {`**
  - **What:** Declares a method `login` that accepts a username (`user`) and password (`pass`) as input and returns no value (`void`).
  - **Why:** This method contains the core logic to verify the credentials provided by the person trying to log in.
  - **How:** It takes the user's input as parameters to compare against the stored credentials.

- **Line 6: `boolean res = user.equals(username) && pass.equals(password);`**
  - **What:** Compares the input `user` with the stored `username` and the input `pass` with the stored `password`. The result (`true` or `false`) is stored in the boolean variable `res`.
  - **Why:** To check if both the username and password match. The `&&` (AND) operator ensures that both conditions must be true for the login to be successful. Using `.equals()` is the correct way to compare String contents in Java.
  - **How:** By calling the `.equals()` method on the strings and using the logical AND operator.

- **Line 7: `if(res) {`**
  - **What:** An `if` statement that checks if the `res` variable is `true`.
  - **Why:** To execute a specific block of code only if the login credentials are correct.
  - **How:** The code inside the curly braces `{}` will run if the condition `res` is true.

- **Line 8: `System.out.println("Login successful.");`**
  - **What:** Prints the message "Login successful." to the console.
  - **Why:** To provide feedback to the user that they have logged in successfully.
  - **How:** Using the standard output stream `System.out.println()`.

- **Line 9: `AdminDashboard dashboard = new AdminDashboard();`**
  - **What:** Creates a new instance (object) of the `AdminDashboard` class.
  - **Why:** To give the authenticated admin access to the administrative functions, which are contained within the `AdminDashboard` class.
  - **How:** Using the `new` keyword to instantiate the class.

- **Line 10: `dashboard.display();`**
  - **What:** Calls the `display()` method on the newly created `dashboard` object.
  - **Why:** To show the admin menu and start the interaction loop for the admin.
  - **How:** Using the dot operator `.` to invoke a method on an object.

- **Line 11: `} else {`**
  - **What:** The `else` part of the `if-else` statement.
  - **Why:** To execute a different block of code if the login credentials are *not* correct (i.e., if `res` was `false`).
  - **How:** The code inside the `else` block runs when the `if` condition is not met.

- **Line 12: `System.out.println("Invalid credentials.");`**
  - **What:** Prints the message "Invalid credentials." to the console.
  - **Why:** To inform the user that their login attempt failed.
  - **How:** Using `System.out.println()`.

## `BusBookingSystem.java`

This is the main entry point of the application. It contains the `main` method that starts the program.

- **Line 1: `import java.util.Scanner;`**
  - **What:** Imports the `Scanner` class from the `java.util` package.
  - **Why:** The `Scanner` class is needed to read input from the user (e.g., menu choices, login details).
  - **How:** Using the `import` keyword.

- **Line 3: `public class BusBookingSystem {`**
  - **What:** Declares a `public` class named `BusBookingSystem`.
  - **Why:** This class serves as the container for the main logic that drives the application.
  - **How:** Using the `public class` keywords.

- **Line 4: `public static void main(String[] args) {`**
  - **What:** This is the main method of the application.
  - **Why:** The Java Virtual Machine (JVM) starts executing the program from this method. It is the entry point of any Java application.
  - **How:** It is a `public` and `static` method, so it can be called without creating an instance of the class. It accepts an array of strings `args` for command-line arguments.

- **Line 5: `Scanner sc = new Scanner(System.in);`**
  - **What:** Creates a new `Scanner` object named `sc` that reads from standard input (`System.in`).
  - **Why:** To enable the program to read user input from the console.
  - **How:** By instantiating the `Scanner` class.

- **Line 6: `int choice;`**
  - **What:** Declares an integer variable `choice`.
  - **Why:** To store the user's menu selection.
  - **How:** By specifying the data type `int` and the variable name.

- **Line 7: `while(true){`**
  - **What:** Starts an infinite `while` loop.
  - **Why:** To keep the main menu running continuously until the user explicitly chooses to exit the program.
  - **How:** The condition `true` ensures the loop never ends on its own.

- **Line 8-11: `System.out.println(...)`**
  - **What:** These lines print the main menu of the bus booking system to the console.
  - **Why:** To show the user the available top-level options: Admin Login, User Menu, or Exit.
  - **How:** Using `System.out.println()` to print each line of the menu.

- **Line 12: `choice = sc.nextInt();`**
  - **What:** Reads the integer value entered by the user and assigns it to the `choice` variable.
  - **Why:** To capture the user's menu selection.
  - **How:** By calling the `nextInt()` method of the `Scanner` object.

- **Line 13: `switch(choice) {`**
  - **What:** A `switch` statement that evaluates the `choice` variable.
  - **Why:** To execute different blocks of code based on which menu option the user selected. It's a clean alternative to a long chain of `if-else if` statements.
  - **How:** The `switch` statement matches the value of `choice` with the `case` labels.

- **Line 14: `case 1:`**
  - **What:** This `case` block is executed if the user's choice was `1`.
  - **Why:** This handles the "Admin Login" option.
  - **How:** The code under this case will run if `choice` is 1.

- **Line 15: `System.out.print("Enter username: ");`**
  - **What:** Prompts the user to enter their username.
  - **Why:** To get the username for admin authentication.
  - **How:** Using `System.out.print()` so the cursor stays on the same line.

- **Line 16: `String user = sc.next();`**
  - **What:** Reads the username (a single word) entered by the user.
  - **Why:** To store the entered username in the `user` variable.
  - **How:** Using the `sc.next()` method, which reads the next token from the input.

- **Line 17: `System.out.print("Enter password: ");`**
  - **What:** Prompts the user to enter their password.
  - **Why:** To get the password for admin authentication.
  - **How:** Using `System.out.print()`.

- **Line 18: `String pass = sc.next();`**
  - **What:** Reads the password entered by the user.
  - **Why:** To store the entered password in the `pass` variable.
  - **How:** Using `sc.next()`.

- **Line 19: `AdminLogin admin = new AdminLogin();`**
  - **What:** Creates a new `AdminLogin` object.
  - **Why:** To use its `login` method to verify the credentials.
  - **How:** By instantiating the `AdminLogin` class.

- **Line 20: `admin.login(user, pass);`**
  - **What:** Calls the `login` method, passing the entered username and password.
  - **Why:** To perform the authentication check.
  - **How:** By invoking the method on the `admin` object.

- **Line 21: `break;`**
  - **What:** Exits the `switch` statement.
  - **Why:** To prevent the code from "falling through" and executing the code in the next `case`.
  - **How:** The `break` keyword terminates the `switch` block.

- **Line 22: `case 2:`**
  - **What:** This block is executed if the user's choice was `2` (User Menu).
  - **Why:** To navigate the user to the passenger-facing menu.
  - **How:** The code under this case will run if `choice` is 2.

- **Line 23: `Passenger p  = new Passenger();`**
  - **What:** Creates a new `Passenger` object.
  - **Why:** To access the passenger functionalities like searching for buses and booking tickets.
  - **How:** By instantiating the `Passenger` class.

- **Line 24: `p.display();`**
  - **What:** Calls the `display` method on the `Passenger` object.
  - **Why:** To show the user menu and handle user interactions.
  - **How:** By invoking the method on the `p` object.

- **Line 25: `break;`**
  - **What:** Exits the `switch` statement.
  - **Why:** To prevent fall-through.

- **Line 26: `case 3:`**
  - **What:** This block is executed if the user's choice was `3` (Exit).
  - **Why:** To terminate the application.
  - **How:** The code under this case will run if `choice` is 3.

- **Line 27: `System.out.println("Exiting...");`**
  - **What:** Prints an "Exiting..." message.
  - **Why:** To inform the user that the application is closing.
  - **How:** Using `System.out.println()`.

- **Line 28: `System.exit(0);`**
  - **What:** Terminates the Java Virtual Machine (JVM).
  - **Why:** To stop the program execution completely.
  - **How:** The `System.exit()` method is a standard way to terminate an application. The argument `0` indicates a successful or normal termination.

- **Line 29: `break;`**
  - **What:** Exits the `switch` statement. While technically not needed after `System.exit()`, it's good practice for consistency.

- **Line 30: `default:`**
  - **What:** This block is executed if the `choice` does not match any of the `case` labels (1, 2, or 3).
  - **Why:** To handle invalid user input.
  - **How:** It acts as a catch-all for any other integer value.

- **Line 31: `System.out.println("Invalid choice.");`**
  - **What:** Prints an "Invalid choice." message.
  - **Why:** To inform the user that their input was not a valid menu option.
  - **How:** Using `System.out.println()`.

## `Bus.java`

This file is extensive. It defines the `Bus` class, custom exceptions, a `Payment` abstract class, a `Cancellable` interface, and the `Booking` class. It's the core model of the application.

- **Line 2: `class InvalidBusException extends Exception {`**
  - **What:** Declares a custom exception class `InvalidBusException` that inherits from the standard `Exception` class.
  - **Why:** To create a specific type of error for situations related to invalid bus data (e.g., negative seats, non-existent bus ID). This makes error handling more precise.
  - **How:** Using the `class` and `extends` keywords.

- **Line 3: `public InvalidBusException(String message) {`**
  - **What:** The constructor for the `InvalidBusException` class.
  - **Why:** To allow creating an instance of this exception with a custom error message.
  - **How:** It takes a `String` message as an argument.

- **Line 4: `super(message);`**
  - **What:** Calls the constructor of the parent class (`Exception`).
  - **Why:** To pass the error message up to the `Exception` class, so it can be stored and later retrieved using methods like `getMessage()`.
  - **How:** Using the `super()` keyword.

- **Lines 8-13: `InvalidSeatException`**
  - **What:** Defines another custom exception, `InvalidSeatException`.
  - **Why:** To handle errors specifically related to seat booking, such as trying to book an already booked seat or an invalid seat number.
  - **How:** Same structure as `InvalidBusException`.

- **Lines 15-20: `PaymentFailedException`**
  - **What:** Defines the `PaymentFailedException` custom exception.
  - **Why:** To signal that a payment transaction has failed.
  - **How:** Same structure as the other custom exceptions.

- **Line 23: `abstract class Payment {`**
  - **What:** Declares an `abstract` class named `Payment`.
  - **Why:** To create a template for payment processing. An abstract class cannot be instantiated directly but can be extended. It defines a common structure (`amount`, `paymentId`) and behavior (`processPayment`, `generateReceipt`) that all payment types must follow.
  - **How:** Using the `abstract class` keywords.

- **Line 24: `protected double amount;`**
  - **What:** A `protected` instance variable to store the payment amount.
  - **Why:** `protected` makes it accessible within the `Payment` class and any of its subclasses (like `Booking`).
  - **How:** Declaring a `double` variable with the `protected` access modifier.

- **Line 27: `abstract boolean processPayment();`**
  - **What:** An `abstract` method declaration.
  - **Why:** It forces any subclass that extends `Payment` to provide its own implementation for how a payment is processed. The method must return a `boolean` (true for success, false for failure).
  - **How:** Using the `abstract` keyword. The method has no body `{}`.

- **Line 28: `abstract void generateReceipt();`**
  - **What:** Another `abstract` method.
  - **Why:** It requires every subclass to implement logic for generating a receipt after a successful payment.
  - **How:** Declared with the `abstract` keyword and no method body.

- **Line 30: `void displayPaymentInfo() {`**
  - **What:** A concrete (non-abstract) method within the abstract class.
  - **Why:** To provide a common, reusable function that all subclasses can use to display basic payment info without having to rewrite it.
  - **How:** It's a regular method definition.

- **Line 36: `interface Cancellable {`**
  - **What:** Declares an `interface` named `Cancellable`.
  - **Why:** To define a "contract" for any class that needs to have cancellation functionality. An interface specifies *what* a class can do, but not *how*. Any class implementing `Cancellable` *must* provide implementations for all its methods.
  - **How:** Using the `interface` keyword.

- **Line 37: `void cancelBooking(int bookingId);`**
  - **What:** A method signature within the interface.
  - **Why:** To specify that any cancellable item must have a way to be cancelled.
  - **How:** Method declaration with no body.

- **Line 38: `double calculateRefund(int daysBeforeJourney);`**
  - **What:** A method signature to calculate a refund amount.
  - **Why:** To define a standard way to determine the refund value based on when the cancellation occurs.
  - **How:** Method declaration with no body.

- **Line 42: `class Bus {`**
  - **What:** Declares the `Bus` class.
  - **Why:** This is a central model class that represents a bus in the system, holding all its details and properties.
  - **How:** Using the `class` keyword.

- **Line 43-53: Instance Variables (`id`, `busNo`, etc.)**
  - **What:** These lines declare the properties of a bus.
  - **Why:** To store the state of each bus object, such as its ID, number, route, capacity, and fare. `seatStatus` is a boolean array where the index represents the seat number, and the value indicates if it's booked.
  - **How:** By declaring variables with appropriate data types and access modifiers (`private`, default). `private` variables can only be accessed from within the `Bus` class itself.

- **Line 55: `static { ... }`**
  - **What:** A `static` initialization block.
  - **Why:** This block of code is executed only once, when the `Bus` class is first loaded into memory by the JVM. It's used for one-time setup for the class itself, not for individual objects.
  - **How:** Using the `static` keyword followed by curly braces.

- **Line 56: `System.out.println("Static Block: Bus Booking System Initialized");`**
  - **What:** Prints a message to the console.
  - **Why:** To indicate that the `Bus` class has been loaded and is ready to be used.

- **Line 59: `public Bus(...) {`**
  - **What:** The constructor for the `Bus` class.
  - **Why:** To create and initialize a new `Bus` object with all its necessary details.
  - **How:** It takes all the bus properties as parameters and assigns them to the instance variables using `this`.

- **Line 69: `this.seatStatus = new boolean[totalSeats + 1];`**
  - **What:** Initializes the `seatStatus` array.
  - **Why:** To create an array to track the booking status of each seat. The size is `totalSeats + 1` so that seat numbers can be used directly as indices (e.g., `seatStatus[1]` for seat 1), leaving index 0 unused for convenience.
  - **How:** Using the `new` keyword to allocate memory for the array.

- **Line 70: `busCount++;`**
  - **What:** Increments the `static` variable `busCount`.
  - **Why:** To keep a running total of how many `Bus` objects have been created in the system. Since it's `static`, this counter is shared across all instances of the `Bus` class.
  - **How:** Using the increment operator `++`.

- **Lines 73-76: Getters (`getId`, `getBusNo`, etc.)**
  - **What:** These are public "getter" methods.
  - **Why:** To provide controlled, read-only access to the `private` fields of the `Bus` class from outside the class. This is a core principle of encapsulation.
  - **How:** By defining public methods that return the value of the private variables.

- **Lines 79-81: Setters (`setOperator`, `setType`, etc.)**
  - **What:** These are public "setter" methods.
  - **Why:** To allow modification of the `Bus` object's properties from outside the class in a controlled manner.
  - **How:** By defining public methods that take a new value as a parameter and assign it to the instance variable.

- **Line 83: `public boolean isSeatAvailable(int seatNo) {`**
  - **What:** A method to check if a specific seat is available.
  - **Why:** To provide a simple way to query the availability of a seat before attempting to book it.
  - **How:** It checks if the `seatStatus` at the given `seatNo` index is `false` (available).

- **Line 89: `public void bookSeat(int seatNo) throws InvalidSeatException {`**
  - **What:** A method to book a specific seat. It can `throw` an `InvalidSeatException`.
  - **Why:** To change the status of a seat from available to booked. It includes validation to prevent booking an invalid or already booked seat.
  - **How:** It sets the `seatStatus` at the given index to `true`.

- **Line 94: `if (seatStatus[seatNo]) { throw new InvalidSeatException(...) }`**
  - **What:** Checks if the seat is already booked.
  - **Why:** To prevent double-booking. If the seat is already booked (`seatStatus[seatNo]` is true), it throws an exception to signal the error.
  - **How:** By creating and throwing a new `InvalidSeatException`.

- **Line 106: `public int getAvailableSeats() {`**
  - **What:** A method to count the total number of available seats on the bus.
  - **Why:** To quickly show users how many seats are left on a particular bus.
  - **How:** It iterates through the `seatStatus` array and counts how many elements are `false`.

- **Line 112: `void show() {`**
  - **What:** A method to display all the details of the bus.
  - **Why:** To provide a formatted, easy-to-read summary of a bus's information on the console.
  - **How:** Using a series of `System.out.println()` statements.

- **Line 125: `class Booking extends Payment implements Cancellable {`**
  - **What:** Declares a `Booking` class that `extends` the `Payment` class and `implements` the `Cancellable` interface.
  - **Why:** This class represents a ticket booking. By extending `Payment`, it inherits payment-related properties and must implement its abstract methods. By implementing `Cancellable`, it promises to provide cancellation functionality. This demonstrates both inheritance and polymorphism.
  - **How:** Using the `extends` and `implements` keywords.

- **Lines 126-141: `Booking` Instance Variables**
  - **What:** Declares all the properties related to a booking.
  - **Why:** To store comprehensive details for each booking, including a unique ID, passenger info, bus details, seat numbers, dates, and payment status.
  - **How:** Declaring variables of various types.

- **Line 143: `public Booking(...) {`**
  - **What:** The constructor for the `Booking` class.
  - **Why:** To create a new `Booking` object with all the required passenger and journey details.
  - **How:** It initializes all the instance variables. `bookingId` is automatically generated by incrementing a `static` counter.

- **Line 157: `public double calculateFare() {`**
  - **What:** A method to calculate the final fare for the booking.
  - **Why:** To compute the total cost, applying any relevant discounts (e.g., for children or seniors).
  - **How:** It multiplies the base fare by the number of seats and then subtracts any calculated discount.

- **Line 171: `@Override public boolean processPayment() {`**
  - **What:** The implementation of the `processPayment` method, which was declared as `abstract` in the parent `Payment` class.
  - **Why:** The `@Override` annotation tells the compiler that this method is intended to override a method from a superclass. This is required for the `Booking` class to be a valid subclass of `Payment`. In this simple app, it just simulates a successful payment.
  - **How:** It provides a concrete body for the abstract method.

- **Line 177: `@Override public void generateReceipt() {`**
  - **What:** The implementation of the `generateReceipt` abstract method from the `Payment` class.
  - **Why:** To provide the logic for printing a detailed ticket/receipt to the console after a successful booking and payment.
  - **How:** It uses a series of `System.out.println()` statements to format and display all the booking details.

- **Line 234: `@Override public void displayCancellationPolicy() {`**
  - **What:** The implementation of the `displayCancellationPolicy` method from the `Cancellable` interface.
  - **Why:** To fulfill the contract of the `Cancellable` interface by providing a way to show the cancellation rules.
  - **How:** It provides a concrete body for the interface method.

- **Line 243: `@Override public double calculateRefund(int hoursBeforeJourney) {`**
  - **What:** The implementation of the `calculateRefund` method from the `Cancellable` interface.
  - **Why:** To define the business logic for how much money is refunded based on how far in advance the cancellation is made.
  - **How:** It uses `if-else if` statements to return a different percentage of the fare.

- **Line 251: `@Override public void cancelBooking(int bookingId) {`**
  - **What:** The implementation of the `cancelBooking` method from the `Cancellable` interface.
  - **Why:** To handle the logic of cancelling a booking.
  - **How:** It marks the booking as cancelled, releases the seats back to the bus (`bus.cancelSeat()`), and calculates/displays the refund.

## `Passenger.java`

This file defines the `Passenger` class, which represents a user of the system who can search for buses and book tickets.

- **Line 1-2: `import java.util.Scanner;` and `import java.util.ArrayList;`**
  - **What:** Imports the `Scanner` and `ArrayList` classes.
  - **Why:** `Scanner` is for reading user input, and `ArrayList` is for storing a dynamic list of a passenger's bookings. An `ArrayList` is used because the number of bookings is not fixed.
  - **How:** Using the `import` keyword.

- **Line 4: `class Passenger extends User {`**
  - **What:** Declares a `Passenger` class that inherits from the `User` class.
  - **Why:** A `Passenger` is a specific type of `User`, so it inherits the common properties (`id`, `name`, `email`) and can have its own additional properties and methods.
  - **How:** Using the `extends` keyword.

- **Line 5-7: Instance Variables (`phone`, `myBookings`, `sc`)**
  - **What:** Declares instance variables for the `Passenger` class.
  - **Why:** `phone` stores the passenger's contact number. `myBookings` is an `ArrayList` to hold all `Booking` objects made by this passenger. `sc` is a `Scanner` object for input within this class.
  - **How:** Declaring variables of the appropriate types.

- **Line 9: `public Passenger() {`**
  - **What:** A no-argument constructor.
  - **Why:** To create a `Passenger` object without initial details. It initializes the `myBookings` list.
  - **How:** By defining a constructor with no parameters.

- **Line 14: `public Passenger(int id, String name, String email, String phone) {`**
  - **What:** A parameterized constructor.
  - **Why:** To create a `Passenger` object with all details provided at once.
  - **How:** It calls the parent `User` class's constructor using `super(id, name, email)` to initialize the inherited fields and then initializes its own `phone` field.

- **Line 21: `void searchBus() {`**
  - **What:** A method to search for buses based on a route (from and to locations).
  - **Why:** This is a primary feature for users, allowing them to find relevant buses for their journey.
  - **How:** It prompts the user for 'From' and 'To' locations, then iterates through the static array of buses (`AdminDashboard.buses`) to find and display matches.

- **Line 28: `for (int i = 0; i < AdminDashboard.busCount; i++) {`**
  - **What:** A loop that iterates through all the buses that have been added to the system.
  - **Why:** To check each bus to see if it matches the search criteria.
  - **How:** It uses the `busCount` static variable from `AdminDashboard` to know how many buses to check.

- **Line 29: `if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) && ...)`**
  - **What:** The condition to check if a bus matches the route.
  - **Why:** It compares the bus's `from` and `to` locations with the user's input. `equalsIgnoreCase` is used to make the search case-insensitive.
  - **How:** Using string comparison and the logical AND (`&&`) operator.

- **Line 42: `void showBus() {`**
  - **What:** A method to display all available buses in the system.
  - **Why:** To give the user a complete overview of all routes currently running.
  - **How:** It iterates through the `AdminDashboard.buses` array and calls the `show()` method for each bus.

- **Line 55: `void busType() {`**
  - **What:** A method to search for buses by their type (e.g., AC, Sleeper).
  - **Why:** To allow users to filter buses based on their preferences for comfort or type.
  - **How:** It prompts for a bus type and then iterates through the bus list, displaying only those that match the specified type.

- **Line 75: `void bookTicket() {`**
  - **What:** A comprehensive method that handles the entire ticket booking process.
  - **Why:** This is the core transactional method for a passenger. It guides the user from selecting a bus to choosing seats, making a payment, and getting a confirmation.
  - **How:** It's a long method that orchestrates several steps: showing buses, getting user input for bus and passenger details, selecting seats, calculating the fare, processing payment, and finally generating a receipt.

- **Line 146: `Booking booking = new Booking(...);`**
  - **What:** Creates a new `Booking` object.
  - **Why:** To encapsulate all the details of the current booking transaction into a single object.
  - **How:** By calling the `Booking` constructor with all the collected information.

- **Line 186: `myBookings.add(booking);`**
  - **What:** Adds the newly created `booking` object to the passenger's `myBookings` list.
  - **Why:** To keep a record of all bookings made by this passenger, so they can view or cancel them later.
  - **How:** By calling the `add()` method of the `ArrayList`.

- **Line 196: `void cancelTicket() {`**
  - **What:** A method to cancel a previously made booking.
  - **Why:** To allow users to cancel their tickets.
  - **How:** It first displays the user's current bookings, asks for the ID of the booking to cancel, finds that `Booking` object in the `myBookings` list, and then calls the `cancelBooking()` method on it.

- **Line 235: `void viewMyBookings() {`**
  - **What:** A method to display a summary of all bookings made by the passenger.
  - **Why:** To allow users to see their booking history and the status of each booking (confirmed or cancelled).
  - **How:** It iterates through the `myBookings` list and prints the ID and status of each `Booking` object.

- **Line 249: `@Override void showProfile() {`**
  - **What:** Overrides the `showProfile` method from the parent `User` class.
  - **Why:** To provide a more detailed profile view for a `Passenger`, including the phone number, which is not part of the base `User` class.
  - **How:** Using the `@Override` annotation and adding a line to print the `phone` number.

- **Line 258: `void display() {`**
  - **What:** The main menu loop for the passenger.
  - **Why:** To present the passenger with all available options (view buses, search, book, cancel, etc.) and handle their choices in a continuous loop.
  - **How:** It uses a `while` loop and a `switch` statement, similar to the main system menu, to navigate between the different passenger functionalities.

## `AdminDashboard.java`

This file defines the dashboard for the administrator, providing functionalities to manage the buses in the system.

- **Line 1: `import java.util.Scanner;`**
  - **What:** Imports the `Scanner` class.
  - **Why:** To read input from the admin (e.g., bus details, menu choices).
  - **How:** Using the `import` keyword.

- **Line 3: `class AdminDashboard {`**
  - **What:** Declares the `AdminDashboard` class.
  - **Why:** To group all the administrative functions like adding, viewing, updating, and deleting buses.
  - **How:** Using the `class` keyword.

- **Line 4: `private Scanner sc = new Scanner(System.in);`**
  - **What:** Creates a `private` `Scanner` object for this class.
  - **Why:** To handle all input operations within the admin dashboard. It's `private` so it's not accessible from outside this class.
  - **How:** By instantiating the `Scanner` class.

- **Line 5: `static Bus[] buses = new Bus[100];`**
  - **What:** Declares a `static` array of `Bus` objects with a fixed size of 100.
  - **Why:** This array acts as a simple in-memory database to store all the bus objects in the system. It is `static` so that there is only one list of buses shared across the entire application (accessible by both `AdminDashboard` and `Passenger` classes).
  - **How:** By declaring an array of type `Bus`.

- **Line 6: `static int busCount = 0;`**
  - **What:** Declares a `static` integer `busCount` initialized to 0.
  - **Why:** To keep track of how many buses have been added to the `buses` array. This is crucial because the array has a fixed size, and we only want to interact with the elements that have been filled.
  - **How:** By declaring a `static int`.

- **Line 8: `void addBus() {`**
  - **What:** A method to add a new bus to the system.
  - **Why:** This is a core administrative function to expand the list of available buses.
  - **How:** It prompts the admin for all the details of the new bus (ID, number, route, etc.), creates a new `Bus` object with these details, and adds it to the `buses` array.

- **Line 16: `for (int i = 0; i < busCount; i++) { ... }`**
  - **What:** A loop to check for duplicate bus IDs.
  - **Why:** To ensure that every bus has a unique ID, which is important for identifying them later (e.g., for updating or deleting).
  - **How:** It iterates through the existing buses and compares their IDs with the new ID entered by the admin.

- **Line 50: `if (fare <= 0) { throw new InvalidBusException(...) }`**
  - **What:** Input validation check.
  - **Why:** To ensure that the data entered by the admin is sensible. A bus cannot have a zero or negative fare. If the input is invalid, it throws a custom exception to signal the error.
  - **How:** Using an `if` statement and throwing a `new InvalidBusException`.

- **Line 56: `if (busCount < buses.length) { ... }`**
  - **What:** Checks if there is space in the `buses` array.
  - **Why:** To prevent an `ArrayIndexOutOfBoundsException` that would occur if the admin tries to add more than 100 buses.
  - **How:** It compares the current `busCount` with the total `length` of the array.

- **Line 57: `buses[busCount++] = newBus;`**
  - **What:** Adds the `newBus` object to the array and then increments `busCount`.
  - **Why:** This is the line that actually stores the new bus in our "database". The post-increment operator (`++` after the variable) means the current value of `busCount` is used as the index, and then `busCount` is increased by one for the next addition.
  - **How:** By assigning the object to an array index.

- **Line 64: `catch (InvalidBusException e) { ... }`**
  - **What:** A `catch` block to handle `InvalidBusException`.
  - **Why:** To gracefully manage specific, expected errors (like duplicate IDs or invalid fare) by printing a user-friendly error message instead of crashing the program.
  - **How:** The `try-catch` block structure allows for error handling.

- **Line 72: `void viewAllBuses() {`**
  - **What:** A method to display all buses currently in the system.
  - **Why:** To give the admin an overview of all the bus data.
  - **How:** It iterates through the `buses` array from index 0 up to `busCount` and calls the `show()` method for each `Bus` object.

- **Line 85: `void updateBus() {`**
  - **What:** A method to modify the details of an existing bus.
  - **Why:** To allow the admin to correct errors or update information (like departure times or operators).
  - **How:** It first asks for the ID of the bus to update, finds that bus in the array, and then presents a menu of options for what detail to change. It then updates the properties of the found `Bus` object.

- **Line 191: `void deleteBus() {`**
  - **What:** A method to remove a bus from the system.
  - **Why:** To allow the admin to remove buses that are no longer in service.
  - **How:** It asks for the ID of the bus to delete, finds its index in the array, and then shifts all subsequent elements in the array one position to the left to fill the gap. Finally, it decrements `busCount`.

- **Line 236: `void display() {`**
  - **What:** The main menu loop for the admin dashboard.
  - **Why:** To provide a continuous command-line interface for the admin to choose from various management tasks (Add, View, Update, Delete, Logout).
  - **How:** It uses a `while` loop that continues until the `exit` flag is set to `true` (when the admin chooses to logout). Inside the loop, a `switch` statement directs the program flow based on the admin's choice.
