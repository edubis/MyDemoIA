package com.example.bookstore.controllers;


import com.example.bookstore.dto.GraphicCardDto;
import com.example.bookstore.models.GraphicCard;
import com.example.bookstore.services.GraphicCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graphic_card")
public class GraphicCardController {

    private final GraphicCardService graphicCardService;

    public GraphicCardController(GraphicCardService graphicCardService) {
        this.graphicCardService = graphicCardService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GraphicCard> saveOpcao(@RequestBody GraphicCardDto body){

        return ResponseEntity.status(HttpStatus.CREATED).body(graphicCardService.save(body));
    }

}
