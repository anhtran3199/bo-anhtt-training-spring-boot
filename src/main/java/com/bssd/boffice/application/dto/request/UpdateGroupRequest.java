package com.bssd.boffice.application.dto.request;

import com.bssd.boffice.application.model.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
public class UpdateGroupRequest implements Serializable {
    @NotBlank(message = "group_name cannot blank")
    private String groupName;

    public UpdateGroupRequest(@JsonProperty("groupName") String groupName) {
        this.groupName = groupName;
    }

    public static UpdateGroupRequest fromGroup(Group group){
        if(group == null) return null;
        return UpdateGroupRequest.builder()
                .groupName(group.getGroupName())
                .build();
    }
}
