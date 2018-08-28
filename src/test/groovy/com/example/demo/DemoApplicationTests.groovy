package com.example.demo

import groovy.util.logging.Slf4j
import org.junit.AfterClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
@Slf4j
class DemoApplicationTests {
    @Autowired
    GDSServiceCallInfoRepository gdsServiceCallInfoRepository

    @Autowired
    SabreAirSearchWebServiceConfigurationRepository sabreAirSearchWebServiceConfigurationRepository

    @Test
    void contextLoads() {

    }

    @Test
    void getReport() {
        long curTime = Calendar.getInstance().timeInMillis
        // int  totalScans = 0
        int i = 0;
        try {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("CST"))
            calendar[Calendar.MONTH] = Calendar.AUGUST
            Date idRequestDate = calendar.getTime()
            int sno = 0
            OutputStreamWriter writer
            BufferedWriter bufWriter
            //   for (day in 23..29) {
            int day = 6
            try {
                writer = new OutputStreamWriter(
                        new FileOutputStream("search" + day + "aug.txt"), "UTF-8")
                bufWriter = new BufferedWriter(writer)
                bufWriter.writeLine('S.No:  :RequestDate:  :ActionType:: ID:: noOfScan:: : totalScans :')
                idRequestDate[Calendar.DAY_OF_MONTH] = day
                BigDecimal totalScans = BigDecimal.ZERO
                for (hour in 0..23) {
                    idRequestDate[Calendar.HOUR_OF_DAY] = hour
                    for (minute in 0..59) {
                        try {
                            idRequestDate[Calendar.MINUTE] = minute

                            idRequestDate[Calendar.SECOND] = 0
                            idRequestDate[Calendar.MILLISECOND] = 0
                            List<GDSServiceCallInfo> serviceCallInfo = gdsServiceCallInfoRepository.findSabreServiceCallInfoByRequestDate(idRequestDate)
                            log.warn("--serviceCallInfo:" + serviceCallInfo.size() + " *** idRequestDate:" + idRequestDate)
                            serviceCallInfo.each {
                                log.warn('S.No: ' + sno + ', RequestDate:' + it.id.requestDate + "req time:" + it.id.requestDateTime + ', ID: ' + it.id.serviceName + ', noOfScan:' + it.noOfScan + ' *** ' + totalScans)
                                bufWriter.writeLine('S.No: ' + sno + 'RequestDate:' + idRequestDate +' time: '+it.id.requestDateTime+ ', serviceName: ' + it.id.serviceName + ', noOfScan:' + it.noOfScan + ' *** ' + totalScans)
                                totalScans = totalScans.add(it.noOfScan)
                                sno++
                            }
                            i = i + 1

                        } catch (all) {
                            bufWriter.writeLine("Exception for this minute:" + idRequestDate + '  is:' + all)
                        }
                    }
                }
                bufWriter.writeLine('totalScans:' + totalScans + ' day:' + idRequestDate)
                def diff = Calendar.getInstance().timeInMillis - curTime
                bufWriter.writeLine(" total time  to get data: " + diff + ' total db calls:' + i + ' for :' + day)
                log.warn('*************totalScans:' + totalScans + ' day:' + idRequestDate + ' total db calls:' + i)
                bufWriter.close()
                writer.close()
            } catch (all) {
                log.warn("Exception for this minute:" + idRequestDate + '  is:' + all)
            }
            //  }
        } catch (all) {
            log.warn('--------excpn:' + all)
        }
    }

    @Test
    void getCount() {

        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("SABRE_CALLS_8-8.CSV"), "UTF-8")
        BufferedWriter bufWriter = new BufferedWriter(writer)
        List<SabreAirSearchWebServiceConfiguration> sabreAirSearchWebServiceConfigurationList = sabreAirSearchWebServiceConfigurationRepository.findAll() as List
        bufWriter.writeLine('origin, destination ,requestFrom ,criteriaIndex ,searchType: ,actionType  ,wsCallType  ,privateFareCall  ,publishedFareCall ,gdsType ,alliancePreferences ,callOnRequest ,carrierDiversityOptions ,carrierPreferences ,defaultCall ,itinerariesRequestType ,legOneLayovers ,legTwoLayovers ,tripType ,longConnectMaxPoints ,longConnectMaxTime ,lowFareBucketPercentage ,onlyPubItinerariesToShow ,psuedoCityCode')
        log.warn('***********result:' + sabreAirSearchWebServiceConfigurationList.size())
        for (SabreAirSearchWebServiceConfiguration it : sabreAirSearchWebServiceConfigurationList) {
            log.warn('****************  ::: ' + it)
            bufWriter.writeLine(it.id.origin + ', ' + it.id.destination + ', ' + it.id.requestFrom + ', ' + it.id.criteriaIndex + ',' + it.id.searchType + ',' + it.actionType + ',' + it.wsCallType + ',' + it.privateFareCall + ',' + it.publishedFareCall + ',' + it.gdsType + ',' + it.alliancePreferences + ',' + it.callOnRequest + ',' + it.carrierDiversityOptions + ',' + it.carrierPreferences + ',' + it.defaultCall + ',' + it.itinerariesRequestType + ',' + it.legOneLayovers + ',' + it.legTwoLayovers + ',' + it.tripType + ',' + it.longConnectMaxPoints + ',' + it.longConnectMaxTime + ',' + it.lowFareBucketPercentage + ',' + it.onlyPubItinerariesToShow + ',' + it.psuedoCityCode)
        }
        bufWriter.close()
        writer.close()
    }

    @Test
    void getTotalNumber() {
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("SWA.csv"), "UTF-8")
        BufferedWriter bufWriter = new BufferedWriter(writer)
        List<SabreAirSearchWebServiceConfiguration> wsConf = sabreAirSearchWebServiceConfigurationRepository.findAll()
        log.warn('********955*****sabreAirSearchWebServiceConfigurationRepository::' + wsConf.size())
        def list = []
        wsConf.each {
            if (it.id.requestFrom == 'ALL' && it.wsCallType == 'VENDOR_PREFERENCE') {
                if ((it.id.origin == 'LAX' && it.id.destination == 'HYD') || (it.id.origin == 'SEA' && it.id.destination == 'DEL') || (it.id.origin == 'ATL' && it.id.destination == 'BOM') || (it.id.origin == 'IAD' && it.id.destination == 'BOM') || (it.id.origin == 'HOU' && it.id.destination == 'HYD') || (it.id.origin == 'DFW' && it.id.destination == 'BOM')) {
                    bufWriter.writeLine(it.id.origin + ', ' + it.id.destination + ', ' + it.id.requestFrom + ',' + it.id.criteriaIndex + ',' + it.id.searchType+','+it.carrierPreferences)
                }
            }



        }
        bufWriter.close()
    }

    @Test
    public void testInputRoutewise(){
        List<String> input=['JFK-DEL:49','JFK-BOM:38','JFK-MAA:24','JFK-DEL:18','EWR-BOM:13','LAX-DEL:13','LAX-DEL:13','SFO-DEL:12','SFO-BLR:11','JFK-BLR:10','JFK-COK:10','JFK-HYD:10','EWR-BLR:9','LAX-BOM:9','LAX-DEL:9','SFO-HYD:9','SFO-MAA:9','JFK-AMD:8','LAX-BLR:8','EWR-EWR:7','SFO-BLR:7','CVG-TXL:6','EWR-AMD:6','JFK-BOM:6','JFK-HYD:6','LAX-DEL:6','ORD-DEL:6','SFO-BOM:6','SFO-DEL:6','SFO-HYD:6','DFW-BLR:5','DTW-BLR:5','JFK-MAA:5','MSP-HYD:5','PHX-BLR:5','SEA-HYD:5','SFO-BOM:5','ATL-BOM:4','CLT-MAA:4','CMH-HYD:4','DEN-DEL:4','DFW-DEL:4','DTW-HYD:4','EWR-MAA:4','IAD-BLR:4','IAD-HYD:4','JFK-BLR:4','JFK-BLR:4','JFK-BOM:4','JFK-DEL:4','LAX-DEL:4','LAX-HYD:4','MCO-HYD:4','ORD-BLR:4','ORD-COK:4','ORD-DEL:4','ORD-HYD:4','PHX-HYD:4','SFO-DEL:4','ATL-BLR:3','ATL-HYD:3','BOS-BLR:3','BOS-BOM:3','DEL-DFW:3','DEL-JFK:3','EWR-COK:3','EWR-HYD:3','EWR-HYD:3','HYD-IAH:3','IAD-BOM:3','IAD-BOM:3','IAD-DEL:3','JFK-DEL:3','JFK-DEL:3','JFK-HYD:3','JFK-JFK:3','JFK-MAA:3','JFK-TRV:3','LAX-BOM:3','LAX-CCU:3','LAX-MAA:3','MAA-ATL:3','MAA-SEA:3','MCI-DEL:3','MSP-MAA:3','MSP-MAA:3','ORD-BOM:3','ORD-BOM:3','ORD-HYD:3','ORD-ORD:3','PHX-DEL:3','PHX-MAA:3','PNQ-IAD:3','RDU-DEL:3','SFO-DEL:3','SFO-HYD:3','SFO-SFO:3','TPA-MAA:3','ATH-JTR:2','ATL-ATL:2','ATL-DEL:2','ATL-DEL:2','ATL-PNQ:2','AUS-BLR:2','AUS-DEL:2','AUS-MAA:2','BLR-XNA:2','BOM-ATL:2','BOM-ATL:2','BOM-ATL:2','BOM-IAH:2','BOS-BLR:2','BOS-DEL:2','BOS-HYD:2','CLE-BLR:2','CLE-MAA:2','CLT-BOM:2','CLT-BOM:2','CLT-DEL:2','CLT-HYD:2','CLT-MAA:2','DEL-BOS:2','DEL-DEL:2','DEL-IAH:2','DEL-ORD:2','DEL-SEA:2','DEN-HYD:2','DEN-HYD:2','DEN-MAA:2','DFW-AMD:2','DFW-BOM:2','DFW-BOM:2','DFW-BOM:2','DFW-BOM:2','DFW-DEL:2','DFW-DEL:2','DFW-MAA:2','DTW-BLR:2','DTW-BOM:2','DTW-HYD:2','DTW-HYD:2','DTW-MAA:2','EWR-BLR:2','EWR-DEL:2','EWR-HYD:2','HYD-CLT:2','HYD-CMH:2','HYD-JFK:2','HYD-JFK:2','HYD-JFK:2','HYD-JFK:2','HYD-SEA:2','HYD-SFO:2','HYD-TPA:2','IAD-BOM:2','IAD-BOM:2','IAD-BOM:2','IAD-BOM:2','IAD-BOM:2','IAD-DEL:2','IAD-HYD:2','IAD-IAD:2','IAD-IAD:2','IAD-MAA:2','IAH-BOM:2','IAH-DEL:2','IAH-DEL:2','ICT-MAA:2','JAX-BOM:2','JAX-BOM:2','JFK-AMD:2','JFK-ATQ:2','JFK-COK:2','JFK-DEL:2','JFK-LAX:2','JFK-PNQ:2','JFK-TRV:2','LAX-AMD:2','LAX-BOM:2','LAX-DEL:2','LAX-HYD:2','LAX-LAX:2','LAX-VTZ:2','MAA-BOS:2','MAA-BOS:2','MAA-ORD:2','MCO-DEL:2','MCO-MAA:2','MEM-BOM:2','MSP-BLR:2','MSP-MAA:2','ORD-AMD:2','ORD-AMD:2','ORD-BLR:2','ORD-BLR:2','ORD-BOM:2','ORD-BOM:2','ORD-BOM:2','ORD-DEL:2','ORD-ORD:2','ORD-ORD:2','PDX-VTZ:2','PHL-BLR:2','PHX-BLR:2','PHX-BLR:2','RDU-HYD:2','RDU-MAA:2','SAN-BLR:2','SEA-BLR:2','SEA-BLR:2','SEA-BLR:2','SEA-BOM:2','SEA-HYD:2','SEA-MAA:2','SFO-BOM:2','SFO-BOM:2','SFO-DEL:2','SFO-HYD:2','SFO-MAA:2','SFO-SFO:2','SFO-SFO:2','SLC-BOM:2','STL-BOM:2','STL-DEL:2','STL-MAA:2','XNA-MAA:2','ABQ-DEL:1','AMD-ATL:1','AMD-BDQ:1','AMD-EWR:1','AMD-JFK:1','AMD-MCO:1','AMD-MSY:1','AMD-SFO:1','AMD-YWG:1','ANC-DEL:1','ATL-AMD:1','ATL-AMS:1','ATL-ATL:1','ATL-ATL:1','ATL-BLR:1','ATL-BLR:1','ATL-BLR:1','ATL-BLR:1','ATL-BOM:1','ATL-BOM:1','ATL-CCU:1','ATL-COK:1','ATL-DEL:1','ATL-DEL:1','ATL-HYD:1','ATL-HYD:1','ATL-MAA:1','ATL-MAA:1','ATL-MAA:1','ATL-MAA:1','AUS-AUS:1','AUS-BLR:1','AUS-BLR:1','AUS-BOM:1','AUS-BOM:1','AUS-BOM:1','AUS-CCU:1','AUS-COK:1','AUS-HYD:1','AUS-MAA:1','AUS-MAA:1','BBI-ORD:1','BDQ-IAD:1','BEG-TIV:1','BHM-MAA:1','BHO-IAD:1','BLR-BLR:1','BLR-BLR:1','BLR-BOS:1','BLR-CVG:1','BLR-DTW:1','BLR-DTW:1','BLR-DTW:1','BLR-EWR:1','BLR-IAH:1','BLR-JFK:1','BLR-LAX:1','BLR-MCI:1','BLR-MCO:1','BLR-ORD:1','BLR-PDX:1','BLR-PHL:1','BLR-SFO:1','BNA-HYD:1','BNA-MAA:1','BOI-BOM:1','BOM-ATL:1','BOM-ATL:1','BOM-BOM:1','BOM-BOM:1','BOM-BOM:1','BOM-BOS:1','BOM-BOS:1','BOM-CLE:1','BOM-CVG:1','BOM-DFW:1','BOM-DFW:1','BOM-EWR:1','BOM-IAD:1','BOM-IAD:1','BOM-IAH:1','BOM-JFK:1','BOM-JFK:1','BOM-JFK:1','BOM-JFK:1','BOM-LAX:1','BOM-LAX:1','BOM-SEA:1','BOM-SFO:1','BOM-SFO:1','BOM-SFO:1','BOM-SFO:1','BOM-SFO:1','BOS-AMD:1','BOS-AMD:1','BOS-BLR:1','BOS-BLR:1','BOS-BOM:1','BOS-BOM:1','BOS-BOM:1','BOS-BOS:1','BOS-BOS:1','BOS-BOS:1','BOS-BOS:1','BOS-BOS:1','BOS-CCU:1','BOS-COK:1','BOS-DEL:1','BOS-DEL:1','BOS-GOI:1','BOS-HYD:1','BOS-HYD:1','BOS-PNQ:1','BOS-SFO:1','BUF-BOM:1','BWI-DEL:1','BWI-MAA:1','CCU-JFK:1','CCU-JFK:1','CDG-JFK:1','CLE-AMD:1','CLE-BLR:1','CLE-BOM:1','CLE-BOM:1','CLE-DEL:1','CLE-DEL:1','CLE-DEL:1','CLE-DFW:1','CLE-HYD:1','CLE-HYD:1','CLE-MAA:1','CLT-BLR:1','CLT-BLR:1','CLT-BOM:1','CLT-BOM:1','CLT-BOM:1','CLT-BOM:1','CLT-CLT:1','CLT-CLT:1','CLT-DEL:1','CLT-DEL:1','CLT-HYD:1','CLT-HYD:1','CLT-IXE:1','CLT-MAA:1','CLT-MAA:1','CMH-BLR:1','CMH-BOM:1','CMH-BOM:1','CMH-HYD:1','CMH-HYD:1','CMH-MAA:1','COK-CLT:1','COK-JFK:1','COK-MCO:1','CVG-AMD:1','CVG-BOM:1','CVG-CVG:1','CVG-DEL:1','DAY-HYD:1','DAY-HYD:1','DAY-MAA:1','DCA-AMD:1','DCA-COK:1','DEL-ATL:1','DEL-BOM:1','DEL-BOS:1','DEL-BOS:1','DEL-BOS:1','DEL-BUF:1','DEL-DAY:1','DEL-IAD:1','DEL-IAD:1','DEL-IAD:1','DEL-IAH:1','DEL-JFK:1','DEL-JFK:1','DEL-LAX:1','DEL-ORD:1','DEL-SAN:1','DEL-SFO:1','DEL-SFO:1','DEL-SFO:1','DEL-SJC:1','DEN-BOM:1','DEN-BOM:1','DEN-BOM:1','DEN-CCU:1','DEN-CCU:1','DEN-COK:1','DEN-DEL:1','DEN-HYD:1','DEN-HYD:1','DEN-HYD:1','DEN-MAA:1','DFW-BLR:1','DFW-BLR:1','DFW-BOM:1','DFW-BOM:1','DFW-BOS:1','DFW-CCU:1','DFW-COK:1','DFW-DEL:1','DFW-DEL:1','DFW-DEL:1','DFW-DFW:1','DFW-HOU:1','DFW-MAA:1','DFW-MAA:1','DFW-ORD:1','DFW-PNQ:1','DFW-SNA:1','DFW-VTZ:1','DSM-BOM:1','DTW-AMD:1','DTW-BLR:1','DTW-BLR:1','DTW-BOM:1','DTW-DEL:1','DTW-DEL:1','DTW-DEL:1','DTW-DEL:1','DTW-DTW:1','DTW-HYD:1','DTW-MAA:1','DTW-MAA:1','DTW-MAA:1','DTW-MAA:1','DTW-PNQ:1','DTW-TRV:1','EWR-AMD:1','EWR-ATQ:1','EWR-BOM:1','EWR-BOS:1','EWR-CCU:1','EWR-CCU:1','EWR-COK:1','EWR-DEL:1','EWR-EWR:1','EWR-IXE:1','EWR-KBP:1','EWR-MAA:1','EWR-MAA:1','EWR-MXP:1','EWR-SFO:1','EWR-TRV:1','FAR-BOM:1','FLL-AMD:1','FLL-HYD:1','GEG-COK:1','GSO-BLR:1','GSO-HYD:1','HSV-BOM:1','HYD-ATL:1','HYD-ATL:1','HYD-BOI:1','HYD-BOS:1','HYD-BOS:1','HYD-DEL:1','HYD-DEN:1','HYD-DFW:1','HYD-DFW:1','HYD-DTW:1','HYD-DTW:1','HYD-DTW:1','HYD-DTW:1','HYD-EWR:1','HYD-EWR:1','HYD-HYD:1','HYD-HYD:1','HYD-IAD:1','HYD-JFK:1','HYD-LAX:1','HYD-MAA:1','HYD-ORD:1','HYD-ORD:1','HYD-SFO:1','HYD-SFO:1','HYD-SJC:1','IAD-AMD:1','IAD-AMD:1','IAD-ATH:1','IAD-BBI:1','IAD-BLR:1','IAD-BOM:1','IAD-BOM:1','IAD-BOM:1','IAD-CCU:1','IAD-COK:1','IAD-DEL:1','IAD-DEL:1','IAD-HYD:1','IAD-IAD:1','IAD-IAD:1','IAD-IAD:1','IAD-LHR:1','IAD-LIS:1','IAD-MAA:1','IAD-MAA:1','IAD-NCE:1','IAD-PNQ:1','IAD-PNQ:1','IAD-TLV:1','IAD-TRV:1','IAD-TRV:1','IAH-BKK:1','IAH-BLR:1','IAH-BLR:1','IAH-BLR:1','IAH-BOM:1','IAH-BOM:1','IAH-BOM:1','IAH-COK:1','IAH-DEL:1','IAH-HYD:1','IAH-HYD:1','IAH-IAH:1','IAH-MAA:1','IAH-MAA:1','IAH-MAA:1','IAH-MNL:1','IAH-SIN:1','ICT-BOM:1','ICT-DEL:1','ILM-BOM:1','IND-BOM:1','IND-HYD:1','IND-MAA:1','IXE-JFK:1','IXE-ORD:1','JAN-DEL:1','JAX-MAA:1','JFK-AMD:1','JFK-AMD:1','JFK-ATQ:1','JFK-BDQ:1','JFK-BLR:1','JFK-BLR:1','JFK-BOM:1','JFK-BOM:1','JFK-BOM:1','JFK-CAN:1','JFK-COK:1','JFK-DEL:1','JFK-DEL:1','JFK-DEL:1','JFK-DEL:1','JFK-DEL:1','JFK-DEL:1','JFK-DEL:1','JFK-DOH:1','JFK-FRA:1','JFK-HND:1','JFK-HYD:1','JFK-HYD:1','JFK-JED:1','JFK-JFK:1','JFK-JFK:1','JFK-JFK:1','JFK-KTM:1','JFK-LAS:1','JFK-LKO:1','JFK-MAA:1','JFK-MAA:1','JFK-MSY:1','JFK-ORY:1','JFK-PNQ:1','JFK-SFO:1','JFK-SOF:1','JFK-SVO:1','JFK-TRV:1','JFK-ZRH:1','JMK-ATH:1','KWI-BOS:1','LAS-DEL:1','LAS-HYD:1','LAS-MAA:1','LAX-AKL:1','LAX-AKL:1','LAX-ATQ:1','LAX-BCN:1','LAX-BDQ:1','LAX-BLR:1','LAX-BLR:1','LAX-BNE:1','LAX-BOM:1','LAX-BOM:1','LAX-BOM:1','LAX-BOM:1','LAX-BOM:1','LAX-CAN:1','LAX-COK:1','LAX-DEL:1','LAX-DEL:1','LAX-DEL:1','LAX-DEL:1','LAX-DEL:1','LAX-DXB:1','LAX-HYD:1','LAX-LAX:1','LAX-LAX:1','LAX-MAA:1','LAX-PNQ:1','LAX-SYD:1','LEX-BOM:1','LFW-EWR:1','LGA-CMH:1','LGW-OAK:1','LHR-ATL:1','LIT-BOM:1','LKO-JFK:1','MAA-AMD:1','MAA-ATL:1','MAA-BUF:1','MAA-CMH:1','MAA-DEN:1','MAA-DFW:1','MAA-DFW:1','MAA-DTW:1','MAA-DTW:1','MAA-EWR:1','MAA-JFK:1','MAA-JFK:1','MAA-LHR:1','MAA-MAA:1','MAA-MCI:1','MAA-ORD:1','MAA-ORD:1','MAA-ORD:1','MAA-SFO:1','MAA-SFO:1','MAA-TPA:1','MAA-TPA:1','MAA-XNA:1','MAN-BOS:1','MCI-BLR:1','MCI-BOM:1','MCI-CCU:1','MCI-MAA:1','MCO-BLR:1','MCO-BOM:1','MCO-HYD:1','MCO-TPA:1','MEM-BLR:1','MEM-BOM:1','MEM-BOM:1','MEM-HYD:1','MEM-MAA:1','MEM-MAA:1','MEM-MAA:1','MIA-CDG:1','MIA-DEL:1','MIA-FLR:1','MIA-HYD:1','MIA-MAA:1','MIA-MAD:1','MIA-MIA:1','MIA-SVO:1','MSN-DEL:1','MSP-AMD:1','MSP-BDQ:1','MSP-BLR:1','MSP-BLR:1','MSP-DEL:1','MSP-DEL:1','MSP-IXM:1','MSP-MAA:1','MSP-MAA:1','MSP-MSP:1','MSP-PHX:1','MSP-PNQ:1','MSY-BLR:1','MSY-HYD:1','MSY-MSY:1','OKC-HYD:1','OKC-MAA:1','OMA-BLR:1','OMA-BOM:1','OMA-MAA:1','OMA-MAA:1','ORD-BDQ:1','ORD-BLR:1','ORD-BLR:1','ORD-BOM:1','ORD-BOM:1','ORD-BOM:1','ORD-CCU:1','ORD-CLO:1','ORD-DEL:1','ORD-DEL:1','ORD-DEL:1','ORD-DEL:1','ORD-DEL:1','ORD-DEL:1','ORD-HYD:1','ORD-HYD:1','ORD-IXE:1','ORD-JAI:1','ORD-LHE:1','ORD-MAA:1','ORD-MAA:1','ORD-MAA:1','ORD-PAT:1','ORD-PNQ:1','ORD-PNQ:1','ORD-VTZ:1','ORD-WAW:1','PDX-AMD:1','PDX-BLR:1','PDX-BOM:1','PDX-BOM:1','PDX-BOM:1','PDX-DEL:1','PDX-DEL:1','PDX-HYD:1','PDX-KUL:1','PDX-PNQ:1','PEN-SIN:1','PHL-BOM:1','PHL-CCU:1','PHL-DEL:1','PHL-DEL:1','PHL-MAA:1','PHL-MAA:1','PHL-PHL:1','PHX-AMD:1','PHX-BLR:1','PHX-BLR:1','PHX-BOM:1','PHX-CCU:1','PHX-CCU:1','PHX-DEL:1','PHX-DEL:1','PHX-DEL:1','PHX-EWR:1','PHX-HYD:1','PHX-HYD:1','PHX-MAA:1','PHX-MAA:1','PIT-BLR:1','PIT-BLR:1','PIT-BOM:1','PIT-BOM:1','PIT-BOM:1','PIT-BOM:1','PIT-DEL:1','PIT-HYD:1','PNQ-ORD:1','RDU-AMD:1','RDU-AMD:1','RDU-BLR:1','RDU-BLR:1','RDU-CCU:1','RDU-DEL:1','RDU-DEL:1','RDU-HYD:1','RDU-HYD:1','RDU-MAA:1','RDU-MAA:1','RDU-MAA:1','ROC-HYD:1','ROC-HYD:1','ROC-MAA:1','RSW-HYD:1','SAN-DEL:1','SAN-HYD:1','SAN-HYD:1','SAN-MAA:1','SAN-NRT:1','SAN-TRV:1','SAT-HYD:1','SAT-MAA:1','SAT-MAA:1','SAT-MAA:1','SAT-MAA:1','SAT-SAT:1','SAT-TRV:1','SAV-BOM:1','SDF-BLR:1','SDF-DEL:1','SDF-HYD:1','SDF-MAA:1','SEA-BDQ:1','SEA-BLR:1','SEA-BLR:1','SEA-BOM:1','SEA-BOM:1','SEA-BOM:1','SEA-CCU:1','SEA-CCU:1','SEA-CCU:1','SEA-CJB:1','SEA-DEL:1','SEA-DEL:1','SEA-DEL:1','SEA-GOI:1','SEA-HYD:1','SEA-IAD:1','SEA-LHR:1','SEA-MAA:1','SEA-MAA:1','SEA-MAA:1','SEA-MAA:1','SEA-MAA:1','SEA-MAA:1','SEA-PEK:1','SEA-SEA:1','SEA-VTZ:1','SFO-AMD:1','SFO-AMD:1','SFO-BBI:1','SFO-BLR:1','SFO-BLR:1','SFO-BLR:1','SFO-BOM:1','SFO-BOM:1','SFO-BOM:1','SFO-BOM:1','SFO-BOM:1','SFO-CCU:1','SFO-CJB:1','SFO-COK:1','SFO-COK:1','SFO-DAL:1','SFO-DEL:1','SFO-DEL:1','SFO-DEL:1','SFO-DEL:1','SFO-DEL:1','SFO-DEL:1','SFO-GYE:1','SFO-HKG:1','SFO-HYD:1','SFO-HYD:1','SFO-JAI:1','SFO-LIM:1','SFO-MDE:1','SFO-SFO:1','SFO-SFO:1','SFO-SFO:1','SFO-TLV:1','SFO-TRV:1','SHA-KTM:1','SJC-DEL:1','SJC-HYD:1','SLC-BOM:1','SLC-BOM:1','SLC-DEL:1','SLC-KBP:1','SLC-LHR:1','SMF-DEL:1','STL-BOM:1','SYR-BOM:1','TIA-LCA:1','TLV-NAP:1','TPA-BOM:1','TPA-CCU:1','TPA-DEL:1','TPA-DEL:1','TPA-HYD:1','TPA-HYD:1','TPA-HYD:1','TPA-MAA:1','TUL-CCU:1','TUS-DEL:1','TYS-AMD:1','TYS-BOM:1','XNA-DEL:1','XNA-DEL:1','YYZ-DEL:1']
       Map<String,Integer> routewise = new HashMap<>()
        input.each {
            def key = it.split(':')[0]
            if(routewise[key]){
                routewise[key] = routewise[key]+Integer.parseInt(it.split(':')[1])
            }else{
                routewise[key] = Integer.parseInt(it.split(':')[1])
            }

        }
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("ouput.csv"), "UTF-8")
        BufferedWriter bufWriter = new BufferedWriter(writer)
        routewise.each {
            bufWriter.writeLine(it.key+','+it.value)
        }
        bufWriter.close()
        writer.close()
    }
}

