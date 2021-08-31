package guru.springframework.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "recipes")
@Component
public class Category {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String description;

   @ManyToMany(mappedBy = "categories")
   private Set<Recipe> recipes = new HashSet<>();

}
