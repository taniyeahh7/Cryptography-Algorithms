
import java.util.Scanner;
public class HillCipher {
    //a = d and -b = -c part
    public static void swap(int mat[][]){
        int temp1 = mat[0][0];
        mat[0][0] = mat[1][1];
        mat[1][1] = temp1;

        mat[0][1] = - mat[0][1];
        mat[1][0] = - mat[1][0];
    }

    public static int multiplicativeInverse(int[][] mat){
        int ans = 0;
        //getting determinant value
        int detValue = (mat[0][0] * mat[1][1]) - (mat[1][0] * mat[0][1]); 
        while(detValue < 0){
            detValue += 26;
        }

        detValue %= 26;
        
        //checking for any value which may be a multiplicative inverse
        for(int i = 0; i < 10001; i++){
            if((detValue * i) % 26 == 1){ 
                ans = i;
                break;
            }
        }

        return ans;
    }


    public static int[][] invertMatrix(int mat[][], int detValue){
        int ans[][] = new int[2][2];
        swap(mat); 

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                ans[i][j] = mat[i][j];
                //converting any negative value
                while(ans[i][j] < 0){
                    ans[i][j] += 26;
                }

                ans[i][j] *= detValue;
                ans[i][j] %= 26;
            }
        }

        return ans;
    }


    //getting encrypted and decrypted string basically for matrix multiplication and getting a string
    public static String getString(String str, int[][] keyMat){
        int getAlpha = 0;
        String ans = "";

        while(getAlpha < str.length()){
            int tempTextMat[][] = new int[2][1];
            tempTextMat[0][0] = (int)str.charAt(getAlpha) - 'A'; //converting every letter to its ascii 
            getAlpha++;
            tempTextMat[1][0] = (int)str.charAt(getAlpha) - 'A'; // dont forget to subtract A and put int in front
            getAlpha++;

            //matrix multiplication to get every word keep appending to ans
            int encryptAscii1 = ((tempTextMat[0][0] * keyMat[0][0]) + (tempTextMat[1][0] * keyMat[0][1])) % 26; // its plus
            ans += Character.toString(encryptAscii1 + 'A');

            int encryptAscii2 = ((tempTextMat[0][0] * keyMat[1][0]) + (tempTextMat[1][0] * keyMat[1][1])) % 26;
            ans += Character.toString(encryptAscii2 + 'A');
        }

        return ans;
    }


    public static String getDecryptedMessage(int[][] keyMat, String message){
        String ans = "";
        ans = getString(message, keyMat);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text: ");
        String str = sc.nextLine();

        if(str.length() % 2 != 0){
            str += 'x';
        }

        String strarr[] = str.split(" ");

        System.out.println("Enter the key: ");
        String keyInput = sc.nextLine();
        String givenKey = keyInput.toUpperCase();
        String finalText = "";

        int keyMat[][] = new int[2][2];

        for(String s: strarr){
            finalText += s.toUpperCase();
        }

        while(givenKey.length() != 4){
            givenKey += 'X';
        }

        //getting the key matrix
        int index = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                keyMat[i][j] = (int)givenKey.charAt(index) - 'A';
                index++;
            }
        }

        String encryptedString = getString(finalText, keyMat);

        System.out.println();
        System.out.println("The encrypted message is: ");
        System.out.println(encryptedString);

        int[][] invertedMatrix = invertMatrix(keyMat, multiplicativeInverse(keyMat));

        System.out.println("\nThe decrypted message is: ");
        System.out.println(getDecryptedMessage(invertedMatrix, encryptedString));

    }
}

