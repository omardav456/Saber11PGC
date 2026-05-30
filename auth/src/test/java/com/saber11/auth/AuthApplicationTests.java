package com.saber11.auth;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
	"DB_URL=jdbc:h2:mem:testdb",
	"DB_USERNAME=sa",
	"DB_PASSWORD=",
	"spring.datasource.driver-class-name=org.h2.Driver",
	"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class AuthApplicationTests {

	@Test
	void main_CallsSpringApplicationRun() {
		try (MockedStatic<SpringApplication> mocked = Mockito.mockStatic(SpringApplication.class)) {
			AuthApplication.main(new String[]{});
			mocked.verify(() -> SpringApplication.run(AuthApplication.class, new String[]{}));
		}
	}

}
