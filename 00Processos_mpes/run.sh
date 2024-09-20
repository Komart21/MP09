#!/bin/bash

mvn clean compile

if [ -z "$1" ]; then
    echo "Ús: ./run.sh com.procesos.ExerciciX per executar l'exercici corresponent."
    exit 1
fi

mvn exec:java -Dexec.mainClass="$1"
