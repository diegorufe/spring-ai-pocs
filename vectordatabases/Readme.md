# Vector databases

[Documentación Spring AI vector databases](https://docs.spring.io/spring-ai/reference/api/vectordbs.html)

Una base de datos de vectores es un sistema diseñado para almacenar embeddings (representaciones numéricas de datos como texto, imágenes o audio) y realizar búsquedas eficientes por similitud entre ellos mediante métricas matemáticas.

[Referencia a similitud entre vectores](https://docs.spring.io/spring-ai/reference/api/vectordbs/understand-vectordbs.html#vectordbs-similarity)

En esta demostración vamos a probar [PGvector](https://docs.spring.io/spring-ai/reference/api/vectordbs/pgvector.html) que es una extensión de postgres para poder tener datos vectoriales
y [Ollama Embeddigns](https://docs.spring.io/spring-ai/reference/api/embeddings/ollama-embeddings.html) para generar los vectores

---

# PGVector 

# Tipos de índices en pgvector

pgvector soporta varios tipos de índices para acelerar la búsqueda de similitud vectorial:

---

## 1. IVFFlat (Inverted File with Flat Compression)

Divide los vectores en **listas (clusters)** mediante k-means y busca solo en las más cercanas al query.

```sql
CREATE INDEX ON items USING ivfflat (embedding vector_cosine_ops)
WITH (lists = 100);
```

**Parámetros clave:**
- `lists` — número de clusters (recomendado: `rows / 1000` para hasta 1M filas)
- `ivfflat.probes` — listas a explorar en la búsqueda (más = más preciso, más lento)

```sql
SET ivfflat.probes = 10;
```

**Características:**
- Rápido de construir
- Requiere datos cargados antes de crear el índice para buenos clusters
- Búsqueda aproximada (ANN)

---

## 2. HNSW (Hierarchical Navigable Small World)

Construye un **grafo multicapa** de proximidad para navegar eficientemente hacia los vecinos más cercanos.

```sql
CREATE INDEX ON items USING hnsw (embedding vector_cosine_ops)
WITH (m = 16, ef_construction = 64);
```

**Parámetros clave:**
- `m` — número máximo de conexiones por nodo (más = mayor recall, más memoria)
- `ef_construction` — tamaño del conjunto de candidatos durante construcción (más = mejor calidad)
- `hnsw.ef_search` — candidatos explorados en búsqueda (más = más preciso)

```sql
SET hnsw.ef_search = 100;
```

**Características:**
- Mayor recall que IVFFlat
- Más lento de construir y consume más memoria
- Se puede insertar sin reconstruir el índice
- **Recomendado para producción**

---

## 3. Sin índice (Búsqueda exacta / Flat Search)

Sin ningún índice, pgvector realiza un **escaneo secuencial completo** (exact nearest neighbor).

```sql
-- Sin CREATE INDEX → búsqueda exacta
SELECT * FROM items
ORDER BY embedding <=> '[0.1, 0.2, 0.3]'
LIMIT 5;
```

**Características:**
- Resultados 100% precisos
- Escala mal con grandes volúmenes
- Útil para datasets pequeños o validación

---

## Operadores de distancia compatibles

| Operador | Distancia | ops class |
|----------|-----------|-----------|
| `<=>` | Coseno | `vector_cosine_ops` |
| `<->` | Euclidiana (L2) | `vector_l2_ops` |
| `<#>` | Producto interno (negativo) | `vector_ip_ops` |

---

## Comparativa rápida

| | IVFFlat | HNSW | Flat |
|---|---|---|---|
| **Velocidad de búsqueda** | ✅ Rápida | ✅✅ Muy rápida | ❌ Lenta |
| **Recall** | Medio | Alto | 100% |
| **Construcción** | ✅ Rápida | ❌ Lenta | — |
| **Memoria** | Baja | Alta | — |
| **Inserciones incrementales** | Degradación | ✅ Sin problema | ✅ |

---

> **Recomendación general:** usa **HNSW** si puedes permitirte la memoria y el tiempo de construcción. Usa **IVFFlat** si tienes restricciones de recursos o datasets muy grandes.

---

# Búsqueda Aproximada (ANN)

**ANN** significa **Approximate Nearest Neighbor** (Vecino más cercano aproximado).

En lugar de comparar el vector query contra **todos** los vectores de la base de datos (búsqueda exacta), ANN usa atajos inteligentes para encontrar resultados **muy cercanos al óptimo**, pero mucho más rápido.

Por defecto posgres sin indice utiliza KNN

---

## Búsqueda exacta vs aproximada

| | Exacta (KNN) | Aproximada (ANN) |
|---|---|---|
| **Resultado** | El vecino más cercano real | Un vecino muy cercano (no garantizado) |
| **Velocidad** | Lenta (escala con N) | Muy rápida |
| **Precisión** | 100% | ~95-99% (configurable) |
| **Uso práctico** | Datasets pequeños | Millones de vectores |

---

## Analogía simple

Imagina que buscas el restaurante más cercano a ti en una ciudad:

- **KNN exacto** → recorres cada calle de la ciudad midiendo distancias. Encuentras el más cercano, pero tardas mucho.
- **ANN** → miras tu barrio y los barrios vecinos. Probablemente encuentres el más cercano, y si no, uno muy parecido. En segundos.

---

## El trade-off clave: Recall

El **recall** mide qué tan buena es la aproximación:

- **Recall 100%** = búsqueda exacta
- **Recall 95%** = de los 10 vecinos reales, encuentras 9-10

Con IVFFlat puedes ajustar el recall subiendo `probes`, y con HNSW subiendo `ef_search`. A más recall, más tiempo de búsqueda.

---

## Casos de uso reales

En aplicaciones como búsqueda semántica, recomendadores o RAG, un recall del 95-99% es más que suficiente, y la ganancia en velocidad es enorme (de segundos a milisegundos).

---

# KNN — K-Nearest Neighbors (K Vecinos más Cercanos)

**KNN** es un algoritmo que, dado un punto de consulta, encuentra los **K elementos más similares** dentro de un conjunto de datos, midiendo la distancia entre vectores.

---

## La idea central

Dado un vector query **q** y un conjunto de vectores, KNN devuelve los **K vectores más cercanos** según una métrica de distancia.

```
Vectores en BD:  A, B, C, D, E, F...
Query:           q

KNN con K=3 → devuelve los 3 más cercanos a q
```

---

## Métricas de distancia

La "cercanía" depende de cómo se mide la distancia:

| Métrica | Descripción | Cuándo usarla |
|---|---|---|
| **Euclidiana (L2)** | Distancia geométrica directa | Embeddings de imágenes |
| **Coseno** | Ángulo entre vectores | Embeddings de texto (NLP) |
| **Producto interno** | Similitud por magnitud y dirección | Recomendadores |

En pgvector:

```sql
-- Distancia coseno (más común en texto)
ORDER BY embedding <=> query_vector

-- Distancia euclidiana
ORDER BY embedding <-> query_vector

-- Producto interno
ORDER BY embedding <#> query_vector
```

---

## Ejemplo práctico

Tienes una base de datos de películas representadas como vectores según su género, trama y estilo. El usuario vio *Inception* y quieres recomendar 5 similares:

```sql
SELECT titulo, genero
FROM peliculas
ORDER BY embedding <=> '[0.82, 0.14, 0.56, ...]'
LIMIT 5;  -- K = 5
```

KNN calcula la distancia de *Inception* contra **todas** las películas y devuelve las 5 más cercanas.

---

## KNN exacto vs ANN

| | KNN exacto | ANN (aproximado) |
|---|---|---|
| **Precisión** | 100% — siempre el más cercano real | ~95-99% |
| **Velocidad** | Lenta — compara contra todos | Muy rápida |
| **Escala** | Mal con millones de vectores | Bien |
| **pgvector** | Sin índice (sequential scan) | Con HNSW o IVFFlat |

---

## ¿Por qué KNN exacto es lento?

Con **N** vectores de dimensión **D**, KNN exacto tiene coste:

```
O(N × D)
```

Con 10 millones de vectores de 1536 dimensiones (OpenAI embeddings), cada búsqueda requiere **15.000 millones de operaciones**. Por eso existe ANN.

---

## KNN en pgvector

```sql
-- KNN exacto: sin índice
SELECT * FROM items
ORDER BY embedding <=> '[0.1, 0.2, 0.3]'
LIMIT 10;  -- K = 10

-- Forzar KNN exacto aunque exista un índice
SET enable_indexscan = off;
SELECT * FROM items
ORDER BY embedding <=> '[0.1, 0.2, 0.3]'
LIMIT 10;
```

---

## Cuándo usar KNN exacto

- Datasets pequeños (< 100k vectores)
- Cuando el recall del 100% es crítico (medicina, legal)
- Para validar resultados ANN en desarrollo
- Cuando la latencia no es un requisito estricto

> En producción con grandes volúmenes, se prefiere **ANN con HNSW** por su velocidad, asumiendo una pequeña pérdida de precisión.