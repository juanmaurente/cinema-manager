package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the input to define totalRows and seats
        System.out.println("Enter the number of rows:");
        int totalRows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = sc.nextInt();
        int purchasedTickets = 0;
        double currentIncome = 0;
        double totalIncome = calculateTotalIncome(totalRows, seatsPerRow);

        // Initialize cinema array with "S" values
        String[][] cinema = new String[totalRows][seatsPerRow];
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                cinema[i][j] = "S";
            }
        }

        // Print a menu with three items
        // 1. Show the seats
        // 2. Buy a ticket
        // 0. Exit to stop the program

        double[] purchaseResult;

        int choice;

        do {
            choice = showMenu(sc);
            // Get user input for menu choice
            System.out.println();

            // Perform actions based on user choice
            switch (choice) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    purchaseResult = buyTicket(cinema, totalRows, seatsPerRow, purchasedTickets, currentIncome, totalIncome, sc);
                    purchasedTickets = (int) purchaseResult[0];
                    currentIncome = purchaseResult[1];
                    break;
                case 3:
                    displayStatistics(purchasedTickets, currentIncome, totalIncome, totalRows, seatsPerRow);
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        // Close the scanner
        sc.close();
    }

        private static void displayStatistics(int purchasedTickets, double currentIncome, double totalIncome ,int totalRows, int seatsPerRow) {
            double percentagePurchased = (double) purchasedTickets / (totalRows * seatsPerRow) * 100;


            System.out.println();
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percentagePurchased);
        System.out.printf("Current income: $%.0f%n", currentIncome);
        System.out.printf("Total income: $%.0f%n", totalIncome);
        System.out.println();
    }



    private static double calculateTotalIncome(int totalRows, int seatsPerRow) {
        double totalIncome = 0;

        for (int row = 1; row <= totalRows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                totalIncome += calculateTicketPrice(totalRows, seatsPerRow, row);
            }
        }

        return totalIncome;
    }


    private static void showSeats(String[][] cinema) {
        System.out.println("Cinema: ");

        // Print Column numbers
        System.out.print("  ");
        for (int col = 1; col <= cinema[0].length; col++) {
            System.out.printf("%-2d", col);
        }
        System.out.println();

        // Print number of Seat line or row
        for (int row = 0; row < cinema.length; row++) {
            System.out.printf("%-2d", row + 1); // Number of line or row

            for (int col = 0; col < cinema[row].length; col++) {
                System.out.printf("%-2s", cinema[row][col]);
            }
            System.out.println();
        }
    }

    private static int calculateTicketPrice(int totalRows, int seatsPerRow, int rowSelected) {
        if (totalRows * seatsPerRow >= 60) {
            return (rowSelected <= totalRows / 2) ? 10 : 8;
        } else {
            return 10;
        }
    }
    private static int showMenu(Scanner sc) {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return sc.nextInt();
    }

    private static double[] buyTicket(String[][] cinema, int totalRows, int seatsPerRow, int purchasedTickets, double currentIncome, double totalIncome, Scanner sc) {
        boolean seatAlreadyPurchased;

        do {
            // Read two integers: a row number and a seat in that row [row][seat of row]
            System.out.println("Enter a row number:");
            sc.nextLine();  // Consume the pending newline character
            int rowSelected = sc.nextInt();

            System.out.println("Enter a seat number in that row:");
            sc.nextLine();  // Consume the pending newline character
            int seatSelected = sc.nextInt();
            int ticketPrice = 0;

            seatAlreadyPurchased = false;

            // Check if coordinates are out of bounds
            if (rowSelected < 1 || rowSelected > totalRows || seatSelected < 1 || seatSelected > seatsPerRow) {
                System.out.println("Wrong input! Please enter valid seat coordinates.");
                continue;
            }

            // Check if the seat has already been purchased
            if (cinema[rowSelected - 1][seatSelected - 1].equals("B")) {
                System.out.println("That ticket has already been purchased! Please choose another seat.");
                seatAlreadyPurchased = true;
            } else {

                ticketPrice= calculateTicketPrice(totalRows, seatsPerRow,rowSelected);

                // Print the ticket price
                System.out.printf("Ticket price: $%d", ticketPrice);
                System.out.println();

                // Update the statistics
                purchasedTickets++;
                currentIncome += ticketPrice;
                displayStatistics(purchasedTickets, currentIncome, totalIncome, totalRows,seatsPerRow);

                // Update the seat matrix and show the current state of the seats
                cinema[rowSelected - 1][seatSelected - 1] = "B";
                showSeats(cinema);
            }
        } while (seatAlreadyPurchased);

        return new double[]{purchasedTickets, currentIncome};
    }
}
