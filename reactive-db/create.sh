#!/bin/bash
port=${1:-8080}

curl -H "content-type: application/json" -d '{"title":"teste","content":"hola mundo"}' http://localhost:8080/examples