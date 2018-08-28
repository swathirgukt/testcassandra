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
class AmadServiceCallInfoDataTest {
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
             // for (day in 17..19) {
            int day = 21
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
                                  List<GDSServiceCallInfo> serviceCallInfo = gdsServiceCallInfoRepository.findAmadeusServiceCallInfoByRequestDateAndTime(idRequestDate)
                                  log.warn("--serviceCallInfo:" + serviceCallInfo.size() + " *** idRequestDate:" + idRequestDate)
                                  serviceCallInfo.each {
                                      log.warn('S.No: ' + sno + ', RequestDate:' + it.id.requestDate + "req time:" + it.id.requestDateTime + ', ID: ' + it.id.serviceName + ', noOfScan:' + it.noOfScan + ' *** ' + totalScans)
                                      bufWriter.writeLine('S.No: ' + sno + 'RequestDate:' + idRequestDate + ' time: ' + it.id.requestDateTime + ', serviceName: ' + it.id.serviceName + ', noOfScan:' + it.noOfScan + ' *** ' + totalScans)
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
              //}
        } catch (all) {
            log.warn('--------excpn:' + all)
        }
    }

    @Test
    void getCalls(){

    }
}

