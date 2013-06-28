package tren2gtfs
//@Grab(
//        group='joda-time',
//        module='joda-time',
//        version='2.2'
//)
//@GrabExclude('xml-apis:xml-apis')
import org.joda.time.DateTimeZone
import org.joda.time.DateTime

class Agency {
    String agencyId
    String agencyName
    String agencyUrl
    String agencyTimezone
    String agencyLang
    String agencyPhone
    String agencyFareUrl
}

class Route {
    String routeId
    String agencyId
    String routeShortName
    String routeLongName
    String routeDesc
    String routeType
    String routeUrl
    String routecolor
    String routeTextColor
}


class Stop {
    String codeName
    String stopId
    String stopCode
    String stopName
    String stopDesc
    String stopLat
    String stopLon
    String zoneId
    String stopUrl
    String locationType
    String parentStation
    String stopTimezone
    String wheelchairBoarding

    Boolean equals(Stop that){
        if(this.stopId == that.stopId){
            return true
        }
        return false
    }
}

class Trip {
    Stop startStop
    Stop endStop
    String routeId
    String serviceId
    String tripId
    String tripHeadsign
    String tripShortName
    String directionId
    String blockId
    String shapeId
    String wheelchairAccessible
}

class Trips {
    private def trips = []
    private String tripsFileName

    Trips(tripsFileName, trips){
        this.tripsFileName = tripsFileName
        this.trips = trips
    }

    def createTripsTxt(){
        def printTrip = {theFile, trip ->
            theFile << trip.routeId+","
            theFile << trip.serviceId+","
            theFile << trip.tripId+","
            theFile << trip.tripHeadsign+","
            theFile << trip.tripShortName+","
            theFile << trip.directionId+","
            theFile << trip.shapeId+","
            theFile << trip.wheelchairAccessible+","
            theFile << "\r\n"

        }
        def tripsTxt = new File ("${tripsFileName}")
        tripsTxt.newWriter()

        tripsTxt << ("route_id,service_id,trip_id,trip_headsign,trip_short_name,direction_id,block_id,shape_id,wheelchair_accessible") << '\r\n'

        for(Trip t: trips){
            printTrip.call(tripsTxt,t)
        }
    }

}
class Trips2 {
    private def routes
    private def stopCollection
    private def dayTypes
    private def trips = []
    private String tripsFileName

    Trips2(tripsFileName, routes, stopCollection, dayTypes){
        this.routes = routes
        this.stopCollection = stopCollection
        this.dayTypes = dayTypes
        this.tripsFileName = tripsFileName
    }

    def createTripsTxt(){
        def printTrip = {theFile, trip ->
            theFile << trip.routeId+","
            theFile << trip.serviceId+","
            theFile << trip.tripId+","
            theFile << trip.tripHeadsign+","
            theFile << trip.tripShortName+","
            theFile << trip.directionId+","
            theFile << trip.shapeId+","
            theFile << trip.wheelchairAccessible+","
            theFile << "\r\n"

        }

        int count = 0;
        def stops = stopCollection.getTheStops();
        for(int startId=1; startId<= stops.size(); startId++){
            Stop theStart = stopCollection.getStopFromId(startId)
            dayTypes.each {dayType ->
                if(startId < stops.size()){
                    for (int stopId=startId+1; stopId<=stops.size(); stopId++){
                        Stop theStop = stopCollection.getStopFromId(stopId)
                        Trip trip = new Trip();
                        trip.startStop = theStart
                        trip.endStop = theStop
                        trip.routeId = ((Route) routes[0]).routeId
                        trip.serviceId = dayType
                        trip.tripId = "${++count}"
                        trip.tripHeadsign = "TO_SAGRADO" // theStop.stopName
                        trip.tripShortName = "${theStart.stopName} / ${theStop.stopName}"
                        trip.directionId = "TO_SAGRADO"
                        trip.blockId = ""
                        trip.shapeId = ""
                        trip.wheelchairAccessible = ""
                        trips << trip
                    }
                }
                if(startId > 1){
                    for (int stopId = startId -1; stopId >=1; stopId--){
                        Stop theStop = stopCollection.getStopFromId(stopId)
                        Trip trip = new Trip();
                        trip.startStop = theStart
                        trip.endStop = theStop
                        trip.routeId = ((Route) routes[0]).routeId
                        trip.serviceId = dayType
                        trip.tripId = "${++count}"
                        trip.tripHeadsign = "TO_BAYAMON" // theStop.stopName
                        trip.tripShortName = "${theStart.stopName} / ${theStop.stopName}"
                        trip.directionId = "TO_BAYAMON"
                        trip.blockId = ""
                        trip.shapeId = ""
                        trip.wheelchairAccessible = ""
                        trips << trip
                    }
                }
            }
        }

        def tripsTxt = new File ("${tripsFileName}")
        tripsTxt.newWriter()

        tripsTxt << ("route_id,service_id,trip_id,trip_headsign,trip_short_name,direction_id,block_id,shape_id,wheelchair_accessible") << '\r\n'

        for(Trip t: trips){
            printTrip.call(tripsTxt,t)
        }
        return trips
    }

    def getTheTrips(){
        return trips
    }

    def findTrip(Stop startStopStation, Stop destinationStopStation, String daytype, String direction){
        trips.find{Trip trip ->
            if((trip.startStop.equals(startStopStation)) &&
                    (trip.endStop.equals(destinationStopStation)) &&
                    (trip.serviceId.toUpperCase() == daytype.toUpperCase()) &&
                    (trip.directionId.toUpperCase() == direction.toUpperCase())
            ) trip}
    }


}

class TrainArrival {
    String arrivesTo
    String direction
    String daytype
    String arrivalTime
}

class StopTime {
    String tripId
    String arrivalTime
    String departureTime
    String stopId
    String stopSequence
}

class TrainLocalTime implements Comparable<TrainLocalTime> {
    int trainLocalHourI
    int trainLocalMinuteI
    int trainLocalSecondI
    boolean nextDay = false
    String trainLocalTimeZone = "America/Puerto_Rico"

    TrainLocalTime(String trainLocalTimeS, tz="America/Puerto_Rico"){
        trainLocalTimeZone = tz

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

    boolean LocalTrainTimeAfter(TrainLocalTime o){
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

class TrainSchedule {
    private def trainScheduleFileName
    private def trainArrivals = []
    private def dayTypes = []
    private def directions = []
    private Object stopCollection
    private def routes
    private def timeZone = "America/Puerto_Rico"

    TrainSchedule(String file, routes, Object stopCollection, String tz="America/Puerto_Rico"){
        trainScheduleFileName = "${file}"
        timeZone = tz
        this.routes = routes
        this.stopCollection = stopCollection
        readTrainScheduleFile()
        this
    }

    private def readTrainScheduleFile() {
        def trainScheduleFile = new File (trainScheduleFileName)

        trainScheduleFile.eachLine() { line, lineNo ->
            if (lineNo>1) {
                line = line.replace("\"","")
                def( arrivesTo, direction, daytype, arrivalTime) = line.split(",")
                TrainArrival trainArrival = new TrainArrival(
                        arrivesTo: ((String) arrivesTo).toLowerCase(),
                        direction: ((String) direction).toLowerCase(),
                        daytype: ((String) daytype).toLowerCase(),
                        arrivalTime: ((String) arrivalTime).toLowerCase()
                )
                if(!(daytype in dayTypes)){
                    dayTypes << daytype
                }
                if(!((((String) direction).toLowerCase()) in directions)){
                    directions << ((String) direction).toLowerCase()
                }
                trainArrivals << trainArrival
            }
        }

        if(TrainGlobals.globals['debug']){
            println "Read the following daytypes: "+dayTypes.each{it}
            println "Read the following directions: "+directions.each{it}
            println "Read ${trainArrivals.size()} schedules"
            /*for (TrainArrival t: trainArrivals){
                println t.dump();
            }*/
        }

        return trainArrivals
    }

    /**
     * Reads stop times from http://www.dtop.gov.pr/itinerario.asp
     */
    def getStopTimesAndTripsFromTrainArrivals(){

        def stops = stopCollection.getTheStops()
        def stopTimes = []
        def trips = []
        int i=1;
        for (String direction: directions) {
            for(int startStationId = 1; startStationId <= stops.size(); startStationId++){
                def startStopStation = stopCollection.getStopFromId(startStationId)
                for(int stopStationId = 1; stopStationId <= stops.size(); stopStationId++){

//                    //For Testing
//                    if(TrainGlobals.globals['debug']){
//                        if(!(((direction == 'to_sagrado')&& (startStationId==1) && (stopStationId==16)) ||
//                                ((direction == "to_bayamon") && (startStationId==16) && (stopStationId==1)))
//                        )
//                            continue;
//                    }
//                    if(TrainGlobals.globals['debug']){
//                        if(!(((direction == 'to_sagrado')&& (startStationId==15) && (stopStationId==16)))
//                        )
//                            continue;
//                    }

                    if((direction == 'to_sagrado' && (startStationId < stopStationId) ) ||
                            (direction == 'to_bayamon' && (startStationId > stopStationId))
                    ){
                        def destinationStopStation = stopCollection.getStopFromId(stopStationId)
                        dayTypes.each({daytype ->

                            if(TrainGlobals.globals['debug']){
                                println ">>>>>>>"
                                println "[${i++}]-${daytype}-(${direction}) From ${startStopStation.stopName}(${startStationId}) To ${destinationStopStation.stopName}(${stopStationId})"// leaving at ${leavingTime}"
//                                    println "Leaves: ${leavingTime} and Arrives: ${arrivalTime}"
                                println "<<<<<<<"
                            }
                            def leavingTimes = getStopTrainScheduleTimes(startStopStation, direction, daytype)
                            leavingTimes.each({ leavingTime ->
                                //def arrivalTime = getStopTrainArrivalTime(startStationId, direction, leavingTime, stopStationId, daytype)



                                def retval = getStopTrainStopSequence(startStationId, direction, leavingTime, stopStationId, daytype, trips.size())

                                Trip t = retval.trip
                                trips << t

                                retval.stopSequence.eachWithIndex {sseq, index ->

                                    StopTime stopTime = new StopTime(
                                            tripId: t.tripId.toString(),
                                            arrivalTime: sseq.arrivalTime.toString(),
//                                            departureTime: leavingTime.toString(),
                                            departureTime: sseq.arrivalTime.toString(),
                                            stopId: sseq.stopId.toString(),
                                            stopSequence: sseq.stopSequence.toString()
                                    )

                                    stopTimes << stopTime
                                    if(true){
                                        def stop = true
                                    }
                                }

                            })
                        })
                    }
                }
            }
        }
        return [stopTimes: stopTimes, trips: trips]
    }

    def getStopTrainStopSequence(int fromStationId, String direction, fromTrainTime, int toStationId, daytype, numberOfglobalTrips){
        def stops = stopCollection.getTheStops()
        def stopSequence = []

        def startStopStation
        def destinationStopStation

        int tripNumber = numberOfglobalTrips+1
//        def localTrips = []
//        Trip trip

        startStopStation = stopCollection.getStopFromId(fromStationId)
        destinationStopStation = stopCollection.getStopFromId(toStationId)

        int directionInt = -1
        if(direction.toUpperCase() == "BAYAMON"){
            directionInt = 0
        } else {
            directionInt = 1
        }
        def localTrip = new Trip(
                startStop:  startStopStation,
                endStop: destinationStopStation,
                routeId: ((Route) routes[0]).routeId,
                serviceId: daytype,
                tripId: "${tripNumber++}",
                tripHeadsign: direction.toUpperCase(),// theStop.stopName
                tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
                directionId: directionInt,
                blockId: "",
                shapeId: "",
                wheelchairAccessible: ""
        );

        if(direction=="to_bayamon"){
            assert(fromStationId>toStationId)
            def stopSequenceItem = [
                    arrivalTime: fromTrainTime,
                    departureTime: fromTrainTime,
                    stopId: fromStationId,
                    stopSequence: 1
            ]
            stopSequence << stopSequenceItem

//            startStopStation = stopCollection.getStopFromId(fromStationId)
//            destinationStopStation = stopCollection.getStopFromId(toStationId)
//
//            trip = new Trip(
//                    startStop:  startStopStation,
//                    endStop: destinationStopStation,
//                    routeId: ((Route) routes[0]).routeId,
//                    serviceId: daytype,
//                    tripId: "${tripNumber++}",
//                    tripHeadsign: direction.toUpperCase(),// theStop.stopName
//                    tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
//                    directionId: direction.toUpperCase(),
//                    blockId: "",
//                    shapeId: "",
//                    wheelchairAccessible: ""
//            );
//            localTrips << trip

            int nextStation = fromStationId-1
            def nextStationSchedule = getStopTrainScheduleTimes(stopCollection.getStopFromId(nextStation), direction, daytype)
            def nextStationArrival = nextStationSchedule.find({
                try{
                    if(((TrainLocalTime) it).LocalTrainTimeAfter(fromTrainTime)) ((TrainLocalTime) it)
                } catch (NullPointerException npe){
                    null
                }
            })


            if(nextStationArrival){
                stopSequenceItem = [
                        arrivalTime: nextStationArrival,
                        departureTime: nextStationArrival,
                        stopId: nextStation,
                        stopSequence: fromStationId - nextStation + 1
                ]
                stopSequence << stopSequenceItem

//                startStopStation = stopCollection.getStopFromId(fromStationId)
//                destinationStopStation = stopCollection.getStopFromId(nextStation)
//
//                trip = new Trip(
//                        startStop:  startStopStation,
//                        endStop: destinationStopStation,
//                        routeId: ((Route) routes[0]).routeId,
//                        serviceId: daytype,
//                        tripId: "${tripNumber++}",
//                        tripHeadsign: direction.toUpperCase(),// theStop.stopName
//                        tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
//                        directionId: direction.toUpperCase(),
//                        blockId: "",
//                        shapeId: "",
//                        wheelchairAccessible: ""
//                );
//                localTrips << trip

                while(nextStation>toStationId){
                    nextStation = nextStation - 1
                    nextStationSchedule = getStopTrainScheduleTimes(stopCollection.getStopFromId(nextStation), direction, daytype)
                    nextStationArrival = nextStationSchedule.find({
                        try{
                            if(((TrainLocalTime) it).LocalTrainTimeAfter(nextStationArrival)) it
                        } catch (NullPointerException npe){
                            null
                        }
                    })

                    if(nextStationArrival){
                        stopSequenceItem = [
                                arrivalTime: nextStationArrival,
                                departureTime: nextStationArrival,
                                stopId: nextStation,
                                stopSequence: fromStationId - nextStation + 1
                        ]
                        stopSequence << stopSequenceItem

//                        startStopStation = stopCollection.getStopFromId(fromStationId)
//                        destinationStopStation = stopCollection.getStopFromId(nextStation)
//
//                        trip = new Trip(
//                                startStop:  startStopStation,
//                                endStop: destinationStopStation,
//                                routeId: ((Route) routes[0]).routeId,
//                                serviceId: daytype,
//                                tripId: "${tripNumber++}",
//                                tripHeadsign: direction.toUpperCase(),// theStop.stopName
//                                tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
//                                directionId: direction.toUpperCase(),
//                                blockId: "",
//                                shapeId: "",
//                                wheelchairAccessible: ""
//                        );
//                        localTrips << trip
                    }
                }
                return [stopSequence: stopSequence, trip: localTrip]
            }
        }

        if(direction=="to_sagrado"){
            assert (toStationId>fromStationId)
            def stopSequenceItem = [
                    arrivalTime: fromTrainTime,
                    departureTime: fromTrainTime,
                    stopId: fromStationId,
                    stopSequence: 1
            ]
            stopSequence << stopSequenceItem

//            startStopStation = stopCollection.getStopFromId(fromStationId)
//            destinationStopStation = stopCollection.getStopFromId(toStationId)
//
//            trip = new Trip(
//                    startStop:  startStopStation,
//                    endStop: destinationStopStation,
//                    routeId: ((Route) routes[0]).routeId,
//                    serviceId: daytype,
//                    tripId: "${tripNumber++}",
//                    tripHeadsign: direction.toUpperCase(),// theStop.stopName
//                    tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
//                    directionId: direction.toUpperCase(),
//                    blockId: "",
//                    shapeId: "",
//                    wheelchairAccessible: ""
//            );
//            localTrips << trip

            int nextStation = fromStationId+1
            def nextStationSchedule = getStopTrainScheduleTimes(stopCollection.getStopFromId(nextStation), direction, daytype)

            def nextStationArrival = nextStationSchedule.find({
                try{
                    if(((TrainLocalTime) it).LocalTrainTimeAfter(fromTrainTime)) it
                } catch (NullPointerException npe){
                    null
                }
            })
            if(nextStationArrival){
                stopSequenceItem = [
                        arrivalTime: nextStationArrival,
                        departureTime: nextStationArrival,
                        stopId: nextStation,
                        stopSequence: nextStation - fromStationId + 1
                ]
                stopSequence << stopSequenceItem

//                startStopStation = stopCollection.getStopFromId(fromStationId)
//                destinationStopStation = stopCollection.getStopFromId(nextStation)
//
//                trip = new Trip(
//                        startStop:  startStopStation,
//                        endStop: destinationStopStation,
//                        routeId: ((Route) routes[0]).routeId,
//                        serviceId: daytype,
//                        tripId: "${tripNumber++}",
//                        tripHeadsign: direction.toUpperCase(),// theStop.stopName
//                        tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
//                        directionId: direction.toUpperCase(),
//                        blockId: "",
//                        shapeId: "",
//                        wheelchairAccessible: ""
//                );
//                localTrips << trip
            }

            while(nextStation<toStationId){
                nextStation = nextStation + 1
                nextStationSchedule = getStopTrainScheduleTimes(stopCollection.getStopFromId(nextStation), direction, daytype)
                nextStationArrival = nextStationSchedule.find({
                    try{
                        if(((TrainLocalTime) it).LocalTrainTimeAfter(nextStationArrival)) ((TrainLocalTime) it)
                    } catch(NullPointerException npe){
                        null
                    }
                })

                if(nextStationArrival){
                    stopSequenceItem = [
                            arrivalTime: nextStationArrival,
                            departureTime: nextStationArrival,
                            stopId: nextStation,
                            stopSequence: nextStation - fromStationId + 1
                    ]

                    stopSequence << stopSequenceItem

//                    startStopStation = stopCollection.getStopFromId(fromStationId)
//                    destinationStopStation = stopCollection.getStopFromId(nextStation)
//
//                    trip = new Trip(
//                            startStop:  startStopStation,
//                            endStop: destinationStopStation,
//                            routeId: ((Route) routes[0]).routeId,
//                            serviceId: daytype,
//                            tripId: "${tripNumber++}",
//                            tripHeadsign: direction.toUpperCase(),// theStop.stopName
//                            tripShortName: "${startStopStation.stopName} / ${destinationStopStation.stopName}",
//                            directionId: direction.toUpperCase(),
//                            blockId: "",
//                            shapeId: "",
//                            wheelchairAccessible: ""
//                    );
//                    localTrips << trip
                }
            }
            return [stopSequence: stopSequence, trip: localTrip]
        }

        assert false

    }

    def getStopTrainScheduleTimes(station, direction, daytype){
        def stationCodeName = station.codeName
        assert(stationCodeName!=null)
        trainArrivals.findAll({trainArrival ->
            if(((String) trainArrival.arrivesTo).toUpperCase() == stationCodeName.toUpperCase() &&
                    trainArrival.direction.toUpperCase() == direction.toUpperCase() &&
                    trainArrival.daytype.toUpperCase() == daytype.toUpperCase() )
                trainArrival
        }).collect({((TrainLocalTime) new TrainLocalTime(it.arrivalTime, timeZone))}).sort()
    }
}

class TrainGlobals {
    static def globals = [:]
}

class CalendarItem {
    String serviceId
    String monday
    String tuesday
    String wednesday
    String thursday
    String friday
    String saturday
    String sunday
    String startDate
    String endDate
}
