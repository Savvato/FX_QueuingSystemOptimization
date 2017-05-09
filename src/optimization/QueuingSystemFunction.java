package optimization;

import system.QueuingSystem;

/**
 * Функция отклика, имитирующая СМО
 */
public class QueuingSystemFunction implements ResponseFunctionInterface
{
    private double averageRequestIncomingTime;

    public QueuingSystemFunction(double averageRequestIncomingTime) {
        this.averageRequestIncomingTime = averageRequestIncomingTime;
    }

    /**
     * Получение значения отклика в точке
     *
     * @param x1 serviceChannelsCount
     * @param x2 averageServiceTime
     * @return
     */
    @Override
    public double calculate(double x1, double x2) {
        QueuingSystem queuingSystem = new QueuingSystem(
                (long) this.averageRequestIncomingTime,
                (int) x1,
                (long) x2
        );
        queuingSystem.run();
        return (double) queuingSystem.calculateAverageTimeInSystem();
    }
}
