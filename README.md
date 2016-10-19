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
 if you set the statuebar is TRANSPARENT
  you can add this app:mStatueBarIsTransparent="true",it default is false;
 
 

