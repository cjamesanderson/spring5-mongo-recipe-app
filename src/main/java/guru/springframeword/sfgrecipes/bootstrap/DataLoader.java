package guru.springframeword.sfgrecipes.bootstrap;

import guru.springframeword.sfgrecipes.domain.*;
import guru.springframeword.sfgrecipes.repositories.CategoryRepository;
import guru.springframeword.sfgrecipes.repositories.RecipeRepository;
import guru.springframeword.sfgrecipes.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

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

        Recipe spicyGrilledChickenTacos = new Recipe();

        spicyGrilledChickenTacos.setDescription("Spicy Grilled Chicken Tacos");

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        spicyGrilledChickenTacos.getCategories().add(americanCategoryOptional.get());
        spicyGrilledChickenTacos.getCategories().add(mexicanCategoryOptional.get());

        spicyGrilledChickenTacos.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacos.setPrepTime(20);
        spicyGrilledChickenTacos.setCookTime(15);
        spicyGrilledChickenTacos.setServings(6);

        UnitOfMeasure tablespoon = unitOfMeasureRepository.findByDescription("Tablespoon").get();
        UnitOfMeasure teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon").get();
        UnitOfMeasure clove = unitOfMeasureRepository.findByDescription("Clove").get();
        UnitOfMeasure each = unitOfMeasureRepository.findByDescription("Each").get();
        UnitOfMeasure cup = unitOfMeasureRepository.findByDescription("Cup").get();
        UnitOfMeasure pint = unitOfMeasureRepository.findByDescription("Pint").get();
        UnitOfMeasure pinch = unitOfMeasureRepository.findByDescription("Pinch").get();

        Ingredient chiliPowder = new Ingredient();
        chiliPowder.setAmount(BigDecimal.valueOf(2));
        chiliPowder.setUom(tablespoon);
        chiliPowder.setDescription("ancho chili powder");
        chiliPowder.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(chiliPowder);

        Ingredient driedOregano = new Ingredient();
        driedOregano.setAmount(BigDecimal.valueOf(1));
        driedOregano.setUom(teaspoon);
        driedOregano.setDescription("dried oregano");
        driedOregano.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(driedOregano);

        Ingredient driedCumin = new Ingredient();
        driedCumin.setAmount(BigDecimal.valueOf(1));
        driedCumin.setUom(teaspoon);
        driedCumin.setDescription("dried cumin");
        driedCumin.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(driedCumin);

        Ingredient sugar = new Ingredient();
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setUom(teaspoon);
        sugar.setDescription("sugar");
        sugar.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(sugar);

        Ingredient kosherSalt = new Ingredient();
        kosherSalt.setAmount(BigDecimal.valueOf(0.5));
        kosherSalt.setUom(teaspoon);
        kosherSalt.setDescription("kosher salt");
        kosherSalt.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(kosherSalt);

        Ingredient garlic = new Ingredient();
        garlic.setAmount(BigDecimal.valueOf(1));
        garlic.setUom(clove);
        garlic.setDescription("garlic, finely chopped");
        garlic.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(garlic);

        Ingredient orangeZest = new Ingredient();
        orangeZest.setAmount(BigDecimal.valueOf(1));
        orangeZest.setUom(tablespoon);
        orangeZest.setDescription("finely grated orange zest");
        orangeZest.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(orangeZest);

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setAmount(BigDecimal.valueOf(3));
        orangeJuice.setUom(tablespoon);
        orangeJuice.setDescription("fresh-squeezed orange juice");
        orangeJuice.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(orangeJuice);

        Ingredient oliveOil = new Ingredient();
        oliveOil.setAmount(BigDecimal.valueOf(2));
        oliveOil.setUom(tablespoon);
        oliveOil.setDescription("olive oil");
        oliveOil.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(oliveOil);

        Ingredient chicken = new Ingredient();
        chicken.setAmount(BigDecimal.valueOf(6));
        chicken.setUom(each);
        chicken.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
        chicken.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(chicken);

        Ingredient tortillas = new Ingredient();
        tortillas.setAmount(BigDecimal.valueOf(8));
        tortillas.setUom(each);
        tortillas.setDescription("small corn tortillas");
        tortillas.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(tortillas);

        Ingredient babyArugula = new Ingredient();
        babyArugula.setAmount(BigDecimal.valueOf(3));
        babyArugula.setUom(cup);
        babyArugula.setDescription("packed baby arugula (3 ounces)");
        babyArugula.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(babyArugula);

        Ingredient avocados = new Ingredient();
        avocados.setAmount(BigDecimal.valueOf(2));
        avocados.setUom(each);
        avocados.setDescription("medium ripe avocados, sliced");
        avocados.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(avocados);

        Ingredient radishes = new Ingredient();
        radishes.setAmount(BigDecimal.valueOf(4));
        radishes.setUom(each);
        radishes.setDescription("radishes, thinly sliced");
        radishes.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(radishes);

        Ingredient cherryTomatoes = new Ingredient();
        cherryTomatoes.setAmount(BigDecimal.valueOf(0.5));
        cherryTomatoes.setUom(pint);
        cherryTomatoes.setDescription("cherry tomatoes, halved");
        cherryTomatoes.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(cherryTomatoes);

        Ingredient redOnion = new Ingredient();
        redOnion.setAmount(BigDecimal.valueOf(0.25));
        redOnion.setUom(each);
        redOnion.setDescription("red onion, thinly sliced");
        redOnion.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(redOnion);

        Ingredient cilantro = new Ingredient();
        cilantro.setAmount(BigDecimal.valueOf(1));
        cilantro.setUom(each);
        cilantro.setDescription("roughly chopped cilantro");
        cilantro.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(cilantro);

        Ingredient sourCream = new Ingredient();
        sourCream.setAmount(BigDecimal.valueOf(0.5));
        sourCream.setUom(cup);
        sourCream.setDescription("sour cream");
        sourCream.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(sourCream);

        Ingredient milk = new Ingredient();
        milk.setAmount(BigDecimal.valueOf(0.25));
        milk.setUom(cup);
        milk.setDescription("milk");
        milk.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(milk);

        Ingredient lime = new Ingredient();
        lime.setAmount(BigDecimal.valueOf(1));
        lime.setUom(each);
        lime.setDescription("lime, cut into wedges");
        lime.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.getIngredients().add(lime);

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

        Notes spicyGrilledChickenTacosNotes = new Notes();
        spicyGrilledChickenTacosNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at " +
                "your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho " +
                "chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor " +
                "won't be quite the same.)");
        spicyGrilledChickenTacosNotes.setRecipe(spicyGrilledChickenTacos);
        spicyGrilledChickenTacos.setNotes(spicyGrilledChickenTacosNotes);

        recipeRepository.save(spicyGrilledChickenTacos);

        Recipe bestGuacamole = new Recipe();

        bestGuacamole.setDescription("The Best Guacamole");

        bestGuacamole.getCategories().add(americanCategoryOptional.get());
        bestGuacamole.getCategories().add(mexicanCategoryOptional.get());
        bestGuacamole.setPrepTime(10);
        bestGuacamole.setCookTime(10);
        bestGuacamole.setServings(4);

        Notes bestGuacamoleNotes = new Notes();
        bestGuacamoleNotes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. " +
                "If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or" +
                " the area near your eyes for several hours afterwards.\n" +
                "Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it " +
                "just before serving. ");
        bestGuacamoleNotes.setRecipe(bestGuacamole);
        bestGuacamole.setNotes(bestGuacamoleNotes);

        bestGuacamole.setDifficulty(Difficulty.EASY);

        Ingredient ripeAvocados = new Ingredient();
        ripeAvocados.setAmount(BigDecimal.valueOf(2));
        ripeAvocados.setUom(each);
        ripeAvocados.setDescription("ripe avocados");
        ripeAvocados.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(ripeAvocados);

        Ingredient kSalt = new Ingredient();
        kSalt.setAmount(BigDecimal.valueOf(0.25));
        kSalt.setUom(teaspoon);
        kSalt.setDescription("kosher salt, plus more to taste");
        kSalt.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(kSalt);

        Ingredient limeJuice = new Ingredient();
        limeJuice.setAmount(BigDecimal.valueOf(1));
        limeJuice.setUom(tablespoon);
        limeJuice.setDescription("fresh lime or lemon juice");
        limeJuice.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(limeJuice);

        Ingredient greenOnion = new Ingredient();
        greenOnion.setAmount(BigDecimal.valueOf(4));
        greenOnion.setUom(tablespoon);
        greenOnion.setDescription("minced red onion or thinly sliced green onion");
        greenOnion.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(greenOnion);

        Ingredient serranoChilis = new Ingredient();
        serranoChilis.setAmount(BigDecimal.valueOf(2));
        serranoChilis.setUom(each);
        serranoChilis.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        serranoChilis.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(serranoChilis);

        Ingredient cilantroChopped = new Ingredient();
        cilantroChopped.setAmount(BigDecimal.valueOf(2));
        cilantroChopped.setUom(tablespoon);
        cilantroChopped.setDescription("cilantro (leaves and tender stems), finely chopped");
        cilantroChopped.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(cilantroChopped);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setAmount(BigDecimal.valueOf(1));
        blackPepper.setUom(pinch);
        blackPepper.setDescription("freshly ground black pepper");
        blackPepper.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(blackPepper);

        Ingredient tomato = new Ingredient();
        tomato.setAmount(BigDecimal.valueOf(0.5));
        tomato.setUom(each);
        tomato.setDescription("ripe tomato, chopped (optional)");
        tomato.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(tomato);

        Ingredient jicama = new Ingredient();
        jicama.setAmount(BigDecimal.valueOf(1));
        jicama.setUom(each);
        jicama.setDescription("red radish or jicama slices for garnish (optional)");
        jicama.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(jicama);

        Ingredient tortillaChips = new Ingredient();
        tortillaChips.setAmount(BigDecimal.valueOf(1));
        tortillaChips.setUom(each);
        tortillaChips.setDescription("tortilla chips, to serve");
        tortillaChips.setRecipe(bestGuacamole);
        bestGuacamole.getIngredients().add(tortillaChips);

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

        System.out.println("loaded");

        recipeRepository.save(bestGuacamole);
    }
}
