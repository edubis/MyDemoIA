package com.example.bookstore.services;


import com.example.bookstore.dto.GraphicCardDto;
import com.example.bookstore.models.GraphicCard;
import com.example.bookstore.repositories.GraphicCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class GraphicCardService {

    private final GraphicCardRepository repository;
    private final TecnologyService tecnologyService;

    public GraphicCardService(GraphicCardRepository repository, TecnologyService tecnologyService) {
        this.repository = repository;
        this.tecnologyService = tecnologyService;
    }


    public GraphicCard save(GraphicCardDto dto){
        var graphic = new GraphicCard();
        BeanUtils.copyProperties(dto, graphic);
        tecnologyService.findByCard(graphic);
        return  repository.save(graphic);

    }

}
