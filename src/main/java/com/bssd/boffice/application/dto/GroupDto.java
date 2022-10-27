package com.bssd.boffice.application.dto;

import com.bssd.boffice.application.model.Group;
import com.bssd.boffice.application.model.User;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto implements Serializable {
    private long id;
    private String groupName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private List<User> users;

    public static GroupDto fromGroup(Group group){
        if(group == null){
            return null;
        }

        return GroupDto.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .createdDate(group.getCreatedDate())
                .createdBy(group.getCreatedBy())
                .users(group.getUsers())
                .build();
    }
}
