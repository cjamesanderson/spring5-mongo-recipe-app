package guru.springframeword.sfgrecipes.bootstrap;

import guru.springframeword.sfgrecipes.domain.*;
import guru.springframeword.sfgrecipes.repositories.CategoryRepository;
import guru.springframeword.sfgrecipes.repositories.RecipeRepository;
import guru.springframeword.sfgrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.print("Loading data from bootstrap...");

        //load units of measure
        if (unitOfMeasureRepository.findByDescription("Tablespoon").isEmpty() ||
                unitOfMeasureRepository.findByDescription("Teaspoon").isEmpty() ||
                unitOfMeasureRepository.findByDescription("Clove").isEmpty() ||
                unitOfMeasureRepository.findByDescription("Each").isEmpty() ||
                unitOfMeasureRepository.findByDescription("Cup").isEmpty() ||
                unitOfMeasureRepository.findByDescription("Pint").isEmpty() ||
                unitOfMeasureRepository.findByDescription("Pinch").isEmpty())
            throw new RuntimeException("Expected UOM not found");

        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure clove = unitOfMeasureRepository.findByDescription("Clove").get();
        UnitOfMeasure each = unitOfMeasureRepository.findByDescription("Each").get();
        UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pint = unitOfMeasureRepository.findByDescription("Pint").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();

        //load categories
        if (categoryRepository.findByDescription("American").isEmpty() ||
            categoryRepository.findByDescription("Mexican").isEmpty())
            throw new RuntimeException("Expected category not found");

        Category americanCategory = categoryRepository.findByDescription("American").get();
        Category mexicanCategory = categoryRepository.findByDescription("Mexican").get();

        //begin first recipe
        Recipe spicyGrilledChickenTacos = new Recipe();

        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setCookTime(15);
        spicyGrilledChickenTacos.setServings(6);
        spicyGrilledChickenTacos.getCategories().add(americanCategory);
        spicyGrilledChickenTacos.getCategories().add(mexicanCategory);

        spicyGrilledChickenTacos.addIngredient(new Ingredient("ancho chili powder", BigDecimal.valueOf(2), tablespoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("dried oregano", BigDecimal.valueOf(1), teaspoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("dried cumin", BigDecimal.valueOf(1), teaspoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("sugar", BigDecimal.valueOf(1), teaspoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("kosher salt", BigDecimal.valueOf(0.5), teaspoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("garlic, finely chopped", BigDecimal.valueOf(1), clove));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("finely grated orange zest", BigDecimal.valueOf(1), tablespoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("fresh-squeezed orange juice", BigDecimal.valueOf(3), tablespoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("olive oil", BigDecimal.valueOf(2), tablespoon));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", BigDecimal.valueOf(6), each));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("small corn tortillas", BigDecimal.valueOf(8), each));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("packed baby arugula (3 ounces)", BigDecimal.valueOf(3), cup));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("medium ripe avocados, sliced", BigDecimal.valueOf(2), each));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("radishes, thinly slice", BigDecimal.valueOf(4), each));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("cherry tomatoes, halved", BigDecimal.valueOf(0.5), pint));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("red onion, thinly sliced", BigDecimal.valueOf(0.25), each));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("roughly chopped cilantro", BigDecimal.valueOf(1), each));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("sour cream", BigDecimal.valueOf(0.5), cup));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("milk", BigDecimal.valueOf(0.25), cup));
        spicyGrilledChickenTacos.addIngredient(new Ingredient("lime, cut into wedges", BigDecimal.valueOf(1), each));

        spicyGrilledChickenTacos.setDirections("1. Prepare the grill:\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat.\n\n" +
                "2. Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest." +
                " Stir in the orange juice and olive oil to make a loose past. Add the chicken to the bowl and toss to" +
                " coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings\n\n" +
                "3. Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part" +
                " of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.\n\n" +
                "4. Thin the sour cream with milk:\n" +
                "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.\n\n" +
                "5. Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken " +
                "slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. " +
                "Serve with lime wedges.\n\n" +
                "6. Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see " +
                "pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on " +
                "the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.");

        spicyGrilledChickenTacos.setNotes(new Notes("Look for ancho chile powder with the Mexican ingredients at " +
                "your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho " +
                "chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor " +
                "won't be quite the same.)"));

        recipeRepository.save(spicyGrilledChickenTacos);

        //begin second test recipe
        Recipe bestGuacamole = new Recipe();

        bestGuacamole.setDescription("The Best Guacamole");
        bestGuacamole.getCategories().add(americanCategory);
        bestGuacamole.getCategories().add(mexicanCategory);
        bestGuacamole.setPrepTime(10);
        bestGuacamole.setCookTime(10);
        bestGuacamole.setServings(4);
        bestGuacamole.setDifficulty(Difficulty.EASY);

        bestGuacamole.setNotes(new Notes("be careful handling chilis! if using, it's best to wear food-safe gloves. " +
                "if no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or" +
                " the area near your eyes for several hours afterwards.\n" +
                "chilling tomatoes hurts their flavor. so, if you want to add chopped tomato to your guacamole, add it " +
                "just before serving. "));

        bestGuacamole.addIngredient(new Ingredient("ripe avocados", BigDecimal.valueOf(2), each));
        bestGuacamole.addIngredient(new Ingredient("kosher salt, plus more to taste", BigDecimal.valueOf(0.25), teaspoon));
        bestGuacamole.addIngredient(new Ingredient("fresh lime or lemon juice", BigDecimal.valueOf(1), tablespoon));
        bestGuacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", BigDecimal.valueOf(4), tablespoon));
        bestGuacamole.addIngredient(new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", BigDecimal.valueOf(2), each));
        bestGuacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", BigDecimal.valueOf(2), tablespoon));
        bestGuacamole.addIngredient(new Ingredient("freshly ground black pepper", BigDecimal.valueOf(1), pinch));
        bestGuacamole.addIngredient(new Ingredient("ripe tomato, chopped (optional)", BigDecimal.valueOf(0.5), each));
        bestGuacamole.addIngredient(new Ingredient("red radish or jicama slices for garnish (optional)", BigDecimal.valueOf(1), each));
        bestGuacamole.addIngredient(new Ingredient("tortilla chips, to serve", BigDecimal.valueOf(1), each));

        bestGuacamole.setDirections("1. Cut the avocados:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and " +
                "scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n\n" +
                "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.\n\n" +
                "3. Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to" +
                " the richness of the avocado and will help delay the avocados from turning brown.\n" +
                " Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their" +
                " spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired " +
                "degree of heat.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste.\n\n" +
                "4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to " +
                "cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the " +
                "guacamole brown.)\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla" +
                " chips or make your own homemade tortilla chips.\n" +
                "Refrigerate leftover guacamole up to 3 days. ");

        recipeRepository.save(bestGuacamole);

        System.out.println("loaded");

    }
}
