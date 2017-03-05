/*
  Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

class TTTClient {
    private static final char[][] board = {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};

    public static void main( String[] args)
    {

        System.out.println("CLIENT is attempting connection....");

        try {
            Socket toserversocket = new Socket("localhost", 7788);
            System.out.println("CONNECTION HAS BEEN MADE");


            BufferedReader in = new BufferedReader(new InputStreamReader(toserversocket.getInputStream()));
            PrintStream out = new PrintStream(toserversocket.getOutputStream());

            String response = in.readLine();
            if(!response.equals("NONE")) {
                String[] rA = response.split(" ");
                int rm = Integer.valueOf(rA[1]);
                int cm = Integer.valueOf(rA[2]);
                update(rm, cm, 'X');
            }
            out.println(move());
            while (true) {
                response = in.readLine();
                String[] rA = response.split(" ");
                int rm = Integer.valueOf(rA[1]);
                int cm = Integer.valueOf(rA[2]);
                if(rA.length > 3){ //if server sent action
                    String check = rA[3];
                    switch (check) {
                        case "LOSS":
                            update(rm, cm, 'X');
                            printBoard();
                            System.out.println("You lost. Sad!");
                            System.exit(0);
                        case "TIE":
                            if (board[rm][cm] == ' ') {
                                update(rm, cm, 'X');
                                printBoard();
                            }
                            System.out.println("You Tied. Sad!");
                            System.exit(0);
                        case "WIN":
                            System.out.println("You win!");
                            System.exit(0);
                    }
                }else{ //game didnt server turn
                    update(rm, cm, 'X');
                    out.println(move());
                }
            }
        }
        catch (IOException  e) {
            e.printStackTrace();
        }
    }

    private static void printBoard() {
        System.out.printf("\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n_________________\n  %c  |  %c  |  %c  \n\n",
                board[0][0],board[0][1],board[0][2],board[1][0],board[1][1],board[1][2],board[2][0],board[2][1],board[2][2]);
    }

    private static void update(int r, int c, char m){// local board update with chosen move
        //move is always row-column
        //assumes that board has been checked for open spot
        board[r][c] = m;
    }

    private static String move(){
        printBoard();
        Scanner sc = new Scanner(System.in);
        boolean loop = false;
        int r = 0;
        int c = 0;
        while(!loop){//input loop
            System.out.println("Row?");
            String rS = sc.next();
            System.out.println("Column");
            String cS = sc.next();
            System.out.println(cS);
            Pattern pR = Pattern.compile("[0-2]+"); //regex magic to check input
            if (!pR.matcher(rS).matches() || !pR.matcher(cS).matches()) {
                //if input wrong throw arg
                System.out.println("One of your inputs was incorrect.");
            } else { //change string to in
                r = Integer.valueOf(rS);
                c = Integer.valueOf(cS);
                if(board[r][c] != ' '){//if board sport taken throw arg
                    System.out.println("Spot not open on board.");
                }else{ //update board
                    update(r, c, 'O');
                    System.out.println("Board updated!");
                    loop = true;
                }
            }
        }

        printBoard();
        String a = Integer.toString(r);
        String b = Integer.toString(c);
        return "MOVE" + " " + a + " " + b;
    }
}

