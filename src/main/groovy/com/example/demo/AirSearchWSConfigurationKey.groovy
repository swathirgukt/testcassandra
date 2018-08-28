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
import org.springframework.data.cassandra.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn

@EqualsAndHashCode
@PrimaryKeyClass
class AirSearchWSConfigurationKey implements Serializable {

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = 'ORIGIN')
    String origin
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED, name = 'DESTINATION')
    String destination
    @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.PARTITIONED, name = 'REQUEST_FROM')
    String requestFrom
    @PrimaryKeyColumn(ordinal = 3, type = PrimaryKeyType.PARTITIONED, name = 'SEARCH_TYPE')
    String searchType
    @PrimaryKeyColumn(ordinal = 4, type = PrimaryKeyType.PARTITIONED, name = 'CRITERIA_INDEX')
    Integer criteriaIndex
}
