package com.example.avito.Ad.elastic;

import com.example.avito.Ad.Ad;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticAdRepository extends ElasticsearchRepository<Ad, String> {
    List<Ad> findByTitleContaining(String title);
    Ad save(Ad ad);
}
