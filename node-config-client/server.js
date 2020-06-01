'use strict';

// const client = require('cloud-config-client');
const SpringCloudConfig = require('spring-cloud-config');

// client.load({
//     name: 'client-app',
//     endpoint: 'http://localhost:8888'
// })
SpringCloudConfig.load({
    configPath: __dirname + '/config',
    activeProfiles: ['default']
})
.then(config => {
    console.log('As JSON:', JSON.stringify(config, null, ' '));
    // console.log('Raw:', JSON.stringify(config.raw, null, ' '));
    // console.log('Properties:', JSON.stringify(config.properties, null, ' '));
    setImmediate(() => process.exit(0));
})
.catch(err => {
    console.error('Error:', err.message, err);
    setImmediate(() => process.exit(-1));
})
