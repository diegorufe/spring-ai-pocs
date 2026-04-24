# Spring AI pocs

Pruebas de concepto de formas de uso de spring con sus módulos de spring AI

Documentación de conceptos básicos:

[Conceptos básicos](https://docs.spring.io/spring-ai/reference/concepts.html)

Para ampliar conceptos básicos. 

En Spring AI, los mensajes estructuran la conversación con modelos de lenguaje (chat-based). Cada mensaje tiene un rol específico dentro del diálogo.

## Tipos de mensajes

```java
public enum MessageType {
    USER("user"),
    ASSISTANT("assistant"),
    SYSTEM("system"),
    TOOL("tool");
}
```

### 1. System Message
Define el comportamiento global del modelo. Sirve para darle contexto o instrucciones generales.

**Ejemplo:**
"Eres un asistente experto en programación Java."

---

### 2. User Message
Es el mensaje que envía el usuario.

**Ejemplo:**
"¿Cómo hago una API REST con Spring Boot?"

---

### 3. Assistant Message
Es la respuesta generada por el modelo.

**Ejemplo:**
"Puedes crear una API REST usando @RestController..."

---

### 4. Function / Tool Message
Se usa cuando el modelo interactúa con funciones externas o herramientas.

**Ejemplo:**
Resultado de una llamada a una API o función.

---

### 5. Context / History Messages
Representa el historial de la conversación. Permite mantener el contexto entre interacciones.

---

## Ejemplo en código (Spring AI)

```java
List<Message> messages = List.of(
    new SystemMessage("Eres un asistente útil"),
    new UserMessage("¿Qué es Spring AI?")
);
```



## Configuración 

Documentación de configuración y dependencias https://docs.spring.io/spring-ai/reference/getting-started.html

## Casos de uso:

- [Chat uso básico](chatbasics/Readme.md)
- [Chat memoria](chatmemory/Readme.md)
- [MCP](mcp/Readme.md)
- [Vector databases] WIP
- [RAG] WIP
- [Spring ai agents] WIP




