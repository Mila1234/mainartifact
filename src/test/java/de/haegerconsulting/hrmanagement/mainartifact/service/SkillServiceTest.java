package de.haegerconsulting.hrmanagement.mainartifact.service;

import de.haegerconsulting.hrmanagement.mainartifact.jpa.SkillRepository;
import de.haegerconsulting.hrmanagement.mainartifact.jpa.UserRepository;
import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SkillService skillService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void save(){
        //given
        String userId = "1";
        User user = new User();
        user.setId(userId);
        user.setSkills(new ArrayList<>());

        Skill skill = new Skill();
        skill.setId("5");
        skill.setName("Nesto");
        skill.setUser(user);
        user.addSkill(skill);

        //when
        when(userRepository.getOne(userId)).thenReturn(user);
        when(skillRepository.save(skill)).thenReturn(skill);
        Skill resultSkill = skillService.save( skill,  userId);

        //then
        assertThat(resultSkill.getId()).isEqualTo("5");
        assertThat(resultSkill.getName()).isEqualTo("Nesto");
    }

    @Test
    public void update(){
        //given
        String userId = "1";
        User user = new User();
        user.setId(userId);
        user.setSkills(new ArrayList<>());

        Skill skill = new Skill();
        skill.setId("5");
        skill.setName("Nesto");
        skill.setUser(user);
        user.addSkill(skill);

        //when
        when(userRepository.getOne(userId)).thenReturn(user);
        when(skillRepository.save(skill)).thenReturn(skill);
        when(skillRepository.exists(skill.getId())).thenReturn(true);
        skillService.update( skill,  userId);

    }

    @Test
    public void findAll(){
        //given
        String userId = "1";
        User user = new User();
        user.setId(userId);
        user.setSkills(new ArrayList<>());

        List<Skill> skills = new ArrayList<>();

        Skill skill = new Skill();
        skill.setId("5");
        skill.setName("Nesto");
        skill.setUser(user);
        user.addSkill(skill);
        skill = new Skill();
        skill.setId("7");
        skill.setName("Novo");
        skill.setUser(user);
        user.addSkill(skill);

        List<String> ids = new ArrayList<String>();
        ids.add(userId);

        //when
        when(skillRepository.findAll(ids)).thenReturn(skills);
        Iterable<Skill> resultIterable = skillService.findAll( userId);
        List<Skill> resultList = new ArrayList<>();
        resultIterable.forEach(resultList::add);
    }

}
