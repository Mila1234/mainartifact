package de.haegerconsulting.hrmanagement.mainartifact.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import de.haegerconsulting.hrmanagement.mainartifact.service.SkillService;
import de.haegerconsulting.hrmanagement.mainartifact.service.UserService;
import org.hamcrest.Matchers;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SkillController.class)
public class SkillControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SkillController skillController;

    @MockBean
    private SkillService skillService;


    @Test
    public void getSkills() throws Exception {
        User user = new User();
        user.setId("1");
        user.setFirstName("Marija");
        user.setSkills(new ArrayList<>());

        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setId("5");
        skill.setName("vestina");
        skills.add(skill);
        user.addSkill(skill);

        skill = new Skill();
        skill.setId("7");
        skill.setName("druga vastina");
        skill.setUser(user);
        skills.add(skill);
        user.addSkill(skill);

        when(skillService.findAll(user.getId())).thenReturn(skills);

        this.mockMvc.perform(get("/users/1/skills").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..name").exists())
                .andExpect(jsonPath("$..id", Matchers.hasSize(2)))
                .andExpect(jsonPath("$..id", Matchers.containsInAnyOrder("5", "7")))
                .andExpect(jsonPath("$..name", Matchers.hasSize(2)))
                .andExpect(jsonPath("$..name", Matchers.containsInAnyOrder("vestina", "druga vastina")));

    }

    @Test
    public void createSkill() throws Exception {
        User user = new User();
        user.setId("1");
        user.setFirstName("Marija");
        user.setSkills(new ArrayList<>());

        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setId("5");
        skill.setName("vestina");
        skills.add(skill);
        user.addSkill(skill);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(skill);
        when(skillService.save(skill,user.getId())).thenReturn(skill);

        this.mockMvc.perform(post("/users/1/skills")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$..id").exists())
                .andExpect(jsonPath("$..name").exists())
                .andExpect(jsonPath("$..id").value("5"))
                .andExpect(jsonPath("$..name").value("vestina"));

    }


    @Test
    public void updateSkill() throws Exception {
        Skill skill = new Skill();
        String id = "5";
        skill.setId(id);
        skill.setName("Vestina");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(skill);

        this.mockMvc.perform(put("/users/1/skills/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteSkill() throws Exception {
        Skill skill = new Skill();
        String id = "5";
        skill.setId(id);
        skill.setName("Vestina");

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(skill);

        this.mockMvc.perform(delete("/users/1/skills/5")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results").exists())
                .andExpect(jsonPath("$.results").value("5"));
    }
}
