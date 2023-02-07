package guru.springframework.sfgrecipes.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public void saveImage(Long recipeId, MultipartFile image) {
        System.out.println("Image received - ID: " + recipeId);
    }
}
