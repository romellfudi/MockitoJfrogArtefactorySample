# Mockito Android Project

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

First at all, begin configuring our testing environment.

[![center](snapshot/Mockito.png)](https://github.com/mockito/mockito/)
[![center](snapshot/jfrog.png)](https://jfrog.com/artifactory/)

## Run test cases
Before running, check the testing units work correctly and each case runs without any error & exceptions:

![center](snapshot/d.png#center)

![center](snapshot/c.png#center)

![center](snapshot/b.png#center)

## Upload library|api

In the path of the android project, add the JFrog Artefactory plug, adding the following statement:

```gradle
classpath "org.jfrog.buildinfo:build-info-extractor-gradle:3.1.1"
```

Next, add the plugins for your library module into gradle file, `artifactory-plug` is to make and update the packaging artifact, and `maven-plug`  to publish into a maven-central repository:

```gradle
apply plugin: 'com.android.library'
apply plugin: 'com.jfrog.artifactory'
apply plugin: 'maven-publish'
```

Defining variables: name and number of the library

```gradle
def packageName = 'fudi.freddy.api'
def libraryVersion = '1.0.a'
```

Overwrite the `publish gradle task`, which you set the GrooupId, version, and path of the library

```gradle
publishing {
    publications {
        aar(MavenPublication) {
            groupId packageName
            version = libraryVersion
            artifactId project.getName()
            // Packaging the *.aar, be careful with flavours  
            ("$buildDir/outputs/aar/${project.getName()}-release.aar")
        }
    }
}
```

Create a task for packaging, you could configure it for your flavor levels
```gradle
artifactory {
    contextUrl = 'http://localhost:8081/artifactory'
    publish {
        repository {
            // Access keys to repo 
            repoKey = 'libs-release-local'
            username = "admin"
            password = "123qweASD"
        }
        defaults {
            // which artifacts should be published
            publications('aar')
            publishArtifacts = true

            properties = ['qa.level': 'basic', 'dev.team': 'core']
            // Publish generated POM files
            publishPom = true
        }
    }
}
```

In terminal, start running JFrog-Artefactory
* Windows: run statement *"artefactoryService.exe"*
* MAC / Ubuntu: mediante el batch "artefactory.sh" 

Once finish configuring, the server runs by dfault in port 8081 (you could change in tomcat/conf/server.xml)

![center](snapshot/i.png#center)

![center](snapshot/a.png#center)

Will set up the password server 

![center](snapshot/e.png#center)

We choose the architecture that we need to deploy (`maven` in this case)

![center](snapshot/f.png#center)

![center](snapshot/g.png#center)

Displaying a dashboard, we could start working the apis repository

![center](snapshot/h.png#center)

Now send the artefact to the apis repository using the following commands

```sh
# build the packaging
./gradlew clean build
# Having built correctky, the artefact, which has pom file and documentation, is going to be deployed in the server
./gradlew assembleRelease generatePomFileForAarPublication artifactoryPublish 
```

In the menu, we can see the publishing library

![center](snapshot/k.png#center)

![center](snapshot/l.png#center)

Here, the dashboard shows the group ID: fudi.freddy.api, Artifact ID: api and version: 1.0.a.

## Download library/api

In this way, you can use this open-source plataform in any organization

Like any maven library, you have to put where the maven repository was in order to find your library dependecies (pom configuration).

Add the full `maven-repository` url, which can be found into the dashboqard, into gradle files:

```gradle
repositories {
    maven { url "http://localhost:8081/artifactory/libs-release-local" }
}
```

Add gradle dependency
```sh
//implementation project(':api')
implementation 'fudi.freddy.api:api:1.0.a'
```

### License
```
Copyright 2016 Romell D.Z.

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

**2016**

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
</style>