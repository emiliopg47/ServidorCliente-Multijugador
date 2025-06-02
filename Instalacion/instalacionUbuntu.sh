#!/bin/bash

# === CONFIGURACIÓN ===
JAR_ORIGEN="Cliente-jfx-all.jar"
ICONO_ORIGEN="icon.png"

NOMBRE_APP="ClienteApp"
JAR_DEST="$HOME/.local/bin/$JAR_ORIGEN"
ICONO_DEST="$HOME/.local/share/icons/$ICONO_ORIGEN"
DESKTOP_FILE="$HOME/.local/share/applications/$NOMBRE_APP.desktop"

# === CREAR DIRECTORIOS SI NO EXISTEN ===
mkdir -p "$HOME/.local/bin"
mkdir -p "$HOME/.local/share/icons"
mkdir -p "$HOME/.local/share/applications"

# === COPIAR ARCHIVOS ===
cp "$JAR_ORIGEN" "$JAR_DEST"
cp "$ICONO_ORIGEN" "$ICONO_DEST"

# === CREAR ARCHIVO .desktop ===
cat > "$DESKTOP_FILE" <<EOL
[Desktop Entry]
Name=$NOMBRE_APP
Comment=Cliente JavaFX con icono personalizado
Exec=java -jar $JAR_DEST
Icon=$ICONO_DEST
Terminal=false
Type=Application
Categories=Utility;
EOL

# === DAR PERMISOS Y ACTUALIZAR CACHE ===
chmod +x "$DESKTOP_FILE"
update-desktop-database ~/.local/share/applications >/dev/null 2>&1

echo "✅ Instalación completada."
echo "🔍 Puedes buscar '$NOMBRE_APP' en el menú de aplicaciones y anclarlo al dock."
