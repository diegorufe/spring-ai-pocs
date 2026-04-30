# Acerar

Parte para probar si el contexto pilla este tipo de conocimiento. 

Acerar es un concepto en contabilidad que sirve para dividir el balace de una cuenta contable 
a una fecha determinada. 

## Ejemplo:

Supongamos que tenemos la cuenta de "Caja" con el siguiente balance al 31 de diciembre de 2023:

| Fecha       | Concepto          | Debe (€) | Haber (€) | Saldo (€) |
|-------------|-------------------|----------|-----------|-----------|
| 01/12/2023  | Saldo inicial     |          |           || 5 000       |
| 05/12/2023  | Ingreso por venta | 2 000    |           || 7 000       |
| 10/12/2023  | Pago a proveedor  |          | 1 500     || 5 500       |
| 15/12/2023  | Ingreso por servicio | 1 000    |           || 6 500       |
| 20/12/2023  | Pago de gastos    |          | 500       || 6 000       |

Si queremos acerar esta cuenta al 31 de diciembre de 2023, creamos un nuevo registros de aceramiento
que refleje el saldo final de la cuenta en esa fecha. El asiento de aceramiento sería:

| Fecha       | Concepto          | Saldo (€) |
|-------------|-------------------|----------|
| 31/12/2023  | Acerar            | 6 000       |