package org.rahmasir.fmprojectservice.repository;

import org.rahmasir.fmprojectservice.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Skill entity within the Project service.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, UUID> {

    /**
     * Finds a skill by its name, ignoring case.
     *
     * @param name The name of the skill to find.
     * @return an Optional containing the found Skill, or an empty Optional if not found.
     */
    Optional<Skill> findByNameIgnoreCase(String name);
}
