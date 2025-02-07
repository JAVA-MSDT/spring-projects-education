package com.clothesshop.web.pub;

import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.model.clothe.SearchBy;
import com.clothesshop.service.ClotheService;
import com.clothesshop.util.ResourcesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClotheController {

    private final ClotheService clotheService;
    @Value("${clothes.images.folder}")
    private String clothesImagesFolder;

    @GetMapping()
    public String getAllClothesPag(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy,
                                   @RequestParam(defaultValue = "asc") String sortDir,
                                   @RequestParam(required = false) String search,
                                   @RequestParam(required = false) SearchBy searchBy,
                                   Model model) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        System.out.println(searchBy);
        Page<Clothe> clothePage = clotheService.getAllClothes(pageable);
        if (search != null) {
            clothePage = clotheService.searchClothes(searchBy, search, pageable);
        }
        model.addAttribute("clothes", clothePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", clothePage.getTotalPages());
        model.addAttribute("totalItems", clothePage.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("search", search);
        model.addAttribute("searchBy", searchBy);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateClotheImage(@RequestParam("id") Long id, @RequestParam("clotheImage") MultipartFile clotheImage) throws IOException {
        String imageName = ResourcesUtil.saveImageToFolder(clotheImage, clothesImagesFolder);

        String imageUrl = "/images/clothes/" + imageName;
        clotheService.updateClotheImage(id, imageUrl);
        return "redirect:/clothes/" + id;
    }

}
