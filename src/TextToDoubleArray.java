import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextToDoubleArray {
    File file;

    public void getFile(String source) {
        file = new File(source);
    }

    public double[][] translate() throws FileNotFoundException {
        Scanner in = new Scanner(file);

        String[] line = in.nextLine().split(" ");
        double[][] xy = new double[line.length][2];
        for (int i = 0; i < line.length; i++)
            xy[i][0] = Double.parseDouble(line[i]);

        line = in.nextLine().split(" ");
        for (int i = 0; i < line.length; i++)
            xy[i][1] = Double.parseDouble(line[i]);

        return xy;
    }
}
