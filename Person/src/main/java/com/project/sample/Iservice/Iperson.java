package com.project.sample.Iservice;

import com.project.sample.Model.Person;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Iperson {

    public List<Person> getAllPersons() ;

    public Person updatePerson(Person person);

    public void delete(int id);

    public int savePerson(@RequestBody Person person);

    public List<Person> getAllPersonsByAgeGreaterThanTwenty();
}
