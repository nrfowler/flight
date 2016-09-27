import templateUrl from './navbar.component.html'

export default {
  templateUrl,
  controllerAs: '$navbar',
  controller:
    /* @ngInject */
    class NavbarController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('NavbarController instantiated');
        var ctrl=this;
        this.UserInfo=JSON.parse(localStorage.getItem('UserInfo'));
        console.log(this.UserInfo.name)
        $http.get('http://localhost:1234/user/'+ctrl.UserInfo.id).then(response=>ctrl.UserInfo=response.data)
        var url ='http://localhost:1234/user/logout';
        this.logout=function(){
          return $http.post(url, this.UserInfo).then(function successCallback(response) {
        }, function errorCallback(response) {
        console.log("User Not Found");
    })
        }


        console.log('navbar.component is running')
      }


    }
}
