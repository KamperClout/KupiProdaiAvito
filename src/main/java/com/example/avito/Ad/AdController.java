package com.example.avito.Ad;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    @PostMapping
    public ResponseEntity saveIndexWithRepo(@RequestBody Ad ad) {
        adService.saveIndex(ad);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Ad> findByNameContainingWithRepo
            (@RequestParam("name") String name) {
        List<Ad> ad = adService.findByNameContaining(name);
        return ad;
    }
}