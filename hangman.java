import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class hangman {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String answer;
        System.out.println("Welcome...");
        System.out.println("Do you want to start a party ??");

        while (true) {
            System.out.println("click 'Y' for 'Yes' or 'N' for  'No' : ");
            answer = scan.nextLine();
            if (answer.equals("N") || answer.equals("Y")) {
                break;
            }
        }
        if(answer.equals("N") ){
            System.out.println("fin de jeu... !!!!!");
        }
        else{
            System.out.println("New Party...!!!! let's Gooo !!!!");
            
            try {
                File dictionary_file = new File("dictionary.txt");
                FileReader toRead = new FileReader(dictionary_file);
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
                String word_to_guest = randomLine;
                int wordSize = word_to_guest.length();

                /* ArrayList<Character> progressArrayList = new ArrayList<>();
                progressArrayList.add(word_to_guest.charAt(0)); */

                StringBuilder progressWord = new StringBuilder();
                progressWord.append(word_to_guest.charAt(0));
                for (int i = 1; i < wordSize-2; i++) {
                    progressWord.append('_');
                }
                progressWord.append(word_to_guest.charAt(wordSize-1));
                System.out.println(progressWord.length());
                
                int nb_attemps = 6;
                System.out.println("You have " + " " + nb_attemps + " " + "attemps");
                System.out.println("To guess this word of "+ " "+wordSize + " " + "charactere");
                System.out.println("Word  : " + progressWord);



                /* CHOOOOSE A LETTER NOW !!!!!! */
                /* J'essais de developper le tout d'abord, après je vais moduler ce qui modulable pour rendre le code maintenable */


            } catch (Exception e) {
               System.err.println(e);
            }
            

        }


        
      
       


        

    }
/*     static void filtring(String input){
        String regex = "[a-zA-Z_]+";
    
        Pattern pattern = Pattern.compile(regex);
    
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            System.out.println("Correspondance trouvée : " + matcher.group());
        }
    }
 */
       



}

/* class  StudentGroupManager{

    private Collection<Student> allStudent = new ArrayList<>();

    void addStudent(Student std){
        allStudent.add(std);
    }

    boolean removeStudent(Student std){
        if(allStudent.remove(std)){
            return true;
        }
        return false;
        
    }

    boolean containsStudent(Student std){
        return allStudent.contains(std);
    }

    void displayStudents(){

        Iterator<Student> iterator = allStudent.iterator();
        while (iterator.hasNext()) {
            
            Student std = iterator.next(); 
            System.out.println("The name :"+ " " + std.name);
            System.out.println("The surname :"+ " " + std.Surname);
            System.out.println("The age :"+ " " + std.age);
        
        }


    
    }




    
} */

/* 
class Student{
    int num;
    String name;
    String Surname;
    int age ; 

    Student(int num, String name, String Surname, int age ){
        this.num = num ;
        this.name = name ;
        this.Surname = Surname ;
        this.age = age ;
    }
    


} */