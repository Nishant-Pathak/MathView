[![Build Status](https://travis-ci.org/Nishant-Pathak/MathView.svg?branch=master)](https://travis-ci.org/Nishant-Pathak/MathView)
# MathView

- [x] Support all types of math encoding ( MathML, TeX or AsciiMath etc).
- [x] Offline capability.
- [x] Lightweight (~150kb).
- [x] Fast rendering.
- [x] Easy to use.
- [x] Sample code [MainActivity.java](/app/src/main/java/com/nishant/mathsample/MainActivity.java)

Demo
----
![Demo Url](/demo.gif)

Configured delimiters
---------------------
* TeX, inline mode: \(...\) or $...$
* TeX, display mode: \[...\] or $$...$$
* Asciimath: \`...\`

Installation
------------
Add below lines to app's build.gradle

```groovy
repositories {
  maven {
    url  "http://nishantpathak.bintray.com/public"
  }
}
```
```groovy
dependencies {
    compile 'com.nishant:mathview:0.6'
}
```

Usage
-----
In your layout:
```xml
    <com.nishant.math.MathView
        android:id="@+id/math_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
```
In Activity:
```java
  @BindView(R.id.math_view)
  MathView mathView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ...
    // update the view as below
    mathView.setText("`2+3 = 5`")
  }
```

License
=======
Copyright (C) 2016 - 2017 Nishant Pathak

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
