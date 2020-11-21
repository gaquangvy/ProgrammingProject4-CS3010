import java.io.FileNotFoundException;

public class ProgrammingProject4 {
    public static void main(String[] args) throws FileNotFoundException {
        GeneralForm generalForm = new GeneralForm();
        NewtonForm newtonForm = new NewtonForm(generalForm);
        newtonForm.print();
        System.out.println(newtonForm.printInterpolate());
    }
}
