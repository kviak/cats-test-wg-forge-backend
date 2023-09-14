package ru.kviak.catstestwgforgebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kviak.catstestwgforgebackend.model.Cat;
import ru.kviak.catstestwgforgebackend.repository.CatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    public List<Cat> getAllCats(String attribute, String order, int offset, int limit){
        return catRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.fromString(order.toUpperCase()),attribute))).getContent();
    }
}
