# Spring Cloud Config Example Using Vault

This is an example project to demonstrate using
[Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/) with
[HashiCorp Vault](https://www.vaultproject.io/) and [Git](https://git-scm.com/)
repositories to serve up configuration properties seamlessly to applications.
In today's highly distributed, DevOps-driven world, the ability to maintain
and distribute system and application configurations in a way that supports
falling back to a "known good state" when updates go awry is incredibly
important to deploying stable, resilient systems. At the same time, credentials
and similarly "sensitive" configuration information should not necessarily be
stored in those CM systems (and even with the use of encryption a'la
[Ansible Vault](https://docs.ansible.com/ansible/latest/user_guide/vault.html),
regulatory or corporate strictures may outright prohibit the storage of
"secrets" in a CM repository).

Spring Cloud Config is an excellent technology for distributed application
configuration, because:

* Spring Cloud Config provides out-of-the-box support for serving up application
configuration properties from multiple sources including Git, S3, SQL databases,
and HashiCorp Vault.

* While written in Java and implemented on top of the Spring Framework, the
Spring Cloud Config Server exposes a RESTful API that be leveraged by clients
written in any language that supports HTTP/HTTPS.

* Non-Java client libraries exist for poplular languages such as JavaScript,
Python and Go.

* Access to the RESTful API can be secured using any of the mechanisms supported
by Spring Security, including custom authentication and authorization modules.

* Spring Cloud Config Server provides a RESTful API for accepting notifications
of property source configurations (e.g., via an GitHub commit webhook), and pushing
notifications of changes to affected applications if the client supports it.

But one of the things I've found lacking is that most examples are fairly
simplistic, and don't necessarily reflect how Spring Cloud Config and its
supporting infrastructure should be deployed, and how it's not just for
Spring Boot applications running on a JVM. This project will seek to fill
that gap, iteratively, as I explore how to construct a robust distributed
configuration subsystem on the Spring Cloug Config foundation.

## Subprojects

This example contains several subprojects, or "modules" in Gradle terminology.

* [cloud-config-server] - The Spring Cloud Config server, which will evolve from
just a vehicle for accessing configuration properties to an application for
managing "sensitive" configuration data contained in vault with an embedded
Spring Cloud Config server.

* [cloud-config-client] - A simple Spring Boot application to illustrate the
basic use case for Spring Cloud Config.

* [node-config-client] - A simple Node.js application to illustrate how a
non-Java application can leverage Spring Cloud Config.

## Road Map

This is a work in progress, and is intended to evolve over time as my understanding
of how Spring Cloud Config works grows, and new use cases present themselves. As of
this writing, the basic goals / path for the project are as follows:

1. Enable building, testing and running the complete project or a subset (e.g.
the config server and the Node.js client) using Gradle multi-module support and
Docker.
1. Support localized, per-developer settings to allow testing against other Git
repositories as sources of configuration properties.
1. Integrate builds into Travis
1. Implement a common Angular SPA that can be served up by the client applications
to display the configuration properties known to the client applications at run-time.
1. Support secure, authenticated access to the Cloud Config Server.
1. Support access control for specific property sets based on who is requesting them.
1. Add the ability to manage secret properties through the Cloud Config Server
using an Angular SPA, with authentication and authorization.
1. Add client implementations in Python, Rust and maybe Go.
