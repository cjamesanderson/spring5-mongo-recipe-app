package guru.springframework.sfgrecipes.services;

import guru.springframework.sfgrecipes.domain.Recipe;
import guru.springframework.sfgrecipes.repositories.reactive.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeReactiveRepository recipeReactiveRepository;

    public ImageServiceImpl(RecipeReactiveRepository recipeReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile file) {

        Mono<Recipe> recipeMono = recipeReactiveRepository.findById(recipeId)
                .map(recipe -> {
                    Byte[] bytesObjects = new Byte[0];
                    try {
                        bytesObjects = new Byte[file.getBytes().length];

                        int i = 0;

                        for (byte b : file.getBytes()) {
                            bytesObjects[i++] = b;
                        }

                        recipe.setImage(bytesObjects);

                        return recipe;

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        recipeReactiveRepository.save(recipeMono.block()).block();

        return Mono.empty();

//        try {
//            Optional<Recipe> recipeOptional = recipeReactiveRepository.findById(recipeId).blockOptional();
//            if (recipeOptional.isPresent()) {
//                Recipe recipe = recipeOptional.get();
//
//                Byte[] byteObjects = new Byte[file.getBytes().length];
//
//                int i = 0;
//
//                for (byte b : file.getBytes()) {
//                    byteObjects[i++] = b;
//                }
//
//                recipe.setImage(byteObjects);
//
//                recipeReactiveRepository.save(recipe).block();
//            } else {
//                //todo: error handling
//                System.out.println("LOGGING: Recipe not found: ID - " + recipeId);
//            }
//        } catch (IOException e) {
//            //todo: error handling
//            System.out.println("LOGGING: Error occurred: " + e);
//
//            e.printStackTrace();
//        }
//        return null;
    }
}
