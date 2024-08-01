# Note-Application
You can run this with application through Java 8 by running the NoteApplication class.
Once the application is running you can choose any API platform to test the endpoints I personally use Postman.

Sample

Create Note
POST http://localhost:8080/notes


Request
{
    "title": "House of Dragons",
    "body": "HBO" 
}

Response
{
    "id": "369b4910-c1a8-4fb4-a697-34db70feeb54",
    "title": "House of Dragons",
    "body": "HBO"
}
