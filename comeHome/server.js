const express = require('express');
const app = express();

const PORT = process.env.PORT || 8080;

app.use(express.static(__dirname + '/dist/comeHome'));

app.get('/*', (req, res) => {
    res.sendFile(__dirname + 'dist/comeHome/index.html');
});

app.listen(PORT, () => {
    console.log('Servidor iniciado na porta ' + PORT)
});
