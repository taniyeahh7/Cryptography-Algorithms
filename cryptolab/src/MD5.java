import java.util.*;
import java.math.BigInteger;


public class MD5{
    public static String circularLeftShift(String str, int k) {
        String left = str.substring(0, k);
        String right = str.substring(k);
        return right + left;
    }


    public static String[] funcF(String keys[], String messages[], String ivs[], int shiftRound1[]) {
        BigInteger a = new BigInteger(ivs[0], 2);
        BigInteger b = new BigInteger(ivs[1], 2);
        BigInteger c = new BigInteger(ivs[2], 2);
        BigInteger d = new BigInteger(ivs[3], 2);
        BigInteger message = new BigInteger(messages[0], 2);
        BigInteger key = new BigInteger(keys[0], 2);
        String[] finalans = new String[4];
    
        BigInteger funcVal = (b.and(c)).or((b.not()).and(d));
        funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 

        String tobeRotated = funcVal.toString(2);
        while (tobeRotated.length() < 32) {
            tobeRotated = "0" + tobeRotated;
        }

        funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound1[0]), 2);
        
        funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
        String ans = funcVal.toString(2);
        while (ans.length() < 32) {
            ans = "0" + ans;
        }

        for(int i = 1; i <= 15; i++){
            BigInteger tempa = a;
            BigInteger tempb = b;
            BigInteger tempc = c;
            BigInteger tempd = d;

            a = tempd;
            b = new BigInteger(ans, 2);
            c = tempb;
            d = tempc;
            message = new BigInteger(messages[i], 2);
            key = new BigInteger(keys[i], 2);
        
            funcVal = (b.and(c)).or((b.not()).and(d));
            funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32));       
            funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
            funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 
            
    
            tobeRotated = funcVal.toString(2);
            while (tobeRotated.length() < 32) {
                tobeRotated = "0" + tobeRotated;
            }
            funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound1[i]), 2);
            
            funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
            ans = funcVal.toString(2);
            while (ans.length() < 32) {
                ans = "0" + ans;
            }
        }

        finalans[0] = d.toString(2);
        finalans[1] = ans;
        finalans[2] = b.toString(2);
        finalans[3] = c.toString(2);

    
        return finalans; 
    }

    public static String[] funcG(String keys[], String messages[], String ivs[], int shiftRound2[]) {
        BigInteger a = new BigInteger(ivs[0], 2);
        BigInteger b = new BigInteger(ivs[1], 2);
        BigInteger c = new BigInteger(ivs[2], 2);
        BigInteger d = new BigInteger(ivs[3], 2);
        BigInteger message = new BigInteger(messages[0], 2);
        BigInteger key = new BigInteger(keys[16], 2);
        String[] finalans = new String[4];
        int index = 17;
    
        BigInteger funcVal = (b.and(c)).or((b.not()).and(d));
        funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 

        String tobeRotated = funcVal.toString(2);
        while (tobeRotated.length() < 32) {
            tobeRotated = "0" + tobeRotated;
        }

        funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound2[0]), 2);
        
        funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
        String ans = funcVal.toString(2);
        while (ans.length() < 32) {
            ans = "0" + ans;
        }

        for(int i = 1; i <= 15; i++){
            BigInteger tempa = a;
            BigInteger tempb = b;
            BigInteger tempc = c;
            BigInteger tempd = d;

            a = tempd;
            b = new BigInteger(ans, 2);
            c = tempb;
            d = tempc;
            message = new BigInteger(messages[i], 2);
            key = new BigInteger(keys[index++], 2);
        
            funcVal = (b.and(d)).or((c).and(d.not()));
            funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32));       
            funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
            funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 
            
    
            tobeRotated = funcVal.toString(2);
            while (tobeRotated.length() < 32) {
                tobeRotated = "0" + tobeRotated;
            }
            funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound2[i]), 2);
            
            funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
            ans = funcVal.toString(2);
            while (ans.length() < 32) {
                ans = "0" + ans;
            }
        }

        finalans[0] = d.toString(2);
        finalans[1] = ans;
        finalans[2] = b.toString(2);
        finalans[3] = c.toString(2);

    
        return finalans; 
    }

    public static String[] funcH(String keys[], String messages[], String ivs[], int shiftRound3[]) {
        BigInteger a = new BigInteger(ivs[0], 2);
        BigInteger b = new BigInteger(ivs[1], 2);
        BigInteger c = new BigInteger(ivs[2], 2);
        BigInteger d = new BigInteger(ivs[3], 2);
        BigInteger message = new BigInteger(messages[0], 2);
        BigInteger key = new BigInteger(keys[32], 2);
        String[] finalans = new String[4];
        int index = 33;
    
        BigInteger funcVal = (b.xor(c)).xor(d);
        funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 

        String tobeRotated = funcVal.toString(2);
        while (tobeRotated.length() < 32) {
            tobeRotated = "0" + tobeRotated;
        }

        funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound3[0]), 2);
        
        funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
        String ans = funcVal.toString(2);
        while (ans.length() < 32) {
            ans = "0" + ans;
        }

        for(int i = 1; i <= 15; i++){
            BigInteger tempa = a;
            BigInteger tempb = b;
            BigInteger tempc = c;
            BigInteger tempd = d;

            a = tempd;
            b = new BigInteger(ans, 2);
            c = tempb;
            d = tempc;
            message = new BigInteger(messages[i], 2);
            key = new BigInteger(keys[index++], 2);
        
            funcVal = (b.and(d)).or((c).and(d.not()));
            funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32));       
            funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
            funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 
            
    
            tobeRotated = funcVal.toString(2);
            while (tobeRotated.length() < 32) {
                tobeRotated = "0" + tobeRotated;
            }
            funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound3[i]), 2);
            
            funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
            ans = funcVal.toString(2);
            while (ans.length() < 32) {
                ans = "0" + ans;
            }
        }

        finalans[0] = d.toString(2);
        finalans[1] = ans;
        finalans[2] = b.toString(2);
        finalans[3] = c.toString(2);

    
        return finalans; 
    }


    public static String[] funcI(String keys[], String messages[], String ivs[], int shiftRound4[]) {
        BigInteger a = new BigInteger(ivs[0], 2);
        BigInteger b = new BigInteger(ivs[1], 2);
        BigInteger c = new BigInteger(ivs[2], 2);
        BigInteger d = new BigInteger(ivs[3], 2);
        BigInteger message = new BigInteger(messages[0], 2);
        BigInteger key = new BigInteger(keys[48], 2);
        String[] finalans = new String[4];
        int index = 49;
    
        BigInteger funcVal = (c).xor(b.or(d.not()));
        funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
        funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 

        String tobeRotated = funcVal.toString(2);
        while (tobeRotated.length() < 32) {
            tobeRotated = "0" + tobeRotated;
        }

        funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound4[0]), 2);
        
        funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
        String ans = funcVal.toString(2);
        while (ans.length() < 32) {
            ans = "0" + ans;
        }

        for(int i = 1; i <= 15; i++){
            BigInteger tempa = a;
            BigInteger tempb = b;
            BigInteger tempc = c;
            BigInteger tempd = d;

            a = tempd;
            b = new BigInteger(ans, 2);
            c = tempb;
            d = tempc;
            message = new BigInteger(messages[i], 2);
            key = new BigInteger(keys[index++], 2);
        
            funcVal = (b.and(d)).or((c).and(d.not()));
            funcVal = funcVal.add(a).mod(BigInteger.valueOf(2).pow(32));       
            funcVal = funcVal.add(message).mod(BigInteger.valueOf(2).pow(32)); 
            funcVal = funcVal.add(key).mod(BigInteger.valueOf(2).pow(32)); 
            
    
            tobeRotated = funcVal.toString(2);
            while (tobeRotated.length() < 32) {
                tobeRotated = "0" + tobeRotated;
            }
            funcVal = new BigInteger(circularLeftShift(tobeRotated, shiftRound4[i]), 2);
            
            funcVal = funcVal.add(b).mod(BigInteger.valueOf(2).pow(32)); 
            ans = funcVal.toString(2);
            while (ans.length() < 32) {
                ans = "0" + ans;
            }
        }

        finalans[0] = d.toString(2);
        finalans[1] = ans;
        finalans[2] = b.toString(2);
        finalans[3] = c.toString(2);

    
        return finalans; 
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int shiftRound1[] = {7, 12, 17, 22,7, 12, 17, 22,7, 12, 17, 22,7, 12, 17, 22};
        int shiftRound2[] = {5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20};
        int shiftRound3[] = {4, 11, 16, 13,4, 11, 16, 13,4, 11, 16, 13,4, 11, 16, 13};
        int shiftRound4[] = {6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21};

        String[] keys = {
            "11010111011010101010010001111000",
            "11101000110011110111010101010110",
            "00100100001000000111000011011011",
            "11000001101111011100111011101110",
            "1111010101111100000011111010",
            "01000111000001111100011000101010",
            "10101000001100000100011000010011",
            "11111101010011010100110100000001",
            "01101001100000001001100011011000",
            "10001011010001001111011110101111",
            "11111111111111110101101110110001",
            "10001001010111001101011110111110",
            "01101011100100000001000100100010",
            "11111101100110001001011110010011",
            "101001101111100100001001001110",
            "01001001101101000000100000100001",
            "11110110000111100010010101100010",
            "11000000010000001011001101000000",
            "00100110010111100101101001010001",
            "11101001101101101100011110101010",
            "11010110001011110000100001011101",
            "00000010010001000001010001010011",
            "11011000101000011110011010000001",
            "11100111110100111111101111001000",
            "00100001111000011100110111100110",
            "11000011001101110000011111010110",
            "11110100110101010000110110000111",
            "01000101010110100001010011101101",
            "10101001111000111110100100000101",
            "11111100111011111010001111111000",
            "11001111011011110000001011011001",
            "10001101001010100100110010001010",
            "11111111111110100011000100100010",
            "10000111011100011111011010000001",
            "01101001100111010110000100100010",
            "11111101110001010111000000001100",
            "10100100101111101110101001000100",
            "01001011110111101100111110101001",
            "11110110101110110100101101100000",
            "10111110101111111011110001110000",
            "00101000100110110111111011000110",
            "11101010100000010010011111111010",
            "11010100111011110011000010000101",
            "00000100100010000001110100000101",
            "11011001110101001101000000111001",
            "11100110110110111001100111100101",
            "00011111101000100111110011111000",
            "11000100101011000101011001100101",
            "11110100001010010010001001000100",
            "01000011010101011111111110010111",
            "10101011100101000010001110100111",
            "11111100100100111010000000111001",
            "01100101010110110101100111000011",
            "10001111000011001100110010010010",
            "11111111111011111111010001111101",
            "10000101000001000101110111010001",
            "01101111101010000111111001001111",
            "11111110001011001110011011100000",
            "101000110000000101000011010100",
            "01001110000010000001000110100001",
            "11110111010100110111111010000010",
            "10111101001110101111001000110101",
            "00101010110101111101001010111011",
            "11101011100001101101001110010001"
        };

        for(String i : keys){
            while(i.length() < 32){
                i = "0" + i;
            }
        }

        String[] messages = {
            "01010100011010000110010101111001",
            "00100000011000010111001001100101",
            "00100000011001000110010101110100",
            "01100101011100100110110101101001",
            "01101110011010010111001101110100",
            "01101001011000111000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000000000000",
            "00000000000000000000000010110000"
        };


        for(String i : messages){
            while(i.length() < 32){
                i = "0" + i;
            }
        }

        String[] ivs = {
            "0001001000110100010101100111",
            "10001001101010111100110111101111",
            "11111110110111001011101010011000",
            "01110110010101000011001000010000"
        };


        String ansF[] = funcF(keys, messages, ivs, shiftRound1);
        String ansG[] = funcG(keys, messages, ansF, shiftRound2);
        String ansH[] = funcH(keys, messages, ansG, shiftRound3);
        String ansI[] = funcI(keys, messages, ansH, shiftRound4);

        System.out.println("Hashing result: ");
        for(String s : ansI){
            BigInteger ans = new BigInteger(s, 2);
            System.out.print(ans.toString(2));
            
        }

        System.out.println("\nor in HexaDecimal:");

        for(String s : ansI){
            BigInteger ans = new BigInteger(s, 2);
            System.out.print(ans.toString(16));
        }
    }
}