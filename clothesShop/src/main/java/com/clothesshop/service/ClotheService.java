package com.clothesshop.service;

import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.model.clothe.SearchBy;
import com.clothesshop.repository.ClotheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class ClotheService {
    private final ClotheRepository clotheRepository;

    public Page<Clothe> getAllClothes(Pageable pageable) {
        return clotheRepository.findAll(pageable);
    }

    public Clothe getClotheById(Long id) {
        return findClotheById(id);
    }

    public void saveClothe(Clothe clothe) {
        clotheRepository.save(clothe);
    }

    public void deleteClothe(Long id) {
        Clothe clothe = findClotheById(id);
        clothe.getCustomers().forEach(customer -> customer.getClothes().remove(clothe));
        clotheRepository.delete(clothe);
    }

    public void addClotheToClothe(Long id, int amount) {
        Clothe clothe = findClotheById(id);
        clothe.setQuantityInStore(clothe.getQuantityInStore() + amount);
        clotheRepository.save(clothe);
    }

    public void removeClotheFromClothe(Long id, int amount) {
        Clothe clothe = findClotheById(id);
        int clothsInStore = clothe.getQuantityInStore();
        if (amount > clothsInStore) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    getRemoveClotheErrorMessage(clothe, amount));
        }
        clothe.setQuantityInStore(clothe.getQuantityInStore() - amount);
        clotheRepository.save(clothe);
    }

    public void updateClotheImage(Long id, String url) {
        Clothe clothe = findClotheById(id);
        clothe.setImages(url);
        clotheRepository.save(clothe);
    }

    public Page<Clothe> searchClothes(SearchBy searchBy, String keyword, Pageable pageable) {
        keyword = keyword.toUpperCase();
        return switch (searchBy) {
            case DESCRIPTION -> clotheRepository.findByDescriptionContainingIgnoreCase(keyword, pageable);
            case TYPE -> clotheRepository.findByCategoryContainingIgnoreCase(keyword, pageable);
            case FABRIC -> clotheRepository.findByFabricContainingIgnoreCase(keyword, pageable);
            case SIZE -> clotheRepository.findBySizeContainingIgnoreCase(keyword, pageable);
            case GENDER -> clotheRepository.findByGenderContainingIgnoreCase(keyword, pageable);
            case null -> clotheRepository.findByDescriptionContainingIgnoreCase(keyword, pageable);
        };
    }

    private Clothe findClotheById(Long id) {
        return clotheRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clothe Not found"));
    }

    private String getRemoveClotheErrorMessage(Clothe clothe, int amount) {
        return "Clothe (" +
                clothe.getCategory() + ", " + clothe.getFabric() + ", " + clothe.getAgeType() + ", "
                + clothe.getGender() + ") in the store are less than the amount you asking for, Clothes in the store = "
                + clothe.getQuantityInStore() + ", Amount you are asking = " + amount + ".";
    }

}
