package generic;
public class SimpleArrayListTest {
    public static void main(String[] args) {
        SimpleArrayList list = new SimpleArrayList();

        list.add(50);
        list.add(100);

        Integer value1 = (Integer) list.get(0);
        Integer value2 = (Integer) list.get(1);

        System.out.println(value1 + value2);
    }
}