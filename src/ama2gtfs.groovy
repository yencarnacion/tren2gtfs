/**
 * Created by yamir on 6/6/13.
 */

 agency_id = 'TREN'
 agency_name = 'Tren Urbano'
 agency_url = 'www.dtop.gov.pr'
 agency_timezone = 'America/Puerto_Rico'
 agency_lang = null
 agency_phone = null
 agency_fare_url = null
 /********/
 stops = []
 sagrado_corazon = new Stop(
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

hato_rey = new Stop(
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

roosevelt = new Stop(
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

domenech = new Stop(
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

pinero = new Stop(
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

universidad = new Stop(
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

rio_piedras = new Stop (
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
cupey = new Stop (
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

centro_medico = new Stop (
        stopId: "8",
        stopCode: "",
        stopName: "Centro M\u009dico",
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

san_francisco = new Stop (
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

las_lomas = new Stop (
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

martinez_nadal = new Stop (
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

torrimar = new Stop (
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


jardines = new Stop (
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

deportivo = new Stop (
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

bayamon = new Stop (
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


 file =  'trainschedule.csv'

 agencyFileName = "agency.txt"
 stopsFileName = "stops.txt"


agency = new Agency(agencyId: agency_id,
                    agencyName: agency_name,
                    agencyUrl: agency_url,
                    agencyTimezone: agency_timezone,
                    agencyLang: agency_lang,
                    agencyPhone: agency_phone,
                    agencyFareUrl: agency_fare_url)
createAgencyTxt(agency)
createStopsTxt(stops)

def createAgencyTxt(def agency){
    def agencyTxt = new File(agencyFileName)
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
    def stopsTxt = new File(stopsFileName)
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

class Agency {
    String agencyId
    String agencyName
    String agencyUrl
    String agencyTimezone
    String agencyLang
    String agencyPhone
    String agencyFareUrl
}

class Stop {
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
}