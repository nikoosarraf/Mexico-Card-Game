public class Mexico {
  /*Nikoo Sarraf
   *260767310*/
 
  //main method, calls playMexico
  public static void main(String[] args){
    double buyin = Double.parseDouble(args[0]);
    double bet = Double.parseDouble(args[1]);
    playMexico(buyin, bet);
  }
  
  
  //code to roll a dice, returns d, number between 1 and 6
  public static int diceRoll() {
    int d = (int) (1 + Math.random() *6);
    return d;
  }
  
  
  /*multiplies the higher digit out of 2 inputs by 10, and adds the lower one to 
   * create the double digit score, returns score which stores the value of the
   players score*/
  public static int getScore(int d1, int d2) {
    if (d1 >= d2) {
      int score = d1*10 +d2;
      return score;
    }
    else {
      int score = d2*10 + d1;
      return score;
    }
  }


  /*calls on diceRoll to get two random numbers, then calls on getScore to turn those 2 values
   * into a score, then 2 print lines which state what numbers the player rolled and what the
   players score is. Returns an int of the score*/
  public static int playOneRound(String name) {
    int d1 = diceRoll();
    int d2 = diceRoll();
    int score = getScore(d1, d2);
    System.out.println(name + " rolled: " + d1 + " " + d2);
    System.out.println(name + "'s score is: " + score);
    return score;
  }
  
  /*series of conditional statements to compare David and Giulia's scores and figure out which
   * one wins, including the special rules about doubles and 21, this method returns a string 
   of the winners name*/
  public static String getWinner(int score1, int score2) {
    String winner = "";
    if (score1 == score2) {
      winner = "Tie";
    }
    else if (score1 == 21 && score2 != 21) {
      winner = "Giulia";
    }
    else if (score2 == 21 && score1 != 21) {
      winner = "David";
    }
    else if (score1%11 == 0 && score1 < score2) {
      winner = "Giulia";
    }
    else if (score2%11 == 0 && score2 < score1) {
      winner = "David";
    }
    else if (score1%11 == 0 && score2%11 == 0) {
      if (score1 > score2) {
        winner = "Giulia";
      }
      else if (score2 > score1) {
        winner = "David";
      }
    }
    else if (score1 > score2) {
      winner = "Giulia";
    }
    else if (score2 > score1) {
      winner = "David";
    }
    return winner;
  }
    
  /*a boolean method to determine whether or not the players have enough money to play
   * another round of the game, returns true or false*/
  public static boolean canPlay(double buyin, double bet) {
    if (buyin > 0 && bet <= buyin && buyin%bet==0) {
      return true;
    } else {
      return false;
    }
  }

  /*method to simulate the game which is called in the main method,
   * creates 2 doubles (giuliasHand and davidsHand) to keep track of the scores
   also uses an int called round to keep track of the number of rounds*/
  public static void playMexico(double buyin, double bet) {
    int round = 1;
    double giuliasHand = buyin;
    double davidsHand = buyin;
    if (canPlay(buyin, bet) == false) {
      System.out.println("Insufficient funds. The game cannot be played!");
    } else {
      //while loop which will keep playing more rounds as long as each player has enough money
      while (giuliasHand > 0 && davidsHand > 0) {
        System.out.println("\nRound " + round + "\n");
        String winner = getWinner((playOneRound("Giulia")), (playOneRound("David")));
        if (winner.equals("Giulia")) {
          //next line subtracts the amount of the bet from the loser of the round
          davidsHand -= bet;
          System.out.println("Giulia wins this round.");
        }
        if (winner.equals("David")) {
          giuliasHand -= bet;
          System.out.println("David wins this round.");
        }
        if (winner.equals("Tie")) {
          System.out.println("It's a tie! Roll again!");
        }
        //round++ adds one round number each time
        round++;
      }
    //if statements to figure out which player won
    if (giuliasHand == 0) {
      System.out.println("\nThe game is over. David wins!"); 
    }
    if (davidsHand == 0) {
      System.out.println("\nThe game is over. Giulia wins!");
    }  
   }
 }
}

