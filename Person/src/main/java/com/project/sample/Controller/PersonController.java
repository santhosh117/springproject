package com.project.sample.Controller;

import com.project.sample.Exception.CustomException;
import com.project.sample.Iservice.Iperson;
import com.project.sample.Model.Person;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PersonController {

    Iperson iPerson;

    @PostMapping("/person")
    private int savePerson(@RequestBody Person person) {
        try {
            iPerson.savePerson(person);
            return person.getId();
        }catch(CustomException e){
            throw new CustomException("Exception while Save Person "+ e.getMessage());
        }
    }

    @GetMapping("/persons")
    private List<Person> getAllPersons() {
        try {
            List<Person> person = iPerson.getAllPersons();
            if(person.size()==0){
                throw new CustomException("RecordNotFoundException,,");
            }
            return iPerson.getAllPersons();
        }catch (CustomException e){
            throw new CustomException("Exception while get all Person details, "+e.getMessage());
        }
    }

    @PutMapping("/updatePersonDetails/{id}")
    private Person updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
        try{
            person.setId(id);
            return iPerson.updatePerson(person);}
        catch(CustomException e){
            throw new CustomException("Exception while Update Person "+id+" "+e.getMessage());
        }
    }

    @DeleteMapping("/persons/{id}")
    private void deletePerson(@PathVariable("id") int id) {
        try {
            iPerson.delete(id);
        }catch(CustomException e){
            throw new CustomException("Exception while Delete Person "+id+" "+e.getMessage());
        }
    }

    @GetMapping("/persons/age")
    private List<Person> getAllPersonsByAgeGreaterThanTwenty() {
        try {
            return iPerson.getAllPersonsByAgeGreaterThanTwenty();
        } catch (CustomException e) {
            throw new CustomException("Exception while get Person age > 20" + e.getMessage());
        }
    }
}