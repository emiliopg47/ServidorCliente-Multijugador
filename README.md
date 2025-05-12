# API REST
## Login


### Request
/api/login
```json
{
  "nick": "login",
  "password": "1234"
}
```
### Response
```json
{
  "success": true,
  "message": "Conexión exitosa",
  "usuario": {
    "id": 1,
    "nick": "login",
    "password": "1234",
    "correo": "login@gmail.com",
    "fechaNac": "2003-11-05"
  }
}
```

## Register

### Request
/api/register
```json
{
  "nick": "prueba",
  "password": "1234",
  "correo": "prueba@gmail.com",
  "fechaNac": "2000-01-01"
}
```
### Response
```json
{
  "success": false,
  "message": "Ese nombre de usuario ya existe"
}
{
  "success": false,
  "message": "Ese correo ya existe"
}
{
  "success": true,
  "message": "Usuario registrado correctamente"
}
```




# WebSockets

# Funcionamiento Chat
1. El cliente inicia se conecta al apartado "chat" en el cliente.
2. El servidor recibe que un cliente se quiere conectar a una sale de chat. Si no existe ningun otro usuario en una sala de chat, se crea una nueva sala de chat y se añade al cliente a la sala. Si ya existe un usuario en la sala, se añade al cliente a la sala.
3. Cuando el cliente envia un mensaje, manda el mensaje al servidor, como ChatData.
4. El servidor recibe el mensaje y lo envía a todos los clientes de la sala de chat.
5. El cliente recibe el mensaje y lo muestra en la pantalla.

### Json ChatData
```json
{
  "type": "chat",
  "data": {
    "mensaje": "Hola",
    "nick": "Usuario1",
    "fecha": "2023-10-01:12:05"
    }
}
```

## Funcionamiento Pong
1. El cliente mueve la pala y se mansa un mensaje tipo MovePaddle con el movimiento que ha realizado (Up,Down), y la pala que ha realizado el movimiento (Left,Right).
2. El servidor recibe el mensaje y actualiza la posición de la pala en el gameState.
3. A demas el servidor estará ejecutando el LoopGame que moverá la bola y detectará colisiones.
4. Cada uno de estos loops enviará un mensaje Pong a todos los clientes con el estado del juego (gameState).
5. El cliente recibe el mensaje Pong y actualiza su estado local del juego (gameState).
6. El cliente renderiza el juego en pantalla.

### Json GameState
```json
{
  "type": "GAME_STATE",
  "data": {
    "bolaX": 0,
    "bolaY": 0,
    "palaIzquierda": 0,
    "palaDerecha": 0,
    "marcaIzquierda": 0,
    "marcaDerecha": 0
  }
}
```

### Json MovePaddle
```json
{
  "type": "MOVE_PADDLE",
  "data": {
    "direccion": "UP",
    "paddle": "Left"
  }
}
```
