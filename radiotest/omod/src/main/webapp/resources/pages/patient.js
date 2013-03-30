$j(function(){
	var inputPlaceholders = {
		firstname: "First Name",
		midname: "Middle Initial",
		lastname: "Last Name",
		alias: "Alias",
		type: "Type",
		philhealth: "Philhealth Number",
		birthday: "Birthday",
		institution: "Institution",
		contactno: "Contact Number",
		region: "Region",
		city: "City",
		barangay: "Barangay/Municipality",
		street: "Street Address"
	};
	
	var reqFields =	[
	              		"firstName",
	              		"lastName",
	              		"alias",
	              		"type",
	              		"gender",
	              		"civilStatus",
	              		"birthday",
	              		"contactNo",
	              		"city",
	              		"street"
	              	];
	
	var validObj = {
			required : reqFields,
			birthday : ["past"],
			contactNo : ["phone"]
		}
	
	function addPlaceholders(){
		GeneralUtils.addPlaceholderById(inputPlaceholders);
		GeneralUtils.addDatepicker($j("#birthday"));
	}
	
	function addValidation(){
		var $form = $j("#patientForm");
		var ruleList = ValidationUtils.initRules(validObj);
		ValidationUtils.attachByName(ruleList);
		
		$form.validationEngine({ promptPosition : "bottomRight" });
		$form.validationEngine("attach");
	}
	
	var funcs = {
		newTransaction : function newTransaction(){
			GeneralUtils.redirect("transExamForm.htm");
		},
		
		load_transaction : function loadTransaction(){
			$j("#details").load(GeneralUtils.modulePath("/transactions.htm") + " #details");
		},
		
		load_profile : function loadProfile(){
			$j("#details").load(GeneralUtils.modulePath("/patientProfile.htm") + " #details");
		},
		
		load_results : function loadResults(){
			$j("#details").load(GeneralUtils.modulePath("/results.htm") + " #details");
			
		}, 
		
		editPatient : function editPatient(){
			$j.get(GeneralUtils.modulePath("/editPatient.htm"), function(data){
				var $form = $j("#patient", data);
				$j("#patient").replaceWith($form);
				
				addPlaceholders();
				addValidation();
			});
		},
		
		search : function search(){
			$j.post(GeneralUtils.modulePath("/searchPatient.htm"), $j("#search").serialize(), function(data){
				var url = data.url;
				if(url){
					window.location = openmrsContextPath + url;
				} else {
					var $list = $j("#patientList", $j(data));
					$j("#patientList").replaceWith($list);	
				}
			});
			return false;
		},

		getPatient : function getPatient(pid){
			$j("#patientId").val(pid);
			$j("#patient").submit();
		},

		newPatient : function newPatient(){
			GeneralUtils.redirect("patientForm.htm");
		}
	}
	
	$j.extend(window, funcs);
	
	// MAIN
	if(document.URL.indexOf("patientForm") > -1){
		addPlaceholders();
		addValidation();
	}
});