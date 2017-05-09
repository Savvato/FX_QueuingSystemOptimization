package optimization.approximation;

import optimization.Point;
import optimization.ResponseFunctionInterface;
import optimization.approximation.least_square_method.Formula;
import optimization.approximation.least_square_method.LeastSquareMethod;
import optimization.matrix.Matrix;

public class ApproximationHandler
{
    private static final double POINTS_DELTA = 0.05;

    private Point startPoint;

    private ResponseFunctionInterface responseFunctionInterface;

    private Point currentCentralPoint;

    public ApproximationHandler(Point startPoint, ResponseFunctionInterface responseFunctionInterface) {
        this.startPoint = startPoint;
        this.responseFunctionInterface = responseFunctionInterface;
    }

    public Point run() {
        this.currentCentralPoint = this.startPoint;
        Point oldPoint;
        double[] gradient;

        do {
            Point[] trainingPoints = this.getTrainingPoints();
            LeastSquareMethod method = new LeastSquareMethod(trainingPoints);
            Matrix coefficients = method.calculate();
            double k1 = coefficients.data[0][0];
            double k2 = coefficients.data[1][0];
            double k3 = coefficients.data[2][0];
            Formula formula = new Formula(k1, k2, k3);
            gradient = this.calculateGradient(formula);
            oldPoint = this.currentCentralPoint;
            this.moveCentralPoint(gradient);
        }
        while (oldPoint.y < this.currentCentralPoint.y);

        return oldPoint;
    }

    /**
     * Расчет градиента
     *
     * @param formula
     * @return
     */
    private double[] calculateGradient(Formula formula) {
        return new double[]{
                this.currentCentralPoint.x1 > 0 ? formula.c2 : -formula.c2,
                this.currentCentralPoint.x2 > 0 ? formula.c3 : -formula.c3,
        };
    }

    /**
     * Перемещение центральной точки эксперимента
     *
     * @param gradient
     */
    private void moveCentralPoint(double[] gradient) {
        double newX1 = this.currentCentralPoint.x1 + gradient[0];
        double newX2 = this.currentCentralPoint.x2 + gradient[1];
        double newY = this.responseFunctionInterface.calculate(newX1, newX2);
        this.currentCentralPoint = new Point(newX1, newX2, newY);
    }

    /**
     * Получение обучающих точек для МНК
     *
     * @return
     */
    private Point[] getTrainingPoints() {
        return new Point[]{
                new Point(
                        currentCentralPoint.x1 + POINTS_DELTA,
                        currentCentralPoint.x2 + POINTS_DELTA,
                        responseFunctionInterface.calculate(currentCentralPoint.x1 + POINTS_DELTA, currentCentralPoint.x2 + POINTS_DELTA)
                ),
                new Point(
                        currentCentralPoint.x1 - POINTS_DELTA,
                        currentCentralPoint.x2 - POINTS_DELTA,
                        responseFunctionInterface.calculate(currentCentralPoint.x1 - POINTS_DELTA, currentCentralPoint.x2 - POINTS_DELTA)
                ),
                new Point(
                        currentCentralPoint.x1 + POINTS_DELTA,
                        currentCentralPoint.x2 - POINTS_DELTA,
                        responseFunctionInterface.calculate(currentCentralPoint.x1 + POINTS_DELTA, currentCentralPoint.x2 - POINTS_DELTA)
                ),
                new Point(
                        currentCentralPoint.x1 - POINTS_DELTA,
                        currentCentralPoint.x2 + POINTS_DELTA,
                        responseFunctionInterface.calculate(currentCentralPoint.x1 - POINTS_DELTA, currentCentralPoint.x2 + POINTS_DELTA)
                )
        };
    }
}
