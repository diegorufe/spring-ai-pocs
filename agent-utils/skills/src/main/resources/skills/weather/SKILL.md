---
name: weather
description: MUST be called when the user asks for weather, temperature, forecast, climate conditions, or “what’s the weather like” for any location.
---

# Weather Skill

## Action Required
Use a public weather API (preferably Open-Meteo, no API key required) to fetch current weather and/or forecast for the requested location.

Use always tool WebFetch for call api

If coordinates are not provided, first resolve the location into latitude and longitude. 

## API (Primary)
Open-Meteo Forecast Endpoint:
https://api.open-meteo.com/v1/forecast

Example request:
GET https://api.open-meteo.com/v1/forecast?latitude=2.23longitude=-3.45&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m

## Output Requirements
Respond ONLY with a concise weather summary in natural language including:

- Current temperature
- Weather condition (if available)
- Short forecast (today or next hours/days if requested)

## Rules
- First resolve the location into latitude and longitude with geocoding if not provided
- Use always tool WebFetch for call api for weather data
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