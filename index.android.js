/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableNativeFeedback,
  ToastAndroid,
  DeviceEventEmitter
} = React;

var Button = require('react-native-button');

var Carousel = require('react-native-carousel');

var Dimensions = require('Dimensions');
var {width, height} = Dimensions.get('window');

var { NativeModules } = require('react-native');
var ShakeAndroid = NativeModules.ShakeAndroid;

var Hello = React.createClass({
  getInitialState: function() {
    return {
       text : "stop" 
    };
  },
  clicked : function() {
      ShakeAndroid.stop();
  },
  componentDidMount: function() {
    ShakeAndroid.start();
    DeviceEventEmitter.addListener('shake', function(e: Event) {
        ToastAndroid.show("Shake!!!", ToastAndroid.SHORT);
    });
  },
  toast : function() {
    ToastAndroid.show("Shake!!!", ToastAndroid.SHORT);
  },
  render: function() {
    return (
      <View style={{flex : 1}}>
       <Carousel style={{width : width}} hideIndicators={true}>
        <View style={styles.container}>
          <Text>Page 1</Text>
        </View>
        <View style={styles.container}>
          <Text>Page 2</Text>
        </View>
        <View style={styles.container}>
          <Text>Page 3</Text>
        </View>
      </Carousel>
        <View style={styles.openBtn}>
          <Text>{this.state.text}</Text>
        </View>
        <View style={styles.menuBtn} onPress={this.clicked}>
            <Text style={{alignSelf : "center"}}>=</Text>
        </View>
        <View style={{position : "absolute", right : 10, bottom : 10, backgroundColor : "#ccc", width : 30, height : 30, borderRadius:15,justifyContent: 'center',
          alignItems: 'center',}}>
            <Text>
                ^
            </Text>
        </View>
      </View>
    );
  }
});

var styles = StyleSheet.create({
  container: {
    width : width,
    flex: 1,
    height : 100,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#ccc',
    borderWidth : 1,
    borderColor : "#000"
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  openBtn : {
    justifyContent: 'center',
    alignItems: 'center',
    width : width * 0.5,
    height : width * 0.5,
    backgroundColor : "#e60044",
    borderRadius : width * 0.25,
    position : "absolute",
    left : width * 0.25,
    top : width * 0.7
  },
  menuBtn : {
      backgroundColor: '#ccc',
      height: 30,
      left : 10,
      width : 30,
      bottom : 10,
      justifyContent: 'center',
      alignItems: 'center',
      borderRadius : 15
  }
});

AppRegistry.registerComponent('Hello', () => Hello);
