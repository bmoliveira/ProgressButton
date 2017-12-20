#Intro

This repo contains 3 libs the Progress Button, MaxWidthLayouts and MaxWidthLayoutsAnko extensions.

--

#Progress button

A material button with rounded corners and a progress indicator on the right.

Important note, this lib was created in kotlin and uses anko-layouts internally and it has anko helpers to build the view.

Current version
-
	0.0.3

Gradle Import
-
	implementation 'com.broliveira:progress-button:<version>'

## Custom XML attributes

All the attributes are programmatically customizable.

### pbTitle
	Title of the button

### pbTitleColor
	Button progress-bar and title color
	Default value is extracted from theme textColor

### pbTitleTypeFaceName
	Button typeface name

### pbTitleTextSize
	Button text size

### pbMaxWidth
	Button max width
	Default is Integer.max

### pbMinHeight
	Button min height

### pbButtonElevation
	Button elevation

### pbButtonRadius
	Button corner radius

### pbRippleBackgroundColor
	Button main background color

### pbRippleSecondaryColor
	Button secondary

### pbIsLoading
	Flag that indicates if the loader is visible or not

XML Example
-

```
<com.broliveira.view.ProgressButton
      android:id="@+id/mainButton"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:pbTitleTypeFaceName="roboto"
      app:pbTitleTextSize="20sp"
      app:pbMinHeight="100dp"
      app:pbButtonElevation="15dp"
      app:pbIsLoading="false"
      app:pbTitleColor="#aaa"
      app:pbRippleBackgroundColor="#aff"
      app:pbRippleSecondaryColor="#faa"
      app:pbTitle="@string/app_name"
      app:pbButtonRadius="30dp"
      />
```

Anko example
-
```
<com.broliveira.view.ProgressButton
      android:id="@+id/mainButton"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:pbTitleTypeFaceName="roboto"
      app:pbTitleTextSize="20sp"
      app:pbMinHeight="100dp"
      app:pbButtonElevation="15dp"
      app:pbIsLoading="false"
      app:pbTitleColor="#aaa"
      app:pbRippleBackgroundColor="#aff"
      app:pbRippleSecondaryColor="#faa"
      app:pbTitle="@string/app_name"
      app:pbButtonRadius="30dp"
      />
```

--


#Max Width Layouts

Current version
-
	0.0.3

Gradle Import
-
	implementation 'com.broliveira:max-width-layouts:<version>'

--

#Max Width Layouts Anko Extensons


Current version
-
	0.0.3

Gradle Import
-
	implementation 'com.broliveira:max-width-layouts-anko:<version>'
