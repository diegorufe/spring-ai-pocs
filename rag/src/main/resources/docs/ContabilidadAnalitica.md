# Contabilidad analítica

También llamada contabilidad de costes o contabilidad interna. Sistema de información que analiza, clasifica y distribuye los costes e ingresos de la empresa por productos, servicios, departamentos o proyectos, con el objetivo de apoyar la toma de decisiones.

---

## Diferencias con la contabilidad financiera

| Aspecto              | Contabilidad financiera          | Contabilidad analítica              |
|----------------------|----------------------------------|-------------------------------------|
| Destinatario         | Externo (inversores, hacienda)   | Interno (dirección, gestión)        |
| Obligatoriedad       | Obligatoria                      | Voluntaria                          |
| Regulación           | PGC, Código de Comercio          | Libre; cada empresa elige su modelo |
| Periodicidad         | Anual (mínimo)                   | Mensual, semanal o continua         |
| Unidad de análisis   | Empresa en su conjunto           | Producto, sección, proyecto         |
| Orientación temporal | Pasado                           | Pasado y futuro (presupuestos)      |
| Precisión            | Exacta y verificable             | Aproximada pero útil                |

---

## Clasificación de costes

### Por naturaleza

| Tipo              | Descripción                                 | Ejemplos                          |
|-------------------|---------------------------------------------|-----------------------------------|
| Materiales        | Consumo de materias primas y auxiliares     | Madera, acero, embalajes          |
| Personal          | Retribuciones y cargas sociales             | Sueldos, SS a cargo empresa       |
| Amortizaciones    | Depreciación sistemática del inmovilizado   | Maquinaria, instalaciones         |
| Servicios externos| Prestaciones de terceros                    | Alquileres, suministros, seguros  |
| Financieros       | Coste del capital ajeno                     | Intereses de préstamos            |

### Por variabilidad

| Tipo        | Comportamiento                                      | Ejemplos                        |
|-------------|-----------------------------------------------------|---------------------------------|
| Fijos       | No varían con el nivel de actividad                 | Alquiler, amortizaciones        |
| Variables   | Varían proporcionalmente con la producción          | Materias primas, energía        |
| Semifijos   | Fijos por tramos; saltan al superar un umbral       | Supervisores, turnos extra      |
| Semivariables | Tienen una parte fija y otra variable             | Suministros con cuota + consumo |

### Por asignación

| Tipo       | Descripción                                              |
|------------|----------------------------------------------------------|
| Directos   | Se asignan sin reparto al producto o sección             |
| Indirectos | Requieren una clave de reparto para su distribución      |

---

## Sistemas de costes

### Coste completo (full costing)

Imputa al producto todos los costes, tanto fijos como variables.

Coste total = Costes variables + Costes fijos

- Ventaja: refleja el coste real de cada unidad
- Inconveniente: los costes fijos distorsionan el coste unitario según el volumen

### Coste variable (direct costing)

Solo imputa al producto los costes variables. Los costes fijos se llevan directamente al resultado del periodo.

Margen de contribución = Precio de venta − Costes variables unitarios

- Ventaja: útil para decisiones a corto plazo
- Inconveniente: no refleja el coste total del producto

### Coste estándar

Utiliza costes predeterminados (normalizados) y compara con los costes reales para calcular desviaciones.

Desviación = Coste estándar − Coste real

- Ventaja: permite el control y la detección de ineficiencias
- Inconveniente: requiere un sistema de presupuestación sólido

---

## Proceso de cálculo del coste

Costes por naturaleza (materiales, personal, amortizaciones...)
        ↓
Clasificación en directos e indirectos
        ↓
Reparto primario: asignación a centros de coste
        ↓
Reparto secundario: redistribución de centros auxiliares a principales
        ↓
Imputación al producto o servicio
        ↓
Cálculo del coste unitario y margen

---

## Centros de coste

Unidades organizativas a las que se asignan costes para su posterior reparto.

| Tipo         | Descripción                                            | Ejemplos                     |
|--------------|--------------------------------------------------------|------------------------------|
| Principales  | Participan directamente en la producción               | Fabricación, montaje         |
| Auxiliares   | Prestan servicio a otros centros                       | Mantenimiento, logística     |
| De estructura| Costes generales no vinculados a la producción         | Dirección, administración    |
| De ventas    | Relacionados con la comercialización                   | Marketing, distribución      |

---

## Claves de reparto habituales

| Coste indirecto       | Clave de reparto habitual                     |
|-----------------------|-----------------------------------------------|
| Alquiler del local    | Metros cuadrados ocupados por cada sección    |
| Energía eléctrica     | Potencia instalada o horas de uso             |
| Amortización maquinaria | Horas máquina por sección                  |
| Gastos de personal    | Número de empleados o horas trabajadas        |
| Informática / sistemas| Número de usuarios o equipos por sección      |

---

## Cuenta de resultados analítica

| Concepto                                    |   Importe   |
|---------------------------------------------|------------:|
| Ingresos por ventas                         |  18 150,00  |
| − Costes variables de producción            |  -7 260,00  |
| − Costes variables de comercialización      |  -1 815,00  |
| **= Margen de contribución**                |   **9 075,00** |
| − Costes fijos de producción                |  -4 000,00  |
| − Costes fijos de estructura                |  -2 325,00  |
| **= Resultado de explotación (EBIT)**        |   **2 750,00** |
| − Gastos financieros                        |    -300,00  |
| **= Resultado antes de impuestos**          |   **2 450,00** |

---

## Punto de equilibrio (umbral de rentabilidad)

Volumen de ventas en el que los ingresos igualan a los costes totales; el beneficio es cero.

Punto de equilibrio (unidades) = Costes fijos / Margen de contribución unitario
Punto de equilibrio (euros)    = Costes fijos / (Margen de contribución / Ventas)

Ejemplo:
- Costes fijos: 6 000 €
- Precio de venta unitario: 50 €
- Coste variable unitario: 30 €
- Margen de contribución unitario: 20 €
- Punto de equilibrio: 6 000 / 20 = 300 unidades

---

## Método ABC (Activity Based Costing)

Asigna los costes indirectos a las actividades que los generan y desde estas a los productos.

Costes indirectos
        ↓
Actividades (comprar, almacenar, configurar, distribuir...)
        ↓
Inductores de coste (cost drivers)
        ↓
Productos o servicios

| Actividad           | Inductor de coste         | Ejemplo de reparto                    |
|---------------------|---------------------------|---------------------------------------|
| Gestión de pedidos  | N.º de pedidos            | 3 € por pedido procesado              |
| Control de calidad  | N.º de inspecciones       | 15 € por lote inspeccionado           |
| Configuración       | N.º de cambios de serie   | 120 € por cambio de configuración     |
| Almacenamiento      | M³ × días en almacén      | 0,5 € por m³ y día                    |

- Ventaja: mayor precisión en la imputación de indirectos
- Inconveniente: costoso de implementar y mantener

---

## Informes principales

| Informe                        | Contenido                                                   |
|--------------------------------|-------------------------------------------------------------|
| Coste de producción            | Coste unitario de cada producto o referencia                |
| Margen por producto            | Contribución de cada línea al resultado global              |
| Análisis de desviaciones       | Diferencia entre coste estándar y real                      |
| Rentabilidad por cliente       | Margen neto generado por cada cliente o segmento            |
| Rentabilidad por canal         | Comparativa entre canales de venta o distribución           |
| Presupuesto vs. real           | Control periódico de la ejecución presupuestaria            |

---

## Normativa y referencia (España)

- No existe regulación legal obligatoria para la contabilidad analítica
- AECA: Principios de Contabilidad de Gestión (documentos 1-23)
- ICAC: no regula la contabilidad interna, pero la recomienda para grupos
- IFRS / NIC: no exigen contabilidad analítica pero sí información por segmentos (NIIF 8)