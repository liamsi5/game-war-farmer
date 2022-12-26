#VARIABLES
JC= javac
JV= java
SRC= cd src
CLASSES= cd classes
REMOVE= rm -rf
JAR=jar cvfm 
JCT= javac -encoding ISO-8859-1 -classpath test4poo.jar
EXEC_TEST=java -jar test4poo.jar
TESTS= test/jeu/plateau/JoueurTest.java	\
	   test/jeu/plateau/PlateauTest.java	\
	   test/jeu/plateau/emplacement/*.java \
	   test/jeu/personnage/util/*.java	 

DOCS= jeu/*.java \
        ./jeu/util/*.java \
        ./jeu/plateau/*.java \
        ./jeu/plateau/emplacement/*.java \
        ./jeu/personnage/*.java \
        ./jeu/personnage/util/*.java

#COMPILATION DE CLASSES
cls:
	$(SRC) && $(JC) -encoding  ISO-8859-1 jeu/*.java -d ../classes

#COMPILATION DES JEUX 
guerre: 
	$(SRC) && $(JC) -encoding ISO-8859-1 jeu/GuerreMain.java -d ../classes

agricole:
	$(SRC) && $(JC) -encoding ISO-8859-1 jeu/AgricoleMain.java -d ../classes


guerreHasard: 
	$(SRC) && $(JC) -encoding ISO-8859-1 jeu/GuerreMain.java -d ../classes

agricoleHasard:
	$(SRC) && $(JC) -encoding ISO-8859-1 jeu/AgricoleMain.java -d ../classes



# COMPILATION DES TESTS

tests: cls
	$(JCT) $(TESTS) -d classes

#EXECUTION DES TESTS
%:
	$(EXEC_TEST) $@

#exemple : make jeu.plateau.PlateauTest


# DOCUMENTATION 

doc: 
	$(SRC) && javadoc  -encoding ISO-8859-1 $(DOCS) -d ../docs

# EXECUTION DES JEUX

playGuerre: guerre
	$(CLASSES) && $(JV) jeu.GuerreMain 

playAgricole: agricole
	$(CLASSES) && $(JV) jeu.AgricoleMain 


playGuerreHasard: guerreHasard
	$(CLASSES) && $(JV) jeu.GuerreHasardMain 

playAgricoleHasard: agricoleHasard
	$(CLASSES) && $(JV) jeu.AgricoleHasardMain



#CREATION DES .JAR

guerre.jar: guerreHasard
	$(CLASSES) && $(JAR) ../jar/guerre.jar ../manifest-guerre jeu

agricole.jar: agricoleHasard
	$(CLASSES) && $(JAR) ../jar/agricole.jar ../manifest-agricole jeu

testExec: testExec
	$(EXEC_TEST) && $(TESTS)

#AUTRES

cleanJar:
	$(REMOVE) jar/*
clean:
	$(REMOVE) classes/jeu/*.class

all: cls tests doc

.PHONY:  cls clean tests all cleanJar

