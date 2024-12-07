# Makefile for compiling and running Java programs

# Compiler
JAVAC = javac

# Interpreter
JAVA = java

# Options
JFLAGS = -d bin

# Source files
SRCS = Main.java AVLTree.java CommandGenerator.java FileOperator.java GUIVisualization.java Stock.java StockDataManager.java

# Class files (in bin directory)
CLASSES = $(SRCS:.java=.class)
CLASS_FILES = $(addprefix bin/, $(CLASSES))

# Default target: compile all source files and run the program
default: compile run

# Target: compile all source files and place class files in bin directory
compile: $(CLASS_FILES)

bin/%.class: %.java
	$(JAVAC) $(JFLAGS) $<

# Target: run the program
run: 
	$(JAVA) -Djava.compiler=NONE -Xint -cp bin Main

# Target: clean the bin directory
clean:
	rm -rf bin/*

# Phony targets
.PHONY: compile run clean default
