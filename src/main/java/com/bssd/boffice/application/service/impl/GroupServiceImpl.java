package com.bssd.boffice.application.service.impl;

import com.bssd.boffice.application.dto.request.CreateGroupRequest;
import com.bssd.boffice.application.dto.request.UpdateGroupRequest;
import com.bssd.boffice.application.dto.response.GroupResponse;
import com.bssd.boffice.application.exception.ResourceNotFoundException;
import com.bssd.boffice.application.model.Group;
import com.bssd.boffice.application.repository.GroupRepository;
import com.bssd.boffice.application.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<GroupResponse> findAll() {
        List<Group> groups = groupRepository.findAll();

        return groups.stream()
                .map(GroupResponse::fromGroup)
                .collect(Collectors.toList());
    }

    @Override
    public GroupResponse findById(Long id) {
        Optional<Group> group = groupRepository.findById(id);

        return GroupResponse.fromGroup(group.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public GroupResponse create(CreateGroupRequest createGroupRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Group group = new Group();
        group.setCreatedDate(LocalDateTime.now());
        group.setCreatedBy(authentication.getName());
        group.setGroupName(createGroupRequest.getGroupName());
        return GroupResponse.fromGroup(groupRepository.save(group));
    }


    @Override
    @Transactional(rollbackFor = ResourceNotFoundException.class, propagation = Propagation.REQUIRED)
    public GroupResponse update(UpdateGroupRequest updateGroupRequest, long id) {
        Optional<Group> group = groupRepository.findById(id);
        group.get().setUpdatedDate(LocalDateTime.now());
        group.get().setGroupName(updateGroupRequest.getGroupName());
        return GroupResponse.fromGroup(groupRepository.save(group.get()));
    }


}
