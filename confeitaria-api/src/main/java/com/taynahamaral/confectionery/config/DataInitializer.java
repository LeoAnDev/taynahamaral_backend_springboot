package com.taynahamaral.confectionery.config;

import com.taynahamaral.confectionery.domain.role.Role;
import com.taynahamaral.confectionery.repository.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {

        if (!roleRepository.existsByName("CUSTOMER")) {
            roleRepository.save(
                    new Role("CUSTOMER", "Default customer role")
            );
        }
    }
}
