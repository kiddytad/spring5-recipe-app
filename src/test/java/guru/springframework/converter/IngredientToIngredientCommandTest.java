package guru.springframework.converter;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientToIngredientCommandTest {

    public static Long ID_VALUE = 1L;
    public static BigDecimal AMOUNT = new BigDecimal(1);
    public static String DESCRIPTION = "description";
    IngredientToIngredientCommand converter;



    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }
    @Test
    void convert() {
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        IngredientCommand ingredientCommand = converter.convert(ingredient);
        assertEquals(ingredientCommand.getId(), ID_VALUE);
        assertEquals(ingredientCommand.getAmount(), AMOUNT);
        assertEquals(ingredientCommand.getDescription(), DESCRIPTION);

    }
}