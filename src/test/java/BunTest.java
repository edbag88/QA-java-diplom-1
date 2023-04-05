import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    private Bun bun;
    private final String testNameBun = "Вкусная-булочка";
    private final float testPriceBun = 1201.99f;

    @Before
    public void setUp() {
        bun = new Bun(testNameBun, testPriceBun);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals(testNameBun, bun.getName());
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals(testPriceBun, bun.getPrice(), 0);
    }
}
