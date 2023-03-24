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

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
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

        if(this.seats[i-1][j-1]){
            // book seat
            this.seats[i-1][j-1] = false;

            // calculate the ticket price
            int ticketPrice;

            if (this.m * this.n <= 60) {
                ticketPrice = this.ticketPriceHigh;
            } else {
                int frontRows = (this.m / 2);
                if(i <= frontRows) ticketPrice = this.ticketPriceHigh;
                else ticketPrice = this.ticketPriceLow;
            }
            return ticketPrice;
        } else {
            return -1;
        }
    }

    public void printStatistics(){
        int purchasedTicketsCount = 0;
        float purchasedTicketsPercent;
        int currentIncome = 0;
        int totalIncome = 0;

        for(int i=0; i<this.m; i++){
            for(int j=0; j<this.n; j++){
                if(!this.seats[i][j]){
                    purchasedTicketsCount++;

                    if (this.m * this.n <= 60) {
                        currentIncome += this.ticketPriceHigh;
                    } else {
                        int frontRows = (this.m / 2);
                        if(i < frontRows) currentIncome += this.ticketPriceHigh;
                        else currentIncome += this.ticketPriceLow;
                    }
                } else {
                    if (this.m * this.n <= 60) {
                        totalIncome += this.ticketPriceHigh;
                    } else {
                        int frontRows = (this.m / 2);
                        if(i < frontRows) totalIncome += this.ticketPriceHigh;
                        else totalIncome += this.ticketPriceLow;
                    }
                }
            }
        }

        totalIncome += currentIncome;
        purchasedTicketsPercent = (float) (purchasedTicketsCount * 100) / (this.m * this.n);

        System.out.println("Number of purchased tickets: " + purchasedTicketsCount);
        System.out.println("Percentage: " + String.format("%.2f", purchasedTicketsPercent) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
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
            System.out.println("""

                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> cinema.printSeats();
                case 2 -> {
                    boolean isBookingDone = false;
                    while (!isBookingDone) {
                        boolean isBookingDoneInner = false;
                        int seatM = -1, seatN = -1;

                        while (!isBookingDoneInner) {
                            System.out.println("Enter a row number:");
                            seatM = scanner.nextInt();

                            System.out.println("Enter a seat number in that row:");
                            seatN = scanner.nextInt();

                            if ((cinema.getM() < seatM) || (cinema.getN() < seatN)) {
                                System.out.println("\nWrong input!\n");
                            } else {
                                isBookingDoneInner = true;
                            }
                        }

                        int ticketPrice = cinema.bookSeat(seatM, seatN);
                        if (ticketPrice == -1) {
                            System.out.println("\nThat ticket has already been purchased!\n");
                        } else {
                            isBookingDone = true;
                            System.out.println("\nTicket price: $" + ticketPrice);
                        }
                    }
                }
                case 3 -> cinema.printStatistics();
                case 0 -> running = false;
            }
        }
    }
}