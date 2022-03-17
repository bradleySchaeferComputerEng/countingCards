package twentyone;
/*
To compile 
1) javac twentyone/*.java
2) java twentyone.Main
 tar -czvf wordle.tgz wordle  To zip
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;  
import java.io.File;  // Import the File clas
import java.lang.Object;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.HashMap;   //Hashmap
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Random; //random class
import java.time.*; //to use the tick

public class Main {

    static String returnSuit (int card) {   //returns suit corrosponding number
        if (card - 100 < 13) {
            return "Hearts";
        }
        else if (card - 200 < 13) {
            return "Dimonds";
        }
        else if (card - 300 < 13) {
            return "Spades";
        }
        else {
            return "Clubs";
        }
    }

    static int returnNumber (int card) {    //returns number corosponding to card
        if (card % 100 == 0) {
            return 2;
        }
        else if ((card-1) % 100 == 0) {
            return 3;
        }
        else if ((card-2) % 100 == 0) {
            return 4;
        }
        else if ((card-3) % 100 == 0) {
            return 5;
        }
        else if ((card-4) % 100 == 0) {
            return 6;
        }
        else if ((card-5) % 100 == 0) {
            return 7;
        }
        else if ((card-6) % 100 == 0) {
            return 8;
        }
        else if ((card-7) % 100 == 0) {
            return 9;
        }
        else if ((card-8) % 100 == 0) {
            return 10;
        }
        else if ((card-9) % 100 == 0) {
            return 11;  //jack
        }
        else if ((card-10) % 100 == 0) {
            return 12;  //queen
        }
        else if ((card-11) % 100 == 0) {
            return 13;  //king
        }
        else if ((card-12) % 100 == 0) {
            return 14;   //ace
        }
        return -1;
    }

    static int[] returnShuffledCards (int cardsArray []) {  //Shuffles Cards
        
        Random rGenerator = new Random(); // Create an instance of the random class 
        for (int i =0; i< cardsArray.length;i++ ) {
            //Swap the positions...

            int rPosition = rGenerator.nextInt(cardsArray.length); // Generates an integer within the range (Any number from 0 - arr.length)
            int temp = cardsArray[i]; // variable temp saves the value of the current array index;
            cardsArray[i] = cardsArray[rPosition];  // array at the current position (i) get the value of the random generated 
            cardsArray[rPosition] = temp; // the array at the position of random generated gets the value of temp

        }

        for(int i = 0; i<cardsArray.length; i++) {
            //System.out.println(i + ": " + cardsArray[i]); //Prints out the array
        } 
        return cardsArray;
    }

    static int returnCount (int numCard, int theCount, int shuffledCard) {
        
        numCard = returnNumber(shuffledCard);
        //System.out.println(numCard + " of " + returnSuit(shuffledCard));
        
        if (numCard >=2 && numCard <=6) {
            theCount++;
            //System.out.println(theCount);
        }
        else if (numCard >=10 && numCard <=15) {    //[10,J,Q,K,A]
            theCount--;
            //System.out.println(theCount);
        }
        else {  //[7,8,9]
            //System.out.println(theCount);
        }
        return theCount;
    }

    static int[] amountOfDecks (int cardsArray[], int numberOfDecks) {
        int [] newDeck = new int[cardsArray.length * numberOfDecks];

        int j = 0;
        for (int i = 0; i < cardsArray.length * numberOfDecks; i++) {
            newDeck[i] = cardsArray[j];
            j++;
            if (j == 52) {
                j = 0;
            }
        }
        return newDeck;
    }

    public static void wait(int ms) {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    static void timeDelayedMainMethod (int shuffledCards[], int theCount, int speed) {
        
        int iterationNum = 0;
        if (speed != 0){
            System.out.println("Time Delay Initiated. New card will show every " + speed + " seconds.");
        }
        for (int i = 0; i < shuffledCards.length; i++) {  //[2,3,4,5,6]
            
            wait(1000 * speed); //waits 3 seconds

            theCount = returnCount(shuffledCards[i], theCount, shuffledCards[i]);
            iterationNum = i + 1;
            System.out.println("Iteration #" + iterationNum + ":\t" + returnNumber(shuffledCards[i]) + " of " + returnSuit(shuffledCards[i]) + "\tThe Count is = " + theCount);
        }
    }

    public static void main (String[] args) {

        Scanner input= new Scanner(System.in); //System.in is a standard input stream.
        int i = 0;
        int theCount = 0;
        int speed = 0;  //inouts the time in seconds
        int numberOfDecks = 0;  //inputs the number of decks in the game
        int cardsArray[] = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, };

        //timeDelayedMainMethod (shuffledCards, theCount, speed);
        //mainMethod (shuffledCards, theCount);
        System.out.println("Welcome to counting cards basic stratigy game.");
        try {
            System.out.println("Enter the number of decks you want to play with. Recommeded number is 4.");
            numberOfDecks = input.nextInt();
            if (numberOfDecks > 10 || numberOfDecks < 1) {
                System.out.println("Please enter a number between 1-9");
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Please retry");
        }
        try {
            System.out.println("Please enter the amount of time you would like the cards to show for.");
            speed = input.nextInt();
            if (speed < 0) {
                System.out.println("Please enter a value >= 0");
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Please retry");
        }

        int [] newDeck = amountOfDecks(cardsArray, numberOfDecks);
        int shuffledCards[] = returnShuffledCards(newDeck);
        timeDelayedMainMethod (shuffledCards, theCount, speed);
        
    }
}