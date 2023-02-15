package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(String recipeId, MultipartFile file) {
        try {
            Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
            if (recipeOptional.isPresent()) {
                Recipe recipe = recipeOptional.get();

                Byte[] byteObjects = new Byte[file.getBytes().length];

                int i = 0;

                for (byte b : file.getBytes()) {
                    byteObjects[i++] = b;
                }

                recipe.setImage(byteObjects);

                recipeRepository.save(recipe);
            } else {
                //todo: error handling
                System.out.println("LOGGING: Recipe not found: ID - " + recipeId);
            }
        } catch (IOException e) {
            //todo: error handling
            System.out.println("LOGGING: Error occurred: " + e);

            e.printStackTrace();
        }
    }
}
