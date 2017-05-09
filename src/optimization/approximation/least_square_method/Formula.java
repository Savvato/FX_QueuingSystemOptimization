package optimization.approximation.least_square_method;

/**
 * Формула
 */
public class Formula
{
    public double c1;

    public double c2;

    public double c3;

    /**
     * Constructor
     *
     * @param c1 значение коэффициента с1
     * @param c2 значение коэффициента с2
     * @param c3 значение коэффициента с3
     */
    public Formula(double c1, double c2, double c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    /**
     * Расчет значения по тестовым x1 и x2
     *
     * @param x1 значение х1
     * @param x2 значение х2
     * @return y
     */
    public double calculate(double x1, double x2) {
        return this.c1 + this.c2 * x1 + this.c3 * x2;
    }
}
