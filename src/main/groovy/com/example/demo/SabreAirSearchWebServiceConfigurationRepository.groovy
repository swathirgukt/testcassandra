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
 * This Repository is used to communicate with the Sabre Air Search Configuration Table
 *
 * User: Hafeez 
 * Date: 3/31/2017
 * Time: 4:52 PM
 */
interface SabreAirSearchWebServiceConfigurationRepository extends TypedIdCassandraRepository<SabreAirSearchWebServiceConfiguration, AirSearchWSConfigurationKey> {

    List<SabreAirSearchWebServiceConfiguration> findSabreAirSearchWebServiceConfigurationById(AirSearchWSConfigurationKey airSearchWSConfigurationKey)

}