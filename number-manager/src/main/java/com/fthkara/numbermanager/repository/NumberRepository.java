package com.fthkara.numbermanager.repository;

import com.fthkara.numbermanager.model.NumberDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberRepository extends MongoRepository<NumberDocument, Integer> {
    NumberDocument findByNumber(int number);
    NumberDocument findTopByOrderByNumberDesc();
    NumberDocument findTopByOrderByNumberAsc();
    void deleteByNumber(int number);
    List<NumberDocument> findAllByOrderByNumberAsc();
    List<NumberDocument> findAllByOrderByNumberDesc();
}
