import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneralForm {
    private double[][] table;

    GeneralForm() throws FileNotFoundException {
        System.out.print("Please choose a filename: ");
        Scanner in = new Scanner(System.in);
        TextToDoubleArray textToDoubleArray = new TextToDoubleArray();
        textToDoubleArray.getFile("inputs/" + in.next());
        table = textToDoubleArray.translate();
    }

    public double[][] getTable() {
        return table;
    }

    public int length() {
        return table.length;
    }
}
