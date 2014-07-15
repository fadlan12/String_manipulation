import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author fadlan12
 *
 */
/* program that parses a 
sentence and replaces each word with the following: first letter, number of distinct characters between first 
and last character, and last letter.  For example, Smooth would become S3h.  Words are separated by spaces or 
non-alphabetic characters and these separators should be maintained in their original form and location in the answer.



*/



public class Parse {
	
	String s;
	
	
	// reads input  from console and creates string s
	public String getSentence()  {
				
		System.out.println("Enter sentence or phrase : ");
		 
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		     s = bufferRead.readLine();			    
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return s;
	 
	  }
	// splits the string into two arrays with reg expressions,
	//one splits into words and the other splits into non alphanumeric charactors
	
	public String parseSentence(String sentence){
		Scanner ch = new Scanner(sentence).useDelimiter("[^A-Za-z0-9]+");
		Scanner na = new Scanner(sentence).useDelimiter("[^\\W]+");
		StringBuilder builder = new StringBuilder();
		StringBuilder naBuilder = new StringBuilder();
		String [] wordArray = new String [30];
		String [] naArray = new String [30];
		
		int i = 0, f = 0;
		// splits word array into char arrays
		while(ch.hasNext()){
			wordArray[i] = ch.next().toString();
			i++;				
		}
		// places non alpha chars into naArray
		while(na.hasNext()){	
			Pattern pattern = Pattern.compile("[0-9a-zA-Z]");
			String next = na.next().toString();
			Matcher matcher = pattern.matcher( next);
			if (!matcher.matches()) {
				naArray[f] = next;	
			
			String nonAlpha = naArray[f];				
			f++;			
			naBuilder.append(nonAlpha);
			}
		}	
		String [] notAlpha = new String [f];		
		for (int n = 0; n < f; n++){
			
				notAlpha[n] = naArray[n];
			}
			
		System.out.println("naArray size: " + notAlpha.length);
		char [] splitWord;	
			
		String [] lettersArray = new String [i];		
		for (int n = 0; n<i;n++){
			lettersArray[n] = wordArray[n];
		}		
		int q = 0;
		// reads word letterarray into split word, and appends back into order with new counts
		// for letters inbetween first and last letter in word. 
		// checks for first character being a letter or not and prepending nonalpha to begining of new
		// string.
		for (int k =0; k< i; k++){
			splitWord =	lettersArray[k].toCharArray();
		// checks for first character being a letter or not and prepending nonalpha to begining of new
		// string.
			if (!Character.isDigit(sentence.charAt(0)) && !Character.isLetter(sentence.charAt(0))&& k == 0){
				builder.append(notAlpha[0]);
				q++;
			}	
			
		// checks for size of word  and appends to builder the new set of characters.
			if(splitWord.length > 2){				
				String condensedWord = splitWord[0] + String.valueOf(splitWord.length - 2)+  splitWord[splitWord.length-1] ;
				builder.append(condensedWord);										
			}				
			
			else if(splitWord.length == 2) {				
				String condensedWord = splitWord[0] + "" + splitWord[1];
				
				builder.append(condensedWord);
				
			}
			else if(splitWord.length == 1){				
				String condensedWord = splitWord[0]+ "" ;
				builder.append(condensedWord);
			}
			
			 if (q < f){
				builder.append(notAlpha[q]);
				q++;
			}
			
		
	}
		 System.out.println(builder);
		
		return sentence;
		
		
	}
}
