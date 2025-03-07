package com.clothesshop;

import com.clothesshop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ClothesShopApplication implements ApplicationRunner {

    private final DelegatingPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ClothesShopApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(passwordEncoder.encode("acme"));
        System.out.println(passwordEncoder.encode("spacely"));
        System.out.println(passwordEncoder.encode("tcal"));
        System.out.println(passwordEncoder.encode("mscott"));
        System.out.println(passwordEncoder.encode("tstark"));
        System.out.println(passwordEncoder.encode("marya"));
        System.out.println(passwordEncoder.encode("bwayne"));

        userRepository.findAll()
                .forEach(user -> {
                    System.out.println("User:: " + user);
                    System.out.println("Customer:: " + user.getCustomer());
                });
    }
}
