package com.exa.fetch;

public class mess_loc {
	private String name;
	private String contact;
	private String address;
	public mess_loc(String name, String contact,String address) {
		super();
		this.name = name;
		this.contact = contact;
		this.address=address;
		//this.condition = condition;
	}
	
	public String getname() {
		return name;
	}
	public String getcontact() {
		return contact;
	}
	public String getaddress() {
		return address;
	}

}
