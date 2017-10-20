#!/bin/bash 

sudo docker build -t postgres-img .
sudo docker run --rm -d -p 10000:5432 --name postgres postgres-img
sleep 5
export PGPASSWORD='postgres'
#psql -h 0.0.0.0 -p 5432 -U postgres -d demodb -a -f ./northwind.postgre.sql


