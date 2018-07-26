package cn.luoxx.shiro.model;

import lombok.Data;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
public class Role {

    private Integer id;

    private String rolename;

    /**
     * 一个角色对应多个权限
     */
    private List<Permission> permissionList;

    /**
     * 一个角色对应多个用户
     */
    private List<User> userList;

    /**
     * Transient：短暂的，瞬态
     * 作用：表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.
     */
    @Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Permission> perlist = getPermissionList();
        for (Permission per : perlist) {
            list.add(per.getPermissionname());
        }
        return list;
    }
}
