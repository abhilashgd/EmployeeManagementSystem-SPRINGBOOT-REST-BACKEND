package com.employee_management_system.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.employee_management_system.domain")
@EnableJpaRepositories("com.employee_management_system.repos")
@EnableTransactionManagement
public class DomainConfig {
}
