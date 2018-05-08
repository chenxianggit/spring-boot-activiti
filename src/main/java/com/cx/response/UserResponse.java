package com.cx.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserResponse extends BaseResponse {
	private static final long serialVersionUID = -2219724352386175871L;

    private long id;
    
    private String name;
}
