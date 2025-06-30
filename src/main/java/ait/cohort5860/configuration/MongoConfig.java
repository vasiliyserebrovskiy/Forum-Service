package ait.cohort5860.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (30.06.2025)
 */
@Configuration
@EnableMongoRepositories(basePackages = "ait.cohort5860.accounting.dao")
public class MongoConfig {
}
