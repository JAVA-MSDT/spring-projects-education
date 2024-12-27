package com.clothesshop.web;

import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.repository.ClotheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClotheController {

    private final ClotheRepository clotheRepository;

    @GetMapping
    public String getAllUsers(Model model){
        List<Clothe> clothes = clotheRepository.findAll();
        model.addAttribute("clothes", clothes);
        model.addAttribute("module", "clothes");
        return "clothes";
    }
}
