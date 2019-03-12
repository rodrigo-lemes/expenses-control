package br.com.rsoukef.expensesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@SpringBootApplication
public class ExpensesManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesManagementApplication.class, args);
	}
	
	@Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("expenses");
    }

}
