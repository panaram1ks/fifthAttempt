package parominsky.evgeny.belbanka.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import parominsky.evgeny.belbanka.dto.RoleDTO;
import parominsky.evgeny.belbanka.dto.UserDTO;
import parominsky.evgeny.belbanka.entity.Role;
import parominsky.evgeny.belbanka.entity.User;

import java.util.Set;

@Component
public class UserDTOMapper extends EntityDTOMapper<UserDTO, User> {

    private static final PropertyMap<UserDTO, User> TO_ENTITY = new PropertyMap<UserDTO, User>() {
        @Override
        protected void configure() {
            skip().setRoles(null);
        }
    };
    private static final PropertyMap<User, UserDTO> TO_DTO = new PropertyMap<User, UserDTO>() {
        @Override
        protected void configure() {
            skip().setRoles(null);
        }
    };
    private ModelMapper mapper;

    @Autowired
    public UserDTOMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserDTO toDTO(User entity, Object... args) {
        UserDTO dto = mapToDTO(mapper, TO_DTO, entity);
        if (args.length == 1) {
            Set<RoleDTO> roles = (Set<RoleDTO>) args[0];
            dto.setRoles(roles);
        }
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto, Object... args) {
        User entity = mapToEntity(mapper, TO_ENTITY, dto);
        if (args.length == 1) {
            Set<Role> roles = (Set<Role>) args[0];
            entity.setRoles(roles);
        }
        return entity;
    }
}