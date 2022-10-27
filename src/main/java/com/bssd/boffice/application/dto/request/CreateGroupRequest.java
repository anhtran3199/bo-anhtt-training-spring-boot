package com.bssd.boffice.application.dto.request;

import com.bssd.boffice.application.model.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
public class CreateGroupRequest implements Serializable {

    @NotBlank(message = "group_name cannot blank")
    private String groupName;

    public CreateGroupRequest(@JsonProperty("groupName") String groupName) {
        this.groupName = groupName;
    }

    public static CreateGroupRequest fromGroup(Group group){
        if(group == null) return null;
        return CreateGroupRequest.builder()
                .groupName(group.getGroupName())
                .build();
    }
}
