package softuni.burgerbox.service;

import org.springframework.web.multipart.MultipartFile;
import softuni.burgerbox.model.entity.PictureEntity;
import softuni.burgerbox.model.service.PictureServiceModel;

import java.io.IOException;

public interface PictureService {
    PictureServiceModel savePicture(MultipartFile multipartFile) throws IOException;
    void deletePicture(String tempPublicId, Long picId);

    void initializePictures();

    PictureEntity findPictureByIt(Long id);
}
