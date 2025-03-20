package com.javamsdt.productstore.controller;

import java.util.List;
import java.util.Set;

import com.javamsdt.productstore.model.Product;
import com.javamsdt.productstore.model.User;
import com.javamsdt.productstore.repository.UserRepository;
import com.javamsdt.productstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username) {
        User user = userService.getUserByUserName(username);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(path = "/{username}/products")
    public ResponseEntity<Product> getUserProducts(@PathVariable(value = "username") String username) {
        User user = userService.getUserByUserName(username);
        Set<Product> products = user.getProducts();
        return new ResponseEntity(products, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity(userService.saveUser(user), HttpStatus.OK);
    }


}
