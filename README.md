## 📦 Descargas
- [🔽 Versiones Portables](https://github.com/emiliopg47/ServidorCliente-Multijugador/releases/tag/v1)


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
1. Una vez el usuario inicia sesion se le conecta al chat.
2. El servidor recibe que un cliente se ha conectado y le manda a todo los usuarios activos para que actualicen el numero de jugadores activos.
3. Cuando el cliente envia un mensaje manda el mensaje al servidor.
4. El servidor recibe el mensaje y lo envía a todos los usuarios conectados.
5. El cliente recibe el mensaje y actualiza la ventana del chat.

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
1. El usuario busca partida, el servidor recibe esto y situa al usuario o en una sala vacia o en una con un jugador que ya esta esperando. Una vez la sala tenga dos jugadores empezará a partir de 3 segundos.
2. El cliente mueve la pala y se manda un mensaje tipo MovePaddle con el movimiento que ha realizado (Up,Down), y la pala que ha realizado el movimiento (Left,Right).
3. El servidor recibe el mensaje y actualiza la posición del movimiento.
4. A parte de esto el servidor estará ejecutando el loop del juego que moverá la bola y detectará colisiones.
5. Cada uno de estos loops enviará un mensaje a los dos jugadores (GameState) con el estado del juego (palas,marcador,bola).
6. El cliente recibe el mensaje GameState y actualiza su estado local del juego.
7. El cliente renderiza el juego en pantalla.

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
