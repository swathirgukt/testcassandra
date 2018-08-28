/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 *
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import com.datastax.driver.core.AuthProvider
import com.datastax.driver.core.PlainTextAuthProvider
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

import javax.annotation.PostConstruct

/**
 * This is the configuration class for cassandra configuration for search data
 *
 * User: Madhu Alagandula
 * Date: 3/28/2017
 * Time: 7:08 PM
 */
@Slf4j
@SpringBootApplication(exclude = [DataSourceAutoConfiguration, HibernateJpaAutoConfiguration])
@EnableCassandraRepositories(basePackages = ['com.example.demo'])
class SearchDataRepositoryConfiguration extends AbstractCassandraConfiguration {
    String keyspaceName = 'search_data_new'
    String contactPoints = '34.224.241.107,52.45.178.107,34.225.180.40'
    String port = '9042'
    String username = 'iccassandra'
    String password = '2db96178b72cf88d868f08dda7556958'
    /*String keyspaceName = 'searchdata'
    String contactPoints = '192.168.10.224'
    String port = '9042'
    String username = 'cassandra'
    String password = 'cassandra'*/

    @Override
    String[] getEntityBasePackages() {
        ['com.example.demo']
    }

    @Override
    protected int getPort() {
        Integer.parseInt port
    }

    @Override
    protected AuthProvider getAuthProvider() {
        new PlainTextAuthProvider(username, password)
    }

    static void main(String[] args) {
        println 'main.................. '
        SpringApplication.run(SearchDataRepositoryConfiguration, args)
        println ' after main.................. '

    }


}
