package com.bssd.boffice.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUserRequest {
    private int page;
    private int pageSize;
}
