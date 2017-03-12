package cc.codehub.newkit.service;

import cc.codehub.newkit.model.Access;
import cc.codehub.newkit.model.AccessRoleMap;
import cc.codehub.newkit.model.Role;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by Li Di on 2017/3/9.
 */
public interface AccessAndRoleService {

    List<Access> getAccessList();

    Iterable<Access> getAllAccesses(Pageable pageable);

    Access createAccess(Access access);

    Access updateAccess(Access access);

    void deleteAccess(Integer id);

    boolean existsAccessName(String name);

    boolean existsAccessUrl(String url);

    Access getAccess(Integer id);

    List<String> findAccessUrlByRoleId(Integer roleId);


    public Role addRole(Role role);

    public void delRole(Integer id);

    public Role updateRole(Role role);

    public Iterable<Role> getAllRoles(Pageable pageable);

    public Role findByRoleId(Integer id);

    public List<String> findRoleNamesByAccessId(Integer accessId);

    boolean existsRoleName(String name);


    List<AccessRoleMap> getAllAccessRoleMapsByRoleId(Integer roleId);

    AccessRoleMap createAccessRoleMap(AccessRoleMap accessRoleMap);

    void deleteAccessRoleMapByRoleId(Integer roleId);

    void deleteAccessRoleMapByMapId(Integer mapId);


    public Set<String> findRoleNamesByUser(User user);

    /**
     * 获取员工角色
     * @param employeeId
     * @return
     */
    Iterable<RoleEmployeeMap> getAllEmployeeRole(int employeeId);

    /**
     * 添加员工角色
     * @param roleEmployeeMap
     * @return
     */
    RoleEmployeeMap addEmployeeRoleMap(RoleEmployeeMap roleEmployeeMap);

    /**
     * 删除员工角色
     * @param employeeId
     */
    void deleteEmployeeRoleByEmployeeId(int employeeId);

    /**
     * 删除单条员工角色
     * @param employeeId
     * @param roleId
     */
    void deleteEmployeeRoleByEmployeeIdAndRoleId(int employeeId, int roleId);
}
