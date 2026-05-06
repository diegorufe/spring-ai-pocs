---
name: geocoding
description: MUST be called when the user provides a city name and you need to convert it into coordinates using Open-Meteo Geocoding API.
---

# Weather Geocoding Skill

## Action Required
Use the Open-Meteo Geocoding API to convert a location name (e.g. city, town, region) into latitude and longitude coordinates.

This step is REQUIRED before calling any weather forecast API if coordinates are not already available.

---

## API Endpoint
https://geocoding-api.open-meteo.com/v1/search

---

## Rules
- Use always tool WebFetch for call api for find coordinates

## Example Request
GET:
https://geocoding-api.open-meteo.com/v1/search?name=Valencia&count=1&language=en

---

## Input
- name: string (city or place name provided by the user)
- count: optional integer (default 1)
- language: optional string (default "en")

---

## Output (normalized)
Return ONLY the best match in this format:

```json
{
  "name": "Test",
  "latitude": 39.47,
  "longitude": -0.38,
  "country": "Spain",
  "timezone": "Europe/Madrid"
}