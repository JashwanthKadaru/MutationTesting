# Mutation Testing Project

This project demonstrates mutation testing using PITest and includes unit tests with JUnit.

---

## Prerequisites

Before setting up the project, ensure the following are installed on your system:

1. **Java 17** or higher ([Download Java](https://adoptopenjdk.net/)).
2. **Apache Maven 3.6.0+** ([Download Maven](https://maven.apache.org/download.cgi)).
3. **Git** for cloning the repository ([Download Git](https://git-scm.com/)).

---

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-folder>

2. **Open the Project in VSCode**:
   ```bash
   code .

3. **Open Terminal in VSCode & install all dependencies**:
   ```bash
   mvn clean install

4. **Run Tests**
   ```bash
   mvn test

5. **Generate Mutation Testing Report**
   ``` bash
   mvn org.pitest:pitest-maven:mutationCoverage

--- 

To Manually run the program *Main.java*, open the `Main.java` file in `src/main/java/com/mutationtesters` in the IDE, and click on run. Otherwise, run the file using command line.

---

**Do's and Dont's**
1. Do not change the `pom.xml` file or dependencies, w/o communicating, as this could lead to errors for others, when they try to pull the repo to download changes.

2. The setup should work as it is, without you having to debug, as long as the `Java` versions and `Maven` versions follow the guidelines mentioned. 

---

**Team Members**
1. `Kadaru Jashwanth Reddy IMT2021095`
2. `Adithya Sunilkumar IMT2021068`

---