package com.santander.bootcamp.service;

import com.santander.bootcamp.exceptions.BusinessException;
import com.santander.bootcamp.exceptions.NotFoundException;
import com.santander.bootcamp.mapper.StockMapper;
import com.santander.bootcamp.model.Stock;
import com.santander.bootcamp.model.dto.StockDTO;
import com.santander.bootcamp.repository.StockRepository;
import com.santander.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXITS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXITS);
        }

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return  mapper.toDTO(stock);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        List<StockDTO> stockDTOList = mapper.toDTO(repository.findAll());
        if(stockDTOList.size() == 0) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXITS);
        }
        return stockDTOList;
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }
}
