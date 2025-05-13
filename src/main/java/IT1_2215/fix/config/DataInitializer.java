package IT1_2215.fix.config;

import IT1_2215.fix.entity.Smartphone;
import IT1_2215.fix.repository.SmartphoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Override
    public void run(String... args) throws Exception {
        // Adding Samsung Galaxy S24 series smartphones and higher
        addSmartphone("Samsung Galaxy S24", "Samsung Galaxy S24 with improved camera", "Android", "128GB", "8GB", "108MP", 50, new BigDecimal("999.99"), new BigDecimal("49999.50"), "image_url_s24");
        addSmartphone("Samsung Galaxy S25", "Samsung Galaxy S25 with improved performance", "Android", "256GB", "12GB", "108MP", 70, new BigDecimal("1099.99"), new BigDecimal("76999.30"), "image_url_s25");

        // Flagship model Samsung Galaxy S24 Ultra
        addSmartphone("Samsung Galaxy S24 Ultra", "Flagship Samsung Galaxy S24 Ultra with 200MP camera", "Android", "512GB", "16GB", "200MP", 30, new BigDecimal("1399.99"), new BigDecimal("41999.70"), "image_url_s24_ultra");

        // Flagship model Samsung Galaxy S25 Ultra
        addSmartphone("Samsung Galaxy S25 Ultra", "Samsung Galaxy S25 Ultra with 200MP camera and 120Hz display", "Android", "1TB", "16GB", "200MP", 40, new BigDecimal("1599.99"), new BigDecimal("63999.90"), "image_url_s25_ultra");

        // Adding iPhone 15 series smartphones and higher
        addSmartphone("iPhone 15", "iPhone 15 with A16 chip", "iOS", "128GB", "6GB", "48MP", 40, new BigDecimal("999.99"), new BigDecimal("39999.60"), "image_url_iphone_15");
        addSmartphone("iPhone 16", "iPhone 16 with revolutionary new features", "iOS", "256GB", "8GB", "64MP", 60, new BigDecimal("1199.99"), new BigDecimal("71999.40"), "image_url_iphone_16");

        // Flagship model iPhone 15 Pro
        addSmartphone("iPhone 15 Pro", "iPhone 15 Pro with A16 chip and improved 48MP camera", "iOS", "512GB", "8GB", "48MP", 25, new BigDecimal("1399.99"), new BigDecimal("34999.75"), "image_url_iphone_15_pro");

        // Flagship model iPhone 16 Pro
        addSmartphone("iPhone 16 Pro", "iPhone 16 Pro with new features and improved 64MP camera", "iOS", "1TB", "12GB", "64MP", 35, new BigDecimal("1599.99"), new BigDecimal("55999.50"), "image_url_iphone_16_pro");
    }


    private void addSmartphone(String model, String description, String os, String storage, String ram, String camera, Integer quantity, BigDecimal price, BigDecimal total, String imageUrl) {
        Smartphone smartphone = new Smartphone();
        smartphone.setModel(model);
        smartphone.setDescription(description);
        smartphone.setOs(os);
        smartphone.setStorage(storage);
        smartphone.setRam(ram);
        smartphone.setCamera(camera);
        smartphone.setQuantity(quantity);
        smartphone.setPrice(price);
        smartphone.setTotal(total);
        smartphone.setImageUrl(imageUrl);
        smartphone.setCreatedAt(LocalDateTime.now());
        smartphone.setUpdatedAt(LocalDateTime.now());

        smartphoneRepository.save(smartphone);
    }
}
