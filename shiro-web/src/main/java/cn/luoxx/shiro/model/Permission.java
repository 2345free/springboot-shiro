package cn.luoxx.shiro.model;

import lombok.Data;

@Data
public class Permission {

    private Integer id;

    private String permissionname;

    /**
     * 一个权限对应一个角色
     */
    private Role role;

}
