# Library Management System (LMS)

## Overview

The Library Management System (LMS) is a Java-based console application created as part of an SDLC assignment.

The goal of the project is to take a previously written software development plan and implement the system in code using Java. The application is designed for a local library and allows a librarian to manage patron records through a command-line menu.

## Features

The LMS allows the user to:

- Load patron records from a text file
- Manually add new patrons
- Remove patrons by their unique ID
- Display all patrons currently stored in the system
- Continue using the menu until the user chooses to exit
- Validate patron IDs and overdue fine amounts
- Prevent duplicate patron IDs

## Patron Information

Each patron contains the following information:

- 7-digit unique patron ID
- Name
- Address
- Overdue fine amount
- Fine range: `$0.00` to `$250.00`

## Input File Format

The program accepts patron data from a text file.

Each line represents one patron and uses the following format:

Example:
1245789-Sarah Jones-1136 Gorden Ave. Orlando, FL 32822-40.54
3256897-Mason Arby-6060 Saginaw St. Casselberry, FL 34852-0
4567891-Avery Jones-1919 Pine Lance Blvd. Oviedo, FL 32478-1.36
