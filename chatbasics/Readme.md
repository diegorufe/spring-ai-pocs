# Chat uso básico

[Documentación de chat client API ](https://docs.spring.io/spring-ai/reference/api/chatclient.html).

Básicamente con chat client API tenemos una forma sencilla de interactuar con nuestros modelos y enviar mensajes al modelo al que estemos conectado y recibir la respuesta. 

Podemos definir un chatClient con opción por defecto ya de sistema para indicarle que se centre en una tarea 

```java
 ChatClient
  .builder(chatModel)
  .defaultSystem("Eres un asistente técnico de programación en JAVA. Responde en español, se conciso y resuelve solo preguntas de JAVA")
  .build();
```

Podemos trabajar de 2 formas: 

- Sincrono, se realiza la llamada y se espera la respuesta completa. 

``` java
String output = chatClient.prompt()
    .user("Tell me a joke")
    .call()
    .content();
```

- Streaming (SSE), se realiza la llamada y se van recogiendo los tokens que devuelve el modelo hasta que termina. En este modo no hay por que bloquear, podemos llamar sin bloquear desde rest controller hasta adaptador de modelo y asi el destino, por ejemplo front puede ir pintando datos. A tener en cuenta es una conexión HTTP que no se cierra, tenerlo en cuenta para posibles cortes originados por envoy o similares.

```java
Flux<String> output = chatClient.prompt()
    .user("Tell me a joke")
    .stream()
    .content();
```


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

## Memoria

[Documentación de memoria ](https://docs.spring.io/spring-ai/reference/api/chat-memory.html)

En este caso no vamos a entrar en profundidad en la memoria, pero en base al advisor vamos a utilizar la de por defecto que se almacena en local, sin guardar en base de datos y con la configuración por defecto del número máximo de mensajes a almacenar.

Para usar la memoria indicamos en los interceptores, que queremos interceptar la conversación y le indicamos el indentificador para almacenar en memoria. 

Lo mas común es que sea por un id de sesión o similar, pero para el ejemplo vamos a tener un id global de aplicación

```java
    this.chatClient
        .prompt()
        .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, "TEST"))
        .user(chatFilter.getInput())
```



# Ejemplo

Ejemplo básico de como utilizar el chat de forma sencilla con chat client y memoria en local.
