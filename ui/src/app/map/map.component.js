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
  colors = ['#AA1100','#CC0099', '#00ff00', '#087fa1','#AA1100','#CC0099', '#00ff00', '#087fa1']

  constructor ($map, locations) {
    this.$map = $map
    var ctrl=this
    this.Marker={}
    ctrl.$map.getMarkerByCityName("Memphis").then(a=>ctrl.Marker["Memphis"]=a)
    ctrl.$map.getMarkerByCityName("Chattanooga").then(a=>ctrl.Marker["Chattanooga"]=a)
    ctrl.$map.getMarkerByCityName("Knoxville").then(a=>ctrl.Marker["Knoxville"]=a)
    ctrl.$map.getMarkerByCityName("Nashville").then(a=>ctrl.Marker["Nashville"]=a)
    console.log('map controller is running')
    this.Trip = JSON.parse(localStorage.getItem('Trip'))
    // add markers from an angular constant
    this.layoversExist=false
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
      $map.getRouteByNameAndPw(this.UserInfo).then(route => ctrl.addRoute(route))

  }

  addMarker ({ latitude, longitude }) {
    this.markers.push({
      position: `[${latitude}, ${longitude}]`
    })
  }

  addPath (a, b) {
    this.paths.push({
      path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
      strokeColor: this.colors[this.paths.length],
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
          ctrl.layoversExist=true
        }
        console.log('flight origin: '+flight.origin)

        ctrl.addPath(ctrl.Marker[flight.origin],ctrl.Marker[flight.destination])
      //ctrl.$map.getMarkerByCityName(flight.origin).then(origin => { console.log('origin '+flight.origin)
      //  ctrl.$map.getMarkerByCityName(flight.destination).then( destination => ctrl.addPath(origin, destination)
      //)})

    })
      console.log(this.paths)


  }

}

export default {
  templateUrl,
  controller: MapController,
  controllerAs: '$mapCtrl'
}
