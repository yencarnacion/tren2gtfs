package tren2gtfs
/**
 * Created by yamir on 6/16/13.
 */
@Grab(
        group='joda-time',
        module='joda-time',
        version='2.2'
)
@GrabExclude('xml-apis:xml-apis')
import org.joda.time.DateTimeZone
import org.joda.time.DateTime

class TrainLocalTime implements Comparable<TrainLocalTime> {
    int trainLocalHourI
    int trainLocalMinuteI
    int trainLocalSecondI
    boolean nextDay = false
    String trainLocalTimeZone = "America/Puerto_Rico"
    TrainLocalTime(String trainLocalTimeS){
        def (hour, minute, second) = trainLocalTimeS.split(":")

        int hourInt = ((String) hour).toInteger()
        int minuteInt = ((String) minute).toInteger()
        int secondInt = ((String) second).toInteger()
        nextDay = false

        if(hourInt>=21 && hourInt <24){
            trainLocalHourI = 8-(24-hourInt)
            trainLocalMinuteI = minuteInt
            trainLocalSecondI = secondInt
            nextDay = false
        } else if (hourInt>=0 && hourInt < 16){
            trainLocalHourI = 8 + hourInt
            trainLocalMinuteI = minuteInt
            trainLocalSecondI = secondInt
            nextDay = false
        } else if (hourInt>=16 && hourInt < 21){
            trainLocalHourI = hourInt - 16
            trainLocalMinuteI = minuteInt
            trainLocalSecondI = secondInt
            nextDay = true
        } else {
            throw new Exception ("Unknown hour: "+trainLocalTimeS)
        }
    }

    // HH:mm:ss
    String toString(String pattern="HH:mm:ss"){

        DateTime jdt = new DateTime(DateTimeZone.forID(trainLocalTimeZone));
        DateTime ljdt = new DateTime(
                                        jdt.getYear(),
                                        jdt.getMonthOfYear(),
                                        jdt.getDayOfMonth(),
                                        trainLocalHourI,
                                        trainLocalMinuteI,
                                        trainLocalSecondI,
                                        DateTimeZone.forID(trainLocalTimeZone)
        )
        def retString
        if(nextDay){
            def timePattern = ljdt.toString(pattern)
            def (h, m, s) = timePattern.split(":")
            def hi = ((String) h).toInteger()+24
            retString = "${hi}:${m}:${s}"
        } else {
            retString = ljdt.toString(pattern)
        }
        return retString
    }

    int compareTo(TrainLocalTime o){

        DateTime jdt = new DateTime(DateTimeZone.forID(trainLocalTimeZone));
        DateTime ljdt = new DateTime(
                jdt.getYear(),
                jdt.getMonthOfYear(),
                jdt.getDayOfMonth(),
                trainLocalHourI,
                trainLocalMinuteI,
                trainLocalSecondI,
                DateTimeZone.forID(trainLocalTimeZone)
        )
        if(nextDay){
            ljdt = ljdt.plusDays(1)
        }

        DateTime ojdt = new DateTime(
                jdt.getYear(),
                jdt.getMonthOfYear(),
                jdt.getDayOfMonth(),
                o.trainLocalHourI,
                o.trainLocalMinuteI,
                o.trainLocalSecondI,
                DateTimeZone.forID(trainLocalTimeZone)
        )
        if(o.nextDay){
            ojdt = ojdt.plusDays(1)
        }

        int comparison = ljdt.compareTo(ojdt)
        return comparison
    }

    boolean isAfter(TrainLocalTime o){
        DateTime jdt = new DateTime(DateTimeZone.forID(trainLocalTimeZone));
        DateTime ljdt = new DateTime(
                jdt.getYear(),
                jdt.getMonthOfYear(),
                jdt.getDayOfMonth(),
                trainLocalHourI,
                trainLocalMinuteI,
                trainLocalSecondI,
                DateTimeZone.forID(trainLocalTimeZone)
        )
        if(nextDay){
            ljdt = ljdt.plusDays(1)
        }

        DateTime ojdt = new DateTime(
                jdt.getYear(),
                jdt.getMonthOfYear(),
                jdt.getDayOfMonth(),
                o.trainLocalHourI,
                o.trainLocalMinuteI,
                o.trainLocalSecondI,
                DateTimeZone.forID(trainLocalTimeZone)
        )
        if(o.nextDay){
            ojdt = ojdt.plusDays(1)
        }

        int comparison = ljdt.compareTo(ojdt)
        return (comparison > 0)
    }
}


