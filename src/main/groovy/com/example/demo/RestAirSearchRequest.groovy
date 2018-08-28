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

import static com.datastax.driver.core.DataType.Name.*

@EqualsAndHashCode
@Table('REST_AIR_SEARCH_REQUEST_NEW')
class RestAirSearchRequest {

    @PrimaryKey
    RestAirSearchRequestKey id

    @Column('TRACKING_CODE')
    String trackingCode
    @Column('RESPONSE_TIME')
    @CassandraType(type = TIMESTAMP)
    Date responseTime
    @Column('ERROR_MESSAGE')
    String errorMessage
    @Column('IP_ADDRESS')
    String customerIpAddress
    @Column('TIME_TO_RESPOND')
    @CassandraType(type = INT)
    Integer timeToRespond
    @Column('RESPONSE_STATUS')
    @CassandraType(type = INT)
    Integer responseStatus
    @Column('NUMBER_OF_AIR_ITINERARY')
    @CassandraType(type = INT)
    Integer numberOfAirItinerary

    @Column('NUM_OF_ADULTS')
    @CassandraType(type = INT)
    Integer adults
    @Column('NUM_OF_CHILDREN')
    @CassandraType(type = INT)
    Integer children
    @Column('NUM_OF_INFANTS')
    @CassandraType(type = INT)
    Integer infants
    @Column('NUM_OF_INFANTS_ON_SEAT')
    @CassandraType(type = INT)
    Integer infantsOnSeat
    @Column('SEARCH_KEY')
    String searchKey

    @Column('CABIN_TYPE')
    String cabinType
    @Column('TRIP_TYPE')
    String tripType
    @Column('PREFERRED_AIRLINES')
    Set<String> preferredAirlines
    @Column('NUM_OF_MAX_ITINERARIES')
    @CassandraType(type = INT)
    Integer maxItineraries
    @Column('IS_AIRPORT_CHANGE_CONNECTION_ALLOWED')
    @CassandraType(type = BOOLEAN)
    Boolean airportChangeConnectionAllowed
    @Column('IS_REFUNDABLE_ITINERARIES')
    @CassandraType(type = BOOLEAN)
    Boolean refundableItineraries

    @Column('LEG_ONE_ORIGIN')
    String legOneOrigin
    @Column('LEG_ONE_NEARBY_ORIGINS')
    Set<String> legOneNearbyOrigins
    @Column('LEG_ONE_NEARBY_ORIGIN_RADIUS')
    @CassandraType(type = INT)
    Integer legOneNearbyOriginRadius
    @Column('IS_INCLUDE_LEG_ONE_NEARBY_ORIGINS')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyOriginsInLegOne

    @Column('LEG_ONE_DESTINATION')
    String legOneDestination
    @Column('LEG_ONE_NEARBY_DESTINATIONS')
    Set<String> legOneNearbyDestinations
    @Column('LEG_ONE_NEARBY_DESTINATION_RADIUS')
    @CassandraType(type = INT)
    Integer legOneNearbyDestinationRadius
    @Column('IS_INCLUDE_LEG_ONE_NEARBY_DESTINATION')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyDestinationsInLegOne

    @Column('LEG_ONE_DEPARTURE_DATE')
    @CassandraType(type = TIMESTAMP)
    Date legOneDepartureDate
    @Column('LEG_ONE_DEPARTURE_DATE_FLEXIBILITY')
    String legOneDepartureDateFlexibility
    @Column('LEG_ONE_PREFERED_DEPARTURE_HOURS_RANGE')
    String legOnePreferredDepartureHoursRange
    @Column('LEG_ONE_PREFERED_ARRIVAL_HOURS_RANGE')
    String legOnePreferredArrivalHoursRange
    @Column('LEG_ONE_MAX_STOPS')
    @CassandraType(type = INT)
    Integer legOneMaxStops
    @Column('LEG_ONE_MAX_CONNECTION_TIME')
    @CassandraType(type = INT)
    Integer legOneMaxConnectionTime

    @Column('LEG_TWO_ORIGIN')
    String legTwoOrigin
    @Column('LEG_TWO_NEARBY_ORIGINS')
    Set<String> legTwoNearbyOrigins
    @Column('LEG_TWO_NEARBY_ORIGIN_RADIUS')
    @CassandraType(type = INT)
    Integer legTwoNearbyOriginRadius
    @Column('IS_INCLUDE_LEG_TWO_NEARBY_ORIGINS')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyOriginsInLegTwo

    @Column('LEG_TWO_DESTINATION')
    String legTwoDestination
    @Column('LEG_TWO_NEARBY_DESTINATIONS')
    Set<String> legTwoNearbyDestinations
    @Column('LEG_TWO_NEARBY_DESTINATION_RADIUS')
    @CassandraType(type = INT)
    Integer legTwoNearbyDestinationRadius
    @Column('IS_INCLUDE_LEG_TWO_NEARBY_DESTINATION')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyDestinationsInLegTwo

    @Column('LEG_TWO_DEPARTURE_DATE')
    @CassandraType(type = TIMESTAMP)
    Date legTwoDepartureDate
    @Column('LEG_TWO_DEPARTURE_DATE_FLEXIBILITY')
    String legTwoDepartureDateFlexibility
    @Column('LEG_TWO_PREFERED_DEPARTURE_HOURS_RANGE')
    String legTwoPreferredDepartureHoursRange
    @Column('LEG_TWO_PREFERED_ARRIVAL_HOURS_RANGE')
    String legTwoPreferredArrivalHoursRange
    @Column('LEG_TWO_MAX_STOPS')
    @CassandraType(type = INT)
    Integer legTwoMaxStops
    @Column('LEG_TWO_MAX_CONNECTION_TIME')
    @CassandraType(type = INT)
    Integer legTwoMaxConnectionTime

    @Column('LEG_THREE_ORIGIN')
    String legThreeOrigin
    @Column('LEG_THREE_NEARBY_ORIGINS')
    Set<String> legThreeNearbyOrigins
    @Column('LEG_THREE_NEARBY_ORIGIN_RADIUS')
    @CassandraType(type = INT)
    Integer legThreeNearbyOriginRadius
    @Column('IS_INCLUDE_LEG_THREE_NEARBY_ORIGINS')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyOriginsInLegThree

    @Column('LEG_THREE_DESTINATION')
    String legThreeDestination
    @Column('LEG_THREE_NEARBY_DESTINATIONS')
    Set<String> legThreeNearbyDestinations
    @Column('LEG_THREE_NEARBY_DESTINATION_RADIUS')
    @CassandraType(type = INT)
    Integer legThreeNearbyDestinationRadius
    @Column('IS_INCLUDE_LEG_THREE_NEARBY_DESTINATION')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyDestinationsInLegThree

    @Column('LEG_THREE_DEPARTURE_DATE')
    @CassandraType(type = TIMESTAMP)
    Date legThreeDepartureDate
    @Column('LEG_THREE_DEPARTURE_DATE_FLEXIBILITY')
    String legThreeDepartureDateFlexibility
    @Column('LEG_THREE_PREFERED_DEPARTURE_HOURS_RANGE')
    String legThreePreferredDepartureHoursRange
    @Column('LEG_THREE_PREFERED_ARRIVAL_HOURS_RANGE')
    String legThreePreferredArrivalHoursRange
    @Column('LEG_THREE_MAX_STOPS')
    @CassandraType(type = INT)
    Integer legThreeMaxStops
    @Column('LEG_THREE_MAX_CONNECTION_TIME')
    @CassandraType(type = INT)
    Integer legThreeMaxConnectionTime

    @Column('LEG_FOUR_ORIGIN')
    String legFourOrigin
    @Column('LEG_FOUR_NEARBY_ORIGINS')
    Set<String> legFourNearbyOrigins
    @Column('LEG_FOUR_NEARBY_ORIGIN_RADIUS')
    @CassandraType(type = INT)
    Integer legFourNearbyOriginRadius
    @Column('IS_INCLUDE_LEG_FOUR_NEARBY_ORIGINS')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyOriginsInLegFour

    @Column('LEG_FOUR_DESTINATION')
    String legFourDestination
    @Column('LEG_FOUR_NEARBY_DESTINATIONS')
    Set<String> legFourNearbyDestinations
    @Column('LEG_FOUR_NEARBY_DESTINATION_RADIUS')
    @CassandraType(type = INT)
    Integer legFourNearbyDestinationRadius
    @Column('IS_INCLUDE_LEG_FOUR_NEARBY_DESTINATION')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyDestinationsInLegFour

    @Column('LEG_FOUR_DEPARTURE_DATE')
    @CassandraType(type = TIMESTAMP)
    Date legFourDepartureDate
    @Column('LEG_FOUR_DEPARTURE_DATE_FLEXIBILITY')
    String legFourDepartureDateFlexibility
    @Column('LEG_FOUR_PREFERED_DEPARTURE_HOURS_RANGE')
    String legFourPreferredDepartureHoursRange
    @Column('LEG_FOUR_PREFERED_ARRIVAL_HOURS_RANGE')
    String legFourPreferredArrivalHoursRange
    @Column('LEG_FOUR_MAX_STOPS')
    @CassandraType(type = INT)
    Integer legFourMaxStops
    @Column('LEG_FOUR_MAX_CONNECTION_TIME')
    @CassandraType(type = INT)
    Integer legFourMaxConnectionTime

    @Column('LEG_FIVE_ORIGIN')
    String legFiveOrigin
    @Column('LEG_FIVE_NEARBY_ORIGINS')
    Set<String> legFiveNearbyOrigins
    @Column('LEG_FIVE_NEARBY_ORIGIN_RADIUS')
    @CassandraType(type = INT)
    Integer legFiveNearbyOriginRadius
    @Column('IS_INCLUDE_LEG_FIVE_NEARBY_ORIGINS')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyOriginsInLegFive

    @Column('LEG_FIVE_DESTINATION')
    String legFiveDestination
    @Column('LEG_FIVE_NEARBY_DESTINATIONS')
    Set<String> legFiveNearbyDestinations
    @Column('LEG_FIVE_NEARBY_DESTINATION_RADIUS')
    @CassandraType(type = INT)
    Integer legFiveNearbyDestinationRadius
    @Column('IS_INCLUDE_LEG_FIVE_NEARBY_DESTINATION')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyDestinationsInLegFive

    @Column('LEG_FIVE_DEPARTURE_DATE')
    @CassandraType(type = TIMESTAMP)
    Date legFiveDepartureDate
    @Column('LEG_FIVE_DEPARTURE_DATE_FLEXIBILITY')
    String legFiveDepartureDateFlexibility
    @Column('LEG_FIVE_PREFERED_DEPARTURE_HOURS_RANGE')
    String legFivePreferredDepartureHoursRange
    @Column('LEG_FIVE_PREFERED_ARRIVAL_HOURS_RANGE')
    String legFivePreferredArrivalHoursRange
    @Column('LEG_FIVE_MAX_STOPS')
    @CassandraType(type = INT)
    Integer legFiveMaxStops
    @Column('LEG_FIVE_MAX_CONNECTION_TIME')
    @CassandraType(type = INT)
    Integer legFiveMaxConnectionTime

    @Column('LEG_SIX_ORIGIN')
    String legSixOrigin
    @Column('LEG_SIX_NEARBY_ORIGINS')
    Set<String> legSixNearbyOrigins
    @Column('LEG_SIX_NEARBY_ORIGIN_RADIUS')
    @CassandraType(type = INT)
    Integer legSixNearbyOriginRadius
    @Column('IS_INCLUDE_LEG_SIX_NEARBY_ORIGINS')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyOriginsInLegSix

    @Column('LEG_SIX_DESTINATION')
    String legSixDestination
    @Column('LEG_SIX_NEARBY_DESTINATIONS')
    Set<String> legSixNearbyDestinations
    @Column('LEG_SIX_NEARBY_DESTINATION_RADIUS')
    @CassandraType(type = INT)
    Integer legSixNearbyDestinationRadius
    @Column('IS_INCLUDE_LEG_SIX_NEARBY_DESTINATION')
    @CassandraType(type = BOOLEAN)
    Boolean includeNearbyDestinationsInLegSix

    @Column('LEG_SIX_DEPARTURE_DATE')
    @CassandraType(type = TIMESTAMP)
    Date legSixDepartureDate
    @Column('LEG_SIX_DEPARTURE_DATE_FLEXIBILITY')
    String legSixDepartureDateFlexibility
    @Column('LEG_SIX_PREFERED_DEPARTURE_HOURS_RANGE')
    String legSixPreferredDepartureHoursRange
    @Column('LEG_SIX_PREFERED_ARRIVAL_HOURS_RANGE')
    String legSixPreferredArrivalHoursRange
    @Column('LEG_SIX_MAX_STOPS')
    @CassandraType(type = INT)
    Integer legSixMaxStops
    @Column('LEG_SIX_MAX_CONNECTION_TIME')
    @CassandraType(type = INT)
    Integer legSixMaxConnectionTime
}
