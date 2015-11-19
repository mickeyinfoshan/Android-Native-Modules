
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

var Dimensions = require('Dimensions');
var {width, height} = Dimensions.get('window');

var { NativeModules } = require('react-native');

var {
  ShakeAndroid,
  ScanAndroid,
  VibrateAndroid,
  DialogAndroid,
  DialAndroid,
  RingAndroid,
  ContactAndroid
} = NativeModules;

var Hello = React.createClass({
  getInitialState: function() {
    return {
       text : "stop" 
    };
  },
  scan : function() {
      ScanAndroid.scan("You can scan something now",function(result) {
        ToastAndroid.show(result, ToastAndroid.SHORT);
      });
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
      title : "Title",
      content : "Content",
      okText : "Confirm"
    }, this.vibrate.bind(this));
  },
  confirm : function() {
    DialogAndroid.confirm ({
      title : "title",
      content : "content",
      okText : "OK",
      cancelText : "Cancel"
    }, function() {
      ToastAndroid.show("You clicked OK", ToastAndroid.SHORT);
    }, function() {
      ToastAndroid.show("You clicked Cancel", ToastAndroid.SHORT);
    });
  },
  date : function() {
    var defaultDate = new Date();
    var minDate = new Date();
    minDate.setMonth(0);
    var maxDate = new Date();
    maxDate.setMonth(11);
    DialogAndroid.date({
      title : "Pick your date",
      okText : "Done",
      cancelText : "Cancel",
      defaultDate : parseInt(defaultDate.getTime() / 1000),
      minDate : parseInt(minDate.getTime() / 1000),
      maxDate : parseInt(maxDate.getTime() / 1000)
    },function(year, month, d) {
      ToastAndroid.show(year + "-" + (month + 1) + "-" + d, ToastAndroid.SHORT);
    });
  },
  time : function() {
    DialogAndroid.time({
      okText : "Done",
      cancelText : "Cancel",
      hour : 15,
      minute : 20,
      is24HourView : true
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
  contacts : function() {
    ContactAndroid.selectContact(function(name, phone) {
      ToastAndroid.show(name + "-" + phone, ToastAndroid.SHORT);
    });
  },
  render: function() {
    return (
      <View style={styles.container}>
       <TouchableWithoutFeedback onPress={this.scan} >
       <View style={styles.item}>
       <Text> 
          Scan
       </Text>
       </View>
       </TouchableWithoutFeedback>
       <TouchableWithoutFeedback onPress={this.vibrate} >
       <View style={styles.item}>
       <Text> 
          Vibrate
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
        <TouchableWithoutFeedback onPress={this.contacts} style={styles.item}>
       <View style={styles.item}>
        <Text> 
          contacts
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
