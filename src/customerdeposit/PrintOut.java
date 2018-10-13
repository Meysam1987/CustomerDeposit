
package customerdeposit;

import java.io.FileWriter;
import java.io.IOException;

/**
 * this class only include a method that makes a file and prints string to it
 *
 * @author Meysam Gholami
 */
public class PrintOut {

    public static void printToFile(String fileName, String message) throws IOException {

        FileWriter fileWriter = new FileWriter(fileName + ".txt");
        fileWriter.write(message);
        fileWriter.close();

    }

}
