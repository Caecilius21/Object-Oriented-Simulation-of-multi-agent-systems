# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testBoid

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestGUI.java

testBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/balls/Balls.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/balls/BallsSimulator.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/balls/TestBalls.java

testConway:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/conway/EtatConway.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/conway/Grid.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/conway/RulesConway.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/conway/ConwaySimulator.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/conway/TestConway.java

testImmigration:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/immigration/ImmigrationSimulator.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/immigration/TestImmigration.java

testSegregation:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/segregation/SegregationSimulator.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/segregation/TestSegregation.java

testBoid:
	# javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/OLD/Boid.java
	# javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/OLD/BoidsEvent.java
	# javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/OLD/TestBoids.java

	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/AbstractBoid.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/ClassicBoid.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/FoodBoid.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/PredateurBoid.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/AbstractBoidsEvent.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/Vecteur.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/boid/TestBoids.java

testEvent:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/event/Event.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/event/EventManager.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/event/MessageEvent.java
	javac -d bin -classpath bin/gui.jar -sourcepath src src/event/TestEventManager.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM
exeGUI:
	java -classpath bin:bin/gui.jar TestGUI

exeBalls:
	java -classpath bin:bin/gui.jar balls/TestBalls

exeConway:
	java -classpath bin:bin/gui.jar conway/TestConway

exeImmigration:
	java -classpath bin:bin/gui.jar immigration/TestImmigration

exeSegregation:
	java -classpath bin:bin/gui.jar segregation/TestSegregation

exeBoid:
	java -classpath bin:bin/gui.jar boid/TestBoids

exeEvent:
	java -classpath bin:bin/gui.jar event/TestEventManager


clean:
	rm -rf bin/*.class
