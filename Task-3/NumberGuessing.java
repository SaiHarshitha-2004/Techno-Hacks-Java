import java.util.*;

public class NumberGuessing {

    public static int numberSumHint(int number) {
        int sum = 0 , digit;

        while(number > 0)  
        {  
            digit = number % 10;  
            sum = sum + digit;  
            number = number / 10;  
        }  

        return sum ;
    }

    public static void main(String[] args) {
        Random rand = new Random()   ;
        Scanner sc = new Scanner(System.in) ;

        int numRamdom = rand.nextInt(1  , 101 ) ;
        boolean guess = true ; 
        boolean hintOne = true ;


        while(guess) {
            String hint1 = "" , hint2 = ""; 
            System.out.println();
            System.out.println("Guess number from (1 - 100 ) : ");
            int guessNo = sc.nextInt(); 
            if(guessNo == numRamdom) {
                System.out.println("You win");
                guess = false ; 
            }
            else{
                if(guessNo > numRamdom) {
                    System.out.println("Too high");
                }
                else{
                    System.out.println("Too low");
                }
                if(hintOne) {

                    System.out.println("Need hint1 (yes/no)");

                    hint1 = sc.next();
    
                    if(hint1.equals("yes")) {
                        int sum = numberSumHint(numRamdom) ;
                        hintOne = false ;
                        System.out.println("");
                        System.out.println("Hint ");

                        System.out.println("Sum of digits in number is : " + sum);
                    }

                }
                else {
                    
                    System.out.println("Need hint2 (yes/no)");

                    hint2 = sc.next();
    
                    if(hint2.equals("yes")) {
                        
                        if(numRamdom % 2 == 0) { 
                            System.out.println("");
                            System.out.println("Hint ");
                            System.out.println("It is an even number");
                        }
                        else{
                            System.out.println("It is an odd number");
                        }
                    }
                }

            }

               
        }


    }
}
