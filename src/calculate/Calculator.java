package calculate;

public class Calculator {
    public int compute(int num1, int num2, char symbol)  {
        Computable computable =  ComputeFactory.getCompuet(symbol);
        return computable.compute(num1, num2);
    }
}
