package tren2gtfs

class Utility {
    def stops = []
    Utility(){
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

    def getStops(){
        return stops
    }
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
}