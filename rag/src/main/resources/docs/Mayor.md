# Libro mayor

Libro contable que recoge, cuenta por cuenta, todos los movimientos registrados en el libro diario. Permite conocer en cualquier momento el saldo de cada cuenta.

---

## Características principales

- No es obligatorio por ley, pero es imprescindible en la práctica
- Se nutre del traspaso de los asientos del libro diario
- Cada cuenta tiene su propia ficha (una página o registro)
- Permite obtener el saldo de cualquier cuenta de forma inmediata
- Base para elaborar el balance de comprobación y las cuentas anuales

---

## La ficha T (representación esquemática)

          Nombre de la cuenta
          Código: 572 — Bancos c/c
  ___________________________________
 |       DEBE       |      HABER      |
 |------------------|-----------------|
 | (1) 10 000,00    |  (3)  2 000,00  |
 | (2)  5 000,00    |  (4)    500,00  |
 |                  |                 |
 | Sumas: 15 000,00 | Sumas: 2 500,00 |
 |___________________________________|
        Saldo deudor: 12 500,00

---

## Estructura de una ficha de mayor

| Fecha      | N.º asiento | Concepto                  |    Debe   |   Haber   |      Saldo    |
|------------|-------------|---------------------------|----------:|----------:|--------------:|
| 01/01/2026 | 001         | Aportación inicial        | 10 000,00 |           | 10 000,00 D   |
| 10/01/2026 | 005         | Cobro factura cliente     |  5 000,00 |           | 15 000,00 D   |
| 15/01/2026 | 008         | Pago a proveedor          |           |  2 000,00 | 13 000,00 D   |
| 28/01/2026 | 012         | Gastos bancarios          |           |    500,00 | 12 500,00 D   |

D = saldo deudor · A = saldo acreedor

---

## Tipos de saldo

| Saldo    | Definición                        | Cuentas habituales                |
|----------|-----------------------------------|-----------------------------------|
| Deudor   | Suma del debe > suma del haber    | Activo, gastos                    |
| Acreedor | Suma del haber > suma del debe    | Pasivo, patrimonio neto, ingresos |
| Nulo     | Suma del debe = suma del haber    | Cuenta saldada (pagada o cerrada) |

---

## Relación diario → mayor

Libro diario (asiento n.º 008)
┌─────────────────────────────────────────────┐
│ 15/01/2026                                  │
│  400  Proveedores          DEBE  2 000,00   │
│    572  Bancos c/c        HABER  2 000,00   │
└─────────────────────────────────────────────┘
          ↙                       ↘
  Mayor cta. 400             Mayor cta. 572
  (cargo 2 000)              (abono 2 000)

Cada línea de un asiento genera un apunte en el mayor de la cuenta correspondiente.

---

## Cuentas habituales y su saldo normal

| Grupo PGC | Tipo de cuenta        | Saldo normal |
|-----------|-----------------------|--------------|
| 1         | Financiación básica   | Acreedor     |
| 2         | Activo no corriente   | Deudor       |
| 3         | Existencias           | Deudor       |
| 4         | Acreedores / deudores | Ambos        |
| 5         | Cuentas financieras   | Ambos        |
| 6         | Gastos                | Deudor       |
| 7         | Ingresos              | Acreedor     |

---

## Mayor y balance de comprobación

El balance de sumas y saldos se obtiene listando todas las fichas del mayor:

| Cuenta | Denominación           | Sumas debe  | Sumas haber | Saldo deudor | Saldo acreedor |
|--------|------------------------|------------:|------------:|-------------:|---------------:|
| 100    | Capital social         |           — |  10 000,00  |            — |     10 000,00  |
| 400    | Proveedores            |   2 000,00  |   6 050,00  |            — |      4 050,00  |
| 472    | H.P. IVA soportado     |   1 050,00  |           — |   1 050,00   |             —  |
| 572    | Bancos c/c             |  10 000,00  |   2 000,00  |   8 000,00   |             —  |
| 600    | Compras mercaderías    |   5 000,00  |           — |   5 000,00   |             —  |
| Σ      |                        | 18 050,00   |  18 050,00  |  14 050,00   |     14 050,00  |

Las sumas del debe deben igualar las del haber, y los saldos deudores deben igualar los acreedores.

---

## Normativa aplicable (España)

- Código de Comercio, arts. 25-33 (no obligatorio pero recomendado)
- Plan General Contable (RD 1514/2007)
- PGC Pymes (RD 1515/2007)