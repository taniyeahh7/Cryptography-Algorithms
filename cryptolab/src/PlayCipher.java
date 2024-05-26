import java.util.*;

public class PlayCipher {
    //to remove any repetitive letters in input string
    public static String modifyInputString(String str){
        int n = str.length();
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        int count = 0;
        String ans = "";

        while(count < sb.length()){
            if(i % 2 == 0){
                i++;
                count++;
            }
            else{
                if(sb.charAt(i) == sb.charAt(i - 1)){
                    sb.insert(i, "X");
                }
                count++;
                i++;
            }
        }

        ans = sb.toString();
        return ans;
    }

    public static String encryptMessage(String str, char[][] matrix){
        int index = 0;
        String ans = "";

        while(index < str.length()){
            char a = str.charAt(index);
            char b = str.charAt(index + 1);
            int rowa = 0, cola = 0, rowb = 0, colb = 0;

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(a == matrix[i][j]){
                        rowa = i;
                        cola = j;
                    }
                    if(b == matrix[i][j]){
                        rowb = i;
                        colb = j;
                    }
                }
            }

            //moving down cyclically
            if(rowa == rowb){
                ans += matrix[rowa][(cola + 1) % 5];
                ans += matrix[rowa][(colb + 1) % 5];
            }

            //moving right cyclically
            else if(cola == colb){
                ans += matrix[(rowa + 1) % 5][cola];
                ans += matrix[(rowb + 1) % 5][cola];
            }

            //getting horizontal opposite
            else{
                ans += matrix[rowa][colb];
                ans += matrix[rowb][cola];
            }

            index += 2;
        }

        return ans;
    }
    
    public static String getDecryptMessage(String str, char[][] matrix){
        int index = 0;
        String ans = "";

        while(index < str.length()){
            char a = str.charAt(index);
            char b = str.charAt(index + 1);
            int rowa = 0, cola = 0, rowb = 0, colb = 0;

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(a == matrix[i][j]){
                        rowa = i;
                        cola = j;
                    }
                    if(b == matrix[i][j]){
                        rowb = i;
                        colb = j;
                    }
                }
            }

            if(rowa == rowb){
                cola -= 1;
                colb -= 1;
                while(cola < 0){
                    cola += 5;
                }

                while(colb < 0){
                    colb += 5;
                }
                
                ans += matrix[rowa][(cola) % 5]; 
                ans += matrix[rowa][(colb) % 5];
            }

            else if(cola == colb){ 
                rowa -= 1;
                rowb -= 1;
                while(rowa < 0){
                    rowa += 5;
                }

                while(rowb < 0){
                    rowb += 5; 
                }
                ans += matrix[(rowa) % 5][cola]; 
                ans += matrix[(rowb) % 5][cola];
            }

            else{
                ans += matrix[rowa][colb];
                ans += matrix[rowb][cola];
            }

            index += 2;
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text: ");
        String text = sc.nextLine().toUpperCase();
        System.out.println("Enter the key: ");
        String key = sc.nextLine().toUpperCase();
                                                      
        Set<Character> set = new LinkedHashSet<>();

        int row = 0, col = 0;

        for(int i = 0; i < key.length(); i++){
            set.add(key.charAt(i));
        }

        char charMatrix[][] = new char[5][5];
        int count = 0;
        

        if(set.contains('I') && set.contains('J')){
            set.remove('J');
        }

        List<Character> al = new ArrayList<>(set);

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(count < al.size()){
                    charMatrix[i][j] = al.get(count);
                    count++;
                }
                else{
                    break;
                }
            }
        }

        if(set.contains('J') && !set.contains('I')){ //if 'i' was already there would not have any j so by default rule it out by adding to set which will show
                                                         //that the set already contains this.
            set.add('I');
        }
        else if(set.contains('I') && !set.contains('J')){
            set.add('J');
        }
        

        
        char ch = 'A';
        row = count / 5; //trying to calculate the row on which the remaining chars will come
        col = count - row * 5;

        for(int j = col; j < 5; j++){
            while(set.contains(ch)){ //if the letter was already in key
                ch++;
            }
            
            charMatrix[row][j] = ch; //completing rest of the elements for that one incomplete row
            ch++;
        }

        for(int i = row + 1; i < 5; i++){
            for(int j = 0; j < 5; j++){
                while(set.contains(ch)){
                    ch++;
                }
                if(ch == 'J') ch++; //just dont give any room to j 
                charMatrix[i][j] = ch;
                ch++;
            }
        }

        System.out.println("\nThe matrix is: ");
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(charMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        text = modifyInputString(text);

        System.out.println("The encrypted message is: ");
        System.out.println(encryptMessage(text, charMatrix));

        String cipherText = encryptMessage(text, charMatrix);

        System.out.println("\nThe decrypted message is: ");
        System.out.println(getDecryptMessage(cipherText, charMatrix));
    }
}

