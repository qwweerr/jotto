
package jotto;
import java.util.Observable;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class JottoModel extends Observable 
{
	public static int NUM_LETTERS = 5;
	public static final String[] LEVELS = {
      "Easy", "Medium", "Hard", "Any Difficulty"};
    public static final int max = 10;
    private static int curWord = 0;
    private static Word secret;
    private static WordList wordlist;
    private static String lastGuess;
    private static int partial;
	private static int exact;
	private static ArrayList<String> guessed; 
	private static String usedLetters;
	private static ArrayList<Character> letter; 
	private static boolean win;
	private static boolean error;
	private static String message;
    

	private void initializeVars() {
    	curWord = 0;
		partial = 0;
		exact = 0;
    	guessed = new ArrayList<String>();
    	letter = new ArrayList<Character>();
    	usedLetters = "";
    	win = false;
    	message = null;
	}

     public JottoModel(Scanner s) {
        initializeVars();
        wordlist = new WordList(s);
        secret = wordlist.randomWord();
        setChanged();
    }

    public JottoModel(Scanner s, int d) {
    	initializeVars();
    	wordlist = new WordList(s);
        secret = wordlist.randomWord(d);
        setChanged();
    }

    public void cleanup(){
        usedLetters = "";
    }
    public int getPartial() {
    	return partial;
    }
    
    public int getExact() {
    	return exact;
    }

    public boolean lost() {
    	return error;
    }
    
    public String getMessage() {
    	return message;
    }
    
    public boolean winGame() {
    	return win;
    }
    
    public int getCurrentWord() {
        return curWord;
    }
    

    public String getSecret() { 
    	return secret.getWord();
    }
    
    public int getDifficulty() {
    	return secret.getDifficulty();
    }

    public String getEasyHint() {
    	String str;
    	Random rand = new Random();
    	int index = rand.nextInt(NUM_LETTERS); 
    	str = "one letter is: " + secret.getWord().charAt(index);
    	return str;
    }

     public String getHint() {
     	String str;
    	Random rand = new Random();
    	int index = rand.nextInt(NUM_LETTERS); 
    	str = "Letter " + index + " is: " + secret.getWord().charAt(index);
    	return str;
    }

    public void finish() {
    	curWord = 10;
        notifyObservers();
    	setChanged();
    	System.exit(0);
    }

    public String usedletters() {
    	return usedLetters;
    }

    public String getLastGuess() {
    	if (lastGuess != null) {
        	message = "Your Last Guess was: " + lastGuess;
    	} else {    		
    		message = "This is your first guess, please input something!";
    	}
    	return lastGuess;
    }

    public boolean check(String s) {
    	boolean gameOver = false;
    	message = null;
    	error = false;
    	String str = s.toUpperCase();
        if (s.length() != NUM_LETTERS) {
        	error = true;
        	message = "only 5-letters valid";
        } else if (curWord+1 >= max) {
        	gameOver = true;
        	error = false;
        } else if (str.equals(secret.getWord())) {
        	error = false;
        	gameOver = true;
        	win = true;
        } else if (wordlist.contains(str) == false) {
        	error = true;
        	message = "Word isn't in dictionary";
        } else if (guessed.contains(str) == true) {
        	message = "You already guessed " + str;
        	error = true;
        } else {
        	error = false;
        	lastGuess = str;
            //System.out.println(lastGuess);
        	curWord++;
        	partial = 0;
        	exact = 0;
        	
        	char[] aword = str.toCharArray();
        	char[] bword = secret.getWord().toCharArray();
            System.out.println(aword);
        	int i = 0;
        	while (i < aword.length) {
        		for(int j = 0; j < bword.length; ++j) {
        			if (aword[i]==bword[j]) {
        				if (i == j) {
        					exact++;
        				} else {
        					partial++;
        				}
        				bword[j] = '-';
        				break;
        			}
        		}
        		i++;
        	}
        	
        	for(int x = 0; x < aword.length; x++) {
        		if (!letter.contains(aword[x])) {
        			letter.add(aword[x]);
                    usedLetters += aword[x];
        		}
                
        	}
        	System.out.println(usedLetters);
        	guessed.add(str); 
            setChanged();
            notifyObservers();        	
        }
        return gameOver;
    }
}

