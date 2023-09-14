package ru.kviak.catstestwgforgebackend.model;

import jakarta.persistence.*;
import lombok.*;
import ru.kviak.catstestwgforgebackend.utill.Color;

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
    @Enumerated(value = EnumType.ORDINAL)
    private Color color; // TODO: create color enum.
    @Column(name = "tail_length")
    private Integer tailLength;
    @Column(name = "whiskers_length")
    private Integer whiskersLength;
}
