package by.bsuir.movieratingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Boolean isBanned;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Review> reviews;
//
//    public void addReview(Review review) {
//        if (reviews.contains(review)) {
//            return;
//        }
//        reviews.add(review);
//        review.setUser(this);
//    }
//
//    public void removeReview(Review review) {
//        if (!reviews.contains(review)) {
//            return;
//        }
//        reviews.remove(review);
//        review.setUser(null);
//    }
}
