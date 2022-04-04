import java.util.Scanner;

public class CinemaRoomManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows;
        int seatsInRow;
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsInRow = scanner.nextInt();
        Cinema1 cinema = new Cinema1(rows,seatsInRow);
        System.out.println(cinema.cinemaRoom[0][0]);
        int option;
        boolean power = true;
        while(power) {
            cinema.menu();
            option = scanner.nextInt();
            switch (option) {
                case 0 -> power = false;
                case 1 -> cinema.printRoom();
                case 2 -> cinema.takeAndBuySeat();
                case 3 -> {
                    cinema.printPurchasedTickets();
                    cinema.printPercentage();
                    cinema.printCurrentIncome();
                    cinema.printTotalIncome();
                }
            }
        }
    }
}

class Cinema1 {
    int rows;
    int seatsInRow;
    char[][] cinemaRoom;
    int currentIncome = 0;
    int purchasedTickets = 0;

    public Cinema1(int a, int b) {
        rows = a;
        seatsInRow = b;
        cinemaRoom = new char[a+1][b+1];
        cinemaRoom[0][0] = ' ';
        for(int i=1; i<a+1; i++) {
            cinemaRoom[i][0] = (char)(i+48);
        }
        for(int i=1; i<b+1; i++) {
            cinemaRoom[0][i] = (char)(i+48);
        }
        for(int i = 1; i < a + 1; i++) {
            for(int j = 1; j < b + 1; j++) {
                cinemaRoom[i][j] = 'S';
            }
        }
    }

    void printRoom() {
        System.out.println("Cinema:");
        for(int i = 0; i < rows + 1; i++) {
            for(int j = 0; j < seatsInRow + 1; j++) {
                System.out.print(cinemaRoom[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    void takeAndBuySeat() {
        Scanner seat = new Scanner(System.in);
        int x,y;
        System.out.println("Enter a row number:");
        x = seat.nextInt();
        System.out.println("Enter a seat number in that row:");
        y = seat.nextInt();
        if (x > rows || y > seatsInRow || x < 1 || y < 1) {
            System.out.println("Wrong input!");
            takeAndBuySeat();
        } else {
            if (cinemaRoom[x][y] == 'B') {
                System.out.println("That ticket has already been purchased!");
                takeAndBuySeat();
            } else {
                cinemaRoom[x][y] = 'B';
                ticketPrice(x);
            }
        }
    }

    void ticketPrice(int x) {
        if(rows * seatsInRow <= 60) {
            System.out.println("Ticket price: $10");
            currentIncome += 10;
            purchasedTickets++;
        } else if (x <= rows/2 ) {
            System.out.println("Ticket price: $10");
            currentIncome += 10;
            purchasedTickets++;
        } else {
            System.out.println("Ticket price: $8");
            currentIncome += 8;
            purchasedTickets++;
        }
    }

    void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    void printTotalIncome() {
        if(rows * seatsInRow <= 60) {
            System.out.println("Total income: $" + rows * seatsInRow * 10);
        } else {
            System.out.println("Total income: $" + ((seatsInRow * (rows/2) * 10) + (seatsInRow *(rows - rows/2) * 8)));
        }
    }

    void printPercentage() {
        double percentage = ((double)(purchasedTickets*100) / (double)(rows * seatsInRow));
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        //System.out.println("Percentage: " + percentage + "%");
    }

    void printPurchasedTickets() {
        System.out.println("Number of purchased tickets: " + purchasedTickets);
    }

    void printCurrentIncome() {
        System.out.println("Current income $" + currentIncome);
    }
}