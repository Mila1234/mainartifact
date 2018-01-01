package de.haegerconsulting.hrmanagement.mainartifact.service;

import de.haegerconsulting.hrmanagement.mainartifact.jpa.SkillRepository;
import de.haegerconsulting.hrmanagement.mainartifact.jpa.UserRepository;
import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService implements GenericService<Skill, String> {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String getId(Skill entity) {
        return entity.getId();
    }

    @Override
    public CrudRepository<Skill, String> getRepository() {
        return this.skillRepository;
    }

    public Skill save(Skill skill, String userId) {
        User user = userRepository.getOne(userId);
        skill.setUser(user);
        return save(skill);
    }

    public void update(Skill skill, String userId) {
        User user = userRepository.getOne(userId);
        skill.setUser(user);
        update(skill);
    }


    public Iterable<Skill> findAll(String userId) {
        List<String> ids = new ArrayList<String>();
        ids.add(userId);
        return findAll(ids);
    }
}
