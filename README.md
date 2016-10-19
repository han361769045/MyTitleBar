# MyTitleBar
a tool for android

# Dependency
```groovy
compile 'com.leo.lu:mytitlebar:1.0.0'
```
# Usage
```xml
  <com.leo.lu.mytitlebar.MyTitleBar
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="@color/colorPrimary"
        app:mCustomView="@layout/title_num_search"
        app:mCustomViewGravity="mRight"
        app:mCustomViewMarginLeft="@dimen/_55dp"
        app:mCustomViewMarginRight="@dimen/_15dp"
        app:mNavButtonIcon="@drawable/title_back"
 />
 ```
 your custmoview is 
 ```xml
 <EditText
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/text_search"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/app_bg"
    android:drawablePadding="15dp"
    android:drawableStart="@drawable/title_search"
    android:ellipsize="start"
    android:gravity="center_vertical"
    android:hint="@string/payment_please_input_number"
    android:imeOptions="actionSearch"
    android:inputType="phone"
    android:maxLines="1"
    android:padding="@dimen/_10dp"
    android:textColorHint="@color/payment_bottom"
    android:textSize="@dimen/_14sp"/>
 ```
 
 
 **Attention** 
 if you set the statuebar is TRANSPARENT
  you can add this `app:mStatueBarIsTransparent="true"`,it default is false;
 
 
License
-------

    Copyright 2016 Jude

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
