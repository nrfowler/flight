import templateUrl from './register.component.html'

export default {
  templateUrl,
  controllerAs: '$register',
  controller:
    /* @ngInject */
    class RegisterController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('RegisterController instantiated');
        var ctrl=this;
        this.UserInfo={username: "", pw: "", content: ""};

        var url ='http://localhost:1234/user/register';
        this.register=function(){ return $http.put(url, this.UserInfo).then(function successCallback(response) {
          console.log("response: " + response.data);
          ctrl.UserInfo=response.data;
          localStorage.setItem('UserInfo', JSON.stringify(ctrl.UserInfo));
          $location.url('profile');
      }, function errorCallback(response) {
      console.log("User Already Exists or Invalid User Credentials");
  }) };

        console.log('register.component is running')
      }


    }
}
