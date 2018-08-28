/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 *
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import groovy.transform.EqualsAndHashCode
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

import static com.datastax.driver.core.DataType.Name.BOOLEAN
import static com.datastax.driver.core.DataType.Name.INT

@EqualsAndHashCode
@Table('AMADEUS_AIR_SEARCH_WEB_SERVICE_CONFIGURATION')
class AmadeusAirSearchWebServiceConfiguration {

    @PrimaryKey
    AirSearchWSConfigurationKey id

    @Column('GDS')
    String gdsType

    @Column('OFFICE_ID')
    String officeid

    @Column('PORT_TYPE')
    String portType

    @Column('TRIP_TYPE')
    String tripType

    @Column('WS_CALL_TYPE')
    String wsCallType

    @Column('IS_PUBLISHED_FARE_CALL')
    @CassandraType(type = BOOLEAN)
    Boolean publishedFareCall

    @Column('IS_PRIVATE_FARE_CALL')
    @CassandraType(type = BOOLEAN)
    Boolean privateFareCall

    @Column('DEFAULT_CALL')
    @CassandraType(type = BOOLEAN)
    Boolean defaultCall

    @Column('CALL_ON_REQUEST')
    @CassandraType(type = BOOLEAN)
    Boolean callOnRequest

    @Column('NO_OF_RECOMMENDATIONS')
    @CassandraType(type = INT)
    Integer noOfRecommendations

    @Column('CARRIER_PREFERENCES')
    String carrierPreferences

    @Column('ALLIANCE_PREFERENCES')
    String alliancePreferences

    @Column('LEG_ONE_LAYOVERS')
    String legOneLayovers

    @Column('LEG_TWO_LAYOVERS')
    String legTwoLayovers

    @Column('ONLY_PUB_ITINERARIES')
    @CassandraType(type = BOOLEAN)
    Boolean onlyPubItinerariesToShow

    // TODO : Nearby Airport details {originNearby, destinationNearBy}, Flexible date details


}
