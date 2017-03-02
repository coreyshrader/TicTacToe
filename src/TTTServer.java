/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class TTTServer {

    static private char[][] board = {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    static private ServerSocket server; // this is the "door"
    static private DataInputStream  in;
    static private DataOutputStream out;
    static private Socket toclientsocket;

    public static void main(String[] args) {

        try {    // NOTE - must be within a try-clause or throw exceptions!!!!

            printBoard();

            server = new ServerSocket(7788);   //listen at the door

            while (true) {

                System.out.println("waiting for connection");
                toclientsocket = server.accept();   // block UNTIL request received

                //AT THIS POINT CONNECTION MADE
                System.out.println("RECEIVED REQUEST");

                in = new DataInputStream(toclientsocket.getInputStream());
                out = new DataOutputStream(toclientsocket.getOutputStream());

                int r = ThreadLocalRandom.current().nextInt(1, 3); //gen number between 1 and 2

                if (r == 1) { // client goes
                    out.writeChars("NONE");
                }
                 else {
                    int[] move = move();
                    out.writeChars("MOVE" + move[0] + " " + move[1]);
                }

                double D = in.readDouble();  // read a double from client
                D = D * D;
                out.writeDouble(D);        // write the square to the client

            }
        }   // end try
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] move() {
        ArrayList<Integer[]> legalMoves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    Integer[] coords = {i, j};
                    legalMoves.add(coords);
                }
            }
        }
        int r = ThreadLocalRandom.current().nextInt(0, legalMoves.size()); //gen number between 0 and array size

        int[] move = {legalMoves.get(r)[0], legalMoves.get(r)[1]};

        update(move[0], move[1], 'X');

        return move;
    }

    public static void printBoard() {
        System.out.printf("\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n\n",
                board[0][0],board[0][1],board[0][2],board[1][0],board[1][1],board[1][2],board[2][0],board[2][1],board[2][2]);
    }

    public static void update(int r, int c, char m){// local board update with chosen move
        //move is always row-column
        //assumes that board has been checked for open spot
        board[r][c] = m;
    }
}
