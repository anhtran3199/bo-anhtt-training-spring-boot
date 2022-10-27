package com.bssd.boffice.application.dto.request;

import com.bssd.boffice.application.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
public class UserRequest implements Serializable {
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Invalid username")
    private String username;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Invalid password")
    private String password;

    @NotBlank(message = "fullname cannot blank")
    private String fullname;

    @Email(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", message = "Invalid email")
    private String email;

    @Pattern(regexp = "^[0-9]{10,11}$", message = "Invalid phone number")
    private String phone;

    @NotNull(message = "group_id cannot blank")
    private long groupId;

    public static UserRequest fromUser(User user){
        if(user == null){
            return null;
        }

        return UserRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .groupId(user.getUserGroup().getId())
                .build();
    }
}
