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
    implementation 'com.github.thementalgoose:android-components:4.0.0'
}
```

Android Components [![](https://jitpack.io/v/thementalgoose/android-components.svg)](https://jitpack.io/#thementalgoose/android-components)


## Usage

Inside your app style supplied to this activity, provide the following attributes

```
<!-- About This App Activity (light) -->
<item name="aboutThisApp_header">?attr/colorPrimary</item>
<item name="aboutThisApp_textPrimary">#181818</item>
<item name="aboutThisApp_textSecondary">#383838</item>
<item name="aboutThisApp_textToolbar">#f8f8f8</item>
<item name="aboutThisApp_iconPrimary">?attr/aboutThisApp_textPrimary</item>
<item name="aboutThisApp_backgroundPrimary">#fbfbfb</item>
<item name="aboutThisApp_backgroundSecondary">#f2f2f2</item>

<!-- About This App Activity (dark) -->
<item name="aboutThisApp_header">@color/colorPrimary</item>
<item name="aboutThisApp_textPrimary">#f8f8f8</item>
<item name="aboutThisApp_textSecondary">#e8e8e8</item>
<item name="aboutThisApp_textToolbar">#f8f8f8</item>
<item name="aboutThisApp_iconPrimary">?attr/aboutThisApp_textPrimary</item>
<item name="aboutThisApp_backgroundPrimary">#181818</item>
<item name="aboutThisApp_backgroundSecondary">#383838</item>
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