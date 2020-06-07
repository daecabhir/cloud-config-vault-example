# Cloud Config Server

Implementation of an embedded Spring Cloud Config Server whose primary purposes
are to:

* Demonstrate how to configure composite environment repositories using Git for
non-sensistive properties under CM control and
[HashiCorp Vault](https://www.vaultproject.io/) for encrypted storage of sensistive
property values such as database passwords.

* Support Spring Cloud Config client applications to demonstrate how Java and
non-Java applications can retrieve their run-time properties from a Spring
Cloud Config Server.

## Road Map

Specific milestones for this module are:

1. Update build so that this project is actually a submodule of the Gradle build
for the parent.
1. Update the build so that it can launch the Vault Docker container during bootRun
1. Update build so that it includes integration tests to verify proper configuration,
including launching and pre-loading Vault with secret values.
1. Update build so that it can spin up the Cloud Config Server in its own Docker
container, taking the the ports from a .env file or something similar
1. Require SSL using self-signed certificates for access to the Cloud Config Server
1. Require authentication for access to the Cloud Config Server
1. Implement access control policies to restrict what properties are visible based
on who is asking for them
1. Implement the ability to set configuration properties via web endpoint with
authentication and authorization
1. Implement Angular SPA for managing properties in Vault.
