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
        this.UserInfo={name: "", pw: "", isLoggedIn: ""};

        var url ='http://localhost:1234/user/register';
        this.register=function(){ return $http.post(url, this.UserInfo).then(function successCallback(response) {
          console.log("response: " + response.data);
          ctrl.UserInfo.id=response.data;
          localStorage.setItem('UserInfo', JSON.stringify(ctrl.UserInfo));
          $location.url('profile');
      }, function errorCallback(response) {
      console.log("User Already Exists or Invalid User Credentials");
  }) };

        console.log('register.component is running')
      }


    }
}
