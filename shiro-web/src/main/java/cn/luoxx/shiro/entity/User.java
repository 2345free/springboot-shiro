package cn.luoxx.shiro.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private Integer id;
    private String username;
    private String password;    
    private List<Role> roleList=new ArrayList<Role>();// 一个用户具有多个角色

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Set<String> getRolesName() {
        List<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
        	System.out.println("getRolesName--------------");
            set.add(role.getRolename());
        }
        return set;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
}
