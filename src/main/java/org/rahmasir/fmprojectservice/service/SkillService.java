package org.rahmasir.fmprojectservice.service;

import org.rahmasir.fmprojectservice.entity.Skill;

import java.util.Set;

/**
 * Service interface for skill-related business logic within the project service.
 */
public interface SkillService {

    /**
     * Finds or creates a set of skills based on their names.
     *
     * @param skillNames A set of strings representing the skill names.
     * @return A set of managed Skill entities.
     */
    Set<Skill> findOrCreateSkills(Set<String> skillNames);
}