/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 *
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import com.datastax.driver.core.DataType
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

import static com.datastax.driver.core.DataType.Name.BOOLEAN

/**
 * This is the DTO for the Sabre Air Search Call Configurations
 *
 * User: Hafeez 
 * Date: 3/31/2017
 * Time: 4:08 PM
 */
@EqualsAndHashCode
@Table('SABRE_AIR_SEARCH_WEB_SERVICE_CONFIGURATION')
@ToString
class SabreAirSearchWebServiceConfiguration {

    @PrimaryKey
    AirSearchWSConfigurationKey id

    @Column('GDS')
    String gdsType

    @Column('PSUEDO_CITY_OODE')
    String psuedoCityCode

    @Column('ACTION_TYPE')
    String actionType

    @Column('TRIP_TYPE')
    String tripType

    @Column('WS_CALL_TYPE')
    String wsCallType

    @Column('IS_PUBLISHED_FARE_CALL')
    Boolean publishedFareCall

    @Column('IS_PRIVATE_FARE_CALL')
    Boolean privateFareCall

    @Column('DEFAULT_CALL')
    @CassandraType(type = BOOLEAN)
    Boolean defaultCall

    @Column('CALL_ON_REQUEST')
    @CassandraType(type = BOOLEAN)
    Boolean callOnRequest

    @Column('ITINERARIES_REQUEST_TYPE')
    String itinerariesRequestType

    @Column('CARRIER_PREFERENCES')
    String carrierPreferences

    @Column('ALLIANCE_PREFERENCES')
    String alliancePreferences

    @Column('LEG_ONE_LAYOVERS')
    String legOneLayovers

    @Column('LEG_TWO_LAYOVERS')
    String legTwoLayovers

    @Column('LOW_FARE_BUCKT_PERCENTAGE')
    String lowFareBucketPercentage

    @Column('CARRIER_DIVERSITY_OPTIONS')
    String carrierDiversityOptions

    @Column('LONG_CONNECT_MIN_TIME')
    @CassandraType(type = DataType.Name.INT)
    Integer longConnectMinTime

    @Column('LONG_CONNECT_MAX_TIME')
    @CassandraType(type = DataType.Name.INT)
    Integer longConnectMaxTime

    @Column('LONG_CONNECT_MIN_POINTS')
    @CassandraType(type = DataType.Name.INT)
    Integer longConnectMinPoints

    @Column('LONG_CONNECT_MAX_POINTS')
    @CassandraType(type = DataType.Name.INT)
    Integer longConnectMaxPoints

    @Column('ONLY_PUB_ITINERARIES')
    @CassandraType(type = BOOLEAN)
    Boolean onlyPubItinerariesToShow

    // TODO : Nearby Airport details {originNearby, destinationNearBy}, Flexible date details
}
