import java.util.Scanner;

class AdminLogin {
    String username = "admin";
    String password = "admin123";

    void login(String user, String pass) {
        boolean res = user.equals(username) && pass.equals(password);
        if(res) {
            System.out.println("Login successful.");
            AdminDashboard dashboard = new AdminDashboard();
            dashboard.display();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}

class AdminDashboard {
    Scanner sc = new Scanner(System.in);
    static Bus[] buses = new Bus[100];
    static int busCount = 0;

    void addBus() {
        System.out.println("enter bus id: ");
        int id = sc.nextInt();
        System.out.println("enter bus operator: ");
        String operator = sc.next();
        System.out.println("enter bus type: ");
        String type = sc.next();
        System.out.println("enter from location: ");
        String from = sc.next();
        System.out.println("enter to location: ");
        String to = sc.next();
        System.out.println("enter departure time: ");
        String time = sc.next();
        System.out.println("enter number of seats: ");
        int seats = sc.nextInt();
        Bus newBus = new Bus(id, operator, type, from, to, time, seats);
        if (busCount < buses.length) {
            buses[busCount++] = newBus;
            System.out.println("New bus added:");   
        }
        else{
            System.out.println("Bus limit reached. Cannot add more buses.");
        }
    }

    void updateBus(){
        viewAllBuses();
        System.out.println("Enter Bus ID to update: ");
        int id = sc.nextInt();
        for(int i = 0; i < busCount; i++) {
            if(buses[i].id == id) {
                System.out.println("Enter what you want to update \n1.Operator\n2.Type\n3.From\n4.To\n5.Time\n6.Seats\n7.All Details");
                int choice = sc.nextInt();
                if(choice >=1 && choice <=7){
                    switch(choice){
                        case 1:
                            System.out.println("Enter new operator: ");
                            String operator = sc.next();
                            buses[i].operator = operator;
                            break;
                        case 2:
                            System.out.println("Enter new bus type: ");
                            String type = sc.next();
                            buses[i].type = type;
                            break;
                        case 3:
                            System.out.println("Enter new from location: ");
                            String from = sc.next();
                            buses[i].from = from;
                            break;
                        case 4:
                            System.out.println("Enter new to location: ");
                            String to = sc.next();
                            buses[i].to = to;
                            break;
                        case 5:
                            System.out.println("Enter new departure time: ");
                            String time = sc.next();
                            buses[i].time = time;
                            break;
                        case 6:
                            System.out.println("Enter new number of seats: ");
                            int seats = sc.nextInt();
                            buses[i].seats = seats;
                            break;
                        case 7:
                            System.out.println("Enter new operator: ");
                            operator = sc.next();
                            buses[i].operator = operator;
                            System.out.println("Enter new bus type: ");
                            type = sc.next();
                            buses[i].type = type;
                            System.out.println("Enter new from location: ");
                            from = sc.next();
                            buses[i].from = from;
                            System.out.println("Enter new to location: ");
                            to = sc.next();
                            buses[i].to = to;
                            System.out.println("Enter new departure time: ");
                            time = sc.next();
                            buses[i].time = time;
                            System.out.println("Enter new number of seats: ");
                            seats = sc.nextInt();
                            buses[i].seats = seats;
                            break;
                    }
                    System.out.println("Bus updated:");
                    buses[i].show();
                    return;
                }
                else{
                    System.out.println("Invalid choice.");
                    return;
                }
            }
        }
    }

    void deleteBus(){
        if (busCount == 0) {
            System.out.println("No buses to delete.");
            return;
        }
        viewAllBuses();
        System.out.println("Enter Bus ID to delete: ");
        int id = sc.nextInt();
        for(int i = 0; i < busCount; i++) {
            if(buses[i].id == id) {
                for (int j = i; j < busCount - 1; j++) {
                    buses[j] = buses[j + 1];
                }
                buses[--busCount] = null; // Clear the last element
                System.out.println("Bus with ID " + id + " deleted.");
                return;
            }
        }
    }

    void viewAllBuses(){
        if(busCount == 0){
            System.out.println("No buses available.");
            return;
        }
        System.out.println("All Buses:");
        for(int i = 0; i < busCount; i++) {
            buses[i].show();
        }
    }

    void display() {
        System.out.println("======================");
        System.out.println("Admin Darshboard");
        System.out.println("======================");
        int choice;
        boolean exit = true;
        while (exit) {
            System.out.println("1.Add Bus\n2.View All Buses\n3.Update Bus Detail(Seats/Routes/Time)\n4.Delete Bus\n5.logout\nChoose an option: ");
            choice = sc.nextInt();
        
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
                    System.out.println("Logging out...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

class Bus {
    int id;
    String operator;
    String type;
    String from;
    String to;
    String time;
    int seats;
    static int busCount = 0;
    
    static{
        System.out.println("Static Block: Bus Booking System Initialized");
    }

    static void showBusCount() {
        System.out.println("Total Buses: " + busCount);
    }
    
    Bus(int id, String operator, String type, String from, String to, String time, int seats) {
        this.id = id;
        this.operator = operator;
        this.type = type;
        this.from = from;
        this.to = to;
        this.time = time;
        this.seats = seats;
    }

    Bus(Bus b){
        this.id = b.id;
        this.operator = b.operator;
        this.type = b.type;
        this.from = b.from;
        this.to = b.to;
        this.time = b.time;
        this.seats = b.seats;
    }
    
    void show() {
        System.out.println("==================================");
        System.out.println("Bus ID: " + id + ", Operator: " + operator + ", Type: " + type);
        System.out.println("From: " + from + " To: " + to + " Time: " + time + " Seats: " + seats);
        System.out.println();
        System.out.println("==================================");
    }
}


class Booking {
    int bookingId = 1000;
    
    void makeBooking(int busId, int seat) {
        bookingId++;
        System.out.println("Booking " + bookingId + " for Bus " + busId + " Seat " + seat);
    }
    
    void makeBooking(int busId, int seat, String type) {
        bookingId++;
        System.out.println("Booking " + bookingId + " for Bus " + busId + " Seat " + seat + " Type " + type);
    }
    
    void makeBooking(int busId, int seat, String type, double fare) {
        bookingId++;
        System.out.println("Booking " + bookingId + " for Bus " + busId + " Seat " + seat + " Type " + type + " Fare " + fare);
    }
    void makePayment(double amount){
        System.out.println("Payment of amount: " + amount + " made successfully.");
    }
    void makePayment(double amount, String method){
        System.out.println("Payment of amount: " + amount + " made successfully using " + method + ".");
    }
}

class User {
    int id;
    String name;
    String email;

    User() {}
    
    User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    void showProfile() {
        System.out.println("User ID: " + id + " Name: " + name + " Email: " + email);
    }
}

class Passenger extends User {
    String phone;
    Scanner sc = new Scanner(System.in);
    Passenger() {}

    void SearchBus(){
        System.out.println("Enter From Location: ");
        String from = sc.next();
        System.out.println("Enter To Location: ");
        String to = sc.next();
        System.out.println("Searching buses from " + from + " to " + to);
        boolean found = false;
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            if (AdminDashboard.buses[i].from.equalsIgnoreCase(from) && AdminDashboard.buses[i].to.equalsIgnoreCase(to)) {
                AdminDashboard.buses[i].show();
                found = true;
            }
        }
        if (!found) {
            System.out.println("--------------------------------");
            System.out.println("No buses found for the given route.");
            System.out.println("--------------------------------");
        }
    }

    void showBus(){
        if (AdminDashboard.busCount == 0) {
            System.out.println("--------------------------------");
            System.out.println("No buses available.");
            return;
        }
        System.out.println("Available Buses:");
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            AdminDashboard.buses[i].show();
        }
    }

    void BusType(){
        System.out.println("Search by bus type: ");
        String type = sc.next();
        boolean found = false;
        for (int i = 0; i < AdminDashboard.busCount; i++) {
            if (AdminDashboard.buses[i].type.equalsIgnoreCase(type)) {
                AdminDashboard.buses[i].show();
                found = true;
            }
        }
        if (!found) {
            System.out.println("--------------------------------");
            System.out.println("No buses found for the given type.");
            System.out.println("--------------------------------");
        }
    }

    void display(){
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean exit = true;
        while (exit) {
            System.out.println("======================");
            System.out.println("User Menu");
            System.out.println("======================");
            System.out.println("1.View Available Buses\n2.Search bus by Route\n3.Select Bus type\n4.Book Ticket\n5.Make Payment\n6.Cancle Ticket\n7.View My Booking\n8.Back to Main Menu\nChoose an option: ");
            choice = sc.nextInt();
        
            switch(choice) {
                case 1:
                    showBus();
                    break;
                case 2:
                    SearchBus();
                    break;
                case 3:
                    BusType();
                    break;
                case 4:
                    Booking book = new Booking();
                    System.out.println("Enter Bus ID: ");
                    int busId = sc.nextInt();
                    System.out.println("Enter Seat Number: ");
                    int seat = sc.nextInt();
                    book.makeBooking(busId, seat);
                    break;
                case 5:
                    Booking payment = new Booking();
                    System.out.println("Enter amount to pay: ");
                    double amount = sc.nextDouble();
                    payment.makePayment(amount);
                    break;
                case 6:
                    System.out.println("Cancel Ticket feature coming soon.");
                    break;
                case 7:
                    System.out.println("View My Booking feature coming soon.");
                    break;
                case 8:
                    System.out.println("Returning to main menu...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break; 
            }
        }
    }

    Passenger(int id, String name, String email, String phone) {
        super(id, name, email);
        this.phone = phone;
    }
    
    void showProfile() {
        System.out.println("Passenger ID: " + id + " Name: " + name + " Email: " + email + " Phone: " + phone);
    }
    
    void showBookings() {
        System.out.println(name + " has bookings");
    }
}

public class BusBookingSystem {
    public static void main(String[] args) {
        
        // Booking book = new Booking();
        // book.makeBooking(101, 5);
        // book.makeBooking(102, 10, "Window");
        // book.makeBooking(101, 15, "AC", 1500);
        // book.makePayment(1500);
        // book.makePayment(1500, "Credit Card");
        
        // Passenger p = new Passenger(201, "yatharth", "yatharth@gmail.com", "9876543210");
        // p.showProfile();
        // p.showBookings();

        // Bus buses[] = new Bus[3];
        // buses[0] = b1;
        // buses[1] = b2;
        // buses[2] = new Bus(103, "Luxury", "SLEEPER", "Ahmedabad", "Delhi", "10:00 PM", 30);
        
        // System.out.println("All Buses:");
        // for(int i = 0; i < buses.length; i++) {
        //     buses[i].show();
        // }
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        int choice;
        while(exit){
            System.out.println("======================");
            System.out.println("Bus Booking System");
            System.out.println("======================");    
            System.out.print("1. Admin Login\n2. User Menu\n3. Exit\nChoose an option: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String user = sc.next();
                    System.out.print("Enter password: ");
                    String pass = sc.next();
                    AdminLogin admin = new AdminLogin();
                    admin.login(user, pass);
                    break;
                case 2:
                    Passenger p  = new Passenger();
                    p.display();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}