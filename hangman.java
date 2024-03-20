import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/* public class hangman { 
    public static void main(String args[]){
        
        
        System.out.println("Welcome...");
        File  myFile = new File("dictionary.txt");
        Extract_word wordd = new Extract_word(myFile);
        String word_to_guess = wordd.extract();
        System.out.println(word_to_guess);



    }       


} */

/* 
class to extract word from a dictionary randomly */

class Extract_word{
    
    private File dictionary_file ;
    String word ;
    
    /* constructor that take as parameter a dictionary file */
    Extract_word(File myDict){
        this.dictionary_file = myDict ; 
    }

    
    /* the method to extract the word to guess */
    public String extract(){
        try {
            
            FileReader toRead = new FileReader(this.dictionary_file);
            BufferedReader reader = new BufferedReader(toRead);
            ArrayList<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            Random random = new Random();
            int randomIndex = random.nextInt(lines.size());
            String randomLine = lines.get(randomIndex);

             // Afficher la ligne aléatoire
            String word_to_guess = randomLine;
            word = word_to_guess;

        } catch (Exception e) {
           System.err.println(e);
        }
        return word ;
    }
    
    
    
}