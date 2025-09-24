# TidyUp - Task Tracker CLI

A simple Command-Line Interface (CLI) Task Tracker built in Java.
This project allows you to add, update, delete, and manage tasks directly from the terminal.
Tasks are stored in a JSON file on your local filesystem (no external libraries required).

🔗 Project URL: https://roadmap.sh/projects/task-tracker

## 🚀 Features

- Add new tasks with a description.
- Update or delete existing tasks.
- Mark tasks as todo, in-progress, or done.
- List tasks by status (all, todo, in-progress, done).
- Persistent storage in a tasks.json file.
- Timestamps for createdAt and updatedAt fields.

## 📂 Project Structure

```
TidyUpCLI/
│
├── Task.java           # Task model with properties & JSON handling
├── TaskManager.java    # Handles file read/write & task formatted outputting
├── TaskCLI.java        # CLI entry point (main program)
├── tasks.json          # JSON file where tasks are stored
└── README.md           # Project documentation
```

## ⚙️ Requirement

- Java 11+ installed on your system
- A terminal/command prompt

## 🛠️ Getting Started

Follow these steps to run the project locally:

```bash
# Clone the repository
git clone https://github.com/aimedivin/TidyUpCLI.git

# Navigate to the project directory
cd TidyUpCLI

# Compile the java Files
javac Task.java TaskManager.java TaskCLI.java

# Run the Program
java TaskCLI <command> [arguments]
```

## </> Commands

Follow these steps to run the project locally:

```bash
# Add a new task
java TaskCLI add "Write you task description here"

# Update an existing task
java TaskCLI update 1 "Task updated description"

# Delete a task
java TaskCLI delete [task-id]

# Mark/Update task status
java TaskCLI <mark-in-progress|mark-done> [task-id]

# List tasks
java TaskCLI list [task-status](optional)

```
