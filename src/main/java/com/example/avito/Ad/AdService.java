package com.example.avito.Ad;

import com.example.avito.Ad.elastic.ElasticAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService {
    private final ElasticAdRepository elasticAdRepository;

    public Ad saveIndex(Ad ad){
        return elasticAdRepository.save(ad);
    }

    public List<Ad> findByNameContaining(String name){
        List<Ad> ads = elasticAdRepository
                .findByTitleContaining(name)
                .stream()
                .collect(Collectors.toList());
        return ads;

    }
}
