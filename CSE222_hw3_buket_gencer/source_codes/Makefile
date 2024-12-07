JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		Inventory.java \
		Device.java \
		Main.java \
		Smartphone.java \
		Laptop.java \
		Smartwatch.java \
		Headphone.java \

# java Main should not be printed clean and cleandoc should be printed
all : cleandoc classes doc run clean

run : Main.class
	@$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	@$(JC) $(JFLAGS) $<

doc:
	@$(JD) $(JDFLAGS) *.java 

clean: *.class
	@$(RM) *.class 

cleandoc: ./javadoc
	@$(RM) -r ./javadoc

