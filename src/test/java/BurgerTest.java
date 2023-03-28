import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Before
    public void setUp() {
        burger = new Burger();
    }
    @Mock
    private Bun bun;
    @Mock
    private List<Bun> buns = new ArrayList<>();
    @Mock
    private Ingredient ingredient;
    @Mock
    private List<Ingredient> ingredients = new ArrayList<>();
    @Mock
    private Database db;
    @Test
    public void addIngredientTest(){
        burger.addIngredient(ingredient);
        int expectedSize = 1;
        int actualSize = burger.ingredients.size();
        assertEquals("Неверное количество ингредиентов!",expectedSize, actualSize);

    }
    @Test
    public void removeIngredientTest(){
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        int expectedSize = 0;
        int actualSize = burger.ingredients.size();
        assertEquals("Не удалось удалить ингредиент!", expectedSize, actualSize);
    }
    @Test
    public void moveIngredientTest(){
        burger.addIngredient(ingredient);
        int currentIndex = burger.ingredients.indexOf(ingredient);
        int newIndex = 0;
        burger.moveIngredient(currentIndex, newIndex);
        assertEquals("Не удалось переместить ингредиент!", burger.ingredients.size(), 1);
        assertEquals(burger.ingredients.indexOf(ingredient), newIndex);
    }
    @Test
    public void getPriceTest(){
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(300f);
        float expectedPrice = 500;
        float actualPrice = burger.getPrice();
        assertEquals("Некорректная цена бургера!",expectedPrice, actualPrice, 0);
    }
    @Test
    public void getReceiptTest(){
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("red bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.addIngredient(ingredient);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getPrice()).thenReturn(300f);
        String expectedReceipt = "(==== red bun ====)\r\n= filling cutlet =\r\n(==== red bun ====)\r\n\r\nPrice: 500,000000\r\n";
        String actualReceipt = burger.getReceipt();
        assertEquals("Состав рецепта неверный!", expectedReceipt, actualReceipt);
    }
    @Test
    public void databaseTest() {
        buns.add(new Bun("black bun", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "galaxy sauce", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "beef", 300));
        assertNotNull("Булочки отсутствуют", db.availableBuns());
        assertNotNull("Ингредиенты не доступны!", db.availableIngredients());
    }
}
