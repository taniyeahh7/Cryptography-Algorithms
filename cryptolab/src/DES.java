// import java.util.*;

// public class DES {
//     static String[] roundKeys = new String[16];
//     static String pt;

//     static String decimalToBinary(int decimal) {
//         StringBuilder binary = new StringBuilder();
//         while (decimal != 0) {
//             binary.insert(0, (decimal % 2 == 0) ? "0" : "1");
//             decimal = decimal / 2;
//         }q
//         while (binary.length() < 4) { //ensures that the binary value never lesser than 4
//             binary.insert(0, "0");
//         }
//         return binary.toString();
//     }

//     static int binaryToDecimal(String binary) {
//         int decimal = 0;
//         int counter = 0;
//         int size = binary.length();
//         for (int i = size - 1; i >= 0; i--) {
//             if (binary.charAt(i) == '1') {
//                 decimal += Math.pow(2, counter);
//             }
//             counter++;
//         }
//         return decimal;
//     }

//     static String shiftLeftOnce(String keyPart) {
//         return keyPart.substring(1) + keyPart.charAt(0); //getting the entire string from index 1 and pushing the last bit ahead again
//     }

//     static String shiftLeftTwice(String keyPart) {
//         String shifted = keyPart;
//         for (int i = 0; i < 2; i++) {
//             shifted = shiftLeftOnce(shifted); //shifting twice
//         }
//         return shifted;
//     }

//     static String xor(String a, String b) {
//         StringBuilder result = new StringBuilder();
//         int size = b.length();
//         for (int i = 0; i < size; i++) {
//             result.append(a.charAt(i) != b.charAt(i) ? "1" : "0");
//         }
//         return result.toString();
//     }

//     static void generateKeys(String key) {
//         int[] pc1 = {
//             57, 49, 41, 33, 25, 17, 9,
//             1, 58, 50, 42, 34, 26, 18,
//             10, 2, 59, 51, 43, 35, 27,
//             19, 11, 3, 60, 52, 44, 36,
//             63, 55, 47, 39, 31, 23, 15,
//             7, 62, 54, 46, 38, 30, 22,
//             14, 6, 61, 53, 45, 37, 29,
//             21, 13, 5, 28, 20, 12, 4
//         };
//         int[] pc2 = {
//             14, 17, 11, 24, 1, 5,
//             3, 28, 15, 6, 21, 10,
//             23, 19, 12, 4, 26, 8,
//             16, 7, 27, 20, 13, 2,
//             41, 52, 31, 37, 47, 55,
//             30, 40, 51, 45, 33, 48,
//             44, 49, 39, 56, 34, 53,
//             46, 42, 50, 36, 29, 32
//         };
//         StringBuilder permKey = new StringBuilder();
//         for (int value : pc1) {
//             permKey.append(key.charAt(value - 1));
//         }
//         String left = permKey.substring(0, 28);
//         String right = permKey.substring(28);
//         for (int i = 0; i < 16; i++) {
//             if (i == 0 || i == 1 || i == 8 || i == 15) { // check what round we are on, if 1, 2, 9, 16 shift once both left and right part otherwise shift twice
//                 left = shiftLeftOnce(left);
//                 right = shiftLeftOnce(right);
//             } else {
//                 left = shiftLeftTwice(left);
//                 right = shiftLeftTwice(right);
//             }
//             String combinedKey = left + right;
//             StringBuilder roundKey = new StringBuilder();
//             for (int value : pc2) {
//                 roundKey.append(combinedKey.charAt(value - 1));
//             }
//             roundKeys[i] = roundKey.toString();
//         }
//     }

//     static String DES() {
//         int[] initialPermutation = {
//             58, 50, 42, 34, 26, 18, 10, 2,
//             60, 52, 44, 36, 28, 20, 12, 4,
//             62, 54, 46, 38, 30, 22, 14, 6,
//             64, 56, 48, 40, 32, 24, 16, 8,
//             57, 49, 41, 33, 25, 17, 9, 1,
//             59, 51, 43, 35, 27, 19, 11, 3,
//             61, 53, 45, 37, 29, 21, 13, 5,
//             63, 55, 47, 39, 31, 23, 15, 7
//         };
//         int[] expansionTable = {
//             32, 1, 2, 3, 4, 5, 4, 5,
//             6, 7, 8, 9, 8, 9, 10, 11,
//             12, 13, 12, 13, 14, 15, 16, 17,
//             16, 17, 18, 19, 20, 21, 20, 21,
//             22, 23, 24, 25, 24, 25, 26, 27,
//             28, 29, 28, 29, 30, 31, 32, 1
//         };
//         int[][][] substitutionBoxes = {
//             {
//                 {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
//                 {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
//                 {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
//                 {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
//             },
//             {
//                 {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
//                 {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
//                 {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
//                 {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
//             },
//             {
//                 {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
//                 {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
//                 {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
//                 {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
//             },
//             {
//                 {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
//                 {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
//                 {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
//                 {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
//             },
//             {
//                 {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
//                 {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
//                 {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
//                 {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
//             },
//             {
//                 {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
//                 {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
//                 {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
//                 {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
//             },
//             {
//                 {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
//                 {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
//                 {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
//                 {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
//             },
//             {
//                 {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
//                 {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
//                 {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
//                 {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
//             }
//         };
//         int[] permutationTab = {
//             16, 7, 20, 21, 29, 12, 28, 17,
//             1, 15, 23, 26, 5, 18, 31, 10,
//             2, 8, 24, 14, 32, 27, 3, 9,
//             19, 13, 30, 6, 22, 11, 4, 25
//         };
//         int[] inversePermutation = {
//             40, 8, 48, 16, 56, 24, 64, 32,
//             39, 7, 47, 15, 55, 23, 63, 31,
//             38, 6, 46, 14, 54, 22, 62, 30,
//             37, 5, 45, 13, 53, 21, 61, 29,
//             36, 4, 44, 12, 52, 20, 60, 28,
//             35, 3, 43, 11, 51, 19, 59, 27,
//             34, 2, 42, 10, 50, 18, 58, 26,
//             33, 1, 41, 9, 49, 17, 57, 25
//         };
//         StringBuilder perm = new StringBuilder();
//         for (int value : initialPermutation) {
//             perm.append(pt.charAt(value - 1));
//         }
//         String left = perm.substring(0, 32);
//         String right = perm.substring(32);
//         for (int i = 0; i < 16; i++) {
//             StringBuilder rightExpanded = new StringBuilder();
//             for (int value : expansionTable) {
//                 rightExpanded.append(right.charAt(value - 1));
//             }
//             String xored = xor(roundKeys[i], rightExpanded.toString());
//             StringBuilder res = new StringBuilder();
//             for (int j = 0; j < 8; j++) { // sboxes
//                 String row1 = xored.substring(j * 6, j * 6 + 1) + xored.substring(j * 6 + 5, j * 6 + 6); // getting the correct row value
//                 int row = binaryToDecimal(row1);
//                 String col1 = xored.substring(j * 6 + 1, j * 6 + 2) + xored.substring(j * 6 + 2, j * 6 + 3) // getting the correct column value
//                         + xored.substring(j * 6 + 3, j * 6 + 4) + xored.substring(j * 6 + 4, j * 6 + 5);
//                 int col = binaryToDecimal(col1);
//                 int val = substitutionBoxes[j][row][col]; // accessing the value
//                 res.append(decimalToBinary(val));
//             }
//             StringBuilder perm2 = new StringBuilder();
//             for (int value : permutationTab) {
//                 perm2.append(res.charAt(value - 1));
//             }
//             xored = xor(perm2.toString(), left);
//             left = xored;
//             if (i < 15) {
//                 String temp = right;
//                 right = xored;
//                 left = temp;
//             }
//         }
//         String combinedText = left + right;
//         StringBuilder ciphertext = new StringBuilder();
//         for (int value : inversePermutation) {
//             ciphertext.append(combinedText.charAt(value - 1));
//         }
//         return ciphertext.toString();
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String key = "";
//         pt = "";
        
//         System.out.println("Enter the plain text :- ");
//         key = sc.nextLine();
//         generateKeys(key);
//         System.out.println("Enter the key :- ");
//         pt = sc.nextLine();
//         String ct = DES();
//         System.out.println();
//         System.out.println("The ciphertext is: " + ct);
//     }
// }


// import java.util.*;
// public class DES {
// static String[] roundKeys = new String[16];
// static String pt;
// static String decimalToBinary(int decimal) {
// StringBuilder binary = new StringBuilder();
// while (decimal != 0) {
// binary.insert(0, (decimal % 2 == 0) ? "0" : "1");
// decimal = decimal / 2;
// }
// while (binary.length() < 4) {
// binary.insert(0, "0");
// }
// return binary.toString();
// }
// static int binaryToDecimal(String binary) {
// int decimal = 0;
// int counter = 0;
// int size = binary.length();
// for (int i = size - 1; i >= 0; i--) {
// if (binary.charAt(i) == '1') {
// decimal += Math.pow(2, counter);
// }
// counter++;
// }
// return decimal;
// }
// static String shiftLeftOnce(String keyPart) {
// return keyPart.substring(1) + keyPart.charAt(0);
// }
// static String shiftLeftTwice(String keyPart) {
// String shifted = keyPart;
// for (int i = 0; i < 2; i++) {
// shifted = shiftLeftOnce(shifted);
// }
// return shifted;
// }
// static String xor(String a, String b) {
// StringBuilder result = new StringBuilder();
// int size = b.length();
// for (int i = 0; i < size; i++) {
// result.append(a.charAt(i) != b.charAt(i) ? "1" : "0");
// }
// return result.toString();
// }
// static void generateKeys(String key) {
// int[] pc1 = {
// 57, 49, 41, 33, 25, 17, 9,
// 1, 58, 50, 42, 34, 26, 18,
// 10, 2, 59, 51, 43, 35, 27,
// 19, 11, 3, 60, 52, 44, 36,
// 63, 55, 47, 39, 31, 23, 15,
// 7, 62, 54, 46, 38, 30, 22,
// 14, 6, 61, 53, 45, 37, 29,
// 21, 13, 5, 28, 20, 12, 4
// };
// int[] pc2 = {
// 14, 17, 11, 24, 1, 5,
// 3, 28, 15, 6, 21, 10,
// 23, 19, 12, 4, 26, 8,
// 16, 7, 27, 20, 13, 2,
// 41, 52, 31, 37, 47, 55,
// 30, 40, 51, 45, 33, 48,
// 44, 49, 39, 56, 34, 53,
// 46, 42, 50, 36, 29, 32
// };
// StringBuilder permKey = new StringBuilder();
// for (int value : pc1) {
// permKey.append(key.charAt(value - 1));
// }
// String left = permKey.substring(0, 28);
// String right = permKey.substring(28);
// for (int i = 0; i < 16; i++) {
// if (i == 0 || i == 1 || i == 8 || i == 15) {
// left = shiftLeftOnce(left);
// right = shiftLeftOnce(right);
// } else {
// left = shiftLeftTwice(left);
// right = shiftLeftTwice(right);
// }
// String combinedKey = left + right;
// StringBuilder roundKey = new StringBuilder();
// for (int value : pc2) {
// roundKey.append(combinedKey.charAt(value - 1));
// }
// roundKeys[i] = roundKey.toString();
// }
// }
// static String DES() {
// int[] initialPermutation = {
// 58, 50, 42, 34, 26, 18, 10, 2,
// 60, 52, 44, 36, 28, 20, 12, 4,
// 62, 54, 46, 38, 30, 22, 14, 6,
// 64, 56, 48, 40, 32, 24, 16, 8,
// 57, 49, 41, 33, 25, 17, 9, 1,
// 59, 51, 43, 35, 27, 19, 11, 3,
// 61, 53, 45, 37, 29, 21, 13, 5,
// 63, 55, 47, 39, 31, 23, 15, 7
// };
// int[] expansionTable = {
// 32, 1, 2, 3, 4, 5, 4, 5,
// 6, 7, 8, 9, 8, 9, 10, 11,
// 12, 13, 12, 13, 14, 15, 16, 17,
// 16, 17, 18, 19, 20, 21, 20, 21,
// 22, 23, 24, 25, 24, 25, 26, 27,
// 28, 29, 28, 29, 30, 31, 32, 1
// };
// int[][][] substitutionBoxes = {
// {
// {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
// {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
// {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
// {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
// },
// {
// {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
// {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
// {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
// {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
// },
// {
// {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
// {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
// {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
// {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
// },
// {
// {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
// {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
// {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
// {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
// },
// {
// {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
// {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
// {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
// {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
// },
// {
// {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
// {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
// {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
// {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
// },
// {
// {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
// {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
// {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
// {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
// },
// {
// {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
// {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
// {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
// {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
// }
// };
// int[] permutationTab = {
// 16, 7, 20, 21, 29, 12, 28, 17,
// 1, 15, 23, 26, 5, 18, 31, 10,
// 2, 8, 24, 14, 32, 27, 3, 9,
// 19, 13, 30, 6, 22, 11, 4, 25
// };
// int[] inversePermutation = {
// 40, 8, 48, 16, 56, 24, 64, 32,
// 39, 7, 47, 15, 55, 23, 63, 31,
// 38, 6, 46, 14, 54, 22, 62, 30,
// 37, 5, 45, 13, 53, 21, 61, 29,
// 36, 4, 44, 12, 52, 20, 60, 28,
// 35, 3, 43, 11, 51, 19, 59, 27,
// 34, 2, 42, 10, 50, 18, 58, 26,
// 33, 1, 41, 9, 49, 17, 57, 25
// };
// StringBuilder perm = new StringBuilder();
// for (int value : initialPermutation) {
// perm.append(pt.charAt(value - 1));
// }
// String left = perm.substring(0, 32);
// String right = perm.substring(32);
// for (int i = 0; i < 16; i++) {
// StringBuilder rightExpanded = new StringBuilder();
// for (int value : expansionTable) {
// rightExpanded.append(right.charAt(value - 1));
// }
// String xored = xor(roundKeys[i], rightExpanded.toString());
// StringBuilder res = new StringBuilder();
// for (int j = 0; j < 8; j++) {
// String row1 = xored.substring(j * 6, j * 6 + 1) + xored.substring(j * 6 + 5, j * 6 + 6);
// int row = binaryToDecimal(row1);
// String col1 = xored.substring(j * 6 + 1, j * 6 + 2) + xored.substring(j * 6 + 2, j * 6 + 3)
// + xored.substring(j * 6 + 3, j * 6 + 4) + xored.substring(j * 6 + 4, j * 6 + 5);
// int col = binaryToDecimal(col1);
// int val = substitutionBoxes[j][row][col];
// res.append(decimalToBinary(val));
// }
// StringBuilder perm2 = new StringBuilder();
// for (int value : permutationTab) {
// perm2.append(res.charAt(value - 1));
// }
// xored = xor(perm2.toString(), left);
// left = xored;
// if (i < 15) {
// String temp = right;
// right = xored;
// left = temp;
// }
// }
// String combinedText = left + right;
// StringBuilder ciphertext = new StringBuilder();
// for (int value : inversePermutation) {
// ciphertext.append(combinedText.charAt(value - 1));
// }
// return ciphertext.toString();
// }
// public static void main(String[] args) {
// Scanner sc = new Scanner(System.in);
// String key = "";
// pt = "";
// System.out.println("Enter the plain text :- ");
// key = sc.nextLine();
// generateKeys(key);
// System.out.println("Enter the key :- ");
// pt = sc.nextLine();
// String ct = DES();
// System.out.println();
// System.out.println("The ciphertext is: " + ct);
// }
// }



// Including dependencies




public class DES{
    public static String pt = "";
    public static String[] roundKeys = new String[16];

    public static int[] pc1 = {

        57,49,41,33,25,17,9, 
        1,58,50,42,34,26,18, 
        10,2,59,51,43,35,27, 
        19,11,3,60,52,44,36,		 
        63,55,47,39,31,23,15, 
        7,62,54,46,38,30,22, 
        14,6,61,53,45,37,29, 
        21,13,5,28,20,12,4 
        };
        // The PC2 table
    public static int[] pc2 = { 
        14,17,11,24,1,5, 
        3,28,15,6,21,10, 
        23,19,12,4,26,8, 
        16,7,27,20,13,2, 
        41,52,31,37,47,55, 
        30,40,51,45,33,48, 
        44,49,39,56,34,53, 
        46,42,50,36,29,32 
        }; 

    public static int initial_permutation[] = { 
            58,50,42,34,26,18,10,2, 
            60,52,44,36,28,20,12,4, 
            62,54,46,38,30,22,14,6, 
            64,56,48,40,32,24,16,8, 
            57,49,41,33,25,17,9,1, 
            59,51,43,35,27,19,11,3, 
            61,53,45,37,29,21,13,5, 
            63,55,47,39,31,23,15,7 
            }; 
            // The expansion table
    public static int expansion_table[] = { 
            32,1,2,3,4,5,4,5, 
            6,7,8,9,8,9,10,11, 
            12,13,12,13,14,15,16,17, 
            16,17,18,19,20,21,20,21, 
            22,23,24,25,24,25,26,27, 
            28,29,28,29,30,31,32,1 
            }; 
            // The substitution boxes. The should contain values
            // from 0 to 15 in any order.
            
            // The permutation table
    public static int permutation_tab[] = { 
            16,7,20,21,29,12,28,17, 
            1,15,23,26,5,18,31,10, 
            2,8,24,14,32,27,3,9,
            19,13,30,6,22,11,4,25 
            }; 
            // The inverse permutation table
    public static int inverse_permutation[] = { 
            40,8,48,16,56,24,64,32, 
            39,7,47,15,55,23,63,31, 
            38,6,46,14,54,22,62,30, 
            37,5,45,13,53,21,61,29, 
            36,4,44,12,52,20,60,28, 
            35,3,43,11,51,19,59,27, 
            34,2,42,10,50,18,58,26, 
            33,1,41,9,49,17,57,25 
            };

    public static int[][][] sBoxes = {
        // S1
        {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        },
        // S2
        {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        },
        // S3
        {
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        },
        // S4
        {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        },
        // S5
        {
            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        },
        // S6
        {
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        },
        // S7
        {
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        },
        // S8
        {
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        }
    };

    public static String shiftLeftOnce(String str){
        return str.substring(1) + str.charAt(0);
    }

    public static String shiftLeftTwice(String str){
        return shiftLeftOnce(shiftLeftOnce(str));
    }

    public static void generateKeys(String str){
        String permKey = "";

        for(int i = 0; i < 56; i++){
            permKey += str.charAt(pc1[i] - 1);
        }

        String left = permKey.substring(0, 28);
        String right = permKey.substring(28, 56);

        for(int i = 0; i < 16; i++){
            if(i == 0 || i == 1 || i == 8 || i == 15){
                left = shiftLeftOnce(left);
                right = shiftLeftOnce(right);
            }
            else{
                left = shiftLeftTwice(left);
                right = shiftLeftTwice(right);
            }

            String combinedKey = left + right;
            String roundKey = "";

            for(int j = 0; j < 48; j++){
                roundKey += combinedKey.charAt(pc2[j] - 1); 
            }

            roundKeys[i] = roundKey;
        }
    }

    public static String xor(String a,  String b){
        String xor = "";

        for(int i = 0; i < b.length(); i++){
            if(b.charAt(i) == a.charAt(i)){
                xor += "0";
            }
            else{
                xor += "1";
            }
        }

        return xor;
    }

    public static int binDec(String str){
        return Integer.parseInt(str, 2);
    }

    public static String decBin(int dec){
        String str = Integer.toBinaryString(dec);

        while(str.length() < 4){
            str =  "0" + str;
        }

        return str;
    }


    public static String DES(){
        String perm = "";

        for(int i = 0; i < 64; i++){
            perm += pt.charAt(initial_permutation[i] - 1); //very important permutation is of plain text 
        }

        String left = perm.substring(0, 32);
        String right = perm.substring(32, 64);

        for(int i = 0; i < 16; i++){
            String rightExpanded = "";

            for(int j = 0; j < 48; j++){
                rightExpanded += right.charAt(expansion_table[j] - 1);
            }

            String xored = xor(roundKeys[i], rightExpanded);
            String res = "";

            for(int j = 0; j < 8; j++){
                String row1 = xored.substring(j * 6, j * 6 + 1) + xored.substring(j * 6 + 5, j * 6 + 6);
                int row = binDec(row1);

                String col1 = xored.substring(j * 6 + 1, j * 6 + 2) + xored.substring(j * 6 + 2, j * 6 + 3) + xored.substring(j * 6 + 3, j * 6 + 4) + xored.substring(j * 6 + 4, j * 6 + 5);
                int col = Integer.parseInt(col1, 2);

                int val = sBoxes[j][row][col];

                res += decBin(val);
            }

            String perm2 = "";

            for(int j = 0; j < 32; j++){
                perm2 += res.charAt(permutation_tab[j] - 1); // basically the straight p box
            }

            xored = xor(perm2, left);

            if(i < 15){ 
                String temp = right;
                right = xored;
                left = temp; 
            } 
            
        }

        String combinedText = left + right;
        String ciphertext = "";

        for(int i = 0; i < 64; i++){ 
            ciphertext += combinedText.charAt(inverse_permutation[i] - 1);
        }
        

        return ciphertext;
    }

    public static void main(String[] args) {
        String key = "1010101010111011000010010001100000100111001101101100110011011101";
        pt = "1010101111001101111001101010101111001101000100110010010100110110";

        generateKeys(key);
        String cipher = DES();

        int  i = 15, j = 0;

        while(i > j){
            // String roundKeys[i]
        }

        System.out.println(cipher);
    }
}
// Array to hold 16 keys
