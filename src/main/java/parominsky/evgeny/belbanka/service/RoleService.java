package parominsky.evgeny.belbanka.service;



import parominsky.evgeny.belbanka.dto.RoleDTO;
import parominsky.evgeny.belbanka.entity.Role;
import parominsky.evgeny.belbanka.repository.RoleRepository;

import java.util.List;

public interface RoleService extends Service<Integer, Role, RoleRepository> {
    List<RoleDTO> getAllRoles();
}