package com.santander.bootcamp.controller;

import java.util.List;

import com.santander.bootcamp.model.dto.StockDTO;

import com.santander.bootcamp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController {

  @Autowired
  private StockService service;
  
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto) {

    return ResponseEntity.ok(service.save(dto));
  }
  
  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto) {

    return ResponseEntity.ok(service.update(dto));
  }
  
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<StockDTO>> findAll() {
    
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<StockDTO>> findByToday() {

    return ResponseEntity.ok(service.findByToday());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<StockDTO> findById(@Valid @PathVariable Long id) {
    
    return ResponseEntity.ok(service.findById(id));
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<StockDTO> delete(@Valid @PathVariable Long id) {

    return ResponseEntity.ok(service.delete(id));
  }
}
