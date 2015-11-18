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
  DialogAndroid,
  DialAndroid,
  RingAndroid
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
  date : function() {
    var defaultDate = new Date();
    var minDate = new Date();
    minDate.setMonth(0);
    var maxDate = new Date();
    maxDate.setMonth(11);
    DialogAndroid.date({
      title : "选择日期",
      okText : "确认",
      cancelText : "取消",
      defaultDate : parseInt(defaultDate.getTime() / 1000),
      minDate : parseInt(minDate.getTime() / 1000),
      maxDate : parseInt(maxDate.getTime() / 1000)
    },function(year, month, d) {
      ToastAndroid.show(year + "-" + (month + 1) + "-" + d, ToastAndroid.SHORT);
    });
  },
  time : function() {
    DialogAndroid.time({
      okText : "确认",
      cancelText : "取消",
    }, function(hours, minutes) {
      ToastAndroid.show(hours + ":" + minutes, ToastAndroid.SHORT);
    });
  },
  dial : function() {
    DialAndroid.dial("1234567890");
  },
  call : function() {
    DialAndroid.call("18268186032");
  },
  beep : function() {
    RingAndroid.beep();
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
       <TouchableWithoutFeedback onPress={this.date} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          date
        </Text>
        </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.time} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          time
        </Text>
        </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.dial} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          dial
        </Text>
        </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.call} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          call
        </Text>
        </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.beep} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          beep
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
