package canard.intern.post.following.backend.entity;


import canard.intern.post.following.backend.enums.PoeType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


    @ToString
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "poes")
    public class Poe {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer id;

        @Column(length = 150, nullable = false)
        private String title;

      @Column(name = "poe_type", length = 10, nullable = false)
       // @Enumerated(EnumType.STRING)
       // @Column(name = "poe_type",length=10)
        private String poeType;
   //     private PoeType poeType;

        @Column(name = "begin_date",nullable = false)
        private LocalDate beginDate;

        @Column(name = "end_date",nullable = false)
        private LocalDate endDate;
// @OneToMany
 //       private Set<Trainee> trainees;

    }

