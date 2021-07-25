package parominsky.evgeny.belbanka.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parominsky.evgeny.belbanka.dto.RoleDTO;
import parominsky.evgeny.belbanka.repository.RoleRepository;
import parominsky.evgeny.belbanka.service.RoleService;
import parominsky.evgeny.belbanka.service.mapper.RoleDTOMapper;

import java.util.List;

import static com.google.common.collect.Lists.transform;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepo;
    private RoleDTOMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepo, RoleDTOMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleRepository getRepository() {
        return roleRepo;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> roleDTOs = transform(getAll(), roleMapper::toDTO);
        return roleDTOs;
    }
}