# MULTITHREADED-CHAT-APPLICATION

*COMPANY*: CODTECH IT SOLUTIONS

*NAME*: MEGANATHAN S

*INTERN ID*: CT12DN552

*DOMAIN*: JAVA PROGRAMMING

*DURATION*: 12 WEEKS

*MENTOR*: NEELA SANTOSH

### Description of Task 3

Task 3 involves designing and implementing a robust client–server chat application in Java that leverages sockets and multithreading to allow multiple users to communicate in real time. The system comprises two primary classes:

* **ChatServer.java**

  * Listens on a configurable TCP port (default 1234) via ServerSocket.
  * Accepts incoming client connections and assigns each a unique user name (e.g., “User1,” “User2”).
  * Spawns a ClientHandler thread per client to manage individual communication streams.
  * Maintains a thread-safe Vector<ClientHandler> for broadcasting messages to all connected clients.
  * Includes proper commenting throughout for readability and future maintenance.

* **ClientHandler (inner class)**

  * Extends Thread and encapsulates each client’s Socket, input/output streams, and userName.
  * Sends a welcome message immediately upon client connection.
  * Continuously reads messages from its client and broadcasts them to all other clients.
  * Catches IOException to handle client disconnects gracefully, logs the event to the server console, and performs resource cleanup by closing sockets and removing itself from the client list.

* **ChatClient.java**

  * Connects to the server at the specified host and port using Socket.
  * Launches two threads:

    1. *Listener Thread*—reads messages from the server and prints them to the console.
    2. *Sender Thread*—reads user input from System.in and sends it to the server.
  * Enables concurrent input and output without blocking.
  * Allows the user to exit cleanly by terminating the console session or using interrupt commands.

---

## 2. Development Environment & Tools

* **IDE**: IntelliJ IDEA for code editing, refactoring, and integrated debugging (breakpoints, thread inspection).
* **Language & Runtime**: Java SE 17 (OpenJDK) ensuring access to modern language features and stable performance.
* **Networking**: Core java.net package (ServerSocket, Socket).
* **Concurrency**: java.lang.Thread and thread-safe Vector for client management.
* **I/O**: BufferedReader, InputStreamReader, and PrintWriter for efficient console and socket streams.
* **Version Control**: Git (initialized in project root) with a GitHub repository containing:

  * All source files
  * A README.md detailing setup, build, and run instructions
  * A dist/ folder housing the compiled JAR
* **Learning Resources**: Official Java documentation, YouTube tutorials, and ChatGPT consultations to troubleshoot threading and synchronization issues.
* **OS Compatibility**: Developed and tested on Windows 11 and Ubuntu 22.04.

---

## 3. Real-World Applications

* **Team Communication Prototypes**: Lightweight, on-premise chat tools for distributed developer teams or workshops.
* **Educational Demonstrations**: Practical example of networking and concurrency in university or bootcamp curricula.
* **Embedded & IoT**: Deployed on headless devices (e.g., Raspberry Pi) to relay messages between sensors or controllers.
* **Proof-of-Concept Platforms**: Foundation for GUI-based chat clients (JavaFX, Swing) or for adding persistence, encryption, and user authentication.
* **Remote Pair Programming**: Enables collaborators to share real-time text messages alongside live coding sessions.

---

## 4. Challenges & Solutions

1. **Thread Safety**

   * Issue: Concurrent modification of the shared client list.
   * Solution: Used Vector, which provides built-in synchronization for add/remove operations.

2. **Graceful Resource Management**

   * Issue: Preventing socket leaks when clients disconnect.
   * Solution: Implemented cleanup() in ClientHandler to close sockets and remove handlers.

3. **Exception Handling**

   * Issue: Uncaught I/O exceptions could crash the server.
   * Solution: Wrapped critical network I/O in try–catch blocks and logged errors without terminating the server.

4. **Concurrent I/O on Client**

   * Issue: Blocking reads could stall message display.
   * Solution: Separated input and output into two dedicated threads to ensure responsiveness.

---

## 5. Key Learning Outcomes

* Mastered Java socket programming and thread management.
* Crafted modular code with clear separation of concerns (ClientHandler, main loops).
* Enhanced debugging skills in IntelliJ, including thread inspection.
* Adopted best practices in commenting, naming conventions, and version control workflows (feature branches, descriptive commits).
* Leveraged online resources (official docs, tutorials, ChatGPT) to accelerate development and problem-solving.

---

## Output
![Image](https://github.com/user-attachments/assets/3ea93583-a57a-4510-9299-43bc4952df4a)
![Image](https://github.com/user-attachments/assets/4f729a83-284a-4c52-a1b4-9587944682d5)
![Image](https://github.com/user-attachments/assets/70b26617-6181-436a-a0b5-fc71e328ae12)
