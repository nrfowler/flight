import templateUrl from './home.component.html'

export default {
  templateUrl,
  controllerAs: '$home',
  controller:
    /* @ngInject */
    class HomeController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('HomeController instantiated');
        var ctrl=this;
        this.UserInfo={name: "", pw: ""};
        localStorage.clearAll();
        var url ='http://localhost:1234/user/create';
        ctrl.goregister=function(){
              $location.url('register')
          }
          ctrl.gologin=function(){
                $location.url('login')
            }


        console.log('home.component is running')
      }


    }
}
