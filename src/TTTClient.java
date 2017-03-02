/**
 * Created by Corey Shrader & Chris Groppe on 2/28/17.
 */

import java.io.*;
import java.net.*;
import java.util.*;


import    java.util.regex.*;




public class TTTClient {
    static char[][] board = {{' ', ' ', ' '},
                             {' ', ' ', ' '},
                             {' ', ' ', ' '}};
    static DataInputStream in;
    static DataOutputStream out;
    static Socket toserversocket;
    static int   reply;   // for later use
    static boolean play = true;

    public static void main( String[] args)
    {

        System.out.println("CLIENT is attempting connection....");

        try {
            toserversocket = new Socket("localhost", 7788);
            System.out.println("CONNECTION HAS BEEN MADE");


            in = new  DataInputStream( toserversocket.getInputStream());
            out = new DataOutputStream( toserversocket.getOutputStream());

            //PrintWriter out = new PrintWriter(outstream, true);
            //BufferedReader in = new BufferedReader(instream);
            String response = in.readUTF();
            if(response != "NONE") {
                String[] rA = response.split(" ");
                    int rm = Integer.valueOf(rA[1]);
                    int cm = Integer.valueOf(rA[2]);
                    update(rm, cm, 'X');
            }
            out.writeChars(move());
            while (play) {
                String[] rA = response.split(" ");
                if(rA.length > 3){ //if server sent action
                    String check = rA[3];
                    if(check.equals("LOSS")){
                        System.out.println("You lost. Sad!");
                        System.exit(0);
                    }else if(check.equals("TIE")) {
                        System.out.println("You Tied. Sad!");
                        System.exit(0);
                    }else if(check.equals("WIN")){
                        System.out.println("You win!");
                        System.exit(0);
                    }
                }else{//game didnt server turn
                    int rm = Integer.valueOf(rA[1]);
                    int cm = Integer.valueOf(rA[2]);
                    update(rm, cm, 'X');
                    out.writeChars(move());
                }
            }
        }
        catch (IOException  e) {};
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

    public static String move(){
        printBoard();
        Scanner sc = new Scanner(System.in);
        boolean loop = false;
        int r = 0;
        int c = 0;
        while(loop == false){//input loop
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
        String ret = "MOVE" + " " + a + " " + b;
        //return "MOVE ROW COLUMN
        return ret;
    }
}

