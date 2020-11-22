import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

public class NewtonForm implements PolynomialInterpolation {
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

    private List<double[]>[] interpolatePolynomial() {
        List<double[]>[] interpolate = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            List<double[]> part = new ArrayList<>();
            part.add(new double[] {dividedTable.get(i)[0]});
            for (int j = 0; j < i; j++)
                part.add(new double[] {1, (-1) * xy[j][0]});
            interpolate[i] = part;
        }

        return interpolate;
    }

    public String printInterpolate() {
        List<double[]>[] interpolate = interpolatePolynomial();
        StringBuilder polynomial = new StringBuilder();

        for (int i = 0; i < n; i++) {
            double constant = interpolate[i].get(0)[0];
            if (constant != 0) {
                polynomial.append((i != 0) ?
                        ((constant < 0 ? " - " : " + ") + (constant != 1 ? String.format("%.3f", abs(constant)) : ""))
                        : String.format("%.3f", constant));
                for (int j = 1; j < i + 1; j++) {
                    double[] p = interpolate[i].get(j);
                    constant = p[1];
                    if (constant == 0) polynomial.append("x");
                    else
                        polynomial.append("(x ").append(constant < 0 ? "- " : "+ ").append(String.format("%.3f", abs(constant))).append(")");
                }
            }
        }

        if (polynomial.charAt(0) == ' ')
            return polynomial.substring(3);
        return polynomial.toString();
    }

    public String printSimplify() {
        List<double[]>[] interpolate = interpolatePolynomial();
        StringBuilder polynomial = new StringBuilder();
        double[] simplifies = new double[n];
        for (int i = 0; i < n; i++) simplifies[i] = 0;

        for (List<double[]> part : interpolate) if (part.get(0)[0] != 0){
            double[] s = new double[] {1};
            for (double[] i : part)
                s = GeneralFunction.multiplyArray(s, i);
            GeneralFunction.reverse(s);
            simplifies = GeneralFunction.additionArray(simplifies, s);
        }
        GeneralFunction.reverse(simplifies);

        if (simplifies[0] != 0)
            polynomial.append(simplifies[0] != 1 ? String.format("%.3f", simplifies[0]) : "")
                    .append("x^")
                    .append(n - 1);
        for (int i = 1; i < n; i++) if (simplifies[i] != 0) {
            polynomial.append(simplifies[i] < 0 ? " - " : " + ").append(simplifies[i] != 1 || i == (n-1) ? String.format("%.3f", abs(simplifies[i])) : "");
            if (i != n - 1)
                if (i == n - 2) polynomial.append("x");
                else polynomial.append("x^").append(n - i - 1);
        }

        if (polynomial.charAt(0) == ' ')
            return polynomial.substring(3);
        return polynomial.toString();
    }

    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(String.format("%12s", "x"));
        for (int i = 0; i < n; i++) {
            String fFormat = "f[" + " ,".repeat(i) +
                    " ]";
            out.append(String.format("%12s", fFormat));
        }

        StringBuilder[] results = new StringBuilder[n*2 - 1];
        for (int i = 0; i < 2*n - 1; i++) {
            results[i] = new StringBuilder();
            if (i % 2 == 0) {
                results[i].append(String.format("%12.3f", xy[i / 2][0]));
                results[i].append(String.format("%12.3f", dividedTable.get(0)[i / 2]));
            } else results[i].append(String.format("%12s", ""));
        }
        for (int i = 1; i < n; i++) {
            double[] col = dividedTable.get(i);
            for (int j = 0; j < col.length; j++) {
                results[i + 2*j].append(String.format("%24.3f", dividedTable.get(i)[j]));
            }
        }

        for (StringBuilder result : results) out.append("\n").append(result.toString());
        return out.toString();
    }
}
