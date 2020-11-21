public class GeneralFunction {
    public static void reverse(double[] x) {
        for (int i = 0; i < x.length/2; i++) {
            double temp = x[i];
            x[i] = x[x.length - i - 1];
            x[x.length - i - 1] = temp;
        }
    }

    public static double[] multiplyArray(double[] x, double[] y) {
        int len = x.length + y.length - 1;
        double[] result = new double[len];
        for (int i = 0; i < len; i++) result[i] = 0;
        for (int i = 0; i < x.length; i++)
            for (int j = 0; j < y.length; j++)
                result[i+j] += x[i]*y[j];

        return result;
    }

    public static double[] additionArray(double[] x, double[] y) {
        double[] result = (x.length > y.length) ? x : y;
        double[] other = (x.length > y.length) ? y : x;
        for (int i = 0; i < other.length; i++)
            result[i] += other[i];
        return result;
    }
}
