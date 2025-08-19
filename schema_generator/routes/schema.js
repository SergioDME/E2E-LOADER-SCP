var express = require('express');
var router = express.Router();
const schema_services = require('../services/schema-services');

/* GET users listing. */
router.post('/', async function(req, res, next) {
    try{

        res.json(await schema_services.generate_schema(req.body));
    }catch (err){
        console.error('erro while generating jsonschema',err.message);
        next(err);
    }
});

module.exports = router;
