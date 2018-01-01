package de.haegerconsulting.hrmanagement.mainartifact.service;



import de.haegerconsulting.hrmanagement.mainartifact.jpa.UserRepository;
import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements GenericService<User, String> {

    @Autowired
    private UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CrudRepository<User, String> getRepository() {
        return this.userRepository;
    }

    @Override
    public String getId(User entity) {
        return entity.getId();
    }


}
