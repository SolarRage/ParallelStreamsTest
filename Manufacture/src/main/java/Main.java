import MYarulin.Manufacture.Consumer;
import MYarulin.Manufacture.Factory;
import MYarulin.Manufacture.FactoryGoodsHolder;


public class Main {

    public static void main(String[] args) {
        FactoryGoodsHolder goodsHolder = new FactoryGoodsHolder();//создали склад

        Factory factory = new Factory();//создали фабрику
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();

        factory.startWork(goodsHolder);
        consumer1.startWork(goodsHolder);
        consumer2.startWork(goodsHolder);
    }

}
