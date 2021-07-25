package parominsky.evgeny.belbanka.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import parominsky.evgeny.belbanka.dto.UserDTO;
import parominsky.evgeny.belbanka.entity.User;
import parominsky.evgeny.belbanka.exception.UserAlreadyExistException;
import parominsky.evgeny.belbanka.repository.UserRepository;

import java.util.List;

public interface UserService extends Service<Integer, User, UserRepository>, UserDetailsService {

    User addUser(UserDTO user) throws UserAlreadyExistException;

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Integer id);

    void editUser(UserDTO user);

    User createAdmin(UserDTO userDTO);

    boolean deleteUser(Integer id);

    default User getUser(String name) {
        return getRepository().findByName(name).orElse(null);
    }

    List<User> filterUser(String filterText);
}