package stringcalculator.domain;

public class Division implements Operator {
    @Override
    public int calculate(int x, int y) {
        return x / y;
    }
}
