package com.fthkara.numbermanager.service;

import com.fthkara.numbermanager.exception.ConflictException;
import com.fthkara.numbermanager.exception.NotFoundException;
import com.fthkara.numbermanager.model.NumberDocument;
import com.fthkara.numbermanager.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberService {

    @Autowired
    private NumberRepository numberRepository;

    private static final String ORDER_TYPE = "DESC";

    public NumberDocument save(NumberDocument number) {

        if(number != null && number.getNumber() != null) {
            NumberDocument numberDocument = numberRepository.findByNumber(number.getNumber());

            if(numberDocument != null) {
                throw new ConflictException("The number is already exist!");
            }
        }

        return numberRepository.insert(number);
    }

    public NumberDocument findByNumber(int number) {
        NumberDocument result = numberRepository.findByNumber(number);

        if(result == null) {
            throw new NotFoundException(String.format("No Record with the number [%s] was found !", number));
        }

       return numberRepository.findByNumber(number);
    }

    public int findMaximumNumber() {
        return numberRepository.findTopByOrderByNumberDesc().getNumber();
    }

    public int findMinimumNumber() {
        return numberRepository.findTopByOrderByNumberAsc().getNumber();
    }

    public void delete(int number) {
        numberRepository.deleteByNumber(number);
    }

    public List<NumberDocument> findAllByNumber(String sort) {
        if(sort!=null && !sort.isEmpty()) {
            if(sort.equalsIgnoreCase(ORDER_TYPE)) {
                return numberRepository.findAllByOrderByNumberDesc();
            }
            return numberRepository.findAllByOrderByNumberAsc();
        }

        return numberRepository.findAllByOrderByNumberAsc();
    }

}
