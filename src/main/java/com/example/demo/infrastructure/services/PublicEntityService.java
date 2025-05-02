package com.example.demo.infrastructure.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.schemas.PublicEntity;
import com.example.demo.infrastructure.repositories.PublicEntityRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PublicEntityService {

    private final PublicEntityRepository publicEntityRepository;

    public PublicEntityService(PublicEntityRepository publicEntityRepository) {
        this.publicEntityRepository = publicEntityRepository;
    }

    public List<PublicEntity> getAll(Long userId) {
        return publicEntityRepository.findByUserOwned_Id(userId);
    }

    public void save(PublicEntity publicEntity, MultipartFile imageFile) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());

        // Si la imagen no es 400x400, la redimensionamos
        if (bufferedImage.getWidth() != 400 || bufferedImage.getHeight() != 400) {
            bufferedImage = resizeImage(bufferedImage, 400, 400);
        }

        // Convertimos la imagen a bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        publicEntity.setImage(baos.toByteArray());

        publicEntityRepository.save(publicEntity);
    }

    public void delete(Long id) {
        publicEntityRepository.deleteById(id);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }
}
