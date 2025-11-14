import java.util.Scanner;

class AdminDashboard {
    private Scanner sc = new Scanner(System.in);
    static Bus[] buses = new Bus[100];
    static int busCount = 0;

    void addBus() {
        try {
            System.out.println("\n=== ADD NEW BUS ===");
            
            System.out.print("Enter Bus ID: ");
            int id = sc.nextInt();
            
            // Check if ID already exists
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    throw new InvalidBusException("Bus ID " + id + " already exists!");
                }
            }
            
            System.out.print("Enter Bus Number: ");
            String busNo = sc.next();
            
            System.out.print("Enter Bus Operator: ");
            sc.nextLine(); // consume newline
            String operator = sc.nextLine();
            
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
                throw new InvalidBusException("Number of seats must be positive!");
            }
            
            System.out.print("Enter Distance (km): ");
            double distanceKm = sc.nextDouble();
            if (distanceKm <= 0) {
                throw new InvalidBusException("Distance must be positive!");
            }

            System.out.print("Enter Price per km (Rs.): ");
            double pricePerKm = sc.nextDouble();
            if (pricePerKm <= 0) {
                throw new InvalidBusException("Price per km must be positive!");
            }

            Bus newBus = new Bus(id, busNo, operator, type, from, to, time, seats, distanceKm, pricePerKm);
            
            if (busCount < buses.length) {
                buses[busCount++] = newBus;
                System.out.println("\n✓ Bus added successfully!");
                newBus.show();
            } else {
                throw new InvalidBusException("Bus limit reached. Cannot add more buses.");
            }
            
        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Invalid input. Please try again.");
            sc.nextLine();
        }
    }

    void viewAllBuses() {
        if (busCount == 0) {
            System.out.println("\nNo buses available.");
            return;
        }
        
        System.out.println("\n=== ALL BUSES ===\n");
        for (int i = 0; i < busCount; i++) {
            buses[i].show();
        }
    }

    void updateBus() {
        if (busCount == 0) {
            System.out.println("No buses available to update.");
            return;
        }
        
        viewAllBuses();
        
        try {
            System.out.print("\nEnter Bus ID to update: ");
            int id = sc.nextInt();
            
            Bus targetBus = null;
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    targetBus = buses[i];
                    break;
                }
            }
            
            if (targetBus == null) {
                throw new InvalidBusException("Bus with ID " + id + " not found!");
            }
            
            System.out.println("\n=== UPDATE OPTIONS ===");
            System.out.println("1. Operator");
            System.out.println("2. Type");
            System.out.println("3. From Location");
            System.out.println("4. To Location");
            System.out.println("5. Departure Time");
            System.out.println("6. All Details");
            System.out.println("7. Fare Settings (Distance & Price per km)");
            System.out.print("Choose what to update: ");

            int choice = sc.nextInt();

            if (choice < 1 || choice > 7) {
                throw new InvalidBusException("Invalid choice!");
            }
            
            switch(choice) {
                case 1:
                    System.out.print("Enter new operator: ");
                    sc.nextLine();
                    String operator = sc.nextLine();
                    targetBus.setOperator(operator);
                    break;
                    
                case 2:
                    System.out.print("Enter new bus type: ");
                    String type = sc.next();
                    targetBus.setType(type);
                    break;
                    
                case 3:
                    System.out.print("Enter new from location: ");
                    String from = sc.next();
                    targetBus.from = from;
                    break;
                    
                case 4:
                    System.out.print("Enter new to location: ");
                    String to = sc.next();
                    targetBus.to = to;
                    break;
                    
                case 5:
                    System.out.print("Enter new departure time: ");
                    String time = sc.next();
                    targetBus.setTime(time);
                    break;
                    
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
            }
            
            System.out.println("\n✓ Bus updated successfully!");
            targetBus.show();
            
        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n✗ Invalid input. Please try again.");
            sc.nextLine();
        }
    }

    void deleteBus() {
        if (busCount == 0) {
            System.out.println("No buses to delete.");
            return;
        }
        
        viewAllBuses();
        
        try {
            System.out.print("\nEnter Bus ID to delete: ");
            int id = sc.nextInt();
            
            int index = -1;
            for (int i = 0; i < busCount; i++) {
                if (buses[i].getId() == id) {
                    index = i;
                    break;
                }
            }
            
            if (index == -1) {
                throw new InvalidBusException("Bus with ID " + id + " not found!");
            }
            
            System.out.print("Are you sure you want to delete this bus? (yes/no): ");
            String confirm = sc.next();
            
            if (confirm.equalsIgnoreCase("yes")) {
                for (int j = index; j < busCount - 1; j++) {
                    buses[j] = buses[j + 1];
                }
                buses[--busCount] = null;
                System.out.println("\n✓ Bus with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
            
        } catch (InvalidBusException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    void display() {
        System.out.println("\n======================");
        System.out.println("   ADMIN DASHBOARD");
        System.out.println("======================");
        
        int choice;
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n1. Add Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Update Bus Details");
            System.out.println("4. Delete Bus");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            
            try {
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
                        System.out.println("\n✓ Logging out...");
                        exit = true;
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("\n✗ Invalid input. Please enter a number.");
                sc.nextLine(); // clear buffer
            }
        }
    }
}