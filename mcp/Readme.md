# MCP 

[Documetanción spring AI MCP](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-overview.html)

Conceptos a tener en cuenta: 

- [Cliente](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-client-boot-starter-docs.html): es quien va hacer uso de los servidores MCP que ofrence las tools

- [Servidor](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html): son los tools o resources con los que queremos interactuar

- [Anotaciones](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-annotations-overview.html) Anotaciones para simplificar la creación de clientes MCP


> ⚠️ **Warning:** Si en el lado del cliente, le indicas un modelo que sea malo, no es capaz de determinar bien las tools y las estará utilizando todo el rato