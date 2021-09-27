package MYarulin.Manufacture;

/**
 *
 */
public class Factory {
    //необходимое колличество произведенных компов
    private static final int NEEDED_COMPUTERS = 21;
    //колличество произведенных компов за цикл производства
    private static final int PRODUCED_COMPUTERS_COUNT = 3;
    //задержка работы фабрики 5 секунд
    private static final long PRODUCED_COMPUTERS_TIME = 5000L;

    private int producedComputers;
    //переменая для остановки потока
    private boolean isThreadShouldWork = true;

    /**
     * Метод заполнения склада компами. Раз в 5 секунд производит 3 компа.
     * Отправляет их на склад (класс FactoryGoodsHolder).
     * Если колличество произведенных компов равно необходимому колличеству произведенных компов
     * то поток отанавливаеться.
     *
     * @param goodsHolder
     */
    public void startWork(FactoryGoodsHolder goodsHolder) {
        new Thread(() -> {
            while (isThreadShouldWork) {
                goodsHolder.putComputers(PRODUCED_COMPUTERS_COUNT);
                this.producedComputers += PRODUCED_COMPUTERS_COUNT;
                if (this.producedComputers >= NEEDED_COMPUTERS) {
                    System.out.println(
                            "factory stop with produced " + this.producedComputers
                    );
                    this.isThreadShouldWork = false;
                    return;
                }
                try {
                    Thread.sleep(PRODUCED_COMPUTERS_TIME);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }).start();
    }
}
