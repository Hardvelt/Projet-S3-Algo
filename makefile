
all: Formation.class

Formation.class: *.java
	javac *.java

clean: 
	rm *.class

