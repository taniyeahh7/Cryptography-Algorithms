
public class RSA {
    public static int gcd(int p, int q) {
        int a = p, b = q, c;

        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        int p = 3, q = 11;
        int phi = (p - 1) * (q - 1);
        int e = 1;

        for (int i = 2; i < 10001; i++) {
            if (gcd(i, phi) == 1) {
                e = i;
                break;
            }
        }

    
        int m = 7;
        int d = 0;

        while ((d * e) % phi != 1) {
            d++;
        }

        int c = (int)(Math.pow(m, e) % (p * q));
        int dec = (int)(Math.pow(c, d) % (p * q));

        System.out.println("Original message: " + m);
        System.out.println("Encrypted message: " + c);
        System.out.println("Decrypted message: " + dec);

    }
}

