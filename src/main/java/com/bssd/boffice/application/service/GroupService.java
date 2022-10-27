package com.bssd.boffice.application.service;

import com.bssd.boffice.application.dto.request.CreateGroupRequest;
import com.bssd.boffice.application.dto.request.UpdateGroupRequest;
import com.bssd.boffice.application.dto.response.GroupResponse;

import java.util.List;

public interface GroupService {
    /**
     * Find all group
     * @author anhtt
     * @return
     */
    List<GroupResponse> findAll();

    /**
     * Find group by id
     * @author anhtt
     * @param id Group's id
     * @return
     */
    GroupResponse findById(Long id);

    /**
     * Create group by GroupRequest
     * @author anhtt
     * @param groupRequest Group's create information request
     * @return
     */
    GroupResponse create(CreateGroupRequest groupRequest);

    /**
     * Update Group
     * @param updateGroupRequest Group's update information request
     * @param id Group's ID
     * @return
     */
    GroupResponse update(UpdateGroupRequest updateGroupRequest, long id);
}
