package com.bssd.boffice.application.dto;

import com.bssd.boffice.application.model.Group;
import com.bssd.boffice.application.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private long id;
    private String fullname;
    private String email;
    private String phone;
    private String groupName;
    private LocalDateTime createdDate;



    public static UserDto fromUser(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .groupName(user.getUserGroup().getGroupName())
                .createdDate(user.getCreatedDate())
                .build();
    }

}
