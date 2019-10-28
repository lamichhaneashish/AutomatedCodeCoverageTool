# AutomatedCodeCoverageTool
SE4367 Software Testing Project

This is the maven integrated Project for peforming on-the-fly bytecode manipulation and collecting code coverage information 
for each JUnit Test Method of the project under the test. 


Steps to test the coverage tool :

In the pom.xml of the project under test add the following lines : 

1. Add the dependency:
<dependencies>  
  <dependency>
  <groupId>ASMCC</groupId>
  <artifactId>ashishyugeshjavier</artifactId>
  <version>0.0.1-SNAPSHOT</version>
 </dependency>
 </dependencies>
 
2. Add the javaagent in the plugin :  
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <configuration>
          <argLine>-javaagent:{address to the jar file}/ashishyugeshjavier-0.0.1-SNAPSHOT.jar</argLine>
          <properties>
            <property>
              <name>listener</name>
              <value>ashishyugeshjavier.JUnitListener</value>
            </property>
          </properties>
          <excludes>
            <exclude>**/BaseTestCase.java</exclude>
          </excludes>
        </configuration>
      </plugin>

<< This part has already been added to our pom files for our observed projects>>
    
 

