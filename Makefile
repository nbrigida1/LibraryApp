##############################################################
# Main Program
##############################################################

run: LibraryCheckoutFrontend.class
	java LibraryCheckoutFrontend

##############################################################
# Frontend Developer
##############################################################
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java \
	-jar junit5.jar \
	-cp . \
	-c FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac FrontendDeveloperTests.java \
	-cp .:junit5.jar 

LibraryCheckoutFrontend.class: LibraryCheckoutFrontend.java BackendDeveloperTests.class
	javac LibraryCheckoutFrontend.java
##############################################################



##############################################################
# Backend Developer
##############################################################
runBackendDeveloperTests: BackendDeveloperTests.class
	java \
	-jar junit5.jar \
	-cp . \
	-c BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java
	javac BackendDeveloperTests.java \
	-cp .:junit5.jar 

LibraryCheckoutBackend.class: LibraryCheckoutBackend.java AlgorithmEngineerTests.class
	javac LibraryCheckoutBackend.java
##############################################################



##############################################################
# Algorithm Engineer
##############################################################
runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java \
	-jar junit5.jar \
	-cp . \
	-c AlgorithmEngineerTests

AlgorithmEngineerTests.class: RBTLibrary.class Book.class BookReader.class
	javac AlgorithmEngineerTests.java \
	-cp .:junit5.jar 

RBTLibrary.class: RBTLibrary.java DataWranglerTests.class
	javac RBTLibrary.java 
##############################################################



##############################################################
# Data Wrangler
##############################################################
runDataWranglerTests: DataWranglerTests.class
	java \
	-jar junit5.jar \
	-cp . \
	-c DataWranglerTests

DataWranglerTests.class: BookReader.class Book.class
	javac DataWranglerTests.java \
	-cp .:junit5.jar 

BookReader.class: BookReader.java
	javac BookReader.java

Book.class: Book.java
	javac Book.java
##############################################################



##############################################################
# Miscellaneous
##############################################################
clean:
	rm -rf *.class
##############################################################