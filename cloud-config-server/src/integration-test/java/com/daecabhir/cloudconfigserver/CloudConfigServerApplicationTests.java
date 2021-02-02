package com.daecabhir.cloudconfigserver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.vault.core.VaultVersionedKeyValueOperations;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.vault.support.Versioned;

import java.util.List;


@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// Have to list them all because it doesn't want to pick them up from src/main/resources/application.yml
@ActiveProfiles({"test", "native", "vault"})
public class CloudConfigServerApplicationTests {

	private static final String CLIENT_APP_PATH = "/cloud-config-example/client-app";

	@Autowired
	private VaultVersionedKeyValueOperations kvOperations;

	@Value("${test.vault.secretsPath}")
	private String secretsPath;

	private String dataPath;

	private String defaultSecretsPath;

	private ClientAppSecrets secrets;

	@BeforeAll
	private void beforeAll() {
		secrets = ClientAppSecrets.builder()
				.username("myuser")
				.password("mypassword")
				.build();

		Versioned.Metadata metadata = kvOperations.put(CLIENT_APP_PATH, Versioned.create(secrets));
		assertThat(metadata.isDestroyed(), is(false));
		assertThat(metadata.isDeleted(), is(false));
		assertThat(metadata.getCreatedAt(), is(notNullValue()));
	}

	@Test
	public void secretsStored () {
		List<String> keys = kvOperations.list("/");
		log.info("Root Keys: {}", keys);
		keys = kvOperations.list(CLIENT_APP_PATH);
		log.info("Client Keys: {}", keys);

		Versioned<ClientAppSecrets> response = kvOperations.get(CLIENT_APP_PATH, ClientAppSecrets.class);
		assertThat(response.hasData(), is(true));
		ClientAppSecrets storedSecrets = response.getData();
		assertThat(storedSecrets, is(equalTo(secrets)));
	}

	@Jacksonized
	@Builder
	@EqualsAndHashCode
	public static class ClientAppSecrets {
		@Getter
		@Setter
		private String username;

		@Getter
		@Setter
		private String password;
	}
}
