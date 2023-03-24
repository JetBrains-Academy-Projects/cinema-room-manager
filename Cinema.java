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
    }

    public void initializeSeats() {
        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                this.seats[i][j] = true; // true means available
            }
        }
    }

    public void printSeats() {
        System.out.println("\nCinema:");
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
        int seatM, seatN;

        System.out.println("Enter the number of rows:");
        m = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        n = scanner.nextInt();

        Cinema cinema = new Cinema(m, n);

        cinema.initializeSeats();
        cinema.printSeats();

        System.out.println();
        System.out.println("Enter a row number:");
        seatM = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        seatN = scanner.nextInt();

        int ticketPrice = cinema.bookSeat(seatM, seatN);

        System.out.println("Ticket price: $" + ticketPrice);
        cinema.printSeats();
    }
}