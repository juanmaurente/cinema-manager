package cinema;

public class Cinema {

    public static void main(String[] args) {
        System.out.println("Cinema:");

        // Print column numbers
        System.out.print("  ");
        for (int col = 1; col <= 8; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        // Print seat arrangement
        for (int row = 1; row <= 7; row++) {
            System.out.print(row + " ");
            for (int col = 1; col <= 8; col++) {
                System.out.print("S ");
            }
            System.out.println();
        }
    }
}
