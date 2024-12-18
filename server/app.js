const express = require('express');
const app = express();
const port = 3000;

// Contenido estático (carpeta public)
app.use(express.static('public'));

// Datos JSON
const data = {
    "categories": ["Peliculas", "Personajes", "Dragones"],
    "items": [
        { "id": 1, "category": "Peliculas", "name": "Cómo entrenar a tu dragón", "photo":"images/pelicula1.png", "description": "En un mundo lleno de dragones, un joven vikingo llamado Hipo se enfrenta al desafío de cazar dragones como parte de su entrenamiento. Sin embargo, Hipo hará una sorprendente amistad con uno de estos dragones, al que llama Desdentao, cambiando su vida y la de su pueblo para siempre." },
        { "id": 2, "category": "Peliculas", "name": "Cómo entrenar a tu dragón 2", "photo":"images/pelicula2.png", "description": "En esta secuela, Hipo y Desdentao han transformado a Berk en un lugar pacífico donde los dragones y los vikingos viven en armonía. Sin embargo, su amistad se ve puesta a prueba cuando Hipo descubre a su madre, quien estaba perdida, y también se enfrenta a un nuevo enemigo: Drago Bludvist, un hombre que busca controlar a los dragones. La película aborda temas de familia, lealtad y el equilibrio entre el poder y la paz." },
        { "id": 3, "category": "Peliculas", "name": "Cómo entrenar a tu dragón 3", "photo":"images/pelicula3.png", "description": "En esta última entrega de la trilogía, Hipo, ahora líder de la aldea de Berk, tiene que enfrentarse a nuevos desafíos mientras busca un refugio secreto para los dragones. La historia se centra en la relación de Hipo con Desdentao y su crecimiento como líder, mientras luchan contra una nueva amenaza: un villano que quiere capturar a los dragones para sus propios fines." },
        { "id": 4, "category": "Peliculas", "name": "Cómo entrenar a tu dragón Vuelta a casa", "photo":"images/pelicula4.png", "description": "En esta película, Hipo y sus amigos deben ayudar a los dragones a encontrar un nuevo hogar lejos de las amenazas humanas. El conflicto surge cuando los vikingos de otras aldeas intentan capturar a los dragones, y Hipo debe hacer un esfuerzo por unir a las tribus vikingas y asegurar un futuro pacífico para todos los dragones y humanos. Esta película muestra el crecimiento del personaje y la importancia de la cooperación." },
        { "id": 5, "category": "Peliculas", "name": "Cómo entrenar a tu dragón Live Action", "photo":"images/pelicula5.png", "description": "Esta adaptación en live action de la popular franquicia lleva la historia de Hipo y Desdentao al mundo real. Con una mezcla de efectos especiales impresionantes y una narrativa emotiva, la película sigue a Hipo mientras lucha por proteger a los dragones de las amenazas que acechan su mundo, mientras forma alianzas inesperadas y enfrenta nuevas adversidades." },
        { "id": 6, "category": "Personajes", "name": "Hipo Horrendo Abadejo III", "photo":"images/hipo.png", "description": "Hipo es un joven vikingo tímido y soñador que se destaca por su inventiva y su capacidad para entender a los dragones. A pesar de no cumplir con las expectativas de su padre, Hipo demuestra que su habilidad para hacer amigos, incluso con las criaturas más temibles, es su mayor fortaleza." },
        { "id": 7, "category": "Personajes", "name": "Astrid Hofferson", "photo":"images/astrid.png", "description": "Astrid es una de las vikingas más valientes y habilidosas de la aldea, conocida por su destreza en el combate y su coraje. Aunque inicialmente es escéptica respecto a la relación de Hipo con los dragones, pronto se convierte en una de sus más fieles aliadas y una heroína en su propia historia." },
        { "id": 8, "category": "Personajes", "name": "Patan Jorgenson", "photo":"images/patan.png", "description": "Patan es un personaje cómico y algo torpe de la aldea vikinga. A pesar de ser un guerrero en entrenamiento, siempre tiene un enfoque peculiar para resolver los problemas. Su lealtad a Hipo y su disposición a ayudar lo hacen un buen amigo, aunque a menudo se encuentra en situaciones cómicas." },
        { "id": 10, "category": "Personajes", "name": "Brusca Torton", "photo":"images/brusca.png", "description": "Brusca Torton es una joven y audaz guerrera de la aldea, conocida por su energía incansable y su temperamento fuerte. Aunque a menudo es impetuosa, Brusca es leal y valiente, y siempre está dispuesta a luchar por lo que es correcto, incluso si sus métodos no siempre son los más convencionales." },
        { "id": 11, "category": "Personajes", "name": "Chusco Laverte Torton", "photo":"images/chusco.png", "description": "Chusco Laverte Torton es el hermano de Brusca, y es un vikingo un poco más relajado y despreocupado. A pesar de su actitud relajada, Chusco es increíblemente hábil con las armas y siempre tiene una perspectiva única en las situaciones. Su sentido del humor y su ingenio lo hacen un personaje carismático y querido por todos en Berk." },
        { "id": 12, "category": "Dragones", "name": "Furia Nocturna", "photo":"images/desdentao.png", "description": "Furia Nocturna es un dragón de aspecto oscuro, rápido y ágil, conocido por su habilidad para volar a altas velocidades y lanzar un tipo de fuego extremadamente potente. Este dragón es especial por su vínculo cercano con Hipo, con quien comparte una conexión profunda que desafía las normas entre dragones y humanos." },
        { "id": 13, "category": "Dragones", "name": "Ala Mortífera", "photo":"images/tormenta.png", "description": "El Ala Mortífera es un dragón feroz con alas largas y poderosas. Este dragón es un formidable cazador, con una habilidad especial para enfrentarse a otros dragones y desafiar a los humanos que se le oponen. Su ferocidad lo hace uno de los dragones más temidos." },
        { "id": 14, "category": "Dragones", "name": "Cremallerus Espantosus", "photo":"images/cremallerus.png", "description": "El Cremallerus Espantosus es un dragón de aspecto temible, con una piel gruesa y escamosa que lo hace casi indestructible. Se caracteriza por su actitud territorial y su capacidad para lanzar ataques devastadores, lo que lo convierte en una de las criaturas más intimidantes." },
        { "id": 15, "category": "Dragones", "name": "Gronckle", "photo":"images/barrilete.png", "description": "El Gronckle es un dragón robusto y con una constitución fuerte, conocido por su capacidad para disparar rocas. A pesar de su apariencia torpe, es un dragón muy valioso y leal, especialmente cuando se encuentra en su hábitat natural, donde sus habilidades de ataque son imbatibles." },
        { "id": 16, "category": "Dragones", "name": "Pesadilla Monstruosa", "photo":"images/garfios.png", "description": "La Pesadilla Monstruosa es un dragón temible con una gran fuerza y agilidad. Su habilidad para crear caos y confusión en su entorno la convierte en una amenaza para cualquier enemigo que se cruce en su camino. Su aspecto es tan aterrador como su naturaleza destructiva." }
    ]
};

// Rutas GET para las categorías
app.get('/api/categories', (req, res) => {
    res.json(data.categories);
});

// Rutas GET para los items de cada categoría
app.get('/api/items', (req, res) => {
    res.json(data.items);
});

// Rutas GET para un item específico por su ID
app.get('/api/items/:id', (req, res) => {
    const item = data.items.find(i => i.id === parseInt(req.params.id));
    if (item) {
        res.json(item);
    } else {
        res.status(404).send('Item no encontrado');
    }
});

// Servir contenido estático desde la carpeta public
app.use(express.static('public'));

// Activar el servidor
const https = require('https');
const fs = require('fs');

const options = {
    key: fs.readFileSync('path_to_your_private_key.key'),
    cert: fs.readFileSync('path_to_your_certificate.crt')
};

https.createServer(options, app).listen(port, () => {
    console.log(`Servidor HTTPS escuchando en https://localhost:${port}`);
});
