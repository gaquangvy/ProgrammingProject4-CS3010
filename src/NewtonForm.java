import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewtonForm {
    private final List<double[]> dividedTable;
    private final int n;
    private final double[][] xy;

    NewtonForm(GeneralForm generalForm) {
        xy = generalForm.getTable();
        n = generalForm.length();

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

    private double[][][] interpolatePolynomial() {
        double[][][] interpolate = new double[n][n][2];

        for (int i = 0; i < n; i++) {
            interpolate[i][0][0] = 0;
            interpolate[i][0][1] = dividedTable.get(i)[0];
            for (int j = 1; j < n; j++)
                if (j > i) {
                    interpolate[i][j][0] = 0;
                    interpolate[i][j][1] = 0;
                }
                else {
                    interpolate[i][j][0] = 1;
                    interpolate[i][j][1] = -xy[j-1][0];
                }
        }

        return interpolate;
    }

    public String printInterpolate() {
        double[][][] interpolate = interpolatePolynomial();
        StringBuilder polynomial = new StringBuilder();

        for (int i = 0; i < n; i++) {
            polynomial.append((i != 0) ? " + " : "");
            double[][] part = interpolate[i];
            for (int j = 0; j < n; j++) {
                if (part[j][0] != 0 && part[j][1] != 0) polynomial.append("(x + ").append(String.format("%.3f", part[j][1])).append(")");
                else if (part[j][0] == 0 && part[j][1] != 0)
                    polynomial.append(String.format("%.3f", part[j][1]));
                else if (part[j][0] != 0 && part[j][1] == 0) polynomial.append("x");
            }
        }

        return polynomial.toString();
    }

    public void print() {
        for (double[] f : dividedTable)
            System.out.println(Arrays.toString(f));
    }
}
