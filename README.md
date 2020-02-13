# Donut Chart

DonutChart is a customised pie chart library.

![preview](https://github.com/ruthwikkk/DonutChart/blob/master/screenshot_1.png)

## Add to project ##
1. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
``` 
2. Add the dependency:

```groovy
implementation 'com.github.ruthwikkk:donutchart:{latest_version}'
```
  Find the latest version [here](https://github.com/ruthwikkk/DonutChart/releases)
   
    

 ## Usage
 1. Add it to your layout:
 ```xml
  <com.ruthwikkk.widget.donutchart.DonutChart
        android:id="@+id/donut_chart"
        android:layout_width="300dp"
        android:layout_height="300dp"

        app:animationDuration="3000"
        app:correctPercentage="70"
        app:donutSmallWidth="10dp"
        app:donutWidth="75dp"
        app:separatorAngle="1"
        app:textPadding="40dp"
        app:unAnsweredPercentage="10"
        app:wrongPercentage="20">
    </com.ruthwikkk.widget.donutchart.DonutChart>
```

