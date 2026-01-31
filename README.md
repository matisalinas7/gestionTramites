# 🏛️ Enterprise Procedure Management System

A robust web application designed for the administration and tracking of **administrative procedures and workflows**.
This application was developed as the Capstone Project for the "Systems Design" course, focusing on a strict Model-Driven Development (MDD) approach.

> **Note:** The project prioritizes architectural fidelity, ensuring that every implementation detail in Java mirrors the UML Sequence Diagrams defined during the design phase.

---

## ⚙️ Tech Stack & Architecture

### 💻 Frontend (Server-Side Rendering)
- **Framework:** JSF
- **Components:** PrimeFaces & AdminFaces for responsive enterprise UI.

### 🧠 Backend
- **Language:** Java.
- **Standard:** Jakarta EE 9.1 (Web API).
- **ORM:** Hibernate (JPA) for persistence.
- **Database:** MySQL 8.x.

---

## 📐 Engineering: Design-to-Code Fidelity

## 📐 Engineering: Design-to-Code Fidelity

The core value of this project is the exact correspondence between the **UML Sequence Diagrams** and the **Java Business Logic**.

Below is a **concise demonstration** using the **"Load Status Combo" (Cargar Estados)** flow. While this represents one of the **simplest interactions** in the system, it clearly illustrates how the *Service Layer* strictly adheres to the design patterns defined in the architecture.

**1. The Blueprint (Sequence Diagram Fragment)**
> <img src="docs/diagrams/fidelity_zoom.png" alt="Sequence Diagram Logic" width="100%">

**2. The Implementation (Java - Experto Layer)**
```java
public class ExpertoRegistrarTramite {

    private Cliente clienteEncontrado;
    private TipoTramite tipoTramiteEncontrado;
    private Tramite tramiteElegido;

    // mostrarComboEstados(): List<DTOEstadoTramite>
    public List<DTOEstadoTramite> mostrarComboEstados() {

        FachadaPersistencia.getInstance().iniciarTransaccion();

        List objetoList = FachadaPersistencia.getInstance().buscar("EstadoTramite", null);

        List<DTOEstadoTramite> estadosTramite = new ArrayList<>();
        for (Object x : objetoList) {
            EstadoTramite estadoTramite = (EstadoTramite) x;
            DTOEstadoTramite dtoEstadoTramite = new DTOEstadoTramite();

            dtoEstadoTramite.setNombreEstado(estadoTramite.getNombreEstadoTramite());
            estadosTramite.add(dtoEstadoTramite);
        }
        FachadaPersistencia.getInstance().finalizarTransaccion();
        return estadosTramite;
    }
```

---

## 📈 Documentation
The system is fully documented following UML standards:

###  [📄 Use Case Diagram (PDF)](docs/diagrams/dcu.pdf)
###  [📄 Domain Class Diagram (PDF)](docs/diagrams/dc.pdf)
###  [📐 Sequence Diagram: Register Procedure (PDF)](docs/diagrams/secuencia_registrar.pdf)
  > *Full A3 diagram detailing the interaction between the Interface, Controller, Service, and Persistence layers.*
###  [📝 Use Case Specification: Flow of Events (PDF)](docs/diagrams/flujo_sucesos_registrar.pdf)
  > *Detailed functional requirements and step-by-step behavior for the "Register Procedure" use case.*

---

## 📸 System Modules

### Login
Access screen with user validation and security integration.
![Login](docs/caps/login.png)

### Main Menu
Dashboard with role-based access to different system modules.
![Menú Principal](docs/caps/menuPrincipal.png)

### Procedure Registration (Alta)
Form to register a new procedure in the system.
![Alta de Trámite](docs/caps/altaTramite.png)

### Procedure Tracking List
List of existing procedures with filtering options and action buttons.
![Lista de Trámites](docs/caps/listaTramites.png)

### Detail View
Detailed audit view of a selected procedure showing full history.
![Detalle de Trámite](docs/caps/detalleTramite.png)

### Annulment Flow
Workflow option to annul an existing procedure.
![Anular Trámite](docs/caps/anularTramite.png)

### Documentation Attachment
Module to attach digital documentation to a procedure.
![Agregar Documentación](docs/caps/agregarDocumentacion.png)

### Status Administration (ABM)
Interface to manage the state machine configuration (Create, Update, Delete states).
![ABM Estado Trámite](docs/caps/abmET.png)

### Status Administration (View 2)
![ABM Estado Trámite 2](docs/caps/abmET2.png)

---

## 🚀 Setup & Execution
###📦 Prerequisites
- Java 11 or higher.
- App Server: Apache TomEE MicroProfile 9.1.3.
- Database: MySQL 8.x.
- Build Tool: Maven 3.x.

## 🗃️ Quick Start (Windows)
The project includes a script to automatically provision the database with seed data.
1. Ensure MySQL is installed and added to your system PATH.
2. Clone the repository and navigate to the root folder.
3. Run the provision script in CMD: scripts/bdCompleta.bat
4. Verify database credentials in: src/main/resources/hibernate.cfg.xml.
5. Configure Apache TomEE 9.1.3 in your IDE.
6. Deploy and Run.

---
