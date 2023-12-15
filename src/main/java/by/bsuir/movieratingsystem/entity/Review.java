package by.bsuir.movieratingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Role userRole;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "movie_id")
    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false)
    private Integer rate;

    @Column(nullable = false)
    private String reviewText;

}
