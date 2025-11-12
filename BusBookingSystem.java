import java.util.Scanner;

public class BusBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true){
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}