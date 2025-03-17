# 🚀 **Technology Platforms Laboratory**

> 📚 *Java project using Maven and Git.*
### 🔗 **Contributors**

[![GitHub - Danylo Zherzdiev](https://img.shields.io/badge/GitHub-Danylo_Zherzdiev-181717?style=for-the-badge&logo=github)](https://github.com/mafinzyx)   [![GitHub - Danylo Lohachov](https://img.shields.io/badge/GitHub-Danylo_Lohachov-181717?style=for-the-badge&logo=github)](https://github.com/eternaki)  [![GitHub - Mikita Kasiak](https://img.shields.io/badge/GitHub-Mikita_Kasiak-181717?style=for-the-badge&logo=github)](https://github.com/Nikitaksk)

---

## 📥 **Cloning the Repository**

1. Ensure Git is installed:  

```bash
git --version
```

2. Clone the repository and navigate to the project directory:  

```bash
git clone https://github.com/mafinzyx/Technology-Platforms.git
cd Technology-Platforms
```

---

## ⚙️ **Building and Running the Project**

### 🔧 **1. Building the JAR File with Maven**

Ensure Maven is installed:  

```bash
mvn -v
```

Build the project and generate the JAR file:

```bash
mvn clean package
```

> 📂 **After building, the JAR file will be located in:**  
> `./target/Technology-Platforms-1.0-SNAPSHOT.jar`

---

### 🚀 **2. Running the Project**

To run the project, use the command:

```bash
java -jar ./target/Technology-Platforms-1.0-SNAPSHOT.jar
```

---

## 🟢 **Running the Project via IntelliJ IDEA**

1. **Open IntelliJ IDEA**  
2. Navigate to:  
   > `File` → `Open` → Select the `Technology-Platforms` directory  
3. Ensure IntelliJ recognizes the project as a Maven project. If not, import the `pom.xml` file.  
4. Build the project:  
   
```bash
mvn clean package
```

5. Run the JAR file:  

```bash
java -jar ./target/Technology-Platforms-1.0-SNAPSHOT.jar
```

---
## 📄 **LAB1 Information**

We have implemented a **text-mode application** that provides the following features:

- ✅ **Load Recursive Objects:** The application allows loading a recursive set of objects.
- 🔄 **Display Options:** Objects can be displayed with or without sorting.
- 📊 **Sorting Capabilities:**
  - **Natural Order:** Sort the objects based on their natural ordering.
  - **Custom Criterion:** Sort the objects based on an additional selected criterion.
