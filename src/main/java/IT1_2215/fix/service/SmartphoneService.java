package IT1_2215.fix.service;

import IT1_2215.fix.entity.Smartphone;
import IT1_2215.fix.repository.SmartphoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SmartphoneService {

    private final SmartphoneRepository smartphoneRepository;
    private static final String IMAGE_DIR = "src/main/resources/static/images/";

    // Получить все смартфоны
    public List<Smartphone> getAllSmartphones() {
        return smartphoneRepository.findAll();
    }

    // Получить смартфон по ID
    public Optional<Smartphone> getSmartphoneById(Long id) {
        return smartphoneRepository.findById(id);
    }

    // Сохранить смартфон (возвращает объект!)
    public Smartphone saveSmartphone(Smartphone smartphone) {
        return smartphoneRepository.save(smartphone);
    }

    // Сохранение смартфона с изображением
    public Smartphone saveSmartphone(Smartphone smartphone, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_DIR + fileName);
            try {
                Files.write(filePath, image.getBytes());
                smartphone.setImageUrl("/images/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка загрузки изображения");
            }
        }
        return smartphoneRepository.save(smartphone);
    }

    // Удалить смартфон
    @Transactional
    public void deleteSmartphone(Long id) {
        smartphoneRepository.deleteById(id);
    }
}
