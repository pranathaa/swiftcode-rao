var app = angular.module('chatApp', ['ngMaterial']);
app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('blue')
        .accentPalette('light-blue');
});
app.controller('chatController', function ($scope, $sce) {
    $scope.messages = [];
    $scope.trust = $sce.trustAsHtml;
    var exampleScoket = new WebSocket('wss://swiftcode-rao.herokuapp.com/chatSocket');
    exampleScoket.onmessage = function (event) {
        var jsonData = JSON.parse(event.data);
        jsonData.time = new Date()
            .toLocaleTimeString();
        $scope.messages.push(jsonData);

        $scope.$apply();
        console.log(jsonData);

    };
    $scope.sendMessage = function () {
        exampleScoket.send($scope.userMessage);
        $scope.userMessage = '';
    };
});