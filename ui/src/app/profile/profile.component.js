import templateUrl from './profile.component.html'

export default {
  templateUrl,
  controllerAs: '$profile',
  controller:
    /* @ngInject */
    class ProfileController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('ProfileController instantiated');
        var ctrl=this;
        this.UserInfo=JSON.parse(localStorage.getItem('UserInfo'));
        console.log(this.UserInfo.name)
        var url ='http://localhost:1234/user/logout';
        this.logout=function(){
          return $http.post(url, this.UserInfo).then(function successCallback(response) {
        }, function errorCallback(response) {
        console.log("User Not Found");
    })
        }


        console.log('profile.component is running')
      }


    }
}
