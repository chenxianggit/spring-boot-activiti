package com.cx.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetUserRequest extends BaseRequest {
	private static final long serialVersionUID = 1L;
	
	private Long id;
}
