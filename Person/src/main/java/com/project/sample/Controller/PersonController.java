package com.project.sample.Controller;

import com.project.sample.Model.Person;
import com.project.sample.Service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {

    PersonService personService;

    @GetMapping("/persons")
    private List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/persons/{id}")
    private Person getPerson(@PathVariable("id") int id, @RequestBody Person person) {
        person.setId(id);
        return personService.updatePerson(person);
    }

    @DeleteMapping("/persons/{id}")
    private void deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
    }

    @PostMapping("/persons")
    private int savePerson(@RequestBody Person person) {
        personService.savePerson(person);
        return person.getId();
    }

    @GetMapping("/persons/age")
    private List<Person> getAllPersonsByAgeGreaterThanTwenty() {
        return personService.getAllPersonsByAgeGreaterThanTwenty();
    }


}
