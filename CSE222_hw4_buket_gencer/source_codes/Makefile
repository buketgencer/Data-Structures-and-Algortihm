all : clean javadoc compile clear run

# Create javadoc for the entire project in JAVADOC directory 
javadoc :
	@javadoc -d JAVADOC -sourcepath src src/*.java

# Compile all the java files
compile :
	@javac -d bin -sourcepath src src/*.java

# Run the main class
run :
	@java -cp bin Main

# Clean the bin and JAVADOC directory 
clean :
	@rm -rf bin/*
	@rm -rf JAVADOC/*

clear : 
	@clear