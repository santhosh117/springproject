package com.project.sample.Service;

import com.project.sample.Iservice.Iperson;
import com.project.sample.Model.Person;
import com.project.sample.Repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService implements Iperson {

    PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return Optional.ofNullable(personRepository.findAll())
                .get()
                .stream()
                .filter(p->p!=null)
                .collect(Collectors.toList());
    }

    public List<Person> getAllPersonsByAgeGreaterThanTwenty(){
        return Optional.ofNullable(personRepository.getAllPersonsByAgeGreaterThanTwenty())
                .get()
                .stream()
                .filter(p->p.getAge()>20)
                .collect(Collectors.toList());
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public int savePerson(Person person) {
        return personRepository.save(person).getId();
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}