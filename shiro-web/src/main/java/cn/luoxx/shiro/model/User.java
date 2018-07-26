package cn.luoxx.shiro.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class User {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;
    
    /**
     * 一个用户具有多个角色
     */
    private List<Role> roleList = new ArrayList<Role>();

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Transient
    public Set<String> getRolesName() {
        List<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            System.out.println("getRolesName--------------");
            set.add(role.getRolename());
        }
        return set;
    }

}
