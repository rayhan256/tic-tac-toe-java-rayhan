import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Tictactoe {
    //mau ngedefine posisi player dan cpu
    static ArrayList<Integer> peoplePosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();
    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|',' '},
                            {'-', '+', '-', '+','-'},
                            {' ', '|', ' ', '|',' '},
                            {'-', '+', '-', '+','-'},
                            {' ', '|', ' ', '|',' '}};
        printGameBoard(gameBoard);

        while (true) {
            //mau masukin posisinya
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter The Point Only 1-9 : ");
            int pos = scan.nextInt();
            while (peoplePosition.contains(pos) || cpuPosition.contains(peoplePosition)) {
                System.out.println("Please take another position, position taken");
                pos = scan.nextInt();
            }
            //masukin kondisi agar x dan o masuk ke gameboard
            placePiece(gameBoard, pos, "player");
            String result = checkWinner();
            if (result.length() > 0 ) {
                System.out.println(result);
                break;
            }
            //mau buat karakter cpu dengan randomnya lah
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (peoplePosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                System.out.println("Please take another position, position taken");
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");
            result = checkWinner();
            if (result.length() > 0 ) {
                System.out.println(result);
                break;
            }   
            printGameBoard(gameBoard); 
            
        }
    }
    
    

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        //mau nentuin pilihan user dan komputer
        char karakter = ' '; 
        if (user.equals("player")) {
            karakter = 'X';
            peoplePosition.add(pos);
        } else if (user.equals("cpu")) {
            karakter = 'O';
            cpuPosition.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = karakter;
                break;
            case 2:
                gameBoard[0][2] = karakter;
                break;
            case 3:
                gameBoard[0][4] = karakter;
                break;
            case 4:
                gameBoard[2][0] = karakter;
                break;
            case 5:
                gameBoard[2][2] = karakter;
                break;
            case 6:
                gameBoard[2][4] = karakter;
                break;
            case 7:
                gameBoard[4][0] = karakter;
                break;
            case 8:
                gameBoard[4][2] = karakter;
                break;
            case 9:
                gameBoard[4][4] = karakter;
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);
        
        List<List> winning = new ArrayList<List>();

        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        //kalau karakter dapat 3 maka akan menang
        for (List l : winning) {
            if (peoplePosition.containsAll(l)) {
                return "Congratulation you win";
            } else if (cpuPosition.containsAll(l)) {
                return "Congratulation Cpu win";
            } else if (peoplePosition.size() + cpuPosition.size() == 9) {
                return "Cat / Seri";
            }
        }
        return " ";
    }
}   