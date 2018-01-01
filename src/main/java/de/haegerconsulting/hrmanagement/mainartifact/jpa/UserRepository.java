package de.haegerconsulting.hrmanagement.mainartifact.jpa;


import de.haegerconsulting.hrmanagement.mainartifact.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {

}
