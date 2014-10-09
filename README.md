Feed Parser (for Bing's homepage images)
========================================

This project is basically an XML based Feed parser, with implementation for Bing Image of the Day as an example. It saves images to disk. Included is a very basic version which may be extended as a general purpose feed parser.

Each image from Bing may or may not have a license as applicable, and are not covered under "Apache License Version 2.0" under which this software is released.

Contributions for improving (or completing) functionalities or adding new features are welcome.

Usage
=====
To use the Bing Image of the Day and download the images, you can either
 1. Download the latest build file from https://github.com/midhunhk/feed-parser/tree/master/BIotD/builds
 2. Extract the zip file contents
 3. Modify the `settings.properties` file in /app folder (Set the dowload path)
 4. Simply run `run-biotd-app.cmd` file if you are on a Windows machine

Or if you are not using a Windows machine, want to see the code or get hands on
 1. Import the project into a Java IDE like Eclipse (or build and run the application from a suitable editor)
 2. Modify the `settings.properties` file in /app folder (Set the dowload path)
 3. Open the file `com.ae.feeds.reader.tests.ReaderTest` and that's where you start

License
=======
Copyright 2013 Midhun Harikumar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Disclaimer
====
This project is not supported or endorsed by Microsoft or Bing. The author of this code is not liable to damages arising from the use or misuse. The images from Bing may have licenses applied to them, but may be used as wallpaper.
