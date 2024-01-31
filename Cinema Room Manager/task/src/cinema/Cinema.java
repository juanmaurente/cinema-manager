import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //read the input to define totalRows and seats
        System.out.println("Enter the number of rows:");
        int totalRows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = sc.nextInt();

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

        int choice;
        do {
            // Print the menu
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("0. Exit");

            // Get user input for menu choice
            choice = sc.nextInt();
            System.out.println();

            // Perform actions based on user choice
            switch (choice) {
                case 1:
                    showSeats(cinema);
                    break;
                case 2:
                    buyTicket(cinema, totalRows, seatsPerRow);
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

    private static void buyTicket(String[][] cinema, int totalRows, int seatsPerRow) {
        Scanner sc = new Scanner(System.in);
        // read two integers: a row number and a seat in that row [row][seat of row]
        System.out.println("Enter a row number:");
        int rowSelected = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatSelected = sc.nextInt();
        int ticketPrice = 0;

        // Define ticketPrice according to the selected seat
        if (totalRows * seatsPerRow >= 60) {
            if (rowSelected <= totalRows / 2) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        } else {
            ticketPrice = 10;
        }

        System.out.printf("Ticket price: $%d\n", ticketPrice);
        System.out.println();
        System.out.println("Cinema: ");

        // Print column numbers
        System.out.print("  ");
        for (int col = 1; col <= cinema[0].length; col++) {
            System.out.printf("%-2d", col);
        }
        System.out.println();

        // Update the selected seat with 'B' and print the seats
        for (int row = 0; row < cinema.length; row++) {
            System.out.printf("%-2d", row + 1);

            for (int col = 0; col < cinema[row].length; col++) {
                if (row + 1 == rowSelected && col + 1 == seatSelected) {
                    cinema[row][col] = "B";
                }

                System.out.printf("%-2s", cinema[row][col]);
            }
            System.out.println();
        }

        // Close the scanner
        sc.close();
    }
}
