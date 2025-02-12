#Project 1

Added by: Christopher Vojkufka and Dominick Smith

# Project Overview:
The point of this project is to establish a connection to a remote API, using java code from a gradle project inside IntelliJIdea, recieve an input from a user that determines an output, or better yet generates a revision history based on the article they may or may not have provided the program. Proper error handling is present and ends the project due to the criteria of this project.

This project should reflect on Test Driven Development as well as Single Responsibility Principles, ensuring that our code is clean and readable.

Paired Programming is a large part of this project.

## Features:
This project features an establishing of an Wikipedia API and collects at most 21 most revisions in reverse chronological order from an article name that is provided to the program from user input. The data is formatted in numerical order in order of count, timestamp, author.

## Suppressed Warnings:
We have suppressed a warning inside WikipediaAPITest as well as WikipediaAPI because the method used is still functional, and we are unaware of suitable alternatives at the moment. The suppression is documented here to maintain clarity and prevent unnecessary warnings. 

## Needed Build Instructions:
Requires Java 11 or Higher.
Requires Gradle.

### Installation:
Clone the respitory.

Add clone url into indpendent project under gradle build.

Every Test and the Program themselves relies on gradle!
