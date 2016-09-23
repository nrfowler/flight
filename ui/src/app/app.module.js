import flightMap from './map/map.module'
import apiUrl from './api.url'
import appComponent from './app.component.js'

export default
  angular
    .module('flight', ['ngRoute', 'LocalStorageModule',
      'ngAria',
      'ngAnimate',
      'ngMaterial',
      'ngMessages',
      flightMap
    ])
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    .name
