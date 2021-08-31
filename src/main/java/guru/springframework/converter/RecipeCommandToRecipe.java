package guru.springframework.converter;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,NotesCommandToNotes notesCommandToNotes, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setSource(source.getSource());
        recipe.setServings(source.getServings());
        recipe.setDescription(source.getDescription());
        recipe.setUrl(source.getUrl());
        recipe.setCookTime(source.getCookTime());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setImage(source.getImage());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient -> recipe.getIngredients().add(
                    ingredientCommandToIngredient.convert(ingredient)));
        }
        return recipe;
    }
}
