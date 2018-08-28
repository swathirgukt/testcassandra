/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import com.datastax.driver.core.DataType
import groovy.transform.EqualsAndHashCode
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@EqualsAndHashCode
@Table('GDS_SERVICE_CALL_INFO_NEW')
class GDSServiceCallInfo {

    @PrimaryKey
    GDSServiceCallInfoKey id

    @Column('SERVICE_TYPE')
    String serviceType

    @Column('ACTION_TYPE')
    String actionType

    @Column('IPCC_CODE')
    String ipccCode

    @Column('GDS')
    String gds

    @Column('NO_OF_SCAN')
    @CassandraType(type = DataType.Name.DECIMAL)
    BigDecimal noOfScan
}