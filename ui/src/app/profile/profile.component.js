import templateUrl from './profile.component.html'

export default {
  templateUrl,
  controllerAs: 'profile',
  controller:
    /* @ngInject */
    class ProfileController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('ProfileController instantiated');
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

        console.log('login.component is running')
      }


    }
}
