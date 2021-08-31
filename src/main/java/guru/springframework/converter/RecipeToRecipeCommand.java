package guru.springframework.converter;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public RecipeToRecipeCommand(
            IngredientToIngredientCommand ingredientToIngredientCommand,
            NotesToNotesCommand notesToNotesCommand,
            CategoryToCategoryCommand categoryToCategoryCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredientCommand -> recipeCommand
                    .getIngredients().add(ingredientToIngredientCommand
                    .convert(ingredientCommand)));
        }

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(categoryCommand -> recipeCommand.getCategories().add(
                    categoryToCategoryCommand.convert(categoryCommand)));
        }

        return recipeCommand;
    }
}
