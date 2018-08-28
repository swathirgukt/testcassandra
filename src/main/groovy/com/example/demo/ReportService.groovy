package com.example.demo

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
@Slf4j
@Component
class ReportService {

    @Autowired
    GDSServiceCallInfoRepository gdsServiceCallInfoRepository

  //   @PostConstruct
    void getReport() {
        long curTime = Calendar.getInstance().timeInMillis
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("report12Jul3.txt"), "UTF-8")
        BufferedWriter bufWriter = new BufferedWriter(writer)
        int i = 0;
        try {
            bufWriter.writeLine('S.No:  :RequestDate:  :ActionType:: ID:: noOfScan:: : totalScans :')
            Calendar calendar = Calendar.instance
            calendar[Calendar.MONTH] = Calendar.JULY
            Date idRequestDate = calendar.getTime()
            int sno = 0
            // for (day in 20..23) {
            //  log.warn("---day:" + day)
            idRequestDate[Calendar.DAY_OF_MONTH] = 12
            BigDecimal totalScans = BigDecimal.ZERO
            for (hour in 11..23) {
                idRequestDate[Calendar.HOUR_OF_DAY] = hour
                for (minute in 0..59) {
                    try {
                        idRequestDate[Calendar.MINUTE] = minute
                        idRequestDate[Calendar.SECOND] = 0
                        //for (second in 0..59) {
                        //  Date reqTime = idRequestDate.clone()
                        // reqTime[Calendar.SECOND] = second
                        i = i + 1
                        idRequestDate[Calendar.MILLISECOND] = 0
                        List<GDSServiceCallInfo> serviceCallInfo = gdsServiceCallInfoRepository.findSabreServiceCallInfoByRequestDate(idRequestDate)
                        // log.warn('')
                        serviceCallInfo.each {
                            log.warn('S.No: ' + sno + ', RequestDate:' + idRequestDate + ', ID: ' + it.id.serviceName + ', noOfScan:' + it.noOfScan + ' *** ' + totalScans)
                            bufWriter.writeLine('S.No: ' + sno + 'RequestDate:' + idRequestDate + ', ID: ' + it.id.serviceName + ', noOfScan:' + it.noOfScan + ' *** ' + totalScans)
                            totalScans = totalScans.add(it.noOfScan)
                            sno++
                        }
                    } catch (all) {
                        bufWriter.writeLine("Exception for this minute:" +   idRequestDate + '  is:'+all)
                    }
                    //  }
                    // log.warn('********************res::findSabreServiceCallInfoByRequestDate' + + '***idRequestDate:' + idRequestDate + ' ........' + i)

                }
            }
            log.warn('*************totalScans:' + totalScans + ' day:' + idRequestDate)
            // }
        } catch (allException) {
            log.warn('*********allException***********res::findSabreServiceCallInfoByRequestDate' + allException, allException)
        } finally {
            def diff =   Calendar.getInstance().timeInMillis - curTime
            bufWriter.writeLine(" total time  to get data: " + diff + ' total db calls:' + i)
            log.warn('*******exit********** total db calls:' + i)
            bufWriter.close()
            writer.close()
        }
    }
}
