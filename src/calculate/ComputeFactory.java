package calculate;

public class ComputeFactory {

    private static final Add add = new Add();
    private static final Sub sub = new Sub();

    public static Computable getCompuet(char symbol) {
        switch (symbol){
            case '+':
                return add;
            case '-':
                return sub;
            default:
                throw new IllegalArgumentException();
        }
    }
}
