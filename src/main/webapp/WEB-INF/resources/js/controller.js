/**
 * Created by Anisha on 08/05/2016.
 */

var cartApp = angular.module("cartApp", []);

// function to define the cart, gets cart data and stores in scope variable

cartApp.controller("cartCtrl", function ($scope, $http) {
    $scope.refreshCart = function (cartId) {
        $http.get('/COM3018/rest/cart/' + $scope.cartId).success(function (data) {
            $scope.cart = data;
        });
    };

    // delete function

    $scope.clearCart = function () {
        $http.delete('/COM318/rest/cart/' + $scope.cartId).success($scope.refreshCart($scope.cartId));
    };

    // use refresh cart function to initialise

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function (productId) {
        $http.put('/COM3018/rest/cart/add/' + productId).success(function (data) {
            $scope.refreshCart($http.get('/COM3018/rest/cart/cartId'));
            alert("product successfully added to the cart!")
        });
    };

    $scope.removeFromCart = function (productId) {
        $http.put('/COM3018/rest/cart/remove/' + productId).success(function (data) {
            $scope.refreshCart($http.get('/COM3018/rest/cart/cartId'));

        });

    };
});