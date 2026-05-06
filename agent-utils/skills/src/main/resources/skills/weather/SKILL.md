---
name: weather
description: MUST be called when the user asks for weather, temperature, forecast, climate conditions, or “what’s the weather like” for any location.
---

# Weather Skill

## Action Required
Use a public weather API (preferably Open-Meteo, no API key required) to fetch current weather and/or forecast for the requested location.

If coordinates are not provided, first resolve the location into latitude and longitude.

## API (Primary)
Open-Meteo Forecast Endpoint:
https://api.open-meteo.com/v1/forecast

Example request:
GET https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lon}&current_weather=true&hourly=temperature_2m&daily=temperature_2m_max,temperature_2m_min&timezone=auto

## Output Requirements
Respond ONLY with a concise weather summary in natural language including:

- Current temperature
- Weather condition (if available)
- Short forecast (today or next hours/days if requested)

## Rules
- Do NOT explain how the API works
- Do NOT include raw JSON or technical details
- Do NOT mention Open-Meteo unless explicitly asked
- Keep response short and user-focused
- Always prioritize current weather if no timeframe is specified

## Example Output

**ES:**
“Ahora mismo hay 22°C y cielo parcialmente nublado. Durante el día las temperaturas se mantendrán entre 19°C y 26°C.”

**EN:**
“Right now it is 22°C with partly cloudy skies. During the day, temperatures will stay between 19°C and 26°C.”