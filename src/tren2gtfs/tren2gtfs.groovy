package tren2gtfs
/**
 * Created by yamir on 6/6/13.
 */
@Grab( 'net.sourceforge.nekohtml:nekohtml:1.9.18' )
@GrabExclude('xml-apis:xml-apis')
import java.util.zip.ZipOutputStream
import java.util.zip.ZipEntry

 zipFileName = "TREN2GTFS.zip"
 agency_id = 'TREN'
 agency_name = 'Tren Urbano'
 agency_url = 'www.dtop.gov.pr'
 agency_timezone = 'America/Puerto_Rico'
 agency_lang = null
 agency_phone = null
 agency_fare_url = null
 trainScheduleFileName =  "trainschedule.csv"
 /********/


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

routes = []
routes << routeTrenUrbano


 resourcesFolder = "../resources"
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
createStopsTxt(stops)
createRoutesTxt(routes)
def trips = createTripsTxt()
//readTrainScheduleFile()
def stopTimes = readStopTimesFromDtopWebsite(trips)
printStopTimes(stopTimes)
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

Stop getStopFromNumber(int i){
    stops.find{stop -> if(stop.stopId == "${i}") return stop}
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

    def trips = []

    int count = 0;
    for(int startId=1; startId<= stops.size(); startId++){
        Stop theStart = getStopFromNumber(startId)
        if(startId < stops.size()){
            for (int stopId=startId+1; stopId<=stops.size(); stopId++){
                Stop theStop = getStopFromNumber(stopId)
                Trip trip = new Trip();
                trip.startStop = theStart
                trip.endStop = theStop
                trip.routeId = ((Route) routes[0]).routeId
                trip.serviceId = "LOW_SEASON_WORKDAY"
                trip.tripId = "${++count}"
                trip.tripHeadsign = theStop.stopName
                trip.tripShortName = "${theStart.stopName} / ${theStop.stopName}"
                trip.directionId = "0"
                trip.blockId = ""
                trip.shapeId = ""
                trip.wheelchairAccessible = ""
                trips << trip
            }
        }
        if(startId > 1){
            for (int stopId = startId -1; stopId >=1; stopId--){
                Stop theStop = getStopFromNumber(stopId)
                Trip trip = new Trip();
                trip.startStop = theStart
                trip.endStop = theStop
                trip.routeId = ((Route) routes[0]).routeId
                trip.serviceId = "LOW_SEASON_WORKDAY"
                trip.tripId = "${++count}"
                trip.tripHeadsign = theStop.stopName
                trip.tripShortName = "${theStart.stopName} / ${theStop.stopName}"
                trip.directionId = "1"
                trip.blockId = ""
                trip.shapeId = ""
                trip.wheelchairAccessible = ""
                trips << trip
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

def readTrainScheduleFile() {

    def trainScheduleFile = new File ("${resourcesFolder}/${trainScheduleFileName}")
    def trainArrivals = []

    trainScheduleFile.eachLine(1) { line ->
        line = line.replace("\"","")
        def (arrivesTo, direction, daytipe, arrivalTime) = line.split(",")
        TrainArrival trainArrival = new TrainArrival(
            arrivesTo: arrivesTo,
            direction: direction,
            daytipe: daytipe,
            arrivalTime: arrivalTime
        )
        trainArrivals << trainArrival
    }

    /*for (TrainArrival t: trainArrivals){
        println t.dump();
    }*/

}

/**
 * Reads stop times from http://www.dtop.gov.pr/itinerario.asp
 */
def readStopTimesFromDtopWebsite(def trips){
    def trainScheduleUrlFrom = {direction, departStationId -> "http://tempo7.praxisinteractive.net/dtopweb/itinerario/departure.php?st_id=${direction}&pl_id=${departStationId}"}
    def trainScheduleUrlDeparting = {direction, departStationId, trainId -> "http://tempo7.praxisinteractive.net/dtopweb/itinerario/plataforma2.php?st_id=${direction}&pl_id=${departStationId}&train_id=${trainId}"}
    def trainScheduleUrlArrivalTime = {direction, departStationId, trainId, arriveStationId -> "http://tempo7.praxisinteractive.net/dtopweb/itinerario/arrival.php?st_id=${direction}&pl_id=${departStationId}&train_id=${trainId}&pl_id2=${arriveStationId}" }

    WebConnectService webConnectService = new WebConnectService()
    def stopTimes = []
    def directions = ["1"]
    for (String direction: directions) {
        for(Stop startStop:stops){
            println startStop.stopName+" : "
            def pageWithDepartingTimes = webConnectService.doGetConnection(new URL(trainScheduleUrlFrom.call(direction, startStop.stopId)), "")
            def page = TrenXmlParserFactory.getXmlParser().parseText(pageWithDepartingTimes.webpage);

            def departTimes = page.depthFirst().getAt('OPTION')

            int count = 0;

            def departTimesList = []
            departTimes.each{time ->
                if(count == 0){
                    count++
                    return null
                }
                departTimesList << [trainId: time.depthFirst()[0]."@value", departureTime: time.value()[0] ]
                println "departTime:"+time.depthFirst()[0]."@value" + "|" + time.value()[0]
            }

            departTimesList.each { departTime ->
                println stops.size()
                println startStop.stopId
                Boolean doNotContinue = false;

                    //println trainScheduleUrlDeparting.call(direction, stop.stopId, departTime['trainId'])
                def pageWithArrivalStationText = webConnectService.doGetConnection(new URL(trainScheduleUrlDeparting.call(direction, startStop.stopId, departTime['trainId'])), "")

                println pageWithArrivalStationText
                def pageWithArrivalStationXml = TrenXmlParserFactory.getXmlParser().parseText(pageWithArrivalStationText.webpage);
                def arrivalStations = pageWithArrivalStationXml.depthFirst().getAt('OPTION')

                def arrivalStationsList = []
                int countArrivalStations = 0
                arrivalStations.each{ arrivalStation ->
                    if(countArrivalStations == 0){
                        countArrivalStations++
                        return null
                    }
                    //def station = arrivalStation.depthFirst()[0].value()[0]
                    //if(station != "[ Seleccione ]"){
                        def arrivalStationMap = [station: arrivalStation.depthFirst()[0].value()[0], value: arrivalStation.depthFirst()[0].@"value"]
                        arrivalStationsList << arrivalStationMap
                        println arrivalStationMap
                        println arrivalStationMap.station +" | "+arrivalStationMap.value
                        //}
                }

                arrivalStationsList.each { arrivalStation ->
                   println trainScheduleUrlArrivalTime.call(direction, startStop.stopId, departTime['trainId'], arrivalStation.value)
                   def pageWithArrivalTimeText = webConnectService.doGetConnection(new URL(
                           trainScheduleUrlArrivalTime.call(direction, startStop.stopId, departTime['trainId'], arrivalStation.value)
                        ), "")
                    //def pageWithArrivalTimeXml = TrenXmlParserFactory.getXmlParser().parseText(pageWithArrivalTimeText.webpage);
                    //def arrivalTime = pageWithArrivalTimeXml.depthFirst()
                    println "*****Arrival Time:"+ pageWithArrivalTimeText.webpage+"*****\n"
                    def at = pageWithArrivalTimeText.webpage

                    def calculateStopSequence = {Stop beginStop, Stop finishStop ->
                        int beginStopInt = new Integer(beginStop.stopId)
                        int endStopInt = new Integer(finishStop.stopId)
                        if(beginStopInt > endStopInt){
                            beginStopInt - endStopInt
                        } else if (endStopInt > beginStopInt) {
                            endStopInt - beginStopInt
                        } else {
                            assert false: "Beginning and End Stop should not be the same.  Received: beginStop = ${beginStopInt}; endStop = ${endStopInt} "
                        }

                    }
                    def finishStop = getStopFromNumber(new Integer((String)arrivalStation.value))
                    def stopTime = new StopTime(
                             tripId: getTripFromStartStopAndEndStop(trips, startStop, finishStop),
                             arrivalTime: at,
                             departureTime: departTime,
                             stopId: startStop.stopId,
                             stopSequence: calculateStopSequence.call(startStop, finishStop)

                    )
                    stopTimes << stopTime

                }
                    //arrivalStation.each

            }

        }
    }

    return stopTimes

}

def printStopTimes(def stopTimes){
    def printStopTime = {theFile, StopTime stopTime ->
        theFile << stopTime.tripId+","
        theFile << stopTime.arrivalTime+","
        theFile << stopTime.departureTime+","
        theFile << stopTime.stopId+","
        theFile << stopTime.stopSequence << "\r\n"
    }

    def stopTimesTxt = new File ("${resourcesFolder}/${stopTimesFileName}")
    stopTimesTxt.newWriter()

    stopTimesTxt << ("trip_id,arrival_time,departure_time,stop_id,stop_sequence") << '\r\n'

    for(StopTime st: stopTimes){
        printStopTime.call(stopTimesTxt,st)
    }
}

Trip getTripFromStartStopAndEndStop(def trips, Stop startStop, Stop endStop){
    for(Trip t: trips){
        if(t.startStop.stopId == startStop.stopId && t.endStop.stopId == endStop.stopId){
            return t
        }
    }

    assert false: "No trip found!\nReceived:\nstartStop = [stopName=${startStop.stopName}, stopId=${startStop.stopId}]"+
                    "endStop = [stopName=${endStop.stopName}, stopId=${endStop.stopId}]"

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

class WebConnectService {

    final String connectionUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1 (compatible; loteria_pr_bot; +http://www.webninjapr.com/)";
    final String connectionAccept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    final String connectionAcceptLanguage = "en-US,en;q=0.8";
    final String connectionAcceptCharset = "ISO-8859-1,utf-8;q=0.7,*;q=0.3";
    final String connectionContentType = "application/x-www-form-urlencoded";

    def doPostConnection(URL url, String requestParameters, ArrayList<String> cookies){
        DataOutputStream wr;
        HttpURLConnection connectionPost;
        //System.setProperty("http.agent", "");
        def webpage=null
        def retCookies=null
        try {
            connectionPost = url.openConnection();
            connectionPost.setConnectTimeout(10000)
            connectionPost.setRequestMethod("POST");
            connectionPost.setDoOutput(true);
            connectionPost.setDoInput(true);
            connectionPost.setRequestProperty("User-Agent", connectionUserAgent);
            connectionPost.setRequestProperty("Accept", connectionAccept);
            //connectionPost.setRequestProperty(Accept-Encoding:gzip,deflate,sdch)
            connectionPost.setRequestProperty("Accept-Language",connectionAcceptLanguage);
            connectionPost.setRequestProperty("Accept-Charset", connectionAcceptCharset);
            //connectionPost.setRequestProperty(java.net.URLEncoder.encode("Cache-Control", "ISO-8859-1"),java.net.URLEncoder.encode("max-age=0", "ISO-8859-1"));
            connectionPost.setRequestProperty("Content-Type", connectionContentType);
            if(cookies && cookies.size()>0){
                connectionPost.setRequestProperty("Cookie", cookies.join("; "));
            }
            connectionPost.setRequestProperty("Content-Length",requestParameters.getBytes().length.toString());

            connectionPost.connect();
            //Send request
            wr = new DataOutputStream (
                    connectionPost.getOutputStream ());
            wr.writeBytes (requestParameters);
            wr.flush ();

            //From http://www.hccp.org/java-net-cookie-how-to.html
            String headerName=null;
            ArrayList<String> returnCookies = [];
            for (int i=1; (headerName = connectionPost.getHeaderFieldKey(i))!=null; i++) {
                if (headerName.equals("Set-Cookie")) {
                    String cookie = connectionPost.getHeaderField(i);

                    def cookieCutter = cookie.split(";\\s?")

                    returnCookies.addAll(cookieCutter);
                    //String cookieName = cookie.substring(0, cookie.indexOf("="));
                    //String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                }
            }

            webpage = connectionPost.content.text
            retCookies = returnCookies
        } catch (e){
            log.error(e); //throw new Exception(e);
        } finally {
            if(wr){
                wr.close ();
            }
            if(connectionPost){
                connectionPost.disconnect();
            }
            return [webpage: webpage, cookies: retCookies];
        }
    }

    def doGetConnection(URL url, String requestParameters){
        DataOutputStream wr;
        HttpURLConnection connectionGet;
        //System.setProperty("http.agent", "");
        def webpage = null
        def cookies = null
        try {
            connectionGet = url.openConnection();
            connectionGet.setConnectTimeout(10000)
            connectionGet.setRequestMethod("GET");
            connectionGet.setDoOutput(false);
            connectionGet.setDoInput(true);
            connectionGet.setRequestProperty("User-Agent", connectionUserAgent);
            connectionGet.setRequestProperty("Accept", connectionAccept);
            //connectionGet.setRequestProperty(Accept-Encoding:gzip,deflate,sdch)
            connectionGet.setRequestProperty("Accept-Language",connectionAcceptLanguage);
            connectionGet.setRequestProperty("Accept-Charset", connectionAcceptCharset);
            //connectionGet.setRequestProperty(java.net.URLEncoder.encode("Cache-Control", "ISO-8859-1"),java.net.URLEncoder.encode("max-age=0", "ISO-8859-1"));
            connectionGet.setRequestProperty("Content-Type", connectionContentType);
            connectionGet.setRequestProperty("Content-Length",requestParameters.getBytes().length.toString());

            connectionGet.connect();

            //From http://www.hccp.org/java-net-cookie-how-to.html
            String headerName=null;
            ArrayList<String> returnCookies = [];
            for (int i=1; (headerName = connectionGet.getHeaderFieldKey(i))!=null; i++) {
                if (headerName.equals("Set-Cookie")) {
                    String cookie = connectionGet.getHeaderField(i);

                    def cookieCutter = cookie.split(";\\s?")

                    returnCookies.addAll(cookieCutter);
                    //String cookieName = cookie.substring(0, cookie.indexOf("="));
                    //String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                }
            }

            webpage = connectionGet.content.text
            cookies = returnCookies
        } catch (e){
            log.error(e) //throw new Exception(e);
        } finally {
            if(wr){
                wr.close ();
            }
            if(connectionGet){
                connectionGet.disconnect();
            }
            return [webpage: webpage, cookies: cookies];
        }

    }

}

class TrenXmlParserFactory {
    static def parser=null
    static def xmlParser=null

    static def getXmlParser(){
        if(!parser){
            parser = new org.cyberneko.html.parsers.SAXParser()
            parser.setFeature('http://xml.org/sax/features/namespaces', false)
        }
        if(!xmlParser){
            xmlParser = new XmlParser(parser)
        }
        return xmlParser;
    }
}