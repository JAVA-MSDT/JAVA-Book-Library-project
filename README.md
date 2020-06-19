# EpamLibraryWebFinalProject
using intelj ultimate

In order to see the project live at your local host, you need to check the file runner in src, uncomment the commands that controling creating 

and inserting tables, items and database.

* you need to refer to a tomcat version on your machine.

* change the database password to your own database password.

Library web project:-

Is about creating a web site which holds a lot of books and allowing the users to order a book, choosing where they want to read it

There are 3 different level of users, Admin, Librarian and Reader.

Library Project 

Users

Books

Orders

Users can search a book by different criteria.

Users are able to take the book home or reading it in the hall.

Book has a count filed which holds how mane book available 

User Role 

Admin is able to see list of all users and controlling everything

Librarian is able to see all the others but not Librarian users, able to add user, block user, edit user, but not able to delete user, or book

User is able to see the books list and to check his own order

Implementation using 

MVC pattern

Database of your choice, preferred Postgresql.

Using DAO to deal with database operation

Service to work with database from DAO then delivering it to the controller

Implement custom front-controller and servlet.

Prevent from refresh effect after form submit F5 effect (Post Request)

View layer using jsp

Control which page to display depends on the user role, so admin and librarian are able to see everything but user only sees his page, his order page and books


