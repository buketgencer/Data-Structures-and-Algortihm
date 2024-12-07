all : clean javadoc compile clear run

# Create javadoc for the entire project in JAVADOC directory 
javadoc :
	@mkdir -p JAVADOC
	@javadoc -d JAVADOC -sourcepath src src/*.java

# Compile all the java files
compile :
	@mkdir -p bin
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
