package com.springaipoc.rag.driven.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatConstants {

    public static final String SYSTEM_MESSAGE_TEXT = """
            Eres un asistente de contabilidad.
            Se te proporcionara información de contabilidad en el contexto
            
            Reglas:
            1. No te inventes nada
            2. Sino encuenctras información en los datos de contexto, indicaba que no sabes la respuesta
            """;

    public static final String DEFAULT_CONVERSATION_ID = "DEFAULT_CONVERSATION_ID";

}
