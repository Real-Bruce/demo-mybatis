package cn.bruce.dao;

import cn.bruce.domain.Role;

import java.util.List;

/**
 * @author Bruce
 * @create 2020-09-16 9:45
 */
public interface IRoleDao {

    /**
     * 获取全部用户
     * @return
     */
    List<Role> getRoles();
}
