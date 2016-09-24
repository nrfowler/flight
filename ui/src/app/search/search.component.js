import templateUrl from './search.component.html'

export default {
  templateUrl,
  controllerAs: 'search',
  controller:
    /* @ngInject */
    class SearchController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('SearchController instantiated');
        var ctrl=this;
        this.UserInfo={username: "", pw: "", content: ""};

        var url ='http://localhost:1234/user/login';
        this.validate=function(){ return $http.put(url, this.UserInfo).then(function successCallback(response) {
          console.log("response: " + response.data);
          localStorage.setItem('UserInfo', JSON.stringify(ctrl.UserInfo));
          $location.url('profile');
      }, function errorCallback(response) {
      console.log("User Not Found");
  }) };

        console.log('search.component is running')
      }


    }
}
