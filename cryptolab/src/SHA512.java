public class SHA512 {

    // Constants
    private static final long[] K = {
        0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL,
        0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L,
        0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L,
        0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L,
        0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
        0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L,
        0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L,
        0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L,
        0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL,
        0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
        0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L,
        0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L,
        0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L,
        0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L,
        0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
        0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL,
        0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L,
        0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL,
        0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL,
        0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L
    };


    // Rotates a 64-bit integer to the right by n bits
    private static long ROTR(long x, int n) {
        return (x >>> n) | (x << (64 - n)); 
    }

    // Shifts a 64-bit integer to the right by n bits
    private static long SHR(long x, int n) {
        return x >>> n;
    }

    // Sigma0 and Sigma1 functions
    private static long sigma0(long x) {
        return ROTR(x, 28) ^ ROTR(x, 34) ^ ROTR(x, 39);
    }

    private static long sigma1(long x) {
        return ROTR(x, 14) ^ ROTR(x, 18) ^ ROTR(x, 41);
    }

    // Ch and Maj functions
    private static long Ch(long x, long y, long z) {
        return (x & y) ^ (~x & z);
    }

    private static long Maj(long x, long y, long z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    // SHA-512 message schedule

private static long[] expandMessage(String message) {
    byte[] bytes = message.getBytes();
    int numChunks = (bytes.length + 16) / 128; // careful
    long[] words = new long[numChunks * 16];

    for (int i = 0; i < numChunks * 16; i += 16) {
        long[] word = new long[16];

        for (int j = 0; j < 16; ++j) {
            int index = i + j * 8;
            if (index < bytes.length) {
                for (int k = 0; k < 8; ++k) {
                    if (index + k < bytes.length) {
                        word[j] |= ((long) bytes[index + k] & 0xFF) << (56 - k * 8); 
                    }
                }
            }
        }

        // Extend the sixteen 64-bit words into eighty 64-bit words
        for (int j = 16; j < 80; ++j) {
            long s0 = sigma0(word[j - 15]);
            long s1 = sigma1(word[j - 2]);
            word[j] = word[j - 16] + s0 + word[j - 7] + s1;
        }

        System.arraycopy(word, 0, words, i, 16); // Use i as the starting index
    }

    return words;
}


    // SHA-512 compression function
    public static String sha512(String message) {
        // Initialize hash values
        long[] H = { //this is the initialization vector and since it is the initialization vector it is the first line of code
            0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L,
            0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
        };

        // Pre-processing
        long[] words = expandMessage(message); //just before made this function
        int numChunks = words.length / 80; // that was  + 16 / 128 here just / 80

        // Compression
        for (int i = 0; i < numChunks; ++i) {
            long a = H[0], b = H[1], c = H[2], d = H[3], e = H[4], f = H[5], g = H[6], h = H[7];

            for (int j = 0; j < 80; ++j) {
                long S0 = ROTR(a, 28) ^ ROTR(a, 34) ^ ROTR(a, 39);
                long S1 = ROTR(e, 14) ^ ROTR(e, 18) ^ ROTR(e, 41);
                long ch = (e & f) ^ (~e & g);
                long temp1 = h + S1 + ch + K[j] + words[i * 80 + j];
                long temp2 = S0 + Maj(a, b, c);
                h = g;
                g = f;
                f = e;
                e = d + temp1;
                d = c;
                c = b;
                b = a;
                a = temp1 + temp2;
            }

            // Update hash values
            H[0] += a;
            H[1] += b;
            H[2] += c;
            H[3] += d;
            H[4] += e;
            H[5] += f;
            H[6] += g;
            H[7] += h;
        }

        // Concatenate hash values
        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            hash.append(String.format("%016x", H[i]));
        }

        return hash.toString();
    }

    public static void main(String[] args) {
        String message = "GeeksForGeeks";
        String hash = sha512(message);

        System.out.println("Message: " + message);
        System.out.println("SHA-512 Hash: " + hash);
    } 
}
