-- A readme.txt detailing the project, usage and assumptions youʼve made as well as the
-- choices around your language, plugins and 3rd party libraries.

jumo-mr is a mini map reduce framework that can be used to create map reduce programs to do data processing.
It uses on external libraries.

To build, cd into the working directory and run the maven command
mvn clean package

To run,
java -jar target/jumo-mr-1.0-SNAPSHOT.jar

The run command requires at least three argument 
    -inputfile= file to be processed
    -inputheader= set as true if the <inputfile> contains the a header row as the first line, otherwise set as false
    -outputdir=the directory where the output file Output.csv will be stored

So a complete command will be 
java -jar target/jumo-mr-1.0-SNAPSHOT.jar -inputfile= -inputHeader= -outputdir=

An example is 
java -jar target/jumo-mr-1.0-SNAPSHOT.jar -inputfile=C:\Users\m.enudi\Downloads\Loans.csv -inputheader=true -outputdir=C:\Users\m.enudi\Downloads