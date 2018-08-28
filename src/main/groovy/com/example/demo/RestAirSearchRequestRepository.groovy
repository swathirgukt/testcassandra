/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 *
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import org.springframework.data.cassandra.repository.Query
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository

/**
 * This is the Data Access interface for communicating with the Rest air search request table in the database
 *
 * User: Prakash Rout
 * Date: 7/14/2017
 * Time: 12:35 PM
 */
interface RestAirSearchRequestRepository extends TypedIdCassandraRepository<RestAirSearchRequest, RestAirSearchRequestKey> {

    @Query('SELECT * FROM REST_AIR_SEARCH_REQUEST WHERE  REQUEST_DATE = ?0 AND COMPANY_ID = ?1 AND REQUEST_FROM = ?2 AND SEARCH_TYPE = ?3 AND REQUEST_TIME >= ?4 AND REQUEST_TIME < ?5 ALLOW FILTERING')
    List<RestAirSearchRequest> findRestAirSearchRequestInTimeRange(Date requestDate, Integer companyId, String requestFrom, String searchType, Date fromTime, Date toTime)
}