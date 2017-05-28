package optimization;

import system.QueuingSystem;

/**
 * Функция отклика, имитирующая СМО
 */
public class QueuingSystemFunction implements ResponseFunctionInterface
{
    private static final byte EXPERIMENTS_COUNT = 5;

    private double averageRequestIncomingTime;

    public QueuingSystemFunction(double averageRequestIncomingTime) {
        this.averageRequestIncomingTime = averageRequestIncomingTime;
    }

    /**
     * Получение математического ожидания значения отклика в точке
     *
     * @param x1 serviceChannelsCount
     * @param x2 averageServiceTime
     * @return
     */
    @Override
    public double calculate(double x1, double x2) {
        double sumResult = 0;
        for (int i = 0; i < EXPERIMENTS_COUNT; i++) {
            sumResult += this.calculateSimpleResponse(x1, x2);
        }
        return sumResult / EXPERIMENTS_COUNT;
    }

    /**
     * Получение значения отклика в точке
     *
     * @param x1 serviceChannelsCount
     * @param x2 averageServiceTime
     * @return
     */
    private double calculateSimpleResponse(double x1, double x2) {
        QueuingSystem queuingSystem = new QueuingSystem(
                (long) this.averageRequestIncomingTime,
                (int) x1,
                (long) x2
        );
        queuingSystem.run();
        return (double) queuingSystem.calculateAverageTimeInSystem();
    }
}
