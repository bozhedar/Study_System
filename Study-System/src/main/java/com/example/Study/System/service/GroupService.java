package com.example.Study.System.service;

import com.example.Study.System.dao.GroupRepository;
import com.example.Study.System.exception.group.GroupExistsException;
import com.example.Study.System.exception.group.GroupNotFoundException;
import com.example.Study.System.model.GroupEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GroupService {
private final GroupRepository groupRepository;

    public void create(String name) {
        if (groupRepository.existsByName(name)) {
            throw new GroupExistsException();
        }
        GroupEntity group = GroupEntity.builder()
                .name(name)
                .build();
        groupRepository.save(group);
    }

    public String update(Long id, String name) {
        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> {
            log.error("Group not found");
            return new GroupNotFoundException();
        });
        group.setName(name);

        groupRepository.save(group);
        return group.getName();
    }
     public void delete(Long id) {
         GroupEntity group = groupRepository.findById(id).orElseThrow(() -> {
             log.error("Group not found");
             return new GroupNotFoundException();
         });
         groupRepository.delete(group);
     }
}
