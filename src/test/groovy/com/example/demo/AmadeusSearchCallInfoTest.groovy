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
class AmadeusSearchCallInfoTest {

    @Autowired
    AmadeusAirSearchWebServiceConfigurationRepository amadeusAirSearchWebServiceConfigurationRepository

    @Test
    void getAmadeusServiceInfo(){
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("AmadsWSConf30-7.csv"), "UTF-8")
        BufferedWriter bufWriter = new BufferedWriter(writer)

        List<AmadeusAirSearchWebServiceConfiguration> amadeusAirSearchWebServiceConfigurationList = amadeusAirSearchWebServiceConfigurationRepository.findAll()
        bufWriter.writeLine('ORIGIN,DESTINATION,SEARCHTYPE,REQUESTFROM,CRITERIAINDEX,TRIPTYPE,GDSTYPE,OFFICEID,PORTTYPE,TRIPTYPE,WSCALLTYPE,PUBLISHEDFARECALL,PRIVATEFARECALL,DEFAULTCALL,CALLONREQUEST,NOOFRECOMMENDATIONS,CARRIERPREFERENCES,ALLIANCEPREFERENCES,LEGONELAYOVERS,LEGTWOLAYOVERS,ONLYPUBITINERARIESTOSHOW')
        amadeusAirSearchWebServiceConfigurationList.each {
            bufWriter.writeLine(it.id.origin+','+it.id.destination+','+it.id.searchType+','+it.id.requestFrom+','+it.id.criteriaIndex+','+it.tripType+','+it.gdsType+','+it.officeid+','+it.portType+','+it.tripType+','+it.wsCallType+','+it.publishedFareCall+','+it.privateFareCall+','+it.defaultCall+','+it.callOnRequest+','+it.noOfRecommendations+','+it.carrierPreferences+','+it.alliancePreferences+','+it.legOneLayovers+','+it.legTwoLayovers+','+it.onlyPubItinerariesToShow)
        }
        bufWriter.close()
        writer.close()
    }

}
