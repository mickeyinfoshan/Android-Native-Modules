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
  TouchableWithoutFeedback,
  ToastAndroid,
  DeviceEventEmitter,
  Modal
} = React;

var Button = require('react-native-button');

var Carousel = require('react-native-carousel');

var Dimensions = require('Dimensions');
var {width, height} = Dimensions.get('window');

var { NativeModules } = require('react-native');

var {
  ShakeAndroid,
  ScanAndroid,
  VibrateAndroid,
  DialogAndroid
} = NativeModules;

var Hello = React.createClass({
  getInitialState: function() {
    return {
       text : "stop" 
    };
  },
  scan : function() {
      ScanAndroid.scan(null, "请扫描二维码");
  },
  componentDidMount: function() {
    ShakeAndroid.start();
    DeviceEventEmitter.addListener('shake', function(e: Event) {
        ToastAndroid.show("Shake!!!", ToastAndroid.SHORT);
    });
  },
  componentWillUnmount: function() {
    ShakeAndroid.stop();
  },
  toast : function() {
    ToastAndroid.show("Shake!!!", ToastAndroid.SHORT);
  },
  vibrate : function() {
    VibrateAndroid.vibrate(800);
  },
  alert : function() {
    DialogAndroid.alert({
      title : "hhh",
      content : "bbb",
      okText : "确认"
    }, this.vibrate.bind(this));
  },
  confirm : function() {
    DialogAndroid.confirm ({
      title : "hhh",
      content : "bbb",
      okText : "确定",
      cancelText : "取消"
    }, function() {
      ToastAndroid.show("您点了确定", ToastAndroid.SHORT);
    }, function() {
      ToastAndroid.show("您点了取消", ToastAndroid.SHORT);
    });
  },
  render: function() {
    return (
      <View style={styles.container}>
       <TouchableWithoutFeedback onPress={this.scan} >
       <View style={styles.item}>
       <Text> 
          扫描
       </Text>
       </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.vibrate} >
       <View style={styles.item}>
       <Text> 
          震动
       </Text>
       </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.alert} >
       <View style={styles.item}>
        <Text> 
          Alert
        </Text>
        </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.confirm} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          Confirm
        </Text>
        </View>
       </TouchableWithoutFeedback>
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
  item : {
    marginBottom : 15
  }
});

AppRegistry.registerComponent('Hello', () => Hello);
