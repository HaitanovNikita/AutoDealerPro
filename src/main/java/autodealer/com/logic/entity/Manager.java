package autodealer.com.logic.entity;

import javax.persistence.*;

@Entity
@Table(name="Manager")
public class Manager {

	private int ID;
	private String manager_fname;
	private String manager_lname;
	private String manager_post;
	private String manager_phone_num;
	private int manager_age;
	private String manager_email;
	private String manager_gender;
	private String manager_login;
	private String manager_pass;

	public Manager() {}
	
	public Manager(int ID, String manager_fname, String manager_lname, String manager_post, String manager_phone_num,
                   int manager_age, String manager_email, String manager_gender,String manager_login, String manager_pass) {
		
		this.ID = ID;
		this.manager_fname = manager_fname;
		this.manager_lname = manager_lname;
		this.manager_post = manager_post;
		this.manager_phone_num = manager_phone_num;
		this.manager_age = manager_age;
		this.manager_email = manager_email;
		this.manager_gender = manager_gender;
		this.manager_login = manager_login;
		this.manager_pass = manager_pass;
	}

	public String printDataManager(){
	return ""+ID+" "+manager_fname+" "+manager_lname+" "+
			manager_post+" "+manager_phone_num+" "+
			manager_age+" "+manager_email+" "+
			manager_gender+" "+manager_login+" "+manager_pass+"\n";
	}

	@Id
	@JoinColumn(name="ID")
	public int getID() {
		return ID;
	}

	@Column(name="manager_fname")
	public String getManager_fname() {
		return manager_fname;
	}

	@Column(name="manager_lname")
	public String getManager_lname() {
		return manager_lname;
	}

	@Column(name="manager_phone_num")
	public String getManager_phone_num() {
		return manager_phone_num;
	}

	@Column(name="manager_age")
	public int getManager_age() {
		return manager_age;
	}	 
	
	@Column(name="manager_email")
	public String getManager_email() {
		return manager_email;
	}
	
	@Column(name="manager_gender")
	public String getManager_gender() {
		return manager_gender;
	}
	
	@Column(name="manager_post")
	public String getManager_post() {
		return manager_post;
	}

	@Column(name="manager_login")
	public String getManager_login() {
		return manager_login;
	}

	@Column(name="manager_pass")
	public String getManager_pass() {
		return manager_pass;
	}

	public void setManager_login(String manager_login) {
		this.manager_login = manager_login;
	}

	public void setManager_pass(String manager_pass) {
		this.manager_pass = manager_pass;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setManager_fname(String manager_fname) {
		this.manager_fname = manager_fname;
	}

	public void setManager_lname(String manager_lname) {
		this.manager_lname = manager_lname;
	}

	
	public void setManager_phone_num(String manager_phone_num) {
		this.manager_phone_num = manager_phone_num;
	}

	public void setManager_age(int manager_age) {
		this.manager_age = manager_age;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}


	public void setManager_gender(String manager_gender) {
		this.manager_gender = manager_gender;
	}

	public void setManager_post(String manager_post) {
		this.manager_post = manager_post;
	}

	
	@Override
	public String toString() {
		return "Manager № "+getID()+ "\n[\n ID=" + ID + ",\n manager_fname=" + manager_fname + ",\n manager_lname=" + manager_lname
				+ ",\n manager_post=" + manager_post + ",\n manager_phone_num=" + manager_phone_num + ",\n manager_age="
				+ manager_age + ",\n manager_email=" + manager_email + ",\n manager_gender=" + manager_gender + "\n]";
	}

	
		
	
	
}
