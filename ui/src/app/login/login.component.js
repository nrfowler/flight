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
        this.UserInfo={username: "", pw: "", content: ""};

        var url ='http://localhost:1234/user/create';
        this.validate=function(){ return $http.put(url, this.UserInfo).then(function successCallback(response) {
          console.log("response: " + response.data);
          ctrl.UserInfo.id=response.data;
          var temp=JSON.stringify(ctrl.UserInfo);
          localStorage.setItem('UserInfo', temp);
          $location.url('home');
      }, function errorCallback(response) {
      console.log("error");
  }) };

        console.log('login.component is running')
      }


    }
}
