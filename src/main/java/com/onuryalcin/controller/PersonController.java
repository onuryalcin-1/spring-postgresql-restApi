package com.onuryalcin.controller;

import com.onuryalcin.dto.PersonDto;
import com.onuryalcin.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class PersonController {

    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.save(personDto));
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getAllPersonList(){
        return ResponseEntity.ok(personService.getAll());
    }


}
