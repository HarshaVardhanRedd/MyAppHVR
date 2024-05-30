package com.hvr.signup;

import lombok.Data;

@Data
public class SignUpResponseData {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
