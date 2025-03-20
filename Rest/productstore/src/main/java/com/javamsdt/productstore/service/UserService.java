package com.javamsdt.productstore.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public User getUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional
    public User saveUser(User user) {
        saveOrUpdateUserImage(user);
        saveOrUpdateUserProduct(user);
        return userRepository.save(user);
    }

    //////////////////////////////////////////////////////////////
    ////////////////// User Product Functionality ////////////////
    //////////////////////////////////////////////////////////////

    private void saveOrUpdateUserProduct(User user) {
        long userId = user.getUserId();
        Set<Product> userProducts = user.getProducts();
        List<Product> storedUserProducts = productRepository.findUserProductsByUserId(userId);
        List<Product> toBeUpdated;
        List<Product> toBeSaved;

        if (userProducts != null) {
            toBeUpdated = userProducts
                    .stream()
                    .filter(product -> storedUserProducts.stream()
                            .anyMatch(storedProduct -> !storedProduct.equals(product)))
                    .collect(Collectors.toList());
        }
        toBeSaved = userProducts
                .stream()
                .filter(product -> storedUserProducts.stream()
                        .anyMatch(storedProduct -> storedProduct.getProductId() != product.getProductId()))
                .collect(Collectors.toList());

    }

    //////////////////////////////////////////////////////////////
    ////////////////// User Image Functionality //////////////////
    //////////////////////////////////////////////////////////////

    public void saveOrUpdateUserImage(User user) {
        long userId = user.getUserId();
        List<Image> newlyStoredImages = Collections.emptyList();
        List<Image> newlySelectedImageForUser = Collections.emptyList();
        List<Image> oldStoredImagesForUser;

        if (user.getUserImages() != null) {
            List<Image> newImage = user.getUserImages().stream()
                    .filter(image -> image.getImageId() == 0)
                    .collect(Collectors.toList());

            newlySelectedImageForUser = user.getUserImages().stream()
                    .filter(image -> image.getImageId() > 0)
                    .collect(Collectors.toList());
            if (!newImage.isEmpty()) {
                newlyStoredImages = imageRepository.saveAll(newImage);
            }
        }

        if (userId != 0) {
            oldStoredImagesForUser = imageRepository.findImagesByUserId(userId);
            List<Image> imageNotInUserDB = imageNotInUserDB(newlySelectedImageForUser, oldStoredImagesForUser);
            Set<Image> mergedImages = mergeUserImages(imageNotInUserDB, oldStoredImagesForUser, newlyStoredImages);

            user.setUserImages(new HashSet<>(mergedImages));
            user.getUserImages().forEach(image -> log.info(image.toString()));
        }
    }

    private List<Image> imageNotInUserDB(List<Image> newlySelectedImageForUser, List<Image> userImages) {

        if (userImages.isEmpty() && newlySelectedImageForUser != null) {
            return newlySelectedImageForUser;
        }
        List<Image> imageNotInUserDB = Collections.emptyList();
        if (newlySelectedImageForUser != null) {
            imageNotInUserDB = newlySelectedImageForUser.stream()
                    .filter(image -> userImages.stream()
                            .anyMatch(userImage -> userImage.getImageId() != image.getImageId())
                    ).collect(Collectors.toList());
        }

        return imageNotInUserDB;
    }

    private Set<Image> mergeUserImages(List<Image> imageNotInUserDB, List<Image> oldStoredImagesForUser,
            List<Image> newlyStoredImages) {
        return Stream.of(imageNotInUserDB, oldStoredImagesForUser, newlyStoredImages)
                .flatMap(Collection::stream).collect(Collectors.toSet());
    }
}
