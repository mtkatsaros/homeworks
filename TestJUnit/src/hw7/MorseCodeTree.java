package hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode){
    	String[] temp = morseCode.split(" ");
    	String value = "";
    	BinaryTree<String> translateTree = new BinaryTree<>();
    	
    	//add the translation file
    	try {
    		File translateFile = new File("translation.txt");
    		Scanner fileScanner = new Scanner(translateFile);
    		BinaryTree.readBinaryTree(fileScanner);
    	}catch(FileNotFoundException e) {
    		e.getMessage();
    	}
    	
    	
    
    	for(String s: temp) {	
    		switch(s) {
    			case "*-":
    				value += 'a';
    				break;
    			case "-***":
    				value += 'b';
    				break;
    			case "-*-*":
    				value += 'c';
    				break;
    			case "-**":
    				value += 'd';
    				break;
    			case "*":
    				value += 'e';
    				break;
    			case "**-*":
    				value += 'f';
    				break;
    			case "--*":
    				value += 'g';
    				break;
    			case "****":
    				value += 'h';
    				break;
    			case "**":
    				value += 'i';
    				break;
    			case "*---":
    				value += 'j';
    				break;
    			case "-*-":
    				value += 'k';
    				break;
    			case "*-**":
    				value += 'l';
    				break;
    			case "--":
    				value += 'm';
    				break;
    			case "-*":
    				value += 'n';
    				break;
    			case "---":
    				value += 'o';
    				break;
    			case "*--*":
    				value += 'p';
    				break;
    			case "--*-":
    				value += 'q';
    				break;
    			case "*-*":
    				value += 'r';
    				break;
    			case "***":
    				value += 's';
    				break;
    			case "-":
    				value += 't';
    				break;
    			case "**-":
    				value += 'u';
    				break;
    			case "***-":
    				value += 'v';
    				break;
    			case "*--":
    				value += 'w';
    				break;
    			case "-**-":
    				value += 'x';
    				break;
    			case "-*--":
    				value += 'y';
    				break;
    			case "--**":
    				value += 'z';
    				break;
    			default:
    				throw new InputMismatchException("Invalid input");
    		}
    		
    	}
        return value;
    }
    

} // End of class MorseCodeTree