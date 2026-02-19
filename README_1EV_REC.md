## **Ejercicio 1 — “Filtro de letras y concatenación”**

**Objetivo:** Scanner + char[] + recorrer + filtrar + concatenar

1. Pide al usuario un número **N** (entre 3 y 20).
2. Crea un array char[] letras = new char[N].
3. Con Scanner, pide **N** letras (una a una). Guarda cada letra en el array.
4. Recorre el array y construye un String con **solo las letras que NO sean vocales** (a,e,i,o,u en mayúscula o minúscula).
5. Muestra:
    - el array original (recorriéndolo)
    - el string final con consonantes
    - El número total de vocales.

---

## **Ejercicio 2 — “Notas y media con estadística básica”**

**Objetivo:** Scanner + double[] + media + contar + mínimo/máximo

1. Pide al usuario un número **N** (mínimo 5).
2. Crea un array double[] notas = new double[N].
3. Pide **N** notas por consola (0 a 10).
    - Si una nota está fuera de rango, vuelve a pedirla.
4. Calcula y muestra:
    - **media** de notas
    - cuántas notas son **>= 5** (aprobados)
    - cuántas notas son **< 5** (suspensos)
    - **nota máxima** y **nota mínima**
    - Nota media solo de aprobados.

---

## **Ejercicio 3 — “Temperaturas: máximo, posición y búsqueda”**

**Objetivo:** Scanner + int[] + máximo + índice + búsqueda

1. Pide al usuario cuántos días quiere registrar (**N**, por ejemplo 7–31).
2. Crea un array int[] temps = new int[N].
3. Pide la temperatura de cada día (enteros).
4. Calcula y muestra:
    - la **temperatura máxima** y el **día (posición)** en el que ocurrió (si se repite, muestra el **primer** día)
    - la **temperatura media** (puede ser double)
5. Luego pide al usuario una temperatura objetivo T y:
    - indica si existe en el array
    - si existe, muestra **todas** las posiciones donde aparece
6. Cuenta cuántos días están por encima de la media.