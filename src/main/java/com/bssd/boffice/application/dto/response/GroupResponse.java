package com.bssd.boffice.application.dto.response;

import com.bssd.boffice.application.model.Group;
import com.bssd.boffice.application.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class GroupResponse {
    private long id;
    private String groupName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private List<UserResponse> users;

    public static GroupResponse fromGroup(Group group){
        if(group == null){
            return null;
        }

        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .createdDate(group.getCreatedDate())
                .createdBy(group.getCreatedBy())
                .users(group.getUsers().stream().map(UserResponse::fromUser).collect(Collectors.toList()))
                .build();
    }
}
