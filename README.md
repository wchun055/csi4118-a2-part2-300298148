### Instruction For Users:

*This is the same as InstructionsForUsers.md, just made again so that it can be seen on display.*

1. In one terminal/computer, enter: `javac myServer.java`. This will begin the server side.
2. In another terminal/computer, enter: `javac myClient.java`. This will begin the client side.
3. **On the server side terminal**, enter: `java myServer 9999`. There will be a server running message.
4. **On the client side terminal**, enter: `java myClient *YOUR IP ADDRESS* 9999`. Please replace with your IP Address (using ipconfig command). There will be a session established and greetings message.
5. To test the commands, enter the following in the **client side terminal** (the commands are based on the assignment PDF examples):
    1. `typeA 5432190` -> Will return the IP address.
    2. `typeB 7345071 hello world1 -> Will return HELLO WORLD.
    3. `typeC 1234567 JUNIPER` -> Will return ACKN.
    4. `typeD 1234567` -> Will exit and end the session. We can check on the server side terminal that the sesion is terminated as well.
6. There will also be a server.log created, logging what commands the client made.
