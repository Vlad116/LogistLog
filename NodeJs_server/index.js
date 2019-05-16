var express = require('express');
// var multer = require('multer');
// var fs = require('fs');
var app = express();
app.use(express.static('public'));
// app.post('/upload', function (req, res, next) {
//     var headers = req.headers;
//     var storage = multer.diskStorage({
//         destination: function (req, file, callback) {
//             var dir = './public/files/' + headers.dir + '/';
//             if (!fs.existsSync(dir))
//                 fs.mkdirSync(dir);
//             callback(null, dir);
//         },
//         filename: function (req, file, callback) {
//             callback(null, file.originalname);
//         }
//     });
//     var upload = multer({storage: storage}).array('files');
//     upload(req, res, next);
//     res.end();
// });
// app.post('/uploadPhoto', function (req, res, next) {
//     var headers = req.headers;
//     var storage = multer.diskStorage({
//         destination: function (req, file, callback) {
//             var dir = './public/' + headers.dir + '/';
//             if (!fs.existsSync(dir))
//                 fs.mkdirSync(dir);
//             callback(null, dir);
//         },
//         filename: function (req, file, callback) {
//             callback(null, file.originalname);
//         }
//     });
//     var upload = multer({storage: storage}).array('files');
//     upload(req, res, next);
//     res.end();
// });
console.log("Static NodeJS server started at 80");
app.listen(80);