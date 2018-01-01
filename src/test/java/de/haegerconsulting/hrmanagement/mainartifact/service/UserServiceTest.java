package de.haegerconsulting.hrmanagement.mainartifact.service;

import de.haegerconsulting.hrmanagement.mainartifact.exception.exceptiontypes.ResourceNotFoundException;
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
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll(){
        //given
        List<User> users = new ArrayList<>();
        User firstUser = new User();
        firstUser.setFirstName("Mirko");
        users.add(firstUser);
        User secondUser = new User();
        firstUser.setFirstName("nesto");
        users.add(secondUser);

        //when
        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> resultIterable = userService.findAll();
        List<User> resultList = new ArrayList<>();
        resultIterable.forEach(resultList::add);

        //then
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList.get(0)).isEqualTo(firstUser);
        assertThat(resultList.get(1)).isEqualTo(secondUser);
    }

    @Test
    public void get(){
        //given
        List<User> users = new ArrayList<>();
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");
        users.add(firstUser);

        //when
        when(userRepository.findOne(id)).thenReturn(firstUser);

        User result = userService.get(id);

        //then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getFirstName()).isEqualTo("Mirko");
    }


    @Test(expected = ResourceNotFoundException.class)
    public void getExeption(){
        //given
        List<User> users = new ArrayList<>();
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");
        users.add(firstUser);

        //when
        when(userRepository.findOne(id)).thenReturn(null);

        User result = userService.get(id);
    }
    @Test
    public void save(){
        //given
        List<User> users = new ArrayList<>();
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");
        users.add(firstUser);

        //when
        when(userRepository.save(firstUser)).thenReturn(firstUser);

        User result = userService.save(firstUser);

        //then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getFirstName()).isEqualTo("Mirko");
    }

    @Test
    public void delete(){
        //given
        List<User> users = new ArrayList<>();
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");
        users.add(firstUser);

        //when
        when(userRepository.exists(id)).thenReturn(true);

        userService.delete(id);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteException(){
        //given
        List<User> users = new ArrayList<>();
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");
        users.add(firstUser);

        //when
        when(userRepository.exists(id)).thenReturn(false);

        userService.delete(id);
    }

    @Test
    public void update(){
        //given
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");

        //when
        when(userRepository.exists(id)).thenReturn(true);

        userService.update(firstUser);


    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateException(){
        //given
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");

        //when
        when(userRepository.exists(id)).thenReturn(false);

        userService.update(firstUser);
    }

    @Test
    public void getId(){
        //given
        User firstUser = new User();
        String id = "5";
        firstUser.setId(id);
        firstUser.setFirstName("Mirko");

        //when
        String result = userService.getId(firstUser);

        //then
        assertThat(result).isEqualTo(firstUser.getId());


    }

}
