package com.project.sample.Repository;

import com.project.sample.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("select p from Person p where p.age>20")
    public List<Person> getAllPersonsByAgeGreaterThanTwenty();
}
