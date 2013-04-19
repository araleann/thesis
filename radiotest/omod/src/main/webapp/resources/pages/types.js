$j(function(){
	
	var funcs = {
		clearForm : function clearForm(){
			GeneralUtils.clearForm();
			addPlaceholders();
		},
		
		saveCategory : function saveCategory(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/categoryForm.htm"),
					$j("#catForm").serialize(),
					"#categories",
					clearForm);
			
			if($j("#catForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		voidCategory : function voidCategory(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullCategory.htm"),
					GeneralUtils.voidObj("cid", id),
					"#category" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteCategory : function deleteCategory(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullCategory.htm"),
					GeneralUtils.deleteObj("cid", id),
					"#category" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editCategory : function editCategory(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editCategory.htm"),
					{ cid : id },
					"#catForm",
					loadPage);
			
			GeneralUtils.post(postConfig);
		},
		
		saveExamType : function saveExamType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/examTypeForm.htm"),
					$j("#typeForm").serialize(),
					"#examTypes",
					clearForm);
			
			if($j("#typeForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editExamType : function editExamType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editExamType.htm"),
					{ eid : id },
					"#typeForm",
					loadPage);
			
			GeneralUtils.post(postConfig);
		},
		
		voidExamType : function voidExamType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullExamType.htm"),
					GeneralUtils.voidObj("eid", id),
					"#type" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteExamType : function deleteExamType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullExamType.htm"),
					GeneralUtils.deleteObj("eid", id),
					"#type" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		saveNoteType : function saveNoteType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/noteTypeForm.htm"),
					$j("#typeForm").serialize(),
					"#noteTypes",
					clearForm);
			
			if($j("#typeForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		voidNoteType : function voidNoteType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullNoteType.htm"),
					GeneralUtils.voidObj("nid", id),
					"#note" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteNoteType : function deleteNoteType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullNoteType.htm"),
					GeneralUtils.deleteObj("nid", id),
					"#note" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editNoteType : function editNoteType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editNoteType.htm"),
					{ nid : id },
					"#typeForm",
					loadPage);
			
			GeneralUtils.post(postConfig);
		},
		
		saveFeeType : function saveFeeType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/feeTypeForm.htm"),
					$j("#feeForm").serialize(),
					"#feeTypes",
					clearForm);
			
			if($j("#feeForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		voidFeeType : function voidFeeType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullFeeType.htm"),
					GeneralUtils.voidObj("tid", id),
					"#type" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteFeeType : function deleteFeeType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullFeeType.htm"),
					GeneralUtils.deleteObj("tid", id),
					"#type" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editFeeType : function editFeeType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editFeeType.htm"),
					{ tid : id },
					"#feeForm",
					loadPage);
			
			GeneralUtils.post(postConfig);
		},
		
		saveItemType : function saveItemType(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/itemTypeForm.htm"),
					$j("#typeForm").serialize(),
					"#itemTypes",
					clearForm);
			
			if($j("#typeForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		voidItemType : function voidItemType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullItemType.htm"),
					GeneralUtils.voidObj("tid", id),
					"#type" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteItemType : function deleteItemType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullItemType.htm"),
					GeneralUtils.deleteObj("tid", id),
					"#type" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editItemType : function editItemType(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editItemType.htm"),
					{ tid : id },
					"#typeForm",
					loadPage);
			
			GeneralUtils.post(postConfig);
		},
		
		saveSignature : function saveSignature(){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/signForm.htm"),
					$j("#signForm").serialize(),
					"#signatures",
					clearForm);
			
			if($j("#signForm").validationEngine("validate")){
				GeneralUtils.post(postConfig);
			}
		},
		
		voidSignature : function voidSignature(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullSignature.htm"),
					GeneralUtils.voidObj("sid", id),
					"#sign" + id);
			
			GeneralUtils.post(postConfig);
		},
		
		deleteSignature : function deleteSignature(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/nullSignature.htm"),
					GeneralUtils.deleteObj("sid", id),
					"#sign" + id);
			
			if(confirm("Are you sure you want to delete?")){
				GeneralUtils.post(postConfig);
			}
		},
		
		editSignature : function editSignature(id){
			var postConfig = GeneralUtils.postConfig(
					GeneralUtils.modulePath("/editSignature.htm"),
					{ sid : id },
					"#signForm",
					loadPage);
			
			GeneralUtils.post(postConfig);
		}
	};
	
	function addPlaceholders(){
		if(GeneralUtils.atPage("categoryForm")){
			GeneralUtils.addPlaceholderById("category", "Enter a category");
		} else if(GeneralUtils.atPage("examTypeForm")){
			var examPh = {
				examtype : "Enter Exam Type",
				template : "Template for Negative Results"
			}
			GeneralUtils.addPlaceholderById(examPh);
			GeneralUtils.addPlaceholderById("examtype", "Enter Exam Type");
		} else if(GeneralUtils.atPage("noteTypeForm")){
			GeneralUtils.addPlaceholderById("notetype", "Enter Note Type");
		} else if(GeneralUtils.atPage("feeTypeForm")){
			GeneralUtils.addPlaceholderById("feetype", "Enter Fee Type");
		} else if(GeneralUtils.atPage("itemTypeForm")){
			GeneralUtils.addPlaceholderById("itemtype", "Enter Item Type");
		} else if(GeneralUtils.atPage("signForm")){
			var signPh = {
				name : "Enter Name of Doctor",
				position : "Enter Position"
			}
			GeneralUtils.addPlaceholderById(signPh);
		}
	}
	
	function addValidation(){
		ValidationUtils.requireForm("form");
		ValidationUtils.attachSubmit("form");
	}
	
	function loadPage(){
		addPlaceholders();
		if(GeneralUtils.atPage("examTypeForm")){
			ValidationUtils.attachSubmit("form");
		} else {
			addValidation();
		}
	}
	
	$j.extend(window, funcs);
	
	// MAIN
	loadPage();
});