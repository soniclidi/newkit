package cc.codehub.newkit.service;

import cc.codehub.newkit.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


public interface AccessAndRoleService {

    List<Access> getAllAccesses();

    Page<Access> getAllAccesses(Pageable pageable);

    Access createAccess(Access access);

    Access updateAccess(Access access);

    void deleteAccess(Integer id);

    boolean existsAccessName(String name);

    boolean existsAccessUrl(String url);

    Access getAccessByAccessId(Integer id);

    List<String> getAccessUrlsByRoleId(Integer roleId);


    Role createRole(Role role);

    void deleteRole(Integer id);

    Role updateRole(Role role);

    List<Role> getAllRoles();

    Page<Role> getAllRoles(Pageable pageable);

    Role getRoleByRoleId(Integer id);

    List<String> getRoleNamesByAccessId(Integer accessId);

    boolean existsRoleName(String name);


    List<AccessRoleMap> getAllAccessRoleMapsByRoleId(Integer roleId);

    AccessRoleMap createAccessRoleMap(AccessRoleMap accessRoleMap);

    void deleteAccessRoleMapByRoleId(Integer roleId);

    void deleteAccessRoleMap(Integer mapId);


    List<String> getRoleNamesByUser(User user);

    List<RoleUserMap> getAllRoleUserMapsByUserId(int userId);

    RoleUserMap createRoleUserMap(RoleUserMap roleUserMap);

    void deleteRoleUserMapByUserId(int userId);

    void deleteRoleUserMapByUserIdAndRoleId(int userId, int roleId);

}
