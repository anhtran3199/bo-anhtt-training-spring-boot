package com.bssd.boffice.application.dto.response;

import com.bssd.boffice.application.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private long id;
    private String fullname;
    private String email;
    private String phone;
    private String groupName;
    private LocalDateTime createdDate;

    public static UserResponse fromUser(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .groupName(user.getUserGroup().getGroupName())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
