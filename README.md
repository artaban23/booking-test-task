# Booksrv - Spring Boot service

A Spring Boot service project with Booking management functionality.

## Features

- RESTful API for Booking management
- E2E Rest test is stored in bruno-test folder (requires Bruno app)
- to avoid concurrent issues a locking mechanism was implemented in PropertyRepository which also acts as in-memory DB
- Sample UnitTest is done for DateRangeUtils class

## Known issue and Todos
- Booking and Blocks range validation logic has a bug when Booking becomes Cancelled
- Did not cover full code base with unit-tests due to lack of time

## Other notes
- For debugging purposes extra endpoint via PropertyController
- To speed up development a single Property is auto created in the DB


