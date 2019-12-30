
How to compile the game?
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable={path-to-javac} clean compile


How to run unit tests for the game?
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable={path-to-javac} clean compile test

How to package the game?
mvn -Dmaven.compiler.fork=true -Dmaven.compiler.executable={path-to-javac} clean compile assembly:single

Note: Replace {path-to-javac} with the actual path of JDK 8 javac
For e.g, /usr/lib/jdk1.8.0_231/bin/javac

How to run the game?
{path-to-java} -jar target/battleship-1.0-jar-with-dependencies.jar

Note: Replace {path-to-java} with the actual path of JDK 8 java
For e.g, /usr/lib/jdk1.8.0_231/bin/java  

Also note: Game works on JDK 8 and does not work on later versions of JDK. Wifi connectivity to eduroam is required. The game will hang if wrong wifi network is chosen like UCD Wireless.
