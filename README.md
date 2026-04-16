# Gym Tracker - APK Android 100% Offline

App para registrar tu rutina semanal de gym. Funciona sin internet, sin cuenta, sin servidor.

---

## Opción 1: Usar AHORA en el celular (sin compilar nada)

1. Pasá el archivo `index.html` a tu celular (por WhatsApp, mail, cable USB, etc.)
2. Abrilo con **Chrome** en el celular
3. En Chrome, tocá los 3 puntitos ⋮ → **"Agregar a pantalla de inicio"**
4. Listo: te queda un ícono en el celular que funciona como app

Los datos se guardan localmente en el navegador. Funciona sin internet.

---

## Opción 2: Compilar APK con Android Studio

Si querés un APK nativo para instalar:

### Requisitos
- [Android Studio](https://developer.android.com/studio) instalado en la PC

### Pasos
1. Abrí **Android Studio**
2. File → Open → seleccioná la carpeta `GymTracker`
3. Esperá a que termine el "Gradle Sync" (puede tardar unos minutos la primera vez)
4. Build → **Build Bundle(s) / APK(s)** → **Build APK(s)**
5. Cuando termine, hacé click en "locate" para encontrar el APK
6. Pasá el APK al celular e instalalo (activá "Orígenes desconocidos" en ajustes)

---

## Cómo usar la app

### 1. Cargar semana
Pegá tu rutina completa en el formato:

```
### **LUNES

1. **Press de Banca con BARRA:** 3-4 series x 6-8 reps | Peso: 17,5 kg
2. **Press Militar Sentado:** 3-4 series x 8-10 reps | Peso: Mancuernas de 17,5 kg

---

### **MARTES
...
```

Tocá **"Cargar Semana"**.

### 2. Registrar repeticiones
- Seleccioná el día en las pestañas superiores
- En cada ejercicio, completá el campo **Hecho** con las reps reales (ej: `8, 8, 7, 6`)
- Se guarda automáticamente

### 3. Ver resumen / Copiar
- Tocá **"Ver Semana"** para ver el resumen completo
- Tocá **"Copiar"** para copiar todo al portapapeles
- Pegalo donde quieras (WhatsApp, notas, etc.)

### 4. Enviar por email (Gmail u otra app de correo)
- En la vista **"Resumen Semanal"**, escribí el correo destino
- Tocá **"Enviar"**
- Se abre la app de correo con destinatario, asunto y cuerpo ya armados
- Asunto automático: **Resultados GYM: DD/MM - DD/MM**
    - El rango se calcula desde el primer día de la rutina cargada hasta el último día de esa misma rutina (por ejemplo, LUNES a VIERNES)

### 5. Nueva semana
- Tocá **"Nueva"** → te pregunta si querés guardar en historial antes de borrar
- Las semanas guardadas se pueden ver desde la pantalla de carga

### 6. Nota de compatibilidad (botón Enviar)
- En el navegador integrado de VS Code, el `mailto:` puede no abrir nada por sandbox
- En celular (Chrome / APK instalada), sí abre Gmail o el cliente de correo configurado

---

## Estructura del proyecto

```
GymTracker/
├── index.html                              ← Abrí esto en Chrome directamente
├── README.md
├── settings.gradle.kts
├── build.gradle.kts
├── gradle.properties
├── gradle/wrapper/gradle-wrapper.properties
└── app/
    ├── build.gradle.kts
    ├── proguard-rules.pro
    └── src/main/
        ├── AndroidManifest.xml
        ├── assets/index.html               ← La app (mismo archivo)
        ├── java/com/gymtracker/
        │   └── MainActivity.kt             ← WebView wrapper
        └── res/
            ├── drawable/ic_launcher_foreground.xml
            ├── mipmap-anydpi-v26/ic_launcher.xml
            ├── mipmap-anydpi-v26/ic_launcher_round.xml
            └── values/
                ├── strings.xml
                ├── colors.xml
                └── themes.xml
```
