package com.project.sample.ControllerTest;

import com.project.sample.Iservice.Iperson;
import com.project.sample.Model.Person;
import com.project.sample.Service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(authorities="ADMIN")
public class PersonControllerTest {

    @Autowired(required = true)
    private MockMvc mockMvc;

    @MockBean
    private Iperson personService;

    Person person;

    @Before
    public void setup() {

        person = new Person();
        person.setId(1);
        person.setAge(18);
        person.setFirstName("Santhosh");
        person.setLastName("kumar");
        person.setGender("male");
    }

    @Test
    public void testSavePerson() throws Exception {
        String createPersonReq = "{	\"firstName\": \"santhosh\",  \"lastName\": \"rajkumar\",  \"gender\": \"Male\",  \"age\": 30}";
        Mockito.when(personService.savePerson(Mockito.any())).thenReturn(1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/person").accept(MediaType.APPLICATION_JSON)
                .content(createPersonReq).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void getAllPersonDetailsTest() throws Exception {
        List<Person> personsList = getPersonList();
        Mockito.when(personService.getAllPersons()).thenReturn(personsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void updatePersonTest() throws Exception {
        String updatePersonReq = "{	\"firstName\": \"santhosh\",  \"lastName\": \"kumar\",  \"gender\": \"Male\",  \"age\": 29}";
        person.setId(1);
        Mockito.when(personService.updatePerson(person)).thenReturn(person);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updatePersonDetails/1")
                .accept(MediaType.APPLICATION_JSON).content(updatePersonReq).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void getPersonsByAgeTest() throws Exception {
        List<Person> personsList = getPersonList();
        Mockito.when(personService.getAllPersonsByAgeGreaterThanTwenty()).thenReturn(personsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/persons/age")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deletePersonTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/persons/1")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    public List<Person> getPersonList(){
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
}
