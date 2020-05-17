# Android Components

Contains a collection of generic UI components that I reuse in all of my apps

## Install

#### `build.gradle`

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### `app/build.gradle`

```
dependencies {
    implementation 'com.github.thementalgoose:android-components:2.0.1'
    implementation 'com.github.thementalgoose:android-utilities:1.1.1'
}
```

Android Utilities [![](https://jitpack.io/v/thementalgoose/android-utilities.svg)](https://jitpack.io/#thementalgoose/android-utilities)
Android Components [![](https://jitpack.io/v/thementalgoose/android-components.svg)](https://jitpack.io/#thementalgoose/android-components)


## Usage

```
<!-- Override colours in the setting by declaring these in your app -->
<color name="aboutThisApp_headerDark">#3C306E</color>
<color name="aboutThisApp_headerLight">#5D25E6</color>

<color name="aboutThisApp_textDesc">#f2f2f2</color>
<color name="aboutThisApp_textName">#fbfbfb</color>

<color name="aboutThisApp_backgroundLight">#f2f2f2</color>
<color name="aboutThisApp_textLight">#f8f8f8</color>
<color name="aboutThisApp_textLightSecondary">#e8e8e8</color>

<color name="aboutThisApp_backgroundDark">#282828</color>
<color name="aboutThisApp_textDark">#222222</color>
<color name="aboutThisApp_textDarkSecondary">#383838</color>
```

## License

```
Copyright (C) 2020 Jordan Fisher

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```