import java.util.Scanner;

class AES {

    public char[][] substitute(char[][] state) {
        String[][] sBox = new String[][] {
                { "63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76" },
                { "ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0" },
                { "b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15" },
                { "04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75" },
                { "09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84" },
                { "53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cd", "be", "39", "4a", "4c", "58", "cf" },
                { "d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8" },
                { "51", "a3", "40", "87", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2" },
                { "cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73" },
                { "60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db" },
                { "e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79" },
                { "e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08" },
                { "ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "lf", "4b", "bd", "8b", "8a" },
                { "70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e" },
                { "e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "le", "87", "e9", "ce", "55", "28", "7e" },
                { "8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16" }, };

        // now we have to do Operation of substitute byte
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                // firstly take the character from input stream
                char chr = state[r][c];
                // then convert Character to hexadecimal string
                String hexStr = Integer.toHexString(chr);
                // now divide the hex string into two subpart 1hex value and 2hex value
                // String divion in two half
                // e.g. hexCode1 = "4"; hexCode2 = "d"
                String hexCode1 = hexStr.substring(0, 1); // give me first 4bits into hex code from string
                String hexCode2 = hexStr.substring(1, 2); // give me first 4bits into hex code from string

                // now get the integer index from hexcode to find the x and y coordinate to
                // subsitute
                int rowSbIdx = Integer.valueOf(hexCode1, 16).intValue();
                int colSbIdx = Integer.valueOf(hexCode2, 16).intValue();
                // convert hexCode to character then store to given grid.
                state[r][c] = (char) Integer.parseInt(sBox[rowSbIdx][colSbIdx], 16);
            }
        }
        return state;
    }

    public char[][] shiftRows(char[][] state) {
        for (int row = 1; row < 4; row++) { // run over the rows

            // byte shifting row by row
            // this loop will count how many of times we need to shift bytes to the left
            for (int shiftCount = row; shiftCount > 0; shiftCount -= 1) {
                char temp = state[row][0];
                for (int col = 0; col <= 2; col++) {
                    state[row][col] = state[row][1 + col];
                }
                state[row][3] = temp; // store temp var value into last index of state.
            }
        }

        return state; // return shifted block of bytes
    }

    public char[][] invShiftRows(char[][] state) {
        for (int row = 1; row < 4; row++) { // run over the rows

            // byte shifting row by row
            // this loop will count how many of times we need to shift bytes to the right
            // side
            for (int shiftCount = row; shiftCount > 0; shiftCount -= 1) {
                char temp = state[row][3]; // store last value of state in temp variable
                for (int col = 3; col >= 1; col--) {
                    state[row][col] = state[row][col - 1];
                }
                state[row][0] = temp; // store temp var value into first index of state.
            }
        }

        return state; // return inverse shifted block of bytes
    }

    public char[][] invSubstitute(char[][] state) {
        String[][] invSbox = new String[][] {
                { "52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb" },
                { "7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb" },
                { "54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e" },
                { "08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25" },
                { "72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92" },
                { "6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84" },
                { "90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06" },
                { "d0", "2c", "le", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b" },
                { "3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73" },
                { "96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e" },
                { "47", "f1", "la", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b" },
                { "fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4" },
                { "1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f" },
                { "60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef" },
                { "a0", "e0", "eb", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61" },
                { "17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d" } };

        // now we have to do Operation of substitute byte
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                // firstly take the character from input stream
                char chr = state[r][c];
                // then convert Character to hexadecimal string
                String hexStr = Integer.toHexString(chr);
                // now divide the hex string into two subpart 1hex value and 2hex value
                // String divion in two half
                // e.g. hexCode1 = "4"; hexCode2 = "d"
                String hexCode1 = hexStr.substring(0, 1); // give me first 4bits into hex code from string
                String hexCode2 = hexStr.substring(1, 2); // give me first 4bits into hex code from string

                // now get the integer index from hexcode to find the x and y coordinate to
                // subsitute
                int rowSbIdx = Integer.valueOf(hexCode1, 16).intValue();
                int colSbIdx = Integer.valueOf(hexCode2, 16).intValue();
                // convert hexCode to character then store to given grid.
                state[r][c] = (char) Integer.parseInt(invSbox[rowSbIdx][colSbIdx], 16);
            }
        }
        return state;
    }

}

public class cyperAES {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the text to securly Encrypt");
        String plainText = scan.next();

        // create an array of size 32 byte to store each character from given sting and
        // do encryption
        char[][] plainTxtGrid = new char[4][4];
        int charAtCount = 0;

        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (charAtCount < plainText.length()) {
                    // convert plain text String into Char array
                    plainTxtGrid[r][c] = plainText.charAt(charAtCount);
                    charAtCount++;
                } else
                    plainTxtGrid[r][c] = 'Z'; // Input string will be end then add dummy char at the end
            }
        }
        System.out.println("orignal text ");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(plainTxtGrid[r][c] + " ");
            }
            System.out.println("");
        }

        AES cyperGen = new AES();

        char subtiGrid[][] = cyperGen.substitute(plainTxtGrid);
        System.out.println("Subsitute");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(plainTxtGrid[r][c] + " ");
            }
            System.out.println("");
        }

        char shiftGrid[][] = cyperGen.shiftRows(plainTxtGrid);
        System.out.println("shift grid");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(shiftGrid[r][c] + " ");
            }
            System.out.println("");
        }

        char invShiftGrid[][] = cyperGen.invShiftRows(shiftGrid);
        System.out.println("Inv shift grid");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(invShiftGrid[r][c] + " ");
            }
            System.out.println("");
        }

        char invSubtiGrid[][] = cyperGen.invSubstitute(subtiGrid);
        System.out.println(" inv Subsitute");
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(invSubtiGrid[r][c] + " ");
            }
            System.out.println("");
        }

    }
}
