package com.clothesshop.service;

import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.repository.ClotheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClotheService {
    private final ClotheRepository clotheRepository;

    public List<Clothe> getAllClothes() {
        return clotheRepository.findAll();
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

    private Clothe findClotheById(Long id) {
        return clotheRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clothe Not found"));
    }

    private String getRemoveClotheErrorMessage(Clothe clothe, int amount) {
        return "Clothe (" +
                clothe.getClotheType() + ", " + clothe.getFabric() + ", " + clothe.getAgeType() + ", "
                + clothe.getGender() + ") in the store are less than the amount you asking for, Clothes in the store = "
                + clothe.getQuantityInStore() + ", Amount you are asking = " + amount + ".";
    }
}
