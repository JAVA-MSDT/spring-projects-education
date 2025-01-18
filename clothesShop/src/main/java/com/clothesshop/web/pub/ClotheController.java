package com.clothesshop.web.pub;

import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.service.ClotheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClotheController {

    private final ClotheService clotheService;
    @Value("${images.folder}")
    private String imagesFolder;

    @GetMapping
    public String getAllClothes(Model model) {
        List<Clothe> clothes = clotheService.getAllClothes();
        model.addAttribute("clothes", clothes);
        model.addAttribute("module", "clothes");
        return "public/clothes";
    }

    @GetMapping("/{id}")
    public String getClotheById(@PathVariable long id, Model model) {
        Clothe clothe = clotheService.getClotheById(id);
        model.addAttribute("clothe", clothe);
        return "public/clothe_details";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addClothePage(Model model) {
        Clothe clothe = new Clothe();
        model.addAttribute("clothe", clothe);
        return "private/add_update_clothe";
    }

    @PostMapping("/save")
    public String saveClothe(@ModelAttribute("clothe") Clothe clothe, RedirectAttributes redirectAttributes) {
        if (clothe.getId() == null) {
            clotheService.saveClothe(clothe);
            redirectAttributes.addFlashAttribute("message", "Would you like to add more clothes?");
            return "redirect:/clothes/add";
        } else {
            clotheService.saveClothe(clothe);
            return "redirect:/clothes/" + clothe.getId();
        }
    }

    @GetMapping("/update/{id}")
    public String updateClothePage(@PathVariable(value = "id") long id, Model model) {
        Clothe clothe = clotheService.getClotheById(id);
        model.addAttribute("clothe", clothe);
        return "private/add_update_clothe";
    }

    @GetMapping("/delete/{id}")
    public String deleteClothe(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        try {
            clotheService.deleteClothe(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "DataIntegrityViolationException: Item with id " + id + " already used somewhere in the system");
            return "redirect:/clothes";
        }
        redirectAttributes.addFlashAttribute("deleteMessage", "Deleted Clothe with id: " + id);
        return "redirect:/clothes";
    }

    @PostMapping("/update-clothe-image")
    public String updateClotheImage(@RequestParam("id") Long id, @RequestParam("clotheImage") MultipartFile clotheImage) throws IOException {
        String imageName = clotheImage.getOriginalFilename();
        Path imagePath = Paths.get(imagesFolder + imageName);
        Files.createDirectories(imagePath.getParent());
        clotheImage.transferTo(imagePath.toFile());

        String imageUrl = "/images/clothes/" + imageName;
        clotheService.updateClotheImage(id, imageUrl);
        return "redirect:/clothes/" + id;
    }

}
