# Proyecto final de automatización OrangeHRM


# ESTUDIANTES: SERGIO JUAN TICONA MAMANI
#              DIANA VEIZAGA ZARATE
# DOCENTE: Lopez Fuentes Juan David 
# MODULO : AUTOMATIZACION

Este proyecto automatiza un caso de negocio completo en la aplicación pública OrangeHRM.

El objetivo es comprobar que un administrador puede iniciar sesión, ingresar al módulo de empleados, registrar personal nuevo, buscarlo posteriormente y confirmar que aparece en el listado de resultados.

Sitio utilizado: https://opensource-demo.orangehrmlive.com/

Las credenciales administrativas se encuentran publicadas en la pantalla de inicio de sesión.

## Caso de negocio

La prueba realiza el siguiente recorrido:

1. Abre OrangeHRM.
2. Inicia sesión como administrador.
3. Ingresa al módulo PIM.
4. Abre el formulario para agregar un empleado.
5. Completa los datos personales.
6. Activa la creación de datos de acceso.
7. Completa el usuario, contraseña, confirmación y estado.
8. Guarda el empleado.
9. Ingresa al listado de empleados.
10. Busca al empleado creado.
11. Comprueba que aparece en la grilla de resultados.

## Flujo detallado de las pruebas

La suite organiza la ejecución por navegador y por empleado. Cada combinación se ejecuta de manera independiente, con su propia apertura y cierre del navegador.

### Preparación de cada ejecución

Antes de comenzar el caso de negocio:

1. La suite indica qué navegador debe utilizarse.
2. Se inicia una ventana nueva del navegador seleccionado.
3. La ventana se maximiza.
4. Se abre la página de OrangeHRM.
5. Se obtiene del archivo externo el empleado que corresponde procesar.
6. Se genera una parte única para el nombre y el usuario.

### Inicio de sesión

1. Se espera que la pantalla de acceso esté disponible.
2. Se escribe el usuario administrador.
3. Se escribe la contraseña.
4. Se presiona el botón de ingreso.
5. Se espera que la aplicación muestre el Dashboard.

### Ingreso al módulo PIM

1. Desde el Dashboard se selecciona PIM.
2. Se espera que aparezca Employee Information.
3. Se selecciona la opción para agregar un nuevo empleado.

### Creación del empleado

1. Se completa el nombre único.
2. Se completa el segundo nombre.
3. Se completa el apellido.
4. Se reemplaza el identificador automático por el identificador del archivo de datos.
5. Se espera que termine la carga del formulario.
6. Se activa Create Login Details.
7. Se comprueba que aparezcan los campos de acceso.
8. Se completa el usuario único.
9. Se completa la contraseña.
10. Se confirma la contraseña.
11. Se selecciona el estado indicado para el empleado.
12. Se guarda el registro.
13. Se espera la confirmación visual de que el empleado fue creado.

### Ingreso a Employee List

1. Se selecciona Employee List.
2. Se espera que aparezca Employee Information.
3. La pantalla baja aproximadamente 350 píxeles.
4. Se muestra de forma más clara la zona de búsqueda y resultados.

### Búsqueda del empleado

1. Se escribe el nombre único utilizado durante la creación.
2. Se espera que aparezca una sugerencia coincidente.
3. Se selecciona la sugerencia.
4. Se ejecuta la búsqueda.
5. Se espera que la grilla termine de actualizarse.
6. Se realiza una pausa visual de dos segundos.

### Validación final

1. Se revisan las filas de la grilla.
2. Se comprueba que una fila contenga el nombre único esperado.
3. Si el empleado aparece, la ejecución se considera correcta y muestra OK.
4. Si el empleado no aparece o sucede una excepción, muestra ERROR y conserva el detalle original del fallo.

### Cierre de cada ejecución

Al finalizar, tanto si el resultado es correcto como si ocurre un error, el navegador se cierra. La siguiente combinación empieza en una ventana nueva y no reutiliza la sesión anterior.

### Orden completo

El ciclo completo se desarrolla así:

1. Chrome crea, busca y valida a Sergio; después cierra Chrome.
2. Chrome crea, busca y valida a Diana; después cierra Chrome.
3. Una vez terminado todo Chrome, Firefox crea, busca y valida a Sergio; después cierra Firefox.
4. Firefox crea, busca y valida a Diana; después cierra Firefox.

De esta manera, el mismo caso de negocio se comprueba cuatro veces sin duplicar la prueba y sin ejecutar navegadores simultáneamente.

## Alcance

La automatización incluye:

- Nombre.
- Segundo nombre.
- Apellido.
- Identificador del empleado.
- Creación de datos de acceso.
- Nombre de usuario.
- Contraseña.
- Confirmación de contraseña.
- Estado habilitado o deshabilitado.
- Guardado del empleado.
- Búsqueda del empleado.
- Validación en la grilla de resultados.

La automatización no incluye:

- Modificación posterior de datos personales.
- Información de contacto.
- Cargo.
- Salario.
- Dependencias.
- Fotografía.
- Eliminación de empleados.

Después de guardar, la prueba únicamente confirma que el alta terminó correctamente. No completa información adicional del empleado.

## Organización

El proyecto utiliza Page Object Model para mantener separadas las responsabilidades.

Cada pantalla contiene sus propios elementos y acciones. La prueba principal se limita a describir el recorrido del caso de negocio y contiene la validación final.

La prueba no contiene localizadores ni búsquedas directas de elementos. Las validaciones no se encuentran dentro de las páginas.

## Datos de prueba

Los datos se cargan desde un archivo externo ubicado en los recursos del proyecto.

El archivo contiene dos empleados:

### Empleado 1

- Nombre: SERGIO.
- Segundo nombre: TICONA.
- Apellido: MAMANI.
- Identificador: 2002.
- Usuario base: Sergiot.
- Contraseña: sergio123.
- Estado: Enabled.

### Empleado 2

- Nombre: DIANA.
- Segundo nombre: VEIZAGA.
- Apellido: ZARATE.
- Identificador: 2001.
- Usuario base: DianaZ.
- Contraseña: diana123.
- Estado: Enabled.

Los dos registros alimentan la misma prueba mediante un proveedor de datos. No existe una prueba independiente para Sergio y otra para Diana.

## Datos únicos

El nombre y el usuario definidos en el archivo son valores base.

Durante cada ejecución se genera una parte única y corta. Esa parte se agrega al nombre y al usuario para reducir conflictos con registros creados anteriormente en la aplicación pública.

El mismo nombre generado se utiliza durante la creación, búsqueda y validación.

## Navegadores

El caso de negocio se ejecuta en:

- Google Chrome.
- Mozilla Firefox.

La ejecución es secuencial. Los navegadores no se ejecutan al mismo tiempo.

El orden es:

1. Chrome con Sergio.
2. Chrome con Diana.
3. Firefox con Sergio.
4. Firefox con Diana.

Primero terminan todas las ejecuciones de Chrome. Después comienza Firefox.

Ambos navegadores se abren con la ventana maximizada.

## Cantidad de ejecuciones

Existe un solo caso de prueba activo.

La combinación de dos empleados y dos navegadores produce cuatro ejecuciones reales.

La prueba de login separada se conserva únicamente como referencia y permanece deshabilitada, porque el inicio de sesión ya forma parte del caso principal.

## Sincronización

La automatización utiliza esperas explícitas para trabajar con los elementos dinámicos de OrangeHRM.

No utiliza esperas implícitas, pausas bloqueantes, reintentos automáticos ni capas adicionales para administrar el navegador.

Las esperas funcionales se encuentran dentro de las páginas correspondientes.

## Presentación durante la ejecución

La ejecución contiene pausas visuales para que el recorrido pueda observarse con claridad.

Se realiza una pausa breve después de:

- iniciar sesión;
- ingresar a PIM;
- abrir el formulario de empleado;
- crear el empleado;
- ingresar al listado.

Después de buscar al empleado, la pausa es de dos segundos antes de validar el resultado.

Estas pausas son únicamente visuales y no reemplazan las esperas utilizadas para sincronizar la aplicación.

Cuando Employee List ya está visible, la página baja aproximadamente 350 píxeles para mostrar mejor la zona de búsqueda y resultados.

## Registro de ejecución

Durante la prueba se informa:

- navegador seleccionado;
- inicio correcto del navegador;
- apertura de la aplicación;
- empleado procesado;
- inicio de sesión;
- ingreso a PIM;
- creación del empleado;
- búsqueda;
- validación en la grilla;
- cierre del navegador.

Cuando una ejecución termina correctamente se muestra OK.

Cuando ocurre un problema se muestra ERROR. El error original no se oculta y la herramienta de pruebas conserva el resultado fallido junto con su detalle.

## Ejecución del proyecto

El proyecto se ejecuta con Maven desde la carpeta principal mediante la operación clean test.

La suite está conectada con Maven, por lo que no depende del entorno de desarrollo ni necesita iniciarse manualmente desde el IDE.

Antes de ejecutar se requiere:

- Java 11 o superior.
- Maven.
- Google Chrome.
- Mozilla Firefox.
- Conexión a Internet.

## Resultado esperado

Una ejecución completamente satisfactoria debe mostrar:

- Cuatro pruebas ejecutadas.
- Cero fallos.
- Cero errores.
- Cero pruebas omitidas.
- Construcción exitosa.

## Reportes

Después de la ejecución, Maven y TestNG generan reportes dentro de la carpeta de resultados del proyecto.

Los reportes permiten revisar:

- cantidad total de ejecuciones;
- pruebas aprobadas;
- pruebas fallidas;
- pruebas omitidas;
- duración;
- mensajes de error;
- detalle de las excepciones.

Cada nueva limpieza elimina los resultados temporales anteriores antes de comenzar otra ejecución.

## Consideraciones

OrangeHRM es una aplicación pública utilizada por muchas personas. Sus datos pueden cambiar o reiniciarse sin aviso.

La interfaz también puede actualizar sus elementos mientras se realiza una búsqueda. Si ocurre una modificación inesperada de la página, la ejecución informa el error real y queda registrada como fallida.

Los nombres y usuarios generados reducen los conflictos entre ejecuciones. Los identificadores de empleado se toman directamente del archivo de datos definido para el proyecto.

## Resumen final

El proyecto cuenta con:

- un solo caso de negocio activo;
- Page Object Model;
- datos externos;
- dos empleados;
- nombres y usuarios únicos;
- creación completa del empleado;
- búsqueda y validación en resultados;
- ejecución en Chrome y Firefox;
- orden secuencial;
- suite independiente del IDE;
- esperas explícitas;
- pausas visuales;
- desplazamiento de la pantalla;
- registro de pasos y resultados;
- conservación de los errores reales.
#   A u t o m a t i z a c i o n 
 
 