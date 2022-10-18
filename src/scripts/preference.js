const fs = require('fs');
const xml2js = require('xml2js');
module.exports = async function(context) {
    const root = context.opts.projectRoot;
    const configPath = 'config.xml';
    const configXml = fs.readFileSync(configPath);
    let config, manifest;
    let swift_version = 0;
    xml2js.parseString(configXml, function(__err, __res){
        config = __res;  
        for(let i = 0; i < config.widget.preference.length; i++){	 
            if(config.widget.preference[i]['$'].name === 'UseSwiftLanguageVersion'){
                swift_version = Number(config.widget.preference[i]['$'].value);
                break;
            }		 
        }
        if(!swift_version || swift_version < 4.2){
            config.widget.preference.push({$:{name:'UseSwiftLanguageVersion', value:'5'}});    
            console.log('cordova-plugin-ogx-secure added Swift support version 5');        
        } 
        const newManifest = (new xml2js.Builder()).buildObject(manifest);
        fs.writeFileSync(manifestPath, newManifest);   
    });
}