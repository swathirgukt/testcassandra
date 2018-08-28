package com.example.demo

import groovy.util.logging.Slf4j
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
@Slf4j
class RestSearchInfoTest {

    @Autowired
    RestAirSearchRequestRepository restAirSearchRequestRepository
    @Test
    void getInformation(){
        Date fromDate = new Date()
        Integer currentHour = fromDate[Calendar.HOUR_OF_DAY]
        fromDate.clearTime()
        Date toDate = fromDate.clone()
        fromDate[Calendar.DAY_OF_MONTH] = 11
        fromDate[Calendar.MONTH] = Calendar.JUNE
        fromDate[Calendar.HOUR_OF_DAY] = 2
        fromDate[Calendar.MINUTE] = 45
        fromDate[Calendar.SECOND] = 0
        fromDate[Calendar.MILLISECOND] = 0
        toDate[Calendar.HOUR_OF_DAY] = currentHour
         log.warn('************count: '+restAirSearchRequestRepository.findRestAirSearchRequestInTimeRange(  fromDate, 1, 'KAYAK',  'CORE',  fromDate, toDate))
        // log.warn('************count: '+restAirSearchRequestRepository.findOne(new RestAirSearchRequestKey(requestDate: fromDate,companyId : 1, requestFrom: 'KAYAK', searchType: 'CORE', requestDateTime: fromDate)))
       // log.warn('************count: '+restAirSearchRequestRepository.findAll().size())
    }
}
