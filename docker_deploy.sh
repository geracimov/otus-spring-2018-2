#!/bin/bash
docker rm  libraryapp
docker rmi libraryapp:v1.0
docker image build --target STEP_BUILD -t libraryapp:compiled .
docker image build -t libraryapp:v1.0 .
#docker run -it  --name libraryapp libraryapp:v1.0
