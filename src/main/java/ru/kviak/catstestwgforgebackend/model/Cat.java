package ru.kviak.catstestwgforgebackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color; // TODO: create color enum.
    @Column(name = "tail_length")
    private Integer tailLength;
    @Column(name = "whiskers_length")
    private Integer whiskersLength;
}
