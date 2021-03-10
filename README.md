# APITestFramework
Java Project for test automation, covering API testing. Created for validating health response status for a given url and fetching EULA for a given tenant.

## Concepts Included
1. API interaction methods
2. Data Provider
3. Excel reader

## Tools
1. Maven
2. TestNG
3. HTTP Client Framework

## Requirements
In order to utilize this project you need to have the following installed locally:
1. Eclipse IDE or any supported IDE
2. Maven
3. TestNG
4. Java 1.8

## Usage
The project is broken into four separate modules. 

The core files handling the working of the framework are present in the path `src/main/java`, inside package named as `com.kosmos.core`. 

Test case is available in the `src/test/java` path, inside package named as `com.kosmos.client`.

Test data like url required for running the test is fed via excel file which is placed under testdata folder available in `src/test/resources` path.

The required fields to set up the framework like link for the api to be tested, path for the testdata sheet etc; are fed via `config.properties` file available under the framework.

To test the framework-  
- open the `RestClient.java` file from `com.kosmos.client` package
- right click on the file
-  choose `RunAs`, then `TestNG Test`

## Reporting
After successfull execution of the test a Test-output folder will be generated (Refresh the project if the folder is not visible). Open the index.html in browser to see the detailed testNG report.

### Note
On updating the maven project you might see an error in the Data Provider code available in the test file. Just hover on the error and set the project compliance and JRE to 1.8.
