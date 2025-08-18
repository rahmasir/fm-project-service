package org.rahmasir.fmprojectservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.rahmasir.fmprojectservice.entity.Skill;
import org.rahmasir.fmprojectservice.repository.SkillRepository;
import org.rahmasir.fmprojectservice.service.SkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    @Transactional
    public Set<Skill> findOrCreateSkills(Set<String> skillNames) {
        return skillNames.stream()
                .map(name -> skillRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> skillRepository.save(new Skill(name))))
                .collect(Collectors.toSet());
    }
}
