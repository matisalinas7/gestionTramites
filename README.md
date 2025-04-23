# 📋 Sistema de Gestión de Trámites
Aplicación web para registrar, administrar y hacer seguimiento del estado de trámites en una empresa de servicios. Proyecto grupal desarrollado en el marco de la materia Diseño de Sistemas (3er año).

---

## ⚙️ Tecnologías utilizadas
### 💻 Frontend
- JSF
- PrimeFaces
- AdminFaces

### 🧠 Backend
- Java
- Jakarta EE 9.1 (Web API)
- JPA (Hibernate)

---
## 📈 Diagramas importantes del sistema

### Diagrama de Casos de Uso
[Ver Diagrama de Casos de Uso (PDF)](docs/diagrams/dcu.pdf)

### Diagrama de Clases
[Ver Diagrama de Clases (PDF)](docs/diagrams/dc.pdf)

---
## 📸 Capturas del sistema

### Login
Pantalla de acceso al sistema con validación de usuario.
![Login](docs/caps/login.png)

### Menú Principal
Pantalla inicial luego del login, con acceso a las distintas funcionalidades del sistema.
![Menú Principal](docs/caps/menuPrincipal.png)

### Alta de Trámite
Formulario para registrar un nuevo trámite en el sistema (de forma presencial).
![Alta de Trámite](docs/caps/altaTramite.png)

### Lista de Trámites
Listado de trámites existentes con opciones de filtrado y acciones.
![Lista de Trámites](docs/caps/listaTramites.png)

### Detalle de Trámite
Visualización detallada de un trámite seleccionado.
![Detalle de Trámite](docs/caps/detalleTramite.png)

### Anular Trámite
Opción para anular un trámite.
![Anular Trámite](docs/caps/anularTramite.png)

### Agregar Documentación
Sección para adjuntar documentación a un trámite.
![Agregar Documentación](docs/caps/agregarDocumentacion.png)

### ABM Estado Trámite
Interfaz de administración de estados posibles para los trámites (alta, baja y modificación).
![ABM Estado Trámite](docs/caps/abmET.png)
### ABM Estado Trámite - Vista 2
![ABM Estado Trámite 2](docs/caps/abmET2.png)

> 🧩 Las secciones mostradas en estas capturas corresponden a las funcionalidades que desarrollé junto a un compañero dentro del proyecto grupal.
---
## 🚀 Cómo ejecutar el proyecto
### 📦 Requisitos previos
- Java 11 o superior
- Servidor de aplicaciones: Apache TomEE MicroProfile 9.1.3
- MySQL 8.x
- Maven 3.x

### 🗃️ Opción rápida: base de datos precargada (Windows)
Este proyecto incluye un script para importar automáticamente una base de datos con datos de prueba.
1. Asegurate de tener MySQL instalado y agregado al PATH.
2. Cloná este repositorio y ubicáte en la raíz del proyecto.
3. Ejecutá el script desde una terminal (CMD): scripts/bdCompleta.bat
4. Verificá y configurá tus credenciales de conexión en el archivo: src/main/resources/hibernate.cfg.xml
5. Asegurate de que esté configurado el runtime Apache TomEE 9.1.3.
6. Ejecutá la aplicación desde el IDE.

 ---
