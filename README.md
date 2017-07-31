transactional: Money Transfer Service
=====================================
Author: Roman Tokarev
Level: Intermediate
Technologies: EAR, JPA
Summary: Based on kitchensink, but deployed as an EAR
Target Project: WildFly
Source: https://github.com/opensearch/revolut/>

Requirements: WildFly 10, Java 8, Maven.
----------------------------------------
1. Clone project

    git clone git@github.com:opensearch/revolut.git

2. download and unzip http://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip.

3. Start Wildfly AC:

    <WildflyInstallDir>/bin/standalone.sh

4. Go to 'revolut' and execute:

    mvn clean compile package wildfly:deploy

Operations
----------
- Show all clients:

    http://localhost:8080/transactional-web/rest/clients

- Show all accounts:

    http://localhost:8080/transactional-web/rest/accounts

- Money transfer:

    http://localhost:8080/transactional-web/rest/transfer?from=0&to=1&currency=USD&amount=100.00