# Makefile

all: package

clean:
	mvn clean

package:
	mvn clean package

install: clean
	mvn clean install

run:
	java -cp target/elevator-sim-1.0-SNAPSHOT.jar io.yiyuzhou.app.App

.PHONY: all package clean install
