package de.haegerconsulting.hrmanagement.mainartifact.controller;


import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationUserControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void createUsergetUsers() throws IOException {

        //createUser
        User user = new User();
        user.setFirstName("Marija");
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<String> responseEntityCreate = this.restTemplate.postForEntity("/users", request, String.class);

        assertEquals(HttpStatus.CREATED, responseEntityCreate.getStatusCode());
        assertEquals("{\"results\":{\"id\":\"1\",\"firstName\":\"Marija\"}}", responseEntityCreate.getBody());


        //getUsers
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/users", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"results\":[{\"id\":\"1\",\"firstName\":\"Marija\"}]}", responseEntity.getBody());


        //addSkill
        Skill skill = new Skill();
        skill.setName("Vestina");

        HttpEntity<Skill> requestSkill = new HttpEntity<>(skill);
        ResponseEntity<String> responseEntityCreateSkill= this.restTemplate.postForEntity("/users/1/skills", requestSkill, String.class);

        assertEquals(HttpStatus.CREATED, responseEntityCreateSkill.getStatusCode());
        assertEquals("{\"results\":{\"id\":\"1\",\"name\":\"Vestina\"}}", responseEntityCreateSkill.getBody());

        //updateSkill
         skill = new Skill();
        skill.setName("Macevanje");
        skill.setId("1");

         requestSkill = new HttpEntity<>(skill);
        restTemplate.exchange("/users/1/skills/1", HttpMethod.PUT, requestSkill, Void.class);


        //getSkill
         responseEntity = this.restTemplate.getForEntity("/users/1/skills", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"results\":[{\"id\":\"1\",\"name\":\"Macevanje\"}]}", responseEntity.getBody());

    }

   /* @Test
    public void addSkill() throws IOException {

        //addSkill
        Skill skill = new Skill();
        skill.setName("Vestina");

        User user =new User();
        user.setId("1");
        skill.setUser(user);
        HttpEntity<Skill> request = new HttpEntity<>(skill);
        ResponseEntity<String> responseEntityCreate = this.restTemplate.postForEntity("/users/1/skills", request, String.class);

        assertEquals(HttpStatus.CREATED, responseEntityCreate.getStatusCode());
        assertEquals("{\"results\":{\"id\":\"1\",\"name\":\"Vestina\"}}", responseEntityCreate.getBody());


    }*/

    @Test
    public void updateSkill() throws IOException {

        //updateSkill
        Skill skill = new Skill();
        skill.setName("Vestina");
        skill.setId("1");

        HttpEntity<Skill> request = new HttpEntity<>(skill);
        restTemplate.exchange("/users/2/skills", HttpMethod.PUT, request, Void.class);


    }

   /* @Test
    public void getSkill() throws IOException {

        //updateSkill

        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/users/1/skills/1", String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("{\"results\":[{\"id\":\"1\",\"firstName\":\"Marija\"}]}", responseEntity.getBody());


    }*/


/*
    @Test
    public void createUser() {
        //createUser
        User user = new User();
        user.setFirstName("Marija");
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<String> responseEntityCreate = this.restTemplate.postForEntity("/users", request, String.class);

        assertEquals(HttpStatus.CREATED, responseEntityCreate.getStatusCode());
        assertEquals("{\"results\":{\"id\":\"1\",\"firstName\":\"Marija\"}}", responseEntityCreate.getBody());
    }*/

    @Test
    public void getUser() throws IOException {
        //getUsers
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/users/105", String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        //assertEquals("{\"results\":[{\"id\":\"1\",\"firstName\":\"Marija\"}]}", responseEntity.getBody());
    }






}
