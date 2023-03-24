import java.util.Scanner;

public class Cinema {
    private final boolean[][] seats = new boolean[9][9];
    private final int ticketPriceHigh = 10;
    private final int ticketPriceLow = 8;
    private final int m;
    private final int n;

    public Cinema(int m, int n){
        this.m = m;
        this.n = n;

        this.initializeSeats();
    }

    private void initializeSeats() {
        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                this.seats[i][j] = true; // true means available
            }
        }
    }

    public void printSeats() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for(int i=0; i<this.n; i++){
            System.out.print(" " + (i + 1));
        }
        System.out.println();


        for(int i=0; i<this.m; i++){
            System.out.print(i+1);
            for(int j=0; j<this.n; j++){
                char seat = this.seats[i][j] ? 'S' : 'B';
                System.out.print(" " + seat);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int bookSeat(int i, int j){
        // book seat
        this.seats[i-1][j-1] = false;

        // calculate the ticket price
        int ticketPrice = 0;

        if (this.m * this.n <= 60) {
            ticketPrice = this.ticketPriceHigh;
        } else {
            int frontRows = (this.m / 2);
            if(i <= frontRows) ticketPrice = this.ticketPriceHigh;
            else ticketPrice = this.ticketPriceLow;
        }
        return ticketPrice;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m, n;

        System.out.println("Enter the number of rows:");
        m = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        n = scanner.nextInt();

        Cinema cinema = new Cinema(m, n);

        boolean running = true;
        while(running){
            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "0. Exit");
            int choice = scanner.nextInt();
            System.out.println();

            switch(choice){
                case 1:
                    cinema.printSeats();
                    break;

                case 2:
                    int seatM, seatN;

                    System.out.println("Enter a row number:");
                    seatM = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    seatN = scanner.nextInt();

                    int ticketPrice = cinema.bookSeat(seatM, seatN);
                    System.out.println("Ticket price: $" + ticketPrice);

                    break;

                case 0:
                    running = false;
                    break;
            }
        }
    }
}