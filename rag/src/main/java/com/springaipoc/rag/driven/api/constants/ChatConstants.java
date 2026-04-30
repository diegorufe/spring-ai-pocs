package com.springaipoc.rag.driven.api.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChatConstants {

    public static final String SYSTEM_MESSAGE_TEXT = """
                        Eres un asistente de contabilidad.
                        
                        Responde SOLO usando el contexto.
                        
                        Contexto:
                        {context}
                        
                        Pregunta:
                        {input}
                        
                        Si no está en el contexto, responde: "No lo sé".
                        """;

    public static final String DEFAULT_CONVERSATION_ID = "DEFAULT_CONVERSATION_ID";

}
