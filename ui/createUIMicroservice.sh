#!/bin/bash 

sudo docker build -t ui-img .
sudo docker run --rm -d -p 10001:8080 --name ui ui-img


