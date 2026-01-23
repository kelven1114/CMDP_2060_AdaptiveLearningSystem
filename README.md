Adaptive Learning System
Overview
An interactive Java Swing application designed to provide help for students with learning disabilities, autism or dyslexia learn through interactive, customizable lessons. This app will track student's progress, adapt to individual learning speeds, through interactive visual learning modules.

Setup Instructions
1. Clone the Repository
Make a new folder

Open VS Code and locate the folder

Open VS Code terminal

Run these commands:

bash
git init
git clone <repository-url>
2. Lesson Files Setup
Run the program

Create your student profile

Exit the program

Move the pattern_lesson.txt and word_lesson.txt files into the lessons/ directory created by the program

3. Directory Structure
text
AdaptiveLearningSystem/
├── lessons/
│   ├── pattern_lesson.txt
│   └── word_lesson.txt
├── students/
├── reports/
├── src/
│   └── AdaptiveLearningSystem.java
└── README.md
4. Compile and Run Application
In terminal, navigate to the project directory and run:

bash
javac AdaptiveLearningSystem.java
java AdaptiveLearningSystem
Important Notes
The program will automatically create students/ and reports/ directories

You must manually move the lesson files to the lessons/ directory after the first run

All student data and reports will be saved locally in their respective directories
