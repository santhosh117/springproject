package com.project.sample.ServiceTest;

import com.project.sample.Model.Person;
import com.project.sample.Repository.PersonRepository;
import com.project.sample.Service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonService personService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    public List<Person> personList(){
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setFirstName("Santhosh");
        person.setLastName("Rajkumar");
        person.setGender("male");
        persons.add(person);
        return persons;
    }

    public Person getPerson(){
        Person person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setFirstName("Santhosh");
        person.setLastName("kumar");
        person.setGender("male");
        return person;
    }

    @Test
    public void testDelete(){
        Person p = new Person();
        personRepository.deleteById(1);
        verify(personRepository, times(1)).deleteById(1);
    }

    @Test
    public void testGetAllPerson(){
        Mockito.when(personRepository.findAll()).thenReturn(personList());
        List<Person> persons = personService.getAllPersons();
        Assert.assertEquals(1,persons.size());
    }
    @Test
    public void testGetAllPersonsByAgeGreaterThanTwenty(){
        Mockito.when((personRepository.getAllPersonsByAgeGreaterThanTwenty())).thenReturn(personList());
        List<Person> persons = personService.getAllPersonsByAgeGreaterThanTwenty();
        Assert.assertEquals(0,persons.size());
    }
    @Test
    public void testSavePerson(){
        Person p =  getPerson();
        Mockito.when(personRepository.save(p)).thenReturn(p);
        int key = personService.savePerson(p);
        Assert.assertEquals(1,key);
    }

    @Test
    public void testUpdatePerson(){
        Person p =  getPerson();
        Mockito.when(personRepository.save(p)).thenReturn(p);
        int key = personService.savePerson(p);
        Assert.assertEquals(1,key);
        Assert.assertEquals("kumar",p.getLastName());
        p.setLastName("Rajkumar");
        Mockito.when(personRepository.save(p)).thenReturn(p);
        p = personService.updatePerson(p);
        Assert.assertEquals(1,key);
        Assert.assertEquals("Rajkumar",p.getLastName());
    }
}