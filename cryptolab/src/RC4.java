import java.util.*;

public class RC4{
    public static int n = 7;
    public static int t[] = new int[8];
    public static int S[] = new int[8];
    public static List<Integer> cipher = new ArrayList<>();
    public static List<Integer> ptback = new ArrayList<>();

    public static void swap(int i, int j){
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
    }

    public static void initialization(List<Integer> key, int n){
        for(int i = 0; i <= n ; i++){ // note : everything is less than equal to
            S[i] = i;
            t[i] = key.get(i % key.size());
        }
    }

    public static void intialPermutation(int n){
        int j = 0;
        for(int i = 0; i <= n; i++){
            j = (j + S[i] + t[i]) % (n + 1);
            swap(i, j);
        }
    }

    public static String streamGeneration(int n, List<Integer> pt, boolean encrypt){
        int j = 0;
        int temp = 0;
        int index = 0;
        StringBuilder key = new StringBuilder();

        for(int i = 1; i <= pt.size(); i++){
            j = (j + S[i]) % (n + 1);
            swap(i, j);
            temp = (S[i] + S[j]) % (n + 1);
            if(encrypt)
                cipher.add(S[temp] ^ pt.get(index++));
            else
                ptback.add(S[temp] ^ pt.get(index++));
        }

        return key.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ele =sc.nextInt();

        List<Integer> keyal = new ArrayList<>();
        List<Integer> ptal = new ArrayList<>();

        for(int i = 0; i < ele; i++){
            keyal.add(sc.nextInt());
        }

        for(int i = 0; i < ele; i++){
            ptal.add(sc.nextInt());
        }

        int n = 7;

        System.out.println("-------------------------------------");
        System.out.println("RC4 Algorithm implementation in Java");
        System.out.println("-------------------------------------");

        System.out.println("The key is: " + keyal);
        System.out.println("The pt is: " + ptal);

        initialization(keyal, n);
        intialPermutation(n);
        String finalKey = streamGeneration(n, ptal, true);

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("Encryption");
        System.out.println("-------------------------------------");
        System.out.println("The cipher text is: " + cipher);

        initialization(keyal, n);
        intialPermutation(n);
        streamGeneration(n, cipher, false);

        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("Decryption");
        System.out.println("-------------------------------------");
        System.out.println("The plain text is: " + ptback);
    }
}
