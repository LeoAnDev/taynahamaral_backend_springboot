package com.taynahamaral.confectionery.service.user;

import com.taynahamaral.confectionery.domain.profile.Profile;
import com.taynahamaral.confectionery.domain.role.Role;
import com.taynahamaral.confectionery.domain.user.User;
import com.taynahamaral.confectionery.repository.user.UserRepository;
import com.taynahamaral.confectionery.repository.role.RoleRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User registerUser(
            String name,
            String email,
            String password,
            String gender,
            String birthDateString, // vem como String
            String whatsapp
    ) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("E-Mail já cadastrado");
        }

        // Buscar role padrão CUSTOMER
        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER not found"));

        // Criar usuário
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // depois vamos colocar BCrypt
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
