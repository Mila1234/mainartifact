package de.haegerconsulting.hrmanagement.mainartifact.controller;


import de.haegerconsulting.hrmanagement.mainartifact.VO.ResponseVO;
import de.haegerconsulting.hrmanagement.mainartifact.model.Skill;
import de.haegerconsulting.hrmanagement.mainartifact.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class SkillController {

    @Autowired
    private SkillService skillService;


    @GetMapping(value = "/{userId}/skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO<Iterable<Skill>>> getSkills(@PathVariable("userId")String userId) throws EntityNotFoundException {
        return ResponseEntity.ok(new ResponseVO<>(skillService.findAll(userId)));
    }

    @PostMapping(value = "/{userId}/skills",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseVO<Skill>> createSkill(@PathVariable("userId") String userId,@RequestBody Skill skill) {
        Skill savedSkill = skillService.save(skill,userId);
        return ResponseEntity.created(URI.create("/"+userId+"/skills/" + savedSkill.getId())).body(new ResponseVO<>(savedSkill));
    }


    @PutMapping(value = "/{userId}/skills/{skillId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSkill(@PathVariable("userId") String userId, @PathVariable("skillId") String skillId, @RequestBody Skill skill) {
        skillService.update(skill,userId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/{userId}/skills/{skillId}")
    public ResponseEntity<ResponseVO<String>> deleteSkill(@PathVariable("userId") String userId, @PathVariable("skillId") String skillId) {
        skillService.delete(skillId);
        return ResponseEntity.ok(new ResponseVO<>(skillId));
    }
}
