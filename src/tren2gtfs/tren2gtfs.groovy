package tren2gtfs
/**
 * Created by yamir on 6/6/13.
 */

import java.util.zip.ZipOutputStream
import java.util.zip.ZipEntry

 resourcesFolder = "../resources"
 trainScheduleFileName =  "trainschedule.csv"

 zipFileName = "TREN2GTFS.zip"
 agency_id = 'TREN'
 agency_name = 'Tren Urbano'
 agency_url = 'www.dtop.gov.pr'
 agency_timezone = 'America/Puerto_Rico'
 agency_lang = null
 agency_phone = null
 agency_fare_url = null


class StopCollection {
    private static def stops = []

    static def getTheStops(){
        if(stops.size()==0){
            populateStops()
        }
        return stops
    }

    static def getStopFromId(stationId) {
        stops.find({stop -> if(stop.stopId == "${stationId}")  stop})
    }

    private static def populateStops(){
        def sagrado_corazon = new Stop(
                codeName: "sagrado_corazon",
                stopId: "16",
                stopCode: "",
                stopName: "Sagrado Coraz\u00F3n",
                stopDesc: "",
                stopLat:"-66.05968D",
                stopLon: "18.437642D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def hato_rey = new Stop(
                codeName: "hato_rey",
                stopId: "15",
                stopCode: "",
                stopName: "Hato Rey",
                stopDesc: "",
                stopLat:"-66.059985D",
                stopLon: "18.430277D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def roosevelt = new Stop(
                codeName: "roosevelt",
                stopId: "14",
                stopCode: "",
                stopName: "Roosevelt",
                stopDesc: "",
                stopLat:"-66.058779D",
                stopLon: "18.424528D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def domenech = new Stop(
                codeName: "domenech",
                stopId: "13",
                stopCode: "",
                stopName: "D\u00F3menech",
                stopDesc: "",
                stopLat:"-66.056338D",
                stopLon: "18.416346D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def pinero = new Stop(
                codeName: "pinero",
                stopId: "12",
                stopCode: "",
                stopName: "Pi\u00F1ero",
                stopDesc: "",
                stopLat:"-66.055225D",
                stopLon: "18.411693D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def universidad = new Stop(
                codeName: "universidad",
                stopId: "11",
                stopCode: "",
                stopName: "Universidad",
                stopDesc: "",
                stopLat:"-66.05193D",
                stopLon: "18.406024D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def rio_piedras = new Stop (
                codeName: "rio_piedras",
                stopId: "10",
                stopCode: "",
                stopName: "Cupey",
                stopDesc: "",
                stopLat:"-66.063209D",
                stopLon: "18.392124D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )
        def cupey = new Stop (
                codeName: "cupey",
                stopId: "9",
                stopCode: "",
                stopName: "R\u00EDo Piedras",
                stopDesc: "",
                stopLat:"-66.052413D",
                stopLon: "18.400251D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def centro_medico = new Stop (
                codeName: "centro_medico",
                stopId: "8",
                stopCode: "",
                stopName: "Centro M\u00E9dico",
                stopDesc: "",
                stopLat:"-66.075734D",
                stopLon: "18.393167D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def san_francisco = new Stop (
                codeName: "san_francisco",
                stopId: "7",
                stopCode: "",
                stopName: "San Francisco",
                stopDesc: "",
                stopLat:"-66.084036D",
                stopLon: "18.392307D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def las_lomas = new Stop (
                codeName: "las_lomas",
                stopId: "6",
                stopCode: "",
                stopName: "Las Lomas",
                stopDesc: "",
                stopLat:"-66.095176D",
                stopLon: "18.392398D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def martinez_nadal = new Stop (
                codeName: "martinez_nadal",
                stopId: "5",
                stopCode: "",
                stopName: "Mart\u00EDnez Nadal",
                stopDesc: "",
                stopLat:"-66.102911D",
                stopLon: "18.392811D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def torrimar = new Stop (
                codeName: "torrimar",
                stopId: "4",
                stopCode: "",
                stopName: "Torrimar",
                stopDesc: "",
                stopLat:"-66.120175D",
                stopLon: "18.393597D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )


        def jardines = new Stop (
                codeName: "jardines",
                stopId: "3",
                stopCode: "",
                stopName: "Jardines",
                stopDesc: "",
                stopLat:"-66.128919D",
                stopLon: "18.395734D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def deportivo = new Stop (
                codeName: "deportivo",
                stopId: "2",
                stopCode: "",
                stopName: "Deportivo",
                stopDesc: "",
                stopLat:"-66.151249D",
                stopLon: "18.395922D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )

        def bayamon = new Stop (
                codeName: "bayamon",
                stopId: "1",
                stopCode: "",
                stopName: "Bayam\u00F3n",
                stopDesc: "",
                stopLat:"-66.153815D",
                stopLon: "18.40095D",
                zoneId: "",
                stopUrl: "",
                locationType: "",
                parentStation: "",
                stopTimezone: "",
                wheelchairBoarding: ""
        )


        stops << sagrado_corazon
        stops << hato_rey
        stops << roosevelt
        stops << domenech
        stops << pinero
        stops << universidad
        stops << rio_piedras
        stops << cupey
        stops << centro_medico
        stops << san_francisco
        stops << las_lomas
        stops << martinez_nadal
        stops << torrimar
        stops << jardines
        stops << deportivo
        stops << bayamon

    }

}

routeTrenUrbano = new Route (
    routeId: "RUTA_TREN",
    agencyId: agency_id,
    routeShortName: "",
    routeLongName: "Ruta del Tren Urbano",
    routeDesc: "Ruta del Tren Urbano de Puerto Rico",
    routeType: "1",
    routeUrl: "www.dtop.gov.pr",
    routecolor: "",
    routeTextColor: ""
)

debug = true

dayTypes = ["LOW_SEASON_WORKDAY", "RESTDAY", "WORKDAY"]

/******/

TrainGlobals.globals['debug'] = debug
timezone = agency_timezone

routes = []
routes << routeTrenUrbano

 agencyFileName = "agency.txt"
 stopsFileName = "stops.txt"
 routesFileName = "routes.txt"
 tripsFileName = "trips.txt"
 stopTimesFileName = "stop_times.txt"

 tren2gtfsFiles = [
         agencyFileName,
         stopsFileName,
         routesFileName,
         tripsFileName,
         stopTimesFileName
      ]

agency = new Agency(agencyId: agency_id,
                    agencyName: agency_name,
                    agencyUrl: agency_url,
                    agencyTimezone: agency_timezone,
                    agencyLang: agency_lang,
                    agencyPhone: agency_phone,
                    agencyFareUrl: agency_fare_url)
createAgencyTxt(agency)
stopCollection = new StopCollection()
createStopsTxt(stopCollection.getTheStops())
createRoutesTxt(routes)
def trips = createTripsTxt(StopCollection.getTheStops(), dayTypes)

//TrainSchedule ts = new TrainSchedule("${resourcesFolder}/${trainScheduleFileName}", stopCollection, timezone).readScheduleFromTrainArrivals()
//readTrainScheduleFile()
//def stopTimes = readStopTimesFromDtopWebsite(trips)
//printStopTimes(stopTimes)
createStopTimesTxt()
createZipFile()

def createAgencyTxt(def agency){
    def agencyTxt = new File("${resourcesFolder}/${agencyFileName}")
    agencyTxt.newWriter()
    agencyTxt << ("agency_id, agency_name,agency_url,agency_timezone,agency_phone,agency_lang") << "\r\n"
    agencyTxt << (agency.agencyId?agency.agencyId+",":",")
    assert(agency.agencyName)
    agencyTxt << (agency.agencyName?agency.agencyName+",":",")
    assert(agency.agencyUrl)
    agencyTxt << (agency.agencyUrl?agency.agencyUrl+",":",")
    assert(agency.agencyTimezone)
    agencyTxt << (agency.agencyTimezone?agency.agencyTimezone+",":",")
    agencyTxt << (agency.agencyLang?agency.agencyLanga+",":",")
    agencyTxt << (agency.agencyPhone?agency.agencyPhone+",":",")
    agencyTxt << (agency.agencyFareUrl?agency.agencyFareUrl:"") << "\r\n"

}

def createStopsTxt(def stops){
    def stopsTxt = new File("${resourcesFolder}/${stopsFileName}")
    stopsTxt.newWriter()
    stopsTxt << ("stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station,stop_timezone,wheelchair_boarding") << "\r\n"
    stops.each { stop ->
        stopsTxt << (stop.stopId?stop.stopId+",":",")
        stopsTxt << (stop.stopCode?stop.stopCode+",":",")
        stopsTxt << (stop.stopName?stop.stopName+",":",")
        stopsTxt << (stop.stopDesc?stop.stopDesc+",":",")
        stopsTxt << (stop.stopLat?stop.stopLat+",":",")
        stopsTxt << (stop.stopLon?stop.stopLon+",":",")
        stopsTxt << (stop.zoneId?stop.zoneId+",":",")
        stopsTxt << (stop.stopUrl?stop.stopUrl+",":",")
        stopsTxt << (stop.locationType?stop.locationType+",":",")
        stopsTxt << (stop.parentStation?stop.parentStation+",":",")
        stopsTxt << (stop.stopTimezone?stop.stopTimezone+",":",")
        stopsTxt << (stop.wheelchairBoarding?stop.wheelchairBoarding:"") << "\r\n"
    }

}

def createRoutesTxt(def routes){
    def routesTxt = new File ("${resourcesFolder}/${routesFileName}")
    routesTxt.newWriter()
    routesTxt << ("route_id,route_short_name,route_long_name,route_desc,route_type") << "\r\n"
    routes.each { route ->
        routesTxt << (route.routeId?route.routeId+",":",")
        routesTxt << (route.agencyId?route.agencyId+",":",")
        routesTxt << (route.routeShortName?route.routeShortName+",":",")
        routesTxt << (route.routeLongName?route.routeLongName+",":",")
        routesTxt << (route.routeDesc?route.routeDesc+",":",")
        routesTxt << (route.routeType?route.routeType+",":",")
        routesTxt << (route.routeUrl?route.routeUrl+",":",")
        routesTxt << (route.routecolor?route.routecolor+",":",")
        routesTxt << (route.routeTextColor?route.routeTextColor+",":",") << "\r\n"
    }

}

def createTripsTxt(stops, dayTypes){
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

    def trips = []

    int count = 0;
    for(int startId=1; startId<= stops.size(); startId++){
        Stop theStart = StopCollection.getStopFromId(startId)
        dayTypes.each {dayType ->
            if(startId < stops.size()){
                for (int stopId=startId+1; stopId<=stops.size(); stopId++){
                    Stop theStop = StopCollection.getStopFromId(stopId)
                    Trip trip = new Trip();
                    trip.startStop = theStart
                    trip.endStop = theStop
                    trip.routeId = ((Route) routes[0]).routeId
                    trip.serviceId = dayType
                    trip.tripId = "${++count}"
                    trip.tripHeadsign = theStop.stopName
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
                    Stop theStop = StopCollection.getStopFromId(stopId)
                    Trip trip = new Trip();
                    trip.startStop = theStart
                    trip.endStop = theStop
                    trip.routeId = ((Route) routes[0]).routeId
                    trip.serviceId = dayType
                    trip.tripId = "${++count}"
                    trip.tripHeadsign = theStop.stopName
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

    def tripsTxt = new File ("${resourcesFolder}/${tripsFileName}")
    tripsTxt.newWriter()

    tripsTxt << ("route_id,service_id,trip_id,trip_headsign,trip_short_name,direction_id,block_id,shape_id,wheelchair_accessible") << '\r\n'

    for(Trip t: trips){
        printTrip.call(tripsTxt,t)
    }
    return trips
}

def createStopTimesTxt(){
    def stopTimesTxt = new File("${resourcesFolder}/${stopTimesFileName}")
    stopTimesTxt.newWriter()
    stopTimesTxt << ("trip_id, arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled") << "\r\n"


//    agencyTxt << (agency.agencyId?agency.agencyId+",":",")
//    assert(agency.agencyName)
//    agencyTxt << (agency.agencyName?agency.agencyName+",":",")
//    assert(agency.agencyUrl)
//    agencyTxt << (agency.agencyUrl?agency.agencyUrl+",":",")
//    assert(agency.agencyTimezone)
//    agencyTxt << (agency.agencyTimezone?agency.agencyTimezone+",":",")
//    agencyTxt << (agency.agencyLang?agency.agencyLanga+",":",")
//    agencyTxt << (agency.agencyPhone?agency.agencyPhone+",":",")
//    agencyTxt << (agency.agencyFareUrl?agency.agencyFareUrl:"") << "\r\n"

}


def createZipFile(){
    ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream("${resourcesFolder}/${zipFileName}"))
    tren2gtfsFiles.each() { fileName ->
        zipFile.putNextEntry(new ZipEntry(fileName))
        def buffer = new byte[1024]
        def file = new File("${resourcesFolder}/${fileName}")
        file.withInputStream { i ->
            def l = i.read(buffer)
            // check wether the file is empty
            if (l > 0) {
                zipFile.write(buffer, 0, l)
            }
        }
        zipFile.closeEntry()
    }
    zipFile.close()
}

//Stop getStopFromNumber(int i){
//    stops.find{stop -> if(stop.stopId == "${i}") return stop}
//}
//
///**
// * Reads stop times from http://www.dtop.gov.pr/itinerario.asp
// */
//def readStopTimesFromDtopWebsite(def trips){
//    def trainScheduleUrlFrom = {direction, departStationId -> "http://tempo7.praxisinteractive.net/dtopweb/itinerario/departure.php?st_id=${direction}&pl_id=${departStationId}"}
//    def trainScheduleUrlDeparting = {direction, departStationId, trainId -> "http://tempo7.praxisinteractive.net/dtopweb/itinerario/plataforma2.php?st_id=${direction}&pl_id=${departStationId}&train_id=${trainId}"}
//    def trainScheduleUrlArrivalTime = {direction, departStationId, trainId, arriveStationId -> "http://tempo7.praxisinteractive.net/dtopweb/itinerario/arrival.php?st_id=${direction}&pl_id=${departStationId}&train_id=${trainId}&pl_id2=${arriveStationId}" }
//
//    WebConnectService webConnectService = new WebConnectService()
//    def stopTimes = []
//    def directions = ["1"]
//    for (String direction: directions) {
//        for(Stop startStop:stops){
//            println startStop.stopName+" : "
//            def pageWithDepartingTimes = webConnectService.doGetConnection(new URL(trainScheduleUrlFrom.call(direction, startStop.stopId)), "")
//            def page = TrenXmlParserFactory.getXmlParser().parseText(pageWithDepartingTimes.webpage);
//
//            def departTimes = page.depthFirst().getAt('OPTION')
//
//            int count = 0;
//
//            def departTimesList = []
//            departTimes.each{time ->
//                if(count == 0){
//                    count++
//                    return null
//                }
//                departTimesList << [trainId: time.depthFirst()[0]."@value", departureTime: time.value()[0] ]
//                println "departTime:"+time.depthFirst()[0]."@value" + "|" + time.value()[0]
//            }
//
//            departTimesList.each { departTime ->
//                println stops.size()
//                println startStop.stopId
//                Boolean doNotContinue = false;
//
//                    //println trainScheduleUrlDeparting.call(direction, stop.stopId, departTime['trainId'])
//                def pageWithArrivalStationText = webConnectService.doGetConnection(new URL(trainScheduleUrlDeparting.call(direction, startStop.stopId, departTime['trainId'])), "")
//
//                println pageWithArrivalStationText
//                def pageWithArrivalStationXml = TrenXmlParserFactory.getXmlParser().parseText(pageWithArrivalStationText.webpage);
//                def arrivalStations = pageWithArrivalStationXml.depthFirst().getAt('OPTION')
//
//                def arrivalStationsList = []
//                int countArrivalStations = 0
//                arrivalStations.each{ arrivalStation ->
//                    if(countArrivalStations == 0){
//                        countArrivalStations++
//                        return null
//                    }
//                    //def station = arrivalStation.depthFirst()[0].value()[0]
//                    //if(station != "[ Seleccione ]"){
//                        def arrivalStationMap = [station: arrivalStation.depthFirst()[0].value()[0], value: arrivalStation.depthFirst()[0].@"value"]
//                        arrivalStationsList << arrivalStationMap
//                        println arrivalStationMap
//                        println arrivalStationMap.station +" | "+arrivalStationMap.value
//                        //}
//                }
//
//                arrivalStationsList.each { arrivalStation ->
//                   println trainScheduleUrlArrivalTime.call(direction, startStop.stopId, departTime['trainId'], arrivalStation.value)
//                   def pageWithArrivalTimeText = webConnectService.doGetConnection(new URL(
//                           trainScheduleUrlArrivalTime.call(direction, startStop.stopId, departTime['trainId'], arrivalStation.value)
//                        ), "")
//                    //def pageWithArrivalTimeXml = TrenXmlParserFactory.getXmlParser().parseText(pageWithArrivalTimeText.webpage);
//                    //def arrivalTime = pageWithArrivalTimeXml.depthFirst()
//                    println "*****Arrival Time:"+ pageWithArrivalTimeText.webpage+"*****\n"
//                    def at = pageWithArrivalTimeText.webpage
//
//                    def calculateStopSequence = {Stop beginStop, Stop finishStop ->
//                        int beginStopInt = new Integer(beginStop.stopId)
//                        int endStopInt = new Integer(finishStop.stopId)
//                        if(beginStopInt > endStopInt){
//                            beginStopInt - endStopInt
//                        } else if (endStopInt > beginStopInt) {
//                            endStopInt - beginStopInt
//                        } else {
//                            assert false: "Beginning and End Stop should not be the same.  Received: beginStop = ${beginStopInt}; endStop = ${endStopInt} "
//                        }
//
//                    }
//                    def finishStop = getStopFromNumber(new Integer((String)arrivalStation.value))
//                    def stopTime = new StopTime(
//                             tripId: getTripFromStartStopAndEndStop(trips, startStop, finishStop),
//                             arrivalTime: at,
//                             departureTime: departTime,
//                             stopId: startStop.stopId,
//                             stopSequence: calculateStopSequence.call(startStop, finishStop)
//
//                    )
//                    stopTimes << stopTime
//
//                }
//                    //arrivalStation.each
//
//            }
//
//        }
//    }
//
//    return stopTimes
//
//}
//
//def printStopTimes(def stopTimes){
//    def printStopTime = {theFile, StopTime stopTime ->
//        theFile << stopTime.tripId+","
//        theFile << stopTime.arrivalTime+","
//        theFile << stopTime.departureTime+","
//        theFile << stopTime.stopId+","
//        theFile << stopTime.stopSequence << "\r\n"
//    }
//
//    def stopTimesTxt = new File ("${resourcesFolder}/${stopTimesFileName}")
//    stopTimesTxt.newWriter()
//
//    stopTimesTxt << ("trip_id,arrival_time,departure_time,stop_id,stop_sequence") << '\r\n'
//
//    for(StopTime st: stopTimes){
//        printStopTime.call(stopTimesTxt,st)
//    }
//}
//
//Trip getTripFromStartStopAndEndStop(def trips, Stop startStop, Stop endStop){
//    for(Trip t: trips){
//        if(t.startStop.stopId == startStop.stopId && t.endStop.stopId == endStop.stopId){
//            return t
//        }
//    }
//
//    assert false: "No trip found!\nReceived:\nstartStop = [stopName=${startStop.stopName}, stopId=${startStop.stopId}]"+
//                    "endStop = [stopName=${endStop.stopName}, stopId=${endStop.stopId}]"
//
//}







