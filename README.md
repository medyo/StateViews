# State Views for Android (BETA version)
Create & Show progress, data or error views, the easy way!

<img src="/art/StatusPage.jpg" width="80%" alt="Android States Page Cover"/>

This library allows to handle the different app states, from loading... to displaying
data or error views, the library is tiny and fully customizable

```xml
<sakout.mehdi.StateViews.StateView
        android:id="@+id/status_page"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="Hello World!" />

 </sakout.mehdi.StateViews.StateView>
```
Then Call the appropriate state:

```java
mStatusPage.displayLoadingState();
```

```java
mStatusPage.displayState("TAG_NAME");
```

```java
mStatusPage.hideStates();
```



## Setup
[![Snapshot](https://jitpack.io/v/medyo/stateviews.svg?style=flat-square)](https://jitpack.io/private#medyo/stateviews/-SNAPSHOT)
```
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
| setTextColor(Int) |  set Title and description colors|
| setFontFace(String) | Set Custom font |
| setButtonBackgroundColor(Int) | Set Button Background color|
| setButtonTextColor(String) | Set Button Text color|

### 2. Available attributes for PageStatesView


| Function        | Description  |
| ------------- |:-------------:|
| displayState(String) | Display a state by his tag name|
| hideStates() | Hide all states and display data|
| displayLoadingState() | Display the loading state|
| addCustomState(Intent) | Create a new state only available for the current activity, fragment...|
| setOnStateButtonClicked(View.OnClickListener) |  Click listener for the state button|
| applyGravity(Int) | Set View Gravity |

### 3. Samples
#### Display an Error View

![No Connection](/art/no_connection.jpg)

```java
addState(
    "TAG_ERROR",
    "No Connection",
    "Error retrieving information from server.",
    AppCompatResources.getDrawable(this, R.drawable.ic_server_error),
    "Retry"
    );

mStatusPage.displayState("TAG_ERROR");
```

#### Display a "no Data" View

![No Data](/art/no_data.jpg)

```java
addState(
    "TAG_NO_RESULTS",
    "No Results Found",
    "Unfortunately I could not find any results matching your search",
    AppCompatResources.getDrawable(this, R.drawable.search), null
)

mStatusPage.displayState("TAG_NO_RESULTS");
```

## License

~~~
The MIT License (MIT)
Copyright (c) 2018 Mehdi Sakout

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
~~~
