package ru.hse.softwear.cinemaworld.restServer.view.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.softwear.cinemaworld.restServer.view.model.interfaces.Image;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images_cinemas")
public class ImageCinema implements Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image")
    private byte[] picture;
}
