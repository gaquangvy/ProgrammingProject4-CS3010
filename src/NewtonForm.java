import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewtonForm {
    private final List<double[]> dividedTable;

    NewtonForm(GeneralForm generalForm) {
        double[][] xy = generalForm.getTable();

        dividedTable = new ArrayList<>();
        double[] y = new double[generalForm.length()];
        for (int i = 0; i < generalForm.length(); i++)
            y[i] = xy[i][1];
        dividedTable.add(y);
        for (int i = 1; i < generalForm.length(); i++) {
            double[] newY = new double[generalForm.length() - i];
            for (int j = 0; j < generalForm.length() - i; j++)
                newY[j] = (y[j + 1] - y[j]) / (xy[j + i][0] - xy[j][0]);
            dividedTable.add(newY);
            y = newY;
        }
    }

    public void print() {
        for (double[] f : dividedTable)
            System.out.println(Arrays.toString(f));
    }
}
