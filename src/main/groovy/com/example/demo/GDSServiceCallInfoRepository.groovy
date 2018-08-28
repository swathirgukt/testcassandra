/*
 * Copyright (c) 2017 by Indian Eagle LLC.
 * All rights reserved. These materials are confidential and proprietary to Indian Eagle LLC. No part of this code may be reproduced, published
 * in any form by any means (electronic or mechanical, including photocopy or any information storage or retrieval system), nor may the materials
 * be disclosed to third parties, or used in derivative works without the express written authorization of Indian Eagle LLC.
 */

package com.example.demo

import org.springframework.data.cassandra.repository.Query
import org.springframework.data.cassandra.repository.TypedIdCassandraRepository

interface GDSServiceCallInfoRepository extends TypedIdCassandraRepository<GDSServiceCallInfo, GDSServiceCallInfoKey> {
    @Query("SELECT * FROM GDS_SERVICE_CALL_INFO_NEW WHERE COMPANY_ID IN(1,2) AND SERVICE_NAME IN('BARGAIN_FINDER_MAX_ADRQ','BARGAIN_FINDER_MAX_SAPT','BARGAIN_FINDER_MAX') AND REQUEST_DATE = ?0  ")
    List<GDSServiceCallInfo> findSabreServiceCallInfoByRequestDate(Date requestDate)

    @Query("SELECT * FROM GDS_SERVICE_CALL_INFO_NEW WHERE COMPANY_ID IN(1,2) AND SERVICE_NAME IN('BARGAIN_FINDER_MAX_ADRQ','BARGAIN_FINDER_MAX_SAPT','BARGAIN_FINDER_MAX') AND REQUEST_DATE = ?0  AND REQUEST_TIME = ?1")
    List<GDSServiceCallInfo> findSabreServiceCallInfoByRequestDateAndTime(Date requestDate, Date requestTime)

    @Query("SELECT * FROM GDS_SERVICE_CALL_INFO_NEW WHERE COMPANY_ID IN(1,2) AND SERVICE_NAME IN('BARGAIN_FINDER_MAX_ADRQ','BARGAIN_FINDER_MAX_SAPT','BARGAIN_FINDER_MAX') AND REQUEST_DATE = ?0  AND REQUEST_TIME = ?1")
    List<GDSServiceCallInfo> findAmadeusServiceCallInfoByRequestDateAndTime(Date requestDate)
}