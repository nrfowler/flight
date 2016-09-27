import bookedLocations from '../booked.locations'
import bookedComponent from './booked.component.js'
import bookedService from '../booked.service'

export default
  angular
    .module('flight.map', ['ngMap'])
    .component('flightMap', mapComponent)
    .service('$map', mapService)
    .name
