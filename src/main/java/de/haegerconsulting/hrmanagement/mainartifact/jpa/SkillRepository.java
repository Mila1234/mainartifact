package de.haegerconsulting.hrmanagement.mainartifact.jpa;


import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SkillRepository extends JpaRepository<Skill, String> {
}
