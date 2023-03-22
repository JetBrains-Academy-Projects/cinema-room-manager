public class Cinema {

    public static void main(String[] args) {
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        for(int i=0; i<7; i++){
            System.out.print(i+1);
            for(int j=0; j<8; j++){
                System.out.print(" S");
            }
            System.out.println();
        }
    }
}