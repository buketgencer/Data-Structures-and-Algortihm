JC = javac
JFLAGS = -g
TARGET = tester
SOURCES = tester.java SortAlgorithm.java SelectionSort.java BubbleSort.java


default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java $(TARGET)


clean:
	rm -f *.class

.PHONY: clean default