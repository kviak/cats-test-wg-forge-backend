package ru.kviak.catstestwgforgebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.catstestwgforgebackend.model.Cat;

public interface CatRepository extends JpaRepository<Cat, String> {
}
