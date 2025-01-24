package org.example.crudperson.controller;

import org.example.crudperson.model.Person;
import org.example.crudperson.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return (List<Person>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = repository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setDob(personDetails.getDob());
        person.setGender(personDetails.getGender());
        return repository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}