package parominsky.evgeny.belbanka.service.impl;

import com.google.common.collect.Collections2;
import lombok.SneakyThrows;
import lombok.val;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import parominsky.evgeny.belbanka.dto.RoleDTO;
import parominsky.evgeny.belbanka.dto.UserDTO;
import parominsky.evgeny.belbanka.entity.Role;
import parominsky.evgeny.belbanka.entity.User;
import parominsky.evgeny.belbanka.exception.NotExistsException;
import parominsky.evgeny.belbanka.exception.UserAlreadyExistException;
import parominsky.evgeny.belbanka.repository.RoleRepository;
import parominsky.evgeny.belbanka.repository.UserRepository;
import parominsky.evgeny.belbanka.service.UserService;
import parominsky.evgeny.belbanka.service.mapper.RoleDTOMapper;
import parominsky.evgeny.belbanka.service.mapper.UserDTOMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.difference;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.stream.Collectors.toSet;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private UserDTOMapper userMapper;
    private RoleDTOMapper roleMapper;
    private BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserServiceImpl(@Lazy BCryptPasswordEncoder passwordEncoder,
                           UserRepository userRepo, RoleRepository roleRepo,
                           UserDTOMapper userMapper, RoleDTOMapper roleMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserRepository getRepository() {
        return userRepo;
    }

    @Override
    public User addUser(UserDTO userDTO) throws UserAlreadyExistException {
        User user = getUser(userDTO.getName());
        if (user == null) {
            user = userMapper.toEntity(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Collection<Role> roles = userDTO.getRoles().stream().map(r ->
                    roleRepo.findRoleByName(r.toString()).get()).collect(toSet());
            user.setRoles(roles);
            return userRepo.save(user);
        } else {
            throw new UserAlreadyExistException();
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> usersDTO = users.stream().map(user -> userMapper.toDTO(user, changeType(user))).collect(Collectors.toList());
        return usersDTO;
    }

    public Collection<RoleDTO> changeType(User user){
        Collection<Role> roles = user.getRoles();
        Collection<RoleDTO> roleDTOS = roles.stream().map(role -> roleMapper.toDTO(role)).collect(toSet());
        return roleDTOS;
    }


    @Override
    @SneakyThrows
    public UserDTO getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() ->
                new NotExistsException("Invalid user Id:" + id));
        Collection<Role> roles = user.getRoles();
        System.out.println(roles);
        Collection<RoleDTO> roleDTOS = roles.stream().map(role -> roleMapper.toDTO(role)).collect(toSet());
        System.out.println(roleDTOS);
        return userMapper.toDTO(user, roleDTOS);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void editUser(UserDTO userDTO) {
        User oldUser = getById(userDTO.getId());//берем из бд по id
        if (oldUser != null) {
            User newUser = userMapper.toEntity(userDTO, userDTO.getRoles());
           if (!oldUser.getPassword().equals(newUser.getPassword())) {
               String pass = passwordEncoder.encode(userDTO.getPassword());
               newUser.setPassword(pass);
            }
            if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
                val r1 = Collections2.transform(oldUser.getRoles(), u -> u.getName());
                val r2 = Collections2.transform(userDTO.getRoles(), u -> u.toString());
                if (!difference(newHashSet(r1), newHashSet(r2)).isEmpty()
                        || !difference(newHashSet(r2), newHashSet(r1)).isEmpty()) {
                    oldUser.setRoles(null);
                    userRepo.save(oldUser);
                    newUser.setRoles(roleRepo.findByNameIn(r2));
                } else {
                    newUser.setRoles(oldUser.getRoles());
                }
                userRepo.save(newUser);
            } else throw new NotExistsException("Invalid user Id:" + userDTO.getId());
        }
    }

    @Override
    public User createAdmin(UserDTO userDTO) {
        User user = userRepo.save(userMapper.toEntity(userDTO));
        Collection<Role> roles;
        List<Role> rls = roleRepo.findAll();
        if (rls != null && !rls.isEmpty()) {
            Optional<Role> roleOptional = roleRepo.findRoleByName("ROLE_ADMIN");
            if (!roleOptional.isPresent()) {
                rls.add(roleMapper.toEntity(RoleDTO.ROLE_ADMIN));
            }
            roles = newHashSet(rls);
        } else {
            roles = newHashSet(
                    new Role("ROLE_ADMIN"),
                    new Role("ROLE_ENGINEER"),
                    new Role("ROLE_DIRECTOR"),
                    new Role("ROLE_WORKER")
            );
        }
        user.setRoles(roles);
        return userRepo.save(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return delete(id) ? true : false;
    }

    @Override
    public List<User> filterUser(String filterText) {
        Session session = em.unwrap(Session.class);
        Criterion criterion1 = Restrictions.ilike("name", "%" + filterText + "%");
       Criterion criterion2 = Restrictions.ilike("a.lastName", "%" + filterText + "%");
        return null;
    }

//    @Override
//    public List<Book> filterBook(String str) {
//        Session session = em.unwrap(Session.class);
//        Criterion criterion1 = Restrictions.ilike("name", "%" + str + "%");
//        Criterion criterion2 = Restrictions.ilike("a.lastName", "%" + str + "%");
//        List books = session.createCriteria(Book.class)
//                .createAlias("author", "a")
//                .add(Restrictions.or(criterion1, criterion2))
//                .setFetchMode("author", FetchMode.EAGER)
//                .list();
//        return books;
//    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(toSet());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = getUser(name);
        if (user == null) {
            System.out.println("Invalid user name or password");
            throw new UsernameNotFoundException("Invalid user name or password");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }
}