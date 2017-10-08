#!/bin/bash 

sudo docker build -t backend-img .
sudo docker run --rm -d -p 10002:8080 --name backend backend-img


