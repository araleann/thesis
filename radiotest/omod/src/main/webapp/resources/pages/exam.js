$j(function(){
	var funcs = {
		loadExam : function loadExam(id){
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/loadExam.htm"),
								{ examId : id },
								"#examForm",
								addValidation);
			
			GeneralUtils.post(postConfig);
		},
		
		saveExam : function saveExam(){
			var form = $j("#form").attr("action", GeneralUtils.modulePath("/examForm.htm"));
			ValidationUtils.submit(form);
		},
		
		voidExam : function voidExam(id){
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/nullExam.htm"),
								GeneralUtils.voidObj("eid", id),
								"#exam" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteExam : function deleteExam(id){
			var postConfig = GeneralUtils.postConfig(
								GeneralUtils.modulePath("/nullExam.htm"),
								GeneralUtils.deleteObj("eid", id),
								"#exam" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		clearForm : function clearForm(){
			GeneralUtils.clearForm();
		}
	};
	
	$j.extend(window, funcs);
	
	var validObj = {
		amount : ["amount"]
	}
	
	function addValidation(){
		var ruleList = ValidationUtils.initRules(validObj);
		ValidationUtils.attachByName(ruleList);
		
		ValidationUtils.requireForm("#form");
		
	}
	
	// MAIN
	addValidation();
});