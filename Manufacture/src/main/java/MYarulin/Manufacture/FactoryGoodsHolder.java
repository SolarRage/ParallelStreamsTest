package MYarulin.Manufacture;

/**
 *
 */
public class FactoryGoodsHolder {

    private int computers;

    /**
     * Метод принимает компы на склад.
     * После получения партии компов, будит все потоки.
     *
     * @param computersCount
     */
    public synchronized void putComputers(int computersCount) {
        this.computers += computersCount;
        System.out.println(computers + " after put.");
        notifyAll();
    }

    /**
     * Метод выдает компы конечному потребителю consumer.
     * Проверяет наличие компов через вспомогательный метод checkIfComputersAreAvailable
     *
     * @param computersCount
     * @return
     * @throws InterruptedException
     */
    public synchronized int receiveComputers(int computersCount) throws InterruptedException {
        checkIfComputersAreAvailable(computersCount);
        this.computers -= computersCount;
        System.out.println(computers + " after receive.");
        return computersCount;
    }

    /**
     * Метод проверяет, достаточно ли компов на складе.
     * Использованна рекурсия!
     *
     * @param computersCount
     * @throws InterruptedException
     */
    private void checkIfComputersAreAvailable(int computersCount) throws InterruptedException {
        if (this.computers < computersCount) {
            wait();
            checkIfComputersAreAvailable(computersCount);
        }
    }
}
