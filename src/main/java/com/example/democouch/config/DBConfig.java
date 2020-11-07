package com.example.democouch.config;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DBConfig {

    @Value("${couchdb.protocol}")
    private String protocol;

    @Value("${couchdb.host}")
    private String host;

    @Value("${couchdb.port}")
    private String port;

    @Value("${couchdb.username}")
    private String username;

    @Value("${couchdb.password}")
    private String password;

    @Value("${couchdb.name}")
    private String dbName;

    @Bean
    public CouchDbConnector couchDbConnector() {
        try {
            HttpClient httpClient = new StdHttpClient.Builder()
                    .url(protocol + "://" + host + ":" + port).username(username)
                    .password(password)
                    .build();

            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
            return dbInstance.createConnector(dbName, true);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
