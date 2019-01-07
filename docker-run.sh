#!/bin/bash

docker run -v $PWD:/app -w /app -it gradle:jdk11 bash
