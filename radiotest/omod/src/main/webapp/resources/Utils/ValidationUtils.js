$j(function(){
	var validAlias = {
		integer : "custom[integer]",
		number : "custom[number]",
		phone : "custom[phone]",
		amount : "custom[amount]",
		past : "past[now]",
		future : "future[now]"
	}
	
	function pushRule(field, ruleArr){
		for(i in ruleArr){
			var rule = ruleArr[i];
			var alias = validAlias[rule];
			
			if(alias){
				field.push(alias);
			} else if(rule instanceof Function){
				field.push("funcCall[" + rule.name + "]");
			} else {
				field.push(rule);
			}
		}
		return field;
	}
	
	window.ValidationUtils = {
		initRules : function initRules(validRules){
			var ruleList = {};
			var fieldIds = validRules["required"];
			
			if(fieldIds){
				for(i in fieldIds){
					ruleList[fieldIds[i]] = ["required"];
				}
				delete validRules["required"];
			}
						
			for(key in validRules){
				var ruleArr = validRules[key];
				var field = ruleList[key];
				
				if(field){
					field = pushRule(field, ruleArr);
				} else {
					ruleList[key] = pushRule([], ruleArr);
				}
			}
			
			return ruleList;
		},
		
		attachById : function attachById(ruleList){
			for(key in ruleList){
				var fieldName = "#" + key;
				var validClass = "validate[" + ruleList[key].join() + "]";
				
				$j(fieldName).addClass(validClass);
			}
		},
		
		attachByName : function attachByName(ruleList){
			for(key in ruleList){
				var fieldName = "[name*='" + key + "']";
				var validClass = "validate[" + ruleList[key].join() + "]";
				
				$j(fieldName).addClass(validClass);
			}
		},
		
		attachSubmit : function attachSubmit(formSel, config){
			conf = {
				promptPosition : "bottomRight",
				validationEventTrigger : "submit"
			}
			
			if(config){
				$j.extend(conf, config);
			}
			
			$j(formSel)
				.validationEngine(conf)
				.validationEngine("attach");
		},
		
		requireForm : function requireForm(formSel){
			$j(formSel)
				.find(":input:not(:button)")
				.each(function(){
					$j(this).addClass("validate[required]");
				});
			
			this.attachSubmit(formSel);
		},
		
		submit : function submit(form){
			var $form = form instanceof jQuery? form : $j(form);
			
			if($form.validationEngine("validate")){
				GeneralUtils.startLoading();
				$form.submit();
			}
		}
	}
});