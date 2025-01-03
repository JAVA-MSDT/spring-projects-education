package com.clothesshop.model.user.security;

import com.clothesshop.model.user.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_security", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@With
@ToString
public class UserSecurity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Username is required")
    @Size(max = 20)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 3, message = "Password must be at least 6 characters long")
    @JsonIgnore
    private String password;

    @Column(name = "account_non_expired", columnDefinition = "DEFAULT true")
    private boolean accountNonExpired;
    @Column(name = "account_non_locked", columnDefinition = "DEFAULT true")
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired", columnDefinition = "DEFAULT true")
    private boolean credentialsNonExpired;
    @Column(name = "enabled", columnDefinition = "DEFAULT true")
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_security_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole())).collect(Collectors.toList());
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

}
