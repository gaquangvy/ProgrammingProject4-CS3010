import java.io.FileWriter;
import java.io.IOException;

public class ProgrammingProject4 {
    public static void main(String[] args) throws IOException {
        GeneralForm generalForm = new GeneralForm();

        FileWriter out = new FileWriter("outputs/newtons_" + generalForm.getFileName());
        NewtonForm newtonForm = new NewtonForm(generalForm);

        out.write("Inputs:\n");
        out.write(generalForm + "\n\n");
        out.write("=========================Newton's Form=========================\n\n");
        out.write("Divided Difference Table:\n");
        out.write(newtonForm + "\n");
        out.write("Interpolating polynomial is:\n");
        out.write("\tp(x) = " + newtonForm.printInterpolate() + "\n");
        out.write("Simplified polynomial is:\n");
        out.write("\tp(x) = " + newtonForm.printSimplify() + "\n");

        System.out.println("\nNewton's Polynomial Interpolation has results printed in");
        System.out.println("\toutputs/newtons_" + generalForm.getFileName());
        out.close();

        out = new FileWriter("outputs/lagrange_" + generalForm.getFileName());
        LagrangeForm lagrangeForm = new LagrangeForm(generalForm);

        out.write("Inputs:\n");
        out.write(generalForm + "\n\n");
        out.write("=========================Lagrange's Form=========================\n\n");
        out.write("Interpolating polynomial is:\n");
        out.write("\tp(x) = " + lagrangeForm.printInterpolate() + "\n");
        out.write("Simplified polynomial is:\n");
        out.write("\tp(x) = " + lagrangeForm.printSimplify() + "\n");

        System.out.println("\nLagrange's Polynomial Interpolation has results printed in");
        System.out.println("\toutputs/lagrange_" + generalForm.getFileName() + "\n");
        out.close();

        System.out.println("Finished! Please, check them out.");
    }
}
