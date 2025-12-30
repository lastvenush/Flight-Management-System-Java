Flight Management and Airport Automation System
A Java-based desktop application designed to manage airline flight schedules and passenger registrations. This project demonstrates the implementation of Object-Oriented Programming (OOP) principles and Graphical User Interface (GUI) development using Java Swing.

Project Architecture
The application is structured into two main layers to ensure a clean separation of concerns:

Model Layer: Contains the core logic and data structures including Ucus.java (Flight) and Yolcu.java (Passenger).

GUI Layer: Managed by MainFrame.java, which handles the user interface, event listeners, and dynamic table rendering.

Core Features
Dynamic Flight Management: Supports the creation of flights for multiple airlines including Turkish Airlines, AJet, and Pegasus.

Automated Flight Prefixing: Automatically assigns flight prefixes such as "TK" for Turkish Airlines, "PC" for Pegasus, and "VF" for AJet based on the selected carrier.

Passenger Operations: Allows users to add passengers to specific flights and remove the last registered passenger from a selected flight.

Real-time Table Updates: The system utilizes a DefaultTableModel to display flight details, origin, destination, and passenger counts in real-time.

Integrated Branding: Displays high-fidelity airline logos within the data table using custom scaling methods.

Mock Data Generation: Automatically populates the system with 15 randomized flight entries upon startup for testing purposes.

Technical Implementation
CardLayout: Used for seamless switching between the Flight Management and Passenger Operations panels.

Swing Timer: Implemented a background timer to ensure the flight selection dropdown remains synchronized with the current flight list.

Data Structures: Utilizes ArrayList for efficient management of flight and passenger collections.

Image Processing: Implements getScaledInstance to ensure airline assets are rendered correctly within the UI rows.

Directory Structure
/model: Contains the Ucus and Yolcu classes.

/gui: Contains the MainFrame class for UI logic.

/assets: Contains graphical assets for Turkish Airlines, AJet, and Pegasus.

Prerequisites
Java Development Kit (JDK) 8 or higher.

Java Swing library (included in the standard library).
