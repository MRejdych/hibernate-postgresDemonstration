#!/bin/bash 

sudo docker build -t backend-img .
sudo docker run --rm -d -p 8080:8080 --name backend backend-img


