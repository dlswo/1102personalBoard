package org.injae.domain;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
	private int uno;
	private String userid, userpw, urole, uname;
	private Date uregdate;
	private boolean del;
	
}
