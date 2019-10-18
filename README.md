# Dragalia Lost library
This library allows to easily query the Gamepedia page at https://dragalialost.gamepedia.com.

It is written using Kotlin multiplatform and builds an publishes in JVM and JS for now.

### **NB: STILL IN DEVELOPMENT** 
At the moment only raw data from Gamepedia is available using the `GamepediaDatasourceImplementantion`, 
but development is going fast. 

## Project structure
The library is divided into 4 group - each one has their respective artifact for any given platform - and is build using a [clean architecture](https://www.google.com/search?q=clean+architecture):
 - **core**: definitions of the entities and `DragaliaLostRepository` interface with suspending functions. The Js build has as well `DragaliaLostJsRepository` with promises instead.
 
 - **data**: definition of gamepedia types and its `GamepediaDatasource` interface. Contains the implementation of the repository using the available datasource.
 
 - **core**: platform specific implementation of datasources.
 
 - **kodein-di**: Contains prebuilt modules to allow dependency injection using [Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI). 
 
 Each maven publication depends on the previous (duh!), which means that depending on your needs you should import just one. 
 
 ## How to use
 ##### USING CORE
 When importing just `core` you need to instantiate `DragaliaLostRepositoryImplementation` wich needs a `GamepediaDatasourceImplementation` (and a bunch of mappers!), which needs a [Ktor `HttpClient`](https://ktor.io/clients/index.html) (with the [`JsonFeature`](https://ktor.io/clients/http-client/features/json-feature.html) installed), which needs `GamepediaEndpointsImplementation`, and so on and so forth.
 
 ##### USING KODEIN-DI
 When building your `kodein` just `import(dragaliaLostModule())` and ask for an `instance<DragaliaLostRepository>()`! Easy right?
 

## Install [![](https://jitpack.io/v/lamba92/dragalia-library.svg)](https://jitpack.io/#lamba92/dragalia-library)

Use Maven or Gradle. Latest master builds are available through Jitpack (click the badge just above).
The stable releases will use the GitHub package registry. [Check the upper section](https://github.com/lamba92/dragalia-library/packages)!

I'll make a n00b-friendly install guide as soon as the library reaches a decent stage.

A day may come when i find a decent way to publish this on NPM and make it usable from a plain JS/TS project, [but it is not this day...](https://i.imgflip.com/3dmhim.jpg) 
