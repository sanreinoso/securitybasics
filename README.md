# Santi Reinoso - Spring Security Implementations

## Descripción
Este repositorio contiene ejemplos y diferentes implementaciones de Spring Security que cubren varios mecanismos de autenticación y autorización, incluyendo:
- **Basic Auth**
- **JWT (JSON Web Tokens)**
- **OAuth 2.0**
- **OpenID Connect**

El objetivo es proporcionar una referencia clara y bien estructurada para implementar y utilizar estos mecanismos de seguridad en aplicaciones basadas en Spring Boot.

---

## Contenidos
El repositorio está dividido en diferentes ramas, cada una dedicada a una implementación específica de Spring Security:

- **`basic-auth`**: Implementación de autenticación básica usando credenciales (nombre de usuario y contraseña).
- **`jwt-auth`**: Implementación de autenticación utilizando JWT para manejar tokens seguros.
- **`oauth2-auth`**: Integración con OAuth 2.0 para autenticación mediante servidores de autorización externos (como Google, GitHub, etc.).
- **`openid-connect-auth`**: Implementación basada en OpenID Connect para una capa de autenticación sobre OAuth 2.0.

---

## Requisitos Previos
Para poder ejecutar los ejemplos y proyectos en este repositorio, necesitarás:

- **Java 17** o superior
- **Maven** o **Gradle** (dependiendo del proyecto)
- **Spring Boot 3.3+**
- Una cuenta en un proveedor OAuth2.0 o OpenID Connect (para los ejemplos relevantes)

---

## Instalación y Ejecución

### Clonar el repositorio
```bash
git clone https://github.com/sanreinoso/securitybasics.git
cd nombre-del-repositorio
