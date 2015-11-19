# Android-Native-Modules
A react-native app uses some native modules. (Android)

# Native Modules

## DialogAndroid

A module shows dialogs.

### Methods

    static alert(options: Object, callback: Function)

**Options**

| Option   | Type    | Default |
|----------|---------|---------|
| title    | String  | ""      | 
| content  | String  | ""      |
| okText   | String  | "OK"    | 


    static confirm(options: Object, OKCallback: Function, cancelCallback: Function)

**Options**

| Option     | Type    | Default |
|------------|---------|---------|
| title      | String  | ""      |
| content    | String  | ""      |
| okText     | String  | "OK"    |
| cancelText | String  | "Cancel"|

    static date(options: Object, callback: Function(year: Number, month: Number, dayInMonth: Number))

**Options**

| Option     | Type    | Default                                 |
|------------|---------|-----------------------------------------|
| title      | String  | (Depends on the system)                 |
| okText     | String  | (Depends on the system)                 |
| cancelText | String  | (No cancel button)                      |
| maxDate    | Integer | null                                    |
| minDate    | Integer | null                                    |
| defaultDate| Integer | parseInt((new Date()).getTime() / 1000) |

#### The maxDate/minDate/defaultDate should be set like this: parseInt(new Date().getTime() / 1000). 

The callback function takes 3 arguments, which are **year**(full year), **month**(0-11) and **dayInMonth**. 

    static time(options: Object), callback: Function(hours: Number, minutes: Number))

**Options**

| Option       | Type    | Default                               |
|--------------|---------|---------------------------------------|
| title        | String  | (Depends on the system)               |
| okText       | String  | (Depends on the system)               |
| cancelText   | String  | (No cancel button)                    |
| hour         | Integer | (new Date()).getHours()               |
| minDate      | Integer | (new Date()).getMinutes()             |
| is24HourView | Boolean | false                                 |

The callback function takes 2 arguments, which are **hours** and **minutes**. 

## ScanAndroid

A module scans codes(e.g. qrcode). Thanks to @[rkistner](https://github.com/rkistner)'s [zxing-android-embedded](https://github.com/journeyapps/zxing-android-embedded)!

More options will be added in the future.

### Method 

	static scan(prompt: String, callback: Function(result: String))

The callback function takes an argument, which is the result.

### ContactAndroid

A module enables your app to select a contact.

More methods will be added in the future.

### Method

	static selectContact(callback: Function(name: String, phone: String))

The callback function takes two arguments, which are the name and the phone number of the selected contact.


## VibrateAndroid

A module makes your device vibrate.

### Method

    static vibrate(millionseconds: number)

## RingAndroid

A module makes your device beep.

### Method
    static beep()

## DialAndroid

A module enables your app to dial a phone number.

### Methods

    static dial(phone: string)  //just dial the phone number
    static call(phone: String)  //call the phone number directly

## ShakeAndroid

A module detects whether the devices is shaked. Thanks to Facebooks's [ShakeDetector](https://github.com/facebook/react-native/blob/master/ReactAndroid/src/main/java/com/facebook/react/common/ShakeDetector.java)
The module will emit a event called "shake" by DeviceEventEmitter. You can set your callback by listening the event.For example:

    DeviceEventEmitter.addListener('shake', function(e: Event) {
        //do your staff
    });

### Methods

    static start()  //start to detect
    static stop() //stop detecting

---

## Questions?

Feel free to create an issue. PRs are welcomed!
