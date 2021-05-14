import java.util.Scanner;

public class cyperAES {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the text to securly Encrypt");
        String plainText = scan.next();

        // create an array of size 32 byte to store each character from given sting and
        // do encryption
        char[][] plainTxtGrid = new char[8][8];
        int charAtCount = 0;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (charAtCount < plainText.length()) {
                    plainTxtGrid[r][c] = plainText.charAt(charAtCount);
                    charAtCount++;
                } else
                    plainTxtGrid[r][c] = 'Z'; // Input string is end then add dummy char
            }
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.print(plainTxtGrid[r][c] + " ");
            }
        }

    }
}
