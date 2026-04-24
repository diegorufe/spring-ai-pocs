# Chat memory

[Documentación de chat memory](https://docs.spring.io/spring-ai/reference/api/chat-memory.html).

Básicamente, el chat memory es una clase que se encarga de almacenar el historial de la conversación. Esto es útil para que el modelo de lenguaje pueda tener contexto sobre lo que se ha hablado anteriormente y pueda generar respuestas más coherentes.

Tenemos tipos de base de datos relacionales tipo PostgreSQL, MySQL, etc. y no relacionales tipo MongoDB, Cassandra, Neo4j

En este ejemplo vamos a utilizar una base de datos relacional, en este caso PostgreSQL, para almacenar el historial de la conversación.

Configuración:

```java
      MessageWindowChatMemory.builder()
        .chatMemoryRepository(chatMemoryRepository)
        .maxMessages(10)
        .build();
```

En **maxMessages** cuando se llega a ese limite por id, lo que hace es ir borrando viejos y añadiendo nuevos.

Si realizamos un clear del chat memory por id de conversación borrará todos los registros almacenados para ese id de conversación. 

Tener en cuenta que es un histórico de memoria de chat, no un histórico de mensajes de usuario, para ese proposito igual lo ideal es añadir un interceptor custom 
que no tenga en cuenta configuraciones de limites y que no vaya asociado a la memoria del chat

## Advisors

Son interceptores que podemos configurar para interactuar y modificar las intereaciones con los mensajes. Ejemplos de uso para guardar el histórico de mensajes, o para pintar log.

Ejemplo de uso de memoria:

```java
 ChatClient.builder(chatModel)
            .defaultAdvisors(
                  MessageChatMemoryAdvisor
                        .builder(this.chatMemory)
                        .build()
             )
             .build()
```

## Ejemplo

En la carpeta devops se encuentra un fichero de configuración para desplegar una base de datos PostgreSQL utilizando Docker Compose.
