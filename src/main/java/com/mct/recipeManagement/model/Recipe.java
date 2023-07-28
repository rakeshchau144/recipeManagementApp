package com.mct.recipeManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @ElementCollection
    private List<String> ingredients;

    private String instructions;

    @ManyToOne
    @JoinColumn(name = "fk_recipeOnUserId")
    private User user;

}
