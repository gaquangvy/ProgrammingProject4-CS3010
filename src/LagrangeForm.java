import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

public class LagrangeForm implements PolynomialInterpolation {
    private final double[][] xy;
    private final int n;

    LagrangeForm(GeneralForm generalForm) {
        xy = generalForm.getTable();
        n = generalForm.length();
    }

    private List<double[]>[] interpolatePolynomial() {
        List<double[]>[] interpolate = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            List<double[]> part = new ArrayList<>();
            double constant = xy[i][1];
            for (int j = 0; j < n; j++)
                if (i != j) {
                    constant /= (xy[i][0] - xy[j][0]);
                    part.add(new double[]{1, (-1) * xy[j][0]});
                }
            part.add(0, new double[] {constant});
            interpolate[i] = part;
        }

        return interpolate;
    }

    @Override
    public String printInterpolate() {
        List<double[]>[] interpolate = interpolatePolynomial();
        StringBuilder polynomial = new StringBuilder();

        for (int i = 0; i < n; i++) {
            double constant = interpolate[i].get(0)[0];
            if (constant != 0) {
                polynomial.append((i != 0) ?
                        ((constant < 0 ? " - " : " + ") + (constant != 1 ? String.format("%.3f", abs(constant)) : ""))
                        : String.format("%.3f", constant));
                for (int j = 1; j < n; j++) {
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

    @Override
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
}
