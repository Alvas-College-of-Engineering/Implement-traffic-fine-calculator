import java.util.*;

public class SimpleTrafficFineSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<TrafficFine> fines = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("=== TRAFFIC FINE MANAGEMENT SYSTEM ===");
        
        // Add some sample vehicles
        vehicles.add(new Vehicle("KA01AB1234", "Toyota", "John Doe"));
        vehicles.add(new Vehicle("MH02CD5678", "Honda", "Jane Smith"));
        
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Issue Fine");
            System.out.println("2. Pay Fine");
            System.out.println("3. View All Fines");
            System.out.println("4. Add New Vehicle");
            System.out.println("5. Remove Vehicle");
            System.out.println("6. Search Fines by Vehicle");
            System.out.println("7. View Paid/Unpaid Fines");
            System.out.println("8. Edit Vehicle Information");
            System.out.println("9. Delete Fine");
            System.out.println("10. View Statistics");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        issueFine();
                        break;
                    case 2:
                        payFine();
                        break;
                    case 3:
                        viewFines();
                        break;
                    case 4:
                        addNewVehicle();
                        break;
                    case 5:
                        removeVehicle();
                        break;
                    case 6:
                        searchFinesByVehicle();
                        break;
                    case 7:
                        viewFinesByStatus();
                        break;
                    case 8:
                        editVehicle();
                        break;
                    case 9:
                        deleteFine();
                        break;
                    case 10:
                        viewStatistics();
                        break;
                    case 11:
                        System.out.println("Thank you for using the system!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void issueFine() {
        System.out.println("\n--- ISSUE FINE ---");
        
        // Show vehicles
        System.out.println("Available Vehicles:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }
        
        System.out.print("Select vehicle (1-" + vehicles.size() + "): ");
        int vehicleChoice = Integer.parseInt(scanner.nextLine());
        
        if (vehicleChoice < 1 || vehicleChoice > vehicles.size()) {
            System.out.println("Invalid vehicle selection!");
            return;
        }
        
        Vehicle vehicle = vehicles.get(vehicleChoice - 1);
        
        // Violation types
        System.out.println("\nViolation Types:");
        System.out.println("1. Speeding - $100");
        System.out.println("2. Red Light - $150");
        System.out.println("3. Illegal Parking - $50");
        System.out.println("4. No Helmet - $200");
        
        System.out.print("Select violation (1-4): ");
        int violationChoice = Integer.parseInt(scanner.nextLine());
        
        String violation;
        double amount;
        
        switch (violationChoice) {
            case 1:
                violation = "Speeding";
                amount = 100;
                break;
            case 2:
                violation = "Red Light";
                amount = 150;
                break;
            case 3:
                violation = "Illegal Parking";
                amount = 50;
                break;
            case 4:
                violation = "No Helmet";
                amount = 200;
                break;
            default:
                System.out.println("Invalid violation type!");
                return;
        }
        
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        
        // Create fine
        TrafficFine fine = new TrafficFine(
            "TFN" + (fines.size() + 1),
            vehicle,
            violation,
            amount,
            location
        );
        
        fines.add(fine);
        System.out.println("\nFine issued successfully!");
        System.out.println("Fine Number: " + fine.getFineNumber());
        System.out.println("Amount: $" + fine.getAmount());
    }
    
    private static void payFine() {
        System.out.println("\n--- PAY FINE ---");
        
        if (fines.isEmpty()) {
            System.out.println("No fines available!");
            return;
        }
        
        // Show unpaid fines
        System.out.println("Unpaid Fines:");
        int count = 0;
        for (TrafficFine fine : fines) {
            if (!fine.isPaid()) {
                count++;
                System.out.println(count + ". " + fine);
            }
        }
        
        if (count == 0) {
            System.out.println("No unpaid fines!");
            return;
        }
        
        System.out.print("Select fine to pay (1-" + count + "): ");
        int fineChoice = Integer.parseInt(scanner.nextLine());
        
        if (fineChoice < 1 || fineChoice > count) {
            System.out.println("Invalid selection!");
            return;
        }
        
        // Find the selected fine
        int index = 0;
        for (TrafficFine fine : fines) {
            if (!fine.isPaid()) {
                index++;
                if (index == fineChoice) {
                    System.out.print("Enter payment amount: $" + fine.getAmount() + ": ");
                    double payment = Double.parseDouble(scanner.nextLine());
                    
                    if (payment >= fine.getAmount()) {
                        fine.setPaid(true);
                        
                        // Remove driver name from vehicle after payment
                        Vehicle vehicle = fine.getVehicle();
                        String driverName = vehicle.getOwner();
                        vehicle.setOwner("Driver removed after payment");
                        
                        System.out.println("Payment successful!");
                        System.out.println("Fine " + fine.getFineNumber() + " is now paid.");
                        System.out.println("Driver '" + driverName + "' has been removed from vehicle " + vehicle.getRegistrationNumber());
                    } else {
                        System.out.println("Insufficient amount!");
                    }
                    break;
                }
            }
        }
    }
    
    private static void addNewVehicle() {
        System.out.println("\n--- ADD NEW VEHICLE ---");
        
        System.out.print("Enter vehicle registration number: ");
        String registrationNumber = scanner.nextLine();
        
        // Check if vehicle already exists
        for (Vehicle v : vehicles) {
            if (v.getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                System.out.println("Vehicle with this registration number already exists!");
                return;
            }
        }
        
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        
        System.out.print("Enter driver name: ");
        String driverName = scanner.nextLine();
        
        Vehicle newVehicle = new Vehicle(registrationNumber, make, driverName);
        vehicles.add(newVehicle);
        
        System.out.println("Vehicle added successfully!");
        System.out.println("Registration: " + registrationNumber);
        System.out.println("Make: " + make);
        System.out.println("Driver: " + driverName);
    }
    
    private static void removeVehicle() {
        System.out.println("\n--- REMOVE VEHICLE ---");
        
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available!");
            return;
        }
        
        System.out.println("Available Vehicles:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }
        
        System.out.print("Select vehicle to remove (1-" + vehicles.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice < 1 || choice > vehicles.size()) {
            System.out.println("Invalid selection!");
            return;
        }
        
        Vehicle vehicle = vehicles.get(choice - 1);
        
        // Check if vehicle has unpaid fines
        boolean hasUnpaidFines = false;
        for (TrafficFine fine : fines) {
            if (fine.getVehicle().equals(vehicle) && !fine.isPaid()) {
                hasUnpaidFines = true;
                break;
            }
        }
        
        if (hasUnpaidFines) {
            System.out.println("Cannot remove vehicle! Vehicle has unpaid fines.");
            return;
        }
        
        vehicles.remove(choice - 1);
        System.out.println("Vehicle removed successfully!");
    }
    
    private static void searchFinesByVehicle() {
        System.out.println("\n--- SEARCH FINES BY VEHICLE ---");
        
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available!");
            return;
        }
        
        System.out.println("Available Vehicles:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }
        
        System.out.print("Select vehicle (1-" + vehicles.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice < 1 || choice > vehicles.size()) {
            System.out.println("Invalid selection!");
            return;
        }
        
        Vehicle vehicle = vehicles.get(choice - 1);
        System.out.println("\nFines for vehicle " + vehicle.getRegistrationNumber() + ":");
        
        boolean found = false;
        for (TrafficFine fine : fines) {
            if (fine.getVehicle().equals(vehicle)) {
                System.out.println(fine);
                System.out.println("---");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No fines found for this vehicle.");
        }
    }
    
    private static void viewFinesByStatus() {
        System.out.println("\n--- VIEW FINES BY STATUS ---");
        System.out.println("1. View Paid Fines");
        System.out.println("2. View Unpaid Fines");
        System.out.print("Enter choice: ");
        
        int choice = Integer.parseInt(scanner.nextLine());
        String status;
        
        switch (choice) {
            case 1:
                status = "PAID";
                break;
            case 2:
                status = "UNPAID";
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        System.out.println("\n--- " + status.toUpperCase() + " FINES ---");
        boolean found = false;
        
        for (TrafficFine fine : fines) {
            if ((choice == 1 && fine.isPaid()) || (choice == 2 && !fine.isPaid())) {
                System.out.println(fine);
                System.out.println("---");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No " + status.toLowerCase() + " fines found.");
        }
    }
    
    private static void editVehicle() {
        System.out.println("\n--- EDIT VEHICLE INFORMATION ---");
        
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available!");
            return;
        }
        
        System.out.println("Available Vehicles:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }
        
        System.out.print("Select vehicle to edit (1-" + vehicles.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice < 1 || choice > vehicles.size()) {
            System.out.println("Invalid selection!");
            return;
        }
        
        Vehicle vehicle = vehicles.get(choice - 1);
        
        System.out.println("Current vehicle information:");
        System.out.println("Registration: " + vehicle.getRegistrationNumber());
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Driver: " + vehicle.getOwner());
        
        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Driver Name");
        System.out.println("2. Vehicle Make");
        System.out.print("Enter choice: ");
        
        int editChoice = Integer.parseInt(scanner.nextLine());
        
        switch (editChoice) {
            case 1:
                System.out.print("Enter new driver name: ");
                String newDriver = scanner.nextLine();
                vehicle.setOwner(newDriver);
                System.out.println("Driver name updated successfully!");
                break;
            case 2:
                System.out.print("Enter new vehicle make: ");
                String newMake = scanner.nextLine();
                vehicle.setMake(newMake);
                System.out.println("Vehicle make updated successfully!");
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
    }
    
    private static void deleteFine() {
        System.out.println("\n--- DELETE FINE ---");
        
        if (fines.isEmpty()) {
            System.out.println("No fines available!");
            return;
        }
        
        System.out.println("Available Fines:");
        for (int i = 0; i < fines.size(); i++) {
            System.out.println((i + 1) + ". " + fines.get(i).getFineNumber() + 
                             " - " + fines.get(i).getViolation() + 
                             " ($" + fines.get(i).getAmount() + ")");
        }
        
        System.out.print("Select fine to delete (1-" + fines.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        
        if (choice < 1 || choice > fines.size()) {
            System.out.println("Invalid selection!");
            return;
        }
        
        TrafficFine fine = fines.get(choice - 1);
        
        System.out.println("Fine to delete:");
        System.out.println(fine);
        System.out.print("Are you sure you want to delete this fine? (y/n): ");
        
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            fines.remove(choice - 1);
            System.out.println("Fine deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static void viewStatistics() {
        System.out.println("\n--- SYSTEM STATISTICS ---");
        
        int totalFines = fines.size();
        int paidFines = 0;
        int unpaidFines = 0;
        double totalRevenue = 0;
        double totalUnpaid = 0;
        
        for (TrafficFine fine : fines) {
            if (fine.isPaid()) {
                paidFines++;
                totalRevenue += fine.getAmount();
            } else {
                unpaidFines++;
                totalUnpaid += fine.getAmount();
            }
        }
        
        System.out.println("Total Vehicles: " + vehicles.size());
        System.out.println("Total Fines: " + totalFines);
        System.out.println("Paid Fines: " + paidFines);
        System.out.println("Unpaid Fines: " + unpaidFines);
        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("Total Unpaid Amount: $" + totalUnpaid);
        
        if (totalFines > 0) {
            System.out.println("Payment Rate: " + String.format("%.1f%%", (paidFines * 100.0 / totalFines)));
        }
        
        // Most common violation
        Map<String, Integer> violationCounts = new HashMap<>();
        for (TrafficFine fine : fines) {
            violationCounts.put(fine.getViolation(), violationCounts.getOrDefault(fine.getViolation(), 0) + 1);
        }
        
        if (!violationCounts.isEmpty()) {
            String mostCommon = Collections.max(violationCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
            System.out.println("Most Common Violation: " + mostCommon + " (" + violationCounts.get(mostCommon) + " times)");
        }
    }
    
    private static void viewFines() {
        System.out.println("\n--- ALL FINES ---");
        
        if (fines.isEmpty()) {
            System.out.println("No fines issued yet!");
            return;
        }
        
        for (TrafficFine fine : fines) {
            System.out.println(fine);
            System.out.println("---");
        }
    }
}

// Simple Vehicle class
class Vehicle {
    private String registrationNumber;
    private String make;
    private String owner;
    
    public Vehicle(String registrationNumber, String make, String owner) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.owner = owner;
    }
    
    public String getRegistrationNumber() { return registrationNumber; }
    public String getMake() { return make; }
    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }
    public void setMake(String make) { this.make = make; }
    
    @Override
    public String toString() {
        return registrationNumber + " - " + make + " (Owner: " + owner + ")";
    }
}

// Simple TrafficFine class
class TrafficFine {
    private String fineNumber;
    private Vehicle vehicle;
    private String violation;
    private double amount;
    private String location;
    private boolean paid;
    
    public TrafficFine(String fineNumber, Vehicle vehicle, String violation, double amount, String location) {
        this.fineNumber = fineNumber;
        this.vehicle = vehicle;
        this.violation = violation;
        this.amount = amount;
        this.location = location;
        this.paid = false;
    }
    
    public String getFineNumber() { return fineNumber; }
    public Vehicle getVehicle() { return vehicle; }
    public String getViolation() { return violation; }
    public double getAmount() { return amount; }
    public String getLocation() { return location; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
    
    @Override
    public String toString() {
        return "Fine: " + fineNumber + 
               "\nVehicle: " + vehicle.getRegistrationNumber() + 
               "\nViolation: " + violation + 
               "\nAmount: $" + amount + 
               "\nLocation: " + location + 
               "\nStatus: " + (paid ? "PAID" : "UNPAID");
    }
}
