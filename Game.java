import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class Game{
    //All the instances variable
    private char letter;
    private String secret_word;
    private HashMap<Character,Integer> guessed_letters;
    private int max_attempts;
    private StringBuilder current_progress;
    private int attempts_remaining;
    private boolean is_game_over;
    private int number_guessed_letters;
    


    //constructor to initiate game object
    public Game(String w, int att) {
        this.secret_word = w;
        this.max_attempts = att;
        this.attempts_remaining = att;
        this.current_progress=new StringBuilder();
        for (int i = 0; i < w.length(); i++) {
            if (i == 0 || i == w.length() - 1)
                this.current_progress.append(w.charAt(i));
            else
                this.current_progress.append('_');
        }
        this.guessed_letters = new HashMap<>();
        this.is_game_over = false;
        this.number_guessed_letters=2;
    }

    //display a banner for the game
    public void banner()
    {
        String banner =
        "  _   _                                          \n" +
        " | | | | __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" +
        " | |_| |/ _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" +
        " |  _  | (_| | | | | (_| | | | | | | (_| | | | |\n" +
        " |_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" +
        "                    |___/                       \n" +
        "                                                 \n" +
        " Welcome to Hangman Game!                        \n" +
        " Guess the secret word by guessing letters.      \n" +
        " You have limited attempts to guess the word.    \n";
    System.out.println(banner);
        
    }



//display the print out what user see
public void displayProgress()
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("attempts remaining: "+attempts_remaining);
        System.out.println("number of found letters: "+number_guessed_letters);
        System.out.println("guess a letter: "+current_progress);
        System.out.print(">");
        letter=sc.next().charAt(0);
        letter=Character.toUpperCase(letter);
    }


//this method verify if the letter is in the secret word and update the current_progress
public boolean isLetterInSecret(char letter)
{
boolean found=false;
if (!guessed_letters.containsKey(letter))guessed_letters.put(letter, 1);
else guessed_letters.put(letter, guessed_letters.get(letter)+1);
for (int i=1;i<secret_word.length()-1;i++)
{
    if (secret_word.charAt(i)==letter)
    {
        found=true;
        current_progress.setCharAt(i, letter);
        
    }
}

return found;
}  


//update the satuts of attemps remaining and number of found letters
public void update_status(char letter)
{
if(!isLetterInSecret(letter))
{

    if (guessed_letters.get(letter)==2)
    {
    System.out.println("!!!Warning: you have already guessed this letter");
    }
    else
    {
        attempts_remaining--;
        System.out.println("not found");
    }

}


else
{

    number_guessed_letters++;
}


}

//verify if the game is over or not
public boolean isGameOver()
{
if (attempts_remaining<1)
{
System.out.println("you lose,secret word: "+secret_word);
is_game_over=true;

}
return is_game_over;
}

//verify if the player win or not
public boolean  youWin()
{
if (attempts_remaining>=1 &&  number_guessed_letters==secret_word.length())
{

System.out.println("congratulation,you find the secret word: "+secret_word);
    
    return true;

}
return false;

}



//there is the logic of the game
public static void play(String word_to_guess)
{

    
    Game mygame= new Game(word_to_guess,6);
    mygame.banner();
    while(!mygame.isGameOver())
    {
    mygame.displayProgress();
    mygame.update_status(mygame.letter);
    if(mygame.youWin())break;

    }

}


static class Extract_word{
    
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


  
public static void main(String args[])
    {
        try {
            File  myFile = new File("dictionary.txt");
            Extract_word wordd = new Extract_word(myFile);
            String word_to_guess = wordd.extract();
            Game.play(word_to_guess);
        } catch (Exception e) {
            System.out.println(e);
        }
   
    
   
    }
    
}