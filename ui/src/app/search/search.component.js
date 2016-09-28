import templateUrl from './search.component.html'

export default {
  templateUrl,
  controllerAs: '$search',
  controller:
    /* @ngInject */
    class SearchController {
      constructor ($log, $http, $routeParams, $location) {
        $log.debug('SearchController instantiated');
        var ctrl=this;
        this.from="Nashville";
        this.to="Chattanooga";
        console.log(document.getElementsByTagName('a'))
        ctrl.searchMessage="Please Enter a Search Query"
        this.UserInfo = JSON.parse(localStorage.getItem('UserInfo'));
        var url ='http://localhost:1234/flights/fromto/';
        this.stop=function(){
          clearInterval(ctrl.timer);
        }
        this.query=function(){$http.get(url+ctrl.from+'/'+ctrl.to, this.UserInfo).then(function successCallback(response) {
       console.log("query is run");
       ctrl.routes=response.data;
       if(ctrl.routes.length==0){
         ctrl.searchMessage="No routes match your query"
       }
       else{
         ctrl.searchMessage=""
       }
   }, function errorCallback(response) {
   console.log("Route Not Found");
})}
        this.search=function(){
            ctrl.query();

              ctrl.timer=setInterval(function(){ctrl.query()},3000)
              //$watch($location.absUrl(),function(){clearInterval(ctrl.timer)})
            }

      this.book=function(route){
        ctrl.UserInfo.bookedRoute=route;
        console.log(ctrl.UserInfo);
        return $http.post('http://localhost:1234/user/book/', this.UserInfo).then(function successCallback(response) {
        console.log("Trip: " + response.data.route[0].origin);
        ctrl.Trip=response.data;
        localStorage.setItem('Trip', JSON.stringify(response.data));
        $location.url('/booked')
    }, function errorCallback(response) {
    console.log("User Not Found");
})

      }
        console.log('search.component is running')
      }


    }
}
