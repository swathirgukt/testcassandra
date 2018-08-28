/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 *
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import groovy.transform.EqualsAndHashCode
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn

import static com.datastax.driver.core.DataType.Name.INT
import static com.datastax.driver.core.DataType.Name.TIMESTAMP

/**
 * This class represents primary key RestAirSearchRequest table
 *
 * User: Prakash Rout
 * Date: 7/14/2017
 * Time: 12:35 PM
 */
@EqualsAndHashCode
@PrimaryKeyClass
class RestAirSearchRequestKey implements Serializable {

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = 'REQUEST_DATE')
    @CassandraType(type = TIMESTAMP)
    Date requestDate
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED, name = 'COMPANY_ID')
    @CassandraType(type = INT)
    Integer companyId
    @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.PARTITIONED, name = 'REQUEST_FROM')
    String requestFrom
    @PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.PARTITIONED, name = 'SEARCH_TYPE')
    String searchType
    @PrimaryKeyColumn(ordinal = 4, type = PrimaryKeyType.PARTITIONED, name = 'REQUEST_TIME')
    @CassandraType(type = TIMESTAMP)
    Date requestDateTime
}