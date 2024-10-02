#!/bin/bash

# Compilar el projecte
mvn clean compile

# Executar el projecte
mvn exec:java -Dexec.mainClass="com.project.Exercici1"
