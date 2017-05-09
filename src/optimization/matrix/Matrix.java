package optimization.matrix;

/**
 * Двумерная матрица
 */
public class Matrix
{
    public double[][] data;

    public int rowsCount;

    public int columnsCount;

    /**
     * Constructor
     *
     * @param matrix двумерный массив
     */
    public Matrix(double[][] matrix) {
        this.data = matrix;
        this.init();
    }

    /**
     * Constructor
     *
     * @param rows    количество строк
     * @param columns количество столбцов
     */
    public Matrix(int rows, int columns) {
        this.data = new double[rows][columns];
        this.init();
    }

    /**
     * Умножение двух матриц
     *
     * @param m1 первая матрица
     * @param m2 вторая матрица
     * @return Результат перемножения
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.rowsCount, m2.columnsCount);
        for (int m1Row = 0; m1Row < m1.rowsCount; m1Row++) {
            double[] row = m1.getRow(m1Row);
            for (int m2Column = 0; m2Column < m2.columnsCount; m2Column++) {
                double[] column = m2.getColumn(m2Column);
                double cellResult = Matrix.multipleArrays(row, column);
                result.data[m1Row][m2Column] = cellResult;
            }
        }
        return result;
    }

    /**
     * Перемножение массивов
     *
     * @param firstArray  Первый массив
     * @param secondArray Второй массив
     * @return Результат перемножения элементов массивов
     */
    private static double multipleArrays(double[] firstArray, double[] secondArray) {
        double result = 0;
        for (int i = 0; i < firstArray.length; i++) {
            result += firstArray[i] * secondArray[i];
        }
        return result;
    }

    /**
     * Создание единичной матрицы
     *
     * @param size размер матрицы
     * @return Единичная матрица
     */
    public static Matrix createUnitMatrix(int size) {
        Matrix unitMatrix = new Matrix(size, size);//единичная матрица
//заполнение единичной матрицы
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    unitMatrix.data[i][j] = 1;
                }
                else {
                    unitMatrix.data[i][j] = 0;
                }
            }
        }
        return unitMatrix;
    }

    /**
     * инверсия матрицы методом Жордана-Гаусса
     *
     * @param inputMatrix исходная матрица
     * @return Обратная матрица
     */
    public static Matrix inverse(Matrix inputMatrix) {
        int i, j, k;
        int size = inputMatrix.rowsCount;
        Matrix bufferMatrix = new Matrix(inputMatrix.data);

        Matrix unitMatrix = Matrix.createUnitMatrix(bufferMatrix.rowsCount);
        //Задаём номер ведущей строки (сначала 0,1...size)
        for (k = 0; k < size; k++) {
            for (j = k + 1; j < size; j++) {
                //все элементы k-ой строки матрицы, кроме k-ого и до него, делим на разрешающий элемент - a[k][k]
                bufferMatrix.data[k][j] = bufferMatrix.data[k][j] / bufferMatrix.data[k][k];
            }
            for (j = 0; j < size; j++) {
                //все элементы k-ой строки матрицы e, делим на разрешающий элемент - a[k][k]
                unitMatrix.data[k][j] = unitMatrix.data[k][j] / bufferMatrix.data[k][k];
            }
            bufferMatrix.data[k][k] = bufferMatrix.data[k][k] / bufferMatrix.data[k][k];//элемент соответствующий  разрещающему - делим на самого себя(т.е получит. 1 )
            //идём сверху вниз, обходя k-ую строку
            if (k > 0) {//если номер ведущей строки не первый
                for (i = 0; i < k; i++) {   //строки, находящиеся выше k-ой
                    for (j = 0; j < size; j++) {
                        //Вычисляем элементы единичной матрицы,идя по столбцам с 0 -ого  к последнему
                        unitMatrix.data[i][j] = unitMatrix.data[i][j] - unitMatrix.data[k][j] * bufferMatrix.data[i][k];
                    }
                    for (j = size - 1; j >= k; j--) {
                        //Вычисляем элементы исходной матрицы,идя по столбцам с последнего к k-ому
                        bufferMatrix.data[i][j] = bufferMatrix.data[i][j] - bufferMatrix.data[k][j] * bufferMatrix.data[i][k];
                    }
                }
            }
            //строки, находящиеся ниже k-ой
            for (i = k + 1; i < size; i++) {
                for (j = 0; j < size; j++) {
                    unitMatrix.data[i][j] = unitMatrix.data[i][j] - unitMatrix.data[k][j] * bufferMatrix.data[i][k];
                }
                for (j = size - 1; j >= k; j--) {
                    bufferMatrix.data[i][j] = bufferMatrix.data[i][j] - bufferMatrix.data[k][j] * bufferMatrix.data[i][k];
                }
            }
        }
        //На месте исходной матрицы должна получиться единичная а на месте единичной - обратная.
        return unitMatrix;
    }

    /**
     * Расчет количества строк и колонок в матрице
     */
    private void init() {
        this.rowsCount = this.data.length;
        this.columnsCount = this.data[0].length;
    }

    /**
     * Получение строки матрицы
     *
     * @param rowNumber номер строки
     * @return строка матрицы
     */
    public double[] getRow(int rowNumber) {
        return this.data[rowNumber];
    }

    /**
     * Получение колонки матрицы
     *
     * @param columnNumber номер колонки
     * @return колонка
     */
    public double[] getColumn(int columnNumber) {
        double[] column = new double[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            column[i] = this.data[i][columnNumber];
        }
        return column;
    }
}
