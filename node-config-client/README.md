# Node.js Cloud Config Client

Node.js application that demonstrates integration with a Spring Cloud Config
server for its configuration properties. This is one of several such example
client implementations, intended to demonstrate that Spring Cloud Config can
be leveraged in a cloud provider agnostic way across multiple languages.

## Road Map

Specific milestones for this module are:

1. Update build so that this project is actually a submodule of the Gradle build
for the parent, which means integration of NPM and Gradle
1. Update build so that the `npm start` or simialr task depends on a running
instance of the [Cloud Config Serer](../cloud-config-server) backed by HashiCorp
Vault.
1. Update build so that it includes integration tests with a running instance of
the [Cloud Config Serer](../cloud-config-server) backed by HashiCorp Vault.
1. Implement a simple web service endpoint to query the current configuration
settings, adhering to the same interface as the [Java Cloud Config Client](,,/cloud-config-client)
1. Leverage the same Angular SPA to reder the configuration properties read from
the web service endpoint as the [Java Cloud Config Client](,,/cloud-config-client)
1. Require SSL using self-signed certificates for access to the Cloud Config Server
1. Require authentication for access to the Cloud Config Server
1. Implement access control policies to restrict what properties are visible based
on who is asking for them
1. Implement the ability to set configuration properties via web endpoint with
authentication and authorization
