package com.project.sample.Service;

import com.project.sample.Model.Person;
import com.project.sample.Repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public List<Person> getAllPersonsByAgeGreaterThanTwenty(){
        return personRepository.getAllPersonsByAgeGreaterThanTwenty();
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}