var express = require("express");
var app = express();
app.use(express.static(__dirname + '/dist'))

app.get('/players/search', () => {
    console.log('Listening on the /players/search');
    res.send('Testing player search api');
});

app.get('/', function(req, res) {
    console.log('Listening on the root path')
    res.status(200).send('OK');
})
app.listen(4200, function(request, response){
    console.log("App Listening on PORT 4200");
});
