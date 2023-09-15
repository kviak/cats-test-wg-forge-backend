package ru.kviak.catstestwgforgebackend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "cats")
public class Cat {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color;
    @Column(name = "tail_length")
    private Integer tailLength;
    @Column(name = "whiskers_length")
    private Integer whiskersLength;
}
