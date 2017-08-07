var AWSregion = 'us-east-1';  // us-east-1

var params = {
    TableName: 'yesno',
    Key:{ "id": '0'  }
};


var Alexa = require('alexa-sdk');
var AWS = require('aws-sdk');

AWS.config.update({
    region: AWSregion
});

exports.handler = function(event, context, callback) {
    var alexa = Alexa.handler(event, context);

    alexa.appId = 'amzn1.ask.skill.750ab328-b60c-4bc5-905f-8b6eed5ed5a2';
    // alexa.dynamoDBTableName = 'You=rTableName'; // creates new table for session.attributes

    alexa.registerHandlers(handlers);
    alexa.execute();
};

var handlers = {
    'LaunchRequest': function () {
		var message = 'Welcome to C One, you can start creating an order by saying Create an order';
		var repromt = 'Say create an order';
        this.emit(':ask', message, repromt);
    },
	'CreateAnOrder': function () {
        console.log('Creating order');
		var message = 'Order has been created, to add kits say Add kit';
		var repromt = 'You can say add kit';
		this.emit(':ask', message, repromt);

    },

    'AddAnItem': function () {
		
		console.log('Entered into add an item');
		var message = 'Kit has been added to the order, say Add kit to add another kit or to place an order say place the order';
		var reprompt = 'If you want to add more kits, you can say add kit or to place an order say place the order';
		console.log('current dialog state is: ' + this.event.request.dialogState);
		//if (this.event.request.dialogState === 'STARTED') {
          //  var updatedIntent = this.event.request.intent;
			
            // Pre-fill slots: update the intent object with slot values for which
            // you have defaults, then emit :delegate with this updated intent.
            //updatedIntent.slots.SlotName.value = 'DefaultValue';
            //this.emit(':delegate');
        //} else
		/*if (this.event.request.dialogState !== 'COMPLETED'){
			console.log('all the slots are not completed forwarding to delegate');
            this.emit(':delegate');
        } else {
			console.log('dialog state is completed');
			var kitType = this.event.request.intent.slots.kitType.value;
			var itemQuantity = parseInt(this.event.request.intent.slots.itemQuantity.value);
			console.log('Add ' + kitType + ' of '+itemQuantity);
			this.emit(':ask', message, reprompt);
        }*/
		var intentObj = this.event.request.intent;
        if (!intentObj.slots.kitType.value) {
            console.log('kit type not set');
            var slotToElicit = 'kitType';
            var speechOutput = 'what is the kit name?';
            var repromptSpeech = speechOutput;
			var updatedIntent = intentObj;
            this.emit(':elicitSlot', slotToElicit, speechOutput, repromptSpeech, updatedIntent);
			
        } else if (!intentObj.slots.itemQuantity.value) {
            console.log('quantity not set');
            var slotToElicit = 'itemQuantity';
            var speechOutput = 'What quantities of kit do you need?';
            var repromptSpeech = speechOutput;
            var updatedIntent = intentObj;
            
            this.emit(':elicitSlot', slotToElicit, speechOutput, repromptSpeech, updatedIntent);
        } else {
            console.log('all the slot values are available');
            var kitType = this.event.request.intent.slots.kitType.value;
			var itemQuantity = parseInt(this.event.request.intent.slots.itemQuantity.value);
			console.log('Add ' + kitType + ' of '+itemQuantity);
			this.emit(':ask', message, reprompt);
        }
        
    },
	'RaiseAnOrder': function () {
		var message = 'Order has been placed, it will take few minutes to process it. You can start over saying Create an order';
        console.log('Raising order');
		this.emit(':tell', message);

    },
    'AMAZON.HelpIntent': function () {
        this.emit(':ask', 'This service is used to raise an order. You can start by saying create order', 'try again by saying create order');
    },
    'AMAZON.CancelIntent': function () {
        this.emit(':tell', 'Thank you for using the service. Goodbye!');
    },
    'AMAZON.StopIntent': function () {
        this.emit(':tell', 'Thank you for using the service. Goodbye!');
    }
};

