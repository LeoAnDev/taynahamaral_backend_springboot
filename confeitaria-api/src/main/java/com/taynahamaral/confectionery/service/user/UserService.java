package com.taynahamaral.confectionery.service.user;

import com.taynahamaral.confectionery.domain.profile.Gender;
import com.taynahamaral.confectionery.domain.profile.Profile;
import com.taynahamaral.confectionery.domain.role.Role;
import com.taynahamaral.confectionery.domain.role.RoleName;
import com.taynahamaral.confectionery.domain.user.User;
import com.taynahamaral.confectionery.domain.user.exception.UserAlreadyExistsException;
import com.taynahamaral.confectionery.repository.user.UserRepository;
import com.taynahamaral.confectionery.repository.role.RoleRepository;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUser(
            String name,
            String email,
            String password,
            Gender gender,
            String birthDateString,
            String whatsapp
    ) {

        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        // Buscar role padrão CUSTOMER
        Role customerRole = roleRepository.findByName(RoleName.CUSTOMER.name())
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER not found"));

        // Criar usuário
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);

        // Converter String para LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        // Criar profile
        Profile profile = new Profile();
        profile.setGender(gender);
        profile.setBirthDate(birthDate);
        profile.setWhatsapp(whatsapp);

        // Associar profile ao user
        profile.setUser(user);
        user.setProfile(profile);

        // Associar role
        user.setRoles(Set.of(customerRole));

        return userRepository.save(user);
    }
}
