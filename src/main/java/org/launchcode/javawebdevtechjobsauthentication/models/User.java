package org.launchcode.javawebdevtechjobsauthentication.models;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @NotBlank
    @Size(min=3, max=20, message="Invalid username. Must contain between 3 and 20 characters.")
    private String username;

    @NotNull
    @NotBlank
    @Size(min=6, max=20, message="Invalid password. Must contain between 6 and 20 characters.")
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() { }

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }



    public String getUsername() {
        return username;
    }
}
