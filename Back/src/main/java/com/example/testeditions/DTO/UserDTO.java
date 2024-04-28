package com.example.testeditions.DTO;

import com.example.testeditions.Entites.TypeRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String nom;
    private String email;
    private String password ;
    private int telephone ;
    private String rating;
    private TypeRole role;

    public UserDTO() {
    }

    public UserDTO(Long id, String nom, String email, String password, int telephone, String rating, TypeRole role) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.rating = rating;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public TypeRole getRole() {
        return role;
    }

    public void setRole(TypeRole role) {
        this.role = role;
    }
}
