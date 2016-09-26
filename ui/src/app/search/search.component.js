import templateUrl from './search.component.html'

export default {
  templateUrl,
  controllerAs: '$search',
  controller:
    /* @ngInject */
    class SearchController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('SearchController instantiated');
        var ctrl=this;
        this.UserInfo = localStorage.getItem('UserInfo');
        var url ='http://localhost:1234/flights/fromto/';
        this.search=function(){ return $http.get(url+ctrl.from+'/'+ctrl.to, this.UserInfo).then(function successCallback(response) {
          console.log("response: " + response.data);
          ctrl.routes=response.data;

      }, function errorCallback(response) {
      console.log("User Not Found");
  }) };
      this.book=function(route){
        ctrl.UserInfo.bookedRoute=route;
        return $http.post('http://localhost:1234/user/book/', this.UserInfo).then(function successCallback(response) {
        console.log("response: " + response.data);
        console.log('route '+route)

    }, function errorCallback(response) {
    console.log("User Not Found");
})

      }
        console.log('search.component is running')
      }


    }
}
