import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        int ticketPriceHigh = 10;
        int ticketPriceLow = 8;
        Scanner scanner = new Scanner(System.in);
        int totalIncome;

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();

        if (rows * seatsPerRow <= 60) {
            totalIncome = rows * seatsPerRow * ticketPriceHigh;
        } else {
            if (rows % 2 == 0) {
                totalIncome = (rows / 2) * seatsPerRow * ticketPriceHigh;
                totalIncome += (rows / 2) * seatsPerRow * ticketPriceLow;
            } else {
                int frontRows = (rows / 2);
                totalIncome = frontRows * seatsPerRow * ticketPriceHigh;
                totalIncome += (rows - frontRows) * seatsPerRow * ticketPriceLow;
            }
        }

        System.out.println("Total income:\n$" + totalIncome);
    }
}