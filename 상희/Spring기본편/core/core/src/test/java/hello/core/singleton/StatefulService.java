package hello.core.singleton;

// 중요
public class StatefulService {

//    private int price; // 상태를 유지하는 필드 10000 -> 20000
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price;
        return price; //  이런씩으로 하면 필드가 공유 되지 않는다. 매우 중요하다
    }

//    public int getPrice() {
//        return price;
//    }
}
