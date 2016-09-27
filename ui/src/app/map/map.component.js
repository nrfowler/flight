import templateUrl from './map.component.html'

/* @ngInject */
class MapController {
  zoom = 7
  center = [35.5175, -86.5804]
  markers = []
  paths = []
  route = []
  totalTime = 0
  layoverTime = 0

  constructor ($map, locations) {
    this.$map = $map
    console.log('map controller is running')
    // add markers from an angular constant
    const { memphis, nashville, knoxville } = locations
    //const markers = [memphis, nashville, knoxville]

    //markers.forEach(marker => this.addMarker(marker))

    // add paths manually
    const paths = [
      [memphis, nashville, '#CC0099'],
      [nashville, knoxville, '#AA1100']
    ]

    //paths.forEach(args => this.addPath(...args))

    // add path from webservice
    //$map.getMarkerByCityName('Chattanooga')
    //  .then(chattanooga => {
    //    this.addPath(knoxville, chattanooga, '#FF3388')
    //  })
      this.UserInfo = JSON.parse(localStorage.getItem('UserInfo'))
      console.log(this.UserInfo)
      var ctrl=this
      $map.getRouteByNameAndPw(this.UserInfo).then(route => ctrl.addRoute(route))

  }

  addMarker ({ latitude, longitude }) {
    this.markers.push({
      position: `[${latitude}, ${longitude}]`
    })
  }

  addPath (a, b, color) {
    this.paths.push({
      path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
      strokeColor: color,
      strokeOpacity: 1.0,
      strokeWeight: 3,
      geodesic: true
    })
  }
  //color will be easy, use index of foreach and enum
  addRoute(route){
    var ctrl= this;
    ctrl.route=route;
console.log(route)
    if(route.length==1){
    }

      route.forEach(function(flight, index, array){
        ctrl.totalTime+=flight.flightTime
        if(index!=0){
          ctrl.layoverTime+=flight.offset -(route[index-1].offset+route[index-1].flightTime)
        }
        console.log('flight'+flight.origin)
      ctrl.$map.getMarkerByCityName(flight.origin).then(origin => { console.log('dest '+flight.destination)
        ctrl.$map.getMarkerByCityName(flight.destination).then( destination => ctrl.addPath(origin, destination, '#FF3388')
      )})})
      console.log(route)


  }

}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}
