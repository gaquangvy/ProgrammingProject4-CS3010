import java.io.FileNotFoundException;

public class ProgrammingProject4 {
    public static void main(String[] args) throws FileNotFoundException {
        GeneralForm generalForm = new GeneralForm();
        NewtonForm newtonForm = new NewtonForm(generalForm);

        System.out.println("Inputs:");
        System.out.println(generalForm + "\n");

        System.out.println("=========================Newton's Form=========================\n");
        System.out.println("Divided Difference Table:");
        System.out.println(newtonForm);
        System.out.println("Interpolating polynomial is:");
        System.out.println("\tp(x) = " + newtonForm.printInterpolate());
        System.out.println("Simplified polynomial is:");
        System.out.println("\tp(x) = " + newtonForm.printSimplify());
    }
}
