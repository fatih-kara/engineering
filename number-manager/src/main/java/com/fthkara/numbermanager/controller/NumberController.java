package com.fthkara.numbermanager.controller;

import com.fthkara.numbermanager.model.NumberDocument;
import com.fthkara.numbermanager.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/numbers")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<NumberDocument> createNewNumber(@Valid @RequestBody NumberDocument number) {

        NumberDocument result = numberService.save(number);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = {"/{number}"})
    public ResponseEntity<NumberDocument> getNumber(@PathVariable int number) {

        NumberDocument result = numberService.findByNumber(number);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/maximum")
    public ResponseEntity<Integer> getMaximumNumber() {

        int result = numberService.findMaximumNumber();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/minimum")
    public ResponseEntity<Integer> getMinumumNumber() {

        int result = numberService.findMinimumNumber();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{number}")
    public ResponseEntity<Void> deleteNumber(@PathVariable int number) {
        numberService.delete(number);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value= {"", "/"})
    public ResponseEntity<List<NumberDocument>> getAllNumbers(@RequestParam(value = "sort",required = false) String sort) {

        List<NumberDocument> results = numberService.findAllByNumber(sort);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
