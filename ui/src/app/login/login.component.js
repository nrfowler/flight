import templateUrl from './login.component.html'

export default {
  templateUrl,
  controllerAs: '$login',
  controller:
    /* @ngInject */
    class LoginController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('LoginController instantiated');
        var ctrl=this;
        this.UserInfo={name: "", pw: "", isLoggedIn: ""};

        var url ='http://localhost:1234/user/login';
        this.validate=function(){ return $http.post(url, this.UserInfo).then(function successCallback(response) {
          console.log("response: " + response.data);
          ctrl.UserInfo.id=response.data;
          localStorage.setItem('UserInfo', JSON.stringify(ctrl.UserInfo));
          $location.url('profile');
      }, function errorCallback(response) {
      console.log("User Not Found");
  }) };

        console.log('login.component is running')
      }


    }
}
