var app = angular.module('chatApp', ['ngMaterial']);
app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('blue')
        .accentPalette('light-blue');
});
app.controller('chatController', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'hello'
    },
        {
            'sender': 'BOT',
            'text': 'Hi what can i do for u'
    },
        {
            'sender': 'USER',
            'text': 'Help me in my peoject'
    },
        {
            'sender': 'BOT',
            'text': 'What the topic'
    },
        {
            'sender': 'USER',
            'text': 'Its about IoT'
    }
    ];
});