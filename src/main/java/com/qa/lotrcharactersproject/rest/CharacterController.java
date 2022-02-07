package com.qa.lotrcharactersproject.rest;

import com.qa.lotrcharactersproject.domain.LOTRCharacter;
import com.qa.lotrcharactersproject.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/LOTRCharacter")
public class CharacterController {

    //Initialise a service
    private CharacterService service;

    //Construct a controller that uses the service
    public CharacterController(CharacterService service) {
        this.service = service;
    }

    //POST - Create Character
    @PostMapping
    public ResponseEntity<LOTRCharacter> create(@RequestBody LOTRCharacter character) {
        return new ResponseEntity<>(this.service.create(character), HttpStatus.CREATED);
    }

    //GET - Read All
    @GetMapping
    public ResponseEntity<List<LOTRCharacter>> readAll() {
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.FOUND);
    }

    //GET - Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<LOTRCharacter> readById(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.readById(id), HttpStatus.FOUND);
    }

    //PUT - Update Character
    @PutMapping("/{id}")
    public ResponseEntity<LOTRCharacter> update(@RequestBody LOTRCharacter character, @PathVariable Long id) {
        return new ResponseEntity<>(this.service.update(character, id), HttpStatus.ACCEPTED);
    }

    //DELETE - Delete Character by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
