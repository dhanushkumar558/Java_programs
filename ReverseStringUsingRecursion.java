package JAVA_SYNTAX;
import java.util.Scanner;


public class ReverseStringUsingRecursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        String reversed = reverseString(input);
        System.out.println("Reversed string: " + reversed);
    }
    
    public static String reverseString(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return reverseString(str.substring(1)) + str.charAt(0);
    }
}
