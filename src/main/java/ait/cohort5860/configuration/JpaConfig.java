package ait.cohort5860.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (30.06.2025)
 */
@Configuration
@EnableJpaRepositories(basePackages = "ait.cohort5860.post.dao")
@EnableTransactionManagement
public class JpaConfig {
}
