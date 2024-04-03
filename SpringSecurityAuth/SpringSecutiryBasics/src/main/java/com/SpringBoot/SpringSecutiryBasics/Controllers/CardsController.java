package com.SpringBoot.SpringSecutiryBasics.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.SpringSecutiryBasics.Models.Cards;
import com.SpringBoot.SpringSecutiryBasics.Repository.CardsRepository;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public ResponseEntity<List<Cards>> getCardDetails(@RequestParam int id) {
        List<Cards> cards = cardsRepository.findByCustomerId(id);
        if (cards != null && !cards.isEmpty()) {
            return ResponseEntity.ok(cards); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
