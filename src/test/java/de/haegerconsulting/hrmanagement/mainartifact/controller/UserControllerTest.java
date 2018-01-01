package de.haegerconsulting.hrmanagement.mainartifact.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.haegerconsulting.hrmanagement.mainartifact.exception.exceptiontypes.ResourceNotFoundException;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import de.haegerconsulting.hrmanagement.mainartifact.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


   /* @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ExceptionHandlingController())
                .build();
    }*/

    @Test
    public void getUsers() throws Exception {
       List<User> users = new ArrayList<>();
       User user = new User();
       user.setId("5");
       user.setFirstName("Mirko");
       users.add(user);


       when(userService.findAll()).thenReturn(users);

       this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
              //  .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..firstName").exists())
                .andExpect(jsonPath("$..id").value("5"))
                .andExpect(jsonPath("$..firstName").value("Mirko"));
    }



    @Test
    public void getUser() throws Exception {
        User user = new User();
        String id = "5";
        user.setId(id);
        user.setFirstName("Mirko");

        when(userService.get(id)).thenReturn(user);

        this.mockMvc.perform(get("/users/5").accept(MediaType.APPLICATION_JSON))
                  .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..firstName").exists())
                .andExpect(jsonPath("$..id").value("5"))
                .andExpect(jsonPath("$..firstName").value("Mirko"));
    }

   /* TODO
    @Test//(expected = ResourceNotFoundException.class)
    public void getUserException() throws Exception {

        when(userService.get("105")).thenThrow(ResourceNotFoundException.class);

        this.mockMvc.perform(get("/users/105").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..firstName").exists())
                .andExpect(jsonPath("$..id").value("5"))
                .andExpect(jsonPath("$..firstName").value("Mirko"));
    }*/

    @Test
    public void createUser() throws Exception {
        User user = new User();
        String id = "5";
        user.setId(id);
        user.setFirstName("Mirko");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(user);
        when(userService.save(user)).thenReturn(user);

        this.mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..firstName").exists())
                .andExpect(jsonPath("$..id").value("5"))
                .andExpect(jsonPath("$..firstName").value("Mirko"));
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        String id = "5";
        user.setId(id);
        user.setFirstName("Mirko");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(user);

        this.mockMvc.perform(put("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteUser() throws Exception {
        User user = new User();
        String id = "5";
        user.setId(id);
        user.setFirstName("Mirko");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(user);

        this.mockMvc.perform(delete("/users/5")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results").exists())
                .andExpect(jsonPath("$.results").value("5"));
    }





}
