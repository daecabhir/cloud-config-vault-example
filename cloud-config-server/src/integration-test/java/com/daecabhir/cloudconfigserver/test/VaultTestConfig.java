package com.daecabhir.cloudconfigserver.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.core.*;

@Configuration
public class VaultTestConfig extends AbstractVaultConfiguration {

  final String vaultHost;
  final int vaultPort;
  final String authToken;
  final String secretsPath;

  public VaultTestConfig(
    @Value("${test.vault.host}") final String vaultHost,
    @Value("${test.vault.port}") final int vaultPort,
    @Value("${test.vault.token}") final String authToken,
    @Value("${test.vault.secretsPath}") final String secretsPath) {

    this.vaultHost = vaultHost;
    this.vaultPort = vaultPort;
    this.authToken = authToken;
    this.secretsPath = secretsPath;
  }

  @Override
  public VaultEndpoint vaultEndpoint() {
    final VaultEndpoint endpoint = new VaultEndpoint();
    endpoint.setHost(vaultHost);
    endpoint.setPort(vaultPort);
    endpoint.setScheme("http");
    return endpoint;
  }

  @Override
  public ClientAuthentication clientAuthentication() {
    return new TokenAuthentication(authToken);
  }

  @Bean
  public VaultVersionedKeyValueOperations versionedKeyValueOperations(@Autowired VaultOperations vaultOperations) {
    return vaultOperations.opsForVersionedKeyValue(secretsPath);
  }
}
