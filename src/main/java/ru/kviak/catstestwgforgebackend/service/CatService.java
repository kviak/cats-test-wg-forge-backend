package ru.kviak.catstestwgforgebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.kviak.catstestwgforgebackend.model.Cat;
import ru.kviak.catstestwgforgebackend.repository.CatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<Cat> getAllCats(String attribute, String order, int offset, int limit){
        return catRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.fromString(order.toUpperCase()),attribute))).getContent();
    }

    public void saveCat(Cat cat){
        catRepository.save(cat);
        updateTables();
    }

    private void updateTables(){ // Method for update cat_color_info and cats_stat after add new cat.
        jdbcTemplate.update("""
                INSERT INTO cat_colors_info (color, count)
                                                              SELECT color, COUNT(*) AS count
                                                              FROM cats
                                                              GROUP BY color
                                                              ORDER BY color
                                                              ON CONFLICT (color)
                                                              DO UPDATE SET count = EXCLUDED.count;""");

        jdbcTemplate.update("""
                INSERT INTO cats_stat (tail_length_mean, tail_length_median, tail_length_mode, whiskers_length_mean, whiskers_length_median, whiskers_length_mode)
                SELECT (SELECT AVG(tail_length) FROM cats)                                                                                                                                                                        AS tail_length_mean,
                    (SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY tail_length) FROM cats)                                                                                                                                   AS tail_length_median,
                    (SELECT ARRAY(SELECT tail_length FROM cats GROUP BY tail_length HAVING COUNT(*) = (SELECT MAX(count) FROM (SELECT tail_length, COUNT(*) AS count FROM cats GROUP BY tail_length) AS counts)))                 AS tail_length_mode,
                       (SELECT AVG(whiskers_length) FROM cats)                                                                                                                                                                    AS whiskers_length_mean,
                    (SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY whiskers_length) FROM cats)                                                                                                                               AS whiskers_length_median,
                    (SELECT ARRAY(SELECT whiskers_length FROM cats GROUP BY whiskers_length HAVING COUNT(*) = (SELECT MAX(count) FROM (SELECT whiskers_length, COUNT(*) AS count FROM cats GROUP BY whiskers_length) AS counts))) AS whiskers_length_mode;
                """);
    }
}
