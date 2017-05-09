package optimization;

public class Point
{
    public double x1;

    public double x2;

    public double y;

    public Point(double x1, double x2, double y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    /**
     * Вывод в консоль
     */
    public void print() {
        System.out.println("(" + x1 + ", " + x2 + ", " + y + ");");
    }
}
