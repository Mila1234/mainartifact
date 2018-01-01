package de.haegerconsulting.hrmanagement.mainartifact.jpa;


import de.haegerconsulting.hrmanagement.mainartifact.exception.exceptiontypes.ResourceNotFoundException;
import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaRespositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;




    @Test
    public void findAll() {
        //given
        User firstUser = new User();
        firstUser.setFirstName("Mirko");
        entityManager.persist(firstUser);
        entityManager.flush();

        User secondUser = new User();
        secondUser.setFirstName("nesto");
        entityManager.persist(secondUser);
        entityManager.flush();

        //when
        List<User> users = userRepository.findAll();
        //then
        assertThat(users.size()).isEqualTo(2);
        assertThat(users.get(0)).isEqualTo(firstUser);
        assertThat(users.get(1)).isEqualTo(secondUser);
    }

    @Test
    public void get() {
        //given
        User firstUser = new User();
        firstUser.setFirstName("Mirko");
        entityManager.persist(firstUser);
        entityManager.flush();

        User secondUser = new User();
        secondUser.setFirstName("Petar");
        entityManager.persist(secondUser);
        entityManager.flush();

        String id = firstUser.getId();
        //when
        User foundCustomer = userRepository.findOne(id);
        //then
        assertThat(foundCustomer.getId()).isEqualTo(id);
        assertThat(foundCustomer.getFirstName()).isEqualTo("Mirko");


    }



    @Test
    public void delete() {
        //given
        User firstUser = new User();
        firstUser.setFirstName("Mirko");
        entityManager.persist(firstUser);
        entityManager.flush();

        String id = firstUser.getId();

        //when
        Boolean result = userRepository.exists(id);

        //then
        //assertThat();
        assertThat(result).isEqualTo(true);
        assertThat(entityManager.getId(firstUser)).isEqualTo(id);

    }



}
