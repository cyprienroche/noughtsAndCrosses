GRADLE  := ./gradlew

all: build

build:
	$(GRADLE) installDist

clean:
	$(GRADLE) clean
	rm -rf .idea
	rm -rf .gradle

.PHONY: all build clean