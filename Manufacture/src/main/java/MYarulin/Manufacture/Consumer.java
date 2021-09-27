package MYarulin.Manufacture;

/**
 *
 */
public class Consumer {
    //необходимое колличество полученых компов
    private static final int NEEDED_COMPUTERS = 10;
    //колличество полученых компов за цикл
    private static final int RECEIVE_COMPUTERS_COUNT = 2;
    //задержка пользователя на 7 секунд
    private static final long RECEIVE_COMPUTERS_TIME = 7000L;

    private int receivedComputers;
    //переменая для остановки потока
    private boolean isThreadShouldWork = true;

    /**
     * Метод запускает работу пользователя. Запускает поток и
     * вспомогательный метод  receiveComputersFromHolder.
     *
     * @param goodsHolder
     */
    public void startWork(FactoryGoodsHolder goodsHolder) {
        new Thread(() -> {
            while (isThreadShouldWork) {
                try {
                    receiveComputersFromHolder(goodsHolder);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Метод получает компы со склада. Если коллчество полученых компов
     * достигло "необходимое колличество полученых компов", поток останавливаеться.
     *
     * @param goodsHolder
     * @throws InterruptedException
     */
    private void receiveComputersFromHolder(FactoryGoodsHolder goodsHolder) throws InterruptedException {
        int receivedComputers = goodsHolder.receiveComputers(RECEIVE_COMPUTERS_COUNT);
        this.receivedComputers += receivedComputers;
        if (this.receivedComputers == NEEDED_COMPUTERS) {
            System.out.println(
                    "consumer stop with received " + this.receivedComputers
            );
            this.isThreadShouldWork = false;
            return;
        }
        System.out.println("sleep " + Thread.currentThread().getName());
        Thread.sleep(RECEIVE_COMPUTERS_TIME);
    }
}
