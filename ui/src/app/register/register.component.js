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

        console.log('register.component is running')
      }


    }
}
