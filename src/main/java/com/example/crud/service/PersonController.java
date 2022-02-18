package com.example.crud.service;

import com.example.crud.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.crud.repo.PersonRepo;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonRepo personRepo;

    public PersonController(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Person savePerson(@RequestBody Person person){
        return personRepo.save(person);
    }

    @GetMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPerson(){
        return personRepo.findAll();
    }

    @GetMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person findPersonById(@PathVariable Long id){
        Person person = personRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id = " + id));
        return person;
    }

    @PutMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person refreshPerson(@RequestBody Person person, @PathVariable Long id){
        return personRepo.findById(id)
                .map(entity -> {
                    entity.setFirstName(person.getFirstName());
                    entity.setLastName(person.getLastName());
                    return personRepo.save(entity);
                }).orElseThrow(() -> new EntityNotFoundException("Person not found with id = " + id));
    }

    @DeleteMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePersonById(@PathVariable Long id){
        Person person = personRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person not found with id = " + id));
        personRepo.delete(person);
    }

    @DeleteMapping("/persons")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllPerson(){
        personRepo.deleteAll();
    }
}
