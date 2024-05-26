import java.util.*;

public class VignereCipher {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the plain text: ");
        String str = sc.nextLine();

        System.out.println("Enter the key: ");
        String keyInput = sc.nextLine();

        String givenKey = keyInput.toUpperCase();
        String finalCipher = "";
        String strarr[] = str.split(" ");
        String finalText = "";
        String decryptText = "";

        for(String s: strarr){
            finalText += s.toUpperCase();
        }

        String key = "";

        for(int i = 0; i < finalText.length(); i++){
            key += givenKey.charAt(i % givenKey.length());
        }

        //encryption
        for(int i = 0; i < finalText.length(); i++){
            int cipher = ((finalText.charAt(i) + key.charAt(i) - 2 * 'A') % 26); //have to subtract two times since adding two elements together
            finalCipher += Character.toString(cipher + 'A');
        }


        //decryption
        for(int i = 0; i < finalText.length(); i++){
            int decryptValue = ((finalCipher.charAt(i) - key.charAt(i)) % 26); 
            while(decryptValue < 0){
                decryptValue += 26;
            }
            
            decryptText += Character.toString(decryptValue + 'A');
        }

        System.out.println();
        System.out.println("The encrypted message is: ");
        System.out.println(finalCipher);
        
        System.out.println();
        System.out.println("The decrypted message is: ");
        System.out.println(decryptText);

        sc.close();
    }
}


