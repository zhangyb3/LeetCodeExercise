import java.util.function.IntConsumer;

public class MyIntConsumer implements IntConsumer {
    @Override
    public void accept(int value) {
        System.out.print(value);
    }
}
