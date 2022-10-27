package com.bssd.boffice.application.dto.response;


import com.bssd.boffice.application.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class PageUserResponse {

    private int currentPage;
    private int totalPage;
    private int totalItem;
    private List<User> userResponseList;

    public static PageUserResponse fromPageUser(Page<User> userResponsePage){
        return PageUserResponse.builder()
                .currentPage(userResponsePage.getNumber())
                .totalPage(userResponsePage.getTotalPages())
                .totalItem(userResponsePage.getNumberOfElements())
                .userResponseList(userResponsePage.getContent())
                .build();
    }
}
