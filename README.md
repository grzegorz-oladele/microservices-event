# Welcome to event application in microservices architecture

The application allows you to comprehensively manage the calendar of events in your company. The project was divided
into 5 microservices, which include two configuration projects and 3 projects containing business logic.

### Documentation:

## EUREKA

A registry to register and provide an instance to execute or deny a client request. The Eureka Server console is
available at <span style="color: gold"> http://localhost:8761/eureka </span>

## GATEWAY-SERVICE

A component that mediates between the service and the client. A component that mediates between the service and the
client. Gateway is available at http://localhost:8282/

Using a path <span style="color: orange"> /api/participants/...</span> we can get to a specific resource on a
participant site Using a path <span style="color: orange"> /api/events/...</span> we can get to a specific resource on a
event site

## PARTICIPANT-SERVICE

A service that contains the business logic responsible for managing event attendees using a MySQL database. The service
has the following functionalities:

<span style="color: yellow">@GetMapping</span> <span style="color: green"> /participants</span> - Retrieves all event
participants from the database; if the resource is not found, an appropriate message is displayed

<span style="color: yellow">@GetMapping</span> <span style="color: green"> /participants/{id}</span> - Retrieves the
selected participant from the database; if the resource is not found, an appropriate message is displayed

<span style="color: yellow">@PostMapping</span> <span style="color: green"> /participants</span> - Allows you to add a
participant from the database

<span style="color: yellow">@PatchMapping</span> <span style="color: green"> /participants/last-name/{id}</span> -
Allows you to edit a participant's name; if the resource is not found, an appropriate message will be displayed

<span style="color: yellow">@PatchMapping</span> <span style="color: green"> /participants/company/{id}</span> - Allows
editing the company where the participant works; if the resource is not found, an appropriate message will be displayed

<span style="color: yellow">@DeleteMapping</span> <span style="color: green"> /participants/{id}</span> - Allows you to
remove a participant from the database; if the resource is not found, an appropriate message will be displayed

<span style="color: yellow">@DeleteMapping</span> <span style="color: green"> /participants/emails</span> - Retrieves
participant emails from the database; if the resource is not found, an appropriate message is displayed

## EVENT-SERVICE

A service containing business logic responsible for managing events using the MongoDB database. The service has the
following functionalities:

<span style="color: yellow">@GetMapping</span> <span style="color: green"> /events/</span> - Retrieves all events from
the database; if the resource is not found, an appropriate message is displayed

<span style="color: yellow">@GetMapping</span> <span style="color: green"> /events/{code}</span> - Allows you to
retrieve a specific event from the database; if the resource is not found, an appropriate message is displayed

<span style="color: yellow">@GetMapping</span> <span style="color: green"> /events/{code}/members</span> - Allows you to
retrieve the attendees of an event from the database; if the resource is not found, an appropriate message will be
displayed

<span style="color: yellow">@PostMapping</span> <span style="color: green"> /events</span> - Allows you to add an event
to the database

<span style="color: yellow">@PatchMapping</span> <span style="color: green"> /events/edit-limit/{code}</span> - Allows
you to edit the attendee limit for an event; if the resource is not found, an appropriate message will be displayed

<span style="color: yellow">@PatchMapping</span> <span style="color: green"> /events/edit-description/{code}</span> -
Allows you to edit the description of the event; if the resource is not found, an appropriate message will be displayed

<span style="color: yellow">@DeleteMapping</span> <span style="color: green"> /events</span> - Allows you to delete an
event from the database; if the resource is not found, an appropriate message will be displayed

<span style="color: yellow">@PostMapping</span> <span style="color: green">
/events/{eventCode}/participants/{participantId}</span> - Allows you to enroll a given participant in the corresponding
event; if any resource is not found, a message will be displayed

<span style="color: yellow">@PostMapping</span> <span style="color: green"> /events/{code}/finish-enroll</span> - Allows
to close enrollment for the event; if the resource is not found, an appropriate message will be displayed

## NOTIFICATION-SERVICE

A service to asynchronously send email messages to event participants using the Java Mail Sender library and RabbitMQ.
Notifications with information about event are loaded on RabbitMQ queue and then sent to event participants when
registration is closed by calling endpoint <span style="color: green"> /events/{code}/finish-enroll</span>
