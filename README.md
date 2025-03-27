# PARCIAL 2doTERCIO CVDS

## DISEÑO



## Explicación
### Se implementaron las siguientes clases en Model:

### Purchase: Clase diseñada para las compras que el usuario agregue
### Transactional: Esta clase esta diseñada para ser el retorno al momento de que el usuario salve o cree la compra, Transactional guarda los datos relevantes de la compra que serán mostrados en pantalla
### Product: Clase para definir atributos básicos del producto: nombre, precio y cantidad, necesaria para poder calcular si la cantidad total de la compra ingresada con el usuario coincide con la cantidad de productos comprados por el precio de cada uno.


### Se implementaron los siguientes paquetes:

### config: Conexiones
### controller: Creación de los endpoints para conectar con Frontend
### model: Clases relevantes dentro del sistema, se agrego dentro de esta DTO para proteger los objetos
### repository: Se creo una interfaz que servirá para conectar con MongoDB
### service: Contiene la lógica de negocio


### Métodos de la API
### savePurchase: Este metodo guarda la compra sea válida o no, y retorna una transacción, la cual será mostrada en el front
### loadPurchaseByUserId: Este método retorna todas las compras de un usuario específico




