# State Views for Android (BETA version)
Create & Show progress, data or error views, the easy way!

<img src="/art/StatusPage.jpg" width="80%" alt="Android States Page Cover"/>

This library allows to handle the different app states, from loading... to displaying
data or error views, the library is tiny and fully customizable

```java
// Create your necessary states
PageStatusBuilder
                .init(this)
                .addState(
                    "TAG",
                    "Title",
                    "Description",
                    "Icon",
                    "Button"
                 );

// Show the state by TAG id
mStatusPage.displayState("TAG");
```

## Setup
[![Snapshot](https://jitpack.io/v/medyo/stateviews.svg?style=flat-square)](https://jitpack.io/private#medyo/stateviews/-SNAPSHOT)
```
maven {
    url 'https://dl.bintray.com/medyo/maven/com/github/medyo/'
}

compile 'com.github.medyo:state-views:0.1'

```

## Usage
[medyo/StateViews/app/](https://github.com/medyo/StateViews/tree/master/app)

### 1. Available attributes for PageStatus Builder

| Function        | Description  |
| ------------- |:-------------:|
| addState(params) | Create a new state|
| setIconColor(Int) | Set Icon color |
| setIconSize(Intent) | Set Icon Size |
| setTextColor(View.OnClickListener) |  set Title and description colors|
| setFontFace(Gravity) | Set Custom font |
| setButtonBackgroundColor(Int) | Set Button Background color|
| setButtonTextColor(String) | Set Button Text color|

### 2. Available attributes for PageStatesView


| Function        | Description  |
| ------------- |:-------------:|
| displayState(String) | Display a state by its tag name|
| displayLoadingState() | Display the loading state|
| addCustomState(Intent) | Create a new state only available for the current activity, fragment...|
| setOnStateButtonClicked(View.OnClickListener) |  Click listener for the state button|
| applyGravity(Int) | Set Gravity for the View  |

### 3. Samples
#### Display an Error View

![No Connection](/art/no_connection.jpg)

```java
StateViewsBuilder
                .init(this)
                .setIconColor(Color.parseColor("#D2D5DA"))
                .addState("error",
                    "No Connection",
                    "Error retrieving information from server.",
                    AppCompatResources.getDrawable(this, R.drawable.ic_server_error),
                    "Retry");
```


## License

~~~
The MIT License (MIT)
Copyright (c) 2018 Mehdi Sakout

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
~~~
