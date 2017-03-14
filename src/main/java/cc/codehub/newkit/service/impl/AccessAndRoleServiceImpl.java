package cc.codehub.newkit.service.impl;

import cc.codehub.newkit.dao.AccessRepository;
import cc.codehub.newkit.dao.AccessRoleMapRepository;
import cc.codehub.newkit.dao.RoleRepository;
import cc.codehub.newkit.dao.RoleUserMapRepository;
import cc.codehub.newkit.model.*;
import cc.codehub.newkit.service.AccessAndRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccessAndRoleServiceImpl implements AccessAndRoleService{

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private AccessRoleMapRepository accessRoleMapRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleUserMapRepository roleUserMapRepository;


    @Override
    public List<Access> getAllAccesses() {
        return accessRepository.findAll();
    }

    @Override
    public Page<Access> getAllAccesses(Pageable pageable) {
        return accessRepository.findAll(pageable);
    }

    @Override
    public Access createAccess(Access access) {
        access.setAccessId(null);
        return accessRepository.save(access);
    }

    @Override
    public Access updateAccess(Access access) {
        return accessRepository.save(access);
    }

    @Override
    public void deleteAccess(Integer id) {
        accessRepository.delete(id);
    }

    @Override
    public boolean existsAccessName(String name) {
        Access access = new Access();
        access.setName(name);
        return accessRepository.exists(Example.of(access));
    }

    @Override
    public boolean existsAccessUrl(String url) {
        Access access = new Access();
        access.setUrl(url);
        return accessRepository.exists(Example.of(access));
    }

    @Override
    public Access getAccessByAccessId(Integer id) {
        return accessRepository.findOne(id);
    }

    @Override
    public List<String> getAccessUrlsByRoleId(Integer roleId) {
        return accessRepository.findUrlsByRoleId(roleId);
    }

    @Override
    public Role createRole(Role role) {
        role.setRoleId(null);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.delete(id);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Role getRoleByRoleId(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<String> getRoleNamesByAccessId(Integer accessId) {
        return roleRepository.findRoleNamesByAccessId(accessId);
    }

    @Override
    public boolean existsRoleName(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.exists(Example.of(role));
    }

    @Override
    public List<AccessRoleMap> getAllAccessRoleMapsByRoleId(Integer roleId) {
        return accessRoleMapRepository.findByRoleId(roleId);
    }

    @Override
    public AccessRoleMap createAccessRoleMap(AccessRoleMap accessRoleMap) {
        accessRoleMap.setMapId(null);
        return accessRoleMapRepository.save(accessRoleMap);
    }

    @Override
    public void deleteAccessRoleMapByRoleId(Integer roleId) {
        accessRoleMapRepository.deleteByRoleId(roleId);
    }

    @Override
    public void deleteAccessRoleMap(Integer mapId) {
        accessRoleMapRepository.delete(mapId);
    }


    @Override
    public List<String> getRoleNamesByUser(User user) {
        return roleUserMapRepository.findRoleNamesByUserId(user.getUid());
    }

    @Override
    public List<RoleUserMap> getAllRoleUserMapsByUserId(int userId) {
        return roleUserMapRepository.findByUid(userId);
    }

    @Override
    public RoleUserMap createRoleUserMap(RoleUserMap roleUserMap) {
        roleUserMap.setMapId(null);
        return roleUserMapRepository.save(roleUserMap);
    }

    @Override
    public void deleteRoleUserMapByUserId(int userId) {
        roleUserMapRepository.deleteByUid(userId);
    }

    @Override
    public void deleteRoleUserMapByUserIdAndRoleId(int userId, int roleId) {
        roleUserMapRepository.deleteByUidAndRoleId(userId, roleId);
    }
}
