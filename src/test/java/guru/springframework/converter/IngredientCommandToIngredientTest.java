package guru.springframework.converter;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    public static Long ID_VALUE = 1L;
    public static BigDecimal AMOUNT = new BigDecimal(1);
    public static String DESCRIPTION = "description";

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);

        final Ingredient ingredient = converter.convert(ingredientCommand);
        assertEquals(ingredient.getId(), ID_VALUE);
        assertEquals(ingredient.getAmount(), AMOUNT);
        assertEquals(ingredient.getDescription(), DESCRIPTION);
    }
}