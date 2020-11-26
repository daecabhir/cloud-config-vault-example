package com.daecabhir.cloudconfigserver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// Have to list them all because it doesn't want to pick them up from src/main/resources/application.yml
@ActiveProfiles({"test", "native", "vault"})
public class CloudConfigServerApplicationTests {

	@Autowired
	private VaultTemplate vaultTemplate;

	private ClientAppSecrets secrets;

	@BeforeAll
	private void beforeAll() {
		secrets = new ClientAppSecrets();
		secrets.setUsername("myuser");
		secrets.setPassword("mypassword");

		vaultTemplate.write("secrets/client-app", secrets);
	}

	@Test
	public void secretsStored () {
		VaultResponseSupport<ClientAppSecrets> response = vaultTemplate.read("secrets/client-app", ClientAppSecrets.class);
		ClientAppSecrets storedSecrets = response.getData();
		assertThat(storedSecrets, is(equalTo(secrets)));
	}

	@EqualsAndHashCode
	public class ClientAppSecrets {
		@Getter
		@Setter
		private String username;

		@Getter
		@Setter
		private String password;
	}
}
