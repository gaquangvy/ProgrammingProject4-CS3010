import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneralForm {
    private final double[][] table;

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

    @Override
    public String toString() {
        StringBuilder x = new StringBuilder();
        x.append("\tx = ");
        StringBuilder y = new StringBuilder();
        y.append("\ty = ");

        for (double[] pair : table) {
            x.append(String.format("%10.3f", pair[0]));
            y.append(String.format("%10.3f", pair[1]));
        }

        return x.toString() + "\n" + y.toString();
    }
}
