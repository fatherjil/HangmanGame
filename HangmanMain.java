import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * Jilian Chow
 * Hangman Game
 */
public class HangmanMain {

  public static void main(String[] args) throws FileNotFoundException {

    // Import Scanner class for user input
    Scanner keyboard = new Scanner(System.in);
    // Declare a variable to store the dictionary word
    String dictionaryWord;

    // Read dictionary text file
    if (true) { // Condition is always true, so block will always execute
      // Create a Scanner to read from text file
      Scanner scanner = new Scanner(new File("5000words.txt"));

      // Create a list called "listOfWords" to store the words from the text file
      List<String> listOfWords = new ArrayList<>();
      // While loop reads each line from the file and adds it to the list just created ^
      while (scanner.hasNext()) {
        listOfWords.add(scanner.nextLine());
      }

      // Create a Random object for generating a random index
      Random rand = new Random();
      // Get a random word from the list of words and store it into variable
      dictionaryWord = listOfWords.get(rand.nextInt(listOfWords.size()));

      // Uncomment line below for debugging purposes
      // System.out.println(dictionaryWord);

      // Create a list to store the characters guessed by the player
      List<Character> timesGuessedCounter = new ArrayList<>();
      // Tracks how many incorrect guesses are made
      int incorrectGuessCounter = 0;
      // While loop (always true/executes)
      while (true) {
        // Displays body part based on # of incorrect guesses
        printBody(incorrectGuessCounter);
        // If over 7 attempts then lost
        if (incorrectGuessCounter >= 7) {
          System.out.println("-----------------------");
          System.out.println("You lose!");
          System.out.println("The word was: " + dictionaryWord);
          System.out.println("-----------------------");
          break;
        }
        // Call printWordState
        printWordState(dictionaryWord, timesGuessedCounter);
        // (timesGuessedCounter = list to store the characters guessed by the player )
        // Checks if the user guessed a letter CORRECTLY using getGuess method
        if (getGuess(keyboard, dictionaryWord, timesGuessedCounter)) {
          // Checks if the word has been completely guessed using printWordState method
          if (printWordState(dictionaryWord, timesGuessedCounter)) {
            // display you win
            System.out.println("-----------------------");
            System.out.println("You win!");
            System.out.println("Congratulations!");
            System.out.println("-----------------------");
            break; // ends the game
          } else { // if the word has not been completely guessed:
            // display prompt for guessing the entire word after guessing a letter correctly
            System.out.println("Try to guess the word!:");
            // ^ check if the user's input for guessing entire word is correct
            if (keyboard.nextLine().equals(dictionaryWord)) {
              // display you win if correct
              System.out.println("-----------------------");
              System.out.println("You win!");
              System.out.println("Congratulations!");
              System.out.println("-----------------------");
              break; // ends the game
              // user's guess is wrong:
            } else {
              // display try again
              System.out.println("Try again!");
            } // end else
          } // end else
          // if the user guessed the letter incorrectly:
        } else {
          // increment incorrect guess counter
          incorrectGuessCounter++;
        } // end else
      } // end while

    } // end if
  }


  // print body parts of the hangman
  private static void printBody(int firstCount) {
    if (firstCount == 0) {
      System.out.println(" "
          + "=====\r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r");
    }
    if (firstCount == 1) {
      System.out.println(""
          + "=====\r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n");
    } else {
      System.out.println("");
    }

    if (firstCount == 2) {
      System.out.println(""
          + "=====\r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n"
          + "    |\r\n");
    } else {
      System.out.println("");
    }
    if (firstCount == 3) {
      System.out.println(""
          + "=====\r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n"
          + "   /|\r\n");

    } else {
      System.out.println("");
    }

    if (firstCount == 4) {
      System.out.println(" "
          + "=====\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n"
          + "   /|\\\r\n");
    } else {
      System.out.println("");
    }

    if (firstCount == 5) {
      System.out.println(""
          + "=====\r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n"
          + "   /|\\\r\n"
          + "    |\r\n");
    } else {
      System.out.println("");
    }
    if (firstCount == 6) {
      System.out.println(""
          + "===== \r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n"
          + "   /|\\\r\n"
          + "    |\r\n"
          + "   / ");
    } else {
      System.out.println("");
    }
    if (firstCount == 7) {
      System.out.println(""
          + "=====\r\n "
          + "   |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    |\r\n"
          + "    O\r\n"
          + "   /|\\\r\n"
          + "    |\r\n"
          + "   / \\");
    }

    System.out.println(""); // print line
    System.out.println(""); // print line
  }

  // function to ask user to enter a letter and keep the guessed letter
  private static boolean getGuess(Scanner keyboard, String word, List<Character> guessedLetters) {
    // display all guessed letters || convert the "guessedLetters" list to a string
    System.out.println("Your guesses so far: " + guessedLetters.toString());
    // asks the user to enter a letter
    System.out.println("Enter one letter:");
    // gets user input as string
    String guessLetter = keyboard.nextLine();
    // get the first character user types in, incase they type in more than one character.
    char letter = guessLetter.charAt(0);
    // add the guessed letter to the "guessedLetters" list
    guessedLetters.add(letter);
    // check if the word to be guessed ("word") contains the guessed letter
    return word.contains(String.valueOf(letter));
  }


  // use character class again with boolean (T/F)
  private static boolean printWordState(String dictionaryWord,
      List<Character> timesGuessedCounter) {
    // tracks how many correct guesses are made
    int correctGuessCounter = 0;
    // iterate/loop through the dictionary word chosen randomly
    for (int index = 0; index < dictionaryWord.length(); index++) {
      // check if the user's guessed letter is in the dictionary word...
      if (timesGuessedCounter.contains(dictionaryWord.charAt(index))) {
        // if the user gets the correct guesses the correct letter at that index, print it
        System.out.print(dictionaryWord.charAt(index));
        // increment the correct guesses count
        correctGuessCounter++;
        // if character is not guessed/correct
      } else {
        // if it does not contain the letter then print out - for unguessed character
        System.out.print("-"); // prints out ----- for number of letters.
      } // end else
    } // end for loop
    System.out.println(); // go to next line after printing each "-"

    // check if the number of correct guesses equals the length of the word. (boolean (T/F))
    return (dictionaryWord.length() == correctGuessCounter);
  }


}


