# User.java - Complete Code Explanation

## File Overview
**Purpose**: This file defines the base `User` class that serves as a parent class for different types of users in the Bus Booking System (like Passenger).

**Why**: Provides a common structure for all user types to avoid code duplication and establish a hierarchy.

**How**: Uses Java class inheritance - other classes can extend this to inherit its properties and methods.

---

## Line-by-Line Explanation

### Line 1: Class Declaration
```java
class User {
```
**What**: Declares a class named `User`  
**Why**: To create a blueprint for user objects in the system  
**How**: Uses the `class` keyword followed by the class name  
**Note**: No access modifier means it has package-private access (accessible within the same package)

---

### Line 2: Instance Variable - id
```java
    int id;
```
**What**: Declares an integer variable to store user ID  
**Why**: Every user needs a unique identifier for tracking and reference  
**How**: Instance variable (belongs to each object created from this class)  
**Type**: `int` - can store integers from -2,147,483,648 to 2,147,483,647  
**Access**: Package-private (no modifier specified)

---

### Line 3: Instance Variable - name
```java
    String name;
```
**What**: Declares a String variable to store user's name  
**Why**: To identify the user by their name in the system  
**How**: `String` is a reference type that holds text data  
**Default Value**: `null` (since not initialized)  
**Access**: Package-private

---

### Line 4: Instance Variable - email
```java
    String email;
```
**What**: Declares a String variable to store user's email address  
**Why**: For communication and user identification  
**How**: Stores email as a text string  
**Usage**: Could be used for login, notifications, or contact purposes  
**Access**: Package-private

---

### Line 6: Default Constructor
```java
    User() {}
```
**What**: A no-argument (default) constructor  
**Why**: Allows creating User objects without providing initial values  
**How**: Empty body `{}` means no initialization code  
**When Called**: `User u = new User();`  
**Result**: Creates a User object with default values (id=0, name=null, email=null)  
**Purpose**: Provides flexibility in object creation

---

### Line 8: Parameterized Constructor - Declaration
```java
    User(int id, String name, String email) {
```
**What**: Constructor that accepts three parameters  
**Why**: To create User objects with initial values in one step  
**How**: Takes three arguments and uses them to initialize instance variables  
**Parameters**:
- `int id` - the user's ID number
- `String name` - the user's name
- `String email` - the user's email address

**Usage Example**: `User u = new User(1, "John", "john@example.com");`

---

### Line 9: Assigning id
```java
        this.id = id;
```
**What**: Assigns the parameter `id` to the instance variable `id`  
**Why**: To initialize the object's id field with the provided value  
**How**: `this.id` refers to the instance variable, `id` refers to the parameter  
**this Keyword**: Distinguishes between instance variable and parameter with same name  
**Without this**: Would cause ambiguity since both have the same name

---

### Line 10: Assigning name
```java
        this.name = name;
```
**What**: Assigns the parameter `name` to the instance variable `name`  
**Why**: To store the user's name in the object  
**How**: Uses `this` keyword to refer to the instance variable  
**Effect**: The object now has a name that can be accessed later

---

### Line 11: Assigning email
```java
        this.email = email;
```
**What**: Assigns the parameter `email` to the instance variable `email`  
**Why**: To store the user's email in the object  
**How**: `this.email` = instance variable, `email` = parameter  
**Result**: Object is fully initialized with all provided values

---

### Line 12: Constructor Closing Brace
```java
    }
```
**What**: Closes the parameterized constructor  
**Why**: Marks the end of the constructor's code block  
**Effect**: After this line, the User object is created and initialized

---

### Line 14: Method Declaration - showProfile
```java
    void showProfile() {
```
**What**: Declares a method to display user profile information  
**Why**: To provide a way to view user details  
**How**: Method with no parameters and no return value  
**Return Type**: `void` - doesn't return anything  
**Access**: Package-private (no modifier)  
**Purpose**: Encapsulates the display logic for user information

---

### Line 15: Print Statement
```java
        System.out.println("User ID: " + id + " Name: " + name + " Email: " + email);
```
**What**: Prints user information to console  
**Why**: To display the user's profile details  
**How**: 
- `System.out.println()` - prints to console with newline
- String concatenation using `+` operator
- Accesses instance variables directly (no `this` needed)

**Output Example**: `User ID: 1 Name: John Email: john@example.com`

**Components**:
- `"User ID: "` - literal string
- `id` - integer (automatically converted to String)
- `" Name: "` - literal string
- `name` - String variable
- `" Email: "` - literal string
- `email` - String variable

---

### Line 16: Method Closing Brace
```java
    }
```
**What**: Closes the `showProfile()` method  
**Why**: Marks the end of the method's code block

---

### Line 17: Class Closing Brace
```java
}
```
**What**: Closes the `User` class  
**Why**: Marks the end of the class definition  
**Effect**: Complete class is now defined and can be used

---

## Summary

### Class Structure
- **3 Instance Variables**: id, name, email
- **2 Constructors**: default (no-args) and parameterized (3-args)
- **1 Method**: showProfile()

### Design Principles Used
1. **Encapsulation**: Data (variables) and behavior (methods) bundled together
2. **Constructor Overloading**: Two constructors with different parameters
3. **this Keyword**: Used to distinguish instance variables from parameters

### Purpose in System
- **Base Class**: Designed to be extended by other user types
- **Common Attributes**: All users have id, name, and email
- **Inheritance Ready**: Other classes (like Passenger) can extend this

### Relationships
- **Parent of**: `Passenger` class (as seen in Passenger.java: `class Passenger extends User`)
- **Part of**: Bus Booking System application

### Usage Pattern
```java
// Direct instantiation
User user1 = new User();  // Default constructor
User user2 = new User(1, "Alice", "alice@email.com");  // Parameterized

// Display profile
user2.showProfile();  // Output: User ID: 1 Name: Alice Email: alice@email.com

// Access variables
int userId = user2.id;
String userName = user2.name;
```

### Key Concepts Demonstrated
1. **Class Definition**: Basic structure of a Java class
2. **Instance Variables**: Object-specific data storage
3. **Constructors**: Object initialization mechanisms
4. **Methods**: Object behaviors/actions
5. **this Keyword**: Resolving naming conflicts
6. **String Concatenation**: Combining strings with + operator
