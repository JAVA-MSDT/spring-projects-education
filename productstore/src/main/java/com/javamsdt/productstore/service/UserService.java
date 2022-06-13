package com.javamsdt.productstore.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.javamsdt.productstore.model.Image;
import com.javamsdt.productstore.model.Product;
import com.javamsdt.productstore.model.User;
import com.javamsdt.productstore.repository.ImageRepository;
import com.javamsdt.productstore.repository.ProductRepository;
import com.javamsdt.productstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User saveUser(User user) {
        saveOrUpdateUserImage(user);
        saveOrUpdateUserProduct(user);

        return userRepository.save(user);
    }

    private void saveOrUpdateUserProduct(User user) {
        List<Product> newProducts = user.getProducts()
                .stream()
                .filter(product -> product.getProductId() == 0)
                .collect(Collectors.toList());
        if (!newProducts.isEmpty()) {
            productRepository.saveAll(newProducts);
        }
        user.setProducts(new HashSet<>(productRepository.findAll()));
    }

    private void saveOrUpdateUserImage(User user) {
        List<Image> newImage = user.getUserImages()
                .stream()
                .filter(image -> image.getImageId() == 0)
                .collect(Collectors.toList());
        if (!newImage.isEmpty()) {
            imageRepository.saveAll(newImage);
        }
        user.setUserImages(new HashSet<>(imageRepository.findAll()));
    }
}
