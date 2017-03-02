/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;
public class TTTServer {

    static char[][] board = {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    static ServerSocket server; // this is the "door"
    static DataInputStream  in;
    static DataOutputStream out;
    static Socket toclientsocket;

    public static void main(String[] args) {



        try {    // NOTE - must be within a try-clause or throw exceptions!!!!

            printBoard();

            server = new ServerSocket(7788);   //listen at the door
            while(true) {
                System.out.println("waiting for connection");
                toclientsocket = server.accept();   // block UNTIL request received

                //AT THIS POINT CONNECTION MADE
                System.out.println("RECEIVED REQUEST");

                in = new DataInputStream(toclientsocket.getInputStream());
                out = new DataOutputStream(toclientsocket.getOutputStream());
                int r = ThreadLocalRandom.current().nextInt(1, 3); //gen number between 1 and 2
                double D = in.readDouble();  // read a double from client
                D = D * D;
                out.writeDouble(D);        // write the square to the client
            }
        }   // end try
        catch (IOException e) {}
    }

    public static void printBoard() {
        System.out.printf("\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n\n",
                board[0][0],board[0][1],board[0][2],board[1][0],board[1][1],board[1][2],board[2][0],board[2][1],board[2][2]);
    }

    public static void localUD(int r, int c, char m){// local board update with chosen move
        //move is always row-column
        //assumes that board has been checked for open spot
        board[r][c] = m;
    }
}
