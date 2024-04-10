package ru.hse.softwear.cinemaworld.userServer.view.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rows")
    private Integer rows;

    @Column(name = "columns")
    private Integer columns;






    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_name")
    private Cinema cinema;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    mappedBy = "hall")
    private List<Session> sessions = new ArrayList<>();

}