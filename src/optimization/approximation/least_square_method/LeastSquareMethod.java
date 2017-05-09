package optimization.approximation.least_square_method;

import optimization.Point;
import optimization.matrix.Matrix;

/**
 * Вычисление коэффициентов уравнения y = c1 +c2x1 + c3x2 методом наименьших квадратов
 */
public class LeastSquareMethod
{
    private Point[] points;

    /**
     * Constructor
     *
     * @param points массив точек
     */
    public LeastSquareMethod(Point[] points) {
        this.points = points;
    }

    private double getX1YSum() {
        double result = 0;
        for (Point point : this.points) {
            result += point.x1 * point.y;
        }
        return result;
    }

    private double getX2YSum() {
        double result = 0;
        for (Point point : this.points) {
            result += point.x2 * point.y;
        }
        return result;
    }

    private double getYSum() {
        double result = 0;
        for (Point point : this.points) {
            result += point.y;
        }
        return result;
    }

    private double getX1Sum() {
        double result = 0;
        for (Point point : this.points) {
            result += point.x1;
        }
        return result;
    }

    private double getX2Sum() {
        double result = 0;
        for (Point point : this.points) {
            result += point.x2;
        }
        return result;
    }

    private double getX1X2Sum() {
        double result = 0;
        for (Point point : this.points) {
            result += point.x1 + point.x2;
        }
        return result;
    }

    private double getX1SquareSum() {
        double result = 0;
        for (Point point : this.points) {
            result += Math.pow(point.x1, 2);
        }
        return result;
    }

    private double getX2SquareSum() {
        double result = 0;
        for (Point point : this.points) {
            result += Math.pow(point.x2, 2);
        }
        return result;
    }

    /**
     * Вычисление значений коэффициентов уравнения
     *
     * @return Матрица коэффициентов
     */
    public Matrix calculate() {
        Matrix params = new Matrix(new double[][]{
                {this.points.length, this.getX1Sum(), this.getX2Sum()},
                {this.getX1Sum(), this.getX1SquareSum(), this.getX1X2Sum()},
                {this.getX2Sum(), this.getX1X2Sum(), this.getX2SquareSum()}
        });

        Matrix invertedParams = Matrix.inverse(params);

        Matrix values = new Matrix(new double[][]{
                {this.getYSum()},
                {this.getX1YSum()},
                {this.getX2YSum()}
        });

        return Matrix.multiply(invertedParams, values);
    }
}
