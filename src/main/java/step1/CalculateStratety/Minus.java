package step1.CalculateStratety;

public class Minus implements CalculateStrategy{
    @Override
    public Integer go(int num1, int num2) {
        return num1 - num2;
    }
}
