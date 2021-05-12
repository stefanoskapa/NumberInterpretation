package numberinterpretation;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NumberInterpretation {

    public static void main(String[] args) {
        
        System.out.print("Enter a sequence of numbers seperated by spaces -> ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        List<String> numbers = Util.parseNums(userInput);
        
       // Random random = new Random();
        //List<String> numbers = new LinkedList<>(); //generate 10 random numbers from 0 to 999
        //for (int i = 0; i<5; i++) {
        //    numbers.add(i,(random.nextInt(999) + 1)+"");
       // }
        
        
        System.out.println("Original sequence: " + numbers);
        System.out.println();
        
       
        Util.findInterpretations((LinkedList)numbers);
        Util.showInterpretations();
        
    
    }

}
