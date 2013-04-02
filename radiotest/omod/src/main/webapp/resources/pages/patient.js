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
		var ruleList = ValidationUtils.initRules(validObj);
		ValidationUtils.attachByName(ruleList);
		
		ValidationUtils.attachSubmit("#patientForm");
	}
	
	var funcs = {
		newTransaction : function newTransaction(){
			GeneralUtils.redirect("transExamForm.htm");
		},
		
		load_transaction : function loadTransaction(){
			GeneralUtils.startLoading();
			$j("#details").load(GeneralUtils.modulePath("/transactions.htm") + " #details", function(){
				GeneralUtils.stopLoading();
			});
		},
		
		load_profile : function loadProfile(){
			GeneralUtils.startLoading();
			$j("#details").load(GeneralUtils.modulePath("/patientProfile.htm") + " #details", function(){
				GeneralUtils.stopLoading();
			});
		},
		
		load_results : function loadResults(){
			GeneralUtils.startLoading();
			$j("#details").load(GeneralUtils.modulePath("/results.htm") + " #details", function(){
				ValidationUtils.requireForm("#trans");
				GeneralUtils.stopLoading();
			});
			
		}, 
		
		editPatient : function editPatient(){
			GeneralUtils.startLoading();
			$j.get(GeneralUtils.modulePath("/editPatient.htm"), function(data){
				var $form = $j("#patient", data);
				var $update = $j("#update");
				$update.append($form);
				
				addPlaceholders();
				addValidation();
				
				var dialogConfig = {
						modal : true,
						title : "Update Patient",
						width : "auto",
						height: 600,
						resizable : false,
						close: function() {
							location.reload(true);
					    }
				}
				
				GeneralUtils.stopLoading();
				$update
					.children()
						.detach()
						.dialog(dialogConfig);
			});
		},
		
		search : function search(){
			if($j("#search").validationEngine("validate")){
				GeneralUtils.startLoading();
				$j.post(GeneralUtils.modulePath("/searchPatient.htm"), $j("#search").serialize(), function(data){
					var url = data.url;
					if(url){
						window.location = openmrsContextPath + url;
					} else {
						var $list = $j("#patientList", $j(data));
						$j("#patientList").replaceWith($list);	
					}
					GeneralUtils.stopLoading();
				});
			}
			return false;
		},

		getPatient : function getPatient(pid){
			GeneralUtils.startLoading();
			$j("#patientId").val(pid);
			$j("#patient").submit();
		},

		newPatient : function newPatient(){
			GeneralUtils.redirect("patientForm.htm");
		}
	}
	
	$j.extend(window, funcs);
	
	// MAIN
	if(GeneralUtils.atPage("patientForm")){
		addPlaceholders();
		addValidation();
	} else if(GeneralUtils.atPage("searchPatientForm")){
		ValidationUtils.requireForm("#search");
	}
});