package IT1_2215.fix.controller;

import IT1_2215.fix.entity.Smartphone;
import IT1_2215.fix.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/smartphones")
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    // 🎯 Веб-интерфейс (Thymeleaf)
    @GetMapping
    public String listSmartphones(Model model) {
        model.addAttribute("smartphones", smartphoneService.getAllSmartphones());
        return "list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("smartphone", new Smartphone());
        return "form";
    }

    @PostMapping
    public String saveSmartphone(@ModelAttribute("smartphone") Smartphone smartphone) {
        smartphoneService.saveSmartphone(smartphone);
        return "redirect:/smartphones";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Smartphone smartphone = smartphoneService.getSmartphoneById(id).orElseThrow();
        model.addAttribute("smartphone", smartphone);
        return "form";
    }

    @PostMapping("/{id}")
    public String updateSmartphone(@PathVariable Long id, @ModelAttribute Smartphone smartphone) {
        smartphone.setId(id);
        smartphoneService.saveSmartphone(smartphone);
        return "redirect:/smartphones";
    }

    @GetMapping("/{id}/delete")
    public String deleteSmartphone(@PathVariable Long id) {
        smartphoneService.deleteSmartphone(id);
        return "redirect:/smartphones";
    }

    // 🎯 API JSON-интерфейс
    @RestController
    @RequestMapping("/api/smartphones")
    public static class SmartphoneApiController {

        @Autowired
        private SmartphoneService smartphoneService;

        // Получить все смартфоны (JSON)
        @GetMapping
        public List<Smartphone> getAllSmartphones() {
            return smartphoneService.getAllSmartphones();
        }

        // Получить смартфон по ID (JSON)
        @GetMapping("/{id}")
        public ResponseEntity<Smartphone> getSmartphoneById(@PathVariable Long id) {
            Optional<Smartphone> smartphone = smartphoneService.getSmartphoneById(id);
            return smartphone.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Создать новый смартфон (JSON)
        @PostMapping
        public ResponseEntity<Smartphone> saveSmartphoneApi(@RequestBody Smartphone smartphone) {
            Smartphone savedSmartphone = smartphoneService.saveSmartphone(smartphone);
            return new ResponseEntity<>(savedSmartphone, HttpStatus.CREATED);
        }

        // Удалить смартфон по ID (JSON)
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSmartphoneApi(@PathVariable Long id) {
            smartphoneService.deleteSmartphone(id);
            return ResponseEntity.noContent().build();
        }
    }
}
