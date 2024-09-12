# Part Time Pay Calculator

## Overview

This project is a technical test for Tap Company. 
The application calculates the total earnings of a single part-time worker from given information.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)

## Installation

### Prerequisites

Java 21

### How to install

```bash
# In the project root folder 
part-time-pay-calculator/

# Compile the application
javac -d out src/**/*.java

# Run the application
java -cp out/production/part-time-pay-calculator Main
```

## Usage

The application will ask you information on the standard input

Three Integers representing the wages for regular shift, nighttime shirt and midnight shift

*Example : 1000 1300 1500*

One Integer representing the number for worked days

*Example : 4*

And for each worked day : two Integers representing the starting and ending hour of the worker.

*Example :*

*0 9* 

*9 17*

*17 22* 

*22 23*



## Configuration

The configuration file allows to change which hour belong to which shift.
It is mandatory to fill the configuration file with all hours from 0 to 23.

[Configuration File](src/resources/configuration.properties)


### Basic configuration example


shift.regular=9 10 11 12 13 14 15 16

shift.nighttime=17 18 19 20 21

shift.midnight=0 1 2 3 4 5 6 7 8 22 23